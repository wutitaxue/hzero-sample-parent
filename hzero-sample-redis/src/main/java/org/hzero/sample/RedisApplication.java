package org.hzero.sample;

import org.hzero.core.jackson.annotation.EnableObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

/**
 * @author zifeng.ding@hand-china.com 2020-01-17 13:50:25
 */
@EnableObjectMapper
@EnableChoerodonResourceServer
@Configuration
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
