<template>
  <div class="chooseCompany">
    <Head :show="true" title="选择企业" />

    <van-radio-group v-if="companyList.length > 0" v-model="radio">
      <van-cell-group>
        <van-cell
          v-for="(item,index) in companyList"
          :key="item.companyid"
          :title="item.enterprisename"
          clickable
          @click="handleChoose(index)"
        >
          <template #right-icon>
            <van-radio :name="index" />
          </template>
        </van-cell>
      </van-cell-group>
    </van-radio-group>

    <!-- 搜索暂无记录提示 -->
    <van-empty v-else image="search" description="暂无记录" />
  </div>
</template>

<script>
import { RadioGroup, Radio, Empty } from "vant";
import Head from "../../components/head/Head";
import {baseUrl} from "../../network/baseUrl";

export default {
  name: "ChooseCompany",
  components: {
    Head
  },
  data() {
    return {
      radio: "",
      companyList: []
    };
  },
  created() {
    const searchValue = this.$route.query.searchValue;
    this.getCompanyData(searchValue);
  },

  methods: {
    async getCompanyData(searchValue) {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/search",
        {
          params: {
            searchname: searchValue
          }
        }
      );
      const { result, resultObj } = resultData.data;
      console.log(resultData.data);
      if (result) {
        this.companyList = resultObj;
      }
    },

    handleChoose(index) {
      // console.log('当前选中企业信息',this.companyList[index]);
      const { companyid, phoneno, enterprisename } = this.companyList[index];
      let searchInfo = { companyid, phoneno, enterprisename };
      // this.$store.commit("searchInfo", searchInfo);
      sessionStorage.setItem("searchInfo", JSON.stringify(searchInfo));
      this.$router.go(-1); //回退页面
    }
  }
};
</script>

<style>
.chooseCompany {
  padding-bottom: 0.98rem;
}
</style>