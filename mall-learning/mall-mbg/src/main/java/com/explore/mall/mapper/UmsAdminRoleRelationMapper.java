package com.explore.mall.mapper;

import com.explore.mall.model.UmsResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsAdminRoleRelationMapper {
    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    UmsResource getResource();
}
