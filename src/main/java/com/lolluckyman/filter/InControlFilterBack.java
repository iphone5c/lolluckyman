package com.lolluckyman.filter;

import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.utils.cmd.LolUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局权限控制器
 * Created by 魏源 on 2015/7/28 0028.
 */
public class InControlFilterBack implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("path:"+requestPath);

        if (
                requestPath.contains("/back/admin/loginStatus")
                || requestPath.contains("/back/admin/login")
            )
        {
//            response.setHeader("statusFlag","1000");
            return true;
        }

        //判断用户是否已经登录
        Admin admin = LolUtils.getCurrentAdmin(request);
        if (admin==null){
            System.out.println("用户没有登录，请登录");
            response.setHeader("statusFlag","1002");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
