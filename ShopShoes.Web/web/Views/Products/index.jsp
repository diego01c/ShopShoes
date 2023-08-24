<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Products"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Products> products = (ArrayList<Products>) request.getAttribute("products");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if(products == null)
    {
        products = new ArrayList();
    }
    else
        if(products.size() > numReg)
        {
            double divNumPage = (double) products.size() / (double) numReg;
            numPage = (int) Math.ceil(divNumPage);
        }
    String strTop_aux = request.getParameter("TopAux");
    int top_aux = 10;
    if(strTop_aux != null && strTop_aux.trim().length() > 0)
    {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Productp</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <main class="container">
            <div class="row">
                <div class="paginationjs">
                    <div style="overflow: auto;">
                        <%
                        for(Products product:products)
                        {
                           int tempNumPage = numPage;
                           if(numPage > 1)
                           {
                                countReg++;
                                double divTempNumPage = (double) countReg / (double) numReg;
                                tempNumPage = (int) Math.ceil(divTempNumPage);
                           }
                                
                        %>
                        <div class="col s4">
                            <div class="card white" data-page="<%=tempNumPage%>">
                                <div class="card-content black-text">
                                    <span class="card-title"><%=product.getProductName()%></span>
                                    <center><img src="<%=product.getProductImage()%>" height="200"/></center>

                                    <p>$<%=product.getCost()%></p>
                                </div>
                                <div class="card-action">
                                    <a href="Products?accion=details&id=<%=product.getId()%>" 
                                       title="Añadir" class="btn-floating btn-large waves-effect waves-light purple">
                                        <i class="material-icons">add_shopping_cart</i>
                                    </a>
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
                <jsp:include page="/Views/Shared/paginacion.jsp">
                    <jsp:param name="numPage" value="<%=numPage%>"/>
                </jsp:include> 
            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>
