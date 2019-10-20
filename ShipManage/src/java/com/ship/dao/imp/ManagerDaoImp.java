/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.ManagerDao;
import com.ship.model.Company;
import com.ship.model.Manager;
import com.ship.model.Ship;
import com.ship.util.DaoFactory;
import com.ship.util.DatabaseBean;
import com.ship.util.FileIO;
import com.ship.util.MD5;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wy105
 */
public class ManagerDaoImp implements ManagerDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public Manager Login(String managerId, String managerPassword) {
        Manager manager = new Manager();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from manager where manager.manager_id = ? and manager.manager_password = ?");
            psmt.setString(1, managerId);
            psmt.setString(2, MD5.EncryptionStr(managerPassword, MD5.MD5));
            rs = psmt.executeQuery();
            if (rs.next()) {
                manager.setManager_id(managerId);
                manager.setManager_password(managerPassword);
                manager.setManager_Company_id(rs.getString("Manager_Company_id"));
                manager.setManager_admin_type(rs.getString("Manager_admin_type"));
                manager.setManager_state(rs.getString("Manager_state"));
                if (!manager.getManager_Company_id().equals("null")) {
                    manager.setCompany(DaoFactory.GetCompanyDaoImp().GetACompany(manager.getManager_Company_id()));
                }
                return manager;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public List<Manager> GetAllManager() {
        List<Manager> managers = new ArrayList<Manager>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from manager");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Manager manager = new Manager();

                manager.setManager_id(rs.getString("Manager_id"));
                manager.setManager_password(rs.getString("Manager_password"));
                manager.setManager_Company_id(rs.getString("Manager_Company_id"));
                manager.setManager_admin_type(rs.getString("Manager_admin_type"));
                manager.setManager_state(rs.getString("Manager_state"));
                manager.setCompany(GetCompanyByCompanyId(rs.getString("Manager_Company_id")));

                managers.add(manager);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return managers;
    }

    @Override
    public Company GetCompanyByCompanyId(String companyId) {
        Company company = DaoFactory.GetCompanyDaoImp().GetACompany(companyId);
        if (company != null) {
            return company;
        } else {
            return null;
        }
    }

    @Override
    public List<Manager> GetManagerByCompanyType(String companyType) {
        List<Manager> allManagers = GetAllManager();
        List<Manager> managers = new ArrayList<>();
        for (int i = 0; i < allManagers.size(); i++) {
            if (allManagers.get(i).getCompany().getCompany_type().equals(companyType)) {
                managers.add(allManagers.get(i));
            }
        }
        return managers;
    }

    @Override
    public Manager GetAManager(String managerId) {
        Manager manager = new Manager();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from manager where Manager_id = ?");
            psmt.setString(1, managerId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                manager.setManager_id(rs.getString("Manager_id"));
                manager.setManager_password(rs.getString("Manager_password"));
                manager.setManager_Company_id(rs.getString("Manager_Company_id"));
                manager.setManager_admin_type(rs.getString("Manager_admin_type"));
                manager.setManager_state(rs.getString("Manager_state"));
                return manager;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean AddManager(Manager manager) {
        try {
            String sql = "insert into manager values(?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, manager.getManager_id());
            psmt.setString(2, MD5.EncryptionStr(manager.getManager_password(), MD5.MD5));
            psmt.setString(3, manager.getManager_Company_id());
            psmt.setString(4, manager.getManager_admin_type());
            psmt.setString(5, manager.getManager_state());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteManager(String managerId) {
        try {
            String sql = "delete from manager where Manager_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, managerId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateManager(Manager manager) {
        try {
            String sql
                    = "update Manager set Manager_password = ? , Manager_Company_id = ? ,"
                    + "Manager_admin_type = ? , Manager_state = ? where Manager_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, manager.getManager_password());
            psmt.setString(2, manager.getManager_Company_id());
            psmt.setString(3, manager.getManager_admin_type());
            psmt.setString(4, manager.getManager_state());
            psmt.setString(5, manager.getManager_id());

            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public List<Manager> GetManagerByCompanyId(String companyId) {
        List<Manager> allManagers = GetAllManager();
        List<Manager> managers = new ArrayList<>();
        for (int i = 0; i < allManagers.size(); i++) {
            if (allManagers.get(i).getManager_Company_id().equals(companyId)) {
                managers.add(allManagers.get(i));
            }
        }
        return managers;
    }

}
