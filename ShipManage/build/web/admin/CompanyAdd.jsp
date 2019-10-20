<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="admin">
            <div class="text-center text-blue margin-big-bottom"><h2>上市公司备案申报</h2></div>
            <hr/>
            <form method="post" action="<%= request.getContextPath()%>/Admin/CompanyAddServlet">
                <!--                <div class="form-group">
                                    <div class="label">
                                        <label for="companyId">企业编号</label>
                                    </div>
                                    <div class="field">
                                        <input type="text" class="input" id="companyId" name="companyId"  placeholder="企业编号" data-validate="required:必填,length#==6:编号为6位"/>
                                    </div>
                                </div>-->
                <div class="form-group">
                    <div class="label">
                        <label for="companyName">企业名称</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="companyName" name="companyName" size="50" placeholder="企业名称" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="companyTel">企业电话</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="companyTel" name="companyTel" size="50" placeholder="企业电话" data-validate="required:必填,number:纯数字"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="companyAddress">企业地址</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input" id="companyAddress" name="companyAddress" size="50" placeholder="企业地址" data-validate="required:必填"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label for="c">企业类型</label>
                    </div>
                    <div class="field">
                        <company_add_tag:CompanyType/>
                    </div>
                </div>
                <div class="form-button">
                    <button class="button" type="submit">提交</button>
                    <a  class="button" href="<%=request.getContextPath()%>/Admin/AdminLogout">返回</a>
                </div>
            </form>
        </div>
    </body>
</html>
