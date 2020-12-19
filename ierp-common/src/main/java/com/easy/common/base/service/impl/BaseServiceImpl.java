package com.easy.common.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.easy.common.base.dao.BaseDao;
import com.easy.common.base.entity.BaseEntity;
import com.easy.common.base.service.BaseService;
import com.easy.common.commons.PageBean;
import com.easy.common.exception.ApiException;
import com.easy.common.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/14 13:27
 * @Version 1.0
 **/

@Transactional(rollbackFor = {Exception.class})
@Service
public abstract class BaseServiceImpl<D extends BaseDao<T>,T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PageInfo list(PageBean pageBean) {
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        PageInfo<T> pageInfo = new PageInfo<>( dao.list(pageBean));
        return pageInfo;
    }

    @Override
    public List listAll(T baseEntity) {
        return dao.listAll(baseEntity);
    }

    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    @Override
    public void save(T baseEntity) {
        baseEntity.setId(snowflakeIdWorker.nextId());
        baseEntity.setCreateDate(new Date());
        dao.save(baseEntity);
    }

    @Override
    public int delete(Long id) {
        if(BeanUtil.isEmpty(id)){
            throw new ApiException("id不能为空");
        }
        return dao.delete(id);
    }

    @Override
    public int update(T baseEntity) {
        return dao.update(baseEntity);
    }
}
