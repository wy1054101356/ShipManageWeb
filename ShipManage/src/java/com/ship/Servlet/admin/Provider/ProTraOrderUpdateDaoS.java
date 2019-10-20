/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Provider;

import com.ship.model.TransportOrder;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;
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
@WebServlet(name = "ProTraOrderUpdateDaoS", urlPatterns = {"/Admin/ProTraOrderUpdateDaoS"})
public class ProTraOrderUpdateDaoS extends HttpServlet {

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
        TransportOrder transportOrderUpdate = null;

        String transportOrderId = request.getParameter("transportOrderId");
        String shipId = request.getParameter("shipId");
        String orderCompanyId = request.getParameter("orderCompanyId");
        String transportCompanyId = request.getParameter("transportCompanyId");
        String transportOrderDate = request.getParameter("transportOrderDate");
        String transportStart = request.getParameter("transportStart");
        String transportOrderDeal = request.getParameter("transportOrderDeal");
        Float transportValue = Float.valueOf(request.getParameter("transportValue"));

        if (transportOrderDeal != null && !transportOrderDeal.equals("")) {
            transportOrderUpdate = new TransportOrder(transportOrderId, shipId, orderCompanyId, transportCompanyId, transportOrderDate, transportStart, "已审理", transportOrderDeal, transportValue);

        } else {
            transportOrderUpdate = new TransportOrder(transportOrderId, shipId, orderCompanyId, transportCompanyId, transportOrderDate, transportStart, "未审理", "等待交易", transportValue);
        }

        DaoFactory.GetTransportOrderDaoImp().UpdateTransportOrder(transportOrderUpdate);
        response.sendRedirect(request.getContextPath() + "/Admin/ProTraOrderManageS");

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
