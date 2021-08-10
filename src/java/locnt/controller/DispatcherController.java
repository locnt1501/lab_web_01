/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LocPC
 */
public class DispatcherController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_RESOURCE_SERVLET = "SearchResourceServlet";
    private final String CREATE_NEW_ACCOUNT = "CreateNewAccountServlet";
    private final String REMOVE_ITEMS_SERVLET = "RemoveItemServlet";
    private final String ADD_ITEM_TO_CART_SERVLET = "AddItemToCartServlet";
    private final String CHECK_OUT_SERVLET = "CheckoutServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
        try {
            String button = request.getParameter("btAction");
            if (button == null) {
                url = LOGIN_PAGE;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_RESOURCE_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("Create New Account")) {
                url = CREATE_NEW_ACCOUNT;
            } else if (button.equals("Add to cart")) {
                url = ADD_ITEM_TO_CART_SERVLET;
            } else if (button.equals("Remove Items")) {
                url = REMOVE_ITEMS_SERVLET;
            } else if (button.equals("Checkout")) {
                url = CHECK_OUT_SERVLET;
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
