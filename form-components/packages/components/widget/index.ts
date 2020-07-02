import input from './input/index.vue'
import InputConfig from './input/config.vue'
import select from './select/index.vue'
import SelectConfig from './select/config.vue'
import date from './date/index.vue'
import DateConfig from './date/config.vue'
import number from './number/index.vue'
import NumberConfig from './number/config.vue'
import upload from './upload/index.vue'
import UploadConfig from './upload/config.vue'

export const widgets = {
    input,
    select,
    date,
    number,
    upload

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
    'string:radio': 'radio',
    'range:date': 'dateRange',
    'range:dateTime': 'dateRange',
    '*?enum': 'select',
    'array?enum': 'checkboxes'
}

export const configs = [
    InputConfig,
    SelectConfig,
    DateConfig,
    NumberConfig,
    UploadConfig
]
