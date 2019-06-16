package com.ylkj.mgt.core.common.impl;

import com.ylkj.mgt.core.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * @Description: 调用外部接口,同HttpClient
 * @Author: youjun
 * @Date: 2019-03-18 16:45:45
 */
@Service("restTemplateService")
public class RestTemplateServiceImpl implements RestTemplateService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T exchange(
            String url,
            HttpMethod reqType,
            Map<String, String> headerMap,
            Object obj,
            Class<T> zClass) {
        HttpHeaders headers = getHeader(headerMap);
        HttpEntity<Object> entity = new HttpEntity<>(obj, headers);

        return restTemplate.exchange(url, reqType, entity, zClass).getBody();
    }

    @Override
    public <T> T exchange(String url, HttpMethod reqType, Map<String, String> headerMap, Object obj, ParameterizedTypeReference<T> ptr) {
        HttpHeaders headers = getHeader(headerMap);
        HttpEntity<Object> entity = new HttpEntity<>(obj, headers);
        return restTemplate.exchange(url, reqType, entity, ptr).getBody();
    }

    @Override
    public <T> T getForObject(String url, Class<T> zClass) {
        return restTemplate.getForObject(url, zClass);
    }

    /**
     * @description: 把参数放进header中
     * @auturn: youjun
     * @date: 2019-03-18 17:46:51
     * @param: headerMap
     * @return : org.springframework.http.HttpHeaders
     */
    private HttpHeaders getHeader(Map<String, String> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(null != headerMap) {
            headerMap.forEach((k,v) -> {
                headers.add(k, v);
            });
        }
        return headers;
    }

}
