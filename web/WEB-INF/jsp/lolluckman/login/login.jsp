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
    <link rel="stylesheet" type="text/css" href="/resources/before/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/component.css" />
    <link rel="stylesheet" type="text/css" href="/resources/before/css/content.css" />
    <script src="/resources/before/js/jquery-3.1.0.min.js"></script>
    <script src="/resources/before/js/modernizr.custom.js"></script>
    <script src="/resources/before/lol/common.js"></script>
    <script src="/resources/before/lol/login.js"></script>
</head>
<body>
<div class="mockup-content">
    <div class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
        <button type="button">登陆</button>
        <div class="morph-content">
            <div>
                <div class="content-style-form content-style-form-1">
                    <img src="/resources/before/img/cloes.png" alt="" class="icon icon-close" style="width: 15px;height: 15px;position: absolute;right: 10px;top: -10px;"/>
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
    <strong class="joiner">or</strong>
    <div class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
        <button type="button">注册</button>
        <div class="morph-content">
            <div>
                <div class="content-style-form content-style-form-2">
                    <img src="/resources/before/img/cloes.png" alt="" class="icon icon-close" style="width: 15px;height: 15px;position: absolute;right: 10px;top: -10px;"/>
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
<script src="/resources/before/js/classie.js"></script>
<script src="/resources/before/js/uiMorphingButton_fixed.js"></script>
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
    })();
</script>
</body>
</html>