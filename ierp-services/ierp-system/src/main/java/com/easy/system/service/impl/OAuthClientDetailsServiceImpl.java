package com.easy.system.service.impl;

import com.easy.system.dao.OAuthClientDetailsDao;
import com.easy.system.service.OAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author li long
 * @Date 2020/9/18 0:18
 * @Version 1.0
 **/
@Service
public class OAuthClientDetailsServiceImpl implements OAuthClientDetailsService {

    @Autowired
    private OAuthClientDetailsDao oAuthClientDetailsDao;


    @Override
    public List<Map<String, Object>> findByClientId(String clientId) {
        return oAuthClientDetailsDao.findByClientId(clientId);
    }
}
