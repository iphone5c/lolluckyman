package com.lolluckyman.lolbefore;

import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.account.service.IAccountService;
import com.lolluckyman.lolbefore.model.AccountParamsModel;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void login(HttpServletRequest request,HttpServletResponse response,String loginName,String password){
        if (LolUtils.isEmptyOrNull(loginName))
            validationResultJSONP(request,response,1001,"用户名不能为空");
        if (LolUtils.isEmptyOrNull(password))
            validationResultJSONP(request,response,1001,"用户密码不能为空");
        Account account=new Account();
        account.setLoginAccount(loginName);
        account.setPassword(password);
        Account loginAccount=accountService.loginAccount(account);
        request.getSession().setAttribute("CURRENT_ACCOUNT",loginAccount);
        if (loginAccount!=null){
            resultJSONP(request,response,"登录成功");
        }else{
            validationResultJSONP(request,response,1001,"登录失败");
        }
    }

    /**
     * 用户注册
     * @param account
     * @param accountParamsModel
     * @return
     */
    @RequestMapping(value = "/registerAccount")
    public void registerAccount(HttpServletRequest request,HttpServletResponse response,Account account,AccountParamsModel accountParamsModel){
        if (account==null)
            validationResultJSONP(request,response,1001,"用户信息不能为空");
        account.setPasswordProblem(accountParamsModel.getPasswordProblem());
        Account temp=accountService.registerAccount(account);
        if (temp!=null){
            resultJSONP(request,response,"注册");
        }else{
            validationResultJSONP(request,response,1001,"注册失败");
        }
    }
}
