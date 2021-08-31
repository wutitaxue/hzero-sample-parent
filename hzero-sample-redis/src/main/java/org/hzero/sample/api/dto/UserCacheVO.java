package org.hzero.sample.api.dto;

import java.io.Serializable;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/19 9:26
 */
public class UserCacheVO implements Serializable {
    private static final long serialVersionUID = -8354548764054921665L;

    private Long userId;
    private String userName;
    private Integer age;
    private String description;


    public static UserCacheVO getDemoUserCache() {
        return new UserCacheVO().setUserId(1L).setUserName("demo_user").setAge(18).setDescription("this is a demo user");
    }

    public Long getUserId() {
        return userId;
    }

    public UserCacheVO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserCacheVO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserCacheVO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserCacheVO setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "UserCacheVO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
