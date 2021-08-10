/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locnt.booking.BookingDAO;
import locnt.bookingdetail.BookingDetailDAO;
import locnt.dtos.AccountDTO;
import locnt.dtos.CartDTO;
import locnt.dtos.ResourceDTO;
import locnt.resource.ResourceDAO;

/**
 *
 * @author LocPC
 */
public class CheckoutServlet extends HttpServlet {

    private final String SUCCESS = "search.jsp";
    private final String FAIL = "invalid.html";

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
        String url = FAIL;
        try {
            BookingDAO dao = new BookingDAO();
            HttpSession session = request.getSession();
            AccountDTO dto = (AccountDTO) session.getAttribute("USER");
            HashMap<Integer, CartDTO> listResourceCart = (HashMap<Integer, CartDTO>) session.getAttribute("CART");
            Date dateNow = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            int bookingId = dao.checkoutBookingReturnBookingID(dateNow, dateNow, dateNow, 1, dto.getEmail());

            ResourceDAO resourceDAO = new ResourceDAO();

            if (bookingId > 0) {
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                for (CartDTO element : listResourceCart.values()) {
                    bookingDetailDAO.insertIntoBookingDetail(element.getQuantity(), bookingId, element.getResourceId());
                    ResourceDTO resourceDTO = resourceDAO.searchResourceById(element.getResourceId());
                    resourceDAO.updateQuantity(resourceDTO.getQuantity() - element.getQuantity(), element.getResourceId());
                }
            }
            url = SUCCESS;
            session.removeAttribute("CART");
        } catch (SQLException ex) {
            log("CheckoutServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutNaming_Naming " + ex.getMessage());
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
