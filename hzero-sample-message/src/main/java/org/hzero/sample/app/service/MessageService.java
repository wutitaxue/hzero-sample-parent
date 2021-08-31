package org.hzero.sample.app.service;

import java.util.List;
import java.util.Map;

import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.MessageSender;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.message.entity.WeChatFont;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/06 14:12
 */
public interface MessageService {

    /**
     * 生成消息内容
     *
     * @return 消息内容
     */
    Message generateMessage(long tenantId, String templateCode, String serverTypeCode, Map<String, String> args,
                    boolean sqlEnable, String lang);

    /**
     * 获取消息接收人
     *
     * @return 消息接收人
     */
    List<Receiver> receiver(long tenantId, String receiverTypeCode);

    /**
     * 发送站内消息
     */
    void sendWebMessage(long tenantId, String messageTemplateCode, String lang, List<Receiver> receiverList,
                    Map<String, String> args);

    /**
     * 发送邮件
     */
    void sendEmail(long tenantId, String serverCode, String messageTemplateCode, String lang,
                    List<Receiver> receiverList, Map<String, String> args);

    /**
     * 不使用消息模板，发送邮件
     *
     * @param tenantId 租户
     * @param serverCode 邮箱账户编码
     * @param subject 标题
     * @param content 内容
     * @param receiverList 接收人
     * @param ccList 抄送地址
     * @param bccList 密送地址
     * @return 发送结果
     */
    void sendCustomEmail(Long tenantId, String serverCode, String subject, String content, List<Receiver> receiverList,
                    List<String> ccList, List<String> bccList);

    /**
     * 发送短信
     */
    void sendSms(long tenantId, String serverCode, String messageTemplateCode, String lang, List<Receiver> receiverList,
                    Map<String, String> args);

    /**
     * 发送消息
     */
    void sendMessage(long tenantId, String messageCode, String lang, List<Receiver> receiverAddressList,
                    Map<String, String> args, List<String> typeCodeList);

    /**
     * 发送消息（关联接收组）
     */
    void sendReceiverGroupMessage(long organizationId, String messageTemplateCode, String lang,
                    String receiverGroupCode, Map<String, String> args, List<String> typeCodeList);

    /**
     * 发送长连接消息
     */
    void sendSockMessage(Long userId, String key, String message);

    /**
     * 发送公众号模板消息
     */
    void sendWeChatOfficial(long tenantId, String serverCode, String messageTemplateCode, List<String> userList,
                    Map<String, WeChatFont> data);

    /**
     * 发送企业微信消息
     */
    void sendWeChatEnterprise(long tenantId, String serverCode, String messageTemplateCode, Long agentId,
                    List<String> userList, Map<String, String> args);

    /**
     * 发送钉钉消息
     */
    void sendDingTalkMessage(Long tenantId, String serverCode, String messageTemplateCode, String lang,
                    Map<String, String> args, Long agentId, List<String> userIdList, List<String> deptIdList,
                    boolean toAllUser);

    /**
     * 测试消息接收配置，个人中心禁用接收站内信后调用该接口发送站内消息，结果应为接收不到站内信
     */
    void sendWebReceiverMessage(MessageSender messageSender);
}
