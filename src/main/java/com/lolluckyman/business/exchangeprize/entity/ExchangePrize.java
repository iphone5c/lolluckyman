/**
 * 2016/9/23 16:25:27 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.lolluckyman.business.exchangeprize.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lolluckyman.business.exchangeprize.entity.em.ConsumptionCurrency;
import com.lolluckyman.business.exchangeprize.entity.em.ExchangeStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 奖品兑换信息
 * Created by lenovo on 2016/09/23.
 */
public class ExchangePrize implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -3349537552962795520L;

    // 奖品兑换code [主键]
    private String code;
    // 兑换的奖品code
    private String prizeCode;
    // 申请兑换的数量
    private Integer applyExchangePrizeNum;
    // 实际到账数量
    private Integer exchangePrizeNum;
    // 消费币种
    private ConsumptionCurrency consumptionCurrency;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 申请兑换时间
    private Date applyExchangeTime;
    // 兑换状态
    private ExchangeStatus exchangeStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 处理时间
    private Date disposalTime;
    // 充值账号
    private String gameAccount;
    // 申请兑换账户code
    private String accountCode;

    /** 
     * 获取奖品兑换code [主键]
     * 
     * @return 奖品兑换code
     */
    public String getCode() {
        return code;
    }

    /** 
     * 设置奖品兑换code [主键]
     * 
     * @param code 奖品兑换code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * 获取兑换的奖品code
     * 
     * @return 兑换的奖品code
     */
    public String getPrizeCode() {
        return prizeCode;
    }

    /** 
     * 设置兑换的奖品code
     * 
     * @param prizeCode 兑换的奖品code
     */
    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }

    /** 
     * 获取申请兑换的数量
     * 
     * @return 申请兑换的数量
     */
    public Integer getApplyExchangePrizeNum() {
        return applyExchangePrizeNum;
    }

    /** 
     * 设置申请兑换的数量
     * 
     * @param applyExchangePrizeNum 申请兑换的数量
     */
    public void setApplyExchangePrizeNum(Integer applyExchangePrizeNum) {
        this.applyExchangePrizeNum = applyExchangePrizeNum;
    }

    /** 
     * 获取实际到账数量
     * 
     * @return 实际到账数量
     */
    public Integer getExchangePrizeNum() {
        return exchangePrizeNum;
    }

    /** 
     * 设置实际到账数量
     * 
     * @param exchangePrizeNum 实际到账数量
     */
    public void setExchangePrizeNum(Integer exchangePrizeNum) {
        this.exchangePrizeNum = exchangePrizeNum;
    }

    /** 
     * 获取消费币种
     * 
     * @return 消费币种
     */
    public ConsumptionCurrency getConsumptionCurrency() {
        return consumptionCurrency;
    }

    /** 
     * 设置消费币种
     * 
     * @param consumptionCurrency 消费币种
     */
    public void setConsumptionCurrency(ConsumptionCurrency consumptionCurrency) {
        this.consumptionCurrency = consumptionCurrency;
    }

    /** 
     * 获取申请兑换时间
     * 
     * @return 申请兑换时间
     */
    public Date getApplyExchangeTime() {
        return applyExchangeTime;
    }

    /** 
     * 设置申请兑换时间
     * 
     * @param applyExchangeTime 申请兑换时间
     */
    public void setApplyExchangeTime(Date applyExchangeTime) {
        this.applyExchangeTime = applyExchangeTime;
    }

    /** 
     * 获取兑换状态
     * 
     * @return 兑换状态
     */
    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    /** 
     * 设置兑换状态
     * 
     * @param exchangeStatus 兑换状态
     */
    public void setExchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    /** 
     * 获取处理时间
     * 
     * @return 处理时间
     */
    public Date getDisposalTime() {
        return disposalTime;
    }

    /** 
     * 设置处理时间
     * 
     * @param disposalTime 处理时间
     */
    public void setDisposalTime(Date disposalTime) {
        this.disposalTime = disposalTime;
    }

    /** 
     * 获取充值账号
     * 
     * @return 充值账号
     */
    public String getGameAccount() {
        return gameAccount;
    }

    /** 
     * 设置充值账号
     * 
     * @param gameAccount 充值账号
     */
    public void setGameAccount(String gameAccount) {
        this.gameAccount = gameAccount;
    }

    /** 
     * 获取申请兑换账户code
     * 
     * @return 申请兑换账户code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /** 
     * 设置申请兑换账户code
     * 
     * @param accountCode 申请兑换账户code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

}
