<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Users" %>
<% Users usuario = (Users) request.getAttribute("usuario");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Cambiar Password</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Cambiar Password</h5>
            <form action="User" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <input type="hidden" name="id" value="<%=usuario.getId()%>">
                 <input type="hidden" id="txtLo" name ="Mail" required 
                               class="validate" maxlength="25" 
                               value="<%=usuario.getMail()%>" readonly>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtLogin" name ="UserName" required 
                               class="validate" maxlength="25" 
                               value="<%=usuario.getUserName()%>" readonly>
                        <label for="txtLogin">Login</label>
                    </div>
                    <div class="input-field col 14 s12">
                        <input type="password" id="txtPassActual" name ="passwordActual" required 
                               class="validate" minlength="5" maxlength="32">
                        <label for="txtPassActual">Password Actual</label>
                    </div>
                    <div class="input-field col 14 s12">
                        <input type="password" id="txtPassword" name ="Pass" required 
                               class="validate" minlength="5" maxlength="32">
                        <label for="txtPassword">Password</label>
                    </div>
                    <div class="input-field col 14 s12">
                        <input type="password" id="txtConfirmPassword_aux" 
                               name ="confirmPassword_aux" required class="validate" 
                               minlength="5" maxlength="32">
                        <label for="txtConfirmPassword_aux">Password</label>
                        <span id="txtConfirmPassword_aux_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                    </div>
                                  
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">swap_horiz</i>Cambiar Password
                        </button>
                        <a href="Home" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Inicio
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
        <script>
            function validarFormulario()
            {
                var result = true;
                var txtPassword = $("#txtPassword").val();
                var txtConfirmPassword = $("#txtConfirmPassword_aux").val();

                if(txtPassword != txtConfirmPassword)
                {
                    $("#txtConfirmPassword_aux_error")
                            .empty();
                    $("#txtConfirmPassword_aux_error")
                    .append("El Password y ConfirmPassword deben ser iguales");
                    result = false;
                }
                else
                {
                     $("#txtConfirmPassword_aux_error")
                            .empty();
                }
                
                return result;
            }   
        </script>
    </body>
</html>
