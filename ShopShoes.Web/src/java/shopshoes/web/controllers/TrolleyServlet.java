/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shopshoes.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import shopshoes.accesoadatos.ClientDAL;
import shopshoes.accesoadatos.InventoryDAL;
import shopshoes.accesoadatos.ProductsDAL;
import shopshoes.accesoadatos.TrolleyDAL;
import shopshoes.accesoadatos.UsersDAL;
import shopshoes.entidadesdenegocio.Client;
import shopshoes.entidadesdenegocio.Inventory;
import shopshoes.entidadesdenegocio.Products;
import shopshoes.entidadesdenegocio.Trolley;
import shopshoes.entidadesdenegocio.Users;
import shopshoes.web.utils.SessionUser;
import shopshoes.web.utils.Utilidad;

/**
 *
 * @author alexg
 */
@WebServlet(name = "TrolleyServlet", urlPatterns = {"/Trolley"})
public class TrolleyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Trolley obtenerTrolley(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        HttpSession session = (HttpSession) request.getSession();
        int inicio = (int) session.getAttribute("id_inicio");
        Trolley trolley = new Trolley();
        Client client = new Client();
        try {
            client = ClientDAL.obtenerPorId(inicio);
            int id = client.getId();
            trolley.setIdClient(id);
        } catch (Exception ex) {
        }
        trolley.setId(Integer.parseInt(Utilidad.getParameter(request, "idtrolley", "0")));
        trolley.setIdProduct(Integer.parseInt(Utilidad.getParameter(request, "idProduct",
                "0")));
        trolley.setQuantity(Integer.parseInt(Utilidad.getParameter(request, "quantity",
                "0")));
        trolley.setStatusTrolley((byte) 1);
        if (accion.equals("create") == false) {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            trolley.setStatusTrolley((byte) 1);
            trolley.setIdProduct(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        if (accion.equals("index")) {
            trolley.setTop_aux(Integer.parseInt(Utilidad.getParameter(request,
                    "TopAux", "10")));
            trolley.setTop_aux(trolley.getTop_aux() == 0 ? Integer.MAX_VALUE : trolley.getTop_aux());
        }
        return trolley;
    }

    private Products obtenerProduct(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Products product = new Products();
        if (accion.equals("create") == false) {
            //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
            //Id de la instancia
            product.setIdCategory(Integer.parseInt(Utilidad.getParameter(request, "id",
                    "0")));
        }
        product.setProductName(Utilidad.getParameter(request, "ProductName", ""));
        if (accion.equals("index")) {
            product.setTopAux(Integer.parseInt(Utilidad.getParameter(request,
                    "TopAux", "10")));
            product.setTopAux(product.getTopAux() == 0 ? Integer.MAX_VALUE : product.getTopAux());
        }
        return product;
    }

    protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = obtenerTrolley(request);
            HttpSession session = (HttpSession) request.getSession();
            int inicio = (int) session.getAttribute("id_inicio");
            Client client = new Client();
            client = ClientDAL.obtenerPorId(inicio);
            trolley.setIdClient(client.getId());
            ArrayList<Trolley> trolleyss = TrolleyDAL.obtenerPorIdP(trolley);
            // Crear una lista para almacenar los objetos Productos
            ArrayList<Trolley> productsList = new ArrayList<>();

            // Recorrer la lista de trolleys y obtener los IdProductos
            for (Trolley trolleyssd : trolleyss) {
                int idProducto = trolleyssd.getIdProduct();
                Products product = new Products();
                product.setId(idProducto);
                // Aquí podrías hacer una consulta a la base de datos o utilizar algún otro método
                // para obtener el objeto Producto correspondiente al idProducto
                Products products = ProductsDAL.obtenerPorId(product);

                Trolley carrito = new Trolley();
                carrito.setProduct(products);
                carrito.setId(trolleyssd.getId());
                carrito.setIdClient(trolleyssd.getIdClient());
                carrito.setIdProduct(trolleyssd.getIdProduct());
                carrito.setQuantity(trolleyssd.getQuantity());

                if (products != null) {
                    productsList.add(carrito);
                }
            }
            Products product = new Products();
            product.setTopAux(10);
            request.setAttribute("products", productsList);
            request.setAttribute("TopAux", product.getTopAux());
            request.getRequestDispatcher("Views/Trolley/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = obtenerTrolley(request);
            Products product = obtenerProduct(request);
            int c = trolley.getIdProduct();
            product.setId(c);
            ArrayList<Products> products = ProductsDAL.buscar(product);
            request.setAttribute("products", products);
            request.setAttribute("TopAux", product.getTopAux());
            request.getRequestDispatcher("Views/Products/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Trolley/create.jsp")
                .forward(request, response);
    }

    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = obtenerTrolley(request);
            HttpSession session = (HttpSession) request.getSession();
            int inicio = (int) session.getAttribute("id_inicio");
            Client client = new Client();
            client = ClientDAL.obtenerPorId(inicio);
            trolley.setIdClient(client.getId());
            int validar = TrolleyDAL.obtenerIdPorIdProduct(trolley);
            if (validar == 0) {
                int result = TrolleyDAL.crear(trolley);
                if (result != 0) {
                    request.setAttribute("accion", "index");
                    doGetRequestIndex(request, response);
                } else {
                    Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
                }
            }
            if (validar != 0) {
                Trolley trResul = TrolleyDAL.obtenerPorIdProduct(trolley);
                trResul.setIdClient(client.getId());
                int QuantityA = trolley.getQuantity();
                int QuantityB = trResul.getQuantity();
                int quantityF = QuantityA + QuantityB;
                Inventory inventory = new Inventory();
                inventory.setIdProduct(trResul.getIdProduct());
                Inventory inventoryResul = InventoryDAL.obtenerPorIdProduct(inventory);
                if (inventoryResul.getStock() == 0) {
                    TrolleyDAL.eliminar(trResul);
                }

                if (quantityF > inventoryResul.getStock()) {
                    trResul.setQuantity(inventoryResul.getStock());
                    int trResulF = TrolleyDAL.modificar(trResul);
                    if (trResulF == 1) {
                        request.setAttribute("accion", "index");
                        doGetRequestIndex(request, response);
                    }
                } else if (quantityF < inventoryResul.getStock()) {
                    trResul.setQuantity(quantityF);
                    int trResult = TrolleyDAL.modificar(trResul);
                    if (trResult != 0) {
                        request.setAttribute("accion", "index");
                        doGetRequestIndex(request, response);
                    }
                }
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Trolley trolley = obtenerTrolley(request);
            int result = TrolleyDAL.eliminar(trolley);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("Error al Eliminar el Regisgtro", request, response);
            }

        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request,
                    "accion", "index");
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
            }
        });
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Utilidad.getParameter(request,
                "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doPostRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doPostRequestCreate(request, response);
                break;
            case "delete":
                request.setAttribute("accion", accion);
                doPostRequestDelete(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
