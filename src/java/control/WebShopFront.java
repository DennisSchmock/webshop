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
import model.DbHandler;
import model.Topping;
import model.User;

/**
 *
 * @author dennisschmock
 */
@WebServlet(name = "FrontControl", urlPatterns = {"/webshop"})
public class WebShopFront extends HttpServlet {

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet WebShopFront</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet WebShopFront at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        
        String url = "/index.jsp"; 
         
        if(request.getParameter("page").equalsIgnoreCase("shop")){
            List<Topping> toppings = dbHandle.getToppings();
            List<Bottom> bottoms = dbHandle.getBottoms();
            request.getSession().setAttribute("bottoms", bottoms);
            request.getSession().setAttribute("toppings", toppings);
            url = "/shop.jsp";
        }

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
       RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getParameter("username");
        String pwd = (String) request.getParameter("pwd");

        if (dbHandle.validateUser(username, pwd)) {
            request.getSession().setAttribute("loggedin", true);
            User user = dbHandle.loadUser(username);
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
        request.getSession().invalidate();
    }
    
    private List<Bottom> getBottoms(){
        return dbHandle.getBottoms();
    }
    
    private List<Topping> getTopping(){
        return dbHandle.getToppings();
    }

}
