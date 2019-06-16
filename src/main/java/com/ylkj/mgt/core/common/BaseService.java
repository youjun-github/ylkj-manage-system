package com.ylkj.mgt.core.common;

import com.github.pagehelper.Page;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Map;

/**
 * @author youjun
 * @create 2019-03-11 10:01
 */
public interface BaseService<T> {

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    T findById(Object id);

    /**
     * 按条件查询
     *
     * @param record
     * @return
     */
    List<T> select(T record);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<T> selectAll();


    /**
     * 查询数量
     *
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 自定义SQL查询数量
     *
     * @param weekend
     * @return
     * @see #selectByExample(Weekend)
     */
    int selectCountByWeekend(Weekend<?> weekend);

    /**
     * 查询单条数据 TODO 可以在entry里面设置默认的orderBy，但以后改造为自定义
     *
     * @param record
     * @return
     */
    T selectOne(T record);

    /**
     * 自定义查询条件查询单挑数据 weekend 可以自定义 orderBy
     *
     * @param weekend
     * @return
     * @see #selectByExample(Weekend)
     */
    T selectOneByWeekend(Weekend<?> weekend);

    /**
     * 自定义SQL语句
     * <p>
     * 例子:
     * </P>
     *
     * <Pre>
     * Weekend<User> weekend = Weekend.of(User.class);
     * weekend.weekendCriteria().andIsNull(User::getId).andBetween(User::getId, 0, 10).andIn(User::getUserName,
     * Arrays.asList("a", "b", "c"));
     * weekend.orderBy("id").desc();
     * </Pre>
     *
     * @param
     * @return
     */
    List<T> selectByExample(Weekend<?> weekend);

    /**
     * 动态插入，如果主键是自增的，则返回主键
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 通过主键动态修改
     *
     * @param record
     * @return
     */
    int updateSelective(T record);

    /**
     * 通过条件修改
     *
     * @param record 要修改的值
     * @param where  过滤条件
     * @return
     */
    int updateByWhere(T record, Weekend<?> where);

    /**
     * 通过条件动态修改
     *
     * @param record
     * @param where
     * @return
     */
    int updateSelectiveByWhere(T record, Weekend<?> where);

    /**
     * 分页查询.
     *
     * @param record   查询条件
     * @param pageNum  页数
     * @param pageSize 每页记录数
     * @return
     */
    Page<T> select(T record, int pageNum, int pageSize);

    Page<T> select(T record, int pageNum, int pageSize, Map<String, String> order);

    Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize);

    Page<T> selectByExampleByIdDesc(Weekend<?> weekend, int pageNum, int pageSize);

    Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize, Map<String, String> order);

}
