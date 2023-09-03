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
    <body style="background-color:#ffffff; margin-top: 10%">
        <div class="row center-align" >
            <div class="col s12 m4 12">
            </div>
            <div class="col s12 m4 18">
                <div class="card-content white-text">
                    <h5 class="black-text">INICIAR SESION</h5>
                    <h6 class="black-text">O registrate para acceder</h6>
                    <form action="User?accion=login" method="post">
                        <input type="hidden" name="accion" 
                               value="<%=request.getAttribute("accion")%>">
                        <div class="row">
                            <div class="input-field col 15 s12">
                                <input class="white-text" type="text" id="txtLogin" name="UserName"
                                       required class="validate" maxlength="25" style="border: 1px solid grey; border-radius: 10px;">
                                <label for="txtLogin" style="background-color:#ffffff; margin-left: 10px;">Login</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col 15 s12">
                                <input class="white-text" type="password" id="txtPassword" name="Pass"
                                       required class="validate" minlength="5" style="border: 1px solid grey; border-radius: 10px;">
                                <label for="txtPassword" style="background-color:#ffffff; margin-left: 10px;">Password</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col 112 s12">
                                <a href="User?accion=crearcuenta" 
                                   title="Crear" class="waves-effect waves-light btn black-text"  style="float:left; background-color: white; border: 1px solid black" >
                                    Registrarme
                                </a>
                                <button type="submit" class="waves-effect waves-light btn grey darken-4" style="float:right;">
                                    <i class="material-icons right"></i>Iniciar Sesion
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
                    </form>
                </div>
            </div>
            <div class="col s12 m4 12">
            </div>
        </div>

    </body>
</html>
