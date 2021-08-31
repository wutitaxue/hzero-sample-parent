package org.hzero.sample.api.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 钉钉消息DTO
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/14 11:00
 */
@ApiModel("钉钉消息DTO")
public class DingTalkMessageDTO {

    @ApiModelProperty("租户ID")
    private Long organizationId;
    @ApiModelProperty("钉钉配置编码")
    private String serverCode;
    @ApiModelProperty("消息模板编码")
    private String messageTemplateCode;
    @ApiModelProperty("语言")
    private String lang;
    @ApiModelProperty("企业应用ID")
    private Long agentId;
    @ApiModelProperty("接收人用户列表")
    private List<String> userList;
    @ApiModelProperty("部门列表")
    private List<String> deptIdList;
    @ApiModelProperty("发送至所有人")
    private Boolean toAllUser;
    @ApiModelProperty("消息参数")
    private Map<String, String> args;


    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getMessageTemplateCode() {
        return messageTemplateCode;
    }

    public void setMessageTemplateCode(String messageTemplateCode) {
        this.messageTemplateCode = messageTemplateCode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<String> deptIdList) {
        this.deptIdList = deptIdList;
    }

    public Boolean getToAllUser() {
        return toAllUser;
    }

    public void setToAllUser(Boolean toAllUser) {
        this.toAllUser = toAllUser;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "DingTalkMessageDTO{" + "organizationId=" + organizationId + ", serverCode='" + serverCode + '\''
                        + ", messageTemplateCode='" + messageTemplateCode + '\'' + ", lang='" + lang + '\''
                        + ", agentId=" + agentId + ", userList=" + userList + ", deptIdList=" + deptIdList
                        + ", toAllUser=" + toAllUser + ", args=" + args + '}';
    }
}
