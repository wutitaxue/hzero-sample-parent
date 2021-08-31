package org.hzero.sample.api.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import org.hzero.core.base.BaseConstants;
import org.hzero.core.util.Regexs;

/**
 * 用户参数
 *
 * @author zifeng.ding@hand-china.com 2020/01/15 20:43
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    /**
     * 用户类型-平台用户
     */
    private static final String USER_TYPE_PLATFORM = "P";
    /**
     * 用户类型-C端用户
     */
    private static final String USER_TYPE_CLIENT = "C";

    /**
     * 默认角色的唯一路径，取自角色的 h_level_path 字段
     */
    private static final String DEFAULT_ROLE_LEVEL_PATH = "role/organization/default/administrator|xxxxxxxx";

    /**
     * 设置用户默认信息
     */
    public void initUserDefault() {
        // 设置用户类型，默认平台用户
        this.setUserType(UserDTO.USER_TYPE_PLATFORM);
        // 分配默认角色，必须为用户分配至少一个角色
        MemberRoleDTO mr = new MemberRoleDTO();
        mr.setLevelPath(DEFAULT_ROLE_LEVEL_PATH);
        this.setMemberRoleList(Collections.singletonList(mr));
        // 生成用户名，loginName 为空则生成默认8位数的账号
        this.setLoginName("xxxx");
        // 设置是否检查手机、邮箱、密码，NO 则需要用户到个人中心验证， YES 则不需要
        this.setPhoneCheckFlag(BaseConstants.Flag.NO);
        this.setEmailCheckFlag(BaseConstants.Flag.NO);
        this.setPasswordResetFlag(BaseConstants.Flag.NO);

        // 是否校验密码符合租户的密码策略
        this.setCheckPasswordPolicy(true);
    }

    //
    // getter/setter
    // ------------------------------------------------------------------------------

    private Long id;
    @Pattern(regexp = Regexs.CODE)
    @ApiModelProperty("登录账号，未传则生成默认账号")
    private String loginName;
    @Length(max = 128)
    @Email
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty(value = "所属租户ID", required = true)
    private Long organizationId;
    @ApiModelProperty(value = "密码", required = true)
    @Length(max = 128)
    private String password;
    @ApiModelProperty(value = "真实姓名", required = true)
    @Length(max = 128)
    private String realName;
    @ApiModelProperty(value = "手机号")
    @Length(max = 32)
    private String phone;
    @ApiModelProperty(value = "国际冠码，默认+86")
    private String internationalTelCode;
    @ApiModelProperty(value = "用户类型，中台用户-P，C端用户-C")
    private String userType;
    @ApiModelProperty(value = "用户头像地址")
    @Length(max = 480)
    private String imageUrl;
    @ApiModelProperty(value = "语言，默认 zh_CN")
    private String language;
    @ApiModelProperty(value = "时区，默认 GMT+8")
    private String timeZone;

    @ApiModelProperty(value = "用户来源")
    private Integer userSource;
    @ApiModelProperty(value = "手机号是否验证通过")
    private Integer phoneCheckFlag;
    @ApiModelProperty(value = "邮箱是否验证通过")
    private Integer emailCheckFlag;
    @ApiModelProperty(value = "密码是否重置过")
    private Integer passwordResetFlag;

    @JsonFormat(pattern = BaseConstants.Pattern.DATE)
    @DateTimeFormat(pattern = BaseConstants.Pattern.DATE)
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "国家ID")
    private Long countryId;
    @ApiModelProperty(value = "区域ID")
    private Long regionId;
    @ApiModelProperty(value = "地址详细")
    private String addressDetail;

    /**
     * 是否检查密码是否符合密码策略，为 非null 且 false 时，不检查密码策略
     */
    @ApiModelProperty("是否检查密码是否符合密码策略")
    @Transient
    private Boolean checkPasswordPolicy;
    /**
     * 分配默认的角色
     */
    @Transient
    @ApiModelProperty("用户角色列表")
    private List<MemberRoleDTO> memberRoleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInternationalTelCode() {
        return internationalTelCode;
    }

    public void setInternationalTelCode(String internationalTelCode) {
        this.internationalTelCode = internationalTelCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public Integer getPhoneCheckFlag() {
        return phoneCheckFlag;
    }

    public void setPhoneCheckFlag(Integer phoneCheckFlag) {
        this.phoneCheckFlag = phoneCheckFlag;
    }

    public Integer getEmailCheckFlag() {
        return emailCheckFlag;
    }

    public void setEmailCheckFlag(Integer emailCheckFlag) {
        this.emailCheckFlag = emailCheckFlag;
    }

    public Integer getPasswordResetFlag() {
        return passwordResetFlag;
    }

    public void setPasswordResetFlag(Integer passwordResetFlag) {
        this.passwordResetFlag = passwordResetFlag;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Boolean getCheckPasswordPolicy() {
        return checkPasswordPolicy;
    }

    public void setCheckPasswordPolicy(Boolean checkPasswordPolicy) {
        this.checkPasswordPolicy = checkPasswordPolicy;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
