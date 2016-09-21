package com.lolluckyman.business.systemparams.service;

import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.business.systemparams.dao.ISystemParamsDao;
import com.lolluckyman.business.systemparams.entity.SystemParams;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("systemParamsService")
public class SystemParamsService extends BaseService implements ISystemParamsService {

    public Logger log = Logger.getLogger(SystemParamsService.class);//日志

    @Autowired
    private ISystemParamsDao systemParamsDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     *获取系统参数配置分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<SystemParams> getSystemParamsPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return systemParamsDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据系统参数配置code查找系统参数配置
     * @param systemParamsCode 系统参数配置code
     * @return 系统参数配置对象
     */
    @Override
    public SystemParams getSystemParamsByCode(String systemParamsCode) {
        if (LolUtils.isEmptyOrNull(systemParamsCode))
            throw new IllegalArgumentException("根据code查找系统参数配置信息时，Code不能为空");
        return systemParamsDao.getObject(systemParamsCode,true);
    }

    /**
     * 添加系统参数配置信息
     * @param systemParams 系统参数配置对象
     * @return 系统参数配置对象
     */
    @Override
    public SystemParams createSystemParams(SystemParams systemParams) {
        if (systemParams==null)
            throw new IllegalArgumentException("新增系统参数配置时，系统参数配置对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(systemParams.getSystemParamsKey()))
            throw new IllegalArgumentException("新增系统参数配置时，系统参数配置对象的参数KEY不能为空或者null");
        if (this.getSystemParamsByKey(systemParams.getSystemParamsKey())!=null)
            throw new IllegalArgumentException("新增系统参数配置时，系统参数配置的KEY已存在");
        systemParams.setSystemParamsCode(codeBuilder.getSystemParamsCode());
        systemParams.setCreateTime(new Date());
        int info=systemParamsDao.insertObject(systemParams);
        return info>0?systemParams:null;
    }

    /**
     * 修改系统参数配置信息
     * @param systemParams 系统参数配置对象
     * @return 系统参数配置对象
     */
    @Override
    public SystemParams updateSystemParams(SystemParams systemParams) {
        if (systemParams==null)
            throw new IllegalArgumentException("修改系统参数配置时，systemParams对象不能为空");
        if (LolUtils.isEmptyOrNull(systemParams.getSystemParamsCode()))
            throw new IllegalArgumentException("修改系统参数配置时，系统参数配置主键不能为空");
        if (systemParamsDao.getObject(systemParams.getSystemParamsCode(),true)==null)
            throw new IllegalArgumentException("修改系统参数配置时，找不到此系统参数配置的基本信息，系统参数配置主键为："+systemParams.getSystemParamsCode());
        SystemParams temp=this.getSystemParamsByKey(systemParams.getSystemParamsKey());
        if (!(temp==null||temp.getSystemParamsCode().equals(systemParams.getSystemParamsCode())))
            throw new IllegalArgumentException("修改系统参数配置时，系统参数配置KEY已存在");
        int info=systemParamsDao.updateObject(systemParams);
        return info>0?systemParams:null;
    }

    /**
     * 根据key获取系统参数配置信息
     * @param systemParamsKey 系统参数配置key
     * @return 系统参数配置
     */
    @Override
    public SystemParams getSystemParamsByKey(String systemParamsKey) {
        if (LolUtils.isEmptyOrNull(systemParamsKey))
            throw new IllegalArgumentException("根据key查询系统参数配置信息时，key不能为空或null");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("systemParamsKey",systemParamsKey);
        List<SystemParams> systemParamsList=systemParamsDao.queryList(queryParams,0,-1,true);
        return ((systemParamsList!=null&&systemParamsList.size()>0)?systemParamsList.get(0):null);
    }
}
