<template>
  <div id="tab-bar" >
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "TabBar",
  data() {
    return {
      docmHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      showHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      hideshow: true //显示或者隐藏footer
    };
  },
  mounted() {
    // window.onresize监听页面高度的变化
    window.onresize = () => {
      return (() => {
        this.showHeight =
          document.documentElement.clientHeight || document.body.clientHeight;
      })();
    };
  },

  watch: {
    //监听显示高度
    showHeight() {
      if (this.docmHeight > this.showHeight) {
        //隐藏
        this.hideshow = false;
      } else {
        //显示
        this.hideshow = true;
      }
    }
  },

  beforeDestroy() {
    window.onresize = null; //注销window.onresize事件
  }
};
</script>

<style scoped>
#tab-bar {
  display: flex;
  background-color: #fff;
  border-top: 1px solid #f7f7f7;
  position: fixed;
  z-index: 1000;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
