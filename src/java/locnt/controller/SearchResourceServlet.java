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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locnt.dtos.ResourceDTO;
import locnt.resource.ResourceDAO;

/**
 *
 * @author LocPC
 */
public class SearchResourceServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";

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
            String category = request.getParameter("txtCategory");
            String name = request.getParameter("txtName");
            String dateFromString = request.getParameter("txtDateFrom");
            String dateToString = request.getParameter("txtDateTo");
            String page = request.getParameter("page");

            Date dateFrom;
            Date dateTo;
            boolean validate = true;
            int pageNum = 1;
            if (page != null) {
                pageNum = Integer.parseInt(page);
            }
            int totalResult = 0;
            if (!dateFromString.isEmpty() && !dateToString.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateFrom = new Date(sdf.parse(dateFromString).getTime());
                dateTo = new Date(sdf.parse(dateToString).getTime());
                if (!dateTo.after(dateFrom)) {
                    validate = false;
                }

            } else {
                dateFrom = null;
                dateTo = null;
            }
            if (validate) {
                ResourceDAO dao = new ResourceDAO();
                dao.searchResource(category, name, dateFrom, dateTo, pageNum);
                totalResult = dao.totoalRecordSearch(category, name, dateFrom, dateTo);
                List<ResourceDTO> listSearch = dao.getListSearch();
                request.setAttribute("SEARCHRESULT", listSearch);
                int pages = (int) Math.ceil((double) totalResult / ResourceDAO.ROW_PER_PAGE);
                request.setAttribute("PAGES", pages);
                request.setAttribute("ROW_PER_PAGE", ResourceDAO.ROW_PER_PAGE);
                url = SEARCH_PAGE;
            }

        } catch (ParseException ex) {
            log("CheckoutServlet_Parse " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutServlet_Naming " + ex.getMessage());
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
