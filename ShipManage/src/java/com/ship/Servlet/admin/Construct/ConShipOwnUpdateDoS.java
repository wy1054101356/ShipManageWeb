/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Construct;

import com.ship.model.*;
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
@WebServlet(name = "ConShipOwnUpdateDoS", urlPatterns = {"/Admin/ConShipOwnUpdateDoS"})
public class ConShipOwnUpdateDoS extends HttpServlet {

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

        Ship ship = null;

        String shipId = request.getParameter("shipId");
        String shipName = request.getParameter("shipName");
        String shipType = request.getParameter("shipType");
        float shipLoad = Float.valueOf(request.getParameter("shipLoad"));
        float shipWeight = Float.valueOf(request.getParameter("shipWeight"));
        String constructCompanyId = request.getParameter("constructCompanyId");
        String ownerCompanyId = request.getParameter("ownerCompanyId");
        String shipConstructDate = request.getParameter("shipConstructDate");
        float shipValue = Float.valueOf(request.getParameter("shipValue"));

        ship = new Ship(shipId, shipName, shipType, shipLoad, shipWeight, constructCompanyId, ownerCompanyId, shipConstructDate, shipValue, DaoFactory.GetShipDaoImp().GetAShip(shipId).getShip_state());
        DaoFactory.GetShipDaoImp().UpdateShip(ship);
        response.sendRedirect(request.getContextPath() + "/Admin/ConShipOwnManageS");
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
