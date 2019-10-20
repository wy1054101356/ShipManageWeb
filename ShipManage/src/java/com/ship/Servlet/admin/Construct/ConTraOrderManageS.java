/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Construct;

import com.ship.model.Manager;
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
@WebServlet(name = "ConTraOrderManageS", urlPatterns = {"/Admin/ConTraOrderManageS"})
public class ConTraOrderManageS extends HttpServlet {

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

        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        List<TransportOrder> transportOrders = new ArrayList<>();
        if (transportOrderId != null && !"".equals(transportOrderId)) {

            //如果输入的运输订单 属于本企业 则显示输出 否则过滤掉
            if (DaoFactory.GetTransportOrderDaoImp().GetATransportOrderByTransportOrderId(transportOrderId) != null
                    && DaoFactory.GetTransportOrderDaoImp().GetATransportOrderByTransportOrderId(transportOrderId)
                            .getTransport_Company_id().equals(loginManager.getManager_Company_id())) {
                pagination.setUrl("ConTraOrderManageS?companyId=" + transportOrderId);
                TransportOrder transportOrder = DaoFactory.GetTransportOrderDaoImp().GetATransportOrderByTransportOrderId(transportOrderId);
                if (transportOrder != null) {
                    transportOrders.add(transportOrder);
                }
            }

        } else {
            pagination.setUrl("ConTraOrderManageS?");
            transportOrders = DaoFactory.GetTransportOrderDaoImp().GetTransportOrderByCompanyId(loginManager.getManager_Company_id());

        }
        request.setAttribute("transportOrders", transportOrders);
        request.setAttribute("pagination", pagination);

        request.getRequestDispatcher("/admin/ConTraOrderManage.jsp").forward(request, response);

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
