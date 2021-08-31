package org.hzero.sample.api.dto;

import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户角色关系
 *
 * @author bojiangzhou 2020/01/19
 */
public class MemberRoleDTO {

    @ApiModelProperty("角色路径")
    @Transient
    private String levelPath;

    public String getLevelPath() {
        return levelPath;
    }

    public void setLevelPath(String levelPath) {
        this.levelPath = levelPath;
    }
}
