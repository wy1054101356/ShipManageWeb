/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Construct;

import com.ship.model.*;
import com.ship.model.TransportOrder;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author 思之声
 */
@WebServlet(name = "ConConOrderManageS", urlPatterns = {"/Admin/ConConOrderManageS"})
public class ConConOrderManageS extends HttpServlet {

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

        HttpSession loginCompany = request.getSession();
        Manager loginManager = (Manager) loginCompany.getAttribute("manager");

        Pagination pagination = new Pagination();
        String pageNo = request.getParameter("pageNo");
        String constructOrderId = request.getParameter("constructOrderId");
        String companyId = request.getParameter("companyId");
        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        List<ConstructOrder> constructOrders = new ArrayList<>();

        if (constructOrderId != null && !"".equals(constructOrderId)) {
            pagination.setUrl("ConConOrderManageS?constructOrderId=" + constructOrderId);
            ConstructOrder constructOrder = DaoFactory.GetConstructOrderDaoImp().GetAConstructOrderByConstructOrderId(constructOrderId);
            if (constructOrder != null && constructOrder.getOrder_Company_id().equals(loginManager.getManager_Company_id())) {
                constructOrders.add(constructOrder);
            }
        } else {

            if (companyId != null && !"".equals(companyId)) {
                pagination.setUrl("ConConOrderManageS?");
                constructOrders = DaoFactory.GetConstructOrderDaoImp().GetConstructOrderByCompanyId(companyId);
            } else {
                pagination.setUrl("ConConOrderManageS?");
                constructOrders = DaoFactory.GetConstructOrderDaoImp().GetConstructOrderByCompanyId(loginManager.getManager_Company_id());

            }

        }
        request.setAttribute("constructOrders", constructOrders);
        request.setAttribute("pagination", pagination);

        request.getRequestDispatcher("/admin/ConConOrderManage.jsp").forward(request, response);

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
