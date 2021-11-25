package com.dr.framework.common.form.engine;

/**
 * 一个抽象service接口，用来统一type和描述，用来给前端展示使用
 *
 * @author dr
 */
public interface TypeComponent {
    /**
     * 类型
     *
     * @return
     */
    String type();

    /**
     * 描述
     *
     * @return
     */
    default String description() {
        return "";
    }

    /**
     * 执行排序
     *
     * @return
     */
    default int order() {
        return 0;
    }

    /**
     * 返回前端的工具类
     */
    class TypeComponentVo implements TypeComponent {
        private transient TypeComponent delegate;
        private String type;
        private String description;
        private int order;


        public TypeComponentVo(TypeComponent delegate) {
            this.delegate = delegate;
            type = delegate.type();
            description = delegate.description();
            order = delegate.order();
        }

        @Override
        public String type() {
            return type;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public int order() {
            return order;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public int getOrder() {
            return order;
        }
    }

}
