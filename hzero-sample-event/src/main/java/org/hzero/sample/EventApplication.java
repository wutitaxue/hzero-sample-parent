package org.hzero.sample;

import org.hzero.core.jackson.annotation.EnableObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

/**
 * @author zifeng.ding@hand-china.com 2020-01-17 9:20:25
 */
@EnableFeignClients({"org.hzero.sample"})
@EnableObjectMapper
@EnableChoerodonResourceServer
@Configuration
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

}
