<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <title>显示注册公司</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        
    </head>
    <body>
        <!--首页面头部开始-->
        <%@include file="WEB-INF/jspf/homer.jspf" %>
        <!--首页面头部结束-->
        <!--首页面内容开始-->
        <div class="container padding-big-top padding-big-bottom">
            <div class="line-big">
                <div class="xl10 xs3 xm3 xb2">
                    <div class="panel margin-big-bottom">
                        <div class="panel-head">企业类型</div>
                        <ul class="list-group">
                            <li><a  class="icon-comments" href="DisplayMessageS?" > 全部</a></li>
                            <li><a  class="icon-comments" href="DisplayMessageS?type=资讯" > 资讯</a></li>
                            <li><a  class="icon-comments" href="DisplayMessageS?type=举报" > 举报</a></li>

                        </ul>
                    </div>


                </div>
                <div class="xl12 xs9 xm9 xb10">

                    <div class="xm12">
                        <div class="panel">
                            <div class="panel-head"><strong>航运咨询 第一时间</strong></div>
                            <table class="table">
                                <c:forEach var="message" items="${messages}">
                                    <tr>
                                        <td width="110" align="right">
                                            【${message.getMessage_type()}】
                                        </td>
                                        <td>
                                            <a href="MessageDetailS?messageId=${message.getMessage_id()}">${message.getMessage_title()}</a>
                                        </td>
                                        <td width="150" align="right">
                                            ${message.getMessage_Date()}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
