/**
 * 2016/9/22 15:44:27 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.mybatis_dao_imp 1.0).
 */

package com.lolluckyman.business.playrecord.dao;

import com.lolluckyman.business.playrecord.entity.PlayRecord;
import com.lolluckyman.utils.core.dao.IbatisBaseStatement;
import com.lolluckyman.utils.core.dao.IbatisDataDAOImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 联盟玩法 Ibatis Dao 实现
 * Created by lenovo on 2016/09/22.
 */
@Repository
public class PlayRecordDao extends IbatisDataDAOImpl<PlayRecord, String> implements IPlayRecordDao {

    private IbatisBaseStatement ibatisBaseStatement;

    // dao的spring配置
    /*
    <bean id="playRecordDao" class="com.lolluckyman.business.playrecord.entity.daos.PlayRecordDao">
        <constructor-arg name="sqlSession" ref="sqlSession"/>
    </bean>
    */

    /**
     * 用Ibatis会话创建Dao
     * @param sqlSession Ibatis会话
     */
    @Autowired
    public PlayRecordDao(SqlSessionTemplate sqlSession){
        super(sqlSession);
    }

	@Override
	protected IbatisBaseStatement getIbatisBaseStatement() {
		if (ibatisBaseStatement == null) {
			ibatisBaseStatement = new IbatisBaseStatement();
			ibatisBaseStatement.setInsertStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.insertObject");
			ibatisBaseStatement.setUpdateStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.updateObject");
			ibatisBaseStatement.setDeleteStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.deleteObject");
			ibatisBaseStatement.setGetHasDetailStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.getDetailObject");
			ibatisBaseStatement.setGetNoDetailStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.getBaseObject");
			ibatisBaseStatement.setWhereDeleteStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.deleteObjectByWhere");
			ibatisBaseStatement.setQueryCountStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.queryCount");
			ibatisBaseStatement.setQueryHasDetailListStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.queryDetailList");
			ibatisBaseStatement.setQueryNoDetailListStatementId("com.lolluckyman.business.playrecord.dao.IPlayRecordDao.queryBaseList");
		}
		return ibatisBaseStatement;
	}

}
