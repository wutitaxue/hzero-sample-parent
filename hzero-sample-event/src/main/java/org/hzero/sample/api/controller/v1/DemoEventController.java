package org.hzero.sample.api.controller.v1;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.platform.event.EventScheduler;
import org.hzero.core.util.Results;
import org.hzero.sample.config.EventSwaggerApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.swagger.annotation.Permission;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/17 10:54
 */
@Api(tags = EventSwaggerApiConfig.DEMO)
@RestController("demoMessageController.v1")
@RequestMapping("/v1/demos")
public class DemoEventController {
    private final EventScheduler eventScheduler;

    @Autowired
    public DemoEventController(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }


    @ApiOperation(value = "事件规则示例")
    @Permission(permissionLogin = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "事件规则", defaultValue = "1", dataType = "Integer", paramType = "query",
                    allowableValues = "1,2,3")
    })
    @GetMapping("/do-event")
    public ResponseEntity<String> doEvent(Integer number) throws Exception {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("number", number);
        //根据事件规则条件，匹配事件执行方法
        Object demo_event = eventScheduler.scheduler("DEMO_EVENT", condition);
        return Results.success(demo_event.toString());
    }
}
