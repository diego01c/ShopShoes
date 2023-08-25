<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Login</title>
    </head>
    <style>
        *{
            margin: 0;
            margin: 0;
        }
        .back-image1 {
            background-image: url('./wwwroot/image/nikey.jpeg');
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }

        .login{
            background: rgba(0, 0, 0, .6);
            margin-top: 100px;

        }
    </style>
    <body class="back-image1">

        <div class="row center-align">
            <div class="col s12 m4 12">

            </div>
            <div class="col s12 m4 18">
                <div class="card login">
                    <div class="card-content white-text">
                        <h5>INICIAR SESION</h5>
                        <form action="User?accion=login" method="post">
                            <input type="hidden" name="accion" 
                                   value="<%=request.getAttribute("accion")%>">
                            <div class="row">
                                <div class="input-field col 15 s12">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input class="white-text" type="text" id="txtLogin" name="UserName"
                                           required class="validate" maxlength="25">
                                    <label for="txtLogin">Login</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col 15 s12">
                                    <i class="material-icons prefix">enhanced_encryption</i>
                                    <input class="white-text" type="password" id="txtPassword" name="Pass"
                                           required class="validate" minlength="5">
                                    <label for="txtPassword">Password</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col 112 s12">
                                    <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                        <i class="material-icons right">send</i>Iniciar Sesion
                                    </button>
                                </div>
                            </div>
                            <%
                              if(request.getAttribute("error") != null)
                                {
                            %>
                            <div class="row">
                                <div class="col 112 s12">
                                    <span style="color:red;font-weight:bold">
                                        <%= request.getAttribute("error") %>
                                    </span>
                                </div>
                            </div>
                            <%}%>
                            <div class="row">
                                <div class="col s6 center-align">
                                    <p class="white-text text-accent-4">
                                        ¿No tienes cuenta?
                                    </p>
                                </div>
                                <div class="col s6">
                                    <a href="User?accion=crearcuenta" 
                                       title="Crear" class="waves-effect waves-light btn transparent">
                                        Crear Cuenta
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
            <div class="col s12 m4 12">

            </div>
        </div>

    </body>
</html>
