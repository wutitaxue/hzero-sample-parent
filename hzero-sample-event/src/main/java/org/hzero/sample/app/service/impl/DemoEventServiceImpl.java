package org.hzero.sample.app.service.impl;

import org.hzero.boot.platform.event.handler.EventHandlerBean;
import org.hzero.boot.platform.event.handler.EventHandlerMethod;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/17 11:05
 */
@Service
public class DemoEventServiceImpl implements EventHandlerBean {

    @Override
    public String getBeanName() {
        return "demoEventService";
    }

    @EventHandlerMethod
    public String getOne() {
        return "one";
    }

    @EventHandlerMethod
    public String getTwo() {
        return "two";
    }

    @EventHandlerMethod
    public String getThree() {
        return "three";
    }
}
