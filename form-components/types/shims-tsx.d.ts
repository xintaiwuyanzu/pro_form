import Vue, {VNode} from 'vue';

declare global {
    /**
     * vuex state对象
     */
    interface StoreState {
        /**
         * 当前编辑字段
         */
        current: Field | null
    }


    /**
     * 组件全局配置定义
     */
    interface InstallOption {
        /**
         *组件前缀
         */
        pre: string
    }

    interface ComponentConfig {
        /**
         * 组件名称
         */
        readonly name: string;
        /**
         * 显示名称
         */
        readonly  label: string;
        /**
         * 控件类型
         */
        readonly   type: string;
        /**
         * 控件图标
         */
        readonly  icon?: string;
    }

    /**
     * 表示字段信息
     */
    interface Field {
        /**
         *字段类型
         * 0 String
         * 1 LONG
         * 2 NUMBER
         * 3 DATE
         * 4 BYTES
         */
        type: 0 | 1 | 2 | 3 | 4;
        /**
         * 字段名称
         */
        name: string;
        /**
         * 主键
         */
        id: string;
        /**
         * 字段长度
         */
        length: number;
        /**
         * 小数点后有几位
         */
        scale?: number;
        /**
         * 显示名称
         */
        label: string;
        /**
         *字段描述
         */
        description?: string;
    }

    interface UISchema {
        /**
         * 控件宽度
         */
        'ui:width': string | number
        /**
         * 控件标题宽度
         */
        'ui:labelWidth': string | number
        /**
         * 控件自定义样式名称
         */
        'ui:className': string
        /**
         * 控件显示方案
         */
        'ui:show': 'show' | 'hidden' | 'readonly' | 'disable'
        /**
         * 控件其他控制属性
         */
        'ui:options': Object
        /**
         * ui显示控件
         */
        'ui:widget': string
    }

    namespace JSX {
        // tslint:disable no-empty-interface
        interface Element extends VNode {
        }

        // tslint:disable no-empty-interface
        interface ElementClass extends Vue {
        }

        interface IntrinsicElements {
            [elem: string]: any;
        }
    }
}
declare module 'vue/types/vue' {
    interface Vue {
        $form: InstallOption
    }
}
