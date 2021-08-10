/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locnt.dtos.CartDTO;
import locnt.dtos.ResourceDTO;
import locnt.resource.ResourceDAO;
import locnt.resource.ResourceSearch;

/**
 *
 * @author LocPC
 */
public class AddItemToCartServlet extends HttpServlet {

    private final String SEARCH_PAGE = "SearchResourceServlet";

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
        PrintWriter out = response.getWriter();
        String url = SEARCH_PAGE;
        try {
            String title = request.getParameter("txtItemName");
            int resourceId = Integer.parseInt(request.getParameter("txtResourceId"));
            
            HttpSession session = request.getSession();
            HashMap<Integer, CartDTO> listResourceCart = (HashMap<Integer, CartDTO>) session.getAttribute("CART");

            if (listResourceCart == null) {
                listResourceCart = new HashMap<>();
                CartDTO cartDTO = new CartDTO(resourceId, title, 1);
                listResourceCart.put(resourceId, cartDTO);

            } else {
                if (listResourceCart.containsKey(resourceId)) {
                    listResourceCart.get(resourceId).setQuantity(listResourceCart.get(resourceId).getQuantity() + 1);
                } else {
                    CartDTO cartDTO = new CartDTO(resourceId, title, 1);
                    listResourceCart.put(resourceId, cartDTO);
                }
            }
            session.setAttribute("CART", listResourceCart);
            
            
            String category = request.getParameter("txtCategory");
            String name = request.getParameter("txtName");
            String dateFromString = request.getParameter("txtDateFrom");
            String dateToString = request.getParameter("txtDateTo");
            url = "DispatcherController"
                    + "?txtCategory=" + category
                    + "&txtName=" + name
                    + "&txtDateFrom=" + dateFromString
                    + "&txtDateTo=" + dateToString
                    + "&btAction=Search";

        } finally {
            response.sendRedirect(url);
            out.close();
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

}
