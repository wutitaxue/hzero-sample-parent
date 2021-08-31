package org.hzero.sample.workflow.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hzero.boot.platform.lov.annotation.LovValue;

import javax.persistence.*;

/**
 * @author qingsheng.chen@hand-china.com
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "SAMPLE_ORDER")
public class Order {

    @Id
    @Column(name = "ORDER_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "TENANT_ID", updatable = false)
    private Long tenantId;

    @Column(name = "ORDER_NUMBER", unique = true, nullable = false, length = 64, updatable = false)
    private String orderNumber;

    @Column(name = "APPLICANT", nullable = false, length = 32, updatable = false)
    private String applicant;

    @Transient
    private String applicantName;

    @Column(name = "PURCHASER", length = 32)
    private String purchaser;

    @Transient
    private String purchaserName;

    @Column(name = "FINANCE", length = 32)
    private String finance;

    @Transient
    private String financeName;

    @Column(name = "PRODUCE_NAME", nullable = false, length = 32)
    private String produceName;

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @LovValue(lovCode = "TEST.SAMPLE.ORDER_STATUS")
    @Column(name = "ORDER_STATUS", nullable = false, length = 32)
    private String orderStatus;

    @Transient
    private String orderStatusMeaning;

    @Column(name = "BALANCE")
    private Double balance;

    public Order() {
    }

    public Order(Long tenantId, String orderNumber, String applicant, String applicantName, String purchaser, String purchaserName, String finance, String financeName, String produceName, Double unitPrice, Integer quantity, String orderStatus, Double balance) {
        this.tenantId = tenantId;
        this.orderNumber = orderNumber;
        this.applicant = applicant;
        this.applicantName = applicantName;
        this.purchaser = purchaser;
        this.purchaserName = purchaserName;
        this.finance = finance;
        this.financeName = financeName;
        this.produceName = produceName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.balance = balance;
    }
}
