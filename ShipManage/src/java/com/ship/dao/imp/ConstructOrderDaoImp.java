/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.ConstructOrderDao;
import com.ship.model.ConstructOrder;
import com.ship.model.Ship;
import com.ship.util.DatabaseBean;
import com.ship.util.FileIO;
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
public class ConstructOrderDaoImp implements ConstructOrderDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<ConstructOrder> GetAllConstructOrder() {
        List<ConstructOrder> constructOrders = new ArrayList<ConstructOrder>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Construct_order");
            rs = psmt.executeQuery();
            while (rs.next()) {
                ConstructOrder constructOrder = new ConstructOrder();

                constructOrder.setConstruct_order_id(rs.getString("Construct_order_id"));
                constructOrder.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                constructOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                constructOrder.setConstruct_order_date(rs.getString("Construct_order_date"));
                constructOrder.setShip_type(rs.getString("Ship_type"));
                constructOrder.setConstruct_value(rs.getFloat("Construct_value"));
                constructOrder.setConstruct_order_state(rs.getString("Construct_order_state"));

                constructOrders.add(constructOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return constructOrders;
    }

    @Override
    public List<ConstructOrder> GetConstructOrderByCompanyId(String companyId) {
        List<ConstructOrder> constructOrders = new ArrayList<ConstructOrder>();
        try {
            conn = DatabaseBean.getConnection();
            cs = conn.prepareCall("{call get_const_order_by_compId_proc(?,?)}");
            cs.setString(1, companyId);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                ConstructOrder constructOrder = new ConstructOrder();

                constructOrder.setConstruct_order_id(rs.getString("Construct_order_id"));
                constructOrder.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                constructOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                constructOrder.setConstruct_order_date(rs.getString("Construct_order_date"));
                constructOrder.setShip_type(rs.getString("Ship_type"));
                constructOrder.setConstruct_value(rs.getFloat("Construct_value"));
                constructOrder.setConstruct_order_state(rs.getString("Construct_order_state"));

                constructOrders.add(constructOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseBean.close(rs, cs, conn);
        }
        return constructOrders;
    }

    @Override
    public ConstructOrder GetAConstructOrderByConstructOrderId(String constructOrderId) {
        ConstructOrder constructOrder = new ConstructOrder();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Construct_order where Construct_order_id=?");
            psmt.setString(1, constructOrderId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                constructOrder.setConstruct_order_id(rs.getString("Construct_order_id"));
                constructOrder.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                constructOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                constructOrder.setConstruct_order_date(rs.getString("Construct_order_date"));
                constructOrder.setShip_type(rs.getString("Ship_type"));
                constructOrder.setConstruct_value(rs.getFloat("Construct_value"));
                constructOrder.setConstruct_order_state(rs.getString("Construct_order_state"));
                return constructOrder;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean AddConstructOrder(ConstructOrder constructOrder) {
        try {
            String sql = "insert into Construct_order values(?,?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, constructOrder.getConstruct_order_id());
            psmt.setString(2, constructOrder.getConstruct_Company_id());
            psmt.setString(3, constructOrder.getOrder_Company_id());
            psmt.setString(4, constructOrder.getConstruct_order_date());
            psmt.setString(5, constructOrder.getShip_type());
            psmt.setFloat(6, constructOrder.getConstruct_value());
            psmt.setString(7, constructOrder.getConstruct_order_state());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    //只允许修改部分信息
    @Override
    public boolean UpdateConstructOrder(ConstructOrder constructOrder) {
        try {
            String sql = "update Construct_order set Construct_Company_id = ? , Ship_type = ?,"
                    + "Construct_value = ? , Construct_order_state = ? where Construct_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, constructOrder.getConstruct_Company_id());
            psmt.setString(2, constructOrder.getShip_type());
            psmt.setFloat(3, constructOrder.getConstruct_value());
            psmt.setString(4, constructOrder.getConstruct_order_state());
            psmt.setString(5, constructOrder.getConstruct_order_id());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteConstructOrder(String constructOrderId) {
        try {
            String sql = "delete from CONSTRUCT_ORDER where Construct_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, constructOrderId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean FindConstructOrder(String constructOrderId) {
        try {
            String sql = "select * from CONSTRUCT_ORDER where Construct_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, constructOrderId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean ChangeState(String constructOrderId) {
        try {
            String sql = "update Construct_order set Construct_order_state = ? where Construct_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "true");
            psmt.setString(2, constructOrderId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
