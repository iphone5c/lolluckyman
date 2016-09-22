package com.lolluckyman.business.prize.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.prize.dao.IPrizeDao;
import com.lolluckyman.business.prize.entity.Prize;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("prizeService")
public class PrizeService extends BaseService implements IPrizeService {

    public Logger log = Logger.getLogger(PrizeService.class);//日志

    @Autowired
    private IPrizeDao prizeDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     *获取奖品分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Prize> getPrizePageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return prizeDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据奖品code查找奖品
     * @param prizeCode 奖品code
     * @return 奖品对象
     */
    @Override
    public Prize getPrizeByCode(String prizeCode) {
        if (LolUtils.isEmptyOrNull(prizeCode))
            throw new IllegalArgumentException("根据code查找奖品信息时，Code不能为空");
        return prizeDao.getObject(prizeCode,true);
    }

    /**
     * 添加奖品信息
     * @param prize 奖品对象
     * @return 奖品对象
     */
    @Override
    public Prize createPrize(Prize prize) {
        if (prize==null)
            throw new IllegalArgumentException("新增奖品时，奖品对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(prize.getPrizeName()))
            throw new IllegalArgumentException("新增奖品时，奖品名字不能为空或者null");
        if(prize.getPrizeType()==null)
            throw new IllegalArgumentException("新增奖品时，奖品类型不能为空或者null");
        prize.setCode(codeBuilder.getPrizeCode());
        prize.setCreateTime(new Date());
        int info=prizeDao.insertObject(prize);
        return info>0?prize:null;
    }

    /**
     * 修改奖品信息
     * @param prize 奖品对象
     * @return 奖品对象
     */
    @Override
    public Prize updatePrize(Prize prize) {
        if (prize==null)
            throw new IllegalArgumentException("修改奖品时，prize对象不能为空");
        if (LolUtils.isEmptyOrNull(prize.getCode()))
            throw new IllegalArgumentException("修改奖品时，奖品主键不能为空");
        if(LolUtils.isEmptyOrNull(prize.getPrizeName()))
            throw new IllegalArgumentException("修改奖品时，奖品名字不能为空或者null");
        if(prize.getPrizeType()==null)
            throw new IllegalArgumentException("修改奖品时，奖品类型不能为空或者null");
        int info=prizeDao.updateObject(prize);
        return info>0?prize:null;
    }

    /**
     * 根据code删除指定奖品
     * @param code 奖品code
     * @return true表示成功 false表示失败
     */
    @Override
    public Boolean deletePrize(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除指定奖品时，奖品主键不能为空");
        int info = prizeDao.deleteObject(code);
        return info>0?true:false;
    }

}
