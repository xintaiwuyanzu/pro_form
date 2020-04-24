<template>
    <section>
        <Render/>
    </section>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import {CreateElement} from "vue";
    import {mapState} from "vuex";

    @Component({
        computed: mapState('Form', ['current'])
    })
    class Render extends Vue {
        current!: Field

        render(h: CreateElement) {
            if (this.current) {
                return h(this.$form.pre + this.current.label + 'Config')
            }
        }
    }

    @Component({
        components: {
            Render
        }
    })
    export default class EditorRight extends Vue {
    }
</script>

<style scoped>

</style>
