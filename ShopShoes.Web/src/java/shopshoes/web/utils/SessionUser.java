/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.web.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import shopshoes.entidadesdenegocio.*;

/**
 *
 * @author MINEDUCYT
 */
public class SessionUser {

    public static void autenticarUser(HttpServletRequest request, Users pUsuario) {
        HttpSession session = (HttpSession) request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("auth", true);
        session.setAttribute("user", pUsuario.getUserName());
        session.setAttribute("rol", pUsuario.getRol().getRolesName());
        session.setAttribute("id_inicio", pUsuario.getId());
    }

    public static boolean isAuth(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        boolean auth = session.getAttribute("auth") != null
                ? (boolean) session.getAttribute("auth") : false;
        return auth;
    }

    public static String getUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) {
            user = session.getAttribute("user") != null
                    ? (String) session.getAttribute("user") : "";
        }
        return user;
    }

    public static String getRol(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String rol = "";
        if (SessionUser.isAuth(request)) {
            rol = session.getAttribute("rol") != null
                    ? (String) session.getAttribute("rol") : "";
        }
        return rol;
    }

    public static void authorize(HttpServletRequest request,
            HttpServletResponse response,
            IAuthorize pIAuthorize) throws ServletException,
            IOException {
        if (SessionUser.isAuth(request)) {
            pIAuthorize.authorize();
        } else {
            response.sendRedirect("User?accion=login");
        }
    }

    public static void cerrarSession(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();
    }
}
