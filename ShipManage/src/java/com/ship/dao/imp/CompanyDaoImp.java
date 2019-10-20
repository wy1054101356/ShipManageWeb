/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.*;
import com.ship.model.Company;
import com.ship.util.DatabaseBean;
import com.ship.util.Pagination;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wy105
 */
public class CompanyDaoImp implements CompanyDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<Company> GetAllCompany() {
        List<Company> companys = new ArrayList<Company>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Company");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getString("Company_id"));
                company.setCompany_name(rs.getString("Company_name"));
                company.setCompany_tel(rs.getString("Company_tel"));
                company.setCompany_address(rs.getString("Company_address"));
                company.setCompany_type(rs.getString("Company_type"));
                company.setCompany_state(rs.getString("Company_state"));
                companys.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return companys;
    }

    @Override
    public List<Company> GetCompanyByCompanyType(String companyType) {
        List<Company> companys = new ArrayList<Company>();
        try {
            conn = DatabaseBean.getConnection();
            cs = conn.prepareCall("{call get_company_by_type_proc(?,?)}");
            cs.setString(1, companyType);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getString("Company_id"));
                company.setCompany_name(rs.getString("Company_name"));
                company.setCompany_tel(rs.getString("Company_tel"));
                company.setCompany_address(rs.getString("Company_address"));
                company.setCompany_type(rs.getString("Company_type"));
                company.setCompany_state(rs.getString("Company_state"));
                companys.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseBean.close(rs, cs, conn);
        }
        return companys;
    }

    @Override
    public Company GetACompany(String companyId) {
        Company company = new Company();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Company where Company_id = ?");
            psmt.setString(1, companyId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                company.setCompany_id(rs.getString("Company_id"));
                company.setCompany_name(rs.getString("Company_name"));
                company.setCompany_tel(rs.getString("Company_tel"));
                company.setCompany_address(rs.getString("Company_address"));
                company.setCompany_type(rs.getString("Company_type"));
                company.setCompany_state(rs.getString("Company_state"));
                return company;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean AddCompany(Company company) {
        try {
            String sql = "insert into Company values(?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, company.getCompany_id());
            psmt.setString(2, company.getCompany_name());
            psmt.setString(3, company.getCompany_tel());
            psmt.setString(4, company.getCompany_address());
            psmt.setString(5, company.getCompany_type());
            psmt.setString(6, company.getCompany_state());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteCompany(String companyId) {
        try {
            String sql = "delete from Company where Company_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, companyId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateCompany(Company company) {
        try {
            String sql = "update Company set Company_name = ? , Company_tel = ?,"
                    + "Company_address = ?, Company_type = ?,"
                    + "Company_state = ? where Company_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, company.getCompany_name());
            psmt.setString(2, company.getCompany_tel());
            psmt.setString(3, company.getCompany_address());
            psmt.setString(4, company.getCompany_type());
            psmt.setString(5, company.getCompany_state());
            psmt.setString(6, company.getCompany_id());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public List<Company> GetAllCompany(Pagination pagination) {
        List<Company> companys = new ArrayList<>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select count(*) as counts from company");
            rs = psmt.executeQuery();
            rs.next();
            //求总记录数
            pagination.setCountSize(rs.getInt("counts"));
            int start = (pagination.getPageNo() - 1) * pagination.getPageSize() + 1;//开始位置
            int end = pagination.getPageNo() * pagination.getPageSize();//结束位置
            psmt = conn.prepareStatement("SELECT * FROM(SELECT A.*, ROWNUM RN FROM "
                    + "(SELECT * FROM company) A WHERE ROWNUM <= ?) WHERE RN >= ?");
            psmt.setInt(1, end);
            psmt.setInt(2, start);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getString("Company_id"));
                company.setCompany_name(rs.getString("Company_name"));
                company.setCompany_tel(rs.getString("Company_tel"));
                company.setCompany_address(rs.getString("Company_address"));
                company.setCompany_type(rs.getString("Company_type"));
                company.setCompany_state(rs.getString("Company_state"));
                companys.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return companys;
    }

    @Override
    public List<Company> getSomeByMid(int mid, Pagination pagination) {
        List<Company> companys = new ArrayList<>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select count(*) as counts from company");
            rs = psmt.executeQuery();
            rs.next();
            //求总记录数
            pagination.setCountSize(rs.getInt("counts"));
            int start = (pagination.getPageNo() - 1) * pagination.getPageSize() + 1;//开始位置
            int end = pagination.getPageNo() * pagination.getPageSize();//结束位置
            psmt = conn.prepareStatement("SELECT * FROM(SELECT A.*, ROWNUM RN FROM "
                    + "(SELECT * FROM company) A WHERE ROWNUM <= ?) WHERE RN >= ?");
            psmt.setInt(1, end);
            psmt.setInt(2, start);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getString("Company_id"));
                company.setCompany_name(rs.getString("Company_name"));
                company.setCompany_tel(rs.getString("Company_tel"));
                company.setCompany_address(rs.getString("Company_address"));
                company.setCompany_type(rs.getString("Company_type"));
                company.setCompany_state(rs.getString("Company_state"));
                companys.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return companys;
    }

}
