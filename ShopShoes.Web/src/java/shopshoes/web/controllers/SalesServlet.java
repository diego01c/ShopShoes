/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shopshoes.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import shopshoes.accesoadatos.ClientDAL;
import shopshoes.accesoadatos.DirectionDAL;
import shopshoes.accesoadatos.InventoryDAL;
import shopshoes.accesoadatos.PaymentMethodDAL;
import shopshoes.accesoadatos.ProductsDAL;
import shopshoes.accesoadatos.SalesDAL;
import shopshoes.accesoadatos.TrolleyDAL;
import shopshoes.entidadesdenegocio.Client;
import shopshoes.entidadesdenegocio.Direction;
import shopshoes.entidadesdenegocio.Inventory;
import shopshoes.entidadesdenegocio.PaymentMethod;
import shopshoes.entidadesdenegocio.Products;
import shopshoes.entidadesdenegocio.Sales;
import shopshoes.entidadesdenegocio.Trolley;
import shopshoes.web.utils.SessionUser;
import shopshoes.web.utils.Utilidad;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "SalesServlet", urlPatterns = {"/Sales"})
public class SalesServlet extends HttpServlet {

    
    
    protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {      
            request.getRequestDispatcher("Views/Sales/index.jsp").forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = new Trolley();
             HttpSession session = (HttpSession) request.getSession();
            int inicio = (int) session.getAttribute("id_inicio");
            
             Client user_ = new Client();
            user_ = ClientDAL.obtenerPorId(inicio);
            trolley.setIdClient(user_.getId());
            ArrayList<Trolley> trolleyss = TrolleyDAL.obtenerPorIdP(trolley);
            for (Trolley trolleyssd : trolleyss) {
                double Total = 0;
                
                Sales compra = new Sales();
                Direction direccion = new Direction();
                Direction direccion_ = new Direction();
                Client client_ = new Client();
                client_ = ClientDAL.obtenerPorId(inicio);
                direccion_.setIdClient(client_.getId());
                
                direccion = DirectionDAL.obtenerPorId(direccion_);
                compra.setIdDirection(direccion.getId());

                compra.setIdPaymentMethod(1);
                compra.setIdTrolley(trolleyssd.getId());
                Products product = new Products();
                product.setId(trolleyssd.getIdProduct());
                Products products = ProductsDAL.obtenerPorId(product);
                Total = trolleyssd.getQuantity() * products.getCost();
                compra.setTotal(Total);
                
                int desactivar = TrolleyDAL.Desactivar(trolleyssd);
                
                Inventory inventario = new Inventory();
                Inventory inventario_ = new Inventory();
                Inventory inventario_1 = new Inventory();
                inventario_1.setIdProduct(products.getId());
                inventario_ = InventoryDAL.obtenerPorIdProduct(inventario_1);
                int Stock = inventario_.getStock() - trolleyssd.getQuantity();
                double Ganancias = inventario.getProfits() + Total;
                int Departures = inventario.getDepartures() + trolleyssd.getQuantity();
                inventario.setIdProduct(products.getId());
                inventario.setDepartures(Departures);
                inventario.setProfits(Ganancias);
                inventario.setStock(Stock);
                int resultado_ = InventoryDAL.modificarSalidas(inventario);
                
                
                if(desactivar != 0){
                    int resultado = SalesDAL.crear(compra);
                    
                }
                else{
                    Utilidad.enviarError("Error al Pagar", request, response);
                }

                
            }
            response.sendRedirect("index.jsp");
            
            
        } catch (Exception ex) {
        }

    }
    
    protected void doGetRequestCompras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = new Trolley();
            HttpSession session = (HttpSession) request.getSession();
        int inicio = (int) session.getAttribute("id_inicio");
        Client client = new Client();
        client = ClientDAL.obtenerPorId(inicio);
        trolley.setIdClient(client.getId());
        Sales ventas = new Sales();
        ventas.setTrolley(trolley);
        trolley.setIdClient(client.getId());
            ArrayList<Sales> trolleyss = SalesDAL.obtenerPorIdA(ventas);
            
            ArrayList<Sales> compras = new ArrayList();
            
            for (Sales trolleyssd : trolleyss) {
                Trolley carrito = new Trolley();
                Trolley carro = new Trolley();
                carro.setId(trolleyssd.getIdTrolley());
                carrito = TrolleyDAL.obtenerPorId(carro);
                
                
                Products producto = new Products();
                Products product = new Products();
                producto.setId(carrito.getIdProduct());
                product = ProductsDAL.obtenerPorId(producto);
                
                carrito.setProduct(product);
                
                PaymentMethod pago = new PaymentMethod();
                pago = PaymentMethodDAL.obtenerPorId(trolleyssd.getIdPaymentMethod());
                        
                Sales ventasfinales = new Sales();
                ventasfinales.setId(trolleyssd.getId());
                ventasfinales.setIdDirection(trolleyssd.getIdDirection());
                ventasfinales.setIdPaymentMethod(trolleyssd.getIdPaymentMethod());
                ventasfinales.setIdTrolley(trolleyssd.getIdTrolley());
                ventasfinales.setTotal(trolleyssd.getTotal());
                ventasfinales.setTrolley(carrito);
                ventasfinales.setPayment(pago);
               
                compras.add(ventasfinales);
                
            }
            

            request.setAttribute("comprasExitosas", compras);
            request.getRequestDispatcher("Views/Sales/compras.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, 
                    "accion", "index");
            switch(accion)
            {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                    case "compras":
                    request.setAttribute("accion", accion);
                    doGetRequestCompras(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
            }
        });
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String accion = Utilidad.getParameter(request, 
                    "accion", "index");
            switch(accion)
            {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                    case "compras":
                    request.setAttribute("accion", accion);
                    doGetRequestCompras(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
