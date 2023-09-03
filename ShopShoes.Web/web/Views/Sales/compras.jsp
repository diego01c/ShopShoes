<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Sales"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Sales> compras = (ArrayList<Sales>) request.getAttribute("comprasExitosas");
   
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Compras</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <main class="container">
            <div class="row">
                <div class="paginationjs">
                    <div style="overflow: auto;">
                        <%
                        for(Sales compra:compras)
                        {
                           
                        %>
                        <div class="col s12">
                            <div class="card white">
                                <div class="card-content black-text">
                                    <div class="row">
                                        <div class="col s6">
                                            <p>Metodo de Pago: <p>
                                        </div>
                                        <div class="col s6">
                                            <p><%=compra.getPayment().getPaymentMethodName()%></p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s6">
                                            <p>Producto: <p>
                                        </div>
                                        <div class="col s6">
                                            <p><%=compra.getTrolley().getProduct().getProductName()%></p>
                                        </div>
                                    </div>
                                        
                                        <div class="row">
                                        <div class="col s6">
                                            <p>Precio Unitario: <p>
                                        </div>
                                        <div class="col s6">
                                            <p><%=compra.getTrolley().getProduct().getCost()%></p>
                                        </div>
                                    </div>
                                        
                                        <div class="row">
                                        <div class="col s6">
                                            <p>Cantidad: <p>
                                        </div>
                                        <div class="col s6">
                                            <p><%=compra.getTrolley().getQuantity()%></p>
                                        </div>
                                    </div>
                                        
                                        <div class="row">
                                        <div class="col s6">
                                            <p>Total: <p>
                                        </div>
                                        <div class="col s6">
                                            <p><%=compra.getTotal()%></p>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                        <%}%>
                    </div> 
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col 112 s12">

            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>

