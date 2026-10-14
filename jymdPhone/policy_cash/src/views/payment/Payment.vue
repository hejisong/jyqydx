<template>
  <div class="payment-list">
    <p class="policy-title">政策信息</p>
    <div class="policy-item">
      <p class="policy">政策文件：{{policyInfo.policyname}}</p>
      <p class="policy">政策大类：{{policyInfo.policycategoryname}}</p>
      <p class="policy" v-if="policyInfo.policysubclassname">政策小类：{{policyInfo.policysubclassname}}</p>
      <p class="policy">政策条件：{{policyInfo.policyconditions}}</p>
      <p class="policy">政策奖励：{{policyInfo.rdescription}}</p>
      <p class="policy" v-if="policyInfo.patentname">专利名称：{{policyInfo.patentname}}</p>
    </div>
    <p class="policy-title">支付信息</p>
    <div class="policy-item">
      <p class="policy">支付单位：{{payment.paycompany}}</p>
      <p class="policy">支付账户：{{payment.payaccount}}</p>
      <p class="policy">支付时间：{{payment.paytime}}</p>
      <p class="policy">支付金额：{{payment.payamount}} 元</p>
    </div>
    <p class="policy-title">收款信息</p>
    <div class="policy-item">
      <p class="policy">收款单位：{{payment.collectioncompany}}</p>
      <p class="policy">收款账户：{{payment.collectionaccount}}</p>
      <p class="policy">收款时间：{{payment.collectiontime}}</p>
      <p class="policy">收款金额：{{payment.collectionamount}} 元</p>
    </div>
    <p style="font-size:0.3rem;color:#2F95FA;" @click="handleCheck">查看回单</p>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import { Toast, Empty } from "vant";

export default {
  name: "Payment",
  data() {
    return {
      policyInfo: {},
      payment: {},
      url: "/doc/TRANS20210413200729020AUTO555.pdf"
    };
  },
  created() {
    const { id } = this.$route.query;
    this.getPaymentInfo(id);
  },
  methods: {
    handleCheck() {
      this.$router.push({
        path: "/browse",
        query: {
          url: this.url
        }
      });
    },
    async getPaymentInfo(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/withoutdetail",
        {
          params: {
            plycomresultdetailid: id
          }
        }
      );
      console.log("免申即享", resultData.data);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.policyInfo = resultObj.plycomresultdetailEntity;
        this.payment = resultObj.payment;
      }
    }
  }
};
</script>

<style scoped>
.payment-list {
  padding: 16px;
  padding-bottom: 0.98rem;
}

.policy-item {
  margin-bottom: 0.24rem;
  padding: 0.04rem 0.28rem;
  background-color: #f6f9fe;
  border-radius: 8px;
  font-size: 0.28rem;
  color: #666;
}

.policy-title {
  margin: 0.2rem 0;
  font-size: 0.3rem;
  font-weight: bold;
}

.policy {
  margin: 0.15rem 0;
}
</style>