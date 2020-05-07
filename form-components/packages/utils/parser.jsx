import vue from 'vue'

/**
 *根据配置获取layout的控件
 * @param layouts
 * @param displayType
 */
const getLayout = (layouts, displayType) => {
    return layouts[displayType]
}
const getContainer = ({'ui:container': container}, {containers}, {displayType}) => {
    const containerName = container || displayType
    return containers[containerName] || containers.default
}
const getWidget = ({'ui:widget': widget, type, enum: enums, format}, {widgets}, {displayType, widgetMapping}) => {
    let result = typeof widget === 'string' ? widgets[widget] : widget
    if (!result) {
        const mappingKeys = []
        if (enums) {
            mappingKeys.push(`${type}?enum`)
            mappingKeys.push(`*?enum`)
        }
        if (format) {
            mappingKeys.push(`${type}:${format}`)
        }
        mappingKeys.push(type)
        const key = mappingKeys.find(k => widgetMapping[k])
        result = widgets[widgetMapping[key]]
    }
    return result
}

const getChildren = (
    schema,
    controls,
    props,
    data,
    isRoot
) => {
    if (isRoot) {
        //跟上的属性绑定上
        props.rootSchema = schema
        props.rootData = data
    }
    switch (schema.type) {
        case "object": {
            const {properties} = schema;
            if (properties) {
                const ObjectMap = () => (
                    <div class={'form_object'}>
                        {
                            Object.keys(properties)
                                .map(name => {
                                    const _schema = properties[name]
                                    let _data = data[name]
                                    //动态添加属性，实现数据双向绑定
                                    if (!_data) {
                                        if (_schema.type == 'object') {
                                            _data = {}
                                        } else {
                                            _data = ''
                                            vue.set(data, name, _data)
                                        }
                                    }
                                    data[name] = _data
                                    const children = getChildren(_schema, controls, props, _data)
                                    //准备相关的参数
                                    const _prop = {
                                        parent: data,
                                        data: _data,
                                        name,
                                        ...props
                                    }
                                    return <children {...{props: _prop}}/>
                                })
                        }
                    </div>
                )
                return ObjectMap
            }
        }
            break;
        case "array": {
            //TODO 列表的情况
        }
            break;
        default: {
            //获取container
            const container = getContainer(schema, controls, props)
            //获取widget
            const widget = getWidget(schema, controls, props)
            const FormComponent = ({data, props}) => {
                //准备控件参数
                const _props = {
                    isRoot,
                    ...data,
                    ...props,
                    schema
                }
                return (
                    <container {...{props: _props}}>
                        <widget {...{props: _props}} />
                    </container>
                )
            }
            return FormComponent
        }
    }
}


export function parser(
    //表结构描述
    schema,
    //表单对象
    data,
    //表单具体的字段控件
    widgets,
    //最外层控制布局的控件
    layouts,
    //字段控件外层用来控制样式的控件
    containers,
    //其他的属性
    props
) {
    //解析schema
    const layout = getLayout(layouts, props.displayType)
    const children = getChildren(
        schema,
        {
            widgets,
            layouts,
            containers
        },
        props,
        data,
        true
    )
    return {
        layout,
        children
    }
}
