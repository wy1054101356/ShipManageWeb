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
            <%@include file="/WEB-INF/jspf/Construct_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>修改建造订单</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ConConOrderUpdateDoS">
                <div class="form-group">
                    <div class="label">
                        <label for="constructOrderId">建造订单编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="constructOrderId" name="constructOrderId" size="50" value="${constructOrder.getConstruct_order_id()}" readonly/>
                    </div>
                </div>

                <c:if test="${ constructOrder.getConstruct_order_state() !='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="constructCompanyId">建造企业编号</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="constructCompanyId" name="constructCompanyId" size="50" value="${constructOrder.getConstruct_Company_id()}" readonly/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ constructOrder.getConstruct_order_state() =='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="constructCompanyId">建造企业编号</label>
                        </div>
                        <select class="input" name="constructCompanyId" id="constructCompanyId" data-validate="required:请选择" >
                            <c:forEach var="company" items="${companys}">
                                <c:if test="${ company.getCompany_id() == constructOrder.getConstruct_Company_id() }">
                                    <option selected="selected" value="${company.getCompany_id()}">(已选择) ${company.getCompany_id()} ${company.getCompany_name()}</option>
                                </c:if>
                                <c:if test="${ company.getCompany_id() != constructOrder.getConstruct_Company_id() }">
                                    <option value="${company.getCompany_id()}">${company.getCompany_id()} ${company.getCompany_name()}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>

                <div class="form-group">
                    <div class="label">
                        <label for="orderCompanyId">购单企业编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="orderCompanyId" name="orderCompanyId" size="50" value="${constructOrder.getOrder_Company_id()}" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="label">
                        <label for="constructOrderDate">建造订单日期</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="constructOrderDate" name="constructOrderDate" size="50" value="${constructOrder.getConstruct_order_date()}" readonly/>
                    </div>
                </div>

                <c:if test="${ constructOrder.getConstruct_order_state() !='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="shipType">船舶类型</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="shipType" name="shipType" size="16" value="${constructOrder.getShip_type()}" readonly/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ constructOrder.getConstruct_order_state() =='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="shipType">船舶类型</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="shipType" name="shipType" size="16" value="${constructOrder.getShip_type()}" data-validate="required:必填"/>
                        </div>
                    </div>
                </c:if>

                <c:if test="${ constructOrder.getConstruct_order_state() !='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="constructValue">建造费用</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="constructValue" name="constructValue" size="16" value="${constructOrder.getConstruct_value()}" readonly/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${ constructOrder.getConstruct_order_state() =='false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="constructValue">建造费用</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="constructValue" name="constructValue" size="16" value="${constructOrder.getConstruct_value()}" data-validate="required:必填,number:纯数字"/>
                        </div>
                    </div>
                </c:if>

                <c:if test="${ constructOrder.getConstruct_order_state() != 'false' }">
                    <div class="form-group">
                        <div class="label">
                            <label for="constructOrderState">订单评价</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="constructOrderState" name="constructOrderState" size="50" data-validate="required:必填,chinese:请输入中文评价"/>
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
