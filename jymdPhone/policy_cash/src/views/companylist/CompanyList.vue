<template>
  <div class="companylist">
    <div
      class="listitem"
      v-for="item in companyList"
      :key="item.companyid"
      @click="handleToDetail(item)"
    >{{item.enterprisename}}</div>

    <!-- 搜索暂无记录提示 -->
    <van-empty v-if="companyList.length <= 0" image="search" description="暂无记录" />
  </div>
</template>

<script>
import { Empty } from "vant";
import { baseUrl } from "../../network/baseUrl";

export default {
  name: "CompanyList",
  data() {
    return {
      companyList: []
    };
  },
  created() {
    const { companyList } = this.$store.state;
    this.companyList = companyList;
  },
  methods: {
    handleToDetail(item) {
      this.$store.commit("SaveSearchId", item.companyid);

      this.$router.push("/assess");
    }
  }
};
</script>

<style scoped>
.listitem {
  display: flex;
  flex-wrap: wrap;
  padding: 0.2rem 0.25rem;
  border-bottom: 1px solid #f1f1f1;
  font-size: 0.28rem;
}
</style>