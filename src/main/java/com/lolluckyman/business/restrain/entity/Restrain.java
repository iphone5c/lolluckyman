/**
 * 2016/9/22 19:05:54 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.lolluckyman.business.restrain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lolluckyman.business.restrain.entity.em.RestrainStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 比赛局数信息
 * Created by lenovo on 2016/09/22.
 */
public class Restrain implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -8262290921184369664L;

    // 局数code [主键]
    private String code;
    // 比赛code外键
    private String competitionCode;
    // 局数顺序
    private int restrainNum;
    // 局数状态
    private RestrainStatus restrainStatus;
    // 局数描述
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 添加时间
    private Date createTime;

    /** 
     * 获取局数code [主键]
     * 
     * @return 局数code
     */
    public String getCode() {
        return code;
    }

    /** 
     * 设置局数code [主键]
     * 
     * @param code 局数code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * 获取比赛code外键
     * 
     * @return 比赛code外键
     */
    public String getCompetitionCode() {
        return competitionCode;
    }

    /** 
     * 设置比赛code外键
     * 
     * @param competitionCode 比赛code外键
     */
    public void setCompetitionCode(String competitionCode) {
        this.competitionCode = competitionCode;
    }

    /** 
     * 获取局数顺序
     * 
     * @return 局数顺序
     */
    public int getRestrainNum() {
        return restrainNum;
    }

    /** 
     * 设置局数顺序
     * 
     * @param restrainNum 局数顺序
     */
    public void setRestrainNum(int restrainNum) {
        this.restrainNum = restrainNum;
    }

    /** 
     * 获取局数状态
     * 
     * @return 局数状态
     */
    public RestrainStatus getRestrainStatus() {
        return restrainStatus;
    }

    /** 
     * 设置局数状态
     * 
     * @param restrainStatus 局数状态
     */
    public void setRestrainStatus(RestrainStatus restrainStatus) {
        this.restrainStatus = restrainStatus;
    }

    /** 
     * 获取局数描述
     * 
     * @return 局数描述
     */
    public String getDescription() {
        return description;
    }

    /** 
     * 设置局数描述
     * 
     * @param description 局数描述
     */
    public void setDescription(String description) {
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