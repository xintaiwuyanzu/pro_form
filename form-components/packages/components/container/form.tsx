import {JSONSchema7} from "json-schema";
import {VNode} from "vue";

export default (context: { data: JSONSchema7; children: VNode; props: Record<string, any> }) => {
    console.log(context)
    const {data, children, props} = context
    return <el-form-item label={data.title}>  {children}</el-form-item>
}
