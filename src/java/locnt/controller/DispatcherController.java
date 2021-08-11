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
import javax.servlet.http.HttpSession;
import locnt.dtos.AccountDTO;

/**
 *
 * @author LocPC
 */
public class DispatcherController extends HttpServlet {

    private final String ERROR_PAGE = "errors.html";
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_RESOURCE_SERVLET = "SearchResourceServlet";
    private final String CREATE_NEW_ACCOUNT = "CreateNewAccountServlet";
    private final String REMOVE_ITEMS_SERVLET = "RemoveItemServlet";
    private final String ADD_ITEM_TO_CART_SERVLET = "AddItemToCartServlet";
    private final String CHECK_OUT_SERVLET = "CheckoutServlet";
    private final String SEARCH_BOOKING_SERVLET = "SearchBookingServlet";
    private final String UPDATE_BOOKING_SERVLET = "UpdateBookingServlet";
    private final String SHOW_HISTORY_BOOKING_SERVLET = "ShowHistoryBookingServlet";
    private final String DELETE_BOOKING_SERVLET = "DeleteBookingServlet";
    private final String VERIFY_CODE_SERVLET = "VerifyCodeServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR_PAGE;

        try {
            String button = request.getParameter("btAction");
            HttpSession session = request.getSession();
            AccountDTO user = (AccountDTO) session.getAttribute("USER");

            if (button == null) {
                url = LOGIN_PAGE;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("SearchHistory")) {
                url = SHOW_HISTORY_BOOKING_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_RESOURCE_SERVLET;
            } else if (button.equals("Create New Account")) {
                url = CREATE_NEW_ACCOUNT;
            } else if (button.equals("Verify")) {
                url = VERIFY_CODE_SERVLET;
            } else {
                if (user != null) {
                    if (user.getRoleId() == 1) { // role manager
                        if (button.equals("SearchBooking")) {
                            url = SEARCH_BOOKING_SERVLET;
                        } else if (button.equals("Update")) {
                            url = UPDATE_BOOKING_SERVLET;
                        }
                    } else if (user.getRoleId() == 2 || user.getRoleId() == 3) {// role leader and employee
                        if (button.equals("Add to cart")) {
                            url = ADD_ITEM_TO_CART_SERVLET;
                        } else if (button.equals("Remove Items")) {
                            url = REMOVE_ITEMS_SERVLET;
                        } else if (button.equals("Checkout")) {
                            url = CHECK_OUT_SERVLET;
                        } else if (button.equals("Delete")) {
                            url = DELETE_BOOKING_SERVLET;
                        }
                    }

                }
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
