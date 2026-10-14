<template>
  <tab-bar>
    <tab-bar-item path="/home">
      <img slot="item-icon" src="@/assets/imgs/home.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/home_active.png" alt />
      <div slot="item-text">首页</div>
    </tab-bar-item>
    <tab-bar-item path="/schedule">
      <img slot="item-icon" src="@/assets/imgs/sand.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/sand_active.png" alt />
      <div slot="item-text">我的兑现</div>
    </tab-bar-item>
    <tab-bar-item path="/remind">
      <img slot="item-icon" src="@/assets/imgs/remind.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/remind_active.png" alt />
      <div slot="item-text">兑现提醒</div>
    </tab-bar-item>
    <tab-bar-item path="/capital">
      <img slot="item-icon" src="@/assets/imgs/capital.png" alt />
      <img slot="item-icon-active" src="@/assets/imgs/capital_active.png" alt />
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
  }
};
</script>

<style></style>
