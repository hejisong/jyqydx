<template>
  <div class="assess">
    <Head :show="true" title="兑现评估" />
    <robot-btn />
    <Indicates :dataList="dataList" :showFlag="showFlag" />
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import Indicates from "../../components/indicates/Indicates";
import RobotBtn from "../../components/robot/RobotBtn";
import { baseUrl } from "../../network/baseUrl";

export default {
  name: "Assess",
  components: {
    Head,
    Indicates,
    RobotBtn
  },
  data() {
    return {
      dataList: [
        {
          id: 1,
          title: "恭喜您！以下政策免申即享",
          count: 0,
          isSelected: true,
          msg: "查看兑现明细",
          list: []
        },
        {
          id: 2,
          title: "补充材料即可兑现",
          count: 0,
          isSelected: false,
          msg: "查看补充材料",
          list: []
        },
        {
          id: 3,
          title: "企业资质能力提升后可兑现",
          count: 0,
          isSelected: false,
          msg: "查看现存差距",
          list: []
        },
        {
          id: 4,
          title: "可享受政策预告",
          count: 0,
          isSelected: false,
          msg: "查看详情",
          list: []
        }
      ],
      showFlag: true
    };
  },
  created() {
    const { searchid } = this.$store.state;
    let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
    var companyid = "";
    if (companyInfo != null) {
      companyid = companyInfo.companyid;
    }

    this.getAssessList(searchid, companyid);
    // const { resultObj } = this.$store.state;
    // console.log(resultObj);
    // this.dataList[0].list = resultObj.withoutRequest;
    // this.dataList[0].count = resultObj.withoutRequest.length;
    // this.dataList[1].list = resultObj.replenishFiles;
    // this.dataList[1].count = resultObj.replenishFiles.length;
    // this.dataList[2].list = resultObj.improvingCapacity;
    // this.dataList[2].count = resultObj.improvingCapacity.length;
    // this.dataList[3].list = resultObj.predict;
    // this.dataList[3].count = resultObj.predict.length;

    // for (let i = 0; i < this.dataList.length; i++) {
    //   // for (let key in resultObj) {
    //   //   if (this.dataList[i].id == resultObj[key][0].enjoypolicycategory) {
    //   //     this.dataList[i].list = resultObj[key];
    //   //     this.dataList[i].count = resultObj[key].length;
    //   //   }
    //   // }
    // }
  },
  methods: {
    async getAssessList(searchid, companyid) {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/findall",
        {
          params: {
            searchid,
            companyid
          }
        }
      );

      const { resultObj, showFlag } = resultData.data;
      this.showFlag = resultObj.showFlag;

      this.dataList[0].list = resultObj.withoutRequest;
      this.dataList[0].count = resultObj.withoutRequest.length;
      this.dataList[1].list = resultObj.replenishFiles;
      this.dataList[1].count = resultObj.replenishFiles.length;
      this.dataList[2].list = resultObj.improvingCapacity;
      this.dataList[2].count = resultObj.improvingCapacity.length;
      this.dataList[3].list = resultObj.predict;
      this.dataList[3].count = resultObj.predict.length;

      // .then(res => {
      //   console.log("兑现评估列表数据", res);
      //   let { resultObj } = res.data;
      //   for (let i = 0; i < this.dataList.length; i++) {
      //     for (let key in resultObj) {
      //       if (
      //         this.dataList[i].id == resultObj[key][0].enjoypolicycategory
      //       ) {
      //         this.dataList[i].list = resultObj[key];
      //         this.dataList[i].count = resultObj[key].length;
      //       }
      //     }
      //   }
      // })
      // .catch(err => {
      //   console.log(err);
      // });
    }
  }
};
</script>

<style scoped>
.assess {
  padding-bottom: 0.98rem;
  height: 100%;
}
.assess img {
  width: 100%;
}
</style>
