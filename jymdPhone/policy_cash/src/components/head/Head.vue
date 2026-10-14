<template>
  <div class="head" :style="outStyle">
    <div class="title">{{title}}</div>

    <img v-if="addbtn" src="@/assets/imgs/add.png" alt="" @click="handleAdd">
  </div>
</template>
<script>
export default {
  name: "Head",
  props: ["show", "title","addbtn"],
  data() {
    return {};
  },
  computed: {
    outStyle() {
      return this.show ? { borderBottom: "1px solid #EEEEEE" } : {};
    }
  },
  methods:{
    handleAdd(){
      this.$emit('adduthorization',true)
    }
  }
};
</script>

<style scoped>
.head {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 0 16px;
  height: 0.78rem;
  font-weight: bold;
  background-color: #fff;
}

.head .title {
  flex: 9;
  font-size: 0.32rem;
  color: #000;
}

.head .login {
  flex: 1;
  font-size: 0.28rem;
  color: #3d91ff;
}

.head > img{
  width: 0.4rem;
  height: 0.4rem;
}
</style>
