package com.lolluckyman.business.codebuilder;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
public interface ICodeBuilder {
    /**
     * 获取一个新的管理员编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的管理员编码
     */
    String getAdminCode();

    /**
     * 获取一个新的系统参数配置编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的系统参数配置编码
     */
    String getSystemParamsCode();

    /**
     * 获取一个新的账户资产编码(规则：年月日时分秒+6位序列号)
     *
     * @return 新的账户资产编码
     */
    String getAccountAssetsCode();
}
