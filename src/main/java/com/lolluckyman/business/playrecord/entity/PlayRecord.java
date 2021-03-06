/**
 * 2016/9/22 18:41:35 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.lolluckyman.business.playrecord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lolluckyman.business.playrecord.entity.em.Play;
import com.lolluckyman.business.playrecord.entity.em.PlayRecordStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 联盟玩法
 * Created by lenovo on 2016/09/22.
 */
public class PlayRecord implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -2404883668015191040L;

    // 联盟玩法记录code [主键]
    private String code;
    // 外键（比赛外键、局数外键）
    private String foreignKey;
    // 玩法
    private Play play;
    // 赔率内容JSON格式字符串
    private String content;
    // 竞猜获胜方
    private String success;
    // 结果描述
    private String resultDesc;
    // 玩法状态
    private PlayRecordStatus playRecordStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 添加时间
    private Date createTime;

    /**
     * 获取联盟玩法记录code [主键]
     *
     * @return 联盟玩法记录code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置联盟玩法记录code [主键]
     *
     * @param code 联盟玩法记录code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取外键（比赛外键、局数外键）
     *
     * @return 外键（比赛外键、局数外键）
     */
    public String getForeignKey() {
        return foreignKey;
    }

    /**
     * 设置外键（比赛外键、局数外键）
     *
     * @param foreignKey 外键（比赛外键、局数外键）
     */
    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    /**
     * 获取玩法
     *
     * @return 玩法
     */
    public Play getPlay() {
        return play;
    }

    /**
     * 设置玩法
     *
     * @param play 玩法
     */
    public void setPlay(Play play) {
        this.play = play;
    }

    /**
     * 获取赔率内容JSON格式字符串
     *
     * @return 赔率内容JSON格式字符串
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置赔率内容JSON格式字符串
     *
     * @param content 赔率内容JSON格式字符串
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取竞猜获胜方
     *
     * @return 竞猜获胜方
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 设置竞猜获胜方
     *
     * @param success 竞猜获胜方
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 获取结果描述
     *
     * @return 结果描述
     */
    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * 设置结果描述
     *
     * @param resultDesc 结果描述
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    /**
     * 获取玩法状态
     *
     * @return 玩法状态
     */
    public PlayRecordStatus getPlayRecordStatus() {
        return playRecordStatus;
    }

    /**
     * 设置玩法状态
     *
     * @param playRecordStatus 玩法状态
     */
    public void setPlayRecordStatus(PlayRecordStatus playRecordStatus) {
        this.playRecordStatus = playRecordStatus;
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
