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
        <script type="text/javascript">
            function confDel() {
                var tip = "是否确认删除当前信息？";
                if (confirm(tip)) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>

        <style>
            .table td{vertical-align: middle;}
        </style>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/Construct_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="table-responsive">
                <div class="text-center text-blue margin-big-bottom"><h2>本企业所属船舶</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/ConShipOwnManageS">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="shipId" size="30" placeholder="请输入船舶编号" />
                                <span class="addbtn"><input type="submit" class="button" value="搜索船舶"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered">
                    <tr style="background-color: #eee ">
                        <td>船舶编号</td><td>船舶名称</td><td>船舶类型</td><td>船舶载重</td><td>船舶吨位</td><td>所属企业编号</td><td>建造企业编号</td><td>船舶建造日期</td><td>船舶当前价值</td><td>政府年检</td><td>功能操作</td>    
                    </tr>
                    <c:forEach var="ship" items="${ships}">
                        <tr>
                            <td>${ship.getShip_id()}</td>
                            <td>${ship.getShip_name()}</td>
                            <td>${ship.getShip_type()}</td>
                            <td>${ship.getShip_load()}</td>
                            <td>${ship.getShip_weight()}</td>
                            <td>${ship.getOwner_Company_id()}</td>
                            <td>${ship.getConstruct_Company_id()}</td>
                            <td>${ship.getShip_construct_date()}</td>
                            <td>${ship.getShip_value()}</td>
                            <td>${ship.getShip_state()}</td>
                            <td>
                                <a class="button button-small border-blue" href="<%=request.getContextPath()%>/Admin/ConShipOwnUpdateS?shipId=${ship.getShip_id()}">修改</a>
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
