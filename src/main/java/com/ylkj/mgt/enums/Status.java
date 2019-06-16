package com.ylkj.mgt.enums;

/**
 * 公用状态，状态枚举可以实现该接口
 * <ul>
 * <li>-1 	删除</li>
 * <li>0	禁用</li>
 * <li>1	启用</li>
 * </ul>
 */
public interface Status {

    /**
     * 正常状态		1
     */
    public Status NORMAL = new Status() {
        private Integer code = 1;
        private String name = "启用";

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };

    /**
     * 禁用状态		0
     */
    public Status DISABLE = new Status() {
        private Integer code = 0;
        private String name = "禁用";

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };

    /**
     * 删除状态		-1
     */
    public Status DELETE = new Status() {
        private Integer code = -1;
        private String name = "删除";

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getName() {
            return name;
        }

    };

    public Integer getCode();

    public String getName();
}
