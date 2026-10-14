<template>
    <div class="child" :style="note">
        <div class="head">
            <div class="close">
                <img @click="close" src="../../../assets/imgs/close2.png" alt="">
            </div>
            <p style="height:3.5rem;margin:0;font-size:0.35rem;line-height:4.2rem;color:#ffffff;margin-left:0.4rem;">
              奖励政策明细
            </p>
        <div class="comtent">
            <div v-for="(item,index) in childList" :key="index" class="listItem van-hairline--bottom">
              <div class="original_item" >
                  <div class="original_info" :class="noRegiste != '1'?'':'boxs'">
                      <div class="original_info_title" :class="noRegiste != '1'?'':'boxs2'">
                          {{ item.rdescription }}
                      </div>
                      <div class="original_info_writ" :class="noRegiste != '1'?'':'boxs2'">
                          {{ item.nplcategory }} 
                      </div>
                  </div>
                  <div class="Approval" v-if="noRegiste != '1'">
                      <van-button type="info" :disabled="item.sbstate == '1'" size="mini" @click="linkTo(item.pldtailid)">{{ item.sbstate != '1'?'申 报':'已申报' }}</van-button>
                  </div>
              </div>
              <div class="details van-multi-ellipsis--l2">
                {{ item.plconditions }}
              </div>
            </div>
        </div>
      </div>     
  </div>
</template>

<script>
import { baseUrl } from "../../../network/baseUrl";
import { Toast, Notify, Empty,Tag } from "vant";

export default {
  name: "propView",  
  data() {
    return {
      note: {
        backgroundImage: "url(" + require("../../../assets/imgs/prop.png") + ")"
      }
    };
  },
  props:{
    childList: {
      type: Array,
      default: []
    },
    noRegiste:{
      type:String,
      default:''
    }
  },
  created() {

  },
  watch: {
   
  },

  methods: {
    close(){
      this.$emit('close')
    },
    async linkTo(ids){      
      const { isLogin } = sessionStorage;
      if(!isLogin){
        Toast("请登录!");
        return
      }
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      if(!data){
        Toast("你还没有企业，无法操作！");
        return
      }
      if(data.islegalperson == '2'){
        Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
        return
      }
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
  }
};
</script>
<style scoped>

.child{
    width: 6.6rem;
    height: 12rem;
    overflow: hidden;
    background-repeat: no-repeat;
    background-color: transparent;
    background-size:100%;

}
.head{
    position: relative;
    width: 100%;
}
.child{
    position: relative;
}
.comtent{
  height: 8.1rem;
  overflow-y: auto;  
}
.close img{
    width: 0.5rem;
    height: 0.5rem;
    position: absolute;
    right: 0;
    top: 0;
}
.original_item {
  display: flex;
  align-items: center;
  /* margin-bottom: 0.24rem; */
  /* padding: 0.28rem 0.32rem; */
  /* height: 2.4rem; */
  /* background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1); */
}
.details{
  font-size: 0.28rem;
  color: #666;
  /* display: flex; */
  align-items: center;
  padding: 0 0.32rem;
}
.original_info {
  margin-left: 0.32rem;
}
.listItem{
  /* border:1px solid red; */
  /* margin-bottom: 0.28rem; */
  padding: 0.28rem 0;
}
.original_info_title {
  width: 5.3rem;
  font-size: 0.32rem;
  font-weight: 500;
  color: #333333;
}
.original_info_writ {
  /* display: flex; */
  /* align-items: center;
  justify-content: space-between; */
  margin: 3px 0;
  width: 5.3rem;
  font-size: 0.28rem;
  font-weight: 400;
  color: #666;
}
.original_info_unit {
  font-size: 0.24rem;
  font-weight: 400;
  color: #666;
}


.text_1{
  /* display: inline-block; */
  white-space: nowrap; 
  overflow: hidden;
  text-overflow:ellipsis;
}

.Approval{
    font-size: 0.28rem;
    text-align: right
}
.boxs{
  width: 100%;
  margin: 0;
  padding: 0 0.32rem
}
.boxs2{
  width: 100%;
}
</style>