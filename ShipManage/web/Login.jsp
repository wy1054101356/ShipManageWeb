<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>
        <c:if test="${!(empty message)}">
            <script type="text/javascript">
                alert('<c:out value="${message}"/>');
            </script>
            <c:remove var="message" scope="session"/>
        </c:if>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <style>

            .bggg{
                background-image:url(images/bg.jpg);
                width:100%;
                height:650px;
                line-height:750px;
                position:absolute;
            }
            .bggg-blur{
                float:left;
                background-position:center;
                background-repeat:no-repeat;
                background-size:cover;
                background-position:center;
                background-repeat:no-repeat;
                background-size:cover;
                -webkit-filter: blur(9px);
                -moz-filter: blur(9px);
                -o-filter: blur(9px);
                -ms-filter: blur(9px);
            }
        </style>
    </head>
    <body >
        <!--首页面头部开始-->
        <%@include file="WEB-INF/jspf/homer.jspf" %>
        <!--首页面头部结束-->
        <div >
            <div  class = "bggg bggg-blur" style="" > </div>
            <div align="center">
                <form action="AdminLogin" method="post">
                    <div class="panel padding" style="width: 450px;text-align: left; background-color:transparent;">
                        <div class="text-center">   
                            <br> <br> <br> <br> <br> <br> <br> 
                            <h2><strong>系统登录</strong></h2>
                        </div>
                        <div class="" style="padding:30px;">
                            <div class="form-group">
                                <div class="field field-icon-right" style="color:white">
                                    <input type="text" class="input" style="background-color:transparent;" name="username" placeholder="请输入账号" data-validate="required:请填写账号" />
                                    <span class="icon icon-user"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field field-icon-right" style="color:white">
                                    <input type="password" class="input" style="background-color:transparent;" name="password" placeholder="请输入密码" data-validate="required:请填写密码" />
                                    <span class="icon icon-key "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field">
                                    <button class="button button-block bg-blue text-big flash-hover ">
                                        立即登录
                                    </button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field test" style="; float: right;color: #008fff ">
                                    <a  href="Regist.jsp">
                                        没有账号?立即注册
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div style="margin-top: 120px">
                <!--首页面尾部开始-->
                <%@include file="WEB-INF/jspf/footer.jspf" %>
                <!--首页面尾部结束-->
            </div>
        </div>
    </body>
</html>
