import base from '../base'

export default {
    extends: base,
    computed: {
        /**
         * 占位提示信息，从title和description提取
         * @returns {*}
         */
        placeholder() {
            const {title, description} = this.schema
            if (description) {
                return description
            }
            return title.startsWith('请选择') ? title : `请选择${title}`
        }
    }
}