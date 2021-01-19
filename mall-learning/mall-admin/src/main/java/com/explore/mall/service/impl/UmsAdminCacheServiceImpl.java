package com.explore.mall.service.impl;

import com.explore.mall.dao.UmsAdminRoleRelationDao;
import com.explore.mall.mapper.UmsAdminRoleRelationMapper;
import com.explore.mall.model.UmsAdmin;
import com.explore.mall.model.UmsResource;
import com.explore.mall.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public void delAdmin(Long adminId) {

    }

    @Override
    public void delResourceList(Long adminId) {

    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        return null;
    }

    @Override
    public void setAdmin(UmsAdmin admin) {

    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        System.out.println(umsAdminRoleRelationMapper.getResource());
        return umsAdminRoleRelationMapper.getResourceList(adminId);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
