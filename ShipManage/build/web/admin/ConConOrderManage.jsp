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
                <div class="text-center text-blue margin-big-bottom"><h2>本企业申请的建造订单信息</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/ConConOrderManageS">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="constructOrderId" size="30" placeholder="请输入建造订单编号" />

                                <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered">
                    <tr style="background-color: #eee ">
                        <td>建造订单编号</td><td>建造企业编号</td><td>订单公司编号</td><td>建造订单日期</td><td>船舶类型</td><td>建造总价</td><td>订单受理状态/评价</td><td>功能操作</td>    
                    </tr>
                    <c:forEach var="constructOrder" items="${constructOrders}">
                        <c:if test="${constructOrder.getConstruct_order_state() == 'false'}">
                            <c:if test="${ constructOrder.getOrder_Company_id() == manager.getManager_Company_id() }">
                                <tr style = "background-color: #F6CECE">
                                </c:if>
                                <c:if test="${ constructOrder.getOrder_Company_id() != manager.getManager_Company_id() }">
                                <tr style = "background-color: #99ccff">
                                </c:if>
                                <td>${constructOrder.getConstruct_order_id()}</td>
                                <td>${constructOrder.getConstruct_Company_id()}</td>
                                <td>${constructOrder.getOrder_Company_id()}</td>
                                <td>${constructOrder.getConstruct_order_date()}</td>
                                <td>${constructOrder.getShip_type()}</td>
                                <td>${constructOrder.getConstruct_value()}</td>
                                <td>${constructOrder.getConstruct_order_state()}</td>
                                <td>
                                    <c:if test="${ constructOrder.getOrder_Company_id() == manager.getManager_Company_id() }">
                                        <a class="button button-small border-red" href="<%=request.getContextPath()%>/Admin/ConConOrderUpdateS?constructOrderId=${constructOrder.getConstruct_order_id()}">修改</a>丨
                                        <a class="button button-small border-red" href="<%=request.getContextPath()%>/Admin/ConConOrderDeleteS?constructOrderId=${constructOrder.getConstruct_order_id()}">退订</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${constructOrder.getConstruct_order_state() != 'false'}">
                            <c:if test="${ constructOrder.getOrder_Company_id() == manager.getManager_Company_id() }">
                                <tr style = "background-color: #E3F6CE">
                                </c:if>
                                <c:if test="${ constructOrder.getOrder_Company_id() != manager.getManager_Company_id() }">
                                <tr style = "background-color: #99ccff">
                                </c:if>
                                <td>${constructOrder.getConstruct_order_id()}</td>
                                <td>${constructOrder.getConstruct_Company_id()}</td>
                                <td>${constructOrder.getOrder_Company_id()}</td>
                                <td>${constructOrder.getConstruct_order_date()}</td>
                                <td>${constructOrder.getShip_type()}</td>
                                <td>${constructOrder.getConstruct_value()}</td>
                                <td>${constructOrder.getConstruct_order_state()}</td>
                                <td>
                                    <c:if test="${ constructOrder.getOrder_Company_id() == manager.getManager_Company_id() }">
                                        <a class="button button-small border-red" href="<%=request.getContextPath()%>/Admin/ConConOrderUpdateS?constructOrderId=${constructOrder.getConstruct_order_id()}">评价订单</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
            <div class="text-center margin-big-top height-big">
                ${pagination.pageBar} &nbsp;&nbsp; ${pagination.numPageBar} &nbsp;&nbsp; 
            </div>
        </div>

    </body>
</html>
