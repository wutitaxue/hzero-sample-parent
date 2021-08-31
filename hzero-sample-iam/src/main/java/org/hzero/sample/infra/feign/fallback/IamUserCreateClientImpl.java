package org.hzero.sample.infra.feign.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.hzero.sample.api.dto.UserDTO;
import org.hzero.sample.infra.feign.IamUserCreateClient;

/**
 * 用户公开操作回调
 *
 * @author zifeng.ding@hand-china.com 2020/01/15 20:26
 */
@Component
public class IamUserCreateClientImpl implements IamUserCreateClient {
    private static final Logger logger = LoggerFactory.getLogger(IamUserCreateClientImpl.class);

    @Override
    public ResponseEntity<String> createUserInternal(UserDTO user, String type, boolean validateCaptcha, String captchaKey, String captcha) {
        logger.info("Error create user {}", user);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
