package com.easy.common.base.entity;

import com.easy.common.validation.CusValidator;
import com.easy.common.validation.InsertActive;
import com.easy.common.validation.UpdateActive;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/14 11:20
 * @Version 1.0
 **/
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {


    @Null(groups = InsertActive.class,message = "不需要id")
    @NotNull(groups = UpdateActive.class,message = "id为必填项")
    public Long id;

    @CusValidator( value = {"0","1"},message = "deleted只有0,1两种状态")
    @ApiModelProperty(value = "是否删除->1 是, 0 否")
    public Integer deleted;

    public Date createDate;

    public Date updateDate;




    public BaseEntity(@Null(groups = InsertActive.class) @Min(value = 0,groups = UpdateActive.class) @NotNull(groups = UpdateActive.class, message = "id为必填项") Long id) {
        this.id = id;
    }




}
