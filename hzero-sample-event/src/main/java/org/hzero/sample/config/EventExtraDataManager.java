package org.hzero.sample.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/16 9:51
 */
@ChoerodonExtraData
public class EventExtraDataManager implements ExtraDataManager {

    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("demo-event");
        choerodonRouteData.setPath("/demo-event/**");
        choerodonRouteData.setServiceId("hzero-event-sample");
        choerodonRouteData.setPackages("org.hzero.sample");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}