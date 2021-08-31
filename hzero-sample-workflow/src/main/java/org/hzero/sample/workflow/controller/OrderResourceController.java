package org.hzero.sample.workflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.swagger.annotation.Permission;
import org.hzero.core.util.TokenUtils;
import org.hzero.sample.workflow.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qingsheng.chen@hand-china.com
 */
@Controller
@RequestMapping("/v1/resource")
public class OrderResourceController {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderResourceController(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/order.html")
    @Permission(permissionLogin = true)
    public ModelAndView orderView(@RequestParam(required = false) Long orderId,
                                  @RequestParam(required = false, defaultValue = "false") boolean readOnly) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("organizationId", DetailsHelper.getUserDetails().getTenantId());
        model.put("accessToken", TokenUtils.getToken());
        model.put("readOnly", readOnly);
        if (orderId != null) {
            model.put("order", orderRepository.findObject(orderId, objectMapper));
        }
        return new ModelAndView("order", model);
    }
}
