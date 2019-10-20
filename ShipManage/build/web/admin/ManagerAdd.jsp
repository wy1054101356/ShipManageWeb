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
<!--        <script src="<%= request.getContextPath()%>/js/admin.js"></script>-->

    </head>
    <body>
        <div class="header bg-main">
            <%@include file="/WEB-INF/jspf/admin_top.jspf" %>
        </div>
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>添加当前企业职员账号</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/ManagerAddDoServlet">
                <div class="form-group">
                    <div class="label">
                        <label for="managerId">职工号</label>
                    </div>
                    <div class="field">

                        <input type="text" class="input" id="managerId" name="managerId" size="16" placeholder="职工号" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="managerPassword">密码</label>
                    </div>
                    <div class="field">
                        <input type="password" class="input" id="managerPassword" name="managerPassword" size="16" placeholder="密码" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="managerPassword2">确认密码</label>
                    </div>
                    <div class="field">
                        <input type="password" class="input" id="managerPassword2" name="managerPassword2" size="16" placeholder="确认密码" data-validate="repeat#managerPassword:两次输入的密码不一致"/>
                    </div>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                </div>
            </form>
        </div>
    </body>
</html>
