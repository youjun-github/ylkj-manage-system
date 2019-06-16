package com.ylkj.mgt.core.common;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

/**
 * 用于远程接口调用,等同HttpClient
 * @author: youjun
 * @Date: 2019-03-18 17:11:11
 */
public interface RestTemplateService {

    <T> T exchange(String url,
                   HttpMethod reqType,
                   Map<String, String> headerMap,
                   Object obj,
                   Class<T> zClass);

    <T> T getForObject(String url, Class<T> zClass);

    /**
     * @description: 如果需要返回泛型，需要调用此方法
     * @auturn: youjun
     * @date: 2019-04-04 17:37:05 
     * @param: url                  访问地址
     * @param: reqType              请求方式
     * @param: headerMap            header数据
     * @param: obj                  请求数据
     * @param: ptr                  泛型
     * @return : T
     */
    <T> T exchange(String url,
                   HttpMethod reqType,
                   Map<String, String> headerMap,
                   Object obj,
                   ParameterizedTypeReference<T> ptr);

}
