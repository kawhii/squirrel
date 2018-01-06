
package com.kawhii.workflow.config;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 工作流配置
 *
 * @author Carl
 * @version 创建时间：2018/1/4
 * @since 1.0.0
 */
@Configuration
public class ActivitiConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiConfiguration.class);

    //开发环境配置
    @Bean
    @Profile("dev")
    protected StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcDriver("org.h2.Driver")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                //是否自动更新
                .setDatabaseSchemaUpdate("true")
                .setAsyncExecutorActivate(false)
                .setMailServerHost("mail.my-corp.com")
                .setMailServerPort(5025)
        ;
        return processEngineConfiguration;
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return strings -> {
            LOGGER.info("Number of process definitions : "
                    + repositoryService.createProcessDefinitionQuery().count());
            LOGGER.info("Number of tasks : " + taskService.createTaskQuery().count());
        };
    }
}
