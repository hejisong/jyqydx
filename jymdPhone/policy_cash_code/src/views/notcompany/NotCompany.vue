<template>
  <div class="detailInfo">
    <div class="detailitem">
      <div class="left">企业名称</div>
      <div class="right">{{infoObj.enterprisename}}</div>
    </div>
    <div class="detailitem">
      <div class="left">法定代表人</div>
      <div class="right">{{infoObj.lrepresentative}}</div>
    </div>
    <div class="detailitem">
      <div class="left">注册资本</div>
      <div class="right">{{infoObj.recapital}}</div>
    </div>
    <div class="detailitem">
      <div class="left">社会信用代码</div>
      <div class="right">{{infoObj.uscreditcode}}</div>
    </div>
    <div class="detailitem">
      <div class="left">工商注册号</div>
      <div class="right">{{infoObj.businessregisnum}}</div>
    </div>
    <div class="detailitem">
      <div class="left">企业类型</div>
      <div class="right">{{infoObj.typesenterprises}}</div>
    </div>
    <div class="detailitem">
      <div class="left">企业地址</div>
      <div class="right">{{infoObj.companyregisaddress}}</div>
    </div>
    <div class="detailitem">
      <div class="left">经营范围</div>
      <div class="right">{{infoObj.naturebusiness}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotCompany",
  data() {
    return {
      infoObj: {}
    };
  },
  created() {
    const { infoObj } = this.$route.query;
    this.infoObj = infoObj;
  }
};
</script>

<style scoped>
.detailitem {
  display: flex;
  flex-wrap: wrap;
  padding: 0.2rem 0.25rem;
  border-bottom: 1px solid #f1f1f1;
  font-size: 0.28rem;
}

.detailitem .left {
  width: 2.2rem;
}

.detailitem .right {
  flex: 1;
  text-align: justify;
}
</style>