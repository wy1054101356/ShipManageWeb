/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.TransportOrderDao;
import com.ship.model.ConstructOrder;
import com.ship.model.TransportOrder;
import com.ship.util.DatabaseBean;
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
public class TransportOrderDaoImp implements TransportOrderDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<TransportOrder> GetAllTransportOrder() {
        List<TransportOrder> transportOrders = new ArrayList<TransportOrder>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Transport_order");
            rs = psmt.executeQuery();
            while (rs.next()) {
                TransportOrder transportOrder = new TransportOrder();

                transportOrder.setTransport_order_id(rs.getString("Transport_order_id"));
                transportOrder.setShip_id(rs.getString("Ship_id"));
                transportOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                transportOrder.setTransport_Company_id(rs.getString("Transport_Company_id"));
                transportOrder.setTransport_Order_date(rs.getString("Transport_Order_date"));
                transportOrder.setTransport_start(rs.getString("Transport_start"));
                transportOrder.setTransport_order_state(rs.getString("Transport_order_state"));
                transportOrder.setTransport_order_deal(rs.getString("Transport_order_deal"));
                transportOrder.setTransport_value(rs.getFloat("Transport_value"));

                transportOrders.add(transportOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return transportOrders;
    }

    @Override
    public List<TransportOrder> GetTransportOrderByCompanyId(String companyId) {
        List<TransportOrder> transportOrders = new ArrayList<TransportOrder>();
        try {
            conn = DatabaseBean.getConnection();
            cs = conn.prepareCall("{call get_trans_order_by_compId_proc(?,?)}");
            cs.setString(1, companyId);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                TransportOrder transportOrder = new TransportOrder();

                transportOrder.setTransport_order_id(rs.getString("Transport_order_id"));
                transportOrder.setShip_id(rs.getString("Ship_id"));
                transportOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                transportOrder.setTransport_Company_id(rs.getString("Transport_Company_id"));
                transportOrder.setTransport_Order_date(rs.getString("Transport_Order_date"));
                transportOrder.setTransport_start(rs.getString("Transport_start"));
                transportOrder.setTransport_order_state(rs.getString("Transport_order_state"));
                transportOrder.setTransport_order_deal(rs.getString("Transport_order_deal"));
                transportOrder.setTransport_value(rs.getFloat("Transport_value"));

                transportOrders.add(transportOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseBean.close(rs, cs, conn);
        }
        return transportOrders;
    }

    @Override
    public TransportOrder GetATransportOrderByTransportOrderId(String transportOrderId) {
        TransportOrder transportOrder = new TransportOrder();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Transport_order where Transport_order_id=?");
            psmt.setString(1, transportOrderId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                transportOrder.setTransport_order_id(rs.getString("Transport_order_id"));
                transportOrder.setShip_id(rs.getString("Ship_id"));
                transportOrder.setOrder_Company_id(rs.getString("Order_Company_id"));
                transportOrder.setTransport_Company_id(rs.getString("Transport_Company_id"));
                transportOrder.setTransport_Order_date(rs.getString("Transport_Order_date"));
                transportOrder.setTransport_start(rs.getString("Transport_start"));
                transportOrder.setTransport_order_state(rs.getString("Transport_order_state"));
                transportOrder.setTransport_order_deal(rs.getString("Transport_order_deal"));
                transportOrder.setTransport_value(rs.getFloat("Transport_value"));

                return transportOrder;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean AddTransportOrder(TransportOrder transportOrder) {
        try {
            String sql = "insert into Transport_order values(?,?,?,?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, transportOrder.getTransport_order_id());
            psmt.setString(2, transportOrder.getShip_id());
            psmt.setString(3, transportOrder.getOrder_Company_id());
            psmt.setString(4, transportOrder.getTransport_Company_id());
            psmt.setString(5, transportOrder.getTransport_Order_date());
            psmt.setString(6, transportOrder.getTransport_start());
            psmt.setString(7, transportOrder.getTransport_order_state());
            psmt.setString(8, transportOrder.getTransport_order_deal());
            psmt.setFloat(9, transportOrder.getTransport_value());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateTransportOrder(TransportOrder transportOrder) {
        try {
            String sql = "update Transport_order set Ship_id = ? , Transport_Company_id = ?,"
                    + "Transport_start = ? ,Transport_order_state = ?,"
                    + "Transport_order_deal = ? ,Transport_value = ?  where Transport_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, transportOrder.getShip_id());
            psmt.setString(2, transportOrder.getTransport_Company_id());
            psmt.setString(3, transportOrder.getTransport_start());
            psmt.setString(4, transportOrder.getTransport_order_state());
            psmt.setString(5, transportOrder.getTransport_order_deal());
            psmt.setFloat(6, transportOrder.getTransport_value());
            psmt.setString(7, transportOrder.getTransport_order_id());

            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean ChangeState(String transportOrderId) {
        try {
            String sql = "update Transport_order set Transport_order_state = ? where Transport_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "已审理");
            psmt.setString(2, transportOrderId);

            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteTransportOrder(String transportOrderId) {
        try {
            String sql = "delete from TRANSPORT_ORDER where Transport_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, transportOrderId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean ChangeDeal(String transportOrderId) {
        try {
            String sql = "update Transport_order set Transport_order_deal = ? where Transport_order_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "交易成功");
            psmt.setString(2, transportOrderId);

            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
