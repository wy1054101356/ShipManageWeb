/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Transport;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;

/**
 *
 * @author 思之声
 */
@WebServlet(name = "TraOrderStateS", urlPatterns = {"/Admin/TraOrderStateS"})
public class TraOrderStateS extends HttpServlet {

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

        String constructOrderId = request.getParameter("constructOrderId");
        //当订单同意后需要创建一个船舶
        ConstructOrder constructOrderAdd = DaoFactory.GetConstructOrderDaoImp().GetAConstructOrderByConstructOrderId(constructOrderId);

        Ship shipAdd = new Ship();
        int shipId = DaoFactory.GetShipDaoImp().AddShipId("Ship");
        String shipIdStr = "S";

        if (shipId < 1000) {
            shipIdStr += "00";
            shipIdStr += String.valueOf(shipId);
        } else if (shipId < 10000) {
            shipIdStr += "0";
            shipIdStr += String.valueOf(shipId);
        } else if (shipId < 100000) {
            shipIdStr += String.valueOf(shipId);
        }

        FileIO.writeFile(constructOrderAdd.getShip_type());
        shipAdd.setShip_id(shipIdStr);
        shipAdd.setShip_name("默认船舶名字");
        shipAdd.setShip_load(0);
        shipAdd.setShip_type(constructOrderAdd.getShip_type());
        shipAdd.setShip_weight(0);
        shipAdd.setConstruct_Company_id(constructOrderAdd.getConstruct_Company_id());
        shipAdd.setOwner_Company_id(constructOrderAdd.getOrder_Company_id());
        shipAdd.setShip_construct_date(constructOrderAdd.getConstruct_order_date());
        shipAdd.setShip_value(constructOrderAdd.getConstruct_value());

        //改变建造订单状态
        DaoFactory.GetConstructOrderDaoImp().ChangeState(constructOrderId);
        //给相应的建造订单 插入一搜船舶
        DaoFactory.GetShipDaoImp().AddShip(shipAdd);

        response.sendRedirect(request.getContextPath() + "/Admin/TraOrderManageS");

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
