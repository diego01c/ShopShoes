<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.web.utils.*"%>
<% if (SessionUser.isAuth(request) == false) {
         response.sendRedirect("User?accion=login");
    }
%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Home</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container"> 
            <div class="row">
                <div class="col l12 s12">
                    <center><h1>Bienvenidos a ShopShoes</h1></center>
                    <center><img src="https://res.cloudinary.com/dcv9tzvbj/image/upload/v1693805842/ShopShoes/Sin_t%C3%ADtulo-1_ycbrmv.png" height="500"/></center> 
                    <center><h5>Tu mejor opcion en calidad y precio</h5></center>
                    
                </div>
            </div>            
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
