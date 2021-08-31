package org.hzero.samples.core.utils;

import org.hzero.core.util.Regexs;

/**
 * 正则工具类，提供了常见正则表达式，常用正则匹配方法
 *
 * @author bojiangzhou 2020/01/20
 */
public class RegexsDemo {

    public void demo() {

        // 正则表达式
        String integer = Regexs.INTEGER; // 整数
        String email = Regexs.EMAIL; // 邮件
        String url = Regexs.URL; // URL
        String chinese = Regexs.CHINESE; // 中文
        // .......

        // 正则匹配方法
        // 判断是否是手机号
        boolean isPhone = Regexs.is("185666444", Regexs.MOBILE);
        // 判断是否是中文
        boolean isChinese = Regexs.is("是否中文", Regexs.CHINESE);
        // 判断是否是数字
        boolean isNumber = Regexs.isNumber("12345");
        // 判断是否是邮箱
        boolean isEmail = Regexs.isEmail("hzero@hand-china.com");
        // 判断是否是IP地址
        boolean isIp = Regexs.isIP("192.168.12.101");
        // 判断是否是URL地址
        boolean isUrl = Regexs.isUrl("www.baidu.com");
        // 判断是否是移动手机号
        boolean isMobile = Regexs.isMobile("18523226666", "+86");

    }

}
