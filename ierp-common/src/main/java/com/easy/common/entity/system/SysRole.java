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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/7 14:10
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SysRole extends BaseEntity {


    @ApiModelProperty(value ="名字")
    @NotBlank
    private String name;

    @ApiModelProperty(value ="英文名")
    @NotBlank
    private String enName;


    @ApiModelProperty(value ="角色状态->1正常,0禁用",allowableValues ="0,1")
    @CusValidator(value = {"0","1"},message = "状态值只能为1或者0")
    @NotNull
    private Integer status;

    @ApiModelProperty(value ="描述")
    private String description;

    private List<Long> menuIdList;

    private List<Long> resourceIdList;

    public SysRole(@Null(groups = InsertActive.class) @Min(value = 0, groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, @NotBlank String name, @NotBlank String enName, @NotNull Integer status, String description, List<Long> menuIdList, List<Long> resourceIdList) {
        super(id);
        this.name = name;
        this.enName = enName;
        this.status = status;
        this.description = description;
        this.menuIdList = menuIdList;
        this.resourceIdList = resourceIdList;
    }
}
