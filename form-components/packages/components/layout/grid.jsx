export default (context) => {
    const {data} = context.data;
    return <el-row  {...{props: {model: data}}}>{context.children}</el-row>
}