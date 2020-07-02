import {JSONSchema7} from "json-schema";
import {VNode} from "vue";

export default (context: { data: { schema: JSONSchema7 }; children: VNode; props: Record<string, any> }) => {
    const {data, children, props} = context
    return <el-table data={data.schema} stripe style="width: 100%"> {children} </el-table>
}