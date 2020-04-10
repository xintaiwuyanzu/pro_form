<template>
    <div class="breadcrumb-container">
        <strong class="title">
            {{title?title:menu.label}}
        </strong>
        <section class="slot">
            <el-button type="primary" round  style="margin-right: 6px" v-if="back" icon="el-icon-back" size="mini" @click="$router.back()">
            </el-button>
            <slot></slot>
        </section>
    </div>
</template>
<script>
    export default {
        props: {
            title: {required: false},
            back: {required: false, default: false}
        }
    }
</script>
