/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import java.sql.*;
import java.util.ArrayList;
import shopshoes.entidadesdenegocio.Category;

public class CategoryDAL {
    
 static String obtenerCampos()
     {
         return"p.Id,p.pCategoryName,p.CategoryImage";
     }
 
  private static String obtenerSelect(Category pCategory)
    {
        String sql;
        sql = "Select ";
        if(pCategory.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pCategory.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Category p");
        return sql;
    }
  
  private static String agregarOrderBy(Category pCategory)
    {
        String sql = " Order by p.Id Desc";
        if(pCategory.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pCategory.getTop_aux()+ " ";
        }
        return sql;
    }
  
 public static int crear(Category pCategory) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Category( CategoryName,CategoryImage) "
                        + "Values(?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setString(1, pCategory.getCategoryName());
                    st.setByte(2, pCategory.getCategoryImage());
                    result = st.executeUpdate();
                    st.close();
                }
                catch(SQLException ex)
                {
                    throw ex;
                }
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        
        return result;
    }
 
 public static int modificar(Category pCategory) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Category Set IdUser = ?, CategoryName= ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                
              ps.setInt(1, pCategory.getId());
              ps.setString(2, pCategory.getCategoryName());
              
                  result = ps.executeUpdate();
                ps.close();
            }
            
             catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        return result;
    }

  public static int eliminar(Category pCategory) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Category Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pCategory.getId());
                result = ps.executeUpdate();
                ps.close();
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        return result;
    }
  
   static int asignarDatosResultSet(Category pCategory, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pCategory.setId(pResultSet.getInt(pIndex)); // index 1
          pCategory.setCategoryName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        return pIndex;
    }
   
 private static void obtenerDatos(PreparedStatement pPS, ArrayList<Category> pCategorys) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Category Category = new Category();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(Category, resultSet, 0);
                pCategorys.add(Category); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
 
      public static Category obtenerPorId(Category pCategory) throws Exception
    {
        Category category = new Category();
        ArrayList<Category> categories = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pCategory);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pCategory.getId());
                obtenerDatos(ps, categories);
                ps.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        if(categories.size() > 0)
        {
           category= categories.get(0);
        }
        return category;
    }
      
    public static ArrayList<Category> obtenerTodos() throws Exception
    {
        ArrayList<Category> Category = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Category());
            sql += agregarOrderBy(new Category());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, Category);
                ps.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
        return Category;
    }
}

      
      

