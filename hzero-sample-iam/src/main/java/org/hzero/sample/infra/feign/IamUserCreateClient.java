package org.hzero.sample.infra.feign;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.hzero.common.HZeroService;
import org.hzero.sample.api.dto.UserDTO;
import org.hzero.sample.infra.feign.fallback.IamUserCreateClientImpl;

/**
 * 用户接口feign调用
 *
 * @author zifeng.ding@hand-china.com 2020/01/15 20:21
 */
@FeignClient(value = HZeroService.Iam.NAME, fallback = IamUserCreateClientImpl.class)
public interface IamUserCreateClient {

    /**
     * 内部调用创建用户
     *
     * @param user            用户
     * @param type            创建类型：手机-phone/邮箱-email
     * @param validateCaptcha 是否校验验证码
     * @param captchaKey      验证码Key：发送验证码时返回的 captchaKey
     * @param captcha         验证码：用户输入的验证码
     * @return 用户
     */
    @PostMapping("/hzero/v1/users/internal")
    ResponseEntity<String> createUserInternal(@RequestBody UserDTO user,
                                              @ApiParam(value = "创建类型：手机-phone/邮箱-email") @RequestParam(required = false) String type,
                                              @ApiParam(value = "是否校验验证码") @RequestParam(required = false, defaultValue = "true") boolean validateCaptcha,
                                              @ApiParam(value = "验证码Key：发送验证码时返回的 captchaKey") @RequestParam(required = false) String captchaKey,
                                              @ApiParam(value = "验证码：用户输入的验证码") @RequestParam(required = false) String captcha);
}
