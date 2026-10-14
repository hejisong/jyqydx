<template>
  <div class="schedule">
    <robot-btn />

    <!-- <head-bar :show="true" /> -->

    <div class="cash">
      <div class="title">
        <div class="mark"></div>
        <div class="assess">我的兑现</div>
      </div>
    </div>
    
    <Table v-if="tableList && tableList.length > 0" :tableList="tableList" />
    <div v-else class="nodate">
      <img src="../../assets/imgs/nodate.png" alt="">
      <p style="font-size:18px;margin:0;margin-bottom:10px">暂无相关数据</p>
      <p class="nodateMsg">
        当前无政策兑现数据，请等待政策匹配结果反馈。
      </p>
      <!-- <van-button style="padding:7px 40px;font-size:14px" size="small" round type="info" @click="Refresh">刷 新</van-button> -->
      
    </div>
    
  </div>
</template>

<script>
import HeadBar from "../../components/head/HeadBar";
import Indicates from "../../components/indicates/Indicates";
import Table from "../../components/table/Table";
import RobotBtn from "../../components/robot/RobotBtn";

import { baseUrl } from "../../network/baseUrl";

import { Toast, Notify, Empty } from "vant";

export default {
  name: "Schedule",
  components: {
    HeadBar,
    Indicates,
    isShow:false,
    Table,
    RobotBtn
  },
  data() {
    return {
      dialogShow: false,
      // tableList: [
      //   {
      //     plycomresultid: 1,
      //     policycategoryname: "奖励30万元",
      //     cashflag: 0,
      //     policysubclassname: "落户奖励",
      //     policyconditions: "开办奖励",
      //     particularyear: ""
      //   },
      //   {
      //     plycomresultid: 2,
      //     policycategoryname: "对区域经济贡献100%的标准给予奖励",
      //     cashflag: 1,
      //     policysubclassname: "经济贡献奖",
      //     policyconditions: "股权投资企业股权投资企业",
      //     particularyear: "2021年1月"
      //   },
      //   {
      //     plycomresultid: 3,
      //     policycategoryname: "每场补助2万元至10万元",
      //     cashflag: 1,
      //     policysubclassname: "其它扶持",
      //     policyconditions: "活动奖励",
      //     particularyear: "2021年1月"
      //   }
      // ]
      tableList: []
    };
  },
  created() {
    
  },
  mounted(){
    let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
    this.getScheduleData(companyInfo.companyid);
  },
  methods: {
    Refresh(){
      let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
      this.getScheduleData(companyInfo.companyid);
    },
    open() {
      this.dialogShow = true;
    },
    close() {
      this.dialogShow = false;
    },
    async getScheduleData(id) {
      console.log("companyid", id);
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/list",
        {
          params: {
            companyid: id
          }
        }
      );
      let { result, resultObj } = resultData.data;
      if (result) {
        this.tableList = resultObj.plycomresultdetailList.reverse();
        if(this.tableList.length>0){
          this.isShow = false
        }else{
          this.isShow = true
        }
      }
      setTimeout(()=>{
        Toast.clear();
      },1000)

      console.log(this.tableList);
    }
  }
};
</script>

<style scoped>
.schedule {
  padding-bottom: 0.98rem;
  height: 100%;
  background-color: #fff;
}
.nodate{
  text-align: center;
  margin-top: 35%;
}
.nodate img{
  width: 185px;
  height: 100px;
}
.cash {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.24rem 16px;
  /* height: 0.92rem; */
  /* background-color: pink; */
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
.nodateMsg{
  font-size:15px;
  width: 257px;
  margin: 0 auto;
  color:#909399
}
.cash .assess {
  display: flex;
  flex-direction: column;
  font-size: 0.3rem;
  font-weight: bold;
}
</style>
