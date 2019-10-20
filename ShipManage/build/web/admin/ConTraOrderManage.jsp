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
            <%@include file="/WEB-INF/jspf/Construct_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="table-responsive">
                <div class="text-center text-blue margin-big-bottom"><h2>本企业接受的运输订单信息</h2></div>
                <form method="post" action="<%= request.getContextPath()%>/Admin/ConTraOrderManageS">
                    <div class="form-group">
                        <div class="field">
                            <div class="input-group">
                                <span class="addbtn">
                                    <button type="button" class="button icon-search"></button>
                                </span>
                                <input type="text" class="input" name="transportOrderId" size="30" placeholder="请输入运输订单编号" />

                                <span class="addbtn"><input type="submit" class="button" value="搜索"/></span>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered">
                    <tr style="background-color: #eee ">
                        <td>运输订单编号</td><td>船舶编号</td><td>购单企业编号</td><td>运输企业编号</td><td>运输订单日期</td><td>运输起点</td><td>运输费用</td><td>订单受理状态</td><td>订单交易状态/评价</td><td>审核订单</td>    
                    </tr>
                    <c:forEach var="transportOrder" items="${transportOrders}">
                        <c:if test="${transportOrder.getTransport_order_state() == '未审理'}">
                            <tr style = "background-color: #F6CECE">
                                <td>${transportOrder.getTransport_order_id()}</td>
                                <td>${transportOrder.getShip_id()}</td>
                                <td>${transportOrder.getOrder_Company_id()}</td>
                                <td>${transportOrder.getTransport_Company_id()}</td>
                                <td>${transportOrder.getTransport_Order_date()}</td>
                                <td>${transportOrder.getTransport_start()}</td>
                                <td>${transportOrder.getTransport_value()}</td>
                                <td>${transportOrder.getTransport_order_state()}</td>
                                <td>${transportOrder.getTransport_order_deal()}</td>
                                <td>
                                    <a class="button button-small border-red" href="<%=request.getContextPath()%>/Admin/ConTraOrderStateS?transportOrderId=${transportOrder.getTransport_order_id()}">通过</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${transportOrder.getTransport_order_state() != '未审理'}">
                            <tr style = "background-color: #E3F6CE">
                                <td>${transportOrder.getTransport_order_id()}</td>
                                <td>${transportOrder.getShip_id()}</td>
                                <td>${transportOrder.getOrder_Company_id()}</td>
                                <td>${transportOrder.getTransport_Company_id()}</td>
                                <td>${transportOrder.getTransport_Order_date()}</td>
                                <td>${transportOrder.getTransport_start()}</td>
                                <td>${transportOrder.getTransport_value()}</td>
                                <td>${transportOrder.getTransport_order_state()}</td>
                                <td>${transportOrder.getTransport_order_deal()}</td>
                                <td>
                                    <c:if test="${transportOrder.getTransport_order_deal() == '运输故障'}">
                                        <!--<a class="button button-small border-red" href="<%=request.getContextPath()%>/Admin/ConTraOrderUpdateS?transportOrderId=${transportOrder.getTransport_order_id()}">解决故障</a>-->
                                        <a class="button button-small border-red" href="#">解决故障</a>
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
