const path = require('path')
const webpack = require('webpack')
module.exports = {
    pages: {
        index: {entry: './src/main.js', title: '表单管理系统'},
    },
    productionSourceMap: false,
    configureWebpack: {
        resolve: {
            alias: {
                '@': path.join(process.cwd(), 'src')
            }
        },
        plugins: [new webpack.ContextReplacementPlugin(/moment[\/\\]locale$/, /zh-cn/)]
    },
    devServer: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8081/api',
                pathRewrite: {'^/api': '/'}
            }
        }
    }
}
