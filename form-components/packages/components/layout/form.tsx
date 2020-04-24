import {RenderContext} from "vue/types/umd";

export default (context: RenderContext) => {
    return <el-form label-width={'100px'}>{context.children}</el-form>
}
