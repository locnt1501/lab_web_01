/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locnt.account.AccountDAO;
import locnt.account.AccountLoginError;
import locnt.dtos.AccountDTO;
import locnt.utils.VerifyRecaptcha;

/**
 *
 * @author LocPC
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";
    private final String LOGIN_PAGE = "login.jsp";

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
        String url = INVALID_PAGE;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String gRecaptchResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchResponse);
            AccountDAO dao = new AccountDAO();
            boolean result = dao.checkLogin(email, password);
            boolean foundErr = false;
            AccountLoginError errors = new AccountLoginError();
            if (result == false) {
                foundErr = true;
                errors.setEmailOrPasswordIncorrect("email or password incorrect!!!");
            }
//            if (verify == false) {
//                foundErr = true;
//                errors.setReCAPTCHANotChecked("reCAPTCHA not checked!!!");
//            }
            if (foundErr) {
                request.setAttribute("LOGINERROR", errors);
                url = LOGIN_PAGE;
            } else {
                if (result) {
//                     && verify
                    AccountDTO dto = dao.getInformation(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                    if (dto.getRoleId() == 1) { //role manager
                        url = "manageProcess.jsp";
                    } else if (dto.getRoleId() == 2) { //role leader

                    } else {
                        url = SEARCH_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            log("LoginServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_Naming " + ex.getMessage());
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
