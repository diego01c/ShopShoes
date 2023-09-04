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
                <div class="container">
                    <div class="row" >
                        <div class="col s6">
                            <h5><%=product.getProductName()%></h5>
                        </div>
                        <div class="col s6 right-align">
                        </div>
                    </div>
                </div>
                <div style="display: flex;justify-content: space-between;height: 80vh;align-items: center;">
                    <div style="background-color: #e0e0e0;width: 45%;padding: 5px;background-color: transparent;">
                        <div class="col s6" style="margin-left: 30px;" >
                            <div class="row">
                                <div class="col s1 center-align" > 
                                    <img src="<%=product.getProductImage()%>" height="100" width="85" id="detailImage" />  
                                    <img src="<%=product.getDetailImageOne()%>" height="100"  width="85"  id="detailImageOne"style="margin-top: 10px" />  
                                    <img src="<%=product.getDetailImageTwo()%>" height="100"  width="85"  id="detailImageTwo" style="margin-top: 10px"/>
                                    <img src="<%=product.getDetailImageThree()%>" height="100"   width="85" id="detailImageThree" style="margin-top: 10px"/>
                                </div>
                                <div class="col s11">
                                    <center><img src="<%=product.getProductImage()%>" height="500" id="mainImage" /></center>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="background-color: #f0f0f0;width: 45%;padding: 20px;background-color: transparent;">
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
                            <div class="row">
                                <div class="col s12 center-align ">
                                    <h4 class="red-text">$<%=product.getCost()%></h4>
                                </div>
                            </div>
                    </div>
                </div>
            </form>  
            <form action="Trolley" method="post">   
                <input type="hidden" name="accion" value="create">
                <input type="hidden" name="idProduct" value="<%=product.getId()%>">
                <input type="number" name="quantity" id="quantity1" value="1" min="1" hidden style="text-align: center; display: none;width: 100px;width: 33%;height: 100%;border: none">
                <div class="container pt-10">
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
                <div class="row" style="margin-top: 50px">
                    <div class="col s6">
                        <div class="row center align" style="border: 3px solid;border-radius: 25px">
                            <button type="button" style="display: inline-block;height: 40px; width: 25%;height: 100%; border-top-left-radius: 20px; border-bottom-left-radius: 20px; float: left" class="waves-effect btn grey darken-4" onclick="decrementQuantity()">Restar</button>
                            <input type="number"  id="quantity" value="1" min="1" readonly style="text-align: center; display: inline-block;width: 100px;width: 33%;height: 100%;border: none; margin-top: 5px">
                            <button type="button" class="waves-effect btn grey darken-4" onclick="incrementQuantity()" style="display: inline-block;height: 40px;width: 25%;height: 100%; border-top-right-radius: 20px; border-bottom-right-radius: 20px; float: right">Agregar</button>
                        </div>
                    </div>
                    <div class="col s6">
                        <button type="submit" class="waves-effect waves-light btn grey darken-4" style="width: 100%; border-radius: 25px; height: 40px" <%=EstadoBtn%>>
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
                                        var quantityInput1 = document.getElementById("quantity1");
                                        var currentQuantity = parseInt(quantityInput.value);

                                        if (currentQuantity < maxQuantity) {
                                            quantityInput.value = currentQuantity + 1;
                                            quantityInput1.value = currentQuantity + 1;
                                        }
                                    }

                                    function decrementQuantity() {
                                        var quantityInput = document.getElementById("quantity");
                                        var quantityInput1 = document.getElementById("quantity1");
                                        var currentQuantity = parseInt(quantityInput.value);

                                        if (currentQuantity > 1) {
                                            quantityInput.value = currentQuantity - 1;
                                            quantityInput1.value = currentQuantity - 1;
                                        }
                                    }
                                    $(document).ready(function () {
                                        // Cuando se hace clic en la primera imagen de detalle
                                        $("#detailImage").click(function () {
                                            var newImageSrc = $(this).attr("src");
                                            $("#mainImage").attr("src", newImageSrc);
                                        });
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
