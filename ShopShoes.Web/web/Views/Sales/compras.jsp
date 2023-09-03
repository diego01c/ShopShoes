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
        <main class="container" >
            <center><h3>Historial de compras</h3></center>
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
                                        <div class="col s8">
                                            <div class="col s4 right-align" >
                                                <p>Metodo de pago:</p><br>
                                                <p>Producto: <p><br>
                                                <p>Precio Unitario: <p><br>
                                                <p>Cantidad: <p><br>
                                                <p>Total: <p>

                                            </div>
                                            <div class="col s4 right-align">
                                                <p><%=compra.getPayment().getPaymentMethodName()%></p><br>
                                                <p><%=compra.getTrolley().getProduct().getProductName()%></p><br>
                                                <p>$<%=compra.getTrolley().getProduct().getCost()%></p><br>
                                                <p><%=compra.getTrolley().getQuantity()%></p><br>
                                                <p>$<%=compra.getTotal()%></p>
                                            </div>                                         
                                        </div>
                                        <div class="col s4">
                                            <image src="<%=compra.getTrolley().getProduct().getProductImage()%>" height="200" >
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

