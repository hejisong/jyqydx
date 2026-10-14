<template>
  <div class="accredit">
    <Head :show="true" title="申报专栏" />

    <div class="accredit_list" v-if="accreditList && accreditList.length > 0">
      <div class="accredit_item" v-for="item in accreditList" :key="item.id">
        <!-- <img src="../../assets/imgs/shouquan.png" alt /> -->
        <div class="accredit_info" @click="linkDetail(item)">
          <div class="accredit_info_name">{{item.declarename}}</div>
          <div class="accredit_info_comfirm">
            <van-tag color="#ffe1e1" text-color="#ad0000">{{ getCode(item.declare_end_date) }}</van-tag>
            <span>{{ item.releasedate }}</span> 
          </div>
          <!-- <div
            class="accredit_info_cancel"
            v-if="item.cancellationtime"
          >取消时间：{{item.cancellationtime | formatDate}}</div> -->
        </div>
        <!-- <div
          class="accredit_btn"
          v-if="item.state == '2'"
          style="background:#F4F8FF;color:#4288FF;"
        >{{getState(item.state)}}</div> -->
      </div>
    </div>

    <van-empty description="暂无数据" v-else />
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import { baseUrl } from "../../network/baseUrl";

import { Toast, Empty } from "vant";

export default {
  name: "declareList",
  components: {
    Head
  },
  data() {
    return {      
      result: [],
      companyid: "",
      accreditList: [],
      radioValue: "",
      username: "",
      noRegiste:'',
      linktel: "",
      linknum:''
    };
  },
  created() {
   
    // this.companyid = JSON.parse(
    //   sessionStorage.getItem("companyInfo")
    // ).companyid;
    // this.companyid = '4028079a79a285850179a28611360000'
    this.getAccreditData(this.companyid);
  },

  filters: {
    formatDate(date) {
      var date = new Date(date);
      var YY = date.getFullYear() + "-";
      var MM =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      var DD = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      var hh =
        (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      var mm =
        (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
        ":";
      var ss =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return YY + MM + DD + " " + hh + mm + ss;
    }
  }, 

  methods: {
    getCode(vla){
      let data = new Date().getTime()
      if(data>vla){
        return "已过期"
      }else{
        return '可申报'
      }
    },
    linkDetail(item){
        this.$router.push(
          {
            path: "/declareDetail",
            query: {
                // content: item.filecontent,
                id:item.uploadfileid,
                name:item.uploadfilename,
                listString:item.policyfileid,
                listName:item.policyfilename,
                entTime:item.declare_end_date,
                starTime:item.declare_start_date
            }
          }
        )
    },
    // 获取授权列表数据
    async getAccreditData(companyid) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true,
        duration:'0'
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policydeclar/queryList",
      );

      console.log("result", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.accreditList = Array.isArray(resultObj.content) ? resultObj.content : [];
      }
    },    
    getState(state) {
      return state == "2" ? "已取消" : "取消授权";
    },

    // 取消授权
    async handleCancelAccredit() {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/cancel",
        {
          params: {
            companyid: this.companyid
          }
        }
      );

      console.log("取消授权返回", resultData);
      const { result } = resultData.data;
      if (result) {
        Toast("取消授权成功");
        setTimeout(() => {
          this.getAccreditData(this.companyid);
        }, 500);
      }
    },        
  }
};
</script>

<style scoped>
.van-icon-search::before {
  content: "\e626";
}

.van-search__content {
  background-color: #fff !important;
}

.accredit {
  padding-bottom: 0.98rem;
}

.accredit_list {
  padding: 12px 16px;
}

.accredit_item {
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
  padding: 0.28rem 0.3rem;
  /* height: 2.4rem; */
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.accredit_item > img {
  width: 0.4rem;
  height: 0.4rem;
}

.accredit_info {
  /* margin-left: 0.32rem; */
  width: 100%;
}

.accredit_info .accredit_info_name {
  font-size: 0.28rem;
  color: #4288ff;
}

.accredit_info .accredit_info_comfirm {
  /* margin: 0.08rem 0; */
  /* text-align: right */
  display: flex;
  justify-content: space-between;
  margin: 0.01rem 0;
}

.accredit_info .accredit_info_comfirm,
.accredit_info .accredit_info_cancel {
  font-size: 0.24rem;
  color: #666;
}
.stutas{
  color: red;
  background-color: red;
  color: #ffffff;
}
.accredit_btn {
  width: 1.2rem;
  height: 0.6rem;
  line-height: 0.6rem;
  font-size: 0.24rem;
  color: #fff;
  text-align: center;
  background-color: #4288ff;
  border-radius: 2px;
}

/* 遮罩层样式 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  position: relative;
  width: 6.4rem;
  height: 7.5rem;
  background-color: #fff;
  border-radius: 6px;
}

.block .head {
  padding: 0 16px;
  height: 1rem;
  line-height: 1rem;
  font-size: 0.32rem;
  color: #3f94ff;
  font-weight: bold;
}

.block .content {
  width: 100%;
  /* height: calc(8.6rem - 2rem); */
  height: 5rem;
  background-color: #f8f8f8;
  border-top: 1px solid #ebedf0;
}

.checkbox {
  width: 100%;
  min-height: 0.8rem;
  max-height: 4rem;
  overflow-x: hidden;
  overflow-y: scroll;
}

.block .bottom {
  position: absolute;
  left: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  width: 100%;
  padding-left: 3.1rem;
  height: 1rem;
  line-height: 1rem;
  font-size: 0.32rem;
  color: #3f94ff;
  font-weight: bold;
}

.bottom .btn {
  margin-right: 0.16rem;
  width: 1.4rem;
  height: 0.6rem;
  line-height: 0.6rem;
  font-weight: normal;
  font-size: 0.28rem;
  text-align: center;
  border-radius: 3px;
}

.cancel {
  color: #999;
  background-color: #e9e9e9;
}

.confirm {
  color: #fff;
  background-color: #4288ff;
}

.not-data {
  font-size: 0.32rem;
  text-align: center;
  margin: 1rem 0;
  color: #ccc;
}
</style>