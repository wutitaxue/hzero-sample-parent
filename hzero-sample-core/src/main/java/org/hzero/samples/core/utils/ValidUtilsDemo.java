package org.hzero.samples.core.utils;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;

/**
 * 参数校验，一般在 Controller 方法中对入参对象进行校验，验证 @NotBlank、@Length 等注解标注的字段
 *
 * Controller 可继承 BaseController，BaseController 中已包含一些通用的校验方法
 *
 * @author bojiangzhou 2020/01/20
 */
@RestController
public class ValidUtilsDemo extends BaseController {


    @PostMapping("/v1/users")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {

        // 校验单个对象
        validObject(user);

        // 分组校验，需在验证注解上加入分组参数
        if (user.isEmailRegister()) {
            validObject(user, UserDTO.EmailRegister.class);
        } else {
            validObject(user, UserDTO.UsernameRegister.class);
        }

        // 校验集合中的对象
        List<UserDTO> list = new ArrayList<>();
        validList(list);

        return Results.success(user);
    }


    public static class UserDTO {

        private Long id;

        @Email
        @NotBlank(groups = EmailRegister.class)
        private String email;

        @NotBlank(groups = UsernameRegister.class)
        @Length(min = 6, max = 30)
        private String username;

        @NotNull
        @Size(min = 0, max = 110)
        private Integer age;

        @Length(max = 255)
        private String address;

        private boolean emailRegister;


        public interface EmailRegister {}
        public interface UsernameRegister {}


        // getter/setter


        public boolean isEmailRegister() {
            return emailRegister;
        }

    }


}
