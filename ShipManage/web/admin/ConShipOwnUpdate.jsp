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
        <div class="header bg-blue">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="menu">
            <%@include file="/WEB-INF/jspf/Construct_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>修改船舶信息</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ConShipOwnUpdateDoS">
                <div class="form-group">
                    <div class="label">
                        <label for="shipId">船舶编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipId" name="shipId" size="50" value="${ship.getShip_id()}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipName">船舶名称</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipName" name="shipName" size="50" value="${ship.getShip_name()}" data-validate="required:必填" />

                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipType">船舶类型</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipType" name="shipType" size="50" value="${ship.getShip_type()}" data-validate="required:必填" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipLoad">船舶载重</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipLoad" name="shipLoad" size="50" value="${ship.getShip_load()}" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipWeight">船舶吨位</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipWeight" name="shipWeight" size="50" value="${ship.getShip_weight()}" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="constructCompanyId">建造企业编号</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="constructCompanyId" name="constructCompanyId" size="50" value="${ship.getConstruct_Company_id()}" readonly/>
                    </div>
                </div>                
                <div class="form-group">
                    <div class="label">
                        <label for="ownerCompanyId">所属企业编号</label>
                    </div>
                    <select class="input" name="ownerCompanyId" id="ownerCompanyId" data-validate="required:请选择" >
                        <c:forEach var="company" items="${companys}">
                            <c:if test="${ company.getCompany_id() == ship.getOwner_Company_id() }">
                                <option selected="selected" value="${company.getCompany_id()}">(本企业) ${company.getCompany_id()} ${company.getCompany_name()} ${company.getCompany_type()}</option>
                            </c:if>
                            <c:if test="${ company.getCompany_id() != ship.getOwner_Company_id() }">
                                <option value="${company.getCompany_id()}">${company.getCompany_id()} ${company.getCompany_name()} ${company.getCompany_type()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>   
                <div class="form-group">
                    <div class="label">
                        <label for="shipConstructDate">船舶建造日期</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipConstructDate" name="shipConstructDate" size="50" value="${ship.getShip_construct_date()}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipValue">船舶剩余价值</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipValue" name="shipValue" size="50" value="${ship.getShip_value()}" data-validate="required:必填,number:纯数字" />
                    </div>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>

    </body>
</html>
