package com.lolluckyman.business.competition.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:conf/applicationContext.xml")
public class CompetitionServiceTest {

    @Autowired
    private ICompetitionService competitionService;

    @Test
    public void testGetCompetitionPageList() throws Exception {

    }

    @Test
    public void testGetCompetitionList() throws Exception {

    }

    @Test
    public void testGetCompetitionByCode() throws Exception {

    }

    @Test
    public void testCreateCompetition() throws Exception {

    }

    @Test
    public void testUpdateCompetition() throws Exception {

    }

    @Test
    public void testDeleteCompetition() throws Exception {

    }

    @Test
    public void testOperationCompetitionStatus() throws Exception {

    }

    @Test
    public void testOpenBetting() throws Exception {

    }

    @Test
    public void testProhibitBetting() throws Exception {

    }

    @Test
    public void testIsExistCompetitionByTeam() throws Exception {

    }

    @Test
    public void testGetCompetitionListMap() throws Exception {
//        StringBuffer dateInfo=new StringBuffer("2016-9-30,");
//        for (int i=1;i<=31;i++){
//            dateInfo.append("2016-10-").append(i).append(",");
//        }
//        dateInfo.deleteCharAt(dateInfo.length()-1);
//        Map<String,List<Competition>> results = competitionService.getCompetitionListMap(dateInfo.toString());
//
//
//
//        for (Map.Entry<String, List<Competition>> entry : results.entrySet()) {
//
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }
    }
}