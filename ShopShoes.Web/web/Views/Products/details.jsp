<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Products" %>
<% Products product = (Products) request.getAttribute("product");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Ver Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <form action="Products" method="post">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <input type="hidden" name="id" value="<%=product.getId()%>" />

                <div class="container pt-10">
                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s5">
                            <h5><%=product.getProductName()%></h5>
                        </div>
                        <div class="col s5 right-align">
                            <h5 class="red-text">$<%=product.getCost()%></h5>
                        </div>
                    </div>
                        
                    <div class="row">
                        <div class="col 14 s12">
                            <center><img src="<%=product.getProductImage()%>" height="150"/></center>
                        </div>
                    </div>

                    <div class="row center-align">
                        <div class="col s4">
                            <img src="<%=product.getDetailImageOne()%>" height="100" />  
                        </div>
                        <div class="col s4">
                            <img src="<%=product.getDetailImageTwo()%>" height="100"/>
                        </div>
                        <div class="col s4">
                            <img src="<%=product.getDetailImageThree()%>" height="100"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s2">
                            <p><b>Marca: </b></p>
                        </div>
                        <div class="col s8 right-align">
                            <p><%=product.getBrand()%></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s2">
                            <p><b>Descripcion: </b></p>
                        </div>
                        <div class="col s8 right-align">
                            <p><%=product.getProductDescription()%></p>
                        </div>
                    </div>
                        
                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s2">
                            <p><b>Especificaciones: </b></p>
                        </div>
                        <div class="col s8 right-align">
                            <p><%=product.getSpecifications()%></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s2">
                            <p><b>Estado: </b></p>
                        </div>
                        <div class="col s8 right-align">
                            <p><%=product.getStatus()%></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s11 right-align">
                            <a href="Products?accion=details&id=<%=product.getId()%>" class="waves-effect waves-light btn purple">
                                <i class="material-icons right">add_shopping_cart</i>Añadir al Carrito
                            </a>
                        </div>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
