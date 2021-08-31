该文档介绍sample-message下面的一些接口的示例调用参数。
1. 生成消息内容

```text
{"organizationId": 0,
"templateCode": "HIAM.CAPTCHA",
"serverTypeCode": "HZERO_BAIDU",
"sqlEnable": false,
"lang": "zh_CN",
"args": {"captcha":"015963"}
}
```



2. 获取消息接收人

```text
{
    "organizationId": 0,
"receiverTypeCode": "TEST";
}
```



3. 发送站内消息

```text
{
  "args": {"processName":"测试消息","processDescription":"测试消息描述"},
  "lang": "zh_CN",
  "messageTemplateCode": "HWFP.REMIND",
  "receivers": [
    {
      "targetUserTenantId": 0,
      "userId": 1
    }
  ],
  "tenantId": 0
}
```



3.1 个人中心关闭站内消息接收配置后测试

```
{
  "args": {"processName":"测试消息","processDescription":"测试消息描述"},
  "lang": "zh_CN",
  "messageCode": "HWFP.REMIND",
  "receiveConfigCode": "OVERALL",
  "receiverAddressList": [
    {
      "targetUserTenantId": 0,
      "userId": 1
    }
  ],
  "tenantId": 0,
  "typeCodeList": [
    "WEB"
  ]
}
```



4. 发送邮件消息

```
{
  "args": {"processName":"测试消息","processDescription":"测试消息描述"},
  "lang": "zh_CN",
  "messageTemplateCode": "HWFP.REMIND",
  "receivers": [
    {
      "email": "xiaoyu.zhao@hand-china.com"
    }
  ],
  "serverCode": "HZERO",
  "tenantId": 0
}
```



5. 短信发送

```
{
  "args": {"captcha":"123456"},
  "lang": "zh_CN",
  "messageTemplateCode": "HIAM.CAPTCHA",
  "receivers": [
    {
      "idd": "+86",
      "phone": "15774504879"
    }
  ],
  "serverCode": "HZERO",
  "tenantId": 0
}
```



6. 消息发送

```
{
  "args": {"processName":"测试消息","processDescription":"测试消息描述"},
  "lang": "zh_CN",
  "messageTemplateCode": "HWFP.REMIND",
  "receivers": [
    {
      "targetUserTenantId": 0,
      "userId": 1
    }
  ],
  "tenantId": 0,
  "typeCodeList": [
    "WEB"
  ]
}
```



6.1 消息发送关联接收组

```
{
  "args": {"processName":"测试消息","processDescription":"测试消息描述"},
  "lang": "zh_CN",
  "messageTemplateCode": "HWFP.REMIND",
  "receiverGroupCode": "SIMPLE-MESSAGE",
  "tenantId": 0,
  "typeCodeList": [
    "WEB"
  ]
}
```



6.2 不使用消息模板发送消息

```
long tenantId = 0L;
String serverCode = "HZERO";
String subject = "今天吃点啥？";
String content = "吃个麻辣烫怎么样！";
Receiver receiver = new Receiver().setEmail("hzerofirst@hand-china.com");
// 抄送人
String ccAddress = "hzerosecond@hand-china.com";
// 密送人
String bccAddress = "hzerothird@hand-china.com";
```




7. 发送长连接消息

```
organizationId: 0
userId = 1;
String key = "hzero-web";
String message = "Hello World";
```



8. 发送微信公众号消息

```
long tenantId = 0L;
String serverCode = "TEST";
String messageTemplateCode = "WE_CHAT";
List<String> userList = new ArrayList<>();
// 微信用户的openID
userList.add("oZjfCs4W9gU2qsD6CiuKGtfPzDPE");
Map<String, WeChatFont> data = new HashMap<>(4);
data.put("name", new WeChatFont().setValue("开发人员").setColor("#173177"));
```



 9. 发送企业微信消息

```
long tenantId = 0L;
String serverCode = "TEST";
String messageTemplateCode = "WE_CHAT";
// 应用ID，微信企业号下面的应用Id
long agentId = 334455L;
List<String> userIdList = new ArrayList<>();
userIdList.add("20999");
Map<String, String> data = new HashMap<>(4);
data.put("name", "开发人员");
```



9. 发送钉钉消息

```
long tenantId = 0L;
String serverCode = "TEST";
String messageTemplateCode = "WE_CHAT";
String lang = "zh_CN";
Map<String, String> args = new HashMap<>(4);
args.put("name", "开发人员");
// 应用ID，钉钉账号下面的应用Id
long agentId = 334455L;
List<String> userIdList = new ArrayList<>();
userIdList.add("20999");
List<String> deptIdList = new ArrayList<>();
deptIdList.add("1234");
```

