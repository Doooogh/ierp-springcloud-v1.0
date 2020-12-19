package com.easy.common.entity.system;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/11 16:07
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SysRoleResource extends BaseEntity {

    private Long roleId;
    private Long resourceId;


    public SysRoleResource(@Null(groups = InsertActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id, Long roleId, Long resourceId) {
        super(id);
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
