<%@page import="com.ship.util.DaoFactory"%>
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
        <div class="keypoint bg">
            <h1> ${message.getMessage_title()}</h1>
            <p> 作者: ${message.getMessage_name()}</p>
            <p style="float: right"> 发布时间: ${message.getMessage_Date()}</p>
        </div>
        <div class="detail" style="text-indent:30px; ">
            <p class="detail text-big" > ${message.getMessage_detail()}</p>
        </div>
    </body>
</html>
