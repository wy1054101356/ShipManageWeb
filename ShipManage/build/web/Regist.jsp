<%@page import="com.ship.util.FileIO"%>
<%@page import="com.ship.util.SendPhoneMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
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
        <script  type="text/javascript">
            var int = self.setInterval("clock()", 1000);
            var second = 0;
            var phone = "";
            var yanzhengma = "";
            function clock()
            {
                if (second > 0) {
                    second = second - 1;
                    if (second < 10)
                        document.getElementById("send").innerHTML = "等待0" + second.toString() + "秒在发送";
                    else {
                        document.getElementById("send").innerHTML = "等待" + second.toString() + "秒在发送";
                    }
                } else {
                    document.getElementById("send").innerHTML = "发送短信验证码";
                }
            }

            function getPhoneMessage() {
                phone = document.getElementById("managerId").value;
                if (phone === "") {
                    alert("手机号为空!!");
                    return;
                }
                if (second > 0) //如果时间还没到
                    return;
                second = 60;
                ajaxFun();
                clock();
            }

            function ajaxFun() {
                $.ajax({
                    type: "POST",
                    url: "RegistServlet?phone=" + phone,
                    dataType: "text",
                    success: function (mes) {
                        document.getElementById("yanzhengma2").value = mes;
                    }
                });
            }

        </script>

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
                <form action="Regist" method="post">
                    <div class="panel padding" style="width: 450px;text-align: left; background-color:transparent;">
                        <div class="text-center">   
                            <br> <br> <br> <br> <br> <br>
                            <h2><strong>注册</strong></h2>
                        </div>
                        <div class="" style="padding:30px;">
                            <div class="form-group" style="color:white; float:end">
                                <div class="field field-icon-right" style="color:white">
                                    <input type="text" class="input" style="background-color:transparent;" id="managerId"  name="managerId" placeholder="请输入手机号" data-validate="required:必填,mobile:手机号格式有误,ajax#CheckNumber?managerId=:账号已存在" />
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
                                <div class="field field-icon-right" style="color:white">
                                    <input type="password" class="input" style="background-color:transparent;" name="password2" placeholder="再次输入密码" data-validate="repeat#password:两次输入的密码不一致" />
                                    <span class="icon icon-key "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field" > 
                                    <input type="text" class="input" hidden="hidden" id="yanzhengma2" name="yanzhengma2" />
                                    <input type="text" class="input" style="background-color:transparent; width: 50%; float: left" id="yanzhengma" name="yanzhengma" placeholder="请输入验证码" data-validate="repeat#yanzhengma2: 错误" />
                                    <a class="button bg-blue" id="send" name="send" style=" float: right; text-decoration:none;"  href="javascript:void(0);" onclick="getPhoneMessage()">发送短信验证码</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field" style=" margin-top: 50px">
                                    <button class="button button-block bg-blue text-big flash-hover ">
                                        立即注册
                                    </button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field test" style="; float: right;color: #008fff ">
                                    <a  href="Login.jsp">
                                        已有账号?返回登录
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
