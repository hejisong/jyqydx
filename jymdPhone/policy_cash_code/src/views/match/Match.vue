<template>
  <div class="match">
    <Head :show="true" title="现存差距详情" />

    <div class="module">
      <div class="difference">
        <img src="@/assets/imgs/statistics.png" alt />
        当前差距
      </div>
      <!-- <div class="income">
        <div>
          1.营业收入达到
          <span class="num">1000</span> 万元
        </div>
        <div>
          企业当前营业收入
          <span class="num">800万元</span>
        </div>
      </div>-->
      <div class="income">
        <div style="width:60%">
          <div>
            <span class="num">{{ policyInfo.policyconditions }}</span>
          </div>
        </div>
        <div>
          <!-- 当前实缴注册资本 -->
          企业2020年业务收入
          <!-- <span class="num capital">{{ policyInfo.recapital }}</span> -->
          <span class="num capital">7431万元</span>
        </div>
      </div>
    </div>
    <div class="module">
      <div class="difference">
        <!-- <img src="@/assets/imgs/suited.png" alt /> -->
        政策信息
      </div>
      <list ref="childList" :showBtn='true' :list="plyfileEntityList" :policyInfo="policyInfo" />
    </div>
    <div class="list">
      <p style="margin:0.2rem 0;font-size:0.3rem;font-weight:bold" >资料信息</p>
      <van-cell v-for="(item,index) in list" :key="index">
        <!-- <div> -->
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="indexStyle">{{ index+1 }}</span>
            <span class="custom-title">{{ item.materialname }}</span>
          </template>
          <template #right-icon>
            <span class="no" v-if="item.status == '1'">未匹配</span>
            <span class="yes" v-else>已匹配</span>
          </template>
      </van-cell>
    </div>
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import List from "../../components/list/List";
import { baseUrl } from "../../network/baseUrl";
import { Toast, Empty } from "vant";

export default {
  name: "Match",
  components: {
    Head,
    List
  },
  data() {
    return {
      list:[],
      policyInfo: {},
      plyfileEntityList: []
    };
  },
  created() {
    const { id } = this.$route.query;
    console.log(id);
    this.getMatchData(id);
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
    async getMatchData(id) {
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
          patentname
        } = resultObj.plycomresultdetailEntity;
        let {
          recapital,
          minamount,
          maxamount
        } = resultObj
        this.policyInfo = {
          rdescription,
          policycategoryname,
          policyconditions,
          policyname,
          patentname,
          recapital,
          minamount,
          maxamount
        };
        this.list = resultObj.plyfileEntityList;
        // setTimeout(()=>{
        //   this.$refs.childList.getList()
        // },10)
      }
      console.log("获取匹配详情数据", resultData);
    }
  }
};
</script>

<style scoped>
.match {
  padding-bottom: 0.98rem;
  /* background-color: #f7f9fb; */
}
.list>>>.van-cell{
  /* padding: 0.4rem 0.2rem; */
  background-color: #f6f9fe;
}
.yes,.no{
  padding:0 2px;
  color: #ffffff;
  background-color: #07c160;  
  /* border-radius: 1px; */
  font-size: 12px;
  margin-left: 0.2rem;
}
.no{
  background-color: gray
}
.module {
  margin-bottom: 0.1rem;
  padding: 12px 16px;
  background: #fff;
}

.module:last-child {
  margin-bottom: 0;
}

.module > .difference {
  display: flex;
  align-items: center;
  margin-bottom: 0.2rem;
  font-size: 0.32rem;
  font-weight: bold;
}
.indexStyle{
  display:inline-block;
  margin-right:0.25rem;
  height: 16px;
  width: 16px;
  text-align: center;
  line-height: 18px;
  padding: 2px;
  border-radius: 50%;
  color: #ffffff;
  background-color: #3d91ff
}
.difference > img {
  margin-right: 0.24rem;
  width: 0.4rem;
  height: 0.4rem;
}

.module > .income {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.2rem;
  padding: 0.3rem 0.2rem;
  border: 1px solid #3d91ff;
  border-radius: 6px;
  font-size: 0.28rem;
}

.module > .income:last-child {
  margin-bottom: 0;
}

.income > div:nth-child(2) {
  width: 1.9rem;
  word-wrap: break-word;
}
.list{
  padding: 0 18px;
  margin-top: -0.98rem;
}
.income .num {
  color: #3d91ff;
}

.income .capital {
  color: #fc011a;
}
</style>
