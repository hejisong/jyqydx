<template>
  <div class="remind">
    <!-- <head-bar :show="true" /> -->
    <robot-btn />
    <Indicates :dataList="dataList" :showFlag="showFlag" />
    <!-- 
    <List :list="firstList" />

    <Indicates title="兑现预告" />

    <div class="explain">
      <div class="state">
        <i class="iconfont icon-gantanhao"></i>说明：以下为未到开始申报时间的已出台政策
      </div>
    </div>

    <List :list="secondList" />-->
    <!-- <button @click="open">open</button> -->

    <div class="box" v-if="dialogShow">
      <div class="alert">
        <img src="@/assets/imgs/popup.png" alt />
        <div class="info">
          <p>当前政策兑现时间</p>
          <p>2021年2月13日</p>
          <div @click.stop="hasKnow">知道了</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeadBar from "../../components/head/HeadBar";
import Indicates from "../../components/indicates/Indicates";
import RobotBtn from "../../components/robot/RobotBtn";
import List from "../../components/list/List";
import { baseUrl } from "../../network/baseUrl";

import { Toast } from "vant";

export default {
  name: "Remind",
  components: {
    HeadBar,
    Indicates,
    RobotBtn,
    List
  },
  data() {
    return {
      dialogShow: false,
      showFlag: true,
      outStyle: {
        borderBottom: "1px solid #F3F3F3"
      },
      dataList: [
        {
          id: 1,
          title: "企业资质能力提升",
          count: 0,
          isSelected: true,
          msg: "查看现存差距",
          list: []
        },
        {
          id: 2,
          title: "可享受政策预告",
          count: 0,
          msg: "查看详情",
          isSelected: true,
          list: []
        }
      ]
    };
  },

  created() {
    let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
    this.getRemindData(companyInfo.companyid);
  },
  methods: {
    open() {
      this.dialogShow = true;
    },
    close() {
      this.dialogShow = false;
    },
    hasKnow() {
      this.dialogShow = false;
    },

    async getRemindData(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/findremind",
        {
          params: {
            companyid: id
          }
        }
      );
      const { result, resultObj } = resultData.data;
      console.log("兑现提醒", resultObj);
      if (result) {
        Toast.clear();
        this.dataList[0].list = resultObj.improvingCapacity;
        this.dataList[0].count = resultObj.improvingCapacity.length;
        this.dataList[1].list = resultObj.predict;
        this.dataList[1].count = resultObj.predict.length;
        this.showFlag = resultObj.showFlag;
      }
    }
  }
};
</script>

<style scoped>
.remind {
  padding-bottom: 0.98rem;
  height: 100%;
  background-color: #fff;
}

.explain {
  margin-bottom: 0.1rem;
}

.explain .state {
  display: flex;
  align-items: center;
  padding: 0.24rem 0.28rem;
  background-color: #faf7f1;
  border-radius: 6px;
  font-size: 0.28rem;
  color: #ceb47b;
}

.state .iconfont {
  margin-right: 0.16rem;
  font-size: 0.32rem;
  color: #ceb47b;
}

.box {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.box .alert {
  position: relative;
  margin: 3rem auto;
  width: 6rem;
  height: 7rem;
}

.alert > img {
  width: 100%;
  height: 100%;
}

.alert .info {
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translateX(-50%);
}

.info > p {
  margin: 0;
  font-size: 0.36rem;
  font-weight: bold;
  text-align: center;
}

.info > p:nth-child(2) {
  margin-top: 0.04rem;
  color: #4288ff;
}

.info > div {
  margin-top: 0.68rem;
  width: 5.4rem;
  height: 0.8rem;
  line-height: 0.8rem;
  background-color: #4288ff;
  font-size: 0.28rem;
  color: #fff;
  text-align: center;
  border-radius: 6px;
}
</style>
