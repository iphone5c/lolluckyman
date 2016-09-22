package com.lolluckyman.business.restrain.service;

import com.lolluckyman.business.restrain.entity.Restrain;
import com.lolluckyman.business.restrain.entity.em.RestrainStatus;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IRestrainService {

    /**
     *获取局数信息分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Restrain> getRestrainPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     *获取局数信息列表
     * @param wheres    条件
     * @return 对象列表
     */
    public List<Restrain> getRestrainList(QueryParams wheres);

    /**
     * 根据局数信息code查找局数信息
     * @param restrainCode 局数信息code
     * @return 局数信息对象
     */
    public Restrain getRestrainByCode(String restrainCode);

    /**
     * 添加局数信息信息
     * @param restrain 局数信息对象
     * @return 局数信息对象
     */
    public Restrain createRestrain(Restrain restrain);

    /**
     * 修改局数信息信息
     * @param restrain 局数信息对象
     * @return 局数信息对象
     */
    public Restrain updateRestrain(Restrain restrain);

    /**
     * 根据code删除指定局数信息
     * @param code 局数信息code
     * @return true表示成功 false表示失败
     */
    public Boolean deleteRestrain(String code);

    /**
     * 操作指定局数状态
     * @param code 局数code
     * @param restrainStatus 局数状态
     * @return true表示操作成功 false表示操作失败
     */
    public boolean operationRestrainStatus(String code, RestrainStatus restrainStatus);

    /**
     * 将指定局数开启
     * @param code 局数code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean openBetting(String code);

    /**
     * 将指定局数禁止投注
     * @param code 局数code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean prohibitBetting(String code);

    /**
     * 根据局数顺序和赛事code判断是否存在局数
     * @param restrainNum 局数顺序
     * @param competitionCode 赛事code
     * @return true表示操作存在 false表示操作不存在
     */
    public boolean isExisRestrain(int restrainNum,String competitionCode);

}
