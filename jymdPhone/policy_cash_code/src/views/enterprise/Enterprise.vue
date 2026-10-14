<template>
  <div class="enterprise">
    <Head :show="true" title="一企来" />

    <div class="content">
      <div class="swiper">
        <img src="../../assets/imgs/banner@3x.png" alt />
        <!-- <mt-swipe :auto="3000">
          <mt-swipe-item>
            
            <span class="desc"></span>
          </mt-swipe-item>
        </mt-swipe> -->
      </div>

      <div class="enterprise-content">
        <img src="../../assets/imgs/yjyjy@3x.png" alt @click="goToSuggestion('1')" />
        <img src="../../assets/imgs/ts@3x.png" alt @click="goToSuggestion('2')" />
        <img src="../../assets/imgs/yydb@3x.png" alt @click="goToSuggestion('3')" />
      </div>

      <div class="enterprise-info">
        <img src="../../assets/imgs/dhrx@3x.png" alt />
        <div class="enterprise-info-tel">
          <div class="text">热线电话：</div>
          <div class="tel">025-52612580</div>
        </div>
      </div>

      <div class="enterprise-info">
        <img src="../../assets/imgs/dz.png" alt />
        <div class="enterprise-info-tel">
          <div class="text">地址：</div>
          <div class="tel">雨润大街99号建邺区政务中心c厅</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import { Swipe, SwipeItem } from "mint-ui";
import { Toast } from 'vant';

export default {
  name: "Enterprise",
  components: {
    Head,
    "mt-swipe": Swipe,
    "mt-swipe-item": SwipeItem
  },
  methods: {
    goToSuggestion(val) {
      let num = sessionStorage.getItem('noRegiste')
      if(num == '1'){
        Toast('暂无权限！');
        return
      }
      if(val == '1'){
        this.$router.push("/suggestion");
      }else if(val == '2'){
        this.$router.push({
          path: "/suggestion",
          query: {
            type:'complaint',
            vals:'2'
            // flag:"edit"
          }
        });
      }else{
        let data = JSON.parse(sessionStorage.getItem("companyInfo"))
        if(!data){
          Toast('您还没有企业，无法操作！')
          return
        }
        this.$router.push('/reserve') 
        // Toast('功能暂未开放！');      
      }
      
    },

 
  }
};
</script>

<style scoped>
.swiper {
  height: 3.3rem;
  width: 100%;
  overflow: hidden;
  border-radius: 8px;
  background-color: #fff;
}

.swiper img {
  width:100%;
  height: 100%;
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

.enterprise {
  padding-bottom: 0.98rem;
}

.content {
  padding: 0 16px;
  margin-top: 0.2rem;
}

.enterprise-content {
  display: flex;
  height: 2.2rem;
  width: 100%;
  overflow: hidden;
  /* align-content: center; */
  justify-content: space-between;
  margin-top: 0.2rem;
}

.enterprise-content img {
  height: 100%;
  width: 32%;
}

.enterprise-info {
  display: flex;
  align-items: center;
  margin-top: 0.28rem;
  padding: 0.28rem 0.3rem;
  border: 1px solid #f7f7f7;
  border-radius: 6px;
}

.enterprise-info img {
  width: 0.6rem;
  height: 0.6rem;
}

.enterprise-info-tel {
  margin-left: 0.32rem;
}

.text {
  font-size: 0.24rem;
  color: #999;
}

.tel {
  font-size: 0.28rem;
  font-weight: 600;
  color: #666;
}
</style>