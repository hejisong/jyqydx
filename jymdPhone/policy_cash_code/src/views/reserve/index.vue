<template>
    <div class="reservelist">
        <Head :show="true" title="预约代办" />
        <div class="suggestion-btn"  @click="reserveClick">
        <img src="../../assets/imgs/wyfb@3x.png" alt />
        <span>我要预约</span>
        </div>
        <div v-if="dataList && dataList.length>0">
            <ul class="reservs" v-for="(item,index) in dataList" :key="index">
                <li class="Agent" style="width:100%;">
                    <p class="name">代办人</p>
                    <p class="content">{{ item.agencyman }}</p>
                </li>
                <li class="Agent" style="width:100%;">
                    <p class="name">预约人</p>
                    <p class="content">{{ item.orderman }}</p>
                </li>
                <li class="Agent" style="width:100%;">
                    <p class="name">预约人手机号</p>
                    <p class="content">{{ item.contact }}</p>
                </li>
                <li class="Agent" style="width:100%;">
                    <p class="name">预约时间</p>
                    <p class="content">{{ getDate(item.ordertime) }}</p>
                </li>
                <li class="Agent" style="width:100%;">
                    <p class="name">状态</p>
                    <p class="content"><van-tag :type="item.state == 1?'success':'warning'">{{ item.state == 1?'已处理':'未处理' }}</van-tag></p>
                </li>
                <li class="Agent" style="width:100%;">
                    <p class="name">备注</p>
                    <p class="content">{{ item.remark }}</p>
                </li>
            </ul>
        </div>
        <div v-else class="nodate">
            <img src="../../assets/imgs/nodate.png" alt="">
            <p style="font-size:18px;margin:0;margin-bottom:10px">暂无相关数据</p>                       
        </div>
        <!-- <van-button class="btn-father" @click="reserveClick" type="info" block >我要预约</van-button> -->
    </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import Head from "../../components/head/Head";
import { Search, Toast, Empty } from "vant";

export default {
  name: "index",
  components: {
    Head
  },
  data() {
    return {
      questionContent:'',
      showQuestion:false,
      showPopover:false,
      dataList:[],
      activeNames:['1'],
      sms:'',
      tel:''
    };
  },

  created() {
    console.log(sessionStorage.getItem("userInfo"))
  },

  mounted() {
    this.getAdmin()
  },

  methods: {
    //时间戳转换方法    date:时间戳数字
    getDate(date) {
      var date = new Date(date);
      var YY = date.getFullYear() + '-';
      var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
      var DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
      var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
      var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
      var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
      return YY + MM + DD +" "+hh + mm + ss;
    },
    reserveClick(){
        this.$router.push('/addReserve')
    },
    async getAdmin(){
        Toast.loading({
            message: "加载中...",
            forbidClick: true
        });
        let user = JSON.parse(sessionStorage.getItem("userInfo"))
        let data = {
            linknum: user.idNumber,// 身份证号码
            username: user.userName// 
        }
        console.log('data------。',data)
        await this.$axios.get(baseUrl + "/interface/company/orderagencyList",{params:data}).then((res) => {
            console.log(res)
            if(res.data.result){
                this.dataList = res.data.resultObj
            }
            Toast.clear()
        }).catch((err) => {
            console.log('请求失败')
        });;
    },
    closedQuestion(){},

  }
};
</script>

<style scoped>
    .reservelist{
        height: 100%;
        width: 100%;
        font-size: 14px;
        background-color: #ffffff;
        overflow-y: auto;
        box-shadow:0 2px 10px -3px rgba(0, 0, 0, .12);
        padding-bottom: 1.3rem;
    }
    .reservelist>>>.van-field__label{
        width: 58px
    }
    .questionBox>>>.van-cell__value{
        border-radius: 5px;
        padding-left: 0.15rem;
        border:1px solid #EBEEF5;        
    }
    .titleMsg{
        font-size: 18px;
        text-align: center;
        font-weight: 550;
        line-height: 1.1rem
    }
    .suggestion-btn {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 1.28rem;
  height: 1.28rem;
  color: #3e92ff;
  border-radius: 50%;
  background-color: #ffffff;
  position: fixed;
  bottom: 2rem;
  right: 28px;
  z-index: 9999;
}

.suggestion-btn span {
  font-size: 0.3rem;
  transform: scale(0.7, 0.7);
  transform-origin: center top;
}

.suggestion-btn img {
  width: 0.6rem;
  height: 0.6rem;
}
    .fl{
        float: left;
    }
    .nodate{
        text-align: center;
        margin-top: 35%;
    }
    .nodate img{
        width: 185px;
        height: 100px;
    }
    .btn-father {
       
        text-align: center;
        font-size: 16px;
        height: 1rem;
        position: fixed;
        bottom: 0;
        width: 94%;
        left: 3%;
        bottom: 0.1rem;
        z-index: 999;
    }
    .reservs{
        width: 90%;
        margin: 0.3rem  auto auto auto;
        border: 1px solid #F2F6FC;
        border-radius: 5px;
        box-shadow:0 2px 10px -3px rgba(0, 0, 0, .12);
        padding: 0.2rem 0.1rem 0.2rem 0;
    }
    p{
        padding: 5px;
        margin: 0;
    }
    .fr{
        float: right
    }
    .clearfix:after{    
        content:"";
        height:0px;
        width:0px;
        clear:both;
        display:block;
        overflow: hidden;
    }
    .name{
        width: 2rem;
        text-align: right;
        margin-right: 0.3rem;
    }
    .content{
        color: #606266
    }
    .Agent{
        display: flex;
        justify-content:flex-start;
    }
    li{
        list-style: none;
    }
</style>