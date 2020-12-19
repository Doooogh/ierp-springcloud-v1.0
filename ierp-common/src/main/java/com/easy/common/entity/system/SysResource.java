package com.easy.common.entity.system;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.validation.CusValidator;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/7 14:12
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysResource extends BaseEntity {


    @ApiModelProperty(value ="所属菜单")
    @NotNull
    private Long menuId;

    @ApiModelProperty(value ="资源名称")
    @NotBlank
    private String title;

    //资源编码
    @NotBlank
    @ApiModelProperty(value ="资源编码")
    private String permissionCode;
    //资源路径
    @NotBlank
    @ApiModelProperty(value ="资源url路径")
    private  String url;

    //状态  1 正常 0停用
    @ApiModelProperty(value ="资源状态->1正常,0禁用",allowableValues ="0,1")
    @NotNull
    @CusValidator(value = {"0","1"},message = "状态值只能为1或者0")
    private Integer status;

    //类型 1 接口  2 按钮资源
    @ApiModelProperty(value ="资源类别->button,interface")
    @NotBlank
    @Pattern(regexp="^|button|interface|$",message = "类型只能是button 或者interface")
    private String type;


    public SysResource(@Null(groups = InsertActive.class) @Min(value = 0, groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, @NotNull Long menuId, @NotBlank String title, @NotBlank String permissionCode, @NotBlank String url, @NotNull Integer status, @NotBlank @Pattern(regexp = "^|button|interface|$", message = "类型只能是button 或者interface") String type) {
        super(id);
        this.menuId = menuId;
        this.title = title;
        this.permissionCode = permissionCode;
        this.url = url;
        this.status = status;
        this.type = type;
    }
}
