package com.easy.common.base.service;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.commons.PageBean;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = {Exception.class})
public interface BaseService <T extends BaseEntity> {

    PageInfo<T> list(PageBean pageBean);

    List<T> listAll(T t);

    T get(Long id);

    void save(T t);

    int delete(Long id);

    int update(T t);


}
