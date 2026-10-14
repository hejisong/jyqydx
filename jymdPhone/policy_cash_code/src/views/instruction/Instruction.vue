<template>
  <div class="instruction">
    <Head :show="true" :title="type ==1?'政策申报步骤说明':'政策兑现步骤说明'" />
    <img class="instruction_img" v-if="type == '1'" src="@/assets/imgs/handbook.png" alt="">
    <img v-else  class="instruction_img" src="@/assets/imgs/linkBanner.png" alt />
  </div>
</template>

<script>
import Head from "../../components/head/Head";

export default {
  name: "Instruction",
  components: {
    Head
  },
  data(){
    return {
      type:this.$route.query.types
    }
  }
};
</script>

<style scoped>
.instruction {
  padding-bottom: 0.98rem;
}

.instruction .instruction_img {
  display: block;
  margin: 0.2rem auto;
}
</style>