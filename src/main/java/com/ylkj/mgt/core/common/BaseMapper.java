package com.ylkj.mgt.core.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * BaseMapper实现通用的增删改查方法<br>
 * 所有的Mapper都要继承该接口,
 * 并添加@{@link org.apache.ibatis.annotations.Mapper}注解
 *
 * @author youjun
 * @create 2019-03-11 10:01
 */
public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T> {

}
