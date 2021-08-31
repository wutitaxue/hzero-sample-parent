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
public class IamSwaggerApiConfig {

    public static final String DEMO = "Iam Demo";

    @Autowired
    public IamSwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(DEMO, "Iam服务示例")
        );
    }

}
