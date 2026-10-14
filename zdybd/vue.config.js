// vue.config.js
module.exports = {
  publicPath: '/',

  devServer: {
    disableHostCheck: true,
    proxy: {
      '/api': {
        target: 'http://ceshi.centit.com/framework',
        cookiePathRewrite: {
          '/framework/': '/api/',
        },
        changeOrigin: false,
      },
    },
  },
}
