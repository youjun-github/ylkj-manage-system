package com.ylkj.mgt.web.mode;

import com.ylkj.mgt.entity.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @author youjun
 * @create 2019-06-22 15:30
 */
@ApiModel(value = "资源返回对象")
public class SysResourceMode {

    @ApiModelProperty(value = "菜单ID")
    private Integer id;

    @ApiModelProperty(value = "资源身份标识")
    private String identity;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源请求地址")
    private String url;

    @ApiModelProperty(value = "父资源主键ID,若顶级则为0")
    private Integer parentId;

    @ApiModelProperty(value = "资源树,形如:0/7,若顶级为0")
    private String parentIds;

    @ApiModelProperty(value = "资源图标")
    private String icon;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "资源类型(暂无使用)")
    private Integer resourceType;

    @ApiModelProperty(value = "是否是资源(是否可点击触发请求)：0否；1是")
    private Boolean isResource;

    @ApiModelProperty(value = "是否显示：0不显示；1显示")
    private Boolean isShow;

    public static SysResourceMode entity(SysResource sysResource) {
        SysResourceMode resourceMode = new SysResourceMode();
        BeanUtils.copyProperties(sysResource, resourceMode);
        return resourceMode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Boolean getResource() {
        return isResource;
    }

    public void setResource(Boolean resource) {
        isResource = resource;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

}
