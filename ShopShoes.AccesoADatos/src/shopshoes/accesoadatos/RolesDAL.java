/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shopshoes.entidadesdenegocio.Roles;
public class RolesDAL {
     static String obtenerCampos()
     {
         return "p.Id,P.RolesName";
     }
      private static String obtenerSelect(Roles pRoles)
    {
        String sql;
        sql = "Select ";
        if(pRoles.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pRoles.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Roles  p");
        return sql;
    }
      
     private static String agregarOrderBy(Roles pRoles)
    {
        String sql = " Order by p.Id Desc";
        if(pRoles.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pRoles.getTop_aux()+ " ";
        }
        return sql;
    }

public static int crear(Roles pRoles) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Roles(RolesName) "
                        + "Values(?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                   
                    st.setString(1, pRoles.getRolesName());
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

 public static int modificar( Roles pRoles) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Roles Set RolesName = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pRoles.getRolesName());
                ps.setInt(2, pRoles.getId());
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
 
 public static ArrayList<Roles> obtenerPorName(String id) throws Exception
    {
      
        ArrayList<Roles> roles = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = "Select " + (obtenerCampos() + " From Roles p"); // obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE p.RolesName Like ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, "%"+id+"%");
                obtenerDatos(ps, roles);
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
 
        return roles;
    }
 
    public static int eliminar(Roles pRoles) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Roles Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pRoles.getId());
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
    
static int asignarDatosResultSet(Roles pRoles, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pRoles.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pRoles.setRolesName(pResultSet.getString(pIndex)); // index 3
         pIndex++;
        return pIndex;
    }

private static void obtenerDatos(PreparedStatement pPS, ArrayList<Roles> pRoles) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Roles Roles = new Roles();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(Roles, resultSet, 0);
                pRoles.add(Roles); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
public static Roles obtenerPorId(Roles pRoles) throws Exception
    {
        Roles Roles = new Roles();
        ArrayList<Roles> Roless = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pRoles);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pRoles.getId());
                obtenerDatos(ps, Roless);
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
        if(!Roless.isEmpty())
        {
           Roles = Roless.get(0);
        }
        return Roles;
    }

  public static Roles obtenerPorId2(int id) throws Exception
    {
        Roles Roles = new Roles();
        ArrayList<Roles> roles = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
           
            String sql = "Select*from Roles Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, id);
                obtenerDatos(ps, roles);
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
        if(!roles.isEmpty())
        {
           Roles = roles.get(0);
        }
        return Roles;
    }
    
    public static ArrayList<Roles> obtenerTodos() throws Exception
    {
        ArrayList<Roles> Roles = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Roles());
            sql += agregarOrderBy(new Roles());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, Roles);
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
        
        return Roles;
    }
}
     




