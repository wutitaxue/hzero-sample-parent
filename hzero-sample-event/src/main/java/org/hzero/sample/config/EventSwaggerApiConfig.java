package org.hzero.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/16 9:51
 */
@Configuration
public class EventSwaggerApiConfig {

    public static final String DEMO = "Event Demo";

    @Autowired
    public EventSwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(DEMO, "事件规则示例")
        );
    }

}
