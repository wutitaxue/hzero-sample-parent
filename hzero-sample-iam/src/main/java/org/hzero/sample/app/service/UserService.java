package org.hzero.sample.app.service;

import org.hzero.sample.api.dto.UserDTO;

/**
 * 定制化创建用户服务
 *
 * @author zifeng.ding@hand-china.com 2020/01/16 10:50
 */
public interface UserService {

    /**
     * 通过手机创建新用户
     *
     * @param user            用户
     * @param captchaKey      验证码Key：发送验证码时返回的 captchaKey
     * @param captcha         验证码：用户输入的验证码
     */
    void createUserByPhone(UserDTO user, String captchaKey, String captcha);

    /**
     * 通过邮箱创建新用户
     *
     * @param user            用户
     * @param captchaKey      验证码Key：发送验证码时返回的 captchaKey
     * @param captcha         验证码：用户输入的验证码
     */
    void createUserByEmail(UserDTO user, String captchaKey, String captcha);
}
