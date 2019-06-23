package com.ylkj.mgt.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource {
    /**
     * 主键,自增
     */
    @Id
    private Integer id;

    /**
     * 资源身份标识
     */
    private String identity;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源请求地址
     */
    private String url;

    /**
     * 父资源主键ID,若顶级则为0
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 资源树,形如:0/7,若顶级为0
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 资源类型(暂无使用)
     */
    @Column(name = "resource_type")
    private Integer resourceType;

    /**
     * 是否是资源(是否可点击触发请求)：0否；1是
     */
    @Column(name = "is_resource")
    private Boolean isResource;

    /**
     * 是否显示：0不显示；1显示
     */
    @Column(name = "is_show")
    private Boolean isShow;

    /**
     * 状态,-1-删除、 0-禁用， 1-正常
     */
    private Integer status;

    /**
     * 创建人，如果是同步的数据，则默认为-1L
     */
    @Column(name = "creator_id")
    private Integer creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_id")
    private Integer updateId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 资源描述
     */
    private String remarks;

    /**
     * 获取主键,自增
     *
     * @return id - 主键,自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键,自增
     *
     * @param id 主键,自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源身份标识
     *
     * @return identity - 资源身份标识
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置资源身份标识
     *
     * @param identity 资源身份标识
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取资源请求地址
     *
     * @return url - 资源请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源请求地址
     *
     * @param url 资源请求地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取父资源主键ID,若顶级则为0
     *
     * @return parent_id - 父资源主键ID,若顶级则为0
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父资源主键ID,若顶级则为0
     *
     * @param parentId 父资源主键ID,若顶级则为0
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取资源树,形如:0/7,若顶级为0
     *
     * @return parent_ids - 资源树,形如:0/7,若顶级为0
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置资源树,形如:0/7,若顶级为0
     *
     * @param parentIds 资源树,形如:0/7,若顶级为0
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * 获取资源图标
     *
     * @return icon - 资源图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置资源图标
     *
     * @param icon 资源图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取资源类型(暂无使用)
     *
     * @return resource_type - 资源类型(暂无使用)
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型(暂无使用)
     *
     * @param resourceType 资源类型(暂无使用)
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取是否是资源(是否可点击触发请求)：0否；1是
     *
     * @return is_resource - 是否是资源(是否可点击触发请求)：0否；1是
     */
    public Boolean getIsResource() {
        return isResource;
    }

    /**
     * 设置是否是资源(是否可点击触发请求)：0否；1是
     *
     * @param isResource 是否是资源(是否可点击触发请求)：0否；1是
     */
    public void setIsResource(Boolean isResource) {
        this.isResource = isResource;
    }

    /**
     * 获取是否显示：0不显示；1显示
     *
     * @return is_show - 是否显示：0不显示；1显示
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示：0不显示；1显示
     *
     * @param isShow 是否显示：0不显示；1显示
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取状态,-1-删除、 0-禁用， 1-正常
     *
     * @return status - 状态,-1-删除、 0-禁用， 1-正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态,-1-删除、 0-禁用， 1-正常
     *
     * @param status 状态,-1-删除、 0-禁用， 1-正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建人，如果是同步的数据，则默认为-1L
     *
     * @return creator_id - 创建人，如果是同步的数据，则默认为-1L
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人，如果是同步的数据，则默认为-1L
     *
     * @param creatorId 创建人，如果是同步的数据，则默认为-1L
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_id - 更新人
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * 设置更新人
     *
     * @param updateId 更新人
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取资源描述
     *
     * @return remarks - 资源描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置资源描述
     *
     * @param remarks 资源描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}