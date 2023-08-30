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
import shopshoes.entidadesdenegocio.PaymentMethod;

public class PaymentMethodDAL {

    static String obtenerCampos() {
        return "p.Id, p.PaymentMethodName, p.PaymentMethodDescription";
    }
    
    private static String obtenerSelect(PaymentMethod pPaymentMethod)
    {
        String sql;
        sql = "Select ";
        if(pPaymentMethod.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pPaymentMethod.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From PaymentMethod  p");
        return sql;
    }
      
     private static String agregarOrderBy(PaymentMethod pPaymentMethod)
    {
        String sql = " Order by p.Id Desc";
        if(pPaymentMethod.getTop_aux()> 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pPaymentMethod.getTop_aux()+ " ";
        }
        return sql;
    }

public static int crear(PaymentMethod pPaymentMethod) throws Exception
    {
        int result;
        String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into PaymentMethod(PaymentMethodName, PaymentMethodDescription) "
                        + "Values(?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                   
                    st.setString(1, pPaymentMethod.getPaymentMethodName());
                    st.setString(2, pPaymentMethod.getPaymentMethodDescription());
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

 public static int modificar( PaymentMethod pPaymentMethod) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update PaymentMethod Set PaymentMethodName = ?, PaymentMethodDescription = ?"
                    + " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pPaymentMethod.getPaymentMethodName());
                ps.setString(2, pPaymentMethod.getPaymentMethodDescription());
                ps.setInt(3, pPaymentMethod.getId());
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
 
 public static ArrayList<PaymentMethod> obtenerPorName(String id) throws Exception
    {
        PaymentMethod payment = new PaymentMethod();
        ArrayList<PaymentMethod> payments = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = "Select " + (obtenerCampos() + " From PaymentMethod p"); // obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE p.PaymentMethodName Like ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, "%"+id+"%");
                obtenerDatos(ps, payments);
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
 
        return payments;
    }
 
    public static int eliminar(PaymentMethod pPaymentMethod) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From PaymentMethod Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pPaymentMethod.getId());
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
    
static int asignarDatosResultSet(PaymentMethod pPaymentMethod, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pPaymentMethod.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pPaymentMethod.setPaymentMethodName(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pPaymentMethod.setPaymentMethodDescription(pResultSet.getString(pIndex));
        return pIndex;
    }

private static void obtenerDatos(PreparedStatement pPS, ArrayList<PaymentMethod> pPaymentMethod) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                PaymentMethod PaymentMethod = new PaymentMethod();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(PaymentMethod, resultSet, 0);
                pPaymentMethod.add(PaymentMethod); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
 public static PaymentMethod obtenerPorId(int id) throws Exception
    {
        PaymentMethod PaymentMethod = new PaymentMethod();
        ArrayList<PaymentMethod> PaymentMethodd = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = "Select*From PaymentMethod Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, id);
                obtenerDatos(ps, PaymentMethodd);
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
        if(!PaymentMethodd.isEmpty())
        {
           PaymentMethod = PaymentMethodd.get(0);
        }
        return PaymentMethod;
    }

    
    public static ArrayList<PaymentMethod> obtenerTodos() throws Exception
    {
        ArrayList<PaymentMethod> PaymentMethod = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new PaymentMethod());
            sql += agregarOrderBy(new PaymentMethod());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, PaymentMethod);
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
        
        return PaymentMethod;
    }
}
