package com.dr.framework.common.form.engine.model;

/**
 * 添加一层抽象定义，用来处理共性的地方
 *
 * @author dr
 */
public interface BaseModel {
    /**
     * 表单定义主键
     *
     * @return
     */
    String getId();

    /**
     * 获取描述
     *
     * @return
     */
    String getDescription();

    /**
     * 获取备注
     *
     * @return
     */
    String getRemarks();

    /**
     * <p>
     * 表单的版本号主要意义是用来防止数据混乱
     * <p>
     * 比如先打开一个表单填写页面
     * 然后修改表单定义
     * 这个时候表单填写一面保存的时候就会报错
     * <p>
     * 使用版本的概念能有效避免此类情况，原来的表单数据保存到了原来的表结构中，
     * 新数据保存到新的数据库中
     * <p>
     * 但是要处理老数据和新数据的迁移问题
     *
     * @return 获取版本号
     */
    Integer getVersion();

}
