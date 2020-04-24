import {RenderContext} from "vue/types/umd";

export default (context: RenderContext) => {
    console.log(context)
    return <el-select></el-select>
}
