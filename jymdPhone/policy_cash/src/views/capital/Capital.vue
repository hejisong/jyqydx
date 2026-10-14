<template>
  <div class="scroll-wrapper">
    <iframe class="scroll-iframe" :src="url" frameborder="0" scrolling="no" width="100%"></iframe>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";

import { Toast } from "vant";

export default {
  name: "Capital",
  data() {
    return {
      url: ""
    };
  },
  created() {
    this.getAccessToken();
  },
  methods: {
    async getAccessToken() {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/oauth/getYshApiToken"
      );
      console.log("result", resultData);

      if (resultData.data.result) {
        Toast.clear();
        this.url = resultData.data.resultObj.jumpUrl;
      }
    }
  }
};
</script>

<style scoped>
.scroll-wrapper {
  height: 100%;
  -webkit-overflow-scrolling: touch;
  overflow-y: scroll;
}

.scroll-wrapper .scroll-iframe {
  display: block;
  height: 100%;
}
</style>