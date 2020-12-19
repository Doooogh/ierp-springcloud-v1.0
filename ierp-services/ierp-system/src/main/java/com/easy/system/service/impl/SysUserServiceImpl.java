package com.easy.system.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.easy.common.base.service.impl.BaseServiceImpl;
import com.easy.common.commons.ResultStatusEnum;
import com.easy.common.entity.auth.AuthUser;
import com.easy.common.entity.system.SysRole;
import com.easy.common.entity.system.SysUser;
import com.easy.common.exception.ApiException;
import com.easy.common.utils.SnowflakeIdWorker;
import com.easy.system.dao.OAuthClientDetailsDao;
import com.easy.system.dao.SysResourceDao;
import com.easy.system.dao.SysRoleDao;
import com.easy.system.dao.SysUserDao;
import com.easy.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/3 16:20
 * @Version 1.0
 **/
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao,SysUser> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysResourceDao sysResourceDao;

    @Autowired
    private OAuthClientDetailsDao oAuthClientDetailsDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;



    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public AuthUser findAuthByUsername(String username) {
        SysUser sysUser = sysUserDao.findByUsername(username);
        if(null==sysUser){
            return null;
        }

        Long id=sysUser.getId();
        //通过id 获取角色 集合 和权限集合
        List<SysRole> sysRoleList = sysRoleDao.listByUserId(id);
        return new AuthUser(sysUser,sysRoleList);
    }


    /**
     * @Author li long
     * @Description --用户注册
     * @Date 2020/9/18 11:23
     * @Param [sysUser]
     * @return void
     **/
    @Override
    public int register(SysUser sysUser) {
         String clientId = sysUser.getClientId();
        List<Map<String, Object>> res = oAuthClientDetailsDao.findByClientId(clientId);
        if(res.size()==0){
            throw  new ApiException(ResultStatusEnum.UNKNOW_CLIENT);
        }
        sysUser.setStatus(1);
        sysUser.setId(snowflakeIdWorker.nextId());
        sysUser.setCreateDate(new Date());
        //TODO sysUser 的密码需要 加密
        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        return sysUserDao.save(sysUser);
    }
}
