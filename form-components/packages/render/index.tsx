import {Component, Prop, Vue} from "vue-property-decorator";
import {JSONSchema7} from "json-schema";
// @ts-ignore
import {containers, layouts, widgetMapping, widgets} from '../components'
// @ts-ignore
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
    /**
     * 控件的映射
     */
    @Prop({default: () => widgetMapping})
    widgetMapping!: Record<string, string>

    @Prop({default: () => layouts})
    layouts!: Record<string, VNode>
    @Prop({default: () => containers})
    containers!: Record<string, VNode>

    render() {
        const prop = {
            displayType: this.displayType,
            widgetMapping: this.widgetMapping
        }

        const {layout, children} = parser(
            this.schema,
            this.data,
            this.widgets,
            this.layouts,
            this.containers,
            prop
        )
        return (
            <layout {...{data: this.data, schema: this.schema}} >
                <children/>
            </layout>
        )
    }
}
