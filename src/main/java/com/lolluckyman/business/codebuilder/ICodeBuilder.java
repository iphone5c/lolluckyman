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

    /**
     * 获取一个新的账户编码(规则：年月日时分秒+6位序列号)
     *
     * @return 新的账户编码
     */
    String getAccountCode();

    /**
     * 获取一个新的奖品编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的奖品编码
     */
    String getPrizeCode();

    /**
     * 获取一个新的战队编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的战队编码
     */
    String getTeamCode();

    /**
     * 获取一个新的战队成员编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的战队成员编码
     */
    String getTeamPlayerCode();

    /**
     * 获取一个新的赛事信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的赛事信息编码
     */
    String getCompetitionCode();

    /**
     * 获取一个新的局数信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的局数信息编码
     */
    String getRestrainCode();

    /**
     * 获取一个新的玩法信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的玩法信息编码
     */
    String getPlayRecordCode();

    /**
     * 获取一个新的充值提现信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的充值提现信息编码
     */
    String getTopUpWithdrawalCode();
}
