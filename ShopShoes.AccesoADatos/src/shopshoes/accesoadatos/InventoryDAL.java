/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */

import java.util.*;
import java.sql.*;
import shopshoes.entidadesdenegocio.*;

public class InventoryDAL {
    static String obtenerCampos()
    {
        return "i.Id, i.IdProduct, i.Stock, i.Tickets, i.Departures, i.Profits, i.InventoryStatus";
    }
    
    private static String obtenerSelect(Inventory pInventory)
    {
        String sql;
        sql = "Select ";
        if(pInventory.getTopAux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pInventory.getTopAux() + " ";
        }
        sql += (obtenerCampos() + " From Inventory i");
        return sql;
    }
    
    private static String agregarOrderBy(Inventory pInventory)
    {
        String sql = " Order by i.Id Desc";
        if(pInventory.getTopAux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pInventory.getTopAux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Inventory pInventory) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Inventory(IdProduct, Stock, Tickets, Departures, Profits, InventoryStatus) "
                        + "Values(?,?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pInventory.getIdProduct());
                    st.setInt(2, pInventory.getStock());
                    st.setInt(3, pInventory.getTickets());
                    st.setInt(4, pInventory.getDepartures());
                    st.setDouble(5, pInventory.getProfits());
                    st.setByte(6, Byte.parseByte("1"));
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
    
    public static int modificar(Inventory pInventory) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Inventory Set Stock = ?, Tickets= ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                 
                 ps.setInt(1, pInventory.getStock());
                 ps.setInt(2, pInventory.getTickets());
                ps.setInt(3, pInventory.getId());
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
    
    
    public static int modificarSalidas(Inventory pInventory) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Inventory Set Stock = ?, Departures= ?, Profits=?"
                    + " Where IdProduct = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                 
                 ps.setInt(1, pInventory.getStock());
                 ps.setInt(2, pInventory.getDepartures());
                ps.setDouble(3, pInventory.getProfits());
                ps.setInt(4, pInventory.getIdProduct());
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
    
    
    public static int eliminar(Inventory pInventory) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Inventory Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pInventory.getId());
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
    
    static int asignarDatosResultSet(Inventory pInventory, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pInventory.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pInventory.setIdProduct(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pInventory.setStock(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pInventory.setTickets(pResultSet.getInt(pIndex)); // index 4
        pIndex++;
        pInventory.setDepartures(pResultSet.getInt(pIndex)); // index 5
        pIndex++;
        pInventory.setProfits(pResultSet.getDouble(pIndex)); // index 6
        pIndex++;
        pInventory.setInventoryStatus(pResultSet.getByte(pIndex)); // index 7
        return pIndex;
    }
    

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Inventory> pInventory) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Inventory inventory = new Inventory();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(inventory, resultSet, 0);
                pInventory.add(inventory); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Inventory obtenerPorId(Inventory pInventory) throws Exception
    {
        Inventory inventory = new Inventory();
        ArrayList<Inventory> inventories = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pInventory);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pInventory.getId());
                obtenerDatos(ps, inventories);
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
        if(inventories.size() > 0)
        {
            inventory = inventories.get(0);
        }
        return inventory;
    }
    public static Inventory obtenerPorIdProduct(Inventory pInventory) throws Exception
    {
        Inventory inventory = new Inventory();
        ArrayList<Inventory> inventories = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pInventory);
            sql += " Where IdProduct = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pInventory.getIdProduct());
                obtenerDatos(ps, inventories);
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
        if(inventories.size() > 0)
        {
            inventory = inventories.get(0);
        }
        return inventory;
    }
    
    public static ArrayList<Inventory> obtenerTodos() throws Exception
    {
        ArrayList<Inventory> inventories = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Inventory());
            sql += agregarOrderBy(new Inventory());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, inventories);
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
        
        return inventories;
    }
    
     static void querySelect(Inventory pInventory, ComunDB.UtilQuery pUtilQuery) throws SQLException {
         
        PreparedStatement statement = pUtilQuery.getStatement(); 
  
        if (pInventory.getId() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" i.Id=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pInventory.getId());
            }
        }
        
       
        if (pInventory.getStock()> 0) {
            pUtilQuery.AgregarWhereAnd(" p.IdCategory=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pInventory.getStock());
            }
        }
        
        if (pInventory.getTickets()> 0) {
            pUtilQuery.AgregarWhereAnd(" i.Tickets=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pInventory.getTickets());
            }
        }
        
        if (pInventory.getInventoryStatus()> 0) {
            pUtilQuery.AgregarWhereAnd(" i.InventoryStatus=? "); // agregar el campo Estatus al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Estatus a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pInventory.getInventoryStatus());
            }
        }
        
       

    }
     
     
    
    private static void obtenerDatosIncluirInventory(PreparedStatement pPS, ArrayList<Inventory> pInventory) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Products> rolMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                Inventory inventory = new Inventory();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(inventory, resultSet, 0);
                if (rolMap.containsKey(inventory.getIdProduct()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Products product = new Products();
                    // en el caso que el Rol no este en el HashMap se asignara
                    ProductsDAL.asignarDatosResultSet(product, resultSet, index);
                    rolMap.put(product.getId(), product); // agregar el Rol al  HashMap
                    inventory.setProduct(product); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    inventory.setProduct(rolMap.get(inventory.getIdProduct())); 
                }
                pInventory.add(inventory); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

    
    public static ArrayList<Inventory> buscarIncluirProduct(Inventory pInventory) throws Exception {
        ArrayList<Inventory> inventories = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pInventory.getTopAux()> 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pInventory.getTopAux()+ " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += ProductsDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Inventory i";
            sql += " JOIN Products p on (p.Id=i.IdProduct)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pInventory, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pInventory); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pInventory, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirInventory(ps, inventories);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return inventories; // Devolver el ArrayList de Usuario
    }
    
   
}
