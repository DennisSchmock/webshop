/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bottom;
import model.Cupcake;
import model.DbHandler;
import model.Topping;
import model.User;

/**
 *
 * @author dennisschmock
 */
@WebServlet(name = "FrontControl", urlPatterns = {"/webshop"})
public class WebShopFront extends HttpServlet {

    List<Cupcake> shoppingCart = new ArrayList<>();
    List<Topping> toppings;
    List<Bottom> bottoms;
    User user;
    Double totalPrice = 0.00;

    DbHandler dbHandle = new DbHandler();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/index.jsp";
        String page = request.getParameter("page");
       
        if (page == null) {
            page = "";
        }
        if (page.equalsIgnoreCase("login")) {
            url = "/login.jsp";
        }

        request.getSession().setAttribute("cart", shoppingCart);
        if (page != null && page.equalsIgnoreCase("shop")) {
            url = showShop(request, response);
        }
        
        if (page.equalsIgnoreCase("cart")) {
            if (request.getParameter("controlCart") != null) {
                controlCart(request, response);
            }
            url = "/cart.jsp";

        }
        if (page.equalsIgnoreCase("checkOut")) {
            url = checkBasketOut(request, url);

        }
        //log user out, and kill session
        if (request.getParameter("logout") != null && request.getParameter("logout").equalsIgnoreCase("true")) {
            request.setAttribute("user", null);
            request.setAttribute("loggedin", false);
            request.getSession().invalidate();
            // logout(request, response);
        }

        String login = request.getParameter("login");
        if (login != null && login.equalsIgnoreCase("login")) {
            this.login(request, response);
        }
        
        this.totalPrice = getTotalPrice(); //calculete total price of basket each time we d something.
        request.getSession().setAttribute("totalprice", this.totalPrice);
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    //Finalizes basket and sends the content to DBHandler.
    private String checkBasketOut(HttpServletRequest request, String url) {
        if (dbHandle.checkOut(this.shoppingCart, this.user)) {
            List<Cupcake> executedshoppingCart = new ArrayList<>();
            for (Cupcake shoppingCart1 : shoppingCart) {
                executedshoppingCart.add(shoppingCart1);
            }
            request.getSession().setAttribute("checkedout", true);
            request.getSession().setAttribute("basket", executedshoppingCart);
            request.getSession().setAttribute("totalPrice", this.totalPrice);
            user = dbHandle.loadUser(user.getUserName());
            request.getSession().setAttribute("user", user);

            this.shoppingCart.clear();
            url = "/invoice.jsp";
        } else if (user.getBalance() < this.totalPrice) {
            request.getSession().setAttribute("notEnoughMoney", true);
            url = "/cart.jsp";
        }
        return url;
    }

    //Get shopitems from DB and send them to view.
    private String showShop(HttpServletRequest request, HttpServletResponse response) {
        String url;
        this.toppings = dbHandle.getToppings();
        this.bottoms = dbHandle.getBottoms();
        request.getSession().setAttribute("bottoms", bottoms);
        request.getSession().setAttribute("toppings", toppings);
        url = "/shop.jsp";
        if (request.getParameter("topping_choice") != null && request.getParameter("bottom_choice") != null) {
            buildCupCake(request, response);
        }

        return url;
    }

    //Validate user and add him/her to session
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getParameter("username");
        String pwd = (String) request.getParameter("pwd");

        if (dbHandle.validateUser(username, pwd)) {
            request.getSession().setAttribute("loggedin", true);
            user = dbHandle.loadUser(username);
            request.getSession().setAttribute("user", user);
        } else {
            request.getSession().setAttribute("loggedin", false);

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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("user", null);
        request.setAttribute("loggedin", null);
        this.user = null;
        request.getSession().invalidate();
    }

    private List<Bottom> getBottoms() {
        return dbHandle.getBottoms();
    }

    private List<Topping> getTopping() {
        return dbHandle.getToppings();
    }

    //Make a cupcake object and put it in cart (LIST)
    private void buildCupCake(HttpServletRequest request, HttpServletResponse response) {
        String idtop = request.getParameter("topping_choice");
        String idbot = request.getParameter("bottom_choice");

        int topId = Integer.parseInt(idtop);
        int botId = Integer.parseInt(idbot);
        Bottom bot = bottoms.get(botId - 1);
        Topping top = toppings.get(topId - 1);
        Cupcake cupcake = new Cupcake(bot, top);
        for (Cupcake cupc : shoppingCart) {
            if (cupcake.getBottom().getId() == cupc.getBottom().getId() && cupcake.getTopping().getId() == cupc.getTopping().getId()) {
                cupc.setQty(cupc.getQty() + 1);
                return;
            }
        }
        shoppingCart.add(cupcake);
        request.getSession().setAttribute("cart", shoppingCart);

    }

    //Method for add and subtracting items from cart. 
    private void controlCart(HttpServletRequest request, HttpServletResponse response) {
        String controlCart = request.getParameter("controlCart");
        String item = request.getParameter("itemNumber");
        int itemNumber = Integer.parseInt(item);
        Cupcake cupcake = shoppingCart.get(itemNumber);
        if (controlCart.equalsIgnoreCase("-")) {
            cupcake.setQty(cupcake.getQty() - 1);
        }
        if (controlCart.equalsIgnoreCase("+")) {
            cupcake.setQty(cupcake.getQty() + 1);
        }
        if (controlCart.equalsIgnoreCase("%")) {
            shoppingCart.remove(itemNumber);
        }
        if (cupcake.getQty() < 1) {
            shoppingCart.remove(itemNumber);
        }

        request.setAttribute("totalprice", this.totalPrice);

    }

    //Method for summing up the total price from shopping cart.
    private double getTotalPrice() {
        double totalPrice = 0.00;
        for (Cupcake cup : shoppingCart) {
            totalPrice = totalPrice + (cup.getPrice() * cup.getQty());
        }
        return totalPrice;
    }

}
