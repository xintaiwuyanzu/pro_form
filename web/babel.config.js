const isProd = process.env.NODE_ENV === 'production'
const plugins = []
if (isProd) {
    plugins.unshift(['transform-remove-console', {exclude: ['error', 'warn']}])
}
module.exports = {
    presets: [
        '@vue/cli-plugin-babel/preset'
    ],
    plugins
}
