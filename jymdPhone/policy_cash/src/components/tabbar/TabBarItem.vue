<template>
  <div class="tab-bar-item" @click="itemClick">
    <div v-if="!isActive">
      <slot name="item-icon"></slot>
    </div>
    <div v-else>
      <slot name="item-icon-active"></slot>
    </div>
    <div :style="activeStyle">
      <slot name="item-text"></slot>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";

export default {
  name: "TabBarItem",
  props: {
    path: String,
    activeColor: {
      type: String,
      default: "#4495fc"
    }
  },
  data() {
    return {};
  },

  computed: {
    isActive() {
      return this.$route.path.indexOf(this.path) !== -1;
    },
    activeStyle() {
      return this.isActive ? { color: this.activeColor } : {};
    }
  },
  methods: {
    itemClick() {
      const { isBind, isLogin } = sessionStorage;
      if (isLogin) {
        if (
          !isBind &&
          ["/schedule", "/remind", "/capital"].includes(this.path)
        ) {
          Toast("请先绑定企业!");
        } else {
          this.$router.replace(this.path);
        }
      } else {
        if (["/schedule", "/remind", "/capital"].includes(this.path)) {
          Toast("请先登录!");
        } else {
          this.$router.replace(this.path);
        }
      }
    }
  }
};
</script>

<style scoped>
.tab-bar-item {
  flex: 1;
  text-align: center;
  height: 49px;
  font-size: 12px;
  color: #ccc;
}

.tab-bar-item img {
  margin-top: 3px;
  margin-bottom: 2px;
  width: 20px;
  height: 20px;
  vertical-align: middle;
}

.active {
  color: #4495fc;
}
</style>
