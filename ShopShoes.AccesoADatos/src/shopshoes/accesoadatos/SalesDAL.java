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
import shopshoes.entidadesdenegocio.Sales;

/**
 *
 * @author alexg
 */
public class SalesDAL {
    static String obtenerCampos()
    {
        return "p.Id, p.IdProduct, p.IdPaymentMethod, p.IdClient, p.Amount, p.Total";
    }
    
    private static String obtenerSelect(Sales pSales)
    {
        String sql;
        sql = "Select ";
        if(pSales.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pSales.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Sales p");
        return sql;
    }
    
    private static String agregarOrderBy(Sales pSales)
    {
        String sql = " Order by p.Id Desc";
        if(pSales.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pSales.getTop_aux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Sales pSales) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Sales(IdProduct, IdpaymentMethod, IdClient, Amount, Total) "
                        + "Values(?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pSales.getIdProduct());
                    st.setInt(2, pSales.getIdPaymentMethod());
                    st.setInt(3, pSales.getIdClient());
                    st.setDouble(4, pSales.getAmount());
                    st.setDouble(5, pSales.getTotal());
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
    
    public static int modificar(Sales pSales) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Sales Set IdProdcut = ?, IdPaymentMethod = ?, IdClient = ?, Amount = ?, Total = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSales.getIdProduct());
                ps.setInt(2, pSales.getIdPaymentMethod());
                ps.setInt(3, pSales.getIdClient());
                ps.setDouble(4, pSales.getAmount());
                ps.setDouble(5, pSales.getTotal());
                ps.setInt(6, pSales.getId());
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
    
    
    public static int eliminar(Sales pSales) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Sales Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSales.getId());
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
    
    static int asignarDatosResultSet(Sales pSales, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pSales.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pSales.setIdProduct(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pSales.setIdPaymentMethod(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pSales.setIdClient(pResultSet.getInt(pIndex)); // index 4
        pIndex++;
        pSales.setAmount(pResultSet.getInt(pIndex)); // index 5
        pIndex++;
        pSales.setTotal(pResultSet.getInt(pIndex)); // index 6
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Sales> pSales) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Sales sales = new Sales();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(sales, resultSet, 0);
                pSales.add(sales); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Sales obtenerPorId(Sales pSales) throws Exception
    {
        Sales sale = new Sales();
        ArrayList<Sales> sales = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pSales);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSales.getId());
                obtenerDatos(ps, sales);
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
        if(!sales.isEmpty())
        {
           sale = sales.get(0);
        }
        return sale;
    }

    
    public static ArrayList<Sales> obtenerTodos() throws Exception
    {
        ArrayList<Sales> sales = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Sales());
            sql += agregarOrderBy(new Sales());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, sales);
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
        
        return sales;
    }
}
