<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.web.utils.*"%>
<!--<nav> 
    <div class="nav-wrapper blue">
        <a href="Home" class="brand-logo">SysSeguridad</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
            <% if (SessionUser.isAuth(request)) {  %> 
            <li><a href="Home">Inicio</a></li>
            <li><a href="Category">Categorias</a></li>
            <li><a href="User?accion=crearcuenta">CrearCuenta</a></li>
            <li><a href="User?accion=cambiarpass">Cambiar password</a></li>
            <li><a href="User?accion=perfil"><%=session.getAttribute("user")%></a></li>
            <li><a href="User?accion=login">Cerrar sesión</a></li>
                <%}%>
        </ul>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
    <% if (SessionUser.isAuth(request)) {  %>
    <li><a href="Home">Inicio</a></li>
    <li><a href="Category">Categorias</a></li>
    <li><a href="User?accion=crearcuenta">Rol</a></li>
    <li><a href="User?accion=cambiarpass">Cambiar password</a></li>
    <li><a href="User?accion=login">Cerrar sesión</a></li>
        <%}%>
</ul>-->      
<div class="container" style="margin-left: 10px; width: 50px; height: 0px">
    <a href="#" data-target="slide-out" class="sidenav-trigger" ><i class="material-icons" style="color: black; font-size: 50px;">dehaze</i></a>
    <ul id="slide-out" class="sidenav">
        <% if (SessionUser.isAuth(request)) {  %> 
       <li><a href="Home">Inicio</a></li>
       <li><a href="Category">Categorias</a></li>
       <li><a href="User?accion=crearcuenta">CrearCuenta</a></li>
       <li><a href="User?accion=cambiarpass">Cambiar password</a></li>
       <li><a href="User?accion=perfil"><%=session.getAttribute("user")%></a></li>
       <li><a href="User?accion=login">Cerrar sesión</a></li>
        <%}%>
    </ul>
</div>
    <ul class="sidenav" id="mobile-demo">
    <% if (SessionUser.isAuth(request)) {  %>
    <li><a href="Home">Inicio</a></li>
    <li><a href="Category">Categorias</a></li>
    <li><a href="User?accion=crearcuenta">Rol</a></li>
    <li><a href="User?accion=cambiarpass">Cambiar password</a></li>
    <li><a href="User?accion=login">Cerrar sesión</a></li>
        <%}%>
</ul>
<script>
    document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
    });
</script>
