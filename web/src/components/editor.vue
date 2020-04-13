<template>
    <div>
        <div id="eddd" ref="editor" style="text-align:left;"></div>
    </div>
</template>

<script>
    import E from 'wangeditor'

    export default {
        name: 'editor',
        props: {
            parentvalue: String
        },
        data() {
            return {
                editorContent: '',
                editor: ''
            }
        },
        methods: {
            //设置不可编辑
            noEdit() {
                this.editor.$textElem.attr('contenteditable', false);	//设置不可编辑
            },
            setValue1(value) {
                this.editor.txt.html(value)
            }
        },
        mounted: function () {
            this.editor = new E(this.$refs.editor)
            this.editor.customConfig.onchange = (html) => {
                this.editorContent = html
            }
            // 上传图片到服务器
            this.editor.customConfig.uploadImgServer = '/api/uploadImage/uplaodWangEditorImage'
            //隐藏网络图片tab
            this.editor.customConfig.showLinkImg = false
            //设置文件上传的参数名称
            this.editor.customConfig.uploadFileName = 'files'
            // 将图片大小限制为 10M
            this.editor.customConfig.uploadImgMaxSize = 10 * 1024 * 1024
            //自定义上传图片事件
            this.editor.customConfig.debug = true
            this.editor.customConfig.uploadImgHooks = {
                customInsert: function (insertImg, result, editor) {
                    if (result.errno === 0) {
                        alert("上次成功！")
                        let urlData = result.data
                        for (let i = 0; i < urlData.length; i++) {
                            insertImg(urlData[i])
                        }
                    }
                }
            }
            this.editor.customConfig.menus = [          // 菜单配置
                'head',  // 标题
                'bold',  // 粗体
                'fontSize',  // 字号
                'fontName',  // 字体
                'italic',  // 斜体
                'underline',  // 下划线
                'strikeThrough',  // 删除线
                'foreColor',  // 文字颜色
                'backColor',  // 背景颜色
                'link',  // 插入链接
                'list',  // 列表
                'justify',  // 对齐方式
                'quote',  // 引用
                'emoticon',  // 表情
                'image',  // 插入图片
                'table',  // 表格
                'undo',  // 撤销
                'redo'  // 重复
            ]
            this.editor.create()
        },
        watch: {
            editorContent() {
                this.$emit('func', this.editorContent)
            }
        }
    }
</script>

<style>
    #eddd .w-e-text-container {
        height: 600px !important; /*!important是重点，因为原div是行内样式设置的高度300px*/
    }

    .w-e-menu {
        z-index: 2 !important; /*解决el-dialog和富文本编辑冲突*/
    }

    .w-e-text-container {
        z-index: 1 !important; /*解决el-dialog和富文本编辑冲突*/
    }
</style>
