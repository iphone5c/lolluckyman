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
     * 获取一个新的用户编码(规则：年月日时分秒+4位序列号)
     *
     * @return 新的用户编码
     */
    @Override
    public String getUserCode() {
        StringBuilder builder = new StringBuilder();
        builder.append(LolConvert.dateToString(new Date(),LolConvert.DATEFORMAT_DATA_EN_ALL));
        builder.append(seriaNumService.getNewSerialNumByString(ECodeType.USER_CODE.name(),4));
        return builder.toString();
    }
}