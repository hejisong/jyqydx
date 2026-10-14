<template>
  <!-- 机器人提问悬浮按钮-->
  <div class="robot" @click="handleQuiz" v-if="hideshow">
    <i class="iconfont icon-jiqiren2"></i>
    <span>点我提问</span>
  </div>
</template>

<script>
export default {
  name: "RobotBtn",
  data() {
    return {
      docmHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      showHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      hideshow: true,
      quizUrl:
        "https://h5-alimebot.dingtalk.com/intl/index.htm?sourceType=ding_talk&from=DEFCYOnKKb0OKvr0E3UhjF7rZhc"
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

  mounted() {
    // window.onresize监听页面高度的变化
    window.onresize = () => {
      return (() => {
        this.showHeight =
          document.documentElement.clientHeight || document.body.clientHeight;
      })();
    };
  },

  beforeDestroy() {
    window.onresize = null; //注销window.onresize事件
  },

  methods: {
    // 点击向机器人提问
    handleQuiz() {
      window.location.href = this.quizUrl;
    }
  }
};
</script>

<style scoped>
.robot {
  margin-bottom: 0.8rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 1.28rem;
  height: 1.28rem;
  color: #3e92ff;
  border-radius: 50%;
  background-color: #ffffff;
  position: fixed;
  bottom: 1.16rem;
  right: 28px;
  z-index: 10;
}

.robot span {
  font-size: 0.3rem;
  transform: scale(0.7, 0.7);
  transform-origin: center top;
}

.robot .iconfont {
  font-size: 30px;
  /* font-weight: bold; */
  color: #2658fd;
}
</style>
