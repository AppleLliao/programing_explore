package com.explore.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录参数
 * create xinrong on 2021/1/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    //@NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    //@NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
