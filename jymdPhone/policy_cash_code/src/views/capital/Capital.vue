<template>
  <div class="scroll-wrapper" :style="{height:boxHeight+'px'}">
    <iframe class="scroll-iframe" :src="url" id="ifra" frameborder="0" scrolling="no" width="100%"></iframe>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";

import { Toast } from "vant";

export default {
  name: "Capital",
  data() {
    return {
      url: "",
      boxHeight:'',
    };
  },
  created() {
    const info = JSON.parse(sessionStorage.getItem("companyInfo"))
    console.log("info",info);
    this.getAccessToken(info);
  },
  mounted(){
    let box = document.documentElement.clientHeight
    let bat = document.getElementById('tab-bar').offsetHeight
    this.boxHeight = box-bat
    console.log(box,bat)
    Toast.loading({
      message: "加载中...",
      forbidClick: true
    });
    var _this = this
    const iframe = document.querySelector('#ifra')
    // 处理兼容行问题
    if (iframe.attachEvent) {
      iframe.attachEvent('onload', function () {
        // iframe加载完毕以后执行操作
        console.log('iframe已加载完毕11')
      })
    } else {
      iframe.onload = function () {
        // iframe加载完毕以后执行操作
        console.log('iframe已加载完毕22')
        Toast.clear();

      }
    }
  },
  methods: {
    async getAccessToken(info) {
      // Toast.loading({
      //   message: "加载中...",
      //   forbidClick: true
      // });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/oauth/getYshApiToken",
        {
          params: {
            mobile:info.linktel,
            companyid:info.companyid
          }
        }
      );
      console.log("result", resultData);

      if (resultData.data.result) {
        // Toast.clear();
        this.url = resultData.data.resultObj.jumpUrl;
      }
    }
  }
};
</script>

<style scoped>
.scroll-wrapper {
  
  -webkit-overflow-scrolling: touch;
  overflow-y: scroll;
}

.scroll-wrapper .scroll-iframe {
  display: block;
  height: 100%;
}
</style>