package com.lolluckyman.business.admin.service;

import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;

/**
 * Created by 魏源 on 2015/6/30 0030.
 */
public interface IAdminService {

    /**
     *获取管理员分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    public PageList<Admin> getAdminPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail);

    /**
     * 根据管理员code查找管理员
     * @param adminCode 管理员code
     * @return 管理员对象
     */
    public Admin getAdminByCode(String adminCode);

    /**
     * 添加管理员信息
     * @param admin 管理员对象
     * @return 管理员对象
     */
    public Admin createAdmin(Admin admin);

    /**
     * 修改管理员信息
     * @param admin 管理员对象
     * @return 管理员对象
     */
    public Admin updateAdmin(Admin admin);

    /**
     * 删除指定管理员账户
     * @param code
     * @return
     */
    public Boolean deleteAdmin(String code);

    /**
     * 根据登录名获取管理员信息
     * @param loginName 管理员登录名
     * @return 管理员
     */
    public Admin getAdminByLoginName(String loginName);

    /**
     * 指定管理员修改密码
     * @param adminCode 用户code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return true表示操作成功 false表示操作失败
     */
    public boolean modifyAdminPassword(String adminCode,String oldPassword,String newPassword,String confirmPassword);

    /**
     * 将指定管理员重置密码
     * @param adminCode 管理员code
     * @return true表示操作成功 false表示操作失败
     */
    public boolean resetAdminPassword(String adminCode);
}
