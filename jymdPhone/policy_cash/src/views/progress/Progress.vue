<template>
  <div class="progress">
    <Head :show="true" title="兑现进度" />

    <robot-btn />
    <p style="margin-left:16px;font-size:0.3rem;font-weight:bold">政策信息</p>

    <div class="policy-item">
      <p class="policy">政策文件：{{policyInfo.policyname}}</p>
      <p class="policy">政策大类：{{policyInfo.policycategoryname}}</p>
      <p class="policy" v-if="policyInfo.policysubclassname">政策小类：{{policyInfo.policysubclassname}}</p>
      <p class="policy">政策条件：{{policyInfo.policyconditions}}</p>
      <p class="policy">政策奖励：{{policyInfo.rdescription}}</p>
      <p class="policy" v-if="policyInfo.patentname">专利名称：{{policyInfo.patentname}}</p>
    </div>

    <div class="exclusive" v-if="isShow=='true'">
      <img src="@/assets/imgs/exclusive.png" alt />
      <div class="direct">专属账户直通车</div>
    </div>

    <div class="g_con">
      <div class="expressRecord">
        <div class="expressRecord-single-close" v-for="(item,index) in dataList" :key="index">
          <!-- 左边子容器 -->
          <div class="expressRecord-single-noReach-online-top-close">
            <!-- 正在进行的时间轴上半个时间线 -->
            <div
              class="online-top-closing"
              :style="{ 'background':index == 0 ? '#fff' :'#dfebff' }"
            ></div>
            <!-- 正在进行的时间轴点 -->
            <div
              class="dot-closing"
              :style="{'borderColor':item.status == '已处理' ? '#3f93ff' :'#A9A9A9' }"
            ></div>
            <!-- 正在进行的时间轴下半个时间线 -->
            <div
              class="online-bottom"
              :style="{ 'background':index == dataList.length-1 ? '#fff' :'#dfebff' }"
            ></div>
          </div>

          <!-- 右边子容器 -->
          <div
            class="expressRecord-text"
            :style="{'background':item.status == '已处理' ? '#fff' :'#FBFBFB','boxShadow':item.status == '已处理' ? '0px 0px 8px rgba(0, 0, 0, 0.1)' : '' }"
          >
            <div class="content">
              <div
                class="title"
                :style="{'color':item.status == '已处理' ? '#3f93ff' :'#6C6C6C' }"
              >{{item.title}}</div>
              <div
                class="time"
                :style="{'color':item.status == '已处理' ? '#51c0a0' :'#898989' }"
              >开始时间：{{item.start}}</div>
              <div
                class="time"
                :style="{'color':item.status == '已处理' ? '#e98c82' :'#898989' }"
              >结束时间：{{item.end}}</div>
            </div>

            <div
              class="status"
              :style="{'color':item.status == '已处理' ? '#3f93ff' :'#ACACAC' ,'background':item.status == '已处理' ? '#F6F9FE' : '#fff'}"
            >{{item.status}}</div>
          </div>

          <!-- 相对父级容器绝对定位的日期 -->
          <!-- <div class="expressRecord-dating">
            <div class="expressRecord-date-text">昨天</div>
            <div class="expressRecord-date-time">20:39</div>
          </div>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import RobotBtn from "../../components/robot/RobotBtn";

import { baseUrl } from "../../network/baseUrl";
import { Step, Steps, Toast } from "vant";

export default {
  name: "Progress",
  components: {
    Head,
    RobotBtn
  },
  data() {
    return {
      dataList: [],
      isShow: false,
      policyInfo: {}
    };
  },
  created() {
    const { dataList, isShow, id } = this.$route.query;
    this.dataList = dataList;
    this.isShow = isShow;
    this.getPolicyData(id);
  },
  methods: {
    async getPolicyData(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/show",
        {
          params: {
            plycomresultdetailid: id
          }
        }
      );

      console.log(resultData);

      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.policyInfo = resultObj.plycomresultdetailEntity;
      }
    },

    handleExamine() {
      // this.$router.push({
      //   path: "/browse",
      //   query: {
      //     url: urlObj.path
      //   }
      // });
    }
  }
};
</script>

<style scoped>
.progress {
  width: 100%;
  height: 100%;
  padding-bottom: 0.98rem;
  background-color: #fff;
}

.policy-item {
  margin-left: 16px;
  margin-right: 16px;
  margin-bottom: 0.24rem;
  padding: 0.04rem 0.28rem;
  background-color: #f6f9fe;
  border-radius: 8px;
  font-size: 0.28rem;
  color: #666;
}

.policy-item .policy {
  margin: 0.1rem 0;
}

.exclusive {
  position: relative;
  margin-top: 0.2rem;
  padding: 0 16px;
}

.exclusive > img {
  width: 100%;
  height: 1.1rem;
}

.exclusive > .direct {
  position: absolute;
  top: 38%;
  left: 22%;
  transform: translateY(-40%);
  font-size: 0.3rem;
  color: #fff;
}

.g_con {
  width: 100vw;
  overflow-x: hidden;
}

.topExpress {
  width: 7.1rem;
  height: 1.54rem;
  background: #fff;
  margin: 0.1rem auto;
  display: flex;
  border-radius: 5px;
}

.topExpress-left {
  width: 1rem;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.topExpress-right {
  font-size: 0.26rem;
  color: #333;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  align-items: flex-start;
  padding: 0.2rem 0;
}

.topExpress-right-middle {
  font-size: 0.22rem;
}

.topExpress-right-bottom {
  font-size: 0.2rem;
  color: #666;
}

.expressRecord {
  width: 7rem;
  padding-bottom: 2rem;
  background: #fff;
  margin: 0 auto;
  border-radius: 5px;
}

.expressRecord-getAddress {
  width: 100%;
  font-size: 0.22rem;
  color: #999;
  display: flex;
}

.expressRecord-top {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.getAddress-icon {
  width: 0.3rem;
  height: 0.3rem;
  border-radius: 50%;
  background: #999;
  font-size: 0.2rem;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 0.8rem;
}

.getAddress-text {
  margin-left: 0.2rem;
}

.noReach-online-top-close {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 1.08rem;
}

.expressRecord-single-close {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  position: relative;
}

.expressRecord-single-noReach-online-top-close {
  display: flex;
  flex-direction: column;
}

.online-top-closing {
  width: 1px;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 0.6rem;
}

.online-top-close {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 1.08rem;
}

.dot-closing {
  width: 0.28rem;
  height: 0.28rem;
  border-radius: 100%;
  margin-left: 0.44rem;
  margin-top: 0.06rem;
  margin-bottom: 0.06rem;
  background: #fff;
  border: 2px solid#3F95FF;
}

.dot-close {
  width: 0.3rem;
  height: 0.3rem;
  border-radius: 100%;
  margin-left: 0.9rem;
  margin-top: 0.06rem;
  margin-bottom: 0.06rem;
  background: #fff;
  border: 2px solid #a9a9a9;
}

.online-bottom {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 0.6rem;
}

.online-bottom-start {
  width: 0.02rem;
  height: 0.8rem;
  /* background: #999; */
  margin-left: 1.08rem;
}

.expressRecord-text {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 5.8rem;
  /* padding: 12px; */
  box-sizing: border-box;
  margin-left: 0.3rem;
  background: #f6f9fe;
  border-radius: 6px;
  /* box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1); */
}

.content {
  margin: 0.24rem 0.28rem;
  height: 100%;
}

.content .title {
  font-size: 0.32rem;
  font-weight: bold;
  color: #3f93ff;
}

.content .time {
  margin-top: 0.04rem;
  font-size: 0.24rem;
  /* color: #898989; */
}

.time.start {
  color: #51c0a0;
}

.time.end {
  color: #e98c82;
}

.status {
  margin-right: 0.28rem;
  width: 1.2rem;
  height: 0.64rem;
  line-height: 0.64rem;
  text-align: center;
  background: #fff;
  border-radius: 6px;
  font-size: 0.26rem;
  font-weight: 600;
  color: #3f93ff;
}

.expressRecord-statusing {
  font-size: 0.26rem;
  color: #333;
}

.expressRecord-status-addressing {
  font-size: 0.22rem;
  color: #333;
}

.expressRecord-status {
  font-size: 0.26rem;
  color: #999;
}

.expressRecord-status-address {
  font-size: 0.22rem;
  color: #999;
}

.expressRecord-dating {
  position: absolute;
  height: 100%;
  /* top: 0;
  bottom: 0; */
  left: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: #333;
}

.expressRecord-date {
  position: absolute;
  height: 100%;
  /* top: 0;
  bottom: 0; */
  left: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: #999;
}

.expressRecord-date-text {
  font-size: 0.24rem;
}

.expressRecord-date-time {
  font-size: 0.18rem;
}
</style>
