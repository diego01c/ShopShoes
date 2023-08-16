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
import java.util.HashSet;
import shopshoes.entidadesdenegocio.*;

public class AdministratorDAL {
    
     static String obtenerCampos()
     {
         return"p.Id,p.IdUser,pAdministratorName";
     }
     
 private static String obtenerSelect(Administrator pAdministrator)
    {
        String sql;
        sql = "Select ";
        if(pAdministrator.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pAdministrator.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Administrator p");
        return sql;
    }
 
 private static String agregarOrderBy(Administrator pAdministrator)
    {
        String sql = " Order by p.Id Desc";
        if(pAdministrator.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pAdministrator.getTop_aux()+ " ";
        }
        return sql;
    }
 
public static int crear(Administrator pAdministrator) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Administrator(IdUser, AdministratorName,) "
                        + "Values(?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pAdministrator.getIdUser());
                    st.setString(2, pAdministrator.getAdministratorName());
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

public static int modificar(Administrator pAdministrator) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Administrator Set IdUser = ?, AdministratorName= ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                
              ps.setInt(1, pAdministrator.getIdUser());
              ps.setString(2, pAdministrator.getAdministratorName());
              
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

  public static int eliminar(Administrator pAdministrator) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Administrator Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pAdministrator.getId());
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
  
     static int asignarDatosResultSet(Administrator pAdministrator, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pAdministrator.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pAdministrator.setIdUser(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pAdministrator.setAdministratortName(pResultSet.getString(pIndex));
        return pIndex;
     }
     
       private static void obtenerDatos(PreparedStatement pPS, ArrayList<Administrator> pAdministrator) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Administrator Administrator = new Administrator();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(Administrator, resultSet, 0);
                pAdministrator.add(Administrator); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
       }
       
       
      public static Administrator obtenerPorId(Administrator pAdministrator) throws Exception
    {
        Administrator Administrator = new Administrator();
        ArrayList<Administrator> Administrators= new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pAdministrator);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pAdministrator.getId());
                obtenerDatos(ps, Administrators);
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
        if(!Administrators.isEmpty())
        {
           Administrator = Administrators.get(0);
        }
        return Administrator;
    }
           
    public static ArrayList<    Administrator> obtenerTodos() throws Exception
    {
        ArrayList<Administrator> Administrator = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Administrator());
            sql += agregarOrderBy(new Administrator());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, Administrator);
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
        
        return Administrator;
    }
}


       
       
       
       
       


    
     

