package org.hzero.samples.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hzero.core.util.EscapeUtils;

/**
 * 转义和反转义工具类，通常用于过转义特殊字符，script 脚本等，避免注入风险
 *
 * @author bojiangzhou 2020/01/20
 */
public class EscapeUtilsDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(EscapeUtilsDemo.class);

    public void demo() {

        String href = "<a src='http://www.baidu.com'>点击链接跳转</script>";
        String script = "<script src='javascript:alert(0);'></script>";

        // 转义字符，转义结果：&#60;script src=&#039;javascript:alert(0);&#039;&#62;&#60;/script&#62;
        String escape = EscapeUtils.escape(href);
        LOGGER.info("escape result is [{}]", escape);

        // 反转义，还原被转义的HTML特殊字符
        String unescape = EscapeUtils.decode(escape);
        LOGGER.info("unescape result is [{}]", unescape);

        // 清除所有HTML标签，但是不删除标签内的内容，结果：点击链接跳转
        String clean = EscapeUtils.clean(href);
        LOGGER.info("clean result is [{}]", clean);

        // 转译script标签，结果：<script src='javascript:alert(0);'>&#60;/script&#62;
        String preventScript = EscapeUtils.preventScript(script);
        LOGGER.info("preventScript result is [{}]", preventScript);

    }

}
