
package com.kawhii.workflow.config;

import com.kawhii.workflow.WebflowApplication;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Carl
 * @date 2018/1/4
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebflowApplication.class)
@Import({ActivitiConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActivitiConfigurationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiConfigurationTest.class);
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void a_processEngineConfiguration() {
        repositoryService.createDeployment().addClasspathResource("processes/onboarding.bpmn20.xml")
                .deploy();
        long count = repositoryService.createDeploymentQuery().count();
        assertEquals(2, count);
    }

    @Test
    public void b_startProcessInstanceByKey() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
        assertEquals(1, runtimeService.createProcessInstanceQuery().count());
    }

    @Test
    public void c_completingTasks() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            LOGGER.info("Task available: " + task.getName());
        }
        assertTrue(tasks.size() > 0);

        //完成任务
        Task task = tasks.get(0);

        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);
    }
}