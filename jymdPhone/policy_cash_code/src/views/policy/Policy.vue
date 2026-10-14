<template>
  <div class="policy">
    <robot-btn />
    <Head :show="true" title="智能匹配详情" />

    <div style="padding:16px">
      <div class="state" v-if="isShow">
        <i class="iconfont icon-gantanhao"></i>说明：以下政策贵单位已满足条件，但尚未到约定到兑现时间，需要省市配套资金到位。
      </div>
      <p style="margin:0.2rem 0;font-size:0.3rem;font-weight:bold">政策信息</p>

      <list ref="childList" @initData='initData' :list="plyfileEntityList" :showBtn='showBtn' :show='noShow' :policyInfo="policyInfo" />
    </div>
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import List from "../../components/list/List";
import RobotBtn from "../../components/robot/RobotBtn";
import { baseUrl } from "../../network/baseUrl";
import { Toast, Empty } from "vant";

export default {
  name: "Policy",
  components: {
    List,
    RobotBtn,
    Head
  },
  data() {
    return {
      isShow: false,
      policyInfo: {},
      noShow:this.$route.query.noShow,
      showBtn:false,
      plyfileEntityList: []
    };
  },
  created() {
    const { isShow, id ,showBtn} = this.$route.query;
    console.log( isShow, id,'-----------------------')
    this.isShow = isShow;
    this.showBtn = showBtn
    this.getListData(id);
  },
  mounted(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
  },
  
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
    Toast.clear()
  },
  methods: {
    initData(){
      const { isShow, id } = this.$route.query;
      this.getListData(id);
    },
    async getListData(id) {
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

      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        let {
          rdescription,
          policycategoryname,
          policyconditions,
          policyname,
          policydeclar,
          policysubclassname
        } = resultObj.plycomresultdetailEntity;
        this.policyInfo = {
          rdescription,
          policycategoryname,
          policyconditions,
          policyname,
          policydeclar,
          policysubclassname
        };
        this.plyfileEntityList = resultObj.plyfileEntityList;
      }
      console.log("获取列表数据", this.plyfileEntityList,this.policyInfo);
      setTimeout(()=>{
        this.$refs.childList.getList()
      },10)
    }
  }
};
</script>

<style scoped>
.policy {
  /* padding: 16px 16px 0.98rem 16px; */

  background-color: #fff;
}

.state {
  display: flex;
  align-items: center;
  margin-bottom: 0.26rem;
  padding: 0.24rem 0.28rem;
  background-color: #fff2f2;
  border-radius: 6px;
  font-size: 0.28rem;
  color: #dc3838;
}

.state .iconfont {
  margin-right: 0.16rem;
  font-size: 0.32rem;
  color: #dc3838;
}
</style>