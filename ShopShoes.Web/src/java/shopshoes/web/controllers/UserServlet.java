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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import shopshoes.accesoadatos.ClientDAL;
import shopshoes.accesoadatos.UsersDAL;
import shopshoes.web.utils.*;
import shopshoes.entidadesdenegocio.Users;
import shopshoes.accesoadatos.RolesDAL;
import shopshoes.entidadesdenegocio.Client;
import shopshoes.entidadesdenegocio.Roles;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private Users obtenerUsuario(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion",
                "index");
        Users usuario = new Users();
        //Llenar objeto usuario desde vista (Nombre)
        usuario.setUserName(Utilidad.getParameter(request, "UserName",
                ""));
        usuario.setMail(Utilidad.getParameter(request, "Mail",
                    ""));
        usuario.setIdRol(Integer.parseInt(Utilidad.getParameter(request,
                "IdRol", "0")));

        //Llenar objeto usuario desde vista (idRol)
        if (accion.equals("create") || accion.equals("login")
                || accion.equals("cambiarpass")) {
            usuario.setPass(Utilidad.getParameter(request,
                    "Pass", ""));
            usuario.setConfirmPassword_aux(Utilidad.getParameter(request,
                    "confirmPassword_aux", ""));
            if (accion.equals("cambiarpass")) {
                usuario.setId(Integer.parseInt(Utilidad.getParameter(request,
                        "id", "0")));
            }
        } else if (accion.equals("index")) {
            usuario.setTop_aux(Integer.parseInt(
                    Utilidad.getParameter(request, "top_aux", "10")));
            usuario.setTop_aux(usuario.getTop_aux() == 0 ? Integer.MAX_VALUE
                    : usuario.getTop_aux());
        } else if (accion.equals("crearcuenta")) {
            Client client = new Client();
            client.setClientName(Utilidad.getParameter(request, "clientname",
                    ""));
            client.setLastName(Utilidad.getParameter(request, "lastname",
                    ""));
            client.setTelefoneNumber(Utilidad.getParameter(request, "telefone",
                    ""));
            client.setClientAddress(Utilidad.getParameter(request, "clientaddress",
                    ""));
            //Llenar objeto usuario desde vista (Nombre)
            usuario.setUserName(Utilidad.getParameter(request, "UserName",
                    ""));
            usuario.setPass(Utilidad.getParameter(request,
                    "Pass", ""));
            usuario.setMail(Utilidad.getParameter(request, "mail",
                    ""));
            usuario.setConfirmPassword_aux(Utilidad.getParameter(request,
                    "confirmaux", ""));
            usuario.setClient(client);

        } else if (accion.equals("perfil")) {
            Client client = new Client();
            client.setClientName(Utilidad.getParameter(request, "clientname",
                    ""));
            client.setLastName(Utilidad.getParameter(request, "lastname",
                    ""));
            client.setTelefoneNumber(Utilidad.getParameter(request, "telefone",
                    ""));
            client.setClientAddress(Utilidad.getParameter(request, "clientaddress",
                    ""));
            usuario.setId(Integer.parseInt(Utilidad.getParameter(request, "idUser",
                    "")));
            //Llenar objeto usuario desde vista (Nombre)
            usuario.setUserName(Utilidad.getParameter(request, "UserName",
                    ""));
            usuario.setPass(Utilidad.getParameter(request,
                    "passwo", ""));
            usuario.setMail(Utilidad.getParameter(request, "mail",
                    ""));
            usuario.setRegistrationDate(LocalDate.parse(Utilidad.getParameter(request,
                    "datere", "")));

            usuario.setClient(client);

        }
        else {
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
        try {
            Users usuario = obtenerUsuario(request);
            Users usuario_auth = UsersDAL.login(usuario);//Provisional
            if (usuario_auth.getId() != 0
                    && usuario_auth.getUserName().equals(usuario.getUserName())) {
                Roles rol = new Roles();
                rol.setId(usuario_auth.getIdRol());
                usuario_auth.setRol(RolesDAL.obtenerPorId(rol));
                SessionUser.autenticarUser(request, usuario_auth);
                response.sendRedirect("Home");
            } else {
                request.setAttribute("error", "Credenciales incorrectas");
                request.setAttribute("accion", "login");
                doGetRequestLogin(request, response);
            }

        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
    }

    protected void doGetRequestPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        int inicio = (int) session.getAttribute("id_inicio");
        Users user = new Users();
        Client client = new Client();
        try {
            user = UsersDAL.obtenerPorId(inicio);
            client = ClientDAL.obtenerPorId(inicio);
            request.setAttribute("user", user);
            request.setAttribute("client", client);
            request.getRequestDispatcher("Views/User/perfil.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Users user = obtenerUsuario(request);
            int result = UsersDAL.modificar(user);
            if (result != 0) {
                Client client = new Client();
                client = user.getClient();
                client.setIdUser(user.getId());
                int result_ = ClientDAL.modificar(client);
                if (result_ != 0) {
                    if (result != 0) {
                        request.setAttribute("accion", "index");
                        doGetRequestCreate(request, response);
                    } else {
                        Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
                    }
                }
            } else {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/User/crearcuenta.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Users user = obtenerUsuario(request);
            if (user.getPass().equals(user.getConfirmPassword_aux())) {
                int result = UsersDAL.crear(user);
                if (result != 0) {
                    Users user_ = UsersDAL.obtenerPorData(user);
                    Client client = new Client();
                    client = user.getClient();
                    client.setIdUser(user_.getId());
                    int result_ = ClientDAL.crear(client);
                    if (result_ != 0) {
                        if (result != 0) {
                            request.setAttribute("accion", "login");
                            doGetRequestLogin(request, response);
                        } else {
                            Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
                        }
                    }
                } else {
                    Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
                }
            } else {
                Utilidad.enviarError("Contraseñas no coinciden", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    protected void doGetRequestCambiarPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Users usuario = new Users();
            //Buscar el usuario que esta logeado y obtiene el login de la 
            //variable de session ViDuran
            usuario.setUserName(SessionUser.getUser(request));
            Users usuario_result = UsersDAL.buscar(usuario)
                    .get(0);
            if(usuario_result.getId() > 0)
            {
                request.setAttribute("usuario", usuario_result);
                request.getRequestDispatcher(
                        "Views/User/cambiarpass.jsp")
                    .forward(request, response);
            }
            else
            {
                Utilidad.enviarError("El login: " + usuario_result.getUserName()+
                        "no existe en la Base de Datos", request, response);
            }
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    
    protected void doPostCambiarPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Users usuario = obtenerUsuario(request);
            String passActual = Utilidad.getParameter(request,
                    "passwordActual", "");
            int result = UsersDAL.cambiarPassword(usuario, 
                    passActual);
            if(result != 0)
            {
                response.sendRedirect("User?accion=login");
                String destinatario = usuario.getMail();
                String remitente = "shopshoesofficial23@gmail.com";
                String claveemail = "tpkdisggvorihafg";
                String asunto = "Cambio de contraseña";
                
                String cuerpo = "<div style='background-color:black;color:white;padding:20px;'>"
                        + "<h1>Se cambio tu contraseña.</h1><br> <p>Se cambio tu contraseña, tal como lo pediste.</p><br> <p>Si no fuiste tu quien solicito el cambio,"
                        + " tenemos algunos consejos para ayudarte a mantener segura tu cuenta. Para mas informacion, consulta https://support.google.com/accounts/answer/46526?hl=es-419</p></div>";
                
                Properties props = System.getProperties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", "587");
                props.setProperty("mail.smtp.user", remitente);
                props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
                props.setProperty("mail.smtp.clave", claveemail);
                props.setProperty("mail.smtp.auth", "true");
                
                
                
                Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setContent(cuerpo, "text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, claveemail);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }
                
            }
            else
            {
                Utilidad.enviarError("Error al cambiar el Password", request, response);
            }

        }
        catch(Exception ex)
        {
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
        String accion = Utilidad.getParameter(request, "accion",
                "index");
        if (accion.equals("login")) {
            request.setAttribute("accion", accion);
            doGetRequestLogin(request, response);
        } else if (accion.equals("crearcuenta")) {
            request.setAttribute("accion", accion);
            doGetRequestCreate(request, response);
        } else {
            SessionUser.authorize(request, response, () -> {
                switch (accion) {
                    case "index":
                        request.setAttribute("accion", accion);
                        doGetRequestLogin(request, response);
                        break;
                    case "perfil":
                        request.setAttribute("accion", accion);
                        doGetRequestPerfil(request, response);
                        break;
                    case "cambiarpass":
                        request.setAttribute("accion", accion);
                        doGetRequestCambiarPassword(request, response);
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
        if (accion.equals("login")) {
            request.setAttribute("accion", accion);
            doPostRequestLogin(request, response);
        } else if (accion.equals("crearcuenta")) {
            request.setAttribute("accion", accion);
            doPostRequestCreate(request, response);
        } else {
            SessionUser.authorize(request, response, () -> {
                switch (accion) {
                    case "index":
                        request.setAttribute("accion", accion);
                        doPostRequestLogin(request, response);
                        break;
                    case "perfil":
                        request.setAttribute("accion", accion);
                        doPostRequestPerfil(request, response);
                        break;
                    case "cambiarpass":
                        request.setAttribute("accion", accion);
                        doPostCambiarPassword(request, response);
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
