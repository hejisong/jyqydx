<template>
  <div class="accredit">
    <Head :show="true" :screen="true" @screenClick="screenClick" title="政策细则" />

    <div class="accredit_list">
        <div class="original_list" v-if="listString.length>0">
            <div class="formChild van-hairline--surround" v-for="(item,index) in listString" :key="index">
                <ul class="van-hairline--top-bottom" style="height:34px;line-height: 34px">
                <li class="clearfix">
                    <span class="pu-left">{{ index+1 }}、</span>
                    <span class="pu-right" style="color:#1989fa" @click="linkTo(item.pldtailid)">{{ item.sbstate !='1'?'申报':'已申报' }}</span>
                </li>
                </ul>
                <ul><span class="titleLabel"> 政策类别：{{ item.nplcategory }}</span></ul>
                <ul><span class="titleLabel">政策条件：{{ item.plconditions }}</span></ul>
                <ul><span class="titleLabel">政策奖励：{{ item.rdescription }}</span></ul>
            </div>
        </div>
        <van-empty description="暂无数据" v-else />
        <van-popup v-model="show" round position="bottom" :style="{ height: '50%' }">
          <van-picker @confirm="entClick" @cancel='cancel' show-toolbar title="筛选" :columns="columns" />
        </van-popup>
        
    </div>
      
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import { baseUrl } from "../../network/baseUrl";

import { Toast, Empty } from "vant";

export default {
  name: "createdDeclare",
  components: {
    Head
  },
  data() {
    return {      
      listString:[],
      show:false,
      columns: [],
    };
  },
  created() {
    this.getData()
    this.queryPolicyCategory()
  },
  mounted(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
  },
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
  },
  methods: {
    screenClick(){
      this.show = true
    },
    entClick(val,val2){
      console.log(val)
      this.getData(val)
      this.show = false
    },
    cancel(){
      this.show = false
    },
    async queryPolicyCategory(){
        let resultData = await this.$axios.get(
            baseUrl + "/interface/policydeclar/queryPolicyCategory"
        );
        console.log('=============>',resultData.data)
        let isTrue = resultData.data.result
        let data = resultData.data.resultObj
        if(isTrue){
            let num1 = {
              values:data,
              defaultIndex: 0,
            }
            num1.values.unshift('无')
            // let num2 = {
            //   values:data.subclasses,
            //   defaultIndex: 0,
            // }
            // num2.values.unshift('无')
            this.columns.push(num1)
            // this.columns.push(num2)
            console.log('===__________',this.columns)
        }else{
            Toast(resultData.data.errorMsg);
            return
        }
    },
    async linkTo(ids){
        const { isLogin } = sessionStorage;
        if(!isLogin){
            Toast("请登录!");
            return
        }
        let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
        let resultData = await this.$axios.get(
            baseUrl + "/interface/policyfile/apply",
            {
                params: {
                    pldtailid:ids,
                    companyid:data.companyid
                }
            }
        );
        console.log('=============>',resultData.data.errorMsg)
        let isTrue = resultData.data.result
        if(isTrue){
            let id = resultData.data.resultObj
            console.log(id,'[[[[[[[[[[')
            this.$router.push({
                path: "/policyDetail",
                query: {
                    id: id,
                }
            });
        }else{
            Toast(resultData.data.errorMsg);
            return
        }
    },
    async getData(val){
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let data = JSON.parse(sessionStorage.getItem("companyInfo"))
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policydeclar/queryDetailByPolicyFile",
        {
          params: {
            plfids:this.$route.query.id,
            search:val && val[0] != '无'?val[0]:'',
            companyid:data.companyid
            // policycategory:val && val[0] != '无'?val[0]:'',
            // policysubclass:val && val[1] != '无'?val[1]:''
          }
        }
      );
      Toast.clear();
      this.listString = resultData.data.resultObj
      console.log('细则=====>',resultData)
    },
  }
};
</script>

<style scoped>
.accredit_list {
    padding: 12px 16px;
}
.original_list {
  padding: 12px 16px;
}

.original_item {
  display: flex;
  align-items: center;
  margin-bottom: 0.24rem;
  padding: 0.28rem 0.32rem;
  /* height: 2.4rem; */
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.original_item > img {
  width: 0.4rem;
  height: 0.4rem;
}

.original_info {
  margin-left: 0.32rem;
}

.original_info .original_info_title {
  width: 5.3rem;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  font-size: 0.28rem;
  font-weight: 500;
  color: #4288ff;
}

.original_info_writ {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0.02rem 0;
  font-size: 0.24rem;
  font-weight: 400;
  color: #666;
}
.titleLabel{
  color: #606266
}
.formChild{
  font-size: 14px;
  width: 96%;
  margin: auto auto 0.35rem auto;
  padding: 0 0.2rem 0.1rem 0.2rem;
  border-radius: 5px;
  box-shadow:rgba(0, 0, 0, 0.2) 1px 1px 5px 1px;
}
.formChild ul{
  padding: 0.1rem;
  margin-bottom: 0.2rem;
}
.original_info_writ .time {
  color: #999;
}

.original_info_unit {
  font-size: 0.24rem;
  font-weight: 400;
  color: #666;
}

.writ_type_item.selectedStatus {
  outline: 1px solid #5390ff;
  background: #e0ebff;
  color: #5390ff;
}
.label{
  margin-right:10px;
  padding:3px 10px;
  /* border:1px solid red; */
  background-color: #f7f8fa;
  color: #B4B4B4;
  border-radius: 15px;
  font-size:12px
}
.labelBgc{
  background-color: #1989fa;
  color: #ffffff;
}
.pu-left{
  float: left;
}
.pu-right{
  float: right;
}
.clearfix:after{
  content:".";       
  display:block;       
  height:0;       
  clear:both;       
  visibility:hidden;
}    
</style>