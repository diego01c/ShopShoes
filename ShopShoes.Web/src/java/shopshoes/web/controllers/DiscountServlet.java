/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shopshoes.web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import shopshoes.accesoadatos.DiscountDAL;
import shopshoes.entidadesdenegocio.Discount;
import shopshoes.web.utils.SessionUser;
import shopshoes.web.utils.Utilidad;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "DiscountServlet", urlPatterns = {"/DiscountServlet"})
public class DiscountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Discount obtenerDiscount(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Discount Discount = new Discount();
        if (accion.equals("create") == false) {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            Discount.setId(Integer.parseInt(Utilidad.getParameter(request, "Id",
                    "0")));
        }
        Discount.setDiscountRate(Utilidad.getParameter(request, "DiscountName", ""));
        if (accion.equals("index")) {
            Discount.setTopAux(Integer.parseInt(Utilidad.getParameter(request,
                    "Top_aux", "10")));
            Discount.setTopAux(Discount.getTopAux() == 0 ? Integer.MAX_VALUE : Discount.getTopAux());
        }
        return Discount;
    }

    protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Discount Discount = new Discount();
            Discount.setTopAux(10);
            ArrayList<Discount> discounts = DiscountDAL.buscar(Discount);
            request.setAttribute("Discount", Discount);
            request.setAttribute("Top_aux", Discount.getTopAux());
            request.getRequestDispatcher("Views/Discount/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Discount discount = obtenerDiscount(request);
            ArrayList<Discount> Discount = DiscountDAL.buscar(discount);
            request.setAttribute("categories", discount);
            request.setAttribute("Top_aux", discount.getTopAux());
            request.getRequestDispatcher("Views/Discount/index.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Discount/create.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Discount Discount = obtenerDiscount(request);
            int result = DiscountDAL.crear(Discount);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Discount Discount = obtenerDiscount(request);
            Discount Discount_result = DiscountDAL.obtenerPorId(Discount);
            if (Discount_result.getId() > 0) {
                request.setAttribute("Discount", Discount_result);
            } else {
                Utilidad.enviarError("El id: " + Discount.getId() + " no existe en la tabla Discount",
                        request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Discount/edit.jsp")
                .forward(request, response);
    }

    protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Discount Discount = obtenerDiscount(request);
            int result = DiscountDAL.modificar(Discount);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/  Discount /details.jsp")
                .forward(request, response);
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
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
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
        //SessionUser.authorize(request, response, () -> {
        String accion = Utilidad.getParameter(request,
                "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doPostRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doPostRequestCreate(request, response);
                break;
            case "edit":
                request.setAttribute("accion", accion);
                doPostRequestEdit(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
                break;
        }
        //});
    }
}
