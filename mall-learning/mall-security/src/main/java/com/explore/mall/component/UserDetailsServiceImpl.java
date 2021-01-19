package com.explore.mall.component;

import cn.hutool.core.collection.CollUtil;
import com.explore.mall.bo.AdminUserDetails;
import com.explore.mall.mapper.UmsAdminMapper;
import com.explore.mall.mapper.UmsAdminRoleRelationMapper;
import com.explore.mall.model.UmsAdmin;
import com.explore.mall.model.UmsAdminExample;
import com.explore.mall.model.UmsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            UmsAdmin umsAdmin=adminList.get(0);
            List<UmsResource> resourceList =getResourceList(umsAdmin.getId());
            return  new AdminUserDetails(adminList.get(0),resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public List<UmsResource> getResourceList(Long adminId) {
        return umsAdminRoleRelationMapper.getResourceList(adminId);
    }
}
