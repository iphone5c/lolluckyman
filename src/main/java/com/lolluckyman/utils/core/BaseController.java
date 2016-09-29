package com.lolluckyman.utils.core;

import com.lolluckyman.utils.cmd.LolUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/2.
 */
public class BaseController {

    public Logger log = Logger.getLogger(BaseController.class);//日志

    /**
     * 所有Controller层通过此方法返回数据给客户端
     * @param obj 返回的结果集
     * @return
     */
    protected ResponseEntity<Object> result(Object obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        Dto result = new BaseDto();
        result.put("result", obj);
        result.put("errorMessage", "");
        result.put("statusCode", LolExceptionCode.OK);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(result,headers, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 所有Controller层通过此方法将验证的错误提示信息返回给客户端
     * @param statusCode 返回状态码
     * @param info 验证提示信息
     * @return
     */
    protected ResponseEntity<Object> validationResult(int statusCode,String info) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        Dto result = new BaseDto();
        result.put("result", "");
        result.put("errorMessage", info);
        result.put("statusCode", statusCode);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(result,headers, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 跨域请求返回的JSONP格式
     * @param request
     * @param response
     */
    protected void resultJSONP(HttpServletRequest request,HttpServletResponse response,Object obj){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        String callbackFunName =request.getParameter("callback");//得到js函数名称
        try {
            Dto result = new BaseDto();
            result.put("result", obj);
            result.put("errorMessage", "");
            result.put("statusCode", LolExceptionCode.OK);

            response.getWriter().write(callbackFunName + "("+ LolUtils.getJsonSerialize(result)+")"); //返回jsonp数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跨域请求返回的JSONP格式,验证信息返回
     * @param request
     * @param response
     */
    protected void validationResultJSONP(HttpServletRequest request,HttpServletResponse response,int statusCode,String info){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        String callbackFunName =request.getParameter("callback");//得到js函数名称
        try {
            Dto result = new BaseDto();
            result.put("result", "");
            result.put("errorMessage", info);
            result.put("statusCode", statusCode);

            response.getWriter().write(callbackFunName + "("+ LolUtils.getJsonSerialize(result)+")"); //返回jsonp数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务端所有的异常处理方法
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Object exception( HttpServletRequest request ,HttpServletResponse response, Exception ex ) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        String callbackFunName =request.getParameter("callback");//得到js函数名称

        Dto result = new BaseDto();
        result.put("result", "");
        if(ex instanceof IllegalArgumentException){
            IllegalArgumentException ycRuntimeException= (IllegalArgumentException) ex;
            result.put("errorMessage", ycRuntimeException.getMessage());
            result.put("statusCode", LolExceptionCode.BUSINESS_EXCEPTION);
            log.error("异常状态码："+LolExceptionCode.BUSINESS_EXCEPTION+"，异常信息："+ycRuntimeException.getMessage());
        }else {
            result.put("errorMessage", "系统错误");
            result.put("statusCode", LolExceptionCode.PROGRAM_EXCEPTION);
            log.error("异常状态码："+LolExceptionCode.PROGRAM_EXCEPTION+"，异常信息："+ex);
        }

        if (LolUtils.isEmptyOrNull(callbackFunName)){
            return result;
        }else if (callbackFunName.equals("web")){
            try {
                response.getWriter().write(callbackFunName + "("+ LolUtils.getJsonSerialize(result)+")"); //返回jsonp数据
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return result;
    }

}
