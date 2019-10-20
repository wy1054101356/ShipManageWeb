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
            window.onload = function () {
                //1、获取表格
                var tab = document.getElementById("tal");
                //2、获取表格中tbody的行数
                var len = tab.tBodies[0].rows.length
                //                alert(len)
                //3、开始循环，设置颜色
                for (var i = 0; i < len; i++) {
                    if (i % 2 == 0) {
                        tab.tBodies[0].rows[i].style.backgroundColor = "write";
                    } else {
                        tab.tBodies[0].rows[i].style.backgroundColor = "#efefef";
                    }
                }
            };
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
            <%@include file="/WEB-INF/jspf/Provider_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="table-responsive">
                <div class="text-center text-blue margin-big-bottom"><h2>在运营船舶信息</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/ProShipManageS">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="companyId" size="30" placeholder="请输入运输企业编号" />
                                <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered" id="tal">
                    <tr style="background-color: #cacaca ">
                        <td>船舶编号</td><td>船舶名称</td><td>船舶类型</td><td>船舶载重</td><td>船舶吨位</td><td>建造企业编号</td><td>所属企业编号</td><td>船舶建造日期</td><td>船舶当前价值</td><td>政府年检</td><td>功能操作</td>    
                    </tr>
                    <c:forEach var="ship" items="${ships}" varStatus="status">
                        <tr>
                            <td><a href="<%=request.getContextPath()%>/Admin/ProTraOrderManageS?shipId=${ship.getShip_id()}">${ship.getShip_id()}</a></td>
                            <td><a href="<%=request.getContextPath()%>/Admin/ProTraOrderManageS?shipId=${ship.getShip_id()}">${ship.getShip_name()}</a></td>
                            <td>${ship.getShip_type()}</td>
                            <td>${ship.getShip_load()}</td>
                            <td>${ship.getShip_weight()}</td>
                            <td>${ship.getConstruct_Company_id()}</td>
                            <td>${ship.getOwner_Company_id()}</td>
                            <td>${ship.getShip_construct_date()}</td>
                            <td>${ship.getShip_value()}</td>
                            <td>${ship.getShip_state()}</td>
                            <td>
                                <c:if test="${shipStates[status.index] == 'true'}">
                                    <a class="button button-small border-red">当前船舶正在运输</a>
                                </c:if>
                                <c:if test="${shipStates[status.index] != 'true'}">
                                    <a class="button button-small border-blue" href="<%=request.getContextPath()%>/Admin/ProTraOrderAddS?shipId=${ship.getShip_id()}&transportCompanyId=${ship.getOwner_Company_id()}">选此船舶定制运输订单</a>
                                </c:if>
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
