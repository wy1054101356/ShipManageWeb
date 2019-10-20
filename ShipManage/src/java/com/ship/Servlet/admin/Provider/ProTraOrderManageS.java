/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Provider;

import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;
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
@WebServlet(name = "ProTraOrderManageS", urlPatterns = {"/Admin/ProTraOrderManageS"})
public class ProTraOrderManageS extends HttpServlet {

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
        String transportOrderId = request.getParameter("transportOrderId");
        String shipId = request.getParameter("shipId");

        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        List<TransportOrder> transportOrders = new ArrayList<>();
        if (transportOrderId != null && !"".equals(transportOrderId)) {
            pagination.setUrl("ProTraOrderManageS?companyId=" + transportOrderId);
            TransportOrder transportOrder = DaoFactory.GetTransportOrderDaoImp().GetATransportOrderByTransportOrderId(transportOrderId);

            transportOrders.add(transportOrder);
        } else {
            if (shipId != null && !"".equals(shipId)) {
                List<TransportOrder> allTransportOrders = DaoFactory.GetTransportOrderDaoImp().GetAllTransportOrder();
                for (int i = 0; i < allTransportOrders.size(); i++) {
                    if (allTransportOrders.get(i).getShip_id().equals(shipId)) {
                        transportOrders.add(allTransportOrders.get(i));
                    }
                }
                pagination.setUrl("ProTraOrderManageS?");
            } else {
                transportOrders = DaoFactory.GetTransportOrderDaoImp().GetTransportOrderByCompanyId(loginManager.getManager_Company_id());
                pagination.setUrl("ProTraOrderManageS?");
            }
        }
        request.setAttribute("transportOrders", transportOrders);
        request.setAttribute("pagination", pagination);

        request.getRequestDispatcher("/admin/ProTraOrderManage.jsp").forward(request, response);

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
