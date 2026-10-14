<template>
  <div id="app">
    <router-view />

    <main-tab-bar />
  </div>
</template>

<script>
import MainTabBar from "./components/tabbar/MainTabBar";
import { Toast } from 'vant';
import { getUrlParams } from "@/api";
export default {
  name: "App",
  components: {
    MainTabBar
  },
  created(){
    let noRegiste = getUrlParams("noRegiste")
    sessionStorage.setItem('noRegiste',noRegiste)
    Toast.clear()
  }
};
</script>

<style scoped>
@import "./assets/css/base.css";
#app{
  width: 100vw;
  height: 100vh;
}
</style>
