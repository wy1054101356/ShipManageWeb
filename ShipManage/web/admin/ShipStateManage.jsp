<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="company_add_tag" uri="/WEB-INF/tlds/company_add_tag" %>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="renderer" content="webkit">
        <title>后台管理中心</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/pintuer.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/admin.css">
        <script src="<%= request.getContextPath()%>/js/jquery.js"></script>
        <script src="<%= request.getContextPath()%>/js/pintuer.js"></script>
        <script src="<%= request.getContextPath()%>/js/admin.js"></script>

        <style>
            .table td{vertical-align: middle;}
        </style>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/Goverment_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="table-responsive">
                <div class="text-center text-blue margin-big-bottom"><h2>船舶年检管理</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/ShipStateManageS">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="companyId" size="30" placeholder="按所属企业编号查找" />

                                <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered">
                    <tr style="background-color: #eee ">
                        <td>船舶编号</td><td>船舶名称</td><td>船舶类型</td><td>建造公司编号</td><td>所属公司编号</td><td>政府年检</td><td>功能操作</td>  
                    </tr>
                    <c:forEach var="ship" items="${ships}">
                        <tr><td>${ship.getShip_id()}</td>
                            <td>${ship.getShip_name()}</td>
                            <td>${ship.getShip_type()}</td>
                            <td>${ship.getConstruct_Company_id()}</td>
                            <td>${ship.getOwner_Company_id()}</td>
                            <td>${ship.getShip_state()}</td>
                            <td>
                                <a class="button button-small border-blue" href="<%=request.getContextPath()%>/Admin/ShipStateUpdateS?shipId=${ship.getShip_id()}">进行年检</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="text-center margin-big-top height-big">
                ${pagination.pageBar} &nbsp;&nbsp; ${pagination.numPageBar} &nbsp;&nbsp; 

            </div>
        </div>

    </body>
</html>
