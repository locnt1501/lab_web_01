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
import java.text.SimpleDateFormat;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locnt.account.AccountCreateError;
import locnt.account.AccountDAO;
import locnt.dtos.AccountDTO;

/**
 *
 * @author LocPC
 */
public class CreateNewAccountServlet extends HttpServlet {

    private final String SUCCESS = "login.jsp";
    private final String FAIL = "createNewAccount.jsp";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String repeatPassword = request.getParameter("txtRepeatPassword");
        String name = request.getParameter("txtName");
        String phone = request.getParameter("txtPhoneNumber");
        String address = request.getParameter("txtAddress");
        int role = 1;
        int status = 1;
        String url = FAIL;

        AccountCreateError errors = new AccountCreateError();
        boolean foundErr = false;
        try {

            if (!password.equals(repeatPassword)) {
                foundErr = true;
                errors.setConfirmNotMatchPassword("Repeat Password must match Password");
            }

            if (foundErr) {
                request.setAttribute("CREATEERROR", errors);
            } else {
                Date dateNow = new Date(System.currentTimeMillis() - 24*60*60*1000);
                AccountDTO dto = new AccountDTO(email, password, name, address, role, dateNow, role, status);
                AccountDAO dao = new AccountDAO();
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = SUCCESS;
                } else {
                    url = FAIL;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet_SQL " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " is existed");
                request.setAttribute("CREATEERROR", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_Naming " + ex.getMessage());
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
