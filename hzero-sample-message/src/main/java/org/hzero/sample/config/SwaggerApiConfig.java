package org.hzero.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2019/07/21 10:01
 */
@Configuration
public class SwaggerApiConfig {

    public static final String DEMO = "Message Demo";

    @Autowired
    public SwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(DEMO, "消息服务示例")
        );
    }

}
