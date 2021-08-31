package org.hzero.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger Api 描述配置
 *
 * @author hzero
 */
@Configuration
public class FileSwaggerApiConfig {

    public static final String FILE = "File";

    @Autowired
    public FileSwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(FILE, "文件相关")
        );
    }
}
