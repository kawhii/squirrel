
package com.kawhii.workflow;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Carl
 * @version 创建时间：2018/1/4
 * @since 1.0.0
 */
@SpringBootApplication
public class WebflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebflowApplication.class, args);
    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return () -> {

            Group group = identityService.newGroup("user");
            group.setName("users");
            group.setType("security-role");
            identityService.saveGroup(group);

            User admin = identityService.newUser("admin");
            admin.setPassword("admin");
            identityService.saveUser(admin);

        };
    }
}
