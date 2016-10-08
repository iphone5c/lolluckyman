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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/resources/before/css/Recharge.css"/>
    <link rel="stylesheet" href="/resources/before/css/index.css"/>
    <script type="text/javascript" src="/resources/before/lol/common.js"></script>
    <script type="text/javascript" src="/resources/before/lol/topupwithdrawal.js"></script>

</head>
<body>
<div class="allcontent">
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
                <div class="guessingRecord" onclick="window.location.href='/lol/main/jsp/mainIndex'">返回首页</div>
                <div class="instantRecharge" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList'">我的竞猜记录</div>
            </div>
        </div>
    </c:if>
    <div class="left">
        <div class="left_div1" style="height: 200px;border-bottom:1px solid #e6e6e6;">
            <img src="/resources/before/img/logo.png" alt="" style="margin-top: 60px;"/>
        </div>
        <div class="left_div1">
                    <span>
                        10竞猜币/元
                    </span>
        </div>
        <ul>
            <li style="border-bottom: none" class="active">
                    <span>
                        账户充值
                    </span>
            </li>
            <li>
                    <span>
                        账户提现
                    </span>
            </li>
        </ul>
        <div class="left_div2">
                   <span>
                       <img src="/resources/before/img/input.png" alt=""/>
                       同意
                       <label style="color:#d29031 ">
                           LOL充值条款
                       </label>
                   </span>
        </div>
    </div>
    <div class="right">
        <div class="right_top">
            <span>充值账号：</span><input id="tradeNumber" type="text" style="width: 256px;height: 30px;margin-bottom: 20px;text-align: center" placeholder="请输入充值的微信或支付宝账号"/><br/>
            <span>充值金额：</span><input id="applyMoney" type="text" style="width: 125px;height: 30px;margin-bottom: 20px;text-align: center"/> 元 <span>(1元=10竞猜币)</span><br/>
            <span>充值方式：</span><div class="xg_input active xg_input_in">微信支付<img src="/resources/before/img/before.png" class="before"/></div><div class="xg_input2 xg_input_in">支付宝支付<img src="/resources/before/img/before.png" class="before"/></div><br/><br/>
            <input id="tradeType" type="hidden" value="微信">
            <span style="opacity:0">充值方式：</span>
            <img src="/resources/before/img/wei.png" alt="" class="img1"/>
            <img src="/resources/before/img/er.png" alt="" class="img2" style="display: none"/>
            <br/>
            <br/>
            <span style="opacity: 0">充值账号：</span><input type="button" value="确认充值" id="confirmTop" class="btn_ok" onclick="topup()"/>
        </div>
        <hr style="width: 80%;margin:0 auto;border:0;border-bottom: 1px solid #e8e8e8;" />
        <div class="right_bottom">
            <img src="/resources/before/img/bg-text.png" alt="" style="margin-left: -50px;"/>
        </div>
    </div>
    <div class="right2" style="display: none">
        <div class="right2_top">
            <span>现有竞猜币：</span><span>${account.accountAssets.victoryMoney}</span><br/><br/>
            <span>体现金额：</span><input type="text" style="width: 125px;height: 30px;margin-bottom: 20px;text-align: center"/> 元<span>(1元=10积分)</span><br/>
            <span>体现方式：</span><div class="xg2_input active xg_input_in">微信支付<img src="/resources/before/img/before.png" class="before"/></div><div class="xg2_input2 xg_input_in">支付宝支付<img src="/resources/before/img/before.png" class="before"/></div><br/><br/>
            <span>充值账号：</span><input type="text" style="width: 256px;height: 30px;margin-bottom: 20px;text-align: center"/>
            <br/>
            <br/>
            <span style="opacity: 0">充值账号：</span><input type="button" value="确认提现" class="btn_ok" onclick="alert('请耐心等到2016.10.31进行提现操作')"/>
        </div>
        <hr style="width: 80%;margin:0 auto;border:0;border-bottom: 1px solid #e8e8e8;" />
        <div class="right2_bottom">
            <img src="/resources/before/img/bg-text.png" alt="" style="margin-left: -50px;"/>
        </div>
    </div>
    <div style="clear: both"></div>
</div>
</body>
<script type="text/javascript" src="/resources/before/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/resources/before/js/js.js"></script>
</html>