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

public class ProductsDAL {
    static String obtenerCampos()
    {
        return "p.Id, p.IdCategory, p.ProductName, p.Cost, p.ProductDescription, p.ProductImage";
    }
    
    private static String obtenerSelect(Products pProducts)
    {
        String sql;
        sql = "Select ";
        if(pProducts.getTopAux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pProducts.getTopAux() + " ";
        }
        sql += (obtenerCampos() + " From Products p");
        return sql;
    }
    
    private static String agregarOrderBy(Products pProducts)
    {
        String sql = " Order by p.Id Desc";
        if(pProducts.getTopAux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pProducts.getTopAux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Products pProducts) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Products(IdCategory, ProductName, Cost, ProductDescription, ProductImage) "
                        + "Values(?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pProducts.getIdCategory());
                    st.setString(2, pProducts.getProductName());
                    st.setDouble(3, pProducts.getCost());
                    st.setString(4, pProducts.getProductDescription());
                    st.setByte(5, pProducts.getProductImage());
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
    
    public static int modificar(Products pProducts) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Products Set IdCategory = ?, ProductName = ?, Cost = ?, ProductDescription = ?, ProductImage = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProducts.getIdCategory());
                ps.setString(2, pProducts.getProductName());
                ps.setDouble(3, pProducts.getCost());
                ps.setString(4, pProducts.getProductDescription());
                ps.setByte(5, pProducts.getProductImage());
                ps.setInt(6, pProducts.getId());
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
    
    
    public static int eliminar(Products pProducts) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Products Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProducts.getId());
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
    
    static int asignarDatosResultSet(Products pProducts, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pProducts.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pProducts.setIdCategory(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pProducts.setProductName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pProducts.setCost(pResultSet.getDouble(pIndex)); // index 4
        pIndex++;
        pProducts.setProductDescription(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pProducts.setProductImage(pResultSet.getByte(pIndex)); // index 6
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Products> pProducts) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Products product = new Products();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(product, resultSet, 0);
                pProducts.add(product); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Products obtenerPorId(Products pProducts) throws Exception
    {
        Products product = new Products();
        ArrayList<Products> products = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProducts);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProducts.getId());
                obtenerDatos(ps, products);
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
        if(products.size() > 0)
        {
           product = products.get(0);
        }
        return product;
    }

    
    public static ArrayList<Products> obtenerTodos() throws Exception
    {
        ArrayList<Products> products = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Products());
            sql += agregarOrderBy(new Products());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, products);
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
        
        return products;
    }
}
