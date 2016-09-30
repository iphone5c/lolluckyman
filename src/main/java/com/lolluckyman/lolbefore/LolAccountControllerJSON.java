package com.lolluckyman.lolbefore;

import com.google.code.kaptcha.Producer;
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
@RequestMapping(value = "/lol/account/json")
public class LolAccountControllerJSON extends BaseController {

    public Logger log = Logger.getLogger(LolAccountControllerJSON.class);//日志

    @Autowired
    private IAccountService accountService;
    @Autowired
    private Producer captchaProducer = null;

    /**
     * 用户登录
     * @param loginAccount
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public Object login(HttpServletRequest request,HttpServletResponse response,String loginAccount,String password){
        if (LolUtils.isEmptyOrNull(loginAccount))
            return validationResult(1001,"用户名不能为空");
        if (LolUtils.isEmptyOrNull(password))
            return validationResult(1001,"用户密码不能为空");
        Account account=new Account();
        account.setLoginAccount(loginAccount);
        account.setPassword(password);
        Account login=accountService.loginAccount(account);
        request.getSession().setAttribute("CURRENT_ACCOUNT",login);
        if (login!=null){
            return result("登录成功");
        }else{
            return validationResult(1001,"登录失败");
        }
    }

    /**
     * 用户注册
     * @param account
     * @param accountParamsModel
     * @param securityCode
     * @return
     */
    @RequestMapping(value = "/registerAccount")
    public Object registerAccount(HttpServletRequest request,HttpServletResponse response,Account account,AccountParamsModel accountParamsModel,String securityCode){
        if (account==null)
            return validationResult(1001,"用户信息不能为空");
        if (!LolUtils.isExistSecurityCode(request,securityCode))
            return validationResult(1001,"验证码输入错误");
//        account.setPasswordProblem(accountParamsModel.getPasswordProblem());
        Account temp=accountService.registerAccount(account);
        if (temp!=null){
            return result("注册成功");
        }else{
            return validationResult(1001,"注册失败");
        }
    }

}
