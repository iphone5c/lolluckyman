package com.lolluckyman.business.admin.service;

import com.lolluckyman.business.admin.dao.IAdminDao;
import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
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
@Service("adminService")
public class AdminService extends BaseService implements IAdminService {

    public Logger log = Logger.getLogger(AdminService.class);//日志

    @Autowired
    private IAdminDao adminDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     *获取管理员分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Admin> getAdminPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return adminDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据管理员code查找管理员
     * @param adminCode 管理员code
     * @return 管理员对象
     */
    @Override
    public Admin getAdminByCode(String adminCode) {
        if (LolUtils.isEmptyOrNull(adminCode))
            throw new IllegalArgumentException("根据code查找管理员信息时，Code不能为空");
        return adminDao.getObject(adminCode,true);
    }

    /**
     * 添加管理员信息
     * @param admin 管理员对象
     * @return 管理员对象
     */
    @Override
    public Admin createAdmin(Admin admin) {
        if (admin==null)
            throw new IllegalArgumentException("新增管理员时，管理员对象不能为空或者null");
        if(LolUtils.isEmptyOrNull(admin.getLoginName()))
            throw new IllegalArgumentException("新增管理员时，登录名不能为空");
        if(LolUtils.isEmptyOrNull(admin.getPassword()))
            throw new IllegalArgumentException("新增管理员时，密码不能为空");
        if (this.getAdminByLoginName(admin.getLoginName())!=null)
            throw new IllegalArgumentException("新增管理员时，登陆名已存在");
        admin.setCode(codeBuilder.getAdminCode());
        admin.setPassword(LolUtils.encryptPassword(admin.getPassword()));
        admin.setCreateTime(new Date());
        int info=adminDao.insertObject(admin);
        return info>0?admin:null;
    }

    /**
     * 修改管理员信息
     * @param admin 管理员对象
     * @return 管理员对象
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        if (admin==null)
            throw new IllegalArgumentException("修改管理员时，admin对象不能为空");
        if (LolUtils.isEmptyOrNull(admin.getCode()))
            throw new IllegalArgumentException("修改管理员时，管理员主键不能为空");
        if (adminDao.getObject(admin.getCode(),true)==null)
            throw new IllegalArgumentException("修改管理员时，找不到此管理员的基本信息，管理员主键为："+admin.getCode());
        Admin temp=this.getAdminByLoginName(admin.getLoginName());
        if (!(temp==null||temp.getCode().equals(admin.getCode())))
            throw new IllegalArgumentException("修改管理员时，管理员登录名已存在");
        int info=adminDao.updateObject(admin);
        return info>0?admin:null;
    }

    /**
     * 删除指定管理员账户
     * @param code
     * @return
     */
    @Override
    public Boolean deleteAdmin(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("删除管理员时，管理员主键不能为空");
        if (adminDao.getObject(code,true)==null)
            return true;
        int info=adminDao.deleteObject(code);
        return info>0?true:false;
    }

    /**
     * 根据登录名获取管理员信息
     * @param loginName 管理员登录名
     * @return 管理员
     */
    @Override
    public Admin getAdminByLoginName(String loginName){
        if (LolUtils.isEmptyOrNull(loginName))
            throw new IllegalArgumentException("根据loginName查询管理员信息时，loginName不能为空或null");
        QueryParams queryParams=new QueryParams();
        queryParams.addParameter("loginName",loginName);
        List<Admin> adminList=adminDao.queryList(queryParams,0,-1,true);
        return ((adminList!=null&&adminList.size()>0)?adminList.get(0):null);
    }

    /**
     * 指定管理员修改密码
     * @param adminCode 管理员code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean modifyAdminPassword(String adminCode, String oldPassword, String newPassword, String confirmPassword) {
        if (LolUtils.isEmptyOrNull(adminCode))
            throw new IllegalArgumentException("指定管理员修改密码时，code不能为空或null");
        if (LolUtils.isEmptyOrNull(oldPassword))
            throw new IllegalArgumentException("指定管理员修改密码时，旧密码不能为空或null");
        if (LolUtils.isEmptyOrNull(newPassword))
            throw new IllegalArgumentException("指定管理员修改密码时，新密码不能为空或null");
        if (LolUtils.isEmptyOrNull(confirmPassword))
            throw new IllegalArgumentException("指定管理员修改密码时，确认密码不能为空或null");
        Admin admin=adminDao.getObject(adminCode,true);
        if (admin==null)
            throw new IllegalArgumentException("指定管理员修改密码时，找不到此管理员信息，code："+adminCode);
        if (!LolUtils.verifyPassword(oldPassword,admin.getPassword()))
            throw new IllegalArgumentException("指定管理员修改密码时，旧密码输出错误");
        if (!newPassword.equals(confirmPassword))
            throw new IllegalArgumentException("指定管理员修改密码时，新密码与确认密码输入不一致");
        admin.setPassword(LolUtils.encryptPassword(newPassword));
        int info=adminDao.updateObject(admin);
        return info>0?true:false;
    }

    /**
     * 将指定管理员重置密码
     * @param adminCode 管理员code
     * @return true表示操作成功 false表示操作失败
     */
    @Override
    public boolean resetAdminPassword(String adminCode) {
        if (LolUtils.isEmptyOrNull(adminCode))
            throw new IllegalArgumentException("指定管理员重置密码时，code不能为空或null");
        Admin admin=adminDao.getObject(adminCode,true);
        if (admin==null)
            throw new IllegalArgumentException("指定管理员重置密码时，找不到此管理员信息，code："+adminCode);
        admin.setPassword(LolUtils.encryptPassword("ABC123"));
        int info=adminDao.updateObject(admin);
        return info>0?true:false;
    }
}
