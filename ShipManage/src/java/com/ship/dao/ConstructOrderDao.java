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
public interface ConstructOrderDao {

    public List<ConstructOrder> GetAllConstructOrder();

    public List<ConstructOrder> GetConstructOrderByCompanyId(String companyId);

    public ConstructOrder GetAConstructOrderByConstructOrderId(String constructOrderId);

    public boolean AddConstructOrder(ConstructOrder constructOrder);

    public boolean UpdateConstructOrder(ConstructOrder constructOrder);

    public boolean ChangeState(String constructOrderId);

    public boolean DeleteConstructOrder(String constructOrderId);

    public boolean FindConstructOrder(String constructOrderId);
}
