<template>
  <div class="flex">
    <div class="head" :style="outStyle">
      <p class="pu-left" ></p>
      <div class="title">{{title}}</div>
      <img v-if="addbtn" style="margin-right:15px" src="@/assets/imgs/add.png" alt="" @click="handleAdd">
      <span class="shenbao" v-if="!shenbao && !screen && !addbtn" style="color:#fff">站位</span>
      <span v-if="shenbao && noRegiste != '1'" class="shenbao" @click="linkTos">申报</span>
      <span v-if="shenbao && noRegiste == '1'" class="shenbao" style="color:#fff">申报</span>
      <span v-if="screen" class="shenbao" @click="screens">筛选</span>
    </div>
  </div>
</template>
<script>
export default {
  name: "Head",
  props: ["show", "title","addbtn",'shenbao','screen','noRegiste'],
  data() {
    return {};
  },
  computed: {
    outStyle() {
      return this.show ? { borderBottom: "1px solid #EEEEEE" } : {};
    }
  },
  methods:{
    // back(){
    //   this.$router.back()
    // },
    handleAdd(){
      this.$emit('adduthorization',true)
    },
    linkTos(){
      this.$emit('linkTo')
    },
    screens(){
      this.$emit('screenClick')
    }
  }
};
</script>

<style scoped>
.flex{
  height: 0.78rem;
}
.head {
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 300;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  /* padding: 0 16px; */
  height: 0.78rem;
  width: 100%;
  font-weight: bold;
  background-color: #fff;
}

.head .title {
  /* flex: 9; */
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
.shenbao{
  font-size: 14px;
  color: #4395ff;
  margin-right: 16px;
}
.pu-left{
  font-size: 14px;
  margin-left: 16px;
  width: 0.4rem;
  height: 0.4rem;
}
</style>
