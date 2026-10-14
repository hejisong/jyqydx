<template>
  <div class="enterprise">
    <Head :show="true" title="一企来" />

    <div class="content">
      <div class="swiper">
        <mt-swipe :auto="3000">
          <mt-swipe-item>
            <img style="width:100%;margin-top:0.2rem" src="../../assets/imgs/banner@3x.png" alt />
            <span class="desc"></span>
          </mt-swipe-item>
        </mt-swipe>
      </div>

      <div class="enterprise-content">
        <img src="../../assets/imgs/意见与建议@3x.png" alt @click="goToSuggestion" />
        <img src="../../assets/imgs/投诉@3x.png" alt />
        <img src="../../assets/imgs/预约代办@3x.png" alt />
      </div>

      <div class="enterprise-info">
        <img src="../../assets/imgs/热线电话@3x.png" alt />
        <div class="enterprise-info-tel">
          <div class="text">热线电话：</div>
          <div class="tel">025-52612580</div>
        </div>
      </div>

      <div class="enterprise-info">
        <img src="../../assets/imgs/地址.png" alt />
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

export default {
  name: "Enterprise",
  components: {
    Head,
    "mt-swipe": Swipe,
    "mt-swipe-item": SwipeItem
  },
  methods: {
    goToSuggestion() {
      this.$router.push("/suggestion");
    }
  }
};
</script>

<style scoped>
.swiper {
  height: 3.3rem;
  background-color: #fff;
}

.swiper .banner-img {
  width: 100%;
  height: 3.3rem;
  border-radius: 8px;
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
}

.enterprise-content {
  display: flex;
  align-content: center;
  justify-content: space-between;
  margin-top: 0.2rem;
}

.enterprise-content img {
  width: 2.18rem;
  height: 2.18rem;
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