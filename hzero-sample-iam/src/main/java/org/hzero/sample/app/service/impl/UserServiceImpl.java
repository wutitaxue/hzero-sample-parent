package org.hzero.sample.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.choerodon.core.exception.CommonException;
import io.choerodon.core.exception.ExceptionResponse;

import org.hzero.core.util.ResponseUtils;
import org.hzero.sample.api.dto.UserDTO;
import org.hzero.sample.app.service.UserService;
import org.hzero.sample.infra.feign.IamUserCreateClient;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/16 10:52
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String REGISTER_TYPE_PHONE = "phone";
    private static final String REGISTER_TYPE_EMAIL = "email";

    private final IamUserCreateClient userCreateClient;

    @Autowired
    public UserServiceImpl(IamUserCreateClient userCreateClient) {
        this.userCreateClient = userCreateClient;
    }

    /**
     *
     * @param user            用户
     * @param captchaKey      验证码Key：发送验证码时返回的 captchaKey
     * @param captcha         验证码：用户输入的验证码
     */
    @Override
    public void createUserByPhone(UserDTO user, String captchaKey, String captcha) {
        // 前置处理 ....
        // ............


        if (StringUtils.isBlank(user.getPhone())) {
            throw new IllegalArgumentException("demo.warn.phoneIsBlank");
        }
        // 初始化信息
        user.initUserDefault();

        // 调用IAM服务创建用户接口
        ResponseEntity<String> result = userCreateClient.createUserInternal(user, REGISTER_TYPE_PHONE, true, captchaKey, captcha);
        if (ResponseUtils.isFailed(result)) {
            ExceptionResponse response = ResponseUtils.getResponse(result, ExceptionResponse.class);
            LOGGER.warn("create user error, exception is {}", response);
            throw new CommonException(response.getMessage());
        }
        UserDTO createdUser = ResponseUtils.getResponse(result, UserDTO.class);

        // 后续处理....
        // ...........
        LOGGER.info("create user's loginName is {}", createdUser.getLoginName());

    }

    @Override
    public void createUserByEmail(UserDTO user, String captchaKey, String captcha) {
        // 前置处理 ....
        // ............

        if (StringUtils.isBlank(user.getEmail())) {
            throw new IllegalArgumentException("demo.warn.emailIsBlank");
        }
        // 初始化信息
        user.initUserDefault();

        // 调用IAM服务创建用户接口
        ResponseEntity<String> result = userCreateClient.createUserInternal(user, REGISTER_TYPE_EMAIL, true, captchaKey, captcha);
        if (ResponseUtils.isFailed(result)) {
            ExceptionResponse response = ResponseUtils.getResponse(result, ExceptionResponse.class);
            LOGGER.warn("create user error, exception is {}", response);
            throw new CommonException(response.getMessage());
        }
        UserDTO createdUser = ResponseUtils.getResponse(result, UserDTO.class);

        // 后续处理....
        // ...........
        LOGGER.info("create user's loginName is {}", createdUser.getLoginName());
    }
}
