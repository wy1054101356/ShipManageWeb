/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Construct;

import com.ship.model.Manager;
import com.ship.model.Ship;
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
@WebServlet(name = "ConShipOwnManageS", urlPatterns = {"/Admin/ConShipOwnManageS"})
public class ConShipOwnManageS extends HttpServlet {

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
        String shipId = request.getParameter("shipId");

        int page = 1;
        if (pageNo != null) {
            page = Integer.parseInt(pageNo);
        }
        pagination.setPageNo(page);
        List<Ship> ships = new ArrayList<>();
        if (shipId != null && !"".equals(shipId)) {
            pagination.setUrl("ConShipOwnManageS?shipId=" + shipId);
            Ship ship = DaoFactory.GetShipDaoImp().GetAShip(shipId);
            if (ship != null && ship.getOwner_Company_id().equals(loginManager.getManager_Company_id())) {
                ships.add(ship);
            }
        } else {
            pagination.setUrl("ConShipOwnManageS?");
            ships = DaoFactory.GetShipDaoImp().GetShipByCompanyId(loginManager.getManager_Company_id());
        }

        request.setAttribute(
                "ships", ships);
        request.setAttribute(
                "pagination", pagination);

        request.getRequestDispatcher(
                "/admin/ConShipOwnManage.jsp").forward(request, response);

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
