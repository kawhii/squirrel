/*
 * 版权所有.(c)2008-2018.广州市森锐科技股份有限公司
 */

package com.kawhii.workflow.config;

import com.kawhii.workflow.WebflowApplication;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Carl
 * @date 2018/1/4
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebflowApplication.class)
@Import({ActivitiConfiguration.class})
public class ActivitiConfigurationTest {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void processEngineConfiguration() {
        repositoryService.createDeployment().addClasspathResource("processes/onboarding.bpmn20.xml")
                .deploy();
        long count = repositoryService.createDeploymentQuery().count();
        assertEquals(2, count);
    }
}