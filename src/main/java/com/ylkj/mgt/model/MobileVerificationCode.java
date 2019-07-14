package com.ylkj.mgt.model;

import java.io.Serializable;

/**
 * 手机验证码信息
 * @author youq
 */
public class MobileVerificationCode implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String verCode;

    /**
     * 时间戳,10位
     */
    private Long timestamp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
