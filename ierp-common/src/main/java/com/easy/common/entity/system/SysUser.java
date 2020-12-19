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
import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/5 0:48
 * @Version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {


    @ApiModelProperty(value = "用户名")
    @NotBlank(groups = InsertActive.class)
    private String username;

    @NotBlank(groups = InsertActive.class)
    private String password;

    @ApiModelProperty(value = "姓名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "身份")
    @NotBlank(groups = InsertActive.class)
    private String identity;




    @ApiModelProperty(value = "性别-> 1 男 0女")
    @Pattern(regexp = "^|1|0|$",message = "性别需要指定 性别-> 1 男 0女")
    @NotBlank
    private String sex;

    @Email
    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    private String idCard;



    @ApiModelProperty(value = "用户状态->1 正常 0禁用",allowableValues ="0,1")
    @NotNull(groups = UpdateActive.class)
    @CusValidator(groups = UpdateActive.class,value = {"0","1"},message = "status 只能为0或者1")
    private Integer status;

    @ApiModelProperty(value = "服务端id")
    @NotBlank(groups = InsertActive.class)
    private String clientId;


    private List<String> roles;


    public SysUser(@Null(groups = InsertActive.class) @Min(value = 0, groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, @NotBlank(groups = InsertActive.class) String username, @NotBlank(groups = InsertActive.class) String password, @NotBlank String name, @NotBlank(groups = InsertActive.class) String identity, @Pattern(regexp = "^|1|0|$", message = "性别需要指定 性别-> 1 男 0女") @NotBlank String sex, @Email String email, String phone, String idCard, @NotNull(groups = UpdateActive.class) Integer status, @NotBlank(groups = InsertActive.class) String clientId, List<String> roles) {
        super(id);
        this.username = username;
        this.password = password;
        this.name = name;
        this.identity = identity;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.idCard = idCard;
        this.status = status;
        this.clientId = clientId;
        this.roles = roles;
    }
}
