/**
 * 2016/9/22 11:31:44 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.lolluckyman.business.teamplayer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 战队队员信息
 * Created by lenovo on 2016/09/22.
 */
public class TeamPlayer implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -3526059195178588672L;

    // 队员Code [主键]
    private String code;
    // 中文名字
    private String chinaName;
    // 外文名字
    private String englishName;
    // 外号
    private String alias;
    // 战队位置
    private String teamLocation;
    // 所属战队
    private String temaCode;
    // 擅长位置
    private String skilledLocation;
    // 擅长的英雄
    private String skilledHero;
    // 队员简介
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 添加时间
    private Date createTime;

    /** 
     * 获取队员Code [主键]
     * 
     * @return 队员Code
     */
    public String getCode() {
        return code;
    }

    /** 
     * 设置队员Code [主键]
     * 
     * @param code 队员Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * 获取中文名字
     * 
     * @return 中文名字
     */
    public String getChinaName() {
        return chinaName;
    }

    /** 
     * 设置中文名字
     * 
     * @param chinaName 中文名字
     */
    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }

    /** 
     * 获取外文名字
     * 
     * @return 外文名字
     */
    public String getEnglishName() {
        return englishName;
    }

    /** 
     * 设置外文名字
     * 
     * @param englishName 外文名字
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /** 
     * 获取外号
     * 
     * @return 外号
     */
    public String getAlias() {
        return alias;
    }

    /** 
     * 设置外号
     * 
     * @param alias 外号
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /** 
     * 获取战队位置
     * 
     * @return 战队位置
     */
    public String getTeamLocation() {
        return teamLocation;
    }

    /** 
     * 设置战队位置
     * 
     * @param teamLocation 战队位置
     */
    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    /** 
     * 获取所属战队
     * 
     * @return 所属战队
     */
    public String getTemaCode() {
        return temaCode;
    }

    /** 
     * 设置所属战队
     * 
     * @param temaCode 所属战队
     */
    public void setTemaCode(String temaCode) {
        this.temaCode = temaCode;
    }

    /** 
     * 获取擅长位置
     * 
     * @return 擅长位置
     */
    public String getSkilledLocation() {
        return skilledLocation;
    }

    /** 
     * 设置擅长位置
     * 
     * @param skilledLocation 擅长位置
     */
    public void setSkilledLocation(String skilledLocation) {
        this.skilledLocation = skilledLocation;
    }

    /** 
     * 获取擅长的英雄
     * 
     * @return 擅长的英雄
     */
    public String getSkilledHero() {
        return skilledHero;
    }

    /** 
     * 设置擅长的英雄
     * 
     * @param skilledHero 擅长的英雄
     */
    public void setSkilledHero(String skilledHero) {
        this.skilledHero = skilledHero;
    }

    /** 
     * 获取队员简介
     * 
     * @return 队员简介
     */
    public String getDescription() {
        return description;
    }

    /** 
     * 设置队员简介
     * 
     * @param description 队员简介
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
