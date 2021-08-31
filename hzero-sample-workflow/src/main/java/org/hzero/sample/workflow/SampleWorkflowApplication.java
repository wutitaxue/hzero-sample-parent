package org.hzero.sample.workflow;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.hzero.core.jackson.annotation.EnableObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qingsheng.chen@hand-china.com
 */
@EnableObjectMapper
@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class SampleWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleWorkflowApplication.class, args);
    }
}
