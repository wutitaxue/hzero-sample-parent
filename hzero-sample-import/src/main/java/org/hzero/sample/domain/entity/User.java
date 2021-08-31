package org.hzero.sample.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author shuangfei.zhu@hand-china.com 2020-01-06 11:36:39
 */
@ApiModel("用户")
@VersionAudit
@ModifyAudit
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AuditDomain {

    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_AGE = "age";
    public static final String FIELD_DESCRIPTION = "description";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------

    @ApiModelProperty("表ID，主键，供其他表做外键")
    @Id
    @GeneratedValue
    private Long userId;
    @ApiModelProperty(value = "姓名")
    @NotBlank
    private String userName;
    @ApiModelProperty(value = "年龄")
    @NotNull
    private Integer age;
    @ApiModelProperty(value = "描述")
    private String description;

    //
    // 非数据库字段
    // ------------------------------------------------------------------------------

    //
    // getter/setter
    // ------------------------------------------------------------------------------


    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }
}
