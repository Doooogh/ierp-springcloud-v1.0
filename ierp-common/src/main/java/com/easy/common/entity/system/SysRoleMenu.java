package com.easy.common.entity.system;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/17 11:15
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SysRoleMenu extends BaseEntity {

    private Long roleId;

    private Long MenuId;

    public SysRoleMenu(@Null(groups = InsertActive.class) @Min(value = 0, groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, Long roleId, Long menuId) {
        super(id);
        this.roleId = roleId;
        MenuId = menuId;
    }
}
