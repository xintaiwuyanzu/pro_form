<template>
    <div>
        <render v-for="field in data"
                :key="field.id"
                :data="field"
                @click.native="current(field)"/>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import {CreateElement} from "vue";
    import {mapMutations} from "vuex";

    @Component({
        props: {data: Object}
    })
    class Render extends Vue {
        data!: Field

        render(h: CreateElement) {
            if (this.data) {
                return h(this.$form.pre + this.data.label)
            }
        }
    }

    const middleProps = Vue.extend({
        props: {
            data: Array
        }
    })

    @Component({
        methods: {
            ...mapMutations('Form', ['current'])
        },
        components: {Render}
    })
    export default class EditorMiddle extends middleProps {

    }
</script>

<style scoped>

</style>
