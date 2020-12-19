package com.easy.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OAuthClientDetailsDao {
    List<Map<String,Object>> findByClientId(@Param("clientId")String clientId);
}
