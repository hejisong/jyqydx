import Vue from 'vue'
import Router from 'vue-router'
import fun from './fun.js'

//  saveScrollPosition: true,  suppressTransitionError: true saveScrollPosition一定要在history模式下才有效.
Vue.use(Router)

const scrollBehavior = (to, from, savedPosition) => {
  if (savedPosition) {
    // savedPosition is only available for popstate navigations.
    return savedPosition
  } else {
    const position = {}
    // new navigation.
    // scroll to anchor by returning the selector
    if (to.hash) {
      position.selector = to.hash

      // specify offset of the element
      if (to.hash === '#hash10') {
        position.offset = {
          y: 100
        }
      }
    }
    // check if any matched route config has meta that requires scrolling to top
    if (to.matched.some(m => m.meta.scrollToTop)) {
      // cords will be used if no selector is provided,
      // or if the selector didn't match any element.
      position.x = 0
      position.y = 0
    }
    // if the returned position is falsy or an empty object,
    // will retain current scroll position.
    return position
  }
}


export default new Router({
  // mode: 'history',
  // saveScrollPostioin: true,
  base:'/jymd',  //包名 一级目录和文件名一致
  routes: [
    {
      path:'/',
      redirect: '/home',
      meta: {title: '首页'},
    },
    ...fun,
  ]
})
