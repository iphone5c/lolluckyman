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
            <li class="${(status!=1&&status!=2&&status!=3)?'active':''}" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList'">所有订单</li>
            <li class="${(status==2)?'active':''}" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList?status=2'">未结算</li>
            <li class="${(status==1)?'active':''}" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList?status=1'">已结算</li>
            <li class="${(status==3)?'active':''}" onclick="window.location.href='/lol/bettingrecord/jsp/getBettingRecordList?status=3'">已撤销</li>
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
            </thead>
            <tbody>
                <c:forEach var="v" items="${bettingRecordList}">
                    <tr>
                        <td class="first_td">
                            <div style="margin-bottom: -45px;"><fmt:formatDate value="${v.competition.gameStartTime }" pattern="yyyy-MM-dd" /></div>
                            <img src=" ${v.teamA.teamphone }" alt="" style="width: 80px;height: 80px;"/>
                            <span class="f1"> ${v.teamA.chinaName } </span>
                            <span class="f2">VS</span>
                            <span class="f1"> ${v.teamB.chinaName } </span>
                            <img src="${v.teamB.teamphone }" alt="" style="width: 80px;height: 80px;"/>
                        </td>
                        <td>${v.bettingRecord.bettingRecordStatus}</td>
                        <td>${v.playRecord.play}</td>
                        <td>你选择了<br><span>"${v.playRecordResult}"</span></td>
                        <td>
                            <c:if test="${v.bettingRecord.bettingRecordResult=='输'}">
                                <button class="btn_record_no">竞猜失败</button>
                            </c:if>
                            <c:if test="${v.bettingRecord.bettingRecordResult=='赢'}">
                                <button class="btn_record_ok">竞猜成功</button>
                            </c:if>
                            <c:if test="${v.bettingRecord.bettingRecordResult=='等待结果'}">
                                等待处理结果
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="/resources/before/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/resources/before/js/js.js"></script>
</html>