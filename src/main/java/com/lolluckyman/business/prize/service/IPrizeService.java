package com.lolluckyman.business.prize.service;

import com.lolluckyman.business.prize.entity.Prize;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IPrizeService {

    /**
     *获取奖品分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Prize> getPrizePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据奖品code查找奖品
     * @param prizeCode 奖品code
     * @return 奖品对象
     */
    public Prize getPrizeByCode(String prizeCode);

    /**
     * 添加奖品信息
     * @param prize 奖品对象
     * @return 奖品对象
     */
    public Prize createPrize(Prize prize);

    /**
     * 修改奖品信息
     * @param prize 奖品对象
     * @return 奖品对象
     */
    public Prize updatePrize(Prize prize);

    /**
     * 根据code删除指定奖品
     * @param code 奖品code
     * @return true表示成功 false表示失败
     */
    public Boolean deletePrize(String code);
}
