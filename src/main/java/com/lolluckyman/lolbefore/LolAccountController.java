package com.lolluckyman.lolbefore;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/lol/account")
public class LolAccountController extends BaseController {

    public Logger log = Logger.getLogger(LolAccountController.class);//日志

    @Autowired
    private IAccountService accountService;

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public Object login(HttpServletRequest request,String loginName,String password){
        if (LolUtils.isEmptyOrNull(loginName))
            return validationResult(1001,"用户名不能为空");
        if (LolUtils.isEmptyOrNull(password))
            return validationResult(1001,"用户密码不能为空");
        Account account=new Account();
        account.setLoginAccount(loginName);
        account.setPassword(password);
        Account loginAccount=accountService.loginAccount(account);
        request.getSession().setAttribute("CURRENT_ACCOUNT",loginAccount);
        if (loginAccount!=null){
            return result("登录成功");
        }else{
            return validationResult(1001,"登录失败");
        }
    }
}
