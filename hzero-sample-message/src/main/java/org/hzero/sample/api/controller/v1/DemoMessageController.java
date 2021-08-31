package org.hzero.sample.api.controller.v1;

import java.util.List;
import java.util.Map;

import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.MessageSender;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.core.util.Results;
import org.hzero.sample.api.dto.*;
import org.hzero.sample.app.service.MessageService;
import org.hzero.sample.config.SwaggerApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * demo Controller
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/13 9:41
 */
@Api(tags = SwaggerApiConfig.DEMO)
@RestController("demoMessageController.v1")
@RequestMapping("/v1/{organizationId}/demos")
@SuppressWarnings("all")
public class DemoMessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("生成消息内容")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({@ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true),
            @ApiImplicitParam(name = "templateCode", value = "消息模板编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "serverTypeCode", value = "服务类型编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "sqlEnable", value = "是否使用SQLValue", paramType = "query", dataType = "boolean",
                            required = true),
            @ApiImplicitParam(name = "lang", value = "语言", paramType = "query", required = true),
            @ApiImplicitParam(name = "args", value = "消息替换参数", paramType = "body")})
    @PostMapping("/generate-message")
    public ResponseEntity<Message> generateMessage(@PathVariable("organizationId") long organizationId,
                    @RequestParam("templateCode") String templateCode,
                    @RequestParam("serverTypeCode") String serverTypeCode, @RequestBody Map<String, String> args,
                    @RequestParam("sqlEnable") boolean sqlEnable, @RequestParam("lang") String lang) {
        return Results.success(messageService.generateMessage(organizationId, templateCode, serverTypeCode, args,
                        sqlEnable, lang));
    }

    @ApiOperation("获取消息接收人")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({@ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true),
            @ApiImplicitParam(name = "args", value = "自定义参数", paramType = "body", required = false),
            @ApiImplicitParam(name = "receiverTypeCode", value = "消息接收人编码", paramType = "query", required = true)})
    @GetMapping("/receivers")
    public ResponseEntity<List<Receiver>> getReceiver(@PathVariable("organizationId") long organizationId,
                    @RequestParam("receiverTypeCode") String receiverTypeCode) {
        return Results.success(messageService.receiver(organizationId, receiverTypeCode));
    }

    @ApiOperation("站内消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-web")
    public ResponseEntity sendWeb(@PathVariable("organizationId") long organizationId,
                    @RequestBody WebMessageDTO webMessages) {
        messageService.sendWebMessage(organizationId, webMessages.getMessageTemplateCode(), webMessages.getLang(),
                        webMessages.getReceivers(), webMessages.getArgs());
        return Results.success();
    }

    @ApiOperation("站内消息发送--测试消息接收配置")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-web-receiver")
    public ResponseEntity sendWebReceiver(@PathVariable("organizationId") long organizationId,
            @RequestBody MessageSender messageSender) {
        messageSender.setTenantId(organizationId);
        messageService.sendWebReceiverMessage(messageSender);
        return Results.success();
    }

    @ApiOperation("邮件发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@PathVariable("organizationId") long organizationId,
                    @RequestBody EmailMessageDTO emailMessage) {
        messageService.sendEmail(organizationId, emailMessage.getServerCode(), emailMessage.getMessageTemplateCode(),
                        emailMessage.getLang(), emailMessage.getReceivers(), emailMessage.getArgs());
        return Results.success();
    }

    @ApiOperation("短信发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-sms")
    public ResponseEntity sendSms(@PathVariable("organizationId") long organizationId,
                    @RequestBody SMSMessageDTO smsMessage) {
        messageService.sendSms(organizationId, smsMessage.getServerCode(), smsMessage.getMessageTemplateCode(),
                        smsMessage.getLang(), smsMessage.getReceivers(), smsMessage.getArgs());
        return Results.success();
    }

    @ApiOperation("消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-message")
    public ResponseEntity sendMessage(@PathVariable("organizationId") long organizationId,
                    @RequestBody MessageDTO message) {
        messageService.sendMessage(organizationId, message.getMessageTemplateCode(), message.getLang(),
                        message.getReceivers(), message.getArgs(), message.getTypeCodeList());
        return Results.success();
    }

    @ApiOperation("接收组消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-receiver-message")
    public ResponseEntity sendReceiverGroupMessage(@PathVariable("organizationId") long organizationId,
            @RequestBody MessageDTO message) {
        messageService.sendReceiverGroupMessage(organizationId, message.getMessageTemplateCode(), message.getLang(),
                message.getReceiverGroupCode(), message.getArgs(), message.getTypeCodeList());
        return Results.success();
    }

    @ApiOperation("长连接消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({@ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query", required = true),
            @ApiImplicitParam(name = "key", value = "自定义的key", paramType = "query", required = true),
            @ApiImplicitParam(name = "message", value = "发送的消息内容", paramType = "query", required = true)})
    @PostMapping("/send-socket")
    public ResponseEntity sendSocket(@RequestParam("userId") Long userId, @RequestParam("key") String key,
                    @RequestParam("message") String message) {
        messageService.sendSockMessage(userId, key, message);
        return Results.success();
    }

    @ApiOperation("微信公众号消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-wechat-official")
    public ResponseEntity sendWeChatOfficial(@PathVariable("organizationId") long organizationId,
                    @RequestBody WechatOfficialMessageDTO officialMessage) {
        messageService.sendWeChatOfficial(organizationId, officialMessage.getServerCode(),
                        officialMessage.getMessageTemplateCode(), officialMessage.getUserList(),
                        officialMessage.getData());
        return Results.success();
    }


    @ApiOperation("企业微信消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-wechat-enterprise")
    public ResponseEntity sendWeChatEnterprise(@PathVariable("organizationId") long organizationId,
                    @RequestBody WechatEnterpriseMessageDTO enterpriseMessage) {
        messageService.sendWeChatEnterprise(organizationId, enterpriseMessage.getServerCode(),
                        enterpriseMessage.getMessageTemplateCode(), enterpriseMessage.getAgentId(),
                        enterpriseMessage.getUserList(), enterpriseMessage.getData());
        return Results.success();
    }


    @ApiOperation("钉钉消息发送")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "租户Id", paramType = "path", required = true)})
    @PostMapping("/send-dingtalk")
    public ResponseEntity sendDingTalk(@PathVariable("organizationId") Long organizationId,
                    @RequestBody DingTalkMessageDTO dingTalkMessage) {
        messageService.sendDingTalkMessage(organizationId, dingTalkMessage.getServerCode(),
                        dingTalkMessage.getMessageTemplateCode(), dingTalkMessage.getLang(), dingTalkMessage.getArgs(),
                        dingTalkMessage.getAgentId(), dingTalkMessage.getUserList(), dingTalkMessage.getDeptIdList(),
                        dingTalkMessage.getToAllUser());
        return Results.success();
    }

}
