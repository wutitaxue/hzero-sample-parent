package org.hzero.sample.app.service;

import org.hzero.sample.api.dto.RoleDTO;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/19 10:38
 */
public interface DemoCacheValueService {
    /**
     * 获取角色，使用CacheValue功能
     *
     * @return 角色对象
     */
    RoleDTO getRoleDTO();
}
