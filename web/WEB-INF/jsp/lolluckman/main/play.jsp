<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/10/1
  Time: 4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="restrainCode" value="${restrainCode}">
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
    <img style="float: left;margin-top: 40px" src="${competition.teamA.teamphone}">
    <img src="/resources/before/img/vs-icon.png">
    <img style="float: right;margin-top: 40px" src="${competition.teamB.teamphone}">
</div>
<div class="gameplayText">
    <img src="/resources/before/img/gameplayText.png">
</div>
<div id="playGame">

</div>
<div style="clear: both"></div>
<div class="confirm">
    <input type="hidden" id="title">
    <input type="hidden" id="info">
    <input type="hidden" id="selectPlayRecord">
    <img src="/resources/before/img/confirm.png" onclick="confirmSelect()">
</div>

<script>
    loadData();
</script>