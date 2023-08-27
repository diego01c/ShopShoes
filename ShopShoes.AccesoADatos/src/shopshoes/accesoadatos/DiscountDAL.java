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
import static shopshoes.accesoadatos.ProductsDAL.querySelect;
import shopshoes.entidadesdenegocio.*;

public class DiscountDAL {
    static String obtenerCampos()
    {
        return "d.Id, d.DiscountRate, d.DiscountStatus";
    } 
    
    private static String obtenerSelect(Discount pDiscount)
    {
        String sql;
        sql = "Select ";
        if(pDiscount.getTopAux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pDiscount.getTopAux() + " ";
        }
        sql += (obtenerCampos() + " From Discount d");
        return sql;
    }
    
    private static String agregarOrderBy(Discount pDiscount)
    {
        String sql = " Order by d.Id Desc";
        if(pDiscount.getTopAux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pDiscount.getTopAux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Discount pDiscount) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Discount(DiscountRate, DiscountStatus) "
                        + "Values(?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setString(1, pDiscount.getDiscountRate());
                    st.setByte(2, pDiscount.getDiscountStatus());
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
    
    public static int modificar(Discount pDiscount) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Discount Set DiscountRate = ?, DiscountStatus = ? Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pDiscount.getDiscountRate());
                ps.setByte(2, pDiscount.getDiscountStatus());
                ps.setInt(3, pDiscount.getId());
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
    
    
    public static int eliminar(Discount pDiscount) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Discount Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pDiscount.getId());
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
    
    static int asignarDatosResultSet(Discount pDiscount, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pDiscount.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDiscount.setDiscountRate(pResultSet.getString(pIndex)); // index 2
        pIndex++;
        pDiscount.setDiscountStatus(pResultSet.getByte(pIndex)); // index 3
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Discount> pDiscounts) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Discount discount = new Discount();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(discount, resultSet, 0);
                pDiscounts.add(discount); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Discount obtenerPorId(Discount pDiscount) throws Exception
    {
        Discount discount = new Discount();
        ArrayList<Discount> discounts = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pDiscount);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pDiscount.getId());
                obtenerDatos(ps, discounts);
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
        if(discounts.size() > 0)
        {
            discount = discounts.get(0);
        }
        return discount;
    }

    
    public static ArrayList<Discount> obtenerTodos() throws Exception
    {
        ArrayList<Discount> discounts = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Discount());
            sql += agregarOrderBy(new Discount());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, discounts);
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
        
        return discounts;
    }
    
    static void querySelect(Discount pDiscount, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pDiscount.getId()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pDiscount.getId());
            }
        }
        
        if(pDiscount.getDiscountRate()!= null && 
           pDiscount.getDiscountRate().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" p.DiscountRate Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pDiscount.getDiscountRate()+ "%");
            }
        }
    }

    public static ArrayList<Discount> buscar(Discount pDiscount) throws Exception
    {
        ArrayList<Discount> discount = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pDiscount);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pDiscount, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pDiscount);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pDiscount, utilQuery);
                obtenerDatos(ps, discount);
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
        
        return discount;
    }
}
    

