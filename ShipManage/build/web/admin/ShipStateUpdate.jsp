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
            <%@include file="/WEB-INF/jspf/Goverment_menu.jspf" %> 
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>船舶年检</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ShipStateUpdateDoS">
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
                        <input type="text" class="input" id="shipName" name="shipName" size="50" value="${ship.getShip_name()}" readonly />

                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="shipType">船舶类型</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipType" name="shipType" size="50" value="${ship.getShip_type()}" readonly />
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
                    <div class="field">
                        <input type="text" class="input" id="ownerCompanyId" name="ownerCompanyId" size="50" value="${ship.getOwner_Company_id()}" readonly/>
                    </div>
                </div> 
                <div class="form-group">
                    <div class="label">
                        <label for="shipState">船舶年检</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="shipState" name="shipState" size="50" value="${ship.getShip_state()}" data-validate="required:必填,chinese:请输入中文评价" />
                    </div>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>

    </body>
</html>
