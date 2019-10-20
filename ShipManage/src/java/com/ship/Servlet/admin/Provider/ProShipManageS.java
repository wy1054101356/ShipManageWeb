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
import java.util.Queue;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;

/**
 *
 * @author 思之声
 */
@WebServlet(name = "ProShipManageS", urlPatterns = {"/Admin/ProShipManageS"})
public class ProShipManageS extends HttpServlet {

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
        Pagination pagination = new Pagination();
        String pageNo = request.getParameter("pageNo");
        String companyId = request.getParameter("companyId");

        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        List<Ship> ships = null;
        if (companyId != null && !"".equals(companyId)) {
            pagination.setUrl("ProShipManageS?companyId=" + companyId);
            ships = DaoFactory.GetShipDaoImp().GetShipByCompanyId(companyId);
        } else {
            pagination.setUrl("ProShipManageS?");
            ships = DaoFactory.GetShipDaoImp().GetAllShip();
        }

        List<TransportOrder> transportOrders = DaoFactory.GetTransportOrderDaoImp().GetAllTransportOrder();
        List<String> shipStates = new ArrayList<String>();

        boolean isTransport = false;
        for (int i = 0; i < ships.size(); i++) {
            isTransport = false;
            for (int j = 0; j < transportOrders.size(); j++) {
                if (ships.get(i).getShip_id().equals(transportOrders.get(j).getShip_id())) {
                    if (transportOrders.get(j).getTransport_order_state().equals("已审理") && transportOrders.get(j).getTransport_order_deal().equals("等待交易")) {
                        isTransport = true;
//                        FileIO.writeFile(String.valueOf(isTransport));
                    }
                }
            }
            if (isTransport) {
                shipStates.add("true");
            } else {
                shipStates.add("false");
            }

        }

        request.setAttribute("ships", ships);
        request.setAttribute("shipStates", shipStates);
        request.setAttribute("pagination", pagination);

        request.getRequestDispatcher("/admin/ProShipManage.jsp").forward(request, response);

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
