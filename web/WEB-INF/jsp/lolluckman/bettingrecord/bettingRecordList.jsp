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
    <link rel="stylesheet" href="/resources/before/css/record.css"/>
    <script type="text/javascript" src="/resources/before/lol/common.js"></script>
</head>
<body>
<div class="allcontent">
    <span>您现在的位置是 ></span><span style="color:#d29031;">我的竞猜记录</span>
    <div class="content" style="margin-top: 20px;">
        <h3>我的订单</h3>
        <ul class="record_ul">
            <li class="active">所有订单</li>
            <li>未结算</li>
            <li>已结算</li>
            <li>已撤销</li>
            <div style="clear: both"></div>
        </ul>
        <ul class="record_ul_title">
            <li style="width: 40%">战队名称</li>
            <li>订单状态</li>
            <li>竞猜玩法</li>
            <li>选择结果</li>
            <li>竞猜结果</li>
            <div style="clear: both"></div>
        </ul>
        <table class="record_table" border="1" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <td colspan="5">9月30日(星期五)</td>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${bettingRecordPageList.list}">
                    <tr>
                        <td class="first_td">
                            <img src="/resources/before/img/d1.png" alt=""/>
                            <span class="f1"> G2 </span>
                            <span class="f2">VS</span>
                            <span class="f1"> CLG </span>
                            <img src="/resources/before/img/d2.png" alt=""/>
                        </td>
                        <td>${v.bettingRecordStatus}</td>
                        <td>${v.playRecordCode}</td>
                        <td>你选择了<br><span>"${v.betting}"</span></td>
                        <td>
                            <c:if test="${v.bettingRecordResult=='输'}">
                                <button class="btn_record_no">竞猜失败</button>
                            </c:if>
                            <c:if test="${v.bettingRecordResult=='赢'}">
                                <button class="btn_record_ok">竞猜成功</button>
                            </c:if>
                            <c:if test="${v.bettingRecordResult=='等待结果'}">
                                等待处理结果
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="xg_F">
            <span class="f3">上一页</span>
            <ul class="F_ul">
                <li class="active">1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li>5</li>
                <li>6</li>
            </ul>
            <span class="f3">下一页</span>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/resources/before/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/resources/before/js/js.js"></script>
</html>