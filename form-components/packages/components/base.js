/**
 *基础组件属性，用来统一声明所有组件的属性清单
 */
export default {
    props: [
        //是否跟schema
        'isRoot',
        //控件本身schema
        'schema',
        //控件本身表单数据
        'data',
        //控件父表单对象
        'parent',
        //字段的key
        'name',
        //根schema
        'rootSchema',
        //根表单数据
        'rootData'
    ],
    /**
     * 计算相关参数和属性
     */
    computed: {
        /**
         * 显示名称
         * @returns {*}
         */
        label() {
            return this.schema.title
        },
        /**
         * 提示信息宽度
         * 从schema的 ui:width提取
         * @returns {*}
         */
        labelWidth() {
            return this.schema['ui:labelWidth'] || this.rootSchema['ui:labelWidth'] || '100px'
        },
        /**
         * 占位提示信息，从title和description提取
         * @returns {*}
         */
        placeholder() {
            const {title, description} = this.schema
            if (description) {
                return description
            }
            return title.startsWith('请输入') ? title : `请输入${title}`
        },
        /**
         * 是否必填，从rootschema 中提取
         *
         * TODO 正则表达式校验规则
         * @returns {boolean}
         */
        rules() {
            return [
                {required: true, message: `${this.label}不能为空`}
            ]
        }
    }
}
