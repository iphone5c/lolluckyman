package com.lolluckyman.business.playrecord.service;

import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.business.playrecord.entity.em.PlayRecordStatus;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IPlayRecordService {

    /**
     *获取联盟玩法信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<PlayRecord> getPlayRecordPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取联盟玩法信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<PlayRecord> getPlayRecordList(QueryParams wheres);

    /**
     * 根据联盟玩法信息code查找联盟玩法信息
     * @param playRecordCode 联盟玩法信息code
     * @return 联盟玩法信息对象
     */
    public PlayRecord getPlayRecordByCode(String playRecordCode);

    /**
     * 添加联盟玩法信息信息
     * @param playRecord 联盟玩法信息对象
     * @return 联盟玩法信息对象
     */
    public PlayRecord createPlayRecord(PlayRecord playRecord);

    /**
     * 修改联盟玩法信息信息
     * @param playRecord 联盟玩法信息对象
     * @return 联盟玩法信息对象
     */
    public PlayRecord updatePlayRecord(PlayRecord playRecord);

    /**
     * 根据code删除指定联盟玩法信息
     * @param code 联盟玩法信息code
     * @return true表示成功 false表示失败
     */
    public Boolean deletePlayRecord(String code);

    /**
     * 操作指定联盟玩法状态
     * @param code 联盟玩法code
     * @param playRecordStatus 联盟玩法状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationPlayRecordStatus(String code, PlayRecordStatus playRecordStatus);

}
