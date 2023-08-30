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

    public static String encriptarMD5(String txt) throws Exception {
        try {
            StringBuffer sb;
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw ex;
        }
    }

    static String obtenerCampos() {
        return "u.Id, u.IdRol, u.UserName, u.RegistrationDate, u.Mail";
    }

    private static String obtenerSelect(Users pUsers) {
        String sql;
        sql = "Select ";
        if (pUsers.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pUsers.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Users u");
        return sql;
    }

    private static String agregarOrderBy(Users pUsers) {
        String sql = " Order by u.Id Desc";
        if (pUsers.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pUsers.getTop_aux() + " ";
        }
        return sql;
    }

    private static boolean existeLogin(Users pUsers) throws Exception {
        boolean existe = false;
        ArrayList<Users> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsers);  // Obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE y el filtro para saber si existe el login
            sql += " WHERE u.UserName=? AND u.Mail=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pUsers.getUserName());  // Agregar el parametros a la consulta donde estan el simbolo ? #1 
                ps.setString(2, pUsers.getMail());  // Agregar el parametros a la consulta donde estan el simbolo ? #2 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de USuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement el en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
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

    public static int crear(Users pUsers) throws Exception {
        int result;
        String sql;
        boolean existe = existeLogin(pUsers);

        if (existe == false) {
            try (Connection conn = ComunDB.obtenerConexion();) {
                sql = "Insert Into Users(IdRol, UserName , Pass"
                        + ", RegistrationDate, Mail) Values"
                        + "(?,?,?,?,?)";
                try (PreparedStatement st
                        = ComunDB.createPreparedStatement(conn, sql);) {

                    st.setInt(1, 2);
                    st.setString(2, pUsers.getUserName());
                    st.setString(3, encriptarMD5(pUsers.getPass()));
                    st.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                    st.setString(5, pUsers.getMail());
                    result = st.executeUpdate();
                } catch (SQLException ex) {
                    throw ex;
                }
            } catch (SQLException ex) {
                throw ex;
            }

        } else {
            result = 0;
            throw new RuntimeException("Login ya Existe");
        }
        return result;
    }

    public static int modificar(Users pUsers) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update Users Set IdRol = ?, UserName = ?, RegistrationDate = ?, Mail = ?"
                    + " Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, 2);
                ps.setString(2, pUsers.getUserName());
                ps.setDate(3, java.sql.Date.valueOf(pUsers.getRegistrationDate()));
                ps.setString(4, pUsers.getMail());
                ps.setInt(5, pUsers.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static Users obtenerPorData(Users pUsuario) throws Exception {
        Users usuario = new Users();
        ArrayList<Users> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsuario); // obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE u.UserName=? AND u.Mail=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pUsuario.getUserName());
                ps.setString(2, pUsuario.getMail());// agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (usuarios.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            usuario = usuarios.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return usuario; // devolver el Usuario encontrado por Id 
    }

    public static Users obtenerPorId(int inicio) throws Exception {
        Users usuario = new Users();
        ArrayList<Users> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "Select " + (obtenerCampos() + " From Users u"); // obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE u.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, inicio);
                // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (usuarios.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            usuario = usuarios.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return usuario; // devolver el Usuario encontrado por Id 
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

    public static Users login(Users pUsers) throws Exception {
        Users usuario = new Users();
        ArrayList<Users> usuarios = new ArrayList();
        String password = encriptarMD5(pUsers.getPass());
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pUsers);
            sql += " Where u.UserName = ? And u.Pass = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pUsers.getUserName());
                ps.setString(2, password);
                obtenerDatos(ps, usuarios);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        if (!usuarios.isEmpty()) {
            usuario = usuarios.get(0);
        }
        return usuario;
    }
    
    public static int cambiarPassword(Users pUsuario, String pPasswordAnt) throws Exception {
        int result;
        String sql;
        Users usuarioAnt = new Users();
        usuarioAnt.setUserName(pUsuario.getUserName());
        usuarioAnt.setPass(pPasswordAnt);
        Users usuarioAut = login(usuarioAnt); // Obtenemos el Usuario autorizado validandolo en el metodo de login
        // Si el usuario que retorno el metodo de login tiene el Id mayor a cero y el Login es igual que el Login del Usuario que viene
        // en el parametro es un Usuario Autorizado
        if (usuarioAut.getId() > 0 && usuarioAut.getUserName().equals(pUsuario.getUserName())) {
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                sql = "UPDATE Users SET Pass=? WHERE Id=?"; // Crear la consulta Update a la tabla de Usuario para poder modificar el Password
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                    // Agregar el parametro a la consulta donde estan el simbolo ? #1 pero antes encriptar el password para enviarlo encriptado
                    ps.setString(1, encriptarMD5(pUsuario.getPass())); //
                    ps.setInt(2, pUsuario.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #2 
                    result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                    ps.close(); // Cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
                }
                conn.close(); // Cerrar la conexion a la base de datos
            }
            catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
            }
        } else {
            result = 0;
            // Enviar la excepcion en el caso que el usuario que intenta modificar el password ingresa un password incorrecto
            throw new RuntimeException("El password actual es incorrecto");
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }
    
    static void querySelect(Users pUsuario, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pUsuario.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" u.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pUsuario.getId());
            }
        }
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getIdRol() > 0) {
            pUtilQuery.AgregarWhereAnd(" u.IdRol=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pUsuario.getIdRol());
            }
        }
        // verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Usuario
        if (pUsuario.getUserName()!= null && pUsuario.getUserName().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" u.UserName LIKE ? "); // agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%" + pUsuario.getUserName()+ "%");
            }
        }
        
    }
    
    public static ArrayList<Users> buscar(Users pUsuario) throws Exception {
        ArrayList<Users> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsuario); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pUsuario, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pUsuario); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pUsuario, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return usuarios; // Devolver el ArrayList de Usuario
    }
    
}
