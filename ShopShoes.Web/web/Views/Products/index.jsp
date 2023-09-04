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
                                <div class="card-content black-text" >                                  
                                    <center>
                                        <img src="<%=product.getProductImage()%>" style="height: 100%; width: 100%"/>
                                        <h6 class="black-text "><%=product.getProductName()%></h6>
                                    </center>
                                    <p class="red-text right">$<%=product.getCost()%></p>
                                </div>
                                <div class="card-action">
                                    

                                    <a href="Products?accion=details&id=<%=product.getId()%>" 
                                       title="Añadir" class="btn-large btn-large waves-effect grey darken-4" style="width: 100%">
                                        <i class="material-icons"></i>Comprar
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
