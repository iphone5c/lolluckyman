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
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
                    <h4 style="text-align: center;padding-top: 20px;">LOL竞猜登陆</h4>
                    <form>
                        <p><input type="button" value="账号" class="xg_title"/><input type="text" class="xg_title2" id="loginAccount" placeholder="请输入账号"/></p>
                        <p><input type="button" value="密码" class="xg_title"/><input type="password" class="xg_title2" id="password" placeholder="请输入密码"/></p>
                        <p style="text-align: right;cursor: pointer">忘记密码？</p>
                        <p><button class="btn_login" style="border-radius: 5px" onclick="submitLogin()">Login</button></p>
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
                    <h4 style="text-align: center;padding-top: 20px;">LOL竞猜注册</h4>
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
            <div class="guessingRecord" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordPageList?pageIndex=0&pageSize=10'">我的竞猜记录</div>
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
            <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList1')">
                <img src="/resources/before/img/corps/G2.png">
                <span>${vs.teamA.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="vsText">
        <img src="/resources/before/img/vstext.png">
    </div>
    <div class="teamsTwo">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList1')">
                <img src="/resources/before/img/corps/G2.png">
                <span>${vs.teamB.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="choiceTexts">
        <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
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
            <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList2')">
                <img src="/resources/before/img/corps/G2.png">
                <span>${vs.teamA.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="vsText">
        <img src="/resources/before/img/vstext.png">
    </div>
    <div class="teamsTwo">
        <c:forEach var="vs" items="${v.value}">
            <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList2')">
                <img src="/resources/before/img/corps/G2.png">
                <span>${vs.teamB.chinaName}</span>
            </div>
        </c:forEach>
    </div>
    <div class="choiceTexts">
        <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
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
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList3')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList3')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
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
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList4')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList4')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
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
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList5')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamA.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="vsText">
            <img src="/resources/before/img/vstext.png">
        </div>
        <div class="teamsTwo" style="margin-left: 460px">
            <c:forEach var="vs" items="${v.value}">
                <div onclick="seletedTeam('${v.key}','${vs.competition.code}','competitionList5')">
                    <img src="/resources/before/img/corps/G2.png">
                    <span>${vs.teamB.chinaName}</span>
                </div>
            </c:forEach>
        </div>
        <div class="choiceTexts">
            <img src="/resources/before/img/choice.png" onclick="submitTeam('${v.key}')">
        </div>
    </c:forEach>
</div>
</div>
</div>
<!--第三步-->
<div class="content-steptwo">
    <div class="choice">
        <img style="margin-left: 21px" src="/resources/before/img/step1.png" onclick="window.location.href='/lol/main/jsp/mainIndex'">
        <img style="margin-left: -21px" src="/resources/before/img/step2.png" onclick="window.location.href='/lol/main/jsp/mainIndex'">
        <img style="margin-left: -21px" src="/resources/before/img/step3.png">
    </div>
    <div class="choiceText">
        <img src="/resources/before/img/rectangle.png">
        <img style="margin-left: 50px" src="/resources/before/img/teamText.png">
    </div>
    <div class="choiceTeam">
        <img style="float: left;margin-top: 40px" src="/resources/before/img/team-iocn.png">
        <img src="/resources/before/img/vs-icon.png">
        <img style="float: right;margin-top: 40px" src="/resources/before/img/team-iocn.png">
    </div>
    <div class="gameplayText">
        <img src="/resources/before/img/gameplayText.png">
    </div>
    <div class="gameplayOne">
        <div class="gameplayLink"></div>
        <ul>
            <li class="active1">总局输赢</li>
            <li>单局输赢</li>
            <li>比分</li>
            <li>一血</li>
            <li>一塔</li>
            <li>一小龙</li>
            <li>第一亚龙属性</li>
            <li>小龙数量</li>
        </ul>
        <div style="display: block" class="concretePlay">
            <span class="gameTitle">竞猜全球总决赛获胜的战队</span>
            <div class="gameTwo">
                <div>投注G2(1.80)</div>
                <div>投注CLG(1.80)</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜获得当局胜利的战队</span>
            <div class="gameTwo">
                <div>投注G2(1.80)</div>
                <div>投注CLG(1.80)</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战局数比分</span>
            <div class="gameThree">
                <div style="margin-left: 120px">1:2</div>
                <div>2:1</div>
                <div>2:0</div>
                <div>0:2</div>
                <div>2:3</div>
                <div style="margin-left: 120px">0:3</div>
                <div>3:0</div>
                <div>1:3</div>
                <div>3:1</div>
                <div>3:2</div>
            </div>
        </div>

        <div class="concretePlay">
            <span class="gameTitle">竞猜"一血"所属位置</span>
            <div class="gameFour">
                <div>上单</div>
                <div>中单</div>
                <div>下路组合</div>
                <div>打野</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜"一塔"所属线路</span>
            <div class="gameFive">
                <div style="margin-left: 215px">上路</div>
                <div>中路</div>
                <div>下路</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜"一小龙"所属战队</span>
            <div class="gameTwo">
                <div>投注G2(1.80)</div>
                <div>投注CLG(1.80)</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜"第一亚龙属性"所属属性</span>
            <div class="gameFour">
                <div>风属性</div>
                <div>火属性</div>
                <div>土属性</div>
                <div>水属性</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战结束时战队所获小龙数量</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>相同</div>
            </div>
        </div>
    </div>
    <div class="gameplayOne gameplayTwo">
        <div class="gameplayLink"></div>
        <ul>
            <li>一大龙</li>
            <li>一峡谷先锋</li>
            <li>单局四杀</li>
            <li>单局五杀</li>
            <li>率先十杀</li>
            <li>单局超神</li>
            <li>一水晶</li>
            <li>单方人头数单双</li>
            <li style="width: 136px">总人头数单双</li>
        </ul>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战中所获的第一条大龙所属战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战中攻占第一条峡谷先锋的战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜单局对战中获得四杀的的战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>

        <div class="concretePlay">
            <span class="gameTitle">竞猜单局对战中获得五杀的的战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜单局对战中率先获得10个人头的战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜单局对战中“单局超神”所属战队</span>
            <div class="gameFive">
                <div style="margin-left: 215px">投注G2(1.80)</div>
                <div>投注G2(1.80)</div>
                <div>均无</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜单局对战中所获的第一座水晶所属线路</span>
            <div class="gameFive">
                <div style="margin-left: 215px">上路</div>
                <div>中路</div>
                <div>下路</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战单方最终所获人头数的单双</span>
            <div class="gameFour">
                <div>投注G2单(1.80)</div>
                <div>投注G2双(1.80)</div>
                <div>投注CLG单(1.80)</div>
                <div>投注CLG双(1.80)</div>
            </div>
        </div>
        <div class="concretePlay">
            <span class="gameTitle">竞猜对战双方最终所获人头总数的单双</span>
            <div class="gameTwo">
                <div>投注单(1.80)</div>
                <div>投注双(1.80)</div>
            </div>
        </div>
    </div>
    <div style="clear: both"></div>
    <div class="confirm">
        <img src="/resources/before/img/confirm.png">
    </div>
</div>
</div>
<!--选中玩法之后的弹出框-->
<div class="bettingBg">
    <div class="betting">
        <div class="bettingTop"><span>总局输赢竞猜</span><img src="/resources/before/img/colse.png"></div>
        <h4>您投给了 : <span>SSG</span></h4>
        <div class="bettingIntegral">
            <span>投注积分</span>
            <div></div>
            <input type="text" value="10">
        </div>
        <div class="bettingEstimate">预计可赢：<span>18</span></div>
        <div class="bettingText">当前积分：<span>100</span></div>
        <div class="bettingQr">确认投注</div>
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
        // 选择小龙数量
        $(".num").click(function(){
            $(".num4").toggle();
        });
        $(".num4,.num").mouseleave(function(){
            $(".num4").hide();

        });
        $(".num4 ul li").click(function(){
            var i= $(".num4 ul li").index(this);
            var x = $(".num4 ul li").eq(i).html();
            $(".num1").html(x)
        });
        // 第三步确认选择并弹出投注框
        $(".confirm").click(function(){
            $(".bettingBg").show();
        })
        $(".bettingTop img").click(function(){
            $(".bettingBg").hide();
        })
        // 第三步选中具体玩法
        $(".gameplayOne>ul li").click(function(){
            var i= $(".gameplayOne>ul li").index(this);
            $(".gameplayOne>ul li").removeClass("active1");
            $(".gameplayOne>ul li").eq(i).addClass("active1");
            $(".concretePlay").css("display","none");
            $(".concretePlay").eq(i).css("display","block");
        });
        // 第三步选中玩法
        $(".concretePlay div div").click(function(){
            var i= $(".concretePlay div div").index(this);
            $(".concretePlay div div").removeClass("active2");
            $(".concretePlay div div").eq(i).addClass("active2");
        })
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