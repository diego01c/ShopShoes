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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import shopshoes.accesoadatos.ClientDAL;
import shopshoes.accesoadatos.DirectionDAL;

import shopshoes.web.utils.*;

import shopshoes.entidadesdenegocio.Client;
;
import shopshoes.entidadesdenegocio.Direction;
import shopshoes.entidadesdenegocio.Direction;

/**
 *
 * @author MINEDUCYT
 */


@WebServlet(name = "DirectionServlet", urlPatterns = {"/Direction"})
public class DirectionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Direction obtenerDirection(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion",
                "index");
        Direction usuario = new Direction();
        //Llenar objeto usuario desde vista (Nombre)
        usuario.setCountry(Utilidad.getParameter(request, "Country",
                ""));
        usuario.setCity(Utilidad.getParameter(request, "City",
                ""));

        usuario.setEmail(Utilidad.getParameter(request, "Email",
                ""));
        usuario.setPhone(Utilidad.getParameter(request, "Phone",
                ""));
       

        return usuario;
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Direction/index.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Direction user = obtenerDirection(request);
            HttpSession session = (HttpSession) request.getSession();
            int inicio = (int) session.getAttribute("id_inicio");
            Client user_ = new Client();
            user_ = ClientDAL.obtenerPorId(inicio);
            user.setIdClient(user_.getId());

            int result = DirectionDAL.crear(user);

            if (result != 0) {
                
                request.getRequestDispatcher(
                        "Views/Sales/index.jsp")
                        .forward(request, response);

            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
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
        String accion = Utilidad.getParameter(request, "accion",
                "index");

        SessionUser.authorize(request, response, () -> {
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
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
        String accion = Utilidad.getParameter(request, "accion",
                "index");
        SessionUser.authorize(request, response, () -> {
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;

            }
        });
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
