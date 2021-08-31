package org.hzero.samples.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.hzero.core.util.Reflections;

/**
 * 反射工具类
 *
 * @author bojiangzhou 2020/01/20
 */
public class ReflectionsDemo {

    public void demo() {
        // 获取类中的字段
        Field field = Reflections.getField(Demo.class, "loginName");

        // 获取Class的所有字段，以及父类字段
        Field[] allFields = Reflections.getAllField(Demo.class);

        // 通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处. 如无法找到, 返回Object.class.
        // 结果：org.hzero.samples.core.utils.ReflectionsDemo$Demo
        Class clazz = Reflections.getClassGenericType(DemoArray.class);

        System.out.println(clazz);
    }

    public static class Demo {

        private String loginName;
        private String email;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class DemoArray extends ArrayList<Demo> {

    }


    public static void main(String[] args) {
        new ReflectionsDemo().demo();
    }

}
