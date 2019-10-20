/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin;

import com.ship.model.Company;
import com.ship.util.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wy105
 */
@WebServlet(name = "CompanyUpdateDoServlet", urlPatterns = {"/Admin/CompanyUpdateDoServlet"})
public class CompanyUpdateDoServlet extends HttpServlet {

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
        Company companyUpdate = null;

        String companyId = request.getParameter("companyId");

        String check = request.getParameter("check");
        if (check != null && !check.equals("")) {
            Company company = DaoFactory.GetCompanyDaoImp().GetACompany(companyId);
            company.setCompany_state("已上市");
            DaoFactory.GetCompanyDaoImp().UpdateCompany(company);
            response.sendRedirect(request.getContextPath() + "/Admin/CompanyManageServlet");
        } else {
            String companyName = request.getParameter("companyName");
            String companyTel = request.getParameter("companyTel");
            String companyAddress = request.getParameter("companyAddress");
            String companyState = request.getParameter("companyState");

            String gid = request.getParameter("gid");
            if (gid.equals("1")) {
                companyUpdate = new Company(companyId, companyName, companyTel, companyAddress, "供应企业", companyState);
            } else if (gid.equals("2")) {
                companyUpdate = new Company(companyId, companyName, companyTel, companyAddress, "运输企业", companyState);
            } else if (gid.equals("3")) {
                companyUpdate = new Company(companyId, companyName, companyTel, companyAddress, "建造企业", companyState);
            }

            if (companyUpdate != null) {
                boolean isUpdate = DaoFactory.GetCompanyDaoImp().UpdateCompany(companyUpdate);
                if (isUpdate) {
                    response.sendRedirect(request.getContextPath() + "/Admin/CompanyManageServlet");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/CompanyUpdate.jsp");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/CompanyUpdate.jsp");
            }
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
