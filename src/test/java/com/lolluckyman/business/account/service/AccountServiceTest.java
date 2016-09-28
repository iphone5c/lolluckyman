package com.lolluckyman.business.account.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void testGetAccountPageList() throws Exception {

    }

    @Test
    public void testGetAccountByCode() throws Exception {

    }

    @Test
    public void testResetAccountPassword() throws Exception {

    }

    @Test
    public void testResetAccountWithdrawalsPassword() throws Exception {

    }

    @Test
    public void testOperationAccountStatus() throws Exception {

    }

    @Test
    public void testDisableAccount() throws Exception {

    }

    @Test
    public void testRemoveDisableAccount() throws Exception {

    }

    @Test
    public void testOperationRechargeStatus() throws Exception {

    }

    @Test
    public void testDisableRechargeStatus() throws Exception {

    }

    @Test
    public void testEnableRechargeStatus() throws Exception {

    }

    @Test
    public void testOperationWithdrawalsStatus() throws Exception {

    }

    @Test
    public void testDisableWithdrawalsStatus() throws Exception {

    }

    @Test
    public void testEnableWithdrawalsStatus() throws Exception {

    }

    @Test
    public void testOperationExchangePrizeStatus() throws Exception {

    }

    @Test
    public void testDisableExchangePrizeStatus() throws Exception {

    }

    @Test
    public void testEnableExchangePrizeStatus() throws Exception {

    }

    @Test
    public void testOperationBettingStatus() throws Exception {

    }

    @Test
    public void testDisableExchangeBettingStatus() throws Exception {

    }

    @Test
    public void testEnableExchangeBettingStatus() throws Exception {

    }

    @Test
    public void testLoginAccount() throws Exception {

    }

    @Test
    public void testGetAccountByLoginName() throws Exception {

    }
}