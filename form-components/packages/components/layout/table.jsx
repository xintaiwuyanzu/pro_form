const defaultAttrs = {
    //剧中对齐
    align: 'center',
    //高亮显示当前选中
    'highlight-current-row': true,
    //高度100%
    height: '100%',
    //边框
    border: true,
    //斑马线
    stripe: true
}


export default (context) => {
    //列表数据，如果是对象则转换成数组
    const {data} = context.data;
    let d = data
    if (!Array.isArray(d)) {
        d = [data]
    }
    const props = Object.assign(
        {},
        {data: d},
        defaultAttrs
    )
    return <el-table  {...{props}}>{context.children}</el-table>
}