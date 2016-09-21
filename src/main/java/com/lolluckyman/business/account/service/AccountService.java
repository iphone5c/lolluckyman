package com.lolluckyman.business.account.service;

import com.lolluckyman.business.account.dao.IAccountDao;
import com.lolluckyman.business.account.entity.Account;
import com.lolluckyman.business.codebuilder.ICodeBuilder;
import com.lolluckyman.utils.cmd.LolUtils;
import com.lolluckyman.utils.core.BaseService;
import com.lolluckyman.utils.core.PageList;
import com.lolluckyman.utils.core.QueryParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@Transactional
@Service("accountService")
public class AccountService extends BaseService implements IAccountService {

    public Logger log = Logger.getLogger(AccountService.class);//日志

    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private ICodeBuilder codeBuilder;

    /**
     *获取账户分页列表
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    @Override
    public PageList<Account> getAccountPageList(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return accountDao.queryListForPaged(wheres,pageIndex,pageSize,detail);
    }

    /**
     * 根据账户code查找账户
     * @param code 账户code
     * @return 账户对象
     */
    @Override
    public Account getAccountByCode(String code) {
        if (LolUtils.isEmptyOrNull(code))
            throw new IllegalArgumentException("根据code查找账户信息，code不能为空");
        return accountDao.getObject(code,true);
    }

}
