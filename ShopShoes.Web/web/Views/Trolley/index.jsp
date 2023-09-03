<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Products"%>
<%@page import="shopshoes.entidadesdenegocio.Client"%>
<%@page import="shopshoes.entidadesdenegocio.Trolley"%>
<%@page import="shopshoes.entidadesdenegocio.Inventory"%>
<%@page import="shopshoes.accesoadatos.TrolleyDAL"%>
<%@page import="shopshoes.accesoadatos.InventoryDAL"%>
<%@page import="shopshoes.web.utils.*"%>
<%@page import="java.util.ArrayList"%>
<% Trolley trolley = (Trolley) request.getAttribute("trolley");%>
<%ArrayList<Trolley> products = (ArrayList<Trolley>) request.getAttribute("products");
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
                <%
                    String visibleorNot = "";
                    if(products.size() == 0){
                        visibleorNot= "disabled";
                    }
                    if(products.size() != 0){
                        visibleorNot = "";
                    }
                %>
                <div class="row" style="margin-top: 1%">
                    <div class="col s12">
                        <a href="Direction?accion=index" 
                           title="Crear" class="waves-effect waves-light btn black" style="float: right;" <%=visibleorNot%>>
                            <i class="material-icons right"></i> Pagar
                        </a>

                    </div>
                </div>
                <div class="paginationjs">
                    <div style="overflow: auto;">
                        <%
                        for(Trolley product:products)
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
                                    <span class="card-title"><%=product.getProduct().getProductName()%></span>
                                    <center><img src="<%=product.getProduct().getProductImage()%>" height="200"/></center>
                                        <%                                        
                                           double totalCosto = product.getQuantity() * product.getProduct().getCost();
                                        %>
                                    <div class="row " style="margin-top:1%;">
                                        <div class="col s6">
                                            <p>Costo Total:</p>
                                        </div>
                                        <div class="col s4">
                                            <p>$<%=totalCosto%></p> 
                                        </div>
                                    </div>
                                    <%Inventory inventory = new Inventory();
                                    inventory.setIdProduct(product.getIdProduct());
                                    Inventory inventoryResult = InventoryDAL.obtenerPorIdProduct(inventory);
                                    int productStock = inventoryResult.getStock();%>
                                    <div class="row">
                                        <div class="col s6">
                                            <p>Unidades:</p>

                                        </div>
                                        <div class="col s4">
                                            <p  name="quantity" id="quantity" readonly ><%=product.getQuantity()%></p>

                                        </div>
                                        <p  class="grey-text"style="font-size:15px; float:right;">Disponibles: <%=productStock%></p>
                                    </div>
                                    <form action="Trolley" method="post" >
                                        <div class="row" style="margin-top:1%">
                                            <div class="col s12">
                                                <input type="hidden" name="accion" value="delete">
                                                <input type="hidden" name="idtrolley" value="<%=product.getId()%>">
                                                <button type="submit" class="waves-effect waves-light btn black" style="width: 100%" >Delete</button>
                                            </div>
                                        </div>
                                    </form>                              
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