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
public interface ManagerDao {

    public Manager Login(String managerId, String managerPassword);

    public Company GetCompanyByCompanyId(String companyId);

    public List<Manager> GetAllManager();

    public List<Manager> GetManagerByCompanyType(String companyType);

    public Manager GetAManager(String managerId);

    public boolean AddManager(Manager manager);

    public boolean DeleteManager(String managerId);

    public boolean UpdateManager(Manager manager);

    public List<Manager> GetManagerByCompanyId(String companyId);
}
