package org.hzero.sample.api.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.boot.message.entity.Receiver;

/**
 * 站内消息DTO
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/14 9:55
 */
@ApiModel("站内消息DTO")
public class WebMessageDTO {

    @ApiModelProperty("租户ID")
    private Long tenantId;
    @ApiModelProperty("消息模板编码")
    private String messageTemplateCode;
    @ApiModelProperty("语言")
    private String lang;
    @ApiModelProperty("接收人列表")
    private List<Receiver> receivers;
    @ApiModelProperty("消息替换参数")
    private Map<String, String> args;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "WebMessageDTO{" + "tenantId=" + tenantId + ", messageTemplateCode='" + messageTemplateCode + '\''
                        + ", lang='" + lang + '\'' + ", receivers=" + receivers + ", args=" + args + '}';
    }
}
