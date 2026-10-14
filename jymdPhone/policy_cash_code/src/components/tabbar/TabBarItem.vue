<template>
  <div class="tab-bar-item" ref="tabItem" @click="itemClick">
    <div v-if="!isActive">
      <slot name="item-icon"></slot>
    </div>
    <div v-if="isActive">
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
      let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"))
      console.log(isLogin,companyInfo)
      if (isLogin && companyInfo) {
        const { islegalperson } = JSON.parse(sessionStorage.getItem("companyInfo"))
        if (
          !isBind &&
          ["/schedule", "/remind", "/capital"].includes(this.path)
        ) {
          Toast("请先绑定企业!");
        } else if(islegalperson != null && ["/capital"].includes(this.path) && islegalperson != '1' && islegalperson != '0'){
          Toast("您没有权限操作，请先授权!");
          // this.$router.replace(this.path);
        }else {
          this.$router.replace(this.path);
        }
      } else if(isLogin && !companyInfo){
        if(["/capital",'/remind','/schedule'].includes(this.path)){
          Toast("您还没有企业，无法操作!");
          return
        }else{
          this.$router.replace(this.path);
        }
        
      }else {
        if (["/schedule", "/remind", "/capital"].includes(this.path)) {
          let noRegiste = sessionStorage.getItem('noRegiste')
          if(noRegiste == '1'){
            Toast("您还没有企业，无法操作!");
            return
          }
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
