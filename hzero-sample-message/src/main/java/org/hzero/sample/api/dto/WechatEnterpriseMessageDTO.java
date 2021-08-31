package org.hzero.sample.api.dto;

import java.util.List;
import java.util.Map;

import org.hzero.boot.message.entity.WeChatFont;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 微信企业号消息DTO
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/14 10:42
 */
@ApiModel("微信企业号消息DTO")
public class WechatEnterpriseMessageDTO {

    @ApiModelProperty("租户ID")
    private Long tenantId;
    @ApiModelProperty("企业微信号配置编码")
    private String serverCode;
    @ApiModelProperty("消息模板编码")
    private String messageTemplateCode;
    @ApiModelProperty("企业应用ID")
    private Long agentId;
    @ApiModelProperty("接收人列表")
    private List<String> userList;
    @ApiModelProperty("参数")
    private Map<String, String> data;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Override
    public String toString() {
        return "WechatEnterpriseMessageDTO{" + "tenantId=" + tenantId + ", serverCode='" + serverCode + '\''
                        + ", messageTemplateCode='" + messageTemplateCode + '\'' + ", agentId=" + agentId
                        + ", userList=" + userList + ", data=" + data + '}';
    }
}
