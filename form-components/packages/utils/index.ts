/**
 * 字段类型枚举
 */
const enum FieldType {
    /**
     * 字符串，长度超过4000自动变成clob
     */
    STRING,
    /**
     * 无精度，没有小数点数字
     */
    LONG,
    /**
     * 带有小数点数字
     */
    NUMBER,
    /**
     * 日期类型，所有日期类型数据库实际类型是long，数据库中存储毫秒
     */
    DATE,
    /**
     * 二进制，对应blob
     */
    BYTES
}

/**
 * 工具方法，生成uuid
 */
export function uuID() {
    let d = new Date().getTime();
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
        const r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}

/**
 * 根据组件默认信息创建一个默认字段信息
 * @param config
 */
export function createField({name}: ComponentConfig): Field {
    const type = FieldType.STRING;
    return {id: uuID(), label: name, length: 200, name: type.toString() + new Date().getTime(), type: type}
}
