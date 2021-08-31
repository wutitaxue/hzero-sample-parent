package org.hzero.sample.api.dto;

import java.util.List;
import java.util.Map;

import org.hzero.boot.message.entity.WeChatFont;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 微信公众号消息DTO
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/14 10:42
 */
@ApiModel("微信公众号消息DTO")
public class WechatOfficialMessageDTO {

    @ApiModelProperty("租户ID")
    private Long tenantId;
    @ApiModelProperty("公众号配置编码")
    private String serverCode;
    @ApiModelProperty("消息模板编码")
    private String messageTemplateCode;
    @ApiModelProperty("接收人列表，存放微信用户的openId")
    private List<String> userList;
    @ApiModelProperty("参数")
    private Map<String, WeChatFont> data;

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

    public Map<String, WeChatFont> getData() {
        return data;
    }

    public void setData(Map<String, WeChatFont> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WechatOfficialMessageDTO{" + "tenantId=" + tenantId + ", serverCode='" + serverCode + '\''
                        + ", messageTemplateCode='" + messageTemplateCode + '\'' + ", userList=" + userList + ", data="
                        + data + '}';
    }
}
