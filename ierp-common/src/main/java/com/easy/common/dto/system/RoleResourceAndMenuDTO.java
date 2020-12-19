package com.easy.common.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 13:35
 * @Version 1.0
 **/
@Data
public class RoleResourceAndMenuDTO {

    //角色id
    @ApiModelProperty(value = "角色id",required = true)
    @NotNull(message = "角色id必须指定")
    private Long roleId;


    //菜单或者资源id集合
    @ApiModelProperty(value = "资源或者菜单id集合")
    private List<Long> ids;



}
