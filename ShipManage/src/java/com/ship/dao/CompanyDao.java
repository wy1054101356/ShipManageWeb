/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.dao;

import com.ship.model.*;
import com.ship.util.Pagination;
import java.util.List;

/**
 *
 * @author wy105
 */
public interface CompanyDao {

    public List<Company> GetAllCompany();

    public List<Company> GetAllCompany(Pagination pagination);

    public List<Company> getSomeByMid(int mid, Pagination pagination);

    public List<Company> GetCompanyByCompanyType(String companyType);

    public Company GetACompany(String companyId);

    public boolean AddCompany(Company company);

    public boolean DeleteCompany(String companyId);

    public boolean UpdateCompany(Company company);
}
