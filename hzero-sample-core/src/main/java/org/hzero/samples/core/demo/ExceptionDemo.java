package org.hzero.samples.core.demo;

import org.apache.commons.lang3.StringUtils;

import io.choerodon.core.exception.CommonException;

import org.hzero.core.exception.CheckedException;
import org.hzero.core.exception.IllegalOperationException;

/**
 * 异常类使用
 *
 * @author bojiangzhou 2020/01/19
 */
public class ExceptionDemo {

    /**
     * 通用异常类的使用
     *
     * 通用异常类主要有 {@link CommonException}、受检异常 {@link CheckedException}、非法操作异常 {@link IllegalOperationException} <br>
     *
     * 抛出异常时，通常需在 messages_zh_CN.properties/messages_en_US.properties 定义多语言消息，抛出异常时指定对应的编码 <br>
     *
     * 定义消息编码时，一般分三段，格式为 服务简码.类型(info/warn/error).消息编码，如：demo.warn.emailNotBlank <br>
     */
    public void commonException() throws CheckedException {

        String email = "";

        // CommonException：RuntimeException 的子类，一般用于封装程序中的业务异常
        if (StringUtils.isBlank(email)) {
            throw new CommonException("demo.warn.emailNotBlank");
        }

        try {
            // do ...
        } catch (Exception e) {
            throw new CommonException("demo.error.createUserError", e);
        }

        // CheckedException: Exception 的子类，一般用于封装高级别的错误，需上级调用者处理
        try {
            // do...
        } catch (RuntimeException e) {
            throw new CheckedException("demo.error.checkedException");
        }

    }

}
