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
import shopshoes.entidadesdenegocio.Direction;
import shopshoes.entidadesdenegocio.Sales;

/**
 *
 * @author alexg
 */
public class DirectionDAL {
    static String obtenerCampos()
    {
        return "p.Id, p.IdClient, p.Country, p.City, p.Phone, p.Email";
    }
    
    private static String obtenerSelect(Direction pSales)
    {
        String sql;
        sql = "Select ";
        if(pSales.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pSales.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Direction p");
        return sql;
    }
    
    private static String agregarOrderBy(Direction pSales)
    {
        String sql = " Order by p.Id Desc";
        if(pSales.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pSales.getTop_aux()+ " ";
        }
        return sql;
    }
    
    public static int crear(Direction pSales) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Direction(IdClient, Country, City, Phone, Email) "
                        + "Values(?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pSales.getIdClient());
                    st.setString(2, pSales.getCountry());
                    st.setString(3, pSales.getCity());
                    st.setString(4, pSales.getPhone());
                    st.setString(5, pSales.getEmail());
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
    
    static int asignarDatosResultSet(Direction pSales, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pSales.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pSales.setIdClient(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pSales.setCountry(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pSales.setCity(pResultSet.getString(pIndex)); // index 3
       pIndex++;
        pSales.setPhone(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pSales.setEmail(pResultSet.getString(pIndex)); // index 3
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Direction> pSales) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Direction sales = new Direction();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(sales, resultSet, 0);
                pSales.add(sales); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
 
    public static Direction obtenerPorId(Direction pSales) throws Exception
    {
        Direction sale = new Direction();
        ArrayList<Direction> sales = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            
            String sql = "Select Top 1 * From Direction where IdClient = ? Order By Id Desc ";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSales.getIdClient());
               
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

    
  
}
