<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Direction"%>


<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Pagar Carrito</title>
    </head>
    <link rel="stylesheet" type="text/css" href="wwwroot/cssw/pago.css">
    <style>
        .tarjeta .delantera {
            width: 100%;
            background: url('./wwwroot/image/base.jpg');
            background-size: cover;
        }
        
         .trasera {
            background: url('./wwwroot/image/base.jpg');
            background-size: cover;
            position: absolute;
            top: 0;
            transform: rotateY(180deg);
            backface-visibility: hidden;
        }
    </style>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <div class="contenedor">

            <!-- Tarjeta -->
            <section class="tarjeta" id="tarjeta">
               
                <div class="delantera">
                    <div class="logo-marca" id="logo-marca">
                        <!-- <img src="img/logos/visa.png" alt=""> -->
                    </div>
                    <img src="./wwwroot/image/chip.png" class="chip" alt="">
                    <div class="datos">
                        <div class="grupo" id="numero">
                            <p class="label">Número Tarjeta</p>
                            <p class="numero">#### #### #### ####</p>
                        </div>
                        <div class="flexbox">
                            <div class="grupo" id="nombre">
                                <p class="label">Nombre Tarjeta</p>
                                <p class="nombre">ShopShoes</p>
                            </div>

                            <div class="grupo" id="expiracion">
                                <p class="label">Expiracion</p>
                                <p class="expiracion"><span class="year">MM/AA</span></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="trasera">
                    <div class="barra-magnetica"></div>
                    <div class="datos">
                        <div class="grupo" id="firma">
                            <p class="label">Firma</p>
                            <div class="firma"><p></p></div>
                        </div>
                        <div class="grupo" id="ccv">
                            <p class="label">CCV</p>
                            <p class="ccv"></p>
                        </div>
                    </div>
                    <p class="leyenda">Tarjeta de Credito Autorizada, Tu Banco ha firmado lo necesario para la verificacion 
                        de tu tarjeta. Tu Banco</p>
                    <a href="#" class="link-banco">www.nombredebanco.com</a>
                </div>
            </section>



        </div>

        <main class="container">
            <div class="row center-align">

                <div class="col 15 s12">
                    <div class="card login">
                        <div class="card-content black-text">
                            <h5>PAGO</h5>
                            <form action="Sales?action=index" id="formulario-tarjeta" class="formulario-tarjeta" method="post">

                                <div class="row">
                                    <div class="input-field col s6">
                                        <input type="text" id="inputNumero" name ="UserName" required 
                                               class="validate" maxlength="19" style="border: 1px solid black; border-radius: 10px;" >
                                        <label for="inputNumero" style="background-color:#ffffff; margin-left: 10px; width: 120px">Número Tarjeta</label>

                                    </div>

                                    <div class="input-field col s6">
                                        <label for="inputNombre" style="background-color:#ffffff; margin-left: 10px; width: 50px">Nombre</label>
                                        <input type="text" id="inputNombre" maxlength="19" autocomplete="off"  style="border: 1px solid black; border-radius: 10px;">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="input-field col s6">

                                        <label for="selectMes">Expiracion</label>

                                        <input type="month" min="2023-01" value="2023-09"id="monthS" style="width: 250px; margin-top: 20px">

                                    </div>

                                    <div class="input-field col s6">
                                        <label for="inputCCV" style="background-color:#ffffff; margin-left: 10px; width: 20px">CCV</label>
                                        <input type="text" id="inputCCV" maxlength="3" style="border: 1px solid black; border-radius: 10px;">
                                    </div>


                                </div>


                        </div>



                        <div class="row">
                            <div class="col 112 s12">
                                <button type="submit" class="waves-effect waves-light btn black">
                                    <i class="material-icons right"></i>Pagar
                                </button>

                            </div>
                        </div>
                        <div class="row">
                            
                        </div>
                        </form>
                    </div>

                </div>
            </div>

        </div>


    </main>
    <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    <script src="wwwroot/js/pago.js"></script>
</body>
</html>

