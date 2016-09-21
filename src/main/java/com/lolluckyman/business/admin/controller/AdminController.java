package com.lolluckyman.business.admin.controller;

import com.lolluckyman.business.admin.entity.Admin;
import com.lolluckyman.business.admin.service.IAdminService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/back/admin")
public class AdminController extends BaseController {

    public Logger log = Logger.getLogger(AdminController.class);//日志

    @Autowired
    private IAdminService adminService;

    /**
     * 获取管理员列表分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAdminPageList")
    public Object getAdminPageList(int pageIndex,int pageSize){
        log.info("获取管理员列表");
        QueryParams queryParams=new QueryParams();
        return result(adminService.getAdminPageList(queryParams,pageIndex,pageSize,true));
    }

    /**
     * 获取指定管理员信息
     * @param adminCode 管理员code
     * @return 管理员信息
     */
    @RequestMapping(value = "/getAdminByCode")
    public Object getAdminByCode(String adminCode){
        if (LolUtils.isEmptyOrNull(adminCode))
            return validationResult(1001,"查询指定管理员时，code不能为空或null");
        Admin admin=adminService.getAdminByCode(adminCode);
        if (admin==null){
            return validationResult(1001,"找不到指定管理员信息，code："+adminCode);
        }else {
            return result(admin);
        }
    }

    /**
     * 新增管理员信息
     * @param admin
     * @param confirmPassword
     * @return
     */
    @RequestMapping(value = "/createAdmin")
    public Object createAdmin(Admin admin,String confirmPassword){
        if (admin==null)
            return validationResult(1001,"新建管理员信息时，管理员对象不能为空或null");
        if (LolUtils.isEmptyOrNull(admin.getLoginName()))
            return validationResult(1001,"新建管理员信息时，管理员名不能为空或null");
        if (LolUtils.isEmptyOrNull(admin.getPassword()))
            return validationResult(1001,"新建管理员信息时，管理员密码不能为空或null");
        if (LolUtils.isEmptyOrNull(confirmPassword))
            return validationResult(1001,"新建管理员信息时，确认密码不能为空");
        if (!admin.getPassword().equals(confirmPassword))
            return validationResult(1001,"新建管理员信息时，两次密码输入不一致");
        if (adminService.getAdminByLoginName(admin.getLoginName())!=null)
            return validationResult(1001,"新建管理员信息时，管理员名已存在");
        Admin temp=adminService.createAdmin(admin);
        if (temp==null){
            return validationResult(1001,"新建失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    public Object updateAdmin(Admin admin){
        if (admin==null)
            return validationResult(1001,"修改管理员信息时，管理员对象不能为空或null");
        if (LolUtils.isEmptyOrNull(admin.getCode()))
            return validationResult(1001,"修改管理员信息时，管理员主键不能为空或null");
        Admin updateAdmin = adminService.getAdminByCode(admin.getCode());
        if (updateAdmin==null)
            return validationResult(1001,"修改管理员信息时，找不到此管理员的信息，code："+admin.getCode());
        if (LolUtils.isEmptyOrNull(admin.getLoginName()))
            return validationResult(1001,"修改管理员信息时，管理员名不能为空或null");
        Admin adminNameValidation = adminService.getAdminByLoginName(admin.getLoginName());
        if (!(adminNameValidation==null || adminNameValidation.getCode().equals(admin.getCode())))
            return validationResult(1001,"修改管理员信息时，管理员名已存在");
        updateAdmin.setLoginName(admin.getLoginName());
        Admin temp=adminService.updateAdmin(updateAdmin);
        if (temp==null){
            return validationResult(1001,"修改失败");
        }else {
            return result(temp);
        }
    }

    /**
     * 将指定管理员修改密码
     * @param adminCode 管理员code
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return
     */
    @RequestMapping(value = "/updateAdminPassword")
    public Object updateAdminPassword(String adminCode, String oldPassword, String newPassword, String confirmPassword){
        if (LolUtils.isEmptyOrNull(adminCode))
            return validationResult(1001,"指定管理员修改密码时，code不能为空或null");
        if (LolUtils.isEmptyOrNull(oldPassword))
            return validationResult(1001,"指定管理员修改密码时，旧密码不能为空或null");
        if (LolUtils.isEmptyOrNull(newPassword))
            return validationResult(1001,"指定管理员修改密码时，新密码不能为空或null");
        if (LolUtils.isEmptyOrNull(confirmPassword))
            return validationResult(1001,"指定管理员修改密码时，确认密码不能为空或null");
        if (!newPassword.equals(confirmPassword))
            return validationResult(1001,"指定管理员修改密码时，新密码与确认密码输入不一致");
        boolean flag=adminService.modifyAdminPassword(adminCode, oldPassword, newPassword, confirmPassword);
        if (flag){
            return result("修改成功");
        }else{
            return validationResult(1001,"修改失败");
        }
    }

    /**
     * 指定管理员重置密码
     * @param adminCode
     * @return
     */
    @RequestMapping(value = "/resetAdminPassword")
    public Object resetAdminPassword(String adminCode){
        if (LolUtils.isEmptyOrNull(adminCode))
            return validationResult(1001,"指定管理员重置密码时，code不能为空或null");
        boolean flag=adminService.resetAdminPassword(adminCode);
        if (flag){
            return result("重置成功");
        }else{
            return validationResult(1001,"重置失败");
        }
    }

    /**
     * 删除指定管理员
     * @param adminCode
     * @return
     */
    @RequestMapping(value = "/deleteAdmin")
    public Object deleteAdmin(String adminCode){
        if (LolUtils.isEmptyOrNull(adminCode))
            return validationResult(1001,"删除指定管理员时，code不能为空或null");
        boolean flag=adminService.deleteAdmin(adminCode);
        if (flag){
            return result("删除成功");
        }else{
            return validationResult(1001,"删除失败");
        }
    }

}
