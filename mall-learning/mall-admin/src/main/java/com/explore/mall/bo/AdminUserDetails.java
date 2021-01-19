package com.explore.mall.bo;

import com.explore.mall.model.UmsAdmin;
import com.explore.mall.model.UmsResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
/**
 * SpringSecurity需要的用户详情
 * create xinrongliao 2021/1/17
 * */
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;
    private List<com.explore.mall.model.UmsResource> UmsResource;
    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsResource> UmsResource) {
        this.umsAdmin = umsAdmin;
        this.UmsResource = UmsResource;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return UmsResource.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
