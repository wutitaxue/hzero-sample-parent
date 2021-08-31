package org.hzero.sample.workflow.config;


import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;
import org.hzero.common.HZeroService;

/**
 * 服务基本信息
 *
 * @author gaokuo.dai@hand-china.com 2018年7月20日下午4:49:12
 */
@ChoerodonExtraData
public class SampleWorkflowExtraDataManager implements ExtraDataManager {

    @Override
    public ExtraData getData() {
        ChoerodonRouteData routeData = new ChoerodonRouteData();
        routeData.setName("hswf");
        routeData.setPath("/hsample-workflow/**");
        routeData.setServiceId("hzero-sample-workflow");
        routeData.setPackages("org.hzero.sample.workflow");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, routeData);
        return extraData;
    }

}