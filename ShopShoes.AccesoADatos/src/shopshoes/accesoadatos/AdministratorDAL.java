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
import java.util.HashMap;
import shopshoes.entidadesdenegocio.*;

public class AdministratorDAL {
    
     static String obtenerCampos()
     {
         return"p.Id, p.IdUser, p.AdministratorName, p.LastName";
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
                sql = "Insert Into Administrator(IdUser, AdministratorName, LastName) "
                        + "Values(?,?, ?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pAdministrator.getIdUser());
                    st.setString(2, pAdministrator.getAdministratorName());
                    st.setString(3, pAdministrator.getLastName());
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
            sql = "Update Administrator Set IdUser = ?, AdministratorName= ?, LastName = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                
              ps.setInt(1, pAdministrator.getIdUser());
              ps.setString(2, pAdministrator.getAdministratorName());
              ps.setString(3, pAdministrator.getLastName());
              ps.setInt(4, pAdministrator.getId());
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
        pAdministrator.setAdministratorName(pResultSet.getString(pIndex));
        pIndex++;
        pAdministrator.setLastName(pResultSet.getString(pIndex));
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
       
       
      public static Administrator obtenerPorId(int id) throws Exception
    {
        Administrator Administrator = new Administrator();
        ArrayList<Administrator> Administrators= new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            
            String sql = "Select*From Administrator Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, id);
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
           
    public static ArrayList<Administrator> obtenerTodos() throws Exception
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
    
    static void querySelect(Administrator pAdministrator, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pAdministrator.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" p.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pAdministrator.getId());
            }
        }
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pAdministrator.getIdUser()> 0) {
            pUtilQuery.AgregarWhereAnd(" p.IdUser=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pAdministrator.getIdUser());
            }
        }
        
        if (pAdministrator.getAdministratorName()!= null && pAdministrator.getAdministratorName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.AdministratorName Like ? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%"+pAdministrator.getAdministratorName()+"%");
            }
        }
       
        if (pAdministrator.getLastName()!= null && pAdministrator.getLastName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.LastName Like ? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%"+pAdministrator.getLastName()+"%");
            }
        }
       
        
    }
    
     private static void obtenerDatosIncluirInventory(PreparedStatement pPS, ArrayList<Administrator> pAdministrator) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Users> rolMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                Administrator administrator = new Administrator();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(administrator, resultSet, 0);
                if (rolMap.containsKey(administrator.getIdUser()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Users user = new Users();
                    // en el caso que el Rol no este en el HashMap se asignara
                    UsersDAL.asignarDatosResultSet(user, resultSet, index);
                    rolMap.put(user.getId(), user);
             
                    administrator.setUser(user); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    administrator.setUser(rolMap.get(administrator.getIdUser())); 
                }
                pAdministrator.add(administrator); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
     
     private static void obtenerDatosIncluirInventory2(PreparedStatement pPS, Administrator pAdministrator) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Users> rolMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                Administrator administrator = new Administrator();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(administrator, resultSet, 0);
                if (rolMap.containsKey(administrator.getIdUser()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Users user = new Users();
                    // en el caso que el Rol no este en el HashMap se asignara
                    UsersDAL.asignarDatosResultSet(user, resultSet, index);
                    rolMap.put(user.getId(), user);
             
                    administrator.setUser(user); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    administrator.setUser(rolMap.get(administrator.getIdUser())); 
                }
                pAdministrator.setAdministratorName(administrator.getAdministratorName()); 
                pAdministrator.setLastName(administrator.getLastName());
                pAdministrator.setId(administrator.getId());
                pAdministrator.setIdUser(administrator.getIdUser());
                pAdministrator.setUser(administrator.getUser());

               
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    public static ArrayList<Administrator> buscarIncluirUsers(Administrator pAdministrator) throws Exception {
        ArrayList<Administrator> administrator = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pAdministrator.getTop_aux()> 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pAdministrator.getTop_aux()+ " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += UsersDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Administrator p";
            sql += " JOIN Users u on (u.Id=p.IdUser)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pAdministrator, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pAdministrator); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pAdministrator, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirInventory(ps, administrator);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return administrator; // Devolver el ArrayList de Usuario
    }
   
    
    
     public static Administrator buscarIncluirUsers2(Administrator pAdministrator) throws Exception {
        Administrator administrator = new Administrator();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pAdministrator.getTop_aux()> 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pAdministrator.getTop_aux()+ " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += UsersDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Administrator p";
            sql += " JOIN Users u on (u.Id=p.IdUser)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pAdministrator, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pAdministrator); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pAdministrator, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirInventory2(ps, administrator);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return administrator; // Devolver el ArrayList de Usuario
    }
}


       
       
       
       
       


    
     

