/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.accesoadatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shopshoes.entidadesdenegocio.Client;

/**
 *
 * @author alexg
 */
public class ClientDAL {

    static String obtenerCampos() {
        return "c.Id, c.IdUser, c.ClientName, c.LastName, c.TelefoneNumber, c.ClientAddress";
    }

    private static String obtenerSelect(Client pClient) {
        String sql;
        sql = "Select ";
        if (pClient.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pClient.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Client c");
        return sql;
    }

    private static String agregarOrderBy(Client pClient) {
        String sql = " Order by c.Id Desc";
        if (pClient.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pClient.getTop_aux() + " ";
        }
        return sql;
    }

    public static int crear(Client pClient) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into Client(IdUser, ClientName, LastName, TelefoneNumber, ClientAddress) "
                    + "Values(?,?,?,?,?)";
            try (PreparedStatement st
                    = ComunDB.createPreparedStatement(conn, sql);) {
                st.setInt(1, pClient.getIdUser());
                st.setString(2, pClient.getClientName());
                st.setString(3, pClient.getLastName());
                st.setString(4, pClient.getTelefoneNumber());
                st.setString(5, pClient.getClientAddress());
                result = st.executeUpdate();
                st.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return result;
    }

    public static int modificar(Client pClient) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update Client Set IdUSer = ?, ClientName = ?, LastName = ?, TelefoneNumber = ?, ClientAddress = ?"
                    + " Where IdUser = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pClient.getIdUser());
                ps.setString(2, pClient.getClientName());
                ps.setString(3, pClient.getLastName());
                ps.setString(4, pClient.getTelefoneNumber());
                ps.setString(5, pClient.getClientAddress());
                ps.setInt(6, pClient.getIdUser());
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

    public static int eliminar(Client pClient) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From Client Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pClient.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    static int asignarDatosResultSet(Client pClient, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pClient.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pClient.setIdUser(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pClient.setClientName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pClient.setLastName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pClient.setTelefoneNumber(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pClient.setClientAddress(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Client> pClient) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Client client = new Client();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(client, resultSet, 0);
                pClient.add(client); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

    public static Client obtenerPorId(int inicio) throws Exception {
        Client client = new Client();
        ArrayList<Client> clients = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "Select " + (obtenerCampos() + " From Client c"); // obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE c.IdUser=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, inicio);
                // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, clients); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (clients.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            client = clients.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return client; // devolver el Usuario encontrado por Id 
    }

    public static ArrayList<Client> obtenerTodos() throws Exception {
        ArrayList<Client> clients = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Client());
            sql += agregarOrderBy(new Client());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, clients);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return clients;
    }
}
