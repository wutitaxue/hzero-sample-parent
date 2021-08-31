package org.hzero.sample.workflow.controller;

import io.choerodon.core.exception.CommonException;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.swagger.annotation.Permission;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.boot.platform.plugin.hr.EmployeeHelper;
import org.hzero.core.base.BaseConstants;
import org.hzero.core.util.Results;
import org.hzero.sample.workflow.constant.OrderStatus;
import org.hzero.sample.workflow.entity.Order;
import org.hzero.sample.workflow.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author qingsheng.chen@hand-china.com
 */
@RestController
@RequestMapping("/v1/{organizationId}/order")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{orderId}")
    @ProcessLovValue(targetField = BaseConstants.FIELD_BODY)
    @Permission(level = ResourceLevel.ORGANIZATION, permissionLogin = true)
    public ResponseEntity<Order> queryOrder(@PathVariable long organizationId,
                                            @PathVariable long orderId) {
        return Results.success(orderRepository.findById(orderId).orElseThrow(() ->
                new CommonException(BaseConstants.ErrorCode.DATA_NOT_EXISTS)
        ));
    }

    @PostMapping
    @Permission(level = ResourceLevel.ORGANIZATION, permissionLogin = true)
    public ResponseEntity<Order> createOrder(@PathVariable long organizationId,
                                             @RequestBody Order order) {
        return Results.success(orderRepository.save(order
                .setTenantId(organizationId)
                .setOrderNumber("O" + System.currentTimeMillis())
                .setApplicant(EmployeeHelper
                        .getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId))
                .setOrderStatus(OrderStatus.CREATE.name())));
    }

    @PutMapping("/{orderId}/approve")
    @Permission(level = ResourceLevel.ORGANIZATION, permissionLogin = true)
    public ResponseEntity<Order> saveOrder(@PathVariable long organizationId,
                                           @PathVariable long orderId,
                                           @RequestBody Order param) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new CommonException(BaseConstants.ErrorCode.DATA_NOT_EXISTS)
        );
        switch (OrderStatus.valueOf(order.getOrderStatus())) {
            case CREATE:
                orderRepository.save(order
                        .setUnitPrice(param.getUnitPrice())
                        .setPurchaser(EmployeeHelper.getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId))
                        .setOrderStatus(OrderStatus.PURCHASE_APPROVAL.name()));
                break;
            case PURCHASE_APPROVAL:
                orderRepository.save(order
                        .setBalance(param.getBalance())
                        .setFinance(EmployeeHelper.getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId))
                        .setOrderStatus(OrderStatus.FINANCIAL_APPROVAL.name()));
                break;
            case FINANCIAL_APPROVAL:
                orderRepository.save(order.setOrderStatus(OrderStatus.PURCHASING.name()));
                break;
            case PURCHASING:
                orderRepository.save(order
                        .setPurchaser(EmployeeHelper.getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId))
                        .setOrderStatus(OrderStatus.EMPLOYEE_APPROVAL.name()));
                break;
            case EMPLOYEE_APPROVAL:
                orderRepository.save(order.setOrderStatus(OrderStatus.COMPLETE.name()));
                break;
            default:
                break;
        }
        return Results.success(order);
    }

    @PutMapping("/{orderId}/deduction")
    @Permission(level = ResourceLevel.ORGANIZATION, permissionLogin = true)
    public ResponseEntity<Order> deduction(@PathVariable long organizationId,
                          @PathVariable long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new CommonException(BaseConstants.ErrorCode.DATA_NOT_EXISTS)
        );
        orderRepository.save(order.setBalance(0d));
        return Results.success(order);
    }
}
