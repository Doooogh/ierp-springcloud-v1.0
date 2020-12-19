package com.easy.common.entity.system;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.validation.CusValidator;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * @Description 菜单
 * @Author li long
 * @Date 2020/9/7 14:14
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SysMenu extends BaseEntity {


    @ApiModelProperty(value = "父id")
    @NotNull
    private Long parentId;


    @ApiModelProperty(value ="标题")
    @NotBlank
    private String title;

    @ApiModelProperty(value ="菜单名字——英文")
    @NotBlank
    private String name;

    @ApiModelProperty(value ="菜单名字——图标")
    private String icon;

    //状态 1 正常 0停用
    @CusValidator(value = {"0","1"},message = "菜单状态->1 正常  0禁用")
    @ApiModelProperty(value ="菜单状态->1 正常  0禁用",allowableValues ="0,1")
    private Integer status;

    @ApiModelProperty(value ="排序")
    @Min(value = 0,message = "最小为0")
    @Digits(integer = 5,fraction = 0)
    private Integer sort=0;


    public SysMenu(@Null(groups = InsertActive.class) @Min(value = 0, groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, @NotNull Long parentId, @NotBlank String title, @NotBlank String name, String icon, Integer status, @Min(value = 0, message = "最小为0") @Digits(integer = 5, fraction = 0) Integer sort) {
        super(id);
        this.parentId = parentId;
        this.title = title;
        this.name = name;
        this.icon = icon;
        this.status = status;
        this.sort = sort;
    }
}
