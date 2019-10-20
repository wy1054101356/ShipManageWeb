/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet;

import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.Pagination;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet
/**
 *
 * @author wy105
 */
@WebServlet(name = "DisplayCompanyServlet", urlPatterns = {"/DisplayCompanyServlet"})
public class DisplayCompanyServlet extends HttpServlet {

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

        String mid = request.getParameter("mid");
        String pageNo = request.getParameter("pageNo");

        Pagination pagination = new Pagination();
        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        pagination.setUrl("DisplayCompanyServlet?");
        //所有专业信息
        List<Company> companys = new ArrayList<>();
        List<CompanyType> companyTypes = new ArrayList<>();
        companyTypes.add(new CompanyType(1, "供应企业"));
        companyTypes.add(new CompanyType(2, "运输企业"));
        companyTypes.add(new CompanyType(3, "建造企业"));

        if (mid != null) {
            for (int i = 0; i < companyTypes.size(); i++) {
                if (mid.equals(String.valueOf(companyTypes.get(i).getMid()))) {
                    companys = DaoFactory.GetCompanyDaoImp().GetCompanyByCompanyType(companyTypes.get(i).getName());
                    pagination.setUrl(pagination.getUrl() + "&mid=" + mid);
                }

            }
        } else {
            companys = DaoFactory.GetCompanyDaoImp().GetAllCompany(pagination);
        }

        request.setAttribute(
                "companyTypes", companyTypes);
        request.setAttribute(
                "companys", companys);
        request.setAttribute(
                "pagination", pagination);

        request.getRequestDispatcher(
                "DisplayCompany.jsp").forward(request, response);

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
