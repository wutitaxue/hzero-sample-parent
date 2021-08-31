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
 * @author shuangfei.zhu@and-china.com
 */
@EnableFeignClients({"org.hzero.sample"})
@EnableObjectMapper
@EnableChoerodonResourceServer
@Configuration
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}
