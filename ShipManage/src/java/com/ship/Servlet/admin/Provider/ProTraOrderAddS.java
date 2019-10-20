/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin.Provider;

import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author wy105
 */
@WebServlet(name = "ProTraOrderAddS", urlPatterns = {"/Admin/ProTraOrderAddS"})
public class ProTraOrderAddS extends HttpServlet {

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
        String transportCompanyId = request.getParameter("transportCompanyId");
        List<Company> companys = DaoFactory.GetCompanyDaoImp().GetCompanyByCompanyType("运输企业");

        //定义时间
        Date date = new Date(System.currentTimeMillis());
        String pattern = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String transportOrderDate = sdf.format(date);

        //获取订单ID 随机的 根据 日期 + 随机数
        String transportOrderId = "YS" + transportOrderDate.replace("-", "");
        String randomStr = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 4; i++) {
            transportOrderId += randomStr.charAt((int) (Math.random() * randomStr.length()));
        }

        if (transportOrderId != null && !transportOrderId.equals("")) {
            request.setAttribute("transportOrderId", transportOrderId);
        }

        if (shipId != null && !shipId.equals("")) {
            request.setAttribute("shipId", shipId);
        } else {
            request.setAttribute("shipId", "000");
        }

        if (transportCompanyId != null && !transportCompanyId.equals("")) {
            List<Ship> ships = DaoFactory.GetShipDaoImp().GetShipByCompanyId(transportCompanyId);
            request.setAttribute("transportCompanyId", transportCompanyId);
            request.setAttribute("ships", ships);
        } else {
            List<Ship> ships = DaoFactory.GetShipDaoImp().GetShipByCompanyId(companys.get(0).getCompany_id());
            request.setAttribute("transportCompanyId", "000"); // 000 代表第一次进
            request.setAttribute("ships", ships);
        }

        if (transportOrderDate != null && !transportOrderDate.equals("")) {
            request.setAttribute("transportOrderDate", transportOrderDate);
        }

        request.setAttribute("companys", companys);
        request.setAttribute("orderCompanyId", loginManager.getManager_Company_id());
        request.getRequestDispatcher("/admin/ProTraOrderAdd.jsp").forward(request, response);
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
