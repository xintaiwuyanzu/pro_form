<template>
    <div id="app">
        <el-switch v-model="edit"/>
        <form-render
                class='content'
                v-if="!edit"
                :schema="schema"
                :data="data"
                width="600px"
        />
        <form-editor class='content' v-else/>
    </div>
</template>
<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import simple from './simple'

    @Component
    export default class App extends Vue {
        edit = false;

        data() {
            return {
                schema: simple.propsSchema,
                data: simple.formData
            }
        }


    }
</script>

<style lang="scss">
    html,
    body {
        height: 100%;
        font: caption;
        margin: 0px;

        #app {
            font-family: Avenir, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            text-align: center;
            color: #2c3e50;
            height: 100%;
            display: flex;
            flex-direction: column;

            .content {
                flex: 1;
                overflow: auto;
            }
        }
    }
</style>
