package org.hzero.samples.core.utils;

import org.hzero.core.util.PinyinUtils;

/**
 * 拼音工具类
 *
 * @author bojiangzhou 2020/01/20
 */
public class PinyinUtilsDemo {

    public void demo() {
        String text1 = "菜单管理"; // 有多音字
        String text2 = "用户管理"; // 无多音字

        String pinyin1 = PinyinUtils.getPinyin(text1);
        String capital1 = PinyinUtils.getPinyinCapital(text1);
        // 获取中文的拼音，结果：|caidanguanli|caishanguanli|caichanguanli|
        System.out.println("pinyin1 >> " + pinyin1);
        // 获取拼音首字母，结果：|ccgl|csgl|cdgl|
        System.out.println("capital1 >> " + capital1);


        String pinyin2 = PinyinUtils.getPinyin(text2);
        String capital2 = PinyinUtils.getPinyinCapital(text2);
        // 结果：|yonghuguanli|
        System.out.println("pinyin2 >> " + pinyin2);
        // 结果：|yhgl|
        System.out.println("capital2 >> " + capital2);
    }

    public static void main(String[] args) {
        new PinyinUtilsDemo().demo();
    }

}
