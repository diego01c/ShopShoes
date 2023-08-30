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
import shopshoes.accesoadatos.AdministratorDAL;
import shopshoes.entidadesdenegocio.Administrator;
import shopshoes.web.utils.SessionUser;
import shopshoes.web.utils.Utilidad;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "AdministratorServlet", urlPatterns = {"/AdministratorServlet"})
public class AdministratorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Administrator obtenerAdministrator(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Administrator Administrator = new Administrator();
        if (accion.equals("create") == false) {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            Administrator.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        Administrator.setAdministratorName(Utilidad.getParameter(request, "nombre", ""));
        if (accion.equals("index")) {
            Administrator.setTop_aux(Integer.parseInt(Utilidad.getParameter(request,
                    "top_aux", "10")));
            Administrator.setTop_aux(Administrator.getTop_aux() == 0 ? Integer.MAX_VALUE : Administrator.getTop_aux());
        }
        return Administrator;
    }

    protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("Views/administrator/index.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("Views/Administrator/index.jsp")
                    .forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Administrator/create.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Administrator administrator = obtenerAdministrator(request);
            int result = AdministratorDAL.crear(administrator);
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
            Administrator administrator = obtenerAdministrator(request);
            Administrator administrator_result = AdministratorDAL.obtenerPorId(2);
            if (administrator_result.getId() > 0) {
                request.setAttribute("administrator", administrator_result);
            } else {
                Utilidad.enviarError("El id: " + administrator.getId() + " no existe en la tabla administrator",
                        request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Administrator/edit.jsp")
                .forward(request, response);
    }

    protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Administrator administrator = obtenerAdministrator(request);
            int result = AdministratorDAL.modificar(administrator);
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
        request.getRequestDispatcher("Views/Administrator/details.jsp")
                .forward(request, response);
    }

    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Administrator/delete.jsp")
                .forward(request, response);
    }

    protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Administrator administrator = obtenerAdministrator(request);
            int result = AdministratorDAL.eliminar(administrator);
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
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
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
            case "delete":
                request.setAttribute("accion", accion);
                doPostRequestDelete(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
                break;
        }
        //});
    }
}
