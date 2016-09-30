package com.lolluckyman.filter;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.utils.cmd.LolUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局权限控制器
 * Created by 魏源 on 2015/7/28 0028.
 */
public class InControlFilterBefore implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse ;

        String requestPath = request.getRequestURI().trim() ;
        String url = request.getQueryString();
        System.out.println("前端path:"+requestPath);

        //不接受任何jsp请求
        if(requestPath.endsWith(".jsp")){
            return ;
        }

        //防止xss攻击
        if (url != null && url.length() > 0 &&
                (url.indexOf('>') != -1 ||
                        url.indexOf('<') != -1 ||
                        url.indexOf('"') != -1 ||
                        url.replace(" ","").indexOf("<script") != -1) ){
            return;
        }

        //只拦截.html结尾的请求
        if(!requestPath.startsWith("/lol/")){
            //添加安全Header
            response.setHeader("X-XSS-Protection", "1; mode=block");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            filterChain.doFilter(request, response) ;
            return ;
        }

        if (
                requestPath.startsWith("/lol/account/jsp/loginHtml")
                || requestPath.startsWith("/lol/account/json/login")
                || requestPath.startsWith("/lol/account/json/registerAccount")
                || requestPath.startsWith("/lol/account/jsp/getKaptchaImage")
            )
        {
            //添加安全Header
            response.setHeader("X-XSS-Protection", "1; mode=block");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            filterChain.doFilter(request, response) ;
            return ;
        }

        //判断用户是否已经登录
        Account account = LolUtils.getCurrentAccount(request);
        if (account==null){
            response.sendRedirect("/lol/account/jsp/loginHtml") ;
            return ;
        }
        filterChain.doFilter(request, response) ;
        return ;
    }

    @Override
    public void destroy() {

    }
}
