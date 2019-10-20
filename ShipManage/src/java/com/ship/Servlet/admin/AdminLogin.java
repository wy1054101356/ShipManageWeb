/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.Servlet.admin;

import com.ship.model.*;
import com.ship.util.DaoFactory;
import com.ship.util.FileIO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

/**
 *
 * @author wy105
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Manager manager = DaoFactory.GetManagerDaoImp().Login(username, password);
        HttpSession session = request.getSession(true);
        if (manager != null) {
            if (manager.getManager_state().equals("通过")) {
                if (manager.getManager_Company_id().equals("null")) {

                    session.setAttribute("manager", manager);
                    response.sendRedirect(request.getContextPath() + "/admin/CompanyAdd.jsp");

                } else {
                    if (manager.getCompany().getCompany_state().equals("false")) {
                        session.setAttribute("message", "您备案的公司正在审核,请耐心等待!");
                        response.sendRedirect(request.getContextPath() + "/Login.jsp");
                    } else {
                        List<Company> companys = DaoFactory.GetCompanyDaoImp().GetAllCompany();
                        List<Ship> ships = DaoFactory.GetShipDaoImp().GetAllShip();
                        int proCompanyNum = 0, traCompanyNum = 0, conCompanyNum = 0;
                        for (int i = 0; i < companys.size(); i++) {
                            if (companys.get(i).getCompany_type().equals("供应企业")) {
                                proCompanyNum++;
                            }
                            if (companys.get(i).getCompany_type().equals("运输企业")) {
                                traCompanyNum++;
                            }
                            if (companys.get(i).getCompany_type().equals("建造企业")) {
                                conCompanyNum++;
                            }
                        }

                        session.setAttribute("shipsSize", ships.size());

                        session.setAttribute("proCompanyNum", proCompanyNum);
                        session.setAttribute("traCompanyNum", traCompanyNum);
                        session.setAttribute("conCompanyNum", conCompanyNum);
                        session.setAttribute("manager", manager);

                        String companyTypeStr = DaoFactory.GetManagerDaoImp().GetCompanyByCompanyId(manager.getManager_Company_id()).getCompany_type();
                        session.setAttribute("managerTypeStr", companyTypeStr);
                        if (companyTypeStr.equals("供应企业")) {
                            response.sendRedirect(request.getContextPath() + "/admin/ProIndex.jsp");
                        } else if (companyTypeStr.equals("运输企业")) {
                            response.sendRedirect(request.getContextPath() + "/admin/ConIndex.jsp");
                        } else if (companyTypeStr.equals("建造企业")) {
                            response.sendRedirect(request.getContextPath() + "/admin/TraIndex.jsp");
                        } else if (companyTypeStr.equals("网站")) {
                            response.sendRedirect(request.getContextPath() + "/admin/SystemIndex.jsp");
                        } else if (companyTypeStr.equals("政府")) {
                            response.sendRedirect(request.getContextPath() + "/admin/GovIndex.jsp");
                        }
                    }
                }

            } else {
                session.setAttribute("message", "本帐号已成功申请,请耐心等待审核!");
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            }

        } else {
            session.setAttribute("message", "用户名或密码错误，请重新输入！");
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        }

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
