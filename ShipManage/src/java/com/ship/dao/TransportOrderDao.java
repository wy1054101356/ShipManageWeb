/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao;

import com.ship.model.*;
import java.util.List;

/**
 *
 * @author wy105
 */
public interface TransportOrderDao {

    public List<TransportOrder> GetAllTransportOrder();

    public List<TransportOrder> GetTransportOrderByCompanyId(String companyId);

    public TransportOrder GetATransportOrderByTransportOrderId(String transportOrderId);

    public boolean AddTransportOrder(TransportOrder transportOrder);

    public boolean UpdateTransportOrder(TransportOrder transportOrder);

    public boolean ChangeState(String transportOrderId);

    public boolean ChangeDeal(String transportOrderId);

    public boolean DeleteTransportOrder(String transportOrderId);
}
