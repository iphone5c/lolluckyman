package com.lolluckyman.utils.core;

import com.lolluckyman.utils.cmd.LolUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CommonController {
    protected Dto success(Object obj) {
        Dto result = new BaseDto();
        result.put("result", obj);
        result.put("success", true);
        return result;
    }

    protected Dto pagingSuccess(Object obj, Integer totalCounts){
        Dto end = new BaseDto();
        end.put("result",obj);
        end.put("totalCounts",totalCounts);
        end.put("success",true);
        return end;
    }

    protected Dto failure(String msg) {
        Dto result = new BaseDto();
        result.put("errorMessage", LolUtils.isEmptyOrNull(msg)?"连接失败,请重试!":msg);
        result.put("success", false);
        return result;
    }

    protected Dto getParams(HttpServletRequest request) {
        Dto dto = new BaseDto();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String value = request.getParameter(name);
            if (name.equals("limit")){
                dto.put(name, Integer.parseInt(value));
            }else if (name.equals("page")){
                dto.put(name, Integer.parseInt(value));
            }else {
                dto.put(name, value);
            }
        }
        return dto;
    }
}
