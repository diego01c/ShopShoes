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
    <body class="back-image1">
        <main class="container">
            <div class="row center-align">

                <div class="col 15 s12">
                    <div class="card login">
                        <div class="card-content white-text">
                            <h5>CREAR CUENTA</h5>
                            <form action="User" method="post">
                                <input type="hidden" name="accion" 
                                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">person</i>
                                        <input class="white-text" type="text" id="txtClientName" name ="clientname" required 
                                               class="validate" maxlength="30">
                                        <label for="txtClientName">Nombre</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">perm_contact_calendar</i>
                                        <input class="white-text" type="text" id="txtLastName" name ="lastname" required 
                                               class="validate" maxlength="30">
                                        <label for="txtLastName">Apellido</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">phone</i>
                                        <input class="white-text" type="text" id="txtTelefoneNumber" name ="telefone" required 
                                               class="validate" maxlength="30">
                                        <label for="txttelefoneNumber">Telefono</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">local_taxi</i>
                                        <input class="white-text" type="text" id="txtClientAddress" name ="clientaddress" required 
                                               class="validate" maxlength="30">
                                        <label for="txtClientAdress">Direccion</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">drafts</i>
                                        <input class="white-text" type="text" id="txtMail" name ="mail" required 
                                               class="validate" maxlength="30">
                                        <label for="txtMail">Correo Electronico</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input class="white-text" type="text" id="txtNombre" name ="UserName" required 
                                               class="validate" maxlength="30">
                                        <label for="txtNombre">Usuario</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">enhanced_encryption</i>
                                        <input class="white-text" type="password" id="txtPass" name ="Pass" required 
                                               class="validate" maxlength="30">
                                        <label for="txtPass">Contraseña</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">build</i>
                                        <input class="white-text" type="password" id="txtConfirmPass" name ="confirmaux" required 
                                               class="validate" maxlength="30">
                                        <label for="txtConfirmPass">Confirmar Contraseña</label>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col 112 s12">
                                        <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                            <i class="material-icons right">save</i>Crear Cuenta
                                        </button>
                                        <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                            <i class="material-icons right">list</i>Cancelar
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
