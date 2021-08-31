package org.hzero.sample.api.dto;

import org.hzero.core.cache.CacheValue;
import org.hzero.core.cache.Cacheable;
import org.hzero.sample.infra.constant.RedisPrefixConstants;

/**
 * description
 *
 * @author zifeng.ding@hand-china.com 2020/01/19 10:09
 */
public class RoleDTO implements Cacheable {
    private Long id;
    private String name;
    private String code;
    private Long createdBy;
    @CacheValue(
            key = RedisPrefixConstants.USER_KEY,
            primaryKey = "createdBy",
            searchKey = "userName",
            structure = CacheValue.DataStructure.MAP_OBJECT
    )
    private String createdUserName;

    public Long getId() {
        return id;
    }

    public RoleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RoleDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public RoleDTO setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public RoleDTO setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
        return this;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createdBy=" + createdBy +
                ", createdUserName='" + createdUserName + '\'' +
                '}';
    }
}
