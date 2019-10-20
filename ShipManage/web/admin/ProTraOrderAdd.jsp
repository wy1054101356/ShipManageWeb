<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="company_add_tag" uri="/WEB-INF/tlds/company_add_tag" %>
<%@ page import="com.ship.util.*"%>
<%@ page import="com.ship.model.*"%>


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
        <script>
            function myFunction()
            {
                var myselect = document.getElementById("transportCompanyId");
                var index = myselect.selectedIndex;
                var transportCompanyId = myselect.options[index].value;
                location.href = "/ShipManage/Admin/ProTraOrderAddS?transportCompanyId=" + transportCompanyId;
            }
        </script>
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/Provider_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>添加运输订单</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ProTraOrderAddDoS">
                <div class="form-group">
                    <div class="label">
                        <label for="transportOrderId">运输订单编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportOrderId" name="transportOrderId" size="50" value="${transportOrderId}" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="label">
                        <label for="orderCompanyId">购单企业编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="orderCompanyId" name="orderCompanyId" size="16" value="${orderCompanyId}" readonly />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportCompanyId">运输企业编号</label>
                    </div>
                    <select class="input" name="transportCompanyId" id="transportCompanyId" data-validate="required:请选择" onchange="myFunction()" >
                        <option  disabled="disabled" >请选择以注册的运输企业作为本次运输的企业</option>
                        <c:forEach var="company" items="${companys}">
                            <c:if test="${transportCompanyId != '000'}">
                                <c:if test="${company.getCompany_id() == transportCompanyId}">
                                    <option selected="selected" value="${company.getCompany_id()}">${company.getCompany_id()} ${company.getCompany_name()}</option>
                                </c:if>
                                <c:if test="${company.getCompany_id() != transportCompanyId}">
                                    <option value="${company.getCompany_id()}">${company.getCompany_id()} ${company.getCompany_name()}</option>
                                </c:if>
                            </c:if>
                            <c:if test="${transportCompanyId == '000'}">
                                <option value="${company.getCompany_id()}">${company.getCompany_id()} ${company.getCompany_name()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipId">运输船舶编号</label>
                    </div>
                    <select class="input" name="shipId"  data-validate="required:请选择学生班级" >
                        <option  disabled="disabled" >请选择当前选中的运输企业的运输船舶 | 若为空则当前企业暂无船舶</option>
                        <c:forEach var="ship" items="${ships}">
                            <c:if test="${shipId != '000'}">
                                <c:if test="${ship.getShip_id() == shipId}">
                                    <option selected="selected" value="${ship.getShip_id()}">${ship.getShip_id()} ${ship.getShip_name()}</option>
                                </c:if>
                                <option value="${ship.getShip_id()}">${ship.getShip_id()} ${ship.getShip_name()}</option>
                            </c:if>
                            <c:if test="${shipId == '000'}">
                                <option value="${ship.getShip_id()}">${ship.getShip_id()} ${ship.getShip_name()}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportOrderDate">运输订单日期</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportOrderDate" name="transportOrderDate" size="20" value="${transportOrderDate}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportStart">运输起点</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportStart" name="transportStart" size="50" data-validate="required:必填"/>

                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportValue">运输费用</label>
                    </div>
                    <div class="field">
                        <input type="number" class="input" id="transportValue" name="transportValue" size="50" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>

    </body>
</html>
