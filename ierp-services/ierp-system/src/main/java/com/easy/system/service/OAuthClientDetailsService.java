package com.easy.system.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OAuthClientDetailsService {
    List<Map<String,Object>> findByClientId(@Param("clientId")String clientId);
}
