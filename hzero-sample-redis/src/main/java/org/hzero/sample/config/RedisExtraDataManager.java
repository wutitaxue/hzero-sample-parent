package org.hzero.sample.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/17 13:51
 */
@ChoerodonExtraData
public class RedisExtraDataManager implements ExtraDataManager {

    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("demo-redis");
        choerodonRouteData.setPath("/demo-redis/**");
        choerodonRouteData.setServiceId("hzero-redis-sample");
        choerodonRouteData.setPackages("org.hzero.sample");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}