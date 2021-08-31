package org.hzero.sample.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.MessageSender;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.message.entity.WeChatFont;
import org.hzero.sample.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu.zhao@hand-china.com 2020/01/06
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageClient messageClient;

    @Autowired
    public MessageServiceImpl(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    @Override
    public Message generateMessage(long tenantId, String templateCode, String serverTypeCode, Map<String, String> args,
                    boolean sqlEnable, String lang) {
        // 生成消息内容
        return messageClient.generateMessage(tenantId, templateCode, serverTypeCode, args, false, lang);
    }

    @Override
    public List<Receiver> receiver(long tenantId, String receiverTypeCode) {
        // 获取消息接收人列表
        return messageClient.receiver(tenantId, receiverTypeCode, new HashMap<>(8));
    }

    @Override
    public void sendWebMessage(long tenantId, String messageTemplateCode, String lang, List<Receiver> receiverList,
                    Map<String, String> args) {
        // 同步发送站内消息
        messageClient.sendWebMessage(tenantId, messageTemplateCode, lang, receiverList, args);
        // 异步发送站内消息
        // messageClient.async().sendWebMessage(tenantId, messageTemplateCode, lang,
        // Collections.singletonList(receiver), args);
    }

    @Override
    public void sendEmail(long tenantId, String serverCode, String messageTemplateCode, String lang,
                    List<Receiver> receiverList, Map<String, String> args) {
        // 同步发送邮件消息
        messageClient.sendEmail(tenantId, serverCode, messageTemplateCode, lang, receiverList, args);
        // 异步发送邮件消息
        // messageClient.async().sendEmail(tenantId, serverCode, messageTemplateCode,
        // Collections.singletonList(receiver), args);
    }

    @Override
    public void sendCustomEmail(Long tenantId, String serverCode, String subject, String content,
                    List<Receiver> receiverList, List<String> ccList, List<String> bccList) {
        // 不使用消息模板发送消息，手动设置抄送人和密送人
        messageClient.sendCustomEmail(tenantId, serverCode, subject, content, receiverList, ccList, bccList);
    }

    @Override
    public void sendSms(long tenantId, String serverCode, String messageTemplateCode, String lang,
                    List<Receiver> receiverList, Map<String, String> args) {
        // 同步发送短信消息
        messageClient.sendSms(tenantId, serverCode, messageTemplateCode, receiverList, args);
        // 异步发送短信消息
        // messageClient.async().sendSms(tenantId, serverCode, messageTemplateCode,
        // Collections.singletonList(receiver), args);
    }

    @Override
    public void sendMessage(long tenantId, String messageCode, String lang, List<Receiver> receiverAddressList,
                    Map<String, String> args, List<String> typeCodeList) {
        // 发送消息
        messageClient.sendMessage(tenantId, messageCode, lang, receiverAddressList, args, typeCodeList);
    }

    @Override
    public void sendReceiverGroupMessage(long organizationId, String messageTemplateCode, String lang,
            String receiverGroupCode, Map<String, String> args, List<String> typeCodeList) {
        // 发送消息（发送给指定接收组下面的用户）
        messageClient.sendMessage(organizationId, messageTemplateCode, lang, receiverGroupCode, args, typeCodeList);
    }

    @Override
    public void sendSockMessage(Long userId, String key, String message) {
        // 指定用户，发送长连接消息
        messageClient.sendByUserId(userId, key, message);
        // 发送给所有用户
        // messageClient.sendToAll(key, message);
    }

    @Override
    public void sendWeChatOfficial(long tenantId, String serverCode, String messageTemplateCode, List<String> userList,
                    Map<String, WeChatFont> data) {
        // 同步发送微信公众号消息
        messageClient.sendWeChatOfficialMessage(tenantId, serverCode, messageTemplateCode, userList, data);
        // 异步发送微信公众号消息
        // messageClient.async().sendWeChatOfficialMessage(tenantId, serverCode, messageTemplateCode,
        // userList, data);
    }

    @Override
    public void sendWeChatEnterprise(long tenantId, String serverCode, String messageTemplateCode, Long agentId,
                    List<String> userList, Map<String, String> args) {
        // 同步发送企业微信消息
        messageClient.sendWeChatEnterpriseMessage(tenantId, serverCode, messageTemplateCode, agentId, userList, args);
        // 异步发送企业微信消息
        // messageClient.async().sendWeChatEnterpriseMessage(tenantId, serverCode, messageTemplateCode,
        // agentId, userList, data);
    }

    @Override
    public void sendDingTalkMessage(Long tenantId, String serverCode, String messageTemplateCode, String lang,
                    Map<String, String> args, Long agentId, List<String> userIdList, List<String> deptIdList,
                    boolean toAllUser) {
        // 发送钉钉消息
        messageClient.sendDingTalkMessage(tenantId, serverCode, messageTemplateCode, lang, args, agentId, userIdList,
                        deptIdList, false);
        // 异步发送钉钉消息
        // messageClient.async().sendDingTalkMessage(tenantId, serverCode, messageTemplateCode, lang, args,
        // agentId, userIdList, deptIdList, false);
    }

    @Override
    public void sendWebReceiverMessage(MessageSender messageSender) {
        // 发送消息
        messageClient.sendMessage(messageSender);
    }
}
