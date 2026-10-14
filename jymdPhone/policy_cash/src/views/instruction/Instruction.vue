<template>
  <div class="instruction">
    <Head :show="true" title="兑现步骤说明" />

    <img class="instruction_img" src="@/assets/imgs/handbook.png" alt />
  </div>
</template>

<script>
import Head from "../../components/head/Head";

export default {
  name: "Instruction",
  components: {
    Head
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