/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locnt.booking.BookingDAO;
import locnt.dtos.BookingDTO;
import locnt.dtos.BookingRequestProcessDTO;
import locnt.resource.ResourceDAO;

/**
 *
 * @author LocPC
 */
public class SearchBookingServlet extends HttpServlet {

    private final String MANAGE_PROCESS_PAGE = "manageProcess.jsp";

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
        String url = MANAGE_PROCESS_PAGE;

        try {
            int status = 0;
            String valueDropDownList = request.getParameter("ddList");
            String page = request.getParameter("page");
            String value = request.getParameter("txtValue");
            int totalResult = 0;

            if (valueDropDownList.equals("new")) {
                status = 1;
            } else if (valueDropDownList.equals("delete")) {
                status = 3;
            } else {
                status = 2;
            }
            int pageNum = 1;
            if (page != null) {
                pageNum = Integer.parseInt(page);
            }
            BookingDAO dao = new BookingDAO();
            dao.searchBooking(value, status, pageNum);
            totalResult = dao.searchTotalBooking(value, status);
            int pages = (int) Math.ceil((double) totalResult / BookingDAO.ROW_PER_PAGE);
            request.setAttribute("PAGES", pages);
            request.setAttribute("ROW_PER_PAGE", ResourceDAO.ROW_PER_PAGE);

            List<BookingRequestProcessDTO> listBooking = dao.getListBooking();
            request.setAttribute("LISTBOOKINGSEARCH", listBooking);
            url = MANAGE_PROCESS_PAGE;
        } catch (SQLException ex) {
            log("SearchBookingServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchBookingServlet_Naming " + ex.getMessage());
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
