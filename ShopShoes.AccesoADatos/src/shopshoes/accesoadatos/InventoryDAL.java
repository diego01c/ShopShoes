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
                    st.setByte(6, pInventory.getInventoryStatus());
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
            sql = "Update Inventory Set IdProduct= ?, Stock = ?, Tickets= ?, Departures = ?, Profits = ?, InventoryStatus = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                 ps.setInt(1, pInventory.getIdProduct());
                 ps.setInt(2, pInventory.getStock());
                 ps.setInt(3, pInventory.getTickets());
                 ps.setInt(4, pInventory.getDepartures());
                 ps.setDouble(5, pInventory.getProfits());
                 ps.setByte(6, pInventory.getInventoryStatus());
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
}
