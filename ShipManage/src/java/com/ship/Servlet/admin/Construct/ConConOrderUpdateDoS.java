/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Construct;

import com.ship.model.ConstructOrder;
import com.ship.model.Ship;
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
 * @author 思之声
 */
@WebServlet(name = "ConConOrderUpdateDoS", urlPatterns = {"/Admin/ConConOrderUpdateDoS"})
public class ConConOrderUpdateDoS extends HttpServlet {

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

        ConstructOrder constructOrder = null;

        String constructOrderId = request.getParameter("constructOrderId");
        String constructCompanyId = request.getParameter("constructCompanyId");
        String orderCompanyId = request.getParameter("orderCompanyId");
        String constructOrderDate = request.getParameter("constructOrderDate");
        String shipType = request.getParameter("shipType");
        float constructValue = Float.valueOf(request.getParameter("constructValue"));
        String constructOrderState = request.getParameter("constructOrderState");

        if (constructOrderState != null && !constructOrderState.equals("")) {
            constructOrder = new ConstructOrder(constructOrderId, constructCompanyId, orderCompanyId, constructOrderDate, shipType, constructValue, constructOrderState);
        } else {
            constructOrder = new ConstructOrder(constructOrderId, constructCompanyId, orderCompanyId, constructOrderDate, shipType, constructValue, "false");
        }

        DaoFactory.GetConstructOrderDaoImp().UpdateConstructOrder(constructOrder);

        response.sendRedirect(request.getContextPath() + "/Admin/ConConOrderManageS");
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