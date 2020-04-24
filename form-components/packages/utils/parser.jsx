/**
 *根据配置获取layout的控件
 * @param layouts
 * @param displayType
 */
const getLayout = (layouts, displayType) => {
    return layouts[displayType]
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
                                    const _data = data[name] || {}
                                    data[name] = _data
                                    const c = getChildren(properties[name], controls, props, _data)
                                    //准备相关的参数
                                    const _prop = {
                                        data,
                                        name
                                    }
                                    return <c {..._prop}/>
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
            const FormComponent = ({props}) => {
                //准备控件参数
                const _props = {
                    isRoot,
                    ...props,
                    schema
                }
                return (
                    <container {..._props}>
                        <widget {..._props}/>
                    </container>
                )
            }
            return FormComponent
        }
    }
}
const getContainer = ({'ui:container': container}, {containers}, {displayType}) => {
    const containerName = container || displayType
    return containers[containerName] || containers.default
}
const getWidget = ({'ui:widget': widget, type, enum: enums, format}, {widgets}, {displayType}) => {
    return widgets[widget] || widgets[type]
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
    {
        //控件显示类型
        displayType
    },
) {
    //解析schema
    const layout = getLayout(layouts, displayType)
    const children = getChildren(
        schema,
        {
            widgets,
            layouts,
            containers
        },
        {
            displayType
        },
        data,
        true
    )
    return {
        layout,
        children
    }
}
