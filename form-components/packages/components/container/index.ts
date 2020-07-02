import form from './form.vue'
import grid from './grid.vue'
import table from './table.vue'

export const containers = {
    form,
    grid,
    table
}
/**
 * 容器配置映射
 *
 * key可以是任何类型，value值需要是containers的key
 */
export const containerMapping = {
    'form': 'form',
    'grid': 'grid',
    'table': 'table',
    'default': 'form'
}
