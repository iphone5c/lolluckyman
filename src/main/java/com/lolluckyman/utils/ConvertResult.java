package com.lolluckyman.utils;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
public class ConvertResult {
    private boolean success;
    private Object result;

    private ConvertResult() {
    }

    /**
     * 创建一个转换成功的实例
     *
     * @param result 转换结果
     * @return 返回success=true的结果
     */
    public static ConvertResult NewSuccess(Object result) {
        ConvertResult obj = new ConvertResult();
        obj.success = true;
        obj.result = result;
        return obj;
    }

    /**
     * 创建一个转换失败的实例
     *
     * @return 返回success=false的结果
     */
    public static ConvertResult NewFail() {
        ConvertResult obj = new ConvertResult();
        obj.success = false;
        return obj;
    }

    /**
     * 如果success=true的情况下，获取转换结果
     *
     * @return 转换结果
     */
    public Object getResult() {
        return result;
    }

    /**
     * 是否转换成功
     *
     * @return 成功返回true, 否则返回false.
     */
    public boolean isSuccess() {
        return success;
    }
}
