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
    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/Provider_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>修改运输订单</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ProTraOrderUpdateDaoS">
                <div class="form-group">
                    <div class="label">
                        <label for="transportOrderId">运输订单编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportOrderId" name="transportOrderId" size="50" value="${transportOrder.getTransport_order_id()}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipId">运输船舶编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipId" name="shipId" size="50" value="${transportOrder.getShip_id()}" readonly />

                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="orderCompanyId">购单企业编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="orderCompanyId" name="orderCompanyId" size="50" value="${transportOrder.getOrder_Company_id()}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportCompanyId">运输企业编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportCompanyId" name="transportCompanyId" size="50" value="${transportOrder.getTransport_Company_id()}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="transportOrderDate">运输订单日期</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="transportOrderDate" name="transportOrderDate" size="50" value="${transportOrder.getTransport_Order_date()}" readonly/>
                    </div>
                </div>
                <c:if test="${ transportOrder.getTransport_order_state() != '未审理' }">

                    <div class="form-group">
                        <div class="label">
                            <label for="transportStart">运输起点</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="transportStart" name="transportStart" size="50"  value="${transportOrder.getTransport_start()}" readonly />
                        </div>
                    </div>
                </c:if>
                <c:if test="${ transportOrder.getTransport_order_state() == '未审理' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="transportStart">运输起点</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="transportStart" name="transportStart" size="50" data-validate="required:必填"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ transportOrder.getTransport_order_state() != '未审理' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="transportValue">运输费用</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="transportValue" name="transportValue" size="50" value="${transportOrder.getTransport_value()}" readonly/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ transportOrder.getTransport_order_state() == '未审理' }"> 
                    <div class="form-group">
                        <div class="label">
                            <label for="transportValue">运输费用</label>
                        </div>
                        <div class="field">
                            <input type="number" class="input" id="transportValue" name="transportValue" size="50" data-validate="required:必填,number:纯数字"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ transportOrder.getTransport_order_state() != '未审理' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="transportOrderDeal">订单评价</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="transportOrderDeal" name="transportOrderDeal" size="50" data-validate="required:必填,chinese:请输入中文评价"/>
                        </div>
                    </div>
                </c:if>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>

    </body>
</html>
