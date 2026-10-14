<template>
  <tab-bar>
    <tab-bar-item path="/home">
      <img slot="item-icon" src="@/assets/imgs/zyn.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/zyy.png" alt />
      <div slot="item-text">首页</div>
    </tab-bar-item>
    <tab-bar-item path="/schedule">
      <img slot="item-icon" src="@/assets/imgs/wddxn.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/wddxy.png" alt />
      <div slot="item-text">我的兑现</div>
    </tab-bar-item>
    <tab-bar-item path="/remind">
      <img slot="item-icon" src="@/assets/imgs/dxtxn.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/dxtxy.png" alt />
      <div slot="item-text">兑现提醒</div>
    </tab-bar-item>
    <tab-bar-item path="/capital" v-if="noRegiste != '1'">
      <img slot="item-icon" src="@/assets/imgs/zjn.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/zjy.png" alt />
      <div slot="item-text">资金</div>
    </tab-bar-item>
  </tab-bar>
</template>

<script>
import TabBar from "./Tabbar";
import TabBarItem from "./TabBarItem";
export default {
  components: {
    TabBar,
    TabBarItem
  },
  data(){
    return {
      noRegiste:''
    }
  },
  mounted(){
    this.noRegiste = sessionStorage.getItem('noRegiste')
  }
};
</script>

<style></style>
