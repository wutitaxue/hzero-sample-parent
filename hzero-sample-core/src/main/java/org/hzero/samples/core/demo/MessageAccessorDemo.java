package org.hzero.samples.core.demo;

import org.hzero.core.helper.LanguageHelper;
import org.hzero.core.message.MessageAccessor;

/**
 * 消息调用示例
 *
 * @author bojiangzhou 2020/01/19
 */
public class MessageAccessorDemo {


    /**
     * 程序中获取多语言消息时，可使用 {@link MessageAccessor} 来获取
     *
     * MessageAccessor 首先从Redis缓存中获取消息，多语言消息可在 [后端返回消息管理] 功能下去维护 <br/>
     *
     * 如果缓存中没有则从 classpath 下的 message_xx.properties 里获取，使用者可提供默认消息。 <br/>
     *
     * 同时，MessageAccessor 的静态方法返回的 Message 对象不会为 null，可放心使用。
     */
    public void getMessage() {

        // 获取消息：demo.warn.emailNotBlank=邮箱不能为空
        String msg1 = MessageAccessor.getMessage("demo.warn.emailNotBlank").getDesc();

        // 获取带参数的消息：demo.warn.usernameLengthError=用户名长度在{0}-{1}
        String msg2 = MessageAccessor.getMessage("demo.warn.usernameLengthError", new Object[]{6, 30}).getDesc();

        // 指定默认消息，没有则使用默认消息
        String msg3 = MessageAccessor.getMessage("demo.warn.emailNotBlank", "Email is blank.").getDesc();

        // 指定消息的语言
        String msg4 = MessageAccessor.getMessage("demo.warn.emailNotBlank", LanguageHelper.locale()).getDesc();
    }

}
