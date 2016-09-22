/**
 * 2016/9/21 21:18:56 魏源 created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.lolluckyman.business.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lolluckyman.business.account.entity.em.AccountStatus;
import com.lolluckyman.business.account.entity.em.Problem;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 账户信息
 * Created by 魏源 on 2016/09/21.
 */
public class Account implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -6787381389544988672L;

    // 账户code [主键]
    private String code;
    // 会员账号
    private String loginAccount;
    // 会员密码
    private String password;
    // 真实姓名
    private String realName;
    // 联系电话
    private String phone;
    // 邮箱地址
    private String email;
    // QQ
    private String qq;
    // 密码问题
    private Problem passwordProblem;
    // 密码答案
    private String passwordAnswer;
    // 取款密码
    private String withdrawalsPassword;
    // 账户状态
    private AccountStatus accountStatus;
    // 账户描述
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 添加时间
    private Date createTime;

    /** 
     * 获取账户code [主键]
     * 
     * @return 账户code
     */
    public String getCode() {
        return code;
    }

    /** 
     * 设置账户code [主键]
     * 
     * @param code 账户code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * 获取会员账号
     * 
     * @return 会员账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /** 
     * 设置会员账号
     * 
     * @param loginAccount 会员账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    /** 
     * 获取会员密码
     * 
     * @return 会员密码
     */
    public String getPassword() {
        return password;
    }

    /** 
     * 设置会员密码
     * 
     * @param password 会员密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * 获取真实姓名
     * 
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /** 
     * 设置真实姓名
     * 
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /** 
     * 获取联系电话
     * 
     * @return 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /** 
     * 设置联系电话
     * 
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 
     * 获取邮箱地址
     * 
     * @return 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /** 
     * 设置邮箱地址
     * 
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     * 获取QQ
     * 
     * @return QQ
     */
    public String getQq() {
        return qq;
    }

    /** 
     * 设置QQ
     * 
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /** 
     * 获取密码问题
     * 
     * @return 密码问题
     */
    public Problem getPasswordProblem() {
        return passwordProblem;
    }

    /** 
     * 设置密码问题
     * 
     * @param passwordProblem 密码问题
     */
    public void setPasswordProblem(Problem passwordProblem) {
        this.passwordProblem = passwordProblem;
    }

    /** 
     * 获取密码答案
     * 
     * @return 密码答案
     */
    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    /** 
     * 设置密码答案
     * 
     * @param passwordAnswer 密码答案
     */
    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }

    /** 
     * 获取取款密码
     * 
     * @return 取款密码
     */
    public String getWithdrawalsPassword() {
        return withdrawalsPassword;
    }

    /** 
     * 设置取款密码
     * 
     * @param withdrawalsPassword 取款密码
     */
    public void setWithdrawalsPassword(String withdrawalsPassword) {
        this.withdrawalsPassword = withdrawalsPassword;
    }

    /** 
     * 获取账户状态
     * 
     * @return 账户状态
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /** 
     * 设置账户状态
     * 
     * @param accountStatus 账户状态
     */
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /** 
     * 获取账户描述
     * 
     * @return 账户描述
     */
    public String getDesc() {
        return description;
    }

    /** 
     * 设置账户描述
     * 
     * @param description 账户描述
     */
    public void setDesc(String description) {
        this.description = description;
    }

    /** 
     * 获取添加时间
     * 
     * @return 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置添加时间
     * 
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
