/**
 * 2016/9/22 18:57:00 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.mybatis_dao_imp 1.0).
 */

package com.lolluckyman.business.competition.dao;

import com.lolluckyman.business.competition.entity.Competition;
import com.lolluckyman.utils.core.dao.IbatisBaseStatement;
import com.lolluckyman.utils.core.dao.IbatisDataDAOImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 比赛信息 Ibatis Dao 实现
 * Created by lenovo on 2016/09/22.
 */
@Repository
public class CompetitionDao extends IbatisDataDAOImpl<Competition, String> implements ICompetitionDao {

    private IbatisBaseStatement ibatisBaseStatement;

    // dao的spring配置
    /*
    <bean id="competitionDao" class="com.lolluckyman.business.competition.entity.daos.CompetitionDao">
        <constructor-arg name="sqlSession" ref="sqlSession"/>
    </bean>
    */

    /**
     * 用Ibatis会话创建Dao
     * @param sqlSession Ibatis会话
     */
    @Autowired
    public CompetitionDao(SqlSessionTemplate sqlSession){
        super(sqlSession);
    }

	@Override
	protected IbatisBaseStatement getIbatisBaseStatement() {
		if (ibatisBaseStatement == null) {
			ibatisBaseStatement = new IbatisBaseStatement();
			ibatisBaseStatement.setInsertStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.insertObject");
			ibatisBaseStatement.setUpdateStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.updateObject");
			ibatisBaseStatement.setDeleteStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.deleteObject");
			ibatisBaseStatement.setGetHasDetailStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.getDetailObject");
			ibatisBaseStatement.setGetNoDetailStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.getBaseObject");
			ibatisBaseStatement.setWhereDeleteStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.deleteObjectByWhere");
			ibatisBaseStatement.setQueryCountStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.queryCount");
			ibatisBaseStatement.setQueryHasDetailListStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.queryDetailList");
			ibatisBaseStatement.setQueryNoDetailListStatementId("com.lolluckyman.business.competition.dao.ICompetitionDao.queryBaseList");
		}
		return ibatisBaseStatement;
	}

}
