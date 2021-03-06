package com.explore.mall.service;

import com.explore.mall.dto.UmsAdminParam;
import com.explore.mall.model.UmsAdmin;
import com.explore.mall.model.UmsPermission;
import com.explore.mall.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
   List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 获取用户对于角色
     */
    //List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
