<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <title>显示船舶</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <script type="text/javascript">
            window.onload = function () {
                //1、获取表格
                var tab = document.getElementById("tal");
                //2、获取表格中tbody的行数
                var len = tab.tBodies[0].rows.length
//                alert(len)
                //3、开始循环，设置颜色
                for (var i = 0; i < len; i++) {
                    if (i % 2 == 0) {
                        tab.tBodies[0].rows[i].style.backgroundColor = "write";
                    } else {
                        tab.tBodies[0].rows[i].style.backgroundColor = "#efefef";
                    }
                }
            };
        </script>
    </head>
    <body>
        <!--首页面头部开始-->
        <%@include file="WEB-INF/jspf/homer.jspf" %>
        <!--首页面头部结束-->
        <!--首页面内容开始-->
        <div class="container padding-big-top padding-big-bottom">
            <div class="line-big">
                <div class="xl12 xs3 xm3 xb2">
                    <div class="panel margin-big-bottom">
                        <div class="panel-head">所属船舶企业</div>
                        <ul class="list-group">
                            <c:forEach var="transportCompany" items="${transportCompanys}">
                                <li><a class="icon-university" href="DisplayShipServlet?mid=${transportCompany.getCompany_id()}" title="${transportCompany.getCompany_id()}"> ${transportCompany.getCompany_name()}</a></li>
                                </c:forEach>

                        </ul>
                    </div>


                </div>
                <div class="xl12 xs9 xm9 xb9">
                    <c:if test="${empty ships}">
                        <div class="text-large text-center margin-big-bottom">当前企业暂无船舶！</div></c:if>
                    <c:if test="${!empty ships}">
                        <div class="text-large text-center margin-big-bottom">船舶总览</div>
                        <table class="table table-hover table-bordered" id="tal">
                            <tr style="background-color: #cacaca;">
                                <td>船舶编号</td><td>船舶名称</td><td>船舶类型</td><td>船舶载重</td><td>船舶吨位</td><td>建造企业编号</td><td>所属企业编号</td><td>船舶建造日期</td><td>船舶当前价值</td><td>政府年检</td>
                            </tr>
                            <c:forEach var="ship" items="${ships}">
                                <tr>
                                    <td>${ship.getShip_id()}</td>
                                    <td>${ship.getShip_name()}</td>
                                    <td>${ship.getShip_type()}</td>
                                    <td>${ship.getShip_load()}</td>
                                    <td>${ship.getShip_weight()}</td>
                                    <td>${ship.getConstruct_Company_id()}</td>
                                    <td>${ship.getOwner_Company_id()}</td>
                                    <td>${ship.getShip_construct_date()}</td>
                                    <td>${ship.getShip_value()}</td>
                                    <td>${ship.getShip_state()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="text-center height-big margin-top">
                            ${pagination.pageBar} ${pagination.numPageBar}
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
        <!--首页面内容结束-->
        <!--首页面尾部开始-->
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!--首页面尾部结束-->
    </body>
</html>
