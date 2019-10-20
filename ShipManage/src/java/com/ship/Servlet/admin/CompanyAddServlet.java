/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin;

import com.ship.util.FileIO;
import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.MD5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.tomcat.util.http.HttpMessages;

/**
 *
 * @author wy105
 */
@WebServlet(name = "CompanyAddServlet", urlPatterns = {"/Admin/CompanyAddServlet"})
public class CompanyAddServlet extends HttpServlet {

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

        Company companyAdd = null;

        String companyId = "";
        String companyName = request.getParameter("companyName");
        String companyTel = request.getParameter("companyTel");
        String companyAddress = request.getParameter("companyAddress");
        String gid = request.getParameter("gid");

        HttpSession loginCompany = request.getSession();
        Manager loginManager = (Manager) loginCompany.getAttribute("manager");
        loginManager.setManager_password(MD5.EncryptionStr(loginManager.getManager_password(), MD5.MD5));

        int companyIdNumber = DaoFactory.GetShipDaoImp().AddShipId("Company");
        String numberStr = "";
        if (companyIdNumber < 999) {
            numberStr = "0";
            numberStr += String.valueOf(companyIdNumber);
        } else if (companyIdNumber < 9999) {
            numberStr += String.valueOf(companyIdNumber);
        }

        if (gid.equals("1")) {
            companyId = "GY" + numberStr;
            companyAdd = new Company(companyId, companyName, companyTel, companyAddress, "供应企业", "false");
        } else if (gid.equals("2")) {
            companyId = "YS" + numberStr;
            companyAdd = new Company(companyId, companyName, companyTel, companyAddress, "运输企业", "false");
        } else if (gid.equals("3")) {
            companyId = "JZ" + numberStr;
            companyAdd = new Company(companyId, companyName, companyTel, companyAddress, "建造企业", "false");
        }

        if (companyAdd != null) {
            boolean isAdd = DaoFactory.GetCompanyDaoImp().AddCompany(companyAdd);
            if (isAdd) {
                loginManager.setManager_Company_id(companyId);
                DaoFactory.GetManagerDaoImp().UpdateManager(loginManager); //在企业申请提交之后 将账号和企业绑定
                response.sendRedirect(request.getContextPath() + "/Admin/AdminLogout");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/CompanyAdd.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/CompanyAdd.jsp");
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
