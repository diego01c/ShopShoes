<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shopshoes.entidadesdenegocio.Products" %>
<%@page import="shopshoes.accesoadatos.ProductsDAL" %>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Products> products = ProductsDAL.obtenerTodos();
   int id = Integer.parseInt(request.getParameter("Id"));
%>
<select id="slRol" name="Id">
    <option <%=(id == 0) ? "selected" : "" %> value="0">Seleccionar</option>
    <%for(Rol rol: roles)
    {
    %>
    <option <%=(id==rol.getId()) ? "selected" : "" %>
        value="<%=rol.getId()%>">
        <%=rol.getNombre()%>
    </option>
    <%
    }%>
</select>
<label for="slRol">Rol</label>
