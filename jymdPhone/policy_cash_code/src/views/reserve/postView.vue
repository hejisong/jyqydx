<template>
    <div class="reservelist">
        <!-- <van-popup @closed='closedQuestion' style="width:85%" v-model="showQuestion" round :closeable='true' :close-on-click-overlay="false"> -->
        <!-- <div class="titleMsg">我要预约</div> -->
        <Head :show="true" title="我要预约" />
        <van-field
            v-model="sms"
            center
            :readonly="true"
            :error-message="this.errormessage.sms"
            clearable
            class="MSG"
            required
            label="代办人"
            placeholder="请添加代办人"
            >
            <template #button>
                <van-button size="small" @click="show = true" icon="plus" type="info" />
            </template>
        </van-field>
        <van-field v-model="tel" type="digit" :error-message="this.errormessage.tel" label="预约人手机号" placeholder="请输入联系方式" />
        <van-field              
            v-model="legalNum"
            name="法人手机号"
            label="法人手机号"
            :readonly="legalNum == ''?false:true"
            placeholder="法人手机号"
            :rules="[{ required: true, message: '请填写法人手机号' }]"
        />
        
        <van-field
            v-model="legalCode"
            name="验证码"
            label="验证码"
            required
            placeholder="验证码"
        >
            <template #button>
            <van-button size="small" type="primary" :disabled='isRun' @click="getCode" :loading='loading' loading-text="获取中...">{{ isRun ?times+'s后再发送':'发送验证码' }}</van-button>
            </template>
        </van-field>
        <div class="questionBox">
            <!-- <div class="sunmit"> -->
            <van-field        
                v-model="questionContent"
                type="textarea"
                maxlength="500"
                label="备注"
                :error-message="this.errormessage.questionContent"
                show-word-limit
                placeholder="请输入内容"
                rows="4"
                autosize
            />
            
        </div>
        <van-button class="btn-father" @click="onSubmit" type="info" block >提交</van-button>
        <van-popup style="width:85%;padding: 0 0.3rem 0.3rem 0.3rem ;"  v-model="show" round>
            <h5 class="userTitle van-hairline--bottom">选择待办人员</h5>
            <van-radio-group v-model="radio" class="userBox">
                <van-cell-group>
                    <van-cell clickable @click="radioClick(item)" v-for="(item,index) in userList" :key='index'>
                        <template #right-icon>
                            <van-radio :name="index" />
                        </template>
                        <template #title>
                            <span class="custom-title">{{ item.username }}</span>
                            <span style="color:#ff976a;margin-left:0.2rem">[{{ item.linkUnit }}]</span>
                        </template>
                    </van-cell>
                </van-cell-group>
            </van-radio-group>
        </van-popup>
    </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import { Search, Toast, Empty } from "vant";
import Head from "../../components/head/Head";

export default {
  name: "postView",
  components:{
      Head
  },
  data() {
    return {
      questionContent:'',
      radio:'1',
      legalNum:'',
      times:60,
      times2:0,
      loading:false,
      isRun:false,
      legalCode:'',
      show:false,
      userDate:{},
      sms:'',
      tel:'',
      userList:[],
      errormessage:{
          tel:'',
          sms:'',
          questionContent:''
      },
    };
  },

    created() {
        this.tel = JSON.parse(sessionStorage.getItem("userInfo")).phone
        console.log(sessionStorage.getItem("userInfo"))
        console.log(sessionStorage.getItem("companyInfo"))
        
        this.getAdmin()
    },
   
    destroyed(){
        let box = document.getElementById('tab-bar')
        box.style.visibility="visible";
    },
    watch:{
        radio:function(val){
            console.log(val)
            this.show = false
        },
        sms(val, oldVal) {
        val
            ? (this.errormessage.sms = "")
            : (this.errormessage.sms = "请选择代办人");
        },
        tel(val, oldVal) {
        val
            ? (this.errormessage.tel = "")
            : (this.errormessage.tel = "请输入联系电话");
        },
        questionContent(val, oldVal) {
        val
            ? (this.errormessage.questionContent = "")
            : (this.errormessage.questionContent = "请输入备注内容");
        },
        
    },
  mounted() {
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
    this.getCompanyData()
  },

  methods: {
      // 获取法人手机号
    async getCompanyData() {
      let data = JSON.parse(sessionStorage.getItem("companyInfo"))
      console.log(data.companyid)
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/search",
        {
          params: {
            searchname: data.enterprisename
          }
        }
      );
      const { result, resultObj } = resultData.data;
      console.log(resultData.data);
      if (result) {
        for(let i = 0;i<resultObj.length;i++){
          if(data.companyid == resultObj[i].companyid){
            this.legalNum = resultObj[i].phoneno;
          }
        }
      }
    },
    // 获取验证码
    async getCode(){
      if(this.legalNum != ''){        
        this.loading = true
        let resultData = await this.$axios.get(
          baseUrl + "/interface/wdnj/sendPhoneMessage",
          {
            params: {
              phone : this.legalNum
            }
          }
        );
        const { result, resultObj } = resultData.data;
        console.log(resultData.data);
        if (result) {
          this.loading = false
          Toast.success('发送成功');
          this.isRun = true;
          console.log('this.times======>',this.times)
          this.times2 = setInterval(() => {
            if (this.times == 0) {
              this.times = 60;
              this.isRun = false;
              clearInterval(this.times2);
              return;
            }
            this.times--;
          }, 1000);
        }else{
          Toast('验证码获取失败')
          this.loading = false
        }
      }else{
        Toast('请输入手机号！')
      }
    },
    // 验证码校验,校验成功调 handleConfirm
    async onSubmit(){
        if (!this.sms) {
            this.errormessage.sms = "请选择代办人";
            return;
        }
        if(this.tel){
            if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(this.tel)) {
                Toast("手机号码有误，请重填");
                return;
            }
        }else{
            this.errormessage.tel = "请输入手机号";
            return;
        }
        if(this.legalCode == ''){
            Toast('请输入验证码！')
            return
        }
        if (!this.questionContent) {
            this.errormessage.questionContent = "请输入备注内容";
            return;
        }
      let data = JSON.parse(sessionStorage.getItem("companyInfo"))
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/checkPhoneMessage",
        {
          params: {
            companyid:data.companyid,
            phone : this.legalNum,
            mess : this.legalCode
          }
        }
      );
      const { result, resultObj } = resultData.data;
      console.log(resultData.data);
      if (result) {
        this.legalCode = ''
        this.times = 60
        this.isRun = false
        this.reserveClick()
      }else{
        Toast('验证码校验失败')
       
      }
    },
    async reserveClick(){
        Toast.loading({
            message: "加载中...",
            forbidClick: true
        });
        let user = JSON.parse(sessionStorage.getItem("userInfo"))
        let company = JSON.parse(sessionStorage.getItem("companyInfo"))
        let data = {
            companyid: company.companyid,
            username: user.userName,
            linknum: user.idNumber,
            agencymanid: this.userDate.id,
            agencyman: this.userDate.username,
            contact: this.tel,
            remark: this.questionContent
        }
        console.log('data------->',data)
        
        await this.$axios.post(baseUrl + "/interface/company/saveOrderagency",data).then((res) => {
            console.log('submit----->',res)
            if(res.data.result){
                // this.userList = res.data.resultObj
                Toast.success('提交成功');
                setTimeout(()=>{
                    this.$router.go(-1)
                },1000)  
                
            }else{
                Toast.fail('失败文案');
            }
            Toast.clear()
        }).catch((err) => {
            console.log('请求失败')
        });
    },
    radioClick(val){
        console.log(val)
        this.sms = val.username
        this.userDate = val
    },
    // 获取用户
    async getAdmin(){
        await this.$axios.get(baseUrl + "/interface/company/adminUsers").then((res) => {
            console.log('=========>',res)
            if(res.data.result){
                this.userList = res.data.resultObj
                console.log('==================>>>>>',this.userList)
            }
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
        height: 80%;
        width: 100%;
        font-size: 14px;
        background-color: #ffffff;
        overflow-y: auto;
        box-shadow:0 2px 10px -3px rgba(0, 0, 0, .12);
        padding-bottom: 1.3rem;
    }
    .reservelist>>>.van-field__label{
        width: 90px;
        text-align: right;
        margin-right: 0.3rem;
    }
    .questionBox>>>.van-cell__value{
        border-radius: 5px;
        padding-left: 0.15rem;
        border:1px solid #EBEEF5;     
    }
    .questionBox>>>.van-cell__title span{
        padding-left: 14px
    }
    .reservelist>>>.van-cell::before{
        left: 51px;
    }
    .questionBox>>>.van-cell::before{
        left:40px
    }
    .MSG::before{
        top: 17px
    }
    .van-cell{
        height: auto;
    }
    .titleMsg{
        font-size: 18px;
        text-align: center;
        font-weight: 550;
        line-height: 1.1rem
    }
    .fl{
        float: left;
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
    .userTitle{
        line-height: 1.2rem;
        text-align: center;
        padding: 0;
        margin: 0;
        font-size: 14px;
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
    .userBox{
        width:100%;
        max-height:7rem;
        overflow-y: auto;
    }
    li{
        list-style: none;
    }
</style>