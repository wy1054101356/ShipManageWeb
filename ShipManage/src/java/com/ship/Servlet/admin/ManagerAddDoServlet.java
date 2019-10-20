/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin;

import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;
import com.ship.util.MD5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 思之声
 */
@WebServlet(name = "ManagerAddDoServlet", urlPatterns = {"/Admin/ManagerAddDoServlet"})
public class ManagerAddDoServlet extends HttpServlet {

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
        Manager managerAdd = null;

        String managerId = request.getParameter("managerId");
        String managerPassword = request.getParameter("managerPassword");

        HttpSession loginCompany = request.getSession();
        Manager loginManager = (Manager) loginCompany.getAttribute("manager");

        loginManager.setManager_password(MD5.EncryptionStr(loginManager.getManager_password(), MD5.MD5));

        managerAdd = new Manager(managerId, managerPassword, loginManager.getManager_Company_id(), "职员", "通过");
        DaoFactory.GetManagerDaoImp().AddManager(managerAdd);

        switch (loginManager.getCompany().getCompany_type()) {

            case "供应企业":
                response.sendRedirect(request.getContextPath() + "/admin/ProIndex.jsp");
                break;
            case "运输企业":
                response.sendRedirect(request.getContextPath() + "/admin/ConIndex.jsp");
                break;
            case "建造企业":
                response.sendRedirect(request.getContextPath() + "/admin/TraIndex.jsp");
                break;
            case "网站":
                response.sendRedirect(request.getContextPath() + "/admin/SystemIndex.jsp");
                break;
            case "政府":
                response.sendRedirect(request.getContextPath() + "/admin/GovIndex.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/Admin/AdminLogout");
                break;
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
