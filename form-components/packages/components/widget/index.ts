import input from './input/index.vue'
import InputConfig from './input/config.vue'
import select from './select/index.vue'
import SelectConfig from './select/config.vue'

export const widgets = {
    input,
    select
}
/**
 * key可以是任何值，value是widgets的key
 */
export const widgetMapping = {
    default: 'input',
    string: 'input',
    array: 'list',
    boolean: 'checkbox',
    integer: 'number',
    number: 'number',
    object: 'map',
    'string:upload': 'upload',
    'string:date': 'date',
    'string:dateTime': 'date',
    'string:time': 'date',
    'string:textarea': 'input',
    'string:color': 'color',
    'string:image': 'input',
    'range:date': 'dateRange',
    'range:dateTime': 'dateRange',
    '*?enum': 'select',
    'array?enum': 'checkboxes'
}

export const configs = [
    InputConfig,
    SelectConfig
]
