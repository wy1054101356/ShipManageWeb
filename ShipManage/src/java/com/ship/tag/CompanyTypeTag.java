/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.tag;

import com.ship.model.*;
import com.ship.util.FileIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author wy105
 */
public class CompanyTypeTag extends SimpleTagSupport {

    private int gid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        StringBuilder str = new StringBuilder();
        List<CompanyType> companyTypes = new ArrayList<>();
        companyTypes.add(new CompanyType(1, "供应企业"));
        companyTypes.add(new CompanyType(2, "运输企业"));
        companyTypes.add(new CompanyType(3, "建造企业"));
        str.append("<select class=\"input\" name=\"gid\" id=\"gid\" data-validate=\"required:请选择企业类型\">");
        str.append("<option value=\"\">请选择企业类型</option>");
        for (CompanyType companyType : companyTypes) {
            if (companyType.getMid() == gid) {
                str.append("<option value='").append(companyType.getMid()).append("' selected>").append(companyType.getName()).append("</option>");
            } else {
                str.append("<option value='").append(companyType.getMid()).append("'>").append(companyType.getName()).append("</option>");
            }
        }
        str.append("</select>");
        out.print(str.toString());
    }

}
