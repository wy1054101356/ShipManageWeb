/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao.imp;

import com.ship.dao.ShipDao;
import com.ship.model.Ship;
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
public class ShipDaoImp implements ShipDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<Ship> GetAllShip() {
        List<Ship> ships = new ArrayList<Ship>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Ship");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Ship ship = new Ship();
                ship.setShip_id(rs.getString("Ship_id"));
                ship.setShip_name(rs.getString("Ship_name"));
                ship.setShip_load(rs.getFloat("Ship_load"));
                ship.setShip_weight(rs.getFloat("Ship_weight"));
                ship.setShip_type(rs.getString("Ship_type"));
                ship.setShip_value(rs.getFloat("Ship_value"));
                ship.setShip_construct_date(rs.getString("Ship_construct_date"));
                ship.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                ship.setOwner_Company_id(rs.getString("Owner_Company_id"));
                ship.setShip_state(rs.getString("Ship_state"));
                ships.add(ship);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return ships;
    }

    @Override
    public List<Ship> GetShipByCompanyId(String companyId) {

        List<Ship> ships = new ArrayList<Ship>();
        try {
            conn = DatabaseBean.getConnection();
            cs = conn.prepareCall("{call get_ship_by_company_id_proc(?,?)}");
            cs.setString(1, companyId);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                Ship ship = new Ship();
                ship.setShip_id(rs.getString("Ship_id"));
                ship.setShip_name(rs.getString("Ship_name"));
                ship.setShip_load(rs.getFloat("Ship_load"));
                ship.setShip_weight(rs.getFloat("Ship_weight"));
                ship.setShip_type(rs.getString("Ship_type"));
                ship.setShip_value(rs.getFloat("Ship_value"));
                ship.setShip_construct_date(rs.getString("Ship_construct_date"));
                ship.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                ship.setOwner_Company_id(rs.getString("Owner_Company_id"));
                ship.setShip_state(rs.getString("Ship_state"));
                ships.add(ship);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseBean.close(rs, cs, conn);
        }
        return ships;

    }

    @Override
    public Ship GetAShip(String shipId) {
        Ship ship = new Ship();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from Ship where Ship.Ship_id=?");
            psmt.setString(1, shipId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                ship.setShip_id(rs.getString("Ship_id"));
                ship.setShip_name(rs.getString("Ship_name"));
                ship.setShip_load(rs.getFloat("Ship_load"));
                ship.setShip_weight(rs.getFloat("Ship_weight"));
                ship.setShip_type(rs.getString("Ship_type"));
                ship.setShip_value(rs.getFloat("Ship_value"));
                ship.setShip_construct_date(rs.getString("Ship_construct_date"));
                ship.setConstruct_Company_id(rs.getString("Construct_Company_id"));
                ship.setOwner_Company_id(rs.getString("Owner_Company_id"));
                ship.setShip_state(rs.getString("Ship_state"));
                return ship;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }

        return null;
    }

    @Override
    public boolean AddShip(Ship ship) {
        try {
            String sql = "insert into ship values(?,?,?,?,?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, ship.getShip_id());
            psmt.setString(2, ship.getShip_name());
            psmt.setString(3, ship.getShip_type());
            psmt.setFloat(4, ship.getShip_load());
            psmt.setFloat(5, ship.getShip_weight());
            psmt.setString(6, ship.getConstruct_Company_id());
            psmt.setString(7, ship.getOwner_Company_id());
            psmt.setString(8, ship.getShip_construct_date());
            psmt.setFloat(9, ship.getShip_value());
            psmt.setString(10, ship.getShip_state());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteShip(String shipId) {
        try {
            String sql = "delete from Ship where Ship_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, shipId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    //只允许修改部分类型
    @Override
    public boolean UpdateShip(Ship ship) {
        try {
            String sql = "update Ship set Ship_name = ? , Ship_type = ?, Ship_load = ? ,"
                    + "Ship_weight = ? , Owner_Company_id = ?, Ship_value = ? ,"
                    + "Ship_state = ? where Ship_id = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, ship.getShip_name());
            psmt.setString(2, ship.getShip_type());
            psmt.setFloat(3, ship.getShip_load());
            psmt.setFloat(4, ship.getShip_weight());
            psmt.setString(5, ship.getOwner_Company_id());
            psmt.setFloat(6, ship.getShip_value());
            psmt.setString(7, ship.getShip_state());
            psmt.setString(8, ship.getShip_id());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public int AddShipId(String tableName) {
        try {
            String sql = "update tableNumbers set tableNumNumber = ? where tableNumName = ?";

            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select tableNumNumber from tableNumbers where tableNumName = ?");
            psmt.setString(1, tableName);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("tableNumNumber");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }

        return 0;
    }

}
