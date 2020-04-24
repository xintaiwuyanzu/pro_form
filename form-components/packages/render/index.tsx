import {Component, Prop, Vue} from "vue-property-decorator";
import {JSONSchema7} from "json-schema";
import {containers, layouts, widgets} from '../components'
import {parser} from '../utils/parser'
import {VNode} from "vue";

@Component
export default class FormRender extends Vue {
    /**
     * 必填属性  schema
     */
    @Prop() schema!: JSONSchema7
    /**
     * 选填属性，用来控制ui
     */
    @Prop() uiSchema!: UISchema
    /**
     *表单数据
     */
    @Prop() data!: Record<string, any>
    /**
     * 显示类型
     */
    @Prop({default: 'form'})
    displayType!: 'form' | 'table'
    /**
     * 控件实现
     */
    @Prop({default: () => widgets})
    widgets!: Record<string, VNode>
    @Prop({default: () => layouts})
    layouts!: Record<string, VNode>
    @Prop({default: () => containers})
    containers!: Record<string, VNode>

    render() {
        const {layout, children} = parser(
            this.schema,
            this.data,
            this.widgets,
            this.layouts,
            this.containers,
            {
                displayType: this.displayType
            }
        )
        return (
            <layout>
                <children/>
            </layout>
        )
    }
}
