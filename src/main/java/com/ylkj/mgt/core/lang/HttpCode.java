package com.ylkj.mgt.core.lang;

/**
 * 返回的状态
 *
 * @author youjun
 */
public interface HttpCode {

    public final String SUCCESS_CODE = "1000";

    public final String ERROR_SYSTEM_EXCEPTION = "9999";

    /**
     * 请求参数无效
     */
    public final String PARAM_INVALID = "1001";

    /**
     * 请求参数为空
     */
    public final String DATA_NULL = "1004";
    /**
     * 数据异常
     */
    public final String DATA_EXCEPTION = "1005";

    /**
     * 超出数据权限
     */
    public final String NO_AUTH = "1006";
    /**
     * 默认错误编码
     */
    public final String DEFAULT_ERROR_CODE = "1003";

    /**
     * 数据不存在
     */
    public final String NO_DATA = "1002";

    public static enum Message {

        SUCCESS(SUCCESS_CODE, "成功"),

        INVALID_PARAM(PARAM_INVALID, "请求参数无效"),

        DATA_NULL_PARAM(DATA_NULL, "请求参数为空"),

        ERROR_SYSTEM(ERROR_SYSTEM_EXCEPTION, "系统错误");

        public String code;

        public String msg;

        private Message(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

}
