package org.hzero.samples.core.demo;

import org.hzero.common.HZeroService;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.redis.RedisHelper;
import org.hzero.core.redis.safe.SafeRedisHelper;

/**
 * 常量类的使用
 *
 * @author bojiangzhou 2020/01/19
 */
public class ConstantDemo {

    private RedisHelper redisHelper;

    /**
     * <h2>服务常量</h2>
     *
     * 代码中用到服务相关的常量，如服务名、服务对应的 Redis DB、服务简码等，可使用 {@link org.hzero.common.HZeroService} 中的服务常量
     */
    public void serviceConstant() {

        // 如平台服务相关

        // 服务名
        String serviceName = HZeroService.Platform.NAME;
        // 服务名常用于 Feign 调用、RestTemplate 调用等
        // @FeignClient(value = HZeroService.Platform.NAME)


        // 服务简码
        String code = HZeroService.Platform.CODE;
        // 服务简码通常用作redis缓存前缀
        String privateKey = redisHelper.strGet(HZeroService.Platform.CODE + ":encrypt:private-key");


        // RedisDB
        int redisDb = HZeroService.Platform.REDIS_DB;
        // RedisDB 通常在切换 RedisDB 时用到，例如当前 spring.redis.database=1，需要到其它服务所在db下取数据
        String clientJson = SafeRedisHelper.execute(HZeroService.Oauth.REDIS_DB, () -> {
            return redisHelper.hshGet(HZeroService.Oauth.CODE + ":client", "client");
        });


        // 服务端口
        int port = HZeroService.Platform.PORT;
        // 服务路由
        String path = HZeroService.Platform.PATH;
    }

    /**
     * <h2>基础常量</h2> 代码中可引用常量类中的常量，避免魔法值
     *
     * {@link BaseConstants} 基础常量类定义了许多最常用的基本常量，
     * 日期时间小数格式 {@link BaseConstants.Pattern},
     * 通用异常编码 {@link BaseConstants.ErrorCode},
     * 常用符号常量 {@link BaseConstants.Symbol}
     *
     * {@link BaseConstants.Pattern}
     */
    public void baseConstant() {
        // 默认租户
        long defaultTenantId = BaseConstants.DEFAULT_TENANT_ID;

        // 匿名用户
        long anonymousUserId = BaseConstants.ANONYMOUS_USER_ID;
        String anonymousUserName = BaseConstants.ANONYMOUS_USER_NAME;

        // 默认编码
        String defaultCharset = BaseConstants.DEFAULT_CHARSET;
        // 默认语言
        String defaultLang = BaseConstants.DEFAULT_LOCALE_STR;
        // 默认国际冠码
        String defaultCrown = BaseConstants.DEFAULT_CROWN_CODE;
        //默认时区
        String defaultTimeZone = BaseConstants.DEFAULT_TIME_ZONE;

        // 格式
        // yyyy-MM-dd
        String date = BaseConstants.Pattern.DATE;
        // yyyy-MM-dd HH:mm:ss
        String datetime = BaseConstants.Pattern.DATETIME;
        // yyyy/MM/dd
        String sysDate = BaseConstants.Pattern.SYS_DATE;
        // yyyy/MM/dd HH:mm:ss
        String sysDatetime = BaseConstants.Pattern.SYS_DATETIME;
        // yyyyMMdd
        String noneDate = BaseConstants.Pattern.NONE_DATE;
        // yyyyMMddHHmmss
        String noneDatetime = BaseConstants.Pattern.NONE_DATETIME;
        // 一位小数 0.0
        String oneDecimal = BaseConstants.Pattern.ONE_DECIMAL;
        // 千分位表示 无小数 #,##0
        String tbNoneDecimal = BaseConstants.Pattern.TB_NONE_DECIMAL;


        // 是/否，通常用于系统布尔值常量
        Integer yes = BaseConstants.Flag.YES;
        Integer no = BaseConstants.Flag.NO;

        // 符号常量
        // 感叹号：!
        String SIGH = BaseConstants.Symbol.SIGH;
        // 符号：@
        String AT = BaseConstants.Symbol.AT;
        // 井号：#
        String WELL = BaseConstants.Symbol.WELL;
        // 美元符：$
        String DOLLAR = BaseConstants.Symbol.DOLLAR;
        // 空格：
        String SPACE = BaseConstants.Symbol.SPACE;

        // .....
    }



}
