export default (context) => {
    const {data} = context.data;
    return <el-form  {...{props: {model: data}}}>{context.children}</el-form>
}
