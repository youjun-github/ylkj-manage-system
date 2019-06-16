package com.ylkj.mgt.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 登录名,唯一
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 是否超管,1是,0否
     */
    private Boolean admin;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

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
     * 描述
     */
    private String remarks;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录名,唯一
     *
     * @return login_name - 登录名,唯一
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名,唯一
     *
     * @param loginName 登录名,唯一
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取是否超管,1是,0否
     *
     * @return admin - 是否超管,1是,0否
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * 设置是否超管,1是,0否
     *
     * @param admin 是否超管,1是,0否
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile_phone - 手机号
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号
     *
     * @param mobilePhone 手机号
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
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
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}