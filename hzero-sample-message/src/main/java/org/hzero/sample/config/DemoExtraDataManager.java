package org.hzero.sample.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

/**
 * @author shuangfei.zhu@hand-china.com 2018-12-27
 */
@ChoerodonExtraData
public class DemoExtraDataManager implements ExtraDataManager {

    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("demo-message");
        choerodonRouteData.setPath("/demo-message/**");
        choerodonRouteData.setServiceId("hzero-message-sample");
        choerodonRouteData.setPackages("org.hzero.sample");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}
