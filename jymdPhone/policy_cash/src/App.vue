<template>
  <div id="app">
    <router-view />

    <main-tab-bar />
  </div>
</template>

<script>
import MainTabBar from "./components/tabbar/MainTabBar";
export default {
  name: "App",
  components: {
    MainTabBar
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
