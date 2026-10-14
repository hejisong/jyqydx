<template>
  <div class="home">
    <robot-btn />

    <div class="enterprise" @click="goToEnterPrise" v-if="hideshow">
      <img src="../../assets/imgs/企业管理@3x.png" alt />
      <span>一企来</span>
    </div>

    <head-bar />

    <div class="swiper">
      <mt-swipe :auto="3000">
        <mt-swipe-item>
          <img class="banner-img" src="@/assets/imgs/banner.png" alt />
          <span class="desc"></span>
        </mt-swipe-item>
      </mt-swipe>
    </div>

    <div class="cash">
      <div class="title">
        <div class="mark"></div>
        <div class="assess">兑现评估</div>
      </div>
    </div>

    <div class="honor-code">
      <div class="code">
        <van-field
          v-model="value"
          label
          clearable
          placeholder="请输入企业名称或统一社会信用代码"
          @focus="handleFocus"
        />
      </div>
      <div class="code-btn" @click="handleToAssess">
        <div class="text">兑现评估</div>
        <div class="icon">
          <i class="iconfont icon-youjiantou1"></i>
        </div>
      </div>
    </div>

    <div class="operate">
      <img src="@/assets/imgs/explain1.png" alt @click="handleOperate" />
    </div>

    <div class="explain">
      <img src="@/assets/imgs/authorization.png" alt @click="handleAccredit" />
      <img src="@/assets/imgs/policy.png" alt @click="handlePolicy" />
    </div>
  </div>
</template>

<script>
import HeadBar from "../../components/head/HeadBar";
import Indicates from "../../components/indicates/Indicates";
import RobotBtn from "../../components/robot/RobotBtn";
import { baseUrl } from "../../network/baseUrl";
import { Field, Toast, Overlay, RadioGroup, Radio } from "vant";

import { Swipe, SwipeItem } from "mint-ui";
export default {
  name: "Home",
  components: {
    HeadBar,
    Indicates,
    RobotBtn,
    "mt-swipe": Swipe,
    "mt-swipe-item": SwipeItem
  },
  data() {
    return {
      value: "",
      show: false,
      radio: "",
      docmHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      showHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      hideshow: true
    };
  },
  created() {
    console.log(this.docmHeight, this.showHeight);
  },
  computed: {},
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
    goToEnterPrise() {
      this.$router.push("/enterprise");
    },
    // 输入框聚焦事件
    handleFocus() {
      let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
      if (companyInfo != null) {
        this.value = companyInfo.companyName
          ? companyInfo.companyName
          : companyInfo.enterprisename;
      }
    },
    // 兑现评估
    async handleToAssess() {
      if (this.value) {
        let resultData = await this.$axios.get(
          baseUrl + "/interface/plycomresultdetail/querycompany",
          {
            params: {
              searchName: this.value
            }
          }
        );

        const { resultObj } = resultData.data;
        console.log(resultObj);

        if (resultObj.count == 1) {
          const searchid = resultObj.companyEntityList[0].companyid;
          this.$store.commit("SaveSearchId", searchid);
          this.$router.push("/assess");
        } else if (resultObj.count != 1) {
          this.$store.commit("saveCompanyList", resultObj.companyEntityList);
          this.$router.push("/companylist");
        }

        // if (resultObj.jumpFlag) {
        //   this.$store.commit("saveResultObj", resultObj);
        //   this.$router.push("/assess");
        // } else {
        //   this.$store.commit("saveCompanyList", resultObj.companyEntityList);
        //   this.$router.push({
        //     path: "/companylist"
        //   });
        // }
      } else {
        Toast("请填写企业名称或信用代码!");
      }
    },

    // 查看兑现步骤说明
    handleOperate() {
      this.$router.push("/instruction");
    },

    // 企业授权
    handleAccredit() {
      const { isLogin } = sessionStorage;
      if (isLogin) {
        this.$router.push("/accredit");
      } else {
        Toast("请先登录!");
      }
    },

    // 查看最新政策
    handlePolicy() {
      this.$router.push("/original");
    },

    handleConfirm() {
      this.show = false;
    }
  }
};
</script>

<style>
.van-cell {
  display: flex;
  align-items: center;
  padding: 15px 10px !important;
  height: 100%;
  border-radius: 5px;
}

.code .van-field__control {
  font-size: 0.27rem;
  color: #3d91ff !important;
}

.code .van-field__control::placeholder {
  font-size: 0.27rem;
  color: #3d91ff;
  font-weight: bold !important;
}

.block > .van-hairline--top-bottom::after {
  border: none;
}

[class*="van-hairline"]::after {
  border: none !important;
}
</style>

<style scoped>
.enterprise {
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
  bottom: 3.4rem;
  right: 28px;
  z-index: 10;
}

.enterprise span {
  font-size: 0.3rem;
  transform: scale(0.7, 0.7);
  transform-origin: center top;
}

.enterprise img {
  width: 0.6rem;
  height: 0.6rem;
}

.home {
  height: calc(100vh - 49px);
  background-color: #fff;
  /* background: url("../../assets/imgs/bg.png") no-repeat;
  background-size: cover;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-position: center 0; */
}

.swiper {
  padding: 0 16px;
  height: 3.2rem;
  background-color: #fff;
}

.swiper .banner-img {
  width: 100%;
  height: 3.2rem;
  border-radius: 8px;
}

.desc {
  font-weight: 600;
  opacity: 0.9;
  padding: 0.1rem;
  height: 0.4rem;
  line-height: 0.4rem;
  width: 100%;
  color: #fff;
  position: absolute;
  bottom: 0;
}

.honor-code {
  display: flex;
  justify-content: space-between;
  background-color: #fff;
  padding: 0 16px;
}

.honor-code .code {
  flex: 4.5;
  margin-right: 0.16rem;
  padding: 0.32rem;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  width: 5.6rem;
  height: 1.6rem;
  background-color: #ecf3ff;
  border-radius: 5px;
}

.honor-code .code-btn {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0;
  box-sizing: border-box;
  width: 1.12rem;
  height: 1.6rem;
  background-color: #3a8eff;
  border-radius: 5px;
}

.code-btn .text {
  width: 0.58rem;
  height: 0.8rem;
  letter-spacing: 1px;
  text-align: center;
  font-size: 0.25rem;
  color: #fff;
  word-break: break-all;
}

.code-btn .icon {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 0.16rem;
  width: 0.22rem;
  height: 0.22rem;
  text-align: center;
  border-radius: 100%;
  color: #fff;
  background-color: #ff9623;
}

.icon .iconfont {
  font-size: 0.24rem;
}

.cash {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.24rem 16px;
  background-color: #fff;
}

.cash .title {
  display: flex;
  align-items: center;
}

.cash .mark {
  margin-right: 0.24rem;
  width: 0.1rem;
  height: 0.32rem;
  background-color: #3d91ff;
}

.cash .assess {
  display: flex;
  flex-direction: column;
  font-size: 0.32rem;
  font-weight: bold;
}

.operate {
  margin-top: 0.28rem;
  padding: 0 16px;
  height: 1.14rem;
}

.operate img {
  width: 100%;
}

.explain {
  display: flex;
  margin-top: 0.2rem;
  padding: 0 16px;
  /* height: 0;
  overflow: hidden;
  padding-bottom: 25.4%; */
  height: 1.12rem;
  background-color: #fff;
}

.explain > img {
  flex: 1;
  margin-right: 0.1rem;
  width: 49%;
}

.explain > img:last-child {
  margin-right: 0;
}

/* 遮罩层 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  padding: 0.2rem;
  width: 6.4rem;
  background-color: #fff;
  border-radius: 4px;
}

.bound {
  margin-top: 0.4rem;
  width: 100%;
  height: 0.8rem;
  line-height: 0.8rem;
  background-color: #4288ff;
  font-size: 0.28rem;
  color: #fff;
  text-align: center;
  border-radius: 6px;
}

.wrapper .van-cell {
  padding: 10px 8px !important;
}
</style>
