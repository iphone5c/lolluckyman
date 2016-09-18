package com.lolluckyman.filter;

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
        return true;

//        if (requestPath.contains("/back/business/mainApp") || requestPath.contains("/back/business/publicController"))
//        {
//            response.setHeader("statusFlag","1000");
//            return true;
//        }

//        //判断用户是否已经登录
//        User user= LolUtils.getCurrentUser(request);
//        if (user==null){
//            System.out.println("用户没有登录，请登录");
//            response.setHeader("statusFlag","1002");
//            return false;
//        }else {
//            ///权限判断
//            //获得当前登录用户的所有权限
//            List<Right> userRigthList=rightService.getRightListByUser(user.getUserId());
//            boolean flag=false;
//            for (Right right:userRigthList){
//                if (requestPath.equals(right.getRightReallyUrl())||hasRightByPath(right.getRightId(),requestPath)){
//                    flag=true;
//                    break;
//                }
//            }
//            if (!flag){
//                System.out.println("你没有操作权限，请联系管理员。。。");
//                response.setHeader("statusFlag","1003");
//                return false;
//            }
//            return true;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
