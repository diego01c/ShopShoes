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
import shopshoes.entidadesdenegocio.Trolley;
 
public class TrolleyDAL {
    
     static String obtenerCampos() {
        return " p.Id, p.IdClient, p.IdProduct, p.Quantity, p.StatusTrolley";
    }
    
     private static String obtenerSelect(Trolley pTrolley)
    {
        String sql;
        sql = "Select ";
        if(pTrolley.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pTrolley.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Trolley p");
        return sql;
    }
    
    private static String agregarOrderBy(Trolley pTrolley)
    {
        String sql = " Order by p.Id Desc";
        if(pTrolley.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pTrolley.getTop_aux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Trolley pTrolley) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Trolley(IdClient ,IdProduct,Quantity,StatusTrolley ) "
                        + "Values(?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pTrolley.getIdClient());
                    st.setInt(2, pTrolley.getIdProduct ());
                    st.setInt(3, pTrolley.getQuantity ());
                    st.setBoolean(4, true);
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
    
    public static int modificar(Trolley pTrolley) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Trolley Set IdClient = ?, IdProduct = ?, Quantity = ?, StatusTrolley = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdClient());
                ps.setInt(2, pTrolley.getIdProduct());
                ps.setInt(3, pTrolley.getQuantity());
                ps.setBoolean(4, true);
                ps.setInt(5, pTrolley.getId());
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
    
    public static int eliminar(Trolley pTrolley) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Trolley Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getId());
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
    
    static int asignarDatosResultSet(Trolley pTrolley, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pTrolley.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pTrolley.setIdClient(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pTrolley.setIdProduct(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pTrolley.setQuantity(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pTrolley.setStatusTrolley((byte) pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Trolley> pTrolley) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Trolley Trolley = new Trolley();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(Trolley, resultSet, 0);
                pTrolley.add(Trolley); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Trolley obtenerPorId(Trolley pTrolley) throws Exception
    {
        Trolley Trolley = new Trolley();
        ArrayList<Trolley> trolley = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pTrolley);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdClient());
                obtenerDatos(ps, trolley);
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
        if(!trolley.isEmpty())
        {
           Trolley = trolley.get(0);
        }
        return Trolley;
    }
    public static ArrayList<Trolley> obtenerPorIdP(Trolley pTrolley) throws Exception
    {
        Trolley Trolley = new Trolley();
        ArrayList<Trolley> trolley = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pTrolley);
            sql += " Where IdClient = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdClient());
                obtenerDatos(ps, trolley);
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
        return trolley;
    }
    public static Trolley obtenerPorIdProduct(Trolley pTrolley) throws Exception
    {
        Trolley Trolley = new Trolley();
        ArrayList<Trolley> trolley = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pTrolley);
            sql += " Where IdProduct = ? and StatusTrolley = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdProduct());
                ps.setBoolean(2, true);
                obtenerDatos(ps, trolley);
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
        if(!trolley.isEmpty())
        {
           Trolley = trolley.get(0);
        }
        return Trolley;
    }
        public static int obtenerPorTrolley(Trolley pTrolley) throws Exception
    {
        Trolley Trolley = new Trolley();
        int result;
        ArrayList<Trolley> trolley = new ArrayList();
        
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pTrolley);
            sql += " Where IdProduct = ? and StatusTrolley = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdProduct());
                ps.setBoolean(2, true);
                obtenerDatos(ps, trolley);
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
        if(!trolley.isEmpty())
        {
           Trolley = trolley.get(0);
        }
        return result;
    }
    public static Trolley obtenerPorIdPC(Trolley pTrolley) throws Exception
    {
        Trolley Trolley = new Trolley();
        ArrayList<Trolley> trolley = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pTrolley);
            sql += " Where IdClient = ? and IdProduct = ? and StatusTrolley = true";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pTrolley.getIdClient());
                ps.setInt(2, pTrolley.getIdProduct());
                obtenerDatos(ps, trolley);
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
        if(!trolley.isEmpty())
        {
           Trolley = trolley.get(0);
        }
        return Trolley;
    }
    public static int obtenerIdPorIdProduct(Trolley pTrolley) throws Exception
    {
        int idProduct = 0;
        try (Connection conn = ComunDB.obtenerConexion())
        {
            String sql = "SELECT IdProduct FROM Trolley WHERE IdProduct = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1, pTrolley.getIdProduct());
                try (ResultSet rs = ps.executeQuery())
                {
                    if (rs.next())
                    {
                        idProduct = rs.getInt("IdProduct");
                    }
                }
            }
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        return idProduct;
    }

    public static ArrayList<Trolley> obtenerTodos() throws Exception
    {
        ArrayList<Trolley> trolley = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Trolley());
            sql += agregarOrderBy(new Trolley());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, trolley);
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
        
        return trolley;
    }
}
