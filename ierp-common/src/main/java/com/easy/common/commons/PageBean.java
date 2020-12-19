package com.easy.common.commons;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.easy.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description  自定义分页实体
 * @Author li long
 * @Date 2020/9/14 10:52
 * @Version 1.0
 **/

@NoArgsConstructor
@Data
public class PageBean<T extends BaseEntity> {

    private int pageSize;

    private int pageNum;

    private long total;

    private List<T> list;

    private T t;

    //除了实体外 其他的参数
    private QueryParam queryParam;

    private static final String DESC="DESC";

    private static final String ASC="ASC";

    private static final Map<String,Object> EMPTY_MAP=new HashMap<>();

    public PageBean(HttpServletRequest request, T t, Map<String,Object> map){
        if(StrUtil.isNotEmpty(request.getParameter("pageNum"))){
            this.pageNum=Integer.parseInt(request.getParameter("pageNum"));
        }else{
            this.pageNum=1;
        }

        if(StrUtil.isNotEmpty(request.getParameter("pageSize"))){
            this.pageSize=Integer.parseInt(request.getParameter("pageSize"));
        }else{
            this.pageSize=10;
        }

        this.t=t;
        if(BeanUtil.isEmpty(t.getDeleted())){
            this.t.setDeleted(0);
        }
        this.queryParam=new QueryParam();
        this.queryParam.putAll(map);
    }

    public PageBean(HttpServletRequest request, T t){
        this(request,t,EMPTY_MAP);
    }

    public PageBean(T t, Integer pageNum,Integer pageSize,Map<String,Object> map){
        this.t=t;
        if(BeanUtil.isEmpty(t.getDeleted())){
            this.t.setDeleted(0);
        }
        if(BeanUtil.isEmpty(pageNum)){
            pageNum=1;
        }
        if(BeanUtil.isEmpty(pageSize)){
            pageSize=10;
        }
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.queryParam=new QueryParam();
        this.queryParam.putAll(map);
    }

    public PageBean(T t,Integer pageNum,Integer pageSize){
        this(t,pageNum,pageSize,EMPTY_MAP);
    }
}
