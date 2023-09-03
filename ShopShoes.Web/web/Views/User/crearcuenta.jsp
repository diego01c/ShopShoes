<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cuenta</title>
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
            margin-top: 50px;

        }
    </style>
    <body>
        <main class="container">
            <div class="row center-align" style="margin-top:10%;">
                <div class="col 15 s12">
                    <div class="card ">
                        <div class="card-content white-text">
                            <h5 class="black-text">CREAR CUENTA</h5>
                            <form action="User" method="post">
                                <input type="hidden" name="accion" 
                                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtClientName" name ="clientname" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtClientName" style="background-color:#ffffff; margin-left: 10px; width: 60px">Nombre</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtLastName" name ="lastname" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtLastName" style="background-color:#ffffff; margin-left: 10px; width: 60px">Apellido</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtTelefoneNumber" name ="telefone" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txttelefoneNumber" style="background-color:#ffffff; margin-left: 10px; width: 60px">Telefono</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtClientAddress" name ="clientaddress" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtClientAdress" style="background-color:#ffffff; margin-left: 10px; width: 70px">Direccion</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtMail" name ="mail" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtMail" style="background-color:#ffffff; margin-left: 10px; width: 130px">Correo Electronico</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input class="black-text" type="text" id="txtNombre" name ="UserName" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtNombre" style="background-color:#ffffff; margin-left: 10px; width: 60px">Usuario</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input class="black-text" type="password" id="txtPass" name ="Pass" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtPass" style="background-color:#ffffff; margin-left: 10px; width: 80px">Contraseña</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input class="black-text" type="password" id="txtConfirmPass" name ="confirmaux" required 
                                               class="validate" maxlength="30" style="border: 1px solid black; border-radius: 10px;">
                                        <label for="txtConfirmPass" style="background-color:#ffffff; margin-left: 10px; width: 150px">Confirmar Contraseña</label>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col 112 s12">
                                        <button type="submit" class="waves-effect waves-light btn grey darken-4">
                                            <i class="material-icons right"></i>Crear Cuenta
                                        </button>
                                        <a href="User?accion=login" >
                                            <button type="button" class="waves-effect waves-light btn black-text" style="background-color: white; border:2px solid black">
                                                Regresar
                                            </button>                                         
                                        </a>   
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>

            </div>


        </main>

    </body>

</html>
