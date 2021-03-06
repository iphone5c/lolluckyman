/**
 * 2016/9/21 23:03:35 魏源 created.
 * Generated by Caven.CodeBuilder (funiJava.mybatis_dao_imp 1.0).
 */

package com.lolluckyman.business.accountassets.dao;

import java.util.*;
import java.math.*;

import com.lolluckyman.utils.core.dao.IbatisBaseStatement;
import com.lolluckyman.utils.core.dao.IbatisDataDAOImpl;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lolluckyman.business.accountassets.entity.AccountAssets;

/**
 * 账户资产信息 Ibatis Dao 实现
 * Created by 魏源 on 2016/09/21.
 */
@Repository
public class AccountAssetsDao extends IbatisDataDAOImpl<AccountAssets, String> implements IAccountAssetsDao {

    private IbatisBaseStatement ibatisBaseStatement;

    // dao的spring配置
    /*
    <bean id="accountAssetsDao" class="com.lolluckyman.business.accountassets.entity.daos.AccountAssetsDao">
        <constructor-arg name="sqlSession" ref="sqlSession"/>
    </bean>
    */

    /**
     * 用Ibatis会话创建Dao
     * @param sqlSession Ibatis会话
     */
    @Autowired
    public AccountAssetsDao(SqlSessionTemplate sqlSession){
        super(sqlSession);
    }

	@Override
	protected IbatisBaseStatement getIbatisBaseStatement() {
		if (ibatisBaseStatement == null) {
			ibatisBaseStatement = new IbatisBaseStatement();
			ibatisBaseStatement.setInsertStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.insertObject");
			ibatisBaseStatement.setUpdateStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.updateObject");
			ibatisBaseStatement.setDeleteStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.deleteObject");
			ibatisBaseStatement.setGetHasDetailStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.getDetailObject");
			ibatisBaseStatement.setGetNoDetailStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.getBaseObject");
			ibatisBaseStatement.setWhereDeleteStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.deleteObjectByWhere");
			ibatisBaseStatement.setQueryCountStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.queryCount");
			ibatisBaseStatement.setQueryHasDetailListStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.queryDetailList");
			ibatisBaseStatement.setQueryNoDetailListStatementId("com.lolluckyman.business.accountassets.dao.IAccountAssetsDao.queryBaseList");
		}
		return ibatisBaseStatement;
	}

}
