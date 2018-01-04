/*
 * 版权所有.(c)2008-2018.广州市森锐科技股份有限公司
 */

package com.kawhii.workflow.config;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
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
}
