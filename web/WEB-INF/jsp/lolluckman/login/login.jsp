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
                    <span class="icon icon-close"></span>
                    <h3 style="text-align:center;padding-top: 50px;">LOL竞猜登陆</h3>
                    <form>
                        <p><label>账号</label><input type="text" id="loginAccount" /></p>
                        <p><label>密码</label><input type="password" id="password"/></p>
                        <p><button class="btn_login" onclick="submitLogin()">Login</button></p>
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
                    <span class="icon icon-close">Close the dialog</span>
                    <h3 style="text-align:center;padding-top: 50px;">LOL竞猜注册</h3>
                    <form>
                        <p><label>会员账号</label><input type="text" id="loginName"/></p>
                        <p><label>会员密码</label><input type="password" id="loginPassword"/></p>
                        <p><label>确认密码</label><input type="password" id="repassword"/></p>
                        <p><label>取款密码</label><input type="password" id="withdrawalsPassword"/></p>
                        <p><label>验证码</label><input type="text" id="securityCode"/></p>
                        <p><label></label><img src="/lol/account/jsp/getKaptchaImage" onclick="getKaptchaImage(this)"></p>
                        <p><button class="btn_register" onclick="submitSignup()">Sign Up</button></p>
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
