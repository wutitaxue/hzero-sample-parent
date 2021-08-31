package org.hzero.sample.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.swagger.annotation.Permission;

import org.hzero.core.util.Results;
import org.hzero.sample.api.dto.UserDTO;
import org.hzero.sample.app.service.UserService;
import org.hzero.sample.config.IamSwaggerApiConfig;

/**
 * 调用IAM服务内部创建用户接口示例
 *
 * @author zifeng.ding@hand-china.com 2020/01/16 9:51
 */
@Api(tags = IamSwaggerApiConfig.DEMO)
@RestController("demoUserController.v1")
@RequestMapping("/v1/{organizationId}/demos")
public class DemoUserController {

    private final UserService userService;

    public DemoUserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation("用户注册 -- 手机注册")
    @Permission(permissionPublic = true)
    @PostMapping("/users/phone")
    public ResponseEntity<Void> createUserByPhone(@RequestBody UserDTO user,
                                              @ApiParam(value = "验证码Key：发送验证码时返回的 captchaKey") String captchaKey,
                                              @ApiParam(value = "验证码：用户输入的验证码") String captcha,
                                              @PathVariable Long organizationId) {
        user.setOrganizationId(organizationId);
        userService.createUserByPhone(user, captchaKey, captcha);
        return Results.success();
    }

    @ApiOperation("用户注册 -- 邮箱注册")
    @Permission(permissionPublic = true)
    @PostMapping("/users/email")
    public ResponseEntity<Void> createUserByEmail(@RequestBody UserDTO user,
                                             @ApiParam(value = "验证码Key：发送验证码时返回的 captchaKey") @RequestParam(required = false) String captchaKey,
                                             @ApiParam(value = "验证码：用户输入的验证码") @RequestParam(required = false) String captcha,
                                             @PathVariable Long organizationId) {
        user.setOrganizationId(organizationId);
        userService.createUserByEmail(user, captchaKey, captcha);
        return Results.success();
    }


}
