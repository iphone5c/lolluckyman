package com.lolluckyman.filter;

import com.alibaba.fastjson.JSON;
import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseDto;
import com.lolluckyman.utils.core.Dto;
import com.lolluckyman.utils.core.LolExceptionCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局权限控制器
 * Created by 魏源 on 2015/7/28 0028.
 */
public class InControlFilterBefore implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().toString();
        System.out.println("前端path:"+requestPath);

        if (
                requestPath.contains("/lol/account/loginStatus")
                || requestPath.contains("/lol/account/login")
            )
        {
            return true;
        }

        //判断用户是否已经登录
        Account account = LolUtils.getCurrentAccount(request);
        if (account==null){
            System.out.println("用户没有登录，请登录");
            this.validateResult(response,9999,"用户没有登录，请登录");
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

    /**
     * 验证结果返回客户端
     * @param statusCode
     * @param response
     */
    private void validateResult(HttpServletResponse response,int statusCode,String errorMessage) {
        Dto result = new BaseDto();
        result.put("result", "");
        result.put("errorMessage", errorMessage);
        result.put("statusCode", statusCode);
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 验证结果返回客户端
     * @param obj
     * @param response
     */
    private void result(HttpServletResponse response,Object obj) {
        Dto result = new BaseDto();
        result.put("result", obj);
        result.put("errorMessage", "");
        result.put("statusCode", LolExceptionCode.OK);
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
