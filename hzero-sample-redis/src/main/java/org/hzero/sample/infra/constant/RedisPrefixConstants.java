package org.hzero.sample.infra.constant;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/17 15:16
 */
public interface RedisPrefixConstants {
    String PREFIX = "demo";
    String STRING_KEY = PREFIX + ":string:";
    String HASH_KEY = PREFIX + ":hash:";
    String LIST_KEY = PREFIX + ":list:";
    String SET_KEY = PREFIX + ":set:";
    String Z_SET_KEY = PREFIX + ":zset:";
    String USER_KEY = PREFIX + ":user";
}
