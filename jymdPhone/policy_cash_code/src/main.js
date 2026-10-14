import Vue from "vue";
import App from "./App";
import router from "./router";
import store from './store/index';
import preview from "vue-photo-preview";
import "vue-photo-preview/dist/skin.css";
// 配置项感觉也可以放在具体的组件中
var options = {
  fullscreenEl: true, //关闭全屏按钮
  tapToClose: true
};
Vue.use(preview, options);

import fastclick from "fastclick";
if ('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function() {
      fastclick.attach(document.body);
    }, false);
}
fastclick.prototype.focus = function(tag){
  tag.focus()
};

import Vant from 'vant';
import 'vant/lib/index.css';
Vue.use(Vant);

import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'

import Mint from 'mint-ui';
Vue.use(Mint);
import 'mint-ui/lib/style.css'

import axios from 'axios';

import './assets/css/iconfont.css';

Vue.use(VueAwesomeSwiper)

Vue.config.productionTip = false;

Vue.prototype.$axios = axios

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  store,
  render: h => h(App)
});
