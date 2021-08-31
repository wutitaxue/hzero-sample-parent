package org.hzero.sample.workflow.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.platform.lov.annotation.ProcessLovValue;
import org.hzero.core.base.BaseConstants;
import org.hzero.sample.workflow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author qingsheng.chen@hand-china.com
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT " +
            "so.order_id AS orderId," +
            "so.tenant_id AS tenantId," +
            "so.order_number AS orderNumber," +
            "so.applicant," +
            "hea.name AS applicantName," +
            "so.purchaser," +
            "hep.name AS purchaserName," +
            "so.finance," +
            "hef.name AS financeName," +
            "so.produce_name AS produceName," +
            "so.unit_price AS unitPrice," +
            "so.quantity," +
            "so.order_status orderStatus," +
            "so.balance " +
            "FROM sample_order so " +
            "LEFT JOIN hzero_platform.hpfm_employee hea ON hea.employee_num = so.applicant " +
            "LEFT JOIN hzero_platform.hpfm_employee hef ON hef.employee_num = so.finance " +
            "LEFT JOIN hzero_platform.hpfm_employee hep ON hep.employee_num = so.purchaser " +
            "WHERE so.order_id = ?1", nativeQuery = true)
    Optional<Map<String, Object>> findByIdWithEmployee(Long orderId);

    @ProcessLovValue
    default Order findObject(Long orderId, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(objectMapper.writeValueAsBytes(findByIdWithEmployee(orderId).orElseThrow(() ->
                new CommonException(BaseConstants.ErrorCode.DATA_NOT_EXISTS)
        )), Order.class);
    }
}
