<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Users" %>
<%@page import="shopshoes.entidadesdenegocio.Client" %>
<% Users user = (Users) request.getAttribute("user");%>
<% Client client = (Client) request.getAttribute("client");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cuenta</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
      <main class="container" style="margin-top: 5%">
            <div class="row center-align">

                <div class="col 15 s12">
                    <div class="card login">
                        <div class="card-content black-text">
                            <h5>PERFIL DE USUARIO</h5>
                            <form action="User" method="post">
                                <input type="hidden" name="accion" 
                                       value="<%=request.getAttribute("accion")%>" id="txtHidden"/>
                                <input type="hidden" name="idUser" value="<%=user.getId()%>" />
                                <input type="hidden" name="passwo" value="<%=user.getPass()%>" />
                                <input type="hidden" name="datere" value="<%=user.getRegistrationDate()%>" />
                                <div class="row">
                                    <div class="input-field col s6"> 
                                        <input type="text" id="txtClientName" name ="clientname" required 
                                               class="validate" maxlength="30" value="<%=client.getClientName()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txtClientName" style="background-color:#ffffff; margin-left: 10px;width: 70px;text-align: center">Nombre</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input type="text" id="txtLastName" name ="lastname" required 
                                               class="validate" maxlength="30" value="<%=client.getLastName()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txtLastName" style="background-color:#ffffff; margin-left: 10px;width: 70px;text-align: center">Apellido</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input type="text" id="txtTelefoneNumber" name ="telefone" required 
                                               class="validate" maxlength="30" value="<%=client.getTelefoneNumber()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txttelefoneNumber" style="background-color:#ffffff; margin-left: 10px;width: 70px;text-align: center">Telefono</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <input type="text" id="txtClientAddress" name ="clientaddress" required 
                                               class="validate" maxlength="30" value="<%=client.getClientAddress()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txtClientAdress" style="background-color:#ffffff; margin-left: 10px;width: 70px;text-align: center">Direccion</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input type="text" id="txtMail" name ="mail" required 
                                               class="validate" maxlength="30" value="<%=user.getMail()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txtMail" style="background-color:#ffffff; margin-left: 10px;width: 130px;text-align: center">Correo Electronico</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input type="text" id="txtNombre" name ="UserName" required 
                                               class="validate" maxlength="30" value="<%=user.getUserName()%>" style="border: 1px solid grey; border-radius: 10px;">
                                        <label for="txtNombre" style="background-color:#ffffff; margin-left: 10px; width: 70px;text-align: center">Usuario</label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col 112 s12">
                                        <button type="submit" class="waves-effect waves-light btn grey darken-4" style="float: right;">
                                            <i class="material-icons right"></i>Actualizar Cuenta
                                        </button>
                                        <a href="Sales?accion=compras" 
                                       title="Crear" class="waves-effect waves-light btn black" style="float: right; margin-right: 10px">
                                        <i class="material-icons right"></i>Compras
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

