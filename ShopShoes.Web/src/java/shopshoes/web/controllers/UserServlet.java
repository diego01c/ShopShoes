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

import shopshoes.accesoadatos.UsersDAL;
import shopshoes.web.utils.*;
import shopshoes.entidadesdenegocio.Users;
import shopshoes.accesoadatos.RolesDAL;
import shopshoes.entidadesdenegocio.Roles;
/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/User"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Users obtenerUsuario(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", 
                "index");
        Users usuario = new Users();
        //Llenar objeto usuario desde vista (Nombre)
        usuario.setUserName(Utilidad.getParameter(request, "UserName", 
                ""));
        usuario.setIdRol(Integer.parseInt(Utilidad.getParameter(request, 
                "IdRol", "0")));
        //Llenar objeto usuario desde vista (idRol)
        if(accion.equals("create") || accion.equals("login")
           || accion.equals("cambiarpass"))
        {
            usuario.setPass(Utilidad.getParameter(request, 
                    "Pass",""));
            usuario.setConfirmPassword_aux(Utilidad.getParameter(request, 
                    "confirmPassword_aux",""));
            if(accion.equals("cambiarpass"))
            {
                usuario.setId(Integer.parseInt(Utilidad.getParameter(request, 
                "id", "0")));
            }
        }
        else
        if(accion.equals("index"))
        {
            usuario.setTop_aux(Integer.parseInt(
                    Utilidad.getParameter(request,"top_aux", "10")));
            usuario.setTop_aux(usuario.getTop_aux() == 0 ? Integer.MAX_VALUE: 
                    usuario.getTop_aux());
        }
        else
        {
            usuario.setId(Integer.parseInt(Utilidad.getParameter(request, 
                "Id", "0")));
        }
        return usuario;
    }
    
    
    
    protected void doGetRequestLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("Views/User/login.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Users usuario = obtenerUsuario(request);
            Users usuario_auth = UsersDAL.login(usuario);//Provisional
            if(usuario_auth.getId() != 0 && 
               usuario_auth.getUserName().equals(usuario.getUserName()))
            {
                Roles rol = new Roles();
                rol.setId(usuario_auth.getIdRol());
                usuario_auth.setRol(RolesDAL.obtenerPorId(rol));
                SessionUser.autenticarUser(request, usuario_auth);
                response.sendRedirect("Home");
            }
            else
            {
                request.setAttribute("error", "Credenciales incorrectas");
                request.setAttribute("accion", "login");
                doGetRequestLogin(request, response);
            }

        }
        catch(Exception ex)
        {
            request.setAttribute("error", ex.getMessage());
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
        if(accion.equals("login"))
        {
            request.setAttribute("accion", accion);
            doGetRequestLogin(request, response);
        }
        else
        {
            SessionUser.authorize(request, response, () -> {
                switch(accion)
                {
                    case "index":
                        request.setAttribute("accion", accion);
                        doGetRequestLogin(request,response);
                        break;
                }
            });
        }
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
        if(accion.equals("login"))
        {
            request.setAttribute("accion", accion);
            doPostRequestLogin(request, response);
        }
        else
        {
            SessionUser.authorize(request, response, () -> {
                switch(accion)
                {
                    case "index":
                        request.setAttribute("accion", accion);
                        doPostRequestLogin(request,response);
                        break;
                }
            });
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
