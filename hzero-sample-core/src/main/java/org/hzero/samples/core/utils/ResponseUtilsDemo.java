package org.hzero.samples.core.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import io.choerodon.core.exception.CommonException;
import io.choerodon.core.exception.ExceptionResponse;

import org.hzero.common.HZeroService;
import org.hzero.core.util.ResponseUtils;

/**
 * 响应处理类，通常用于处理 Feign 调用返回结果，由于 Feign 调用结果可能错误，返回对象并不一定是指定的泛型，
 * 所以通用的处理方式是指定返回类型为 String，然后使用 ResponseUtils 来处理返回结果。
 *
 * @author bojiangzhou 2020/01/20
 */
public class ResponseUtilsDemo {

    private UserFeignClient userFeignClient;

    public void demo() {

        UserDTO dto = new UserDTO();
        dto.setEmail("demo@hand-china.com");

        // Feign 调用创建用户
        ResponseEntity<String> result = userFeignClient.createUser(dto);

        // 处理方式一：判断结果处理
        if (ResponseUtils.isFailed(result)) {
            // 获取异常信息
            ExceptionResponse response = ResponseUtils.getResponse(result, ExceptionResponse.class);
            throw new CommonException(response.getMessage());
        }
        // 成功，获取返回用户信息
        UserDTO user1 = ResponseUtils.getResponse(result, UserDTO.class);


        // 处理方式二，基于 lambada 表达式处理
        // 第三个参数：非2xx错误处理；第四个参数：200，但是failed=true处理
        UserDTO user2 = ResponseUtils.getResponse(result, UserDTO.class, (httpStatus, response) -> {
            throw new CommonException(response);
        }, (exceptionResponse) -> {
            throw new CommonException(exceptionResponse.getMessage());
        });

    }


    @FeignClient(value = HZeroService.Iam.NAME)
    public interface UserFeignClient {

        @PostMapping("/v1/users")
        ResponseEntity<String> createUser(UserDTO user);
    }

    public class UserDTO {
        private Long id;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
