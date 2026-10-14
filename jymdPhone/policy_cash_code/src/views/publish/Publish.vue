<template>
  <div class="qustionFeedback">
    <Head :show="true" :title="getTitle()" />
    <div class="sendMsg">
      <van-cell-group>
        <van-form :readonly="$route.query.flag!='edit'?false:true">
          <van-field
            class="lang"
            type="text"
            :label-width="labelwidth"
            label="单位名称"
            :readonly="unit == ''?false:true"
            v-model="unit"
            placeholder="请输入单位名称"
            required
            clearable
            :error-message="errormessage.unit"
          />

          <van-field
            class="lang"
            type="tel"
            :label-width="labelwidth"
            label="联系电话"
            v-model="phone"
            placeholder="请输入联系电话"
            maxlength="11"
            required
            clearable
            :error-message="errormessage.phone"
          />

          <van-field
            class="short"
            :label-width="labelwidth"
            v-model="title"
            :error-message="errormessage.title"
            rows="1"
            autosize
            :label-align='"right"'
            required
            maxlength="36"
            clearable
            label="标题"
            placeholder="请输入标题"
          />
          <!-- <van-field class="short" :label-width="labelwidth" name="radio" label="是否公开">
            <template #input>
              <van-radio-group :disabled="$route.query.flag!='edit'?false:true" v-model="radio" direction="horizontal">
                <van-radio name="1">公开</van-radio>
                <van-radio name="0">不公开</van-radio>
              </van-radio-group>
            </template>
          </van-field> -->
          <van-field
            class="short short2"
            :label-width="labelwidth"
            v-model="content"
            border
            label="内容"
            type="textarea"
            :maxlength="500"
            :show-word-limit="$route.query.flag!='edit'?true:false"
            placeholder="请输入内容"
            rows="4"
            clearable
            :label-align='"right"'
            :error-message="errormessage.content"
            required
            autosize
          />
        </van-form>
      </van-cell-group>
    </div>
    <!-- <div style="background-color: #f1f1f1;height:10px"></div> -->
    <div class="replyMsg">
      <ul class="clearfix" v-for="(item,index) in replyList" :key="index">
        <li class="fl" style="padding-top:10px;width:25%;margin-right:25px;text-align: right;color:#646566">{{ item.infotype =='2'?'回复':'追答' }}</li>
        <li class="boxShow fl">{{ item.content }}
          <p style="text-align: right;margin-bottom:0;color:#C0C4CC">{{ getDate(item.replytime) }}</p>
        </li>
        
      </ul>
    </div>

    <!--提交-->
    <div class="btn-father">
      <button v-if="$route.query.flag!='edit' && isLogin" class="sumbit-button-right" @click="sumbit">发&nbsp;&nbsp;表</button>
      <button v-if="$route.query.flag=='edit' && isBtn" class="sumbit-button-right" @click="showQuestion =true">追&nbsp;&nbsp;问</button>
    </div>
    <van-popup @closed='closedQuestion' style="width:85%" v-model="showQuestion" round :closeable='true' :close-on-click-overlay="false">
      <div class="titleMsg">追问</div>
      <div class="questionBox">
        <div class="sunmit">
          <van-field        
            v-model="questionContent"
            type="textarea"
            maxlength="500"
            required
            :error-message="errormessage.questionContent"
            show-word-limit
            placeholder="请输入内容"
            rows="4"
            autosize
          />
        </div>
        <van-button style="width:50%;margin:0 auto" round @click="submitQuestion" size="small" type="primary" block>提交</van-button>
      </div>
      
    </van-popup>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import { Toast, Dialog,Col, Row } from "vant";
import Head from "../../components/head/Head";

export default {
  components: {
    Head
  },
  data() {
    return {
      unit: "",
      phone: "",
      isLogin:false,
      // radio:'1',
      showQuestion:false,
      title: "",
      activeNames:['1'],
      content: "",
      isBtn:false,
      replyList:[],
      questionContent:'',
      labelwidth: "25%",
      errormessage: {
        unit: "",
        phone: "",
        title: "",
        content: "",
        questionContent:''
      }
    };
  },

  created() {
    let user = JSON.parse(sessionStorage.getItem("userInfo"))
    this.unit = sessionStorage.getItem("companyInfo")?JSON.parse(sessionStorage.getItem("companyInfo")).enterprisename:''
    if(user){
      this.isLogin = true
    }else{
      this.isLogin = false
    }
    this.phone = user.phone
    if (this.$route.query && this.$route.query.id) {
      const { id } = this.$route.query;
      this.suggestionDetail(id);
      this.getReplyList(id)
    }
  },

  watch: {
    unit(val, oldVal) {
      val
        ? (this.errormessage.unit = "")
        : (this.errormessage.unit = "请输入单位名称");
    },
    phone(val, oldVal) {
      val
        ? (this.errormessage.phone = "")
        : (this.errormessage.phone = "请输入联系电话");
    },
    title(val, oldVal) {
      val
        ? (this.errormessage.title = "")
        : (this.errormessage.title = "请输入标题");
    },
    content(val, oldVal) {
      val
        ? (this.errormessage.content = "")
        : (this.errormessage.content = "请输入内容");
    },
    questionContent(val){
      val
        ? (this.errormessage.questionContent = "")
        : (this.errormessage.questionContent = "请输入追问内容");
    },
  },
  mounted(){ 
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
    // display
  },
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
  },
  methods: {
    getTitle(){
      if(this.$route.query.id && this.$route.query.id != '' && this.$route.query.id != null){
        if(this.$route.query.type && this.$route.query.type !=''){
          return '投诉'
        }else{
          return '意见建议'
        }
      }else{
        return '我要发表'
      }
    },
    closedQuestion(){
      this.questionContent = ''
      this.showQuestion = false
    },
    async submitQuestion(){
      if (!this.questionContent) {
        this.errormessage.questionContent = "请输入追问内容";
        return;
      }
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let data = {
        suggestid:this.$route.query.id,
        content:this.questionContent,
        infotype:this.$route.query.type !=''?'1':'2'
      }
      console.log('data-------->',data)
      await this.$axios.get(baseUrl + '/interface/suggestions/addReply',{params:data}).then(res=>{
        console.log('回复列表',res)
        if(res.status == '200'){
          this.closedQuestion()
          this.suggestionDetail(this.$route.query.id);
          this.getReplyList(this.$route.query.id)
          Toast.clear();
        }
      });
    },
    //回复列表
    async getReplyList(id){
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      await this.$axios.get(baseUrl + `/interface/suggestions/detail?id=${id}`).then(res=>{
        console.log('回复列表',res)
        if(res.status == '200'){
          this.replyList = res.data.resultObj
          Toast.clear();
        }
        

      });
      
    },
    // 详情
    async suggestionDetail(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/suggestions/queryone",
        {
          params: {
            id
          }
        }
      );

      console.log("详情", resultData);
      const {
        result,
        resultObj: { companyname, mobile, title, content,companyid }
      } = resultData.data;

      if (result) {
        Toast.clear();
        let id = sessionStorage.getItem("companyInfo")?JSON.parse(sessionStorage.getItem("companyInfo")).companyid:''
        if(id != companyid){
          console.log('不是同一家公司')
          this.isBtn = false
        }else{
          console.log('是同一家公司')
          this.isBtn = true
        }
        this.unit = companyname;
        this.phone = mobile;
        this.title = title;
        this.content = content;
      }
    },
    // 发表
    sumbit() {
      if (!this.unit) {
        this.errormessage.unit = "请输入单位名称";
        return;
      }

      if (!this.phone) {
        this.errormessage.phone = "请输入联系电话";
        return;
      }

      if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(this.phone)) {
        Toast("手机号码有误，请重填");
        return;
      }

      if (!this.title) {
        this.errormessage.title = "请输入标题";
        return;
      }

      if (!this.content) {
        this.errormessage.content = "请输入内容";
        return;
      }

      Dialog.confirm({
        title: "提示",
        message: "确认提交吗？",
        confirmButtonColor: "#3196fa"
      })
        .then(() => {
          this.submitRequest();
        })
        .catch(() => {
          console.log("取消了");
        });
    },
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
    async submitRequest() {
      let data2 = JSON.parse(sessionStorage.getItem("companyInfo"))
      let params = {
            create: JSON.parse(sessionStorage.getItem("userInfo")).phone,
            companyname: this.unit,
            mobile: this.phone,
            title: this.title,
            content: this.content,
            companyid:data2?data2.companyid:'',
            // isshow:this.radio,
            infotype:this.$route.query.type !=''?'2':'1'
          }
      console.log(this.$route.query.type)
      let resultData = await this.$axios.get(
        baseUrl + "/interface/suggestions/save",
        {params:params}
      );

      console.log("result", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.success("提交成功");
        this.$router.go(-1);
      }
    }
  }
};
</script>

<style scoped>
.questionBox{
  padding: 0.4rem 0.3rem;
}
.titleMsg{
  font-size: 18px;
  text-align: center;
  font-weight: 550;
  line-height: 1.1rem
}
.van-cell--required::before {
  left: 5px;
}

.qustionFeedback {
  height: 98%;
  background-color: #ffffff;
  overflow-y: auto;
  padding-bottom: 1.45rem;
}
.sunmit{
  border-radius: 5px;
  border:1px solid #EBEEF5;
  /* max-height: 2.5rem; */
  /* overflow-y: auto; */
  margin-bottom: 0.5rem;
}
/* .sendMsg{
  border:1px solid red;
} */

.img-box {
  color: #323233;
  padding: 10px 15px;
  position: relative;
  border-bottom: 0.5px solid #e5e5e5;
}

.upload-img {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.upload-icon {
  width: 1.4rem;
  height: 1.4rem;
}

.replyMsg{
  padding: 6px;
  font-size: 14px;
  margin-top: 10px;
  background-color: #ffffff
}
.replyMsg ul{
  margin-bottom: 10px;
}
.boxShow{
  box-shadow:0 2px 10px -3px rgba(0, 0, 0, .12);
  padding: 10px;
  border-radius: 5px;
  width: 60%;
}
.fl{
  float: left;
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
.photo-value {
  object-fit: cover;
  position: relative;
  width: 1.4rem;
  height: 1.4rem;
  margin-right: 0.3rem;
  margin-top: 0.26rem;
  background: pink;
}

.iconfont.icon-delete {
  font-size: 0.38rem;
  position: absolute;
  z-index: 2;
  left: 1.2rem;
  padding: 0.03rem;
  color: #ef353f;
  background-color: #fff;
  border-radius: 100%;
}
.btn-father {
  display: flex;
  background: #ffffff;
  height: 1.4rem;
  position: fixed;
  bottom: 0;
  width: 100%;
  z-index: 100;
}
.lang::before{
  left: 33px;
}
.sumbit-button-right {
  text-align: center;
  width: 96%;
  color: #ffffff;
  height: 0.98rem;
  font-size: 0.36rem;
  line-height: 0.9rem;
  margin: 0 2%;
  margin-top: 0.2rem;
  background: #3196fa;
  border-radius: 3px;
  border: none;
}
.lang>>>.van-field__label{
  text-align: right;
  margin-right: 25px;
}
.short::before{
  left: 59px;
}
.short>>>.van-field__label{
  text-align: right;
  margin-right: 25px;
}
.short2>>>.van-cell__value{
  border: 1px solid #ebedf0;
  padding: 0 0.1rem
}
</style>
