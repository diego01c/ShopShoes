<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Direction" %>
<%@page import="shopshoes.entidadesdenegocio.Client" %>


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
                            <h5>DIRECCION</h5>
                            <form action="Direction" method="post">
                                <input type="hidden" name="accion" 
                                       value="<%=request.getAttribute("accion")%>" id="txtHidden"/>
                                
                                
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">person</i>
                                        <input type="text" id="txtClientName" name ="Country" required 
                                               class="validate" maxlength="30">
                                        <label for="txtClientName">Pais</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">perm_contact_calendar</i>
                                        <input type="text" id="txtLastName" name ="City" required 
                                               class="validate" maxlength="30" >
                                        <label for="txtLastName">Ciudad</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">phone</i>
                                        <input type="text" id="txtTelefoneNumber" name ="Phone" required 
                                               class="validate" maxlength="30">
                                        <label for="txttelefoneNumber">Contacto</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <i class="material-icons prefix">local_taxi</i>
                                        <input type="text" id="txtClientAddress" name ="Email" required 
                                               class="validate" maxlength="30" >
                                        <label for="txtClientAdress">Correo</label>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="col 112 s12">
                                        <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                            <i class="material-icons right">save</i>Continuar
                                        </button>
                                        <button type="submit" class="waves-effect waves-light btn teal darken-4">
                                            <i class="material-icons right">list</i>Regresar
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


