<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <title>国内航运.船舶信息管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/pintuer.css">
        <script src="js/jquery.js"></script>
        <script src="js/pintuer.js"></script>

        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        <!--Header-->
        <div class="header" id="home">
            <!--top-bar-w3-agile-->
            <%@include file="WEB-INF/jspf/homer.jspf" %>
        </div>
        <!--//top-bar-w3-agile-->
        <!-- banner-text -->
        <div class="slider" id="top">
            <div class="callbacks_container">
                <ul class="rslides callbacks callbacks1" id="slider4">
                    <li>
                        <div class="banner-top">
                            <div class="banner-info_agileits_w3ls">
                                <h3>多管理员系统</h3>
                                <p>- 完备详细的航运和船舶信息</p>
                                <a href="Login.jsp">立即登录<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                                <a href="#communication">合作联系<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                                <br>
                                <a href="Regist.jsp">企业负责人 注册入住系统<i class="fa fa-caret-right" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!--//Slider-->

        <!-- /about -->
        <div class="banner_bottom">
            <div class="container">
                <div class="news-main">
                    <div class="col-md-6 news-left">
                        <div class="col-md-6 b_left">
                            <img src="images/ab.jpg" alt=" " class="img-responsive">

                        </div>
                        <div class="col-md-6 b_right">
                            <img src="images/ab1.jpg" alt=" " class="img-responsive">

                        </div>
                    </div>
                    <div class="col-md-6 news-right">
                        <h4>航运船舶系统简介</h4>
                        <p class="sub_p" style="text-indent:2em;">航运（Shipping）表示透过水路运输和空中运输等方式来运送人或货物。一般来说水路运
                            输的所需时间较长，但成本较为低廉，这是空中运输与陆路运输所不能比拟的。水路运输每次航程能运送
                            大量货物，而空运和陆运每次的负载数量则相对较少。因此在国际贸易上，水路运输是较为普遍的运送方
                            式。15世纪以来航运业的蓬勃发展极大的改变了人类社会与自然景观。
                        </p>
                        <p style="text-indent:2em;"> 国内航运企业、船舶管理信息系统，是针对国内的有关船舶制造，使用和管理的企业为更好企业业务发展，
                            而建立的信息管理系统。该系统服务于不同类型的企业以及船舶信息的管理，系统集成了国内在用和已弃
                            用等大量的企业提供的船舶信息，同时为已经在系统注册的企业提供任何为船舶提供服务的企业信息，以
                            及船舶信息，港口航运信息，人员流通信息等有关企业发展和船舶使用动态等信息。
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- //about -->
        <div class="history">
            <div class="container">
                <h3 class="tittle two">多 类 型 企 业 系 统</h3>
                <div class="inner_sec_info_wthree_agile">
                    <div class="col-md-4 history-grid">
                        <h4>供 应 企 业</h4>
                        <div class="story-img">
                            <img src="images/b1.jpg" alt="供应企业">
                        </div>
                        <div class="caption_story_w3ls">
                            <p>供应企业拥大量货物订单，需要将其利用海运进行长途或短 途运输，系统可以提供完备的船舶和企业管理信息。</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="col-md-4 history-grid">
                        <h4>运 输 企 业</h4>
                        <div class="story-img">
                            <img src="images/b3.jpg" alt=" ">
                        </div>
                        <div class="caption_story_w3ls">

                            <p>运输企业拥有企业在运营的船舶信息，并解决供应企业的需求，同时兼备对新船舶的需求响应。</p>

                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="col-md-4 history-grid">
                        <h4>建 造 企 业</h4>
                        <div class="story-img">
                            <img src="images/b4.jpg" alt=" ">
                        </div>
                        <div class="caption_story_w3ls">

                            <p>建造企业针对船舶市场、航运情况、运输企业需求，进行船舶建造和订单处理，以进行数据统计和管理分析。</p>

                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!-- /projects -->

        <div class="mid_slider_info" style="margin-top: 70px">
            <h3 class="tittle">合 作 伙 伴</h3>
            <div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top: 20px">
                <div class="row" >
                    <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                        <div class="thumbnail"><img src="images/zghy.jpg" alt="Image" style="max-width:100%;">
                            <center>
                                <a href="http://3g.cnshipping.com">中国海运</a>
                            </center>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                        <div class="thumbnail"><img src="images/zghyxxw.jpg" alt="Image" style="max-width:100%;">
                            <center>
                                <a href="http://www.chinashippinginfo.net">中国海运信息网</a>
                            </center>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                        <div class="thumbnail"><img src="images/zggtxww.jpg" alt="Image" style="max-width:100%;">
                            <center>
                                <a href="http://www.csteelnews.com">中国钢铁新闻网</a>
                            </center>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                        <div class="thumbnail"><img src="images/zggk.jpg" alt="Image" style="max-width:100%;">
                            <center>
                                <a href="http://www.chinaports.com">中国港口网</a>
                            </center>
                        </div>
                    </div>
                </div>
            </div>   

            <center id="communication" style="margin-top: 20px">           
                <h4> 
                    <a > 联系方式 </a>
                    <a href="#top"> 回到顶部 </a>
                </h4>
                TEL: 1381234562 QQ: 12543212 微信:船舶航运
            </center>
        </div>


        <!-- //projects -->

        <!--首页面尾部开始-->
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!--首页面尾部结束-->

    </body>

</html>