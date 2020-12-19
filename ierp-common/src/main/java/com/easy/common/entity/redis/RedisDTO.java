package com.easy.common.entity.redis;

import com.easy.common.commons.RedisOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/8 15:22
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisDTO {

    private String key;
    private Object object;
    private long time;

    //什么操作
    private String operation;

    private RedisDTO(String key,Object object,String operation,long time){
        this.key=key;
        this.object=object;
        this.operation=operation;
        if(time<=0L){
            this.time=-1L ;
        }else{
            this.time=time;
        }
    }

    private RedisDTO(String key,Object object,String operation){
        this(key,object,operation,-1L);
    }



    public static RedisDTO  getIntance(String key, Object object, RedisOperationEnum operation){
        return new RedisDTO(key,object,operation.getMethod());
    }

    public static RedisDTO  getIntance(String key,Object object,RedisOperationEnum operation,long time){
        return new RedisDTO(key,object,operation.getMethod(),time);
    }
    public static void main(String[] args) {
       /* AuthUser sysUser=new AuthUser();
        sysUser.setId("123id");
        sysUser.setUsername("testuser");
        sysUser.setClientId("这个是clientId");
        List<SysRole> roleList=new ArrayList<>();
        Class a=roleList.getClass();
        System.out.println(a);
       for (int i = 0; i < 3; i++) {
            SysRole role=new SysRole();
            role.setId(String.valueOf(i+1));
            role.setName("角色:role"+i+1);
            role.setCreateDate(new Date());
            role.setStatus(0);
            roleList.add(role);
        }

        ArrayList arrayList = JSONUtil.toBean(JSONUtil.parseArray(roleList).toString(), ArrayList.class);
        System.out.println(arrayList);*/

        /* sysUser.setRoles(roleList);
        RedisDTO redisDTO=new RedisDTO("authUser",sysUser,100);
        System.out.println(JSONUtil.parseObj(redisDTO).toStringPretty());*/

    }
}
