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
      <main class="container">
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
                                        <i class="material-icons prefix">person</i>
                                        <input type="text" id="txtClientName" name ="clientname" required 
                                               class="validate" maxlength="30" value="<%=client.getClientName()%>">
                                        <label for="txtClientName">Nombre</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">perm_contact_calendar</i>
                                        <input type="text" id="txtLastName" name ="lastname" required 
                                               class="validate" maxlength="30" value="<%=client.getLastName()%>">
                                        <label for="txtLastName">Apellido</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">phone</i>
                                        <input type="text" id="txtTelefoneNumber" name ="telefone" required 
                                               class="validate" maxlength="30" value="<%=client.getTelefoneNumber()%>">
                                        <label for="txttelefoneNumber">Telefono</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">local_taxi</i>
                                        <input type="text" id="txtClientAddress" name ="clientaddress" required 
                                               class="validate" maxlength="30" value="<%=client.getClientAddress()%>">
                                        <label for="txtClientAdress">Direccion</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">drafts</i>
                                        <input type="text" id="txtMail" name ="mail" required 
                                               class="validate" maxlength="30" value="<%=user.getMail()%>">
                                        <label for="txtMail">Correo Electronico</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input type="text" id="txtNombre" name ="UserName" required 
                                               class="validate" maxlength="30" value="<%=user.getUserName()%>">
                                        <label for="txtNombre">Usuario</label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col 112 s12">
                                        <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                            <i class="material-icons right">save</i>Actualizar Cuenta
                                        </button>
                                        <a href="Sales?accion=compras" 
                                       title="Crear" class="waves-effect waves-light btn black" style="float: right;">
                                        <i class="material-icons right">add_shopping_cart</i>Compras
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

