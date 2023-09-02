<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Products" %>
<%@page import="shopshoes.entidadesdenegocio.Users" %>
<%@page import="shopshoes.entidadesdenegocio.Client" %>
<%@page import="shopshoes.entidadesdenegocio.Inventory" %>
<%@page import="shopshoes.accesoadatos.InventoryDAL"%>
<% Products product = (Products) request.getAttribute("product");%>
<% Users users = (Users) request.getAttribute("users");%>
<% Client client = (Client) request.getAttribute("client");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Ver Producto</title>     
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main style="margin-top: 0px">
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
                        <div class="col s12">
                            <center><img src="<%=product.getProductImage()%>" height="175" id="mainImage" /></center>
                        </div>
                    </div>

                    <div class="row center-align">
                        <div class="col s4">
                            <img src="<%=product.getDetailImageOne()%>" height="80" id="detailImageOne" />  
                        </div>
                        <div class="col s4">
                            <img src="<%=product.getDetailImageTwo()%>" height="80" id="detailImageTwo" />
                        </div>
                        <div class="col s4">
                            <img src="<%=product.getDetailImageThree()%>" height="80" id="detailImageThree" />
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
                <%
                    Inventory inventory = new Inventory();
                    inventory.setIdProduct(product.getId());
                    Inventory inventoryResult = InventoryDAL.obtenerPorIdProduct(inventory);
                    int productStock = inventoryResult.getStock();
                    String stockMessage;
                    if (productStock > 0) {
                        stockMessage = "El producto está disponible.";
                    } else {
                        stockMessage = "El producto está agotado.";
                    }
                %>
                    <div class="row">
                        <div class="col s1">
                        </div>
                        <div class="col s2">
                            <p><b>Scock: </b></p>
                        </div>
                        <div class="col s8 right-align">
                            <p><%=productStock%></p>
                        </div>
                    </div>
                </div>
            </form>
            
            <form action="Trolley" method="post">   
                <input type="hidden" name="accion" value="create">
                <input type="hidden" name="idProduct" value="<%=product.getId()%>">
                <div class="container pt-10">
                    <div class="row">
                        <div class="col s3"></div>
                        <div class="col s2" style="align-items: center;">
                            <button type="button" style="float: right;" class="waves-effect btn grey darken-4" onclick="decrementQuantity()">Restar</button>
                        </div>
                        <div class="col s2">
                            <input type="number" name="quantity" id="quantity" value="1" min="1" readonly style="text-align: center; border: 1px solid;">
                        </div>
                        <div class="col s2">
                            <button type="button" class="waves-effect btn grey darken-4" onclick="incrementQuantity()">Agregar</button>
                        </div>
                    </div>
                </div>
                <%
                    String EstadoBtn = "";
                    if(productStock==0){
                        EstadoBtn= "disabled";
                    }
                    if(productStock!=0){
                        EstadoBtn="";
                    }
                %>
                <div class="row">
                    <div class="col s12">
                        <button type="submit" class="waves-effect waves-light btn grey darken-4" style="float: right;" <%=EstadoBtn%>>
                            <i class="material-icons right">add_shopping_cart</i>Añadir al Carrito
                        </button>
                    </div>
                </div>

            </form>
        </main>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            var maxQuantity = <%=productStock%>;// Obtén el valor máximo de la base de datos

            function incrementQuantity() {
                var quantityInput = document.getElementById("quantity");
                var currentQuantity = parseInt(quantityInput.value);

                if (currentQuantity < maxQuantity) {
                    quantityInput.value = currentQuantity + 1;
                }
            }

            function decrementQuantity() {
                var quantityInput = document.getElementById("quantity");
                var currentQuantity = parseInt(quantityInput.value);

                if (currentQuantity > 1) {
                    quantityInput.value = currentQuantity - 1;
                }
            }         
            $(document).ready(function () {
                // Cuando se hace clic en la primera imagen de detalle
                $("#detailImageOne").click(function () {
                    var newImageSrc = $(this).attr("src");
                    $("#mainImage").attr("src", newImageSrc);
                });

                // Cuando se hace clic en la segunda imagen de detalle
                $("#detailImageTwo").click(function () {
                    var newImageSrc = $(this).attr("src");
                    $("#mainImage").attr("src", newImageSrc);
                });

                // Cuando se hace clic en la tercera imagen de detalle
                $("#detailImageThree").click(function () {
                    var newImageSrc = $(this).attr("src");
                    $("#mainImage").attr("src", newImageSrc);
                });
            });
        </script>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>
