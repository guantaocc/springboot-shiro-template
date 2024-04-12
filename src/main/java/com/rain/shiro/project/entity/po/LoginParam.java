package com.rain.shiro.project.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录对象")
public class LoginParam {

    @NotBlank(message = "用户账号不能为空")
    @ApiModelProperty(value = "用户账号")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码（/captcha 接口获取）")
    private String code;

    @NotBlank(message = "唯一标识不能为空")
    @ApiModelProperty(value = "唯一标识（/captcha 接口获取）")
    private String uuid;

}
