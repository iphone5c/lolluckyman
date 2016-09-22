package com.lolluckyman.business.codebuilder;

import com.lolluckyman.business.serianum.service.ISeriaNumService;
import com.lolluckyman.utils.LolConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 魏源 on 2015/7/15 0015.
 */
@Transactional
@Service("codeBuilder")
public class CodeBuilder implements ICodeBuilder{

    @Autowired
    private ISeriaNumService seriaNumService;

    private String fillLeftToString(int value, int len) {
        return fillLeftToString(LolConvert.intToString(value), len);
    }

    private String fillLeftToString(String value, int len) {
        StringBuilder result = new StringBuilder();
        result.append(value);
        while (result.length() < len)
            result.insert(0, '0');
        return result.toString();
    }

    private int getSignBit(StringBuilder builder) {
        int result = 0;
        for (int i = 0; i < builder.length(); i++)
            result += LolConvert.toInt(LolConvert.charToString(builder.charAt(i)));
        return result % 10;
    }

    /**
     * 获取一个新的管理员编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的管理员编码
     */
    @Override
    public String getAdminCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.USER_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的系统参数配置编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的系统参数配置编码
     */
    @Override
    public String getSystemParamsCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.SYSTEMPARAMS_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的账户资产编码(规则：年月日时分秒+6位序列号)
     *
     * @return 新的账户资产编码
     */
    @Override
    public String getAccountAssetsCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.ACCOUNTASSETS_CODE.name(),6));
        return builder.toString();
    }

    /**
     * 获取一个新的奖品编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的奖品编码
     */
    @Override
    public String getPrizeCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.PRIZE_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的战队编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的战队编码
     */
    @Override
    public String getTeamCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.TEAM_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的战队成员编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的战队成员编码
     */
    @Override
    public String getTeamPlayerCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.TEAMPLAYER_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的赛事信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的赛事信息编码
     */
    @Override
    public String getCompetitionCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.COMPETITION_CODE.name(),4));
        return builder.toString();
    }

    /**
     * 获取一个新的局数信息编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的局数信息编码
     */
    @Override
    public String getRestrainCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.RESTRAIN_CODE.name(),4));
        return builder.toString();
    }
}
