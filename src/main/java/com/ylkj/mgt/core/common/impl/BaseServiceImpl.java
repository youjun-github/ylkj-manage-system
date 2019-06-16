package com.ylkj.mgt.core.common.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ylkj.mgt.core.common.BaseMapper;
import com.ylkj.mgt.core.common.BaseService;
import com.ylkj.mgt.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.weekend.Weekend;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youjun
 * @create 2019-03-11 10:02
 */
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseMapper<T> mapper;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T>) types[0];
        }
    }

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    public T findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 按条件查询
     *
     * @param record
     * @return
     */
    public List<T> select(T record) {
        return mapper.select(record);
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 分页查询.
     *
     * @param record   查询条件
     * @param pageNum  页数
     * @param pageSize 每页记录数
     * @return
     */
    public Page<T> select(T record, int pageNum, int pageSize) {
        return select(record, pageNum, pageSize, null);
    }

    public Page<T> select(T record, int pageNum, int pageSize, Map<String, String> order) {
        Page<T> page = PageHelper.startPage(pageNum, pageSize);
        if (order != null) {
            page.setOrderBy(orderSql(order));
        }
        page.doSelectPage(() -> mapper.select(record));
        return page;
    }


    /**
     * 查询数量
     *
     * @param record
     * @return
     */
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    /**
     * 自定义SQL查询数量
     *
     * @param weekend
     * @return
     * @see #selectByExample(Weekend)
     */
    public int selectCountByWeekend(Weekend<?> weekend) {
        return mapper.selectCountByExample(weekend);
    }

    /**
     * 查询单条数据 TODO 可以在entry里面设置默认的orderBy，但以后改造为自定义
     *
     * @param record
     * @return
     */
    public T selectOne(T record) {
        List<T> list = mapper.select(record);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 自定义查询条件查询单挑数据 weekend 可以自定义 orderBy
     *
     * @param weekend
     * @return
     * @see #selectByExample(Weekend)
     */
    public T selectOneByWeekend(Weekend<?> weekend) {
        List<T> list = mapper.selectByExample(weekend);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

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
    public List<T> selectByExample(Weekend<?> weekend) {
        return mapper.selectByExample(weekend);
    }

    public Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize) {
        return selectByExample(weekend, pageNum, pageSize, null);
    }

    public Page<T> selectByExampleByIdDesc(Weekend<?> weekend, int pageNum, int pageSize) {
        Map<String, String> orderBy = new HashMap<>();
        orderBy.put("id", "desc");
        return selectByExample(weekend, pageNum, pageSize, orderBy);
    }

    public Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize, Map<String, String> order) {
        Page<T> page = PageHelper.startPage(pageNum, pageSize);
        if (CommonUtils.isNotEmpty(order)) {
            page.setOrderBy(orderSql(order));
        }
        page.doSelectPage(() -> mapper.selectByExample(weekend));
        return page;
    }


    private String orderSql(Map<String, String> order) {
        EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
        Map<String, EntityColumn> propertyMap = entityTable.getPropertyMap();
        StringBuilder orderBy = new StringBuilder();
        order.forEach((k, v) -> {
            EntityColumn column = propertyMap.get(k);
            orderBy.append(column.getColumn()).append(" ").append(v).append(",");
        });
        orderBy.replace(orderBy.length() - 1, orderBy.length(), "");
        return orderBy.toString();
    }

    /**
     * 动态插入，如果主键是自增的，则返回主键
     *
     * @param record
     * @return
     */
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 通过主键动态修改
     *
     * @param record
     * @return
     */
    public int updateSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 通过条件修改
     *
     * @param record 要修改的值
     * @param where  过滤条件
     * @return
     */
    public int updateByWhere(T record, Weekend<?> where) {
        return mapper.updateByExample(record, where);
    }

    /**
     * 通过条件动态修改
     *
     * @param record
     * @param where
     * @return
     */
    public int updateSelectiveByWhere(T record, Weekend<?> where) {
        return mapper.updateByExampleSelective(record, where);
    }
}
