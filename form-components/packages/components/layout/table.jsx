export default (context) => {
    const {data} = context.data;
    return <el-table  {...{props: {model: data}}}>{context.children}</el-table>
}