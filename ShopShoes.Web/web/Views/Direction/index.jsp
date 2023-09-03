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
            <div class="row center-align" style="margin-top: 5%">

                <div class="col 15 s12">
                    <div class="card login">
                        <div class="card-content black-text">
                            <h5>DIRECCION</h5>
                            <form action="Direction" method="post">
                                <input type="hidden" name="accion" 
                                       value="<%=request.getAttribute("accion")%>" id="txtHidden"/>


                                <div class="row">
                                    <div class="input-field col s6">
                                        <input type="text" id="txtClientName" name ="Country" required 
                                               class="validate" maxlength="30" style="border: 1px solid grey; border-radius: 10px; ">
                                        <label for="txtClientName" style="background-color:#ffffff; margin-left: 10px; width: 50px">Pais</label>
                                    </div>

                                    <div class="input-field col s6">
                                        <input type="text" id="txtLastName" name ="City" required 
                                               class="validate" maxlength="30" style="border: 1px solid grey; border-radius: 10px; ">
                                        <label for="txtLastName" style="background-color:#ffffff; margin-left: 10px; width: 50px">Ciudad</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input type="text" id="txtTelefoneNumber" name ="Phone" required 
                                               class="validate" maxlength="30" style="border: 1px solid grey; border-radius: 10px; ">
                                        <label for="txttelefoneNumber" style="background-color:#ffffff; margin-left: 10px; width: 50px">Contacto</label>
                                    </div>


                                    <div class="input-field col s6">
                                        <input type="text" id="txtClientAddress" name ="Email" required 
                                               class="validate" maxlength="30" style="border: 1px solid grey; border-radius: 10px; ">
                                        <label for="txtClientAdress" style="background-color:#ffffff; margin-left: 10px; width: 50px">Correo</label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col 112 s12 right-align">                                    
                                        <a href="Trolley" >
                                            <button type="button" class="waves-effect waves-light btn black-text" style="background-color: white; border:2px solid black">
                                                Regresar
                                            </button>
                                            
                                        </a>   
                                        <button type="submit" class="waves-effect waves-light btn white-text grey darken-4" >
                                            <i class="material-icons right"></i>Continuar
                                            </a></button>
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


