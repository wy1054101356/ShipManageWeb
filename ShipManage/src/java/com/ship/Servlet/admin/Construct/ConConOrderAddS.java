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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
@WebServlet(name = "ConConOrderAddS", urlPatterns = {"/Admin/ConConOrderAddS"})
public class ConConOrderAddS extends HttpServlet {

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

        String shipId = request.getParameter("shipId");
        String constructOrderId = "";
        String constructCompanyId = request.getParameter("constructCompanyId");

        List<Company> companys = DaoFactory.GetCompanyDaoImp().GetCompanyByCompanyType("建造企业");

        //定义时间
        Date date = new Date(System.currentTimeMillis());
        String pattern = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String constructOrderDate = sdf.format(date);

        //获取订单ID 随机的 根据 日期 + 随机数
        constructOrderId = "JZ" + constructOrderDate.replace("-", "");

//        int randomNumber = (int) (Math.random() * 100);
//        if (randomNumber < 10) {
//            constructOrderId += "0";
//            constructOrderId += String.valueOf(randomNumber);
//        } else {
//            constructOrderId += String.valueOf(randomNumber);
//        }
        String randomStr = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 4; i++) {
            constructOrderId += randomStr.charAt((int) (Math.random() * randomStr.length()));
        }

        if (constructOrderId != null && !constructOrderId.equals("")) {
            request.setAttribute("constructOrderId", constructOrderId);
        }

        if (constructCompanyId != null && !constructCompanyId.equals("")) {
            request.setAttribute("constructCompanyId", constructCompanyId);
        } else {
            request.setAttribute("constructCompanyId", "000");

        }

        if (shipId != null && !shipId.equals("")) {
            Ship ship = DaoFactory.GetShipDaoImp().GetAShip(shipId);
            request.setAttribute("shipType", ship.getShip_type());
            request.setAttribute("constructCompanyId", ship.getConstruct_Company_id());
        }

        if (constructOrderDate != null && !constructOrderDate.equals("")) {
            request.setAttribute("constructOrderDate", constructOrderDate);
        }

        request.setAttribute("companys", companys);
        request.setAttribute("orderCompanyId", loginManager.getManager_Company_id());
        request.getRequestDispatcher("/admin/ConConOrderAdd.jsp").forward(request, response);
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
