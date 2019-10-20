/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.util;

import com.ship.dao.imp.*;

/**
 * 工厂方法 返回每个实体的功能接口实现
 */
public class DaoFactory {

    public static CompanyDaoImp GetCompanyDaoImp() {
        return new CompanyDaoImp();
    }

    public static ShipDaoImp GetShipDaoImp() {
        return new ShipDaoImp();
    }

    public static ConstructOrderDaoImp GetConstructOrderDaoImp() {
        return new ConstructOrderDaoImp();
    }

    public static TransportOrderDaoImp GetTransportOrderDaoImp() {
        return new TransportOrderDaoImp();
    }

    public static ManagerDaoImp GetManagerDaoImp() {
        return new ManagerDaoImp();
    }

    public static MessageDaoImp GetMessageDaoImp() {
        return new MessageDaoImp();
    }
}
