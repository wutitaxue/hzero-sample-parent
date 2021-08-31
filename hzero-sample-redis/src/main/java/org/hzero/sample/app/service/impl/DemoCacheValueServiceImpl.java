package org.hzero.sample.app.service.impl;

import org.hzero.core.cache.ProcessCacheValue;
import org.hzero.sample.api.dto.RoleDTO;
import org.hzero.sample.api.dto.UserCacheVO;
import org.hzero.sample.app.service.DemoCacheValueService;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/19 10:41
 */
@Service
public class DemoCacheValueServiceImpl implements DemoCacheValueService {
    @Override
    @ProcessCacheValue
    public RoleDTO getRoleDTO() {
        //返回demo 角色，创建者id为缓存中存储的demo 用户id
        return new RoleDTO().setCode("DEMO_ROLE").setId(1L).setName("demo_role").setCreatedBy(UserCacheVO.getDemoUserCache().getUserId());
    }

}
