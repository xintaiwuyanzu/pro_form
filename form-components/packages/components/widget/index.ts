import Input from './input'
import InputConfig from './input/config.vue'
import Select from './select'
import SelectConfig from './select/config.vue'

export const widgets = {
    string: Input,
    select: Select
}
export const configs = [
    InputConfig,
    SelectConfig
]
