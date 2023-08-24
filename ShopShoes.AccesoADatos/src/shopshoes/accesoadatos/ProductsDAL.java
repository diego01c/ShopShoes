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
        return "p.Id, p.IdCategory, p.ProductName, p.Cost, p.ProductDescription, p.ProductImage, p.DetailImageOne, p.DetailImageTwo, p.DetailImageThree, p.Brand, p.Specifications, p.Status";
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
                sql = "Insert Into Products(IdCategory, ProductName, Cost, ProductDescription, ProductImage, DetailImageOne, DetailImageTwo, DetailImageThree, Brand, Specifications, Status) "
                        + "Values(?,?,?,?,?,?,?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pProducts.getIdCategory());
                    st.setString(2, pProducts.getProductName());
                    st.setDouble(3, pProducts.getCost());
                    st.setString(4, pProducts.getProductDescription());
                    st.setString(5, pProducts.getProductImage());
                    st.setString(6, pProducts.getDetailImageOne());
                    st.setString(7, pProducts.getDetailImageTwo());
                    st.setString(8, pProducts.getDetailImageThree());
                    st.setString(9, pProducts.getBrand());
                    st.setString(10, pProducts.getSpecifications());
                    st.setString(11, pProducts.getStatus());
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
            sql = "Update Products Set IdCategory = ?, ProductName = ?, Cost = ?, ProductDescription = ?, ProductImage = ?, DetailImageOne = ?, DetailImageTwo = ?, DetailImageThree = ?, Brand = ?, Specifications = ?, Status = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProducts.getIdCategory());
                ps.setString(2, pProducts.getProductName());
                ps.setDouble(3, pProducts.getCost());
                ps.setString(4, pProducts.getProductDescription());
                ps.setString(5, pProducts.getProductImage());
                ps.setString(6, pProducts.getDetailImageOne());
                ps.setString(7, pProducts.getDetailImageTwo());
                ps.setString(8, pProducts.getDetailImageThree());
                ps.setString(9, pProducts.getBrand());
                ps.setString(10, pProducts.getSpecifications());
                ps.setString(11, pProducts.getStatus());
                ps.setInt(12, pProducts.getId());
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
        pProducts.setProductImage(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pProducts.setDetailImageOne(pResultSet.getString(pIndex)); 
        pIndex++;
        pProducts.setDetailImageTwo(pResultSet.getString(pIndex)); 
        pIndex++;
        pProducts.setDetailImageThree(pResultSet.getString(pIndex)); 
        pIndex++;
        pProducts.setBrand(pResultSet.getString(pIndex)); 
        pIndex++;
        pProducts.setSpecifications(pResultSet.getString(pIndex)); 
        pIndex++;
        pProducts.setStatus(pResultSet.getString(pIndex)); 
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
    
    static void querySelect(Products pProducts, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pProducts.getIdCategory() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.IdCategory = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pProducts.getIdCategory());
            }
        }
        
        if(pProducts.getProductName() != null && 
           pProducts.getProductName().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" p.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pProducts.getProductName() + "%");
            }
        }
    }
    
    public static ArrayList<Products> buscar(Products pProducts) throws Exception
    {
        ArrayList<Products> products = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProducts);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pProducts, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pProducts);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pProducts, utilQuery);
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
