package com.easy.common.base.dao;

import com.easy.common.base.entity.BaseEntity;
import com.easy.common.commons.PageBean;

import java.util.List;

public interface BaseDao <T extends BaseEntity>{

    List<T> list(PageBean pageBean);

    List<T> listAll(T t);

    T get(Long id);

    int save(T t);

    int delete(Long id);

    int update(T t);


}
