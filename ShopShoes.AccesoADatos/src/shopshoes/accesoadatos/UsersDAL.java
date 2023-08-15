/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.accesoadatos;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shopshoes.entidadesdenegocio.Users;
import java.time.LocalDate;

/**
 *
 * @author alexg
 */
public class UsersDAL {
    public static String encriptarMD5(String txt) throws Exception 
    {
     try {
          StringBuffer sb;
          java.security.MessageDigest md = java.security.MessageDigest
                .getInstance("MD5");
          byte[] array = md.digest(txt.getBytes());
          sb = new StringBuffer();
          for (int i = 0; i < array.length; ++i) 
          {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
          .substring(1, 3));
         }
         return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
        throw ex;
        }
    }
    
    static String obtenerCampos()
    {
        return "u.Id, u.IdRol, u.UserName, u.RegistrationDate, u.Mail";
    }
    
    private static String obtenerSelect(Users pUsers)
    {
        String sql;
        sql = "Select ";
        if(pUsers.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pUsers.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Users u");
        return sql;
    }
    
    private static String agregarOrderBy(Users pUsers)
    {
        String sql = " Order by u.Id Desc";
        if(pUsers.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pUsers.getTop_aux()+ " ";
        }
        return sql;
    }
    
    private static boolean existeLogin(Users pUsers) throws Exception {
        boolean existe = false;
        ArrayList<Users> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsers);  // Obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE y el filtro para saber si existe el login
            sql += " WHERE u.Id<>? AND u.Mail=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pUsers.getId());  // Agregar el parametros a la consulta donde estan el simbolo ? #1 
                ps.setString(2, pUsers.getMail());  // Agregar el parametros a la consulta donde estan el simbolo ? #2 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de USuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement el en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (!usuarios.isEmpty()) { // Verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            Users users;
             // Se solucciono tenia valor de 1 cuando debe de ser cero
            users = usuarios.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero 
            if (users.getId() > 0 && users.getMail().equals(pUsers.getMail())) {
                // Si el Id de Usuario es mayor a cero y el Login que se busco en la tabla de Usuario es igual al que solicitamos
                // en los parametros significa que el login ya existe en la base de datos y devolvemos true en la variable "existe"
                existe = true;
            }
        }
        return existe; //Devolver la variable "existe" con el valor true o false si existe o no el Login en la tabla de Usuario de la base de datos

    }
    
    public static int crear(Users pUsers) throws Exception
    {
        int result;
        String sql;
        boolean existe = existeLogin(pUsers);
        if(existe == false)
        {
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Users(IdRol, UserName , Pass"
                        + ", RegistrationDate) Values"
                        + "(?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pUsers.getId());
                    st.setString(2, pUsers.getUserName());
                    st.setString(3, encriptarMD5(pUsers.getPass()));
                    st.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                    st.setString(5, pUsers.getMail());
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
        }
        else
        {
            result=0;
            throw new RuntimeException("Login ya Existe");
        }
        return result;
    }
    
    static int asignarDatosResultSet(Users pUsuario, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pUsuario.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pUsuario.setIdRol(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pUsuario.setUserName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pUsuario.setRegistrationDate(pResultSet.getDate(pIndex).toLocalDate()); // index 4
        pIndex++;
        pUsuario.setMail(pResultSet.getString(pIndex)); // index 5
        return pIndex;
    }
    
     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Users> pUsers) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Users users = new Users();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(users, resultSet, 0);
                pUsers.add(users); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
     }
     
     public static Users login(Users pUsers) throws Exception
    {
        Users usuario = new Users();
        ArrayList<Users> usuarios = new ArrayList();
        String password = encriptarMD5(pUsers.getPass());
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pUsers);
            sql += " Where u.Mail = ? And u.Pass = ? And ";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pUsers.getMail());
                ps.setString(2, password);
                obtenerDatos(ps, usuarios);
                ps.close();
            }
            catch(SQLException ex)
            {
                throw ex;
            }
            conn.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        if(!usuarios.isEmpty())
        {
            usuario = usuarios.get(0);
        }
        return usuario;
    }
}

