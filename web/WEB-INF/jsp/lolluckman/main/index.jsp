<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/9/30
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html lang="zh" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/resources/before/css/index.css">
    <link rel="stylesheet" type="text/css" href="/resources/before/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/component.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/content.css" />
    <script src="/resources/before/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/before/js/modernizr.custom.js"></script>
    <script src="/resources/before/lol/common.js"></script>
    <script src="/resources/before/lol/login.js"></script>
    <script src="/resources/before/lol/main.js"></script>
    <script src="/resources/before/js/classie.js"></script>
    <script src="/resources/before/js/uiMorphingButton_fixed.js"></script>
</head>
<body>
<div class="mockup-content" style="margin-top:-100px;">
    <%--<button type="button" style="position:absolute;width: 270px;height: 60px;top:500px;background:none;border: none;z-index: 999;left: 30%;"><img class="loginImg" src="/resources/before/img/login.png"></button>--%>
    <%--<button type="button" style="position:absolute;width: 270px;height: 60px;top:500px;background:none;border: none;z-index: 999;right: 30%;"><img class="registerImg" src="/resources/before/img/register.png"></button>--%>
    <div class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed active open" style="display: none">

        <div class="morph-content">
            <div>
                <div class="content-style-form content-style-form-1">
                    <img src="/resources/before/img/cloes.png" alt="" class="icons1" style="width: 15px;height: 15px;position: absolute;right: 10px;top: 10px;"/>
                    <h4 style="text-align: center;padding-top: 30px;">LOL竞猜登陆</h4>
                    <br/>
                    <form>
                        <p style="margin-bottom: 20px;"><input type="button" value="账号" class="xg_title"/><input type="text" class="xg_title2" id="loginAccount" placeholder="请输入账号"/></p>
                        <p><input type="button" value="密码" class="xg_title"/><input type="password" class="xg_title2" id="password" placeholder="请输入密码"/></p>
                        <%--<p style="text-align: right;cursor: pointer">忘记密码？</p>--%>
                        <p><button class="btn_login" style="border-radius: 5px" onclick="submitLogin()">登陆</button></p>
                    </form>
                </div>
            </div>
        </div>
    </div><!-- morph-button -->
    <div class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed active open" style="display: none">

        <div class="morph-content">
            <div>
                <div class="content-style-form content-style-form-2">
                    <img src="/resources/before/img/cloes.png" alt="" class="icons2" style="width: 15px;height: 15px;position: absolute;right: 10px;top: 10px;"/>
                    <h4 style="text-align: center;padding-top: 30px;">LOL竞猜注册</h4>
                    <br/>
                    <form class="xg_form">
                        <p><input type="button" value="会员账号" class="xg_title"/><input type="text" class="xg_title2" id="loginName" placeholder="请输入账号"/></p>
                        <p><input type="button" value="会员密码" class="xg_title"/><input type="password" class="xg_title2" id="loginPassword" placeholder="请输入密码"/></p>
                        <p><input type="button" value="确认密码" class="xg_title"/><input type="password" class="xg_title2" id="repassword" placeholder="请输入确认密码"/></p>
                        <p><input type="button" value="提现密码" class="xg_title"/><input type="password" class="xg_title2" id="withdrawalsPassword" placeholder="请输入提现密码"/></p>
                        <p style="position: relative"><input type="button" value="验证码" class="xg_title"/><input type="text" class="xg_title2 xg_Yz" id="securityCode" placeholder="请输入验证码"/>
                            <img src="/lol/account/jsp/getKaptchaImage" onclick="getKaptchaImage(this)" style="position: absolute;top:0;right:5px">
                        </p>
                        <p><button class="btn_login" style="border-radius: 5px" onclick="submitSignup()">注册</button></p>
                    </form>
                </div>
            </div>
        </div>
    </div><!-- morph-button -->
</div><!-- /form-mockup -->
<div class="guessbox">
<c:if test="${not empty account}">
    <div class="navboxs">
        <div class="navbox">
            <div class="portrait">
                <img src="/resources/before/img/portrait.png">
                <span>${account.loginAccount}</span>
            </div>
            <div class="link"></div>
            <%--<div class="account">--%>
                <%--<div class="integral">--%>
                    <%--<h4>我的积分:</h4>--%>
                    <%--<span class="integralText">1520</span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="guessing">
                <img src="/resources/before/img/guessing-icon.png">
                竞猜币
                <span>${account.accountAssets.quizMoney}</span>
            </div>
            <div class="pension">
                <img src="/resources/before/img/Pension-icon.png">
                抚恤金
                <span>${account.accountAssets.pensionMoney}</span>
            </div>
            <div class="win">
                <img src="/resources/before/img/win-icon.png">
                胜利币
                <span>${account.accountAssets.victoryMoney}</span>
            </div>
            <div class="link"></div>
            <div class="guessingRecord" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList'">我的竞猜记录</div>
            <div class="instantRecharge" onclick="window.location.href='/lol/topupwithdrawal/jsp/topUpWithdrawal'">立即充值</div>
        </div>
    </div>
</c:if>

<div style="clear: both"></div>
<div class="contentbox">
<div class="banner">
    <img class="bannerImg" src="/resources/before/img/banner.jpg">
    <img class="title" src="/resources/before/img/title.png">
    <c:if test="${not empty account}">
        // 充值按钮
        <img  class="rechargeImg" src="/resources/before/img/recharge.png" onclick="window.location.href='/lol/topupwithdrawal/jsp/topUpWithdrawal'">
        // 退出按钮
        <img  class="exitImg" src="/resources/before/img/exit.png" onclick="logout()">
    </c:if>
    <c:if test="${empty account}">
        // 登录按钮
        <img class="loginImg" src="/resources/before/img/login.png">
        // 注册按钮
        <img class="registerImg" src="/resources/before/img/register.png">
    </c:if>
</div>
<!--第一、二步-->
<div class="content-stepone">
<div class="choice">
    <img style="margin-left: 21px" src="/resources/before/img/step1.png">
    <img style="margin-left: -21px" src="/resources/before/img/step2.png">
    <img style="margin-left: -21px" src="/resources/before/img/step3.png">
</div>
<div style="clear: both"></div>
<div class="firstround">
<!--<img class="timepanel" src="/resources/before/img/timepanel.png">-->
<img class="time1 times" src="/resources/before/img/timeBgAf.png">
<img class="time2 times" src="/resources/before/img/timeBgBf.png">
<img class="time3 times" src="/resources/before/img/timeBgBf.png">
<img class="time4 times" src="/resources/before/img/timeBgBf.png">
<img class="time5 times" src="/resources/before/img/timeBgBf.png">
<img class="time1 times1" style="top: -75px;left: 155px;" src="/resources/before/img/930-103.png">
<img class="time2 times1" style="top: -75px;left: 355px;"  src="/resources/before/img/1007-1010.png">
<img class="time3 times1" style="top: -75px;left: 555px;" src="/resources/before/img/1014-1017.png">
<img class="time4 times1" style="top: -75px;left: 755px;" src="/resources/before/img/1022-1023.png">
<img class="time5 times1" style="top: -45px;left: 945px;" src="/resources/before/img/1030.png">
<div style="display: block" class="timeXz">
<div class="firstroundText">
    <img src="/resources/before/img/firstround.png">
</div>
    <c:forEach var="v" items="${competitionList1}">
    <input type="hidden" id="${v.key}" name="competitionList1">
    <div class="timeText">
        <span>${v.key}</span>
    </div>
    <div class="teamsOne">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList1')">
                <img src="${vs.teamA.teamphone}">
                <span>${vs.teamA.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="vsText">
        <img src="/resources/before/img/vstext.png">
    </div>
    <div class="teamsTwo">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList1')">
                <img src="${vs.teamB.teamphone}">
                <span>${vs.teamB.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="choiceTexts">
        <c:if test="${empty account}">
            <img src="/resources/before/img/choice.png" onclick="alert('请先登录，再来选择。')">
        </c:if>
        <c:if test="${not empty account}">
            <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
        </c:if>
    </div>
</c:forEach>
</div>
<div  class="timeXz">
<div class="firstroundText">
    <img src="/resources/before/img/secondround.png">
</div>
<c:forEach var="v" items="${competitionList2}">
    <input type="hidden" id="${v.key}" name="competitionList2">
    <div class="timeText">
        <span>${v.key}</span>
    </div>
    <div class="teamsOne">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList2')">
                <img src="${vs.teamA.teamphone}">
                <span>${vs.teamA.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="vsText">
        <img src="/resources/before/img/vstext.png">
    </div>
    <div class="teamsTwo">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList2')">
                <img src="${vs.teamB.teamphone}">
                <span>${vs.teamB.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="choiceTexts">
        <c:if test="${empty account}">
            <img src="/resources/before/img/choice.png" onclick="alert('请先登录，再来选择。')">
        </c:if>
        <c:if test="${not empty account}">
            <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
        </c:if>
    </div>
</c:forEach>
</div>
<div  class="timeXz">
    <div class="firstroundText">
        <img src="/resources/before/img/4-1finals.png">
    </div>
    <c:forEach var="v" items="${competitionList3}">
        <input type="hidden" id="${v.key}" name="competitionList3">
        <div class="timeText">
            <span>${v.key}</span>
        </div>
        <div class="teamsOne" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList3')">
                    <img src="${vs.teamA.teamphone}">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList3')">
                    <img src="${vs.teamB.teamphone}">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <c:if test="${empty account}">
                <img src="/resources/before/img/choice.png" onclick="alert('请先登录，再来选择。')">
            </c:if>
            <c:if test="${not empty account}">
                <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
            </c:if>
        </div>
    </c:forEach>
</div>
<div  class="timeXz">
    <div class="firstroundText">
        <img src="/resources/before/img/semifinal.png">
    </div>
    <c:forEach var="v" items="${competitionList4}">
        <input type="hidden" id="${v.key}" name="competitionList4">
        <div class="timeText">
            <span>${v.key}</span>
        </div>
        <div class="teamsOne" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList4')">
                    <img src="${vs.teamA.teamphone}">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList4')">
                    <img src="${vs.teamB.teamphone}">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <c:if test="${empty account}">
                <img src="/resources/before/img/choice.png" onclick="alert('请先登录，再来选择。')">
            </c:if>
            <c:if test="${not empty account}">
                <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
            </c:if>
        </div>
    </c:forEach>
</div>
<div  class="timeXz">
    <div class="firstroundText">
        <img src="/resources/before/img/Finals.png">
    </div>
    <c:forEach var="v" items="${competitionList5}">
        <input type="hidden" id="${v.key}" name="competitionList5">
        <div class="timeText">
            <span>${v.key}</span>
        </div>
        <div class="teamsOne" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList5')">
                    <img src="${vs.teamA.teamphone}">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.restrain.code}','competitionList5')">
                    <img src="${vs.teamB.teamphone}">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <c:if test="${empty account}">
                <img src="/resources/before/img/choice.png" onclick="alert('请先登录，再来选择。')">
            </c:if>
            <c:if test="${not empty account}">
                <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
            </c:if>
        </div>
    </c:forEach>
</div>
</div>
</div>
<div id="selectTeamCompetition" class="content-steptwo">

</div>
<!--选中玩法之后的弹出框-->
<div class="bettingBg">
    <div class="betting">
        <div class="bettingTop"><span id="titleConfirm"></span><img src="/resources/before/img/colse.png"></div>
        <h4>您的投注: <span id="infoConfirm"></span></h4>
        <div class="bettingIntegral">
            <span>投注竞猜币</span>
            <div><input id="quizMoney" type="text" style="width: 246px;height: 38px;margin-left: 3px;text-align: center; placeholder="请输入数量"/></div>
        </div>
        <div class="bettingEstimate">预计可赢：<span>0</span></div>
        <div class="bettingText">当前竞猜币：<span>${account.accountAssets.quizMoney}</span></div>
        <div class="bettingQr" onclick="submitTz()">确认投注</div>
    </div>
</div>
<!--提示模板-->
<div class="bombBoxbg">
    <div class="bombBox">
        <div class="bombBoxTop">提示<img src="/resources/before/img/colse.png"></div>
        <div class="bombBoxText" style="color: #000000">您还没有选择！</div>
    </div>
</div>
</div>
<%--<script src="/resources/before/js/classie.js"></script>--%>
<%--<script src="/resources/before/js/uiMorphingButton_fixed.js"></script>--%>
<script>
    (function() {
        var docElem = window.document.documentElement, didScroll, scrollPosition;
        // trick to prevent scrolling when opening/closing button44444
        function noScrollFn() {
            window.scrollTo( scrollPosition ? scrollPosition.x : 0, scrollPosition ? scrollPosition.y : 0 );
        }
        function noScroll() {
            window.removeEventListener( 'scroll', scrollHandler );
            window.addEventListener( 'scroll', noScrollFn );
        }
        function scrollFn() {
            window.addEventListener( 'scroll', scrollHandler );
        }
        function canScroll() {
            window.removeEventListener( 'scroll', noScrollFn );
            scrollFn();
        }
        function scrollHandler() {
            if( !didScroll ) {
                didScroll = true;
                setTimeout( function() { scrollPage(); }, 60 );
            }
        };

        function scrollPage() {
            scrollPosition = { x : window.pageXOffset || docElem.scrollLeft, y : window.pageYOffset || docElem.scrollTop };
            didScroll = false;
        };

        scrollFn();

        [].slice.call( document.querySelectorAll( '.morph-button' ) ).forEach( function( bttn ) {
            new UIMorphingButton( bttn, {
                closeEl : '.icon-close',
                onBeforeOpen : function() {
                    // don't allow to scroll
                    noScroll();
                },
                onAfterOpen : function() {
                    // can scroll again
                    canScroll();
                },
                onBeforeClose : function() {
                    // don't allow to scroll
                    noScroll();
                },
                onAfterClose : function() {
                    // can scroll again
                    canScroll();
                }
            } );
        } );

        // for demo purposes only
        [].slice.call( document.querySelectorAll( 'form button' ) ).forEach( function( bttn ) {
            bttn.addEventListener( 'click', function( ev ) { ev.preventDefault(); } );
        } );
        $(".mockup-content").css("background","transparent")

        // 第一、二步选择队伍
        $(".teamsOne div").click(function(){
            var i = $(".teamsOne div").index(this);
            $(".teamsOne div").css("background","none");
            $(".teamsOne div").css("border","2px solid #160E02");
            $(".teamsTwo div").css("background","none");
            $(".teamsTwo div").css("border","2px solid #160E02");
            $(".teamsTwo div span").css("color","#B28247");
            $(".teamsTwo div span").eq(i).css("color","black");
            $(".teamsOne div span").css("color","#B28247");
            $(".teamsOne div span").eq(i).css("color","black");
            $(".teamsOne div").eq(i).css("background","url('/resources/before/img/teambg.png')");
            $(".teamsOne div").eq(i).css("border","2px solid transparent");
            $(".teamsTwo div").eq(i).css("background","url('/resources/before/img/teambg.png')");
            $(".teamsTwo div").eq(i).css("border","2px solid transparent");
        })
        $(".teamsTwo div").click(function(){
            var i = $(".teamsTwo div").index(this);
            $(".teamsOne div").css("background","none");
            $(".teamsOne div").css("border","2px solid #160E02");
            $(".teamsTwo div").css("background","none");
            $(".teamsTwo div").css("border","2px solid #160E02");
            $(".teamsTwo div span").css("color","#B28247");
            $(".teamsTwo div span").eq(i).css("color","black");
            $(".teamsOne div span").css("color","#B28247");
            $(".teamsOne div span").eq(i).css("color","black");
            $(".teamsOne div").eq(i).css("background","url('/resources/before/img/teambg.png')");
            $(".teamsOne div").eq(i).css("border","2px solid transparent");
            $(".teamsTwo div").eq(i).css("background","url('/resources/before/img/teambg.png')");
            $(".teamsTwo div").eq(i).css("border","2px solid transparent");

        });
        // 第一、二步确认选择
        $(".choiceTexts img").click(function(){
//            $(".content-steptwo").show();
//            $(".content-stepone").hide();
//            document.body.scrollTop=0;
        })
        // 第一、二步选择时间
        $(".firstround .times").click(function(){
            var i = $(".firstround .times").index(this);
            $(".firstround .times").attr("src","/resources/before/img/timeBgBf.png");
            $(".firstround .times").eq(i).attr("src","/resources/before/img/timeBgAf.png");
            $(".timeXz").hide()
            $(".timeXz").eq(i).show();
        })
        $(".firstround .times1").click(function(){
            var i = $(".firstround .times1").index(this);
            $(".firstround .times").attr("src","/resources/before/img/timeBgBf.png");
            $(".firstround .times").eq(i).attr("src","/resources/before/img/timeBgAf.png");
            $(".timeXz").hide()
            $(".timeXz").eq(i).show();
        })
        // 关闭提示模板
        $(".bombBoxTop img").click(function(){
            $(".bombBoxbg").hide();
        })
        // 登陆按钮
        $(".loginImg").click(function(){
            $(".morph-button-modal-2").show();
        })
        $(".icons1").eq(0).click(function(){
            $(".morph-button-modal-2").hide();
        })
        // 注册按钮
        $(".registerImg").click(function(){
            $(".morph-button-modal-3").show();
        })
        $(".icons2").click(function(){
            $(".morph-button-modal-3").hide();

        })
    })();
</script>
</body>
</html>