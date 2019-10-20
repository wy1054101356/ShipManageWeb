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
public interface ShipDao {

    public List<Ship> GetAllShip();

    public List<Ship> GetShipByCompanyId(String companyId);

    public Ship GetAShip(String shipId);

    public boolean AddShip(Ship ship);

    public boolean DeleteShip(String shipId);

    public boolean UpdateShip(Ship ship);

    public int AddShipId(String tableName);
}
