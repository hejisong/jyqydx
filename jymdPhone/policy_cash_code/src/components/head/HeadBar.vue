<template>
  <div class="head" :style="outStyle">
    <!-- 本地 getinfo -->
    <!-- <div class="title" v-show="!isLogin">南京建邺“秒触发”政策服务系统</div>
    <van-dropdown-menu v-show="isLogin" active-color="#1989fa" class="title" :style="openStyle == true?'width:5.1rem':'width:8.9rem'">
      <van-dropdown-item v-model="value" @open='openStyles("1")' @close='openStyles("0")' @change='changeClick' :options="option1" ></van-dropdown-item>
      <van-icon class="arrow-down" :class="!openStyle?'colors':''" :name="openStyle?'arrow-down':'arrow-up'"/>
    </van-dropdown-menu>
    <div class="login" v-show="openStyle">{{loginStatus}}</div> -->
    <!-- 生产 handleLogin -->
    <div class="title" v-show="!isLogin">南京建邺“秒触发”政策服务系统</div>
    <div class="title" v-show="isLogin && !hasData">南京建邺“秒触发”政策服务系统</div>
    <van-dropdown-menu v-show="isLogin && hasData" active-color="#1989fa" class="title" :style="openStyle == true?'width:5.1rem':'width:5.1rem'">
      <van-dropdown-item v-model="value" @open='openStyles("1")' @close='openStyles("0")' @change='changeClick' :options="option1" ></van-dropdown-item>
      <van-icon class="arrow-down" :class="!openStyle?'colors':''" :name="openStyle?'arrow-down':'arrow-up'"/>
    </van-dropdown-menu>
    <div class="login" >{{loginStatus}}</div>
    <!-- 弹窗组件 -->
    <!-- <Popup :isShow="isShow" @handleShow="handleShow" :infoObj="infoObj" /> -->
  </div>
</template>
<script>
import { baseUrl } from "../../network/baseUrl";
import Popup from "../../components/popup/Popup";
import { Toast } from "vant";

export default {
  name: "HeadBar",
  props: ["show"],
  components: { Popup },
  data() {
    return {
      companyName: 0,
      loginStatus: "",
      isLogin: false,
      isShow: false,
      openStyle:true,
      hasData:false,
      loginShow:false,
      value:0,
      option1: [],
      infoObj: {}
    };
  },

  computed: {
    outStyle() {
      return this.show ? { borderBottom: "1px solid #EEEEEE" } : {};
    }
  },

  mounted() {
    const { isLogin } = sessionStorage;
    if(!isLogin){
      this.isLogin = false
    }else{
      this.isLogin = true
    }
    if(!JSON.parse(sessionStorage.getItem('allData'))){
      this.hasData = false
    }else{
      this.hasData = true
    }
    
    console.log('-------->',this.isLogin)  
    if (isLogin && this.hasData) {    
      this.$nextTick(()=>{
        this.option1 = JSON.parse(sessionStorage.getItem('option1'))
        this.value = sessionStorage.getItem('comid')       
        let allList = JSON.parse(sessionStorage.getItem('allData'))
        for(let i = 0;i<allList.length;i++){
          if(allList[i].companyid == this.value){
            sessionStorage.setItem("companyInfo",JSON.stringify(allList[i]));
          }
        }
        let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        console.log('-------------',userInfo)
        this.loginStatus = userInfo.userName;
      })
    } else if(isLogin && !this.hasData){
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      this.loginStatus = userInfo.userName;
    }else {
      this.loginStatus = "";
      this.option1 = []
    }
  },
  methods: {    
    changeClick(value){
      sessionStorage.setItem('comid',value)
      let allList = JSON.parse(sessionStorage.getItem('allData'))
      for(let i = 0;i<allList.length;i++){
        if(allList[i].companyid == value){
          sessionStorage.setItem("companyInfo",JSON.stringify(allList[i]));
        }
      } 
    },
    openStyles(value){
      if(value == '0'){
        this.openStyle = true
      }else{
        this.openStyle = false
      }
      
    },
    getinfo() {
      const { isLogin } = sessionStorage;
      
      if (!isLogin) {
        Toast.loading({
          message: "正在获取用户信息",
          forbidClick: true,
          duration:'0'
        });
        console.log("第一次登录");
        this.$axios
          .get(
            baseUrl + 
            // '/interface/wdnj/decrypt?data=jHW3IEJFQQmFUxtGAD3KsqS7TyxuTwYbGzcMW%252FuSBqcP7urkUVYMqu3nha7eeCmQ0uTxmSE9TrQe%252B%252B%252FOV7ePGqgPGAW3MvkLICB6fuIkNkL7bH2FhZdwIwFm6rrk5Hs0W08AvYBUSCo3lUtcPtWo7eR8z9uCt3a9mmLRYFl%252Fo3gWq7xuZeKWs4%252FbgpRvI29ESJ0M6IEMcnfVZCPIq9c9CtnvQZ2Whvf1nrjMhuuCptJwyBbRGzFRCTvKnDWN%252BRHwkrsHst%252BGZ091eQ%252BJnNQA%252Frl3DPBFlt72m%252FMrZBsX%252Flt1yigJ4C3eEYeFulw%252FbrGPAgX5lvSHInS22A5FTgTzig%253D%253D'
              "/interface/wdnj/decrypt?data=neUgEt2vrHtsFW1ShGqcVW5uEX3ErNPOz3Z6EydgY8JJQ7wIBjseRqVo%252Fqvu80IawFx91WzP%252Fqb5HgOqD9EamtkpZ44MX1w6wL1ExzgDWdJYy3aFbeH57FKiXJsf%252FtoK90Cy9TGQ5vHqWvG55PuwaDBbtH7LEdrQEvhzdHrw3JMMPmIhlq4vs3ZokQ9oLWLCa8j%252F5xCUj8zG8TM5f1iXu5UzbRSHu9h3Bob5ytY4dFC7eEqxfSAQosdJSRkLy6qeACIcj2vdpjfB5C4%252BszM4ElC1leYiCYWRw5xi7TcBW5GZ7YTb%252FYukkeZr4805DMLTsfHVXONqgrsODrHdk%252BHfEg%253D%253D"
          )
          .then(res => {
            console.log("返回参数", res.data);
            let { result, resultObj } = res.data;
            if (result) {
              this.isLogin = true
              sessionStorage.isLogin = true;
              sessionStorage.isBind = resultObj.isReletedCompanyFlag;
              this.loginStatus = resultObj.userInfo.userName;
              let userInfo = JSON.stringify(resultObj.userInfo);
              sessionStorage.setItem("userInfo", userInfo);
              Toast("获取成功!");
              // 如果未绑定企业
              // if (!resultObj.isReletedCompanyFlag) {
              //   this.isShow = true;
              //   // this.infoObj = resultObj.userInfo;
              //   this.$store.commit("userInfo", resultObj.userInfo);
              // } else {
                if(resultObj.companyList && resultObj.companyList.length>0){
                  this.hasData = true
                  for(let i = 0;i<resultObj.companyList.length;i++){
                    let num = {
                      text:resultObj.companyList[i].enterprisename,
                      value:resultObj.companyList[i].companyid
                    }
                    this.option1.push(num)
                  }
                  this.$nextTick(()=>{
                    this.value = this.option1[0].value
                    this.loginShow = true
                    sessionStorage.setItem('allData',JSON.stringify(resultObj.companyList))
                    sessionStorage.setItem('comid',this.value)
                    let companyInfo = JSON.stringify(resultObj.companyList[0]);
                    sessionStorage.setItem("companyInfo", companyInfo);//登录默认显示第一个企业
                    sessionStorage.setItem('option1',JSON.stringify(this.option1))
                  })
                }else{
                  
                  this.hasData = false
                }
                
                
              // }
            }else{
              Toast("登录失败!");
            }
          }).catch(()=>{
            Toast.clear();
          });
      }
    },
    handleLogin() {
      const { isLogin } = sessionStorage;
      if(isLogin){       
        return
      }
      var appId = "cs_mcf";
      console.log("sessionStorage.isLogin", sessionStorage.isLogin);
      //发起请求（获取accessToken）
      var params = '{"appId":"' + appId + '"}';
      window.auth.getAccessToken(params);
      this.getAccessTokenCallback(params);
    },

    getAccessTokenCallback(params) {
      //回调函数（APP调用，返回accessToken）
      window.getAccessTokenCallback = params => {
        var params = JSON.parse(params); //jsonStr 转jsonObject
        if (params.success) {
          var accessToken = params.data.accessToken;
          console.log("accessToken", accessToken);
          var appId = "cs_mcf";
          //调起获取用户授权信息接口
          var userInfoParams =
            '{"accessToken":"' + accessToken + '","appId":"' + appId + '"}';
          //调起获取用户授权信息，接口（首次展示用户授权界面）
          this.getUserInfoByToken(userInfoParams);
        } else {
          console.log("获取accessToken失败，原因：" + params.msg);
        }
      };
    },

    //调起获取用户授权信息接口（首次展示用户授权界面）
    getUserInfoByToken(params) {
      window.auth.getUserInfoByToken(params);
      this.getUserInfoByTokenCallback(params);
    },

    getUserInfoByTokenCallback(params) {
      //回调函数（APP调用，返回用户授权信息）
      window.getUserInfoByTokenCallback = params => {
        var params = JSON.parse(params); //jsonStr 转jsonObject
        console.log(params);
        if (params.success) {
          var paramsdata = params.data;
          console.log("用户信息", paramsdata);
          let ciphertext = encodeURIComponent(paramsdata);

          this.getUserInfo(ciphertext);
          console.log("用户信息密文", ciphertext);
        } else {
          console.log("获取用户授权信息失败，原因：" + params.msg);
        }
      };
    },

    async getUserInfo(ciphertext) {
      Toast.loading({
        message: "正在获取用户信息",
        forbidClick: true,
        duration:'0'
      });
      console.log("第一次登录");
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/decrypt",
        {
          params: {
            data: ciphertext
          }
        }
      );

      console.log("返回参数", resultData.data);
      let { result, resultObj } = resultData.data;
      console.log(result, resultObj);

      if (result) {
        this.$nextTick(() => {
          sessionStorage.isLogin = true;
          sessionStorage.isBind = resultObj.isReletedCompanyFlag;
          this.loginStatus = resultObj.userInfo["userName"];
          console.log("赋值之后", this.loginStatus);
          let userInfo = JSON.stringify(resultObj.userInfo);
          sessionStorage.setItem("userInfo", userInfo);
          Toast("获取成功!");
        });

        // // 如果未绑定企业
        // if (!resultObj.isReletedCompanyFlag) {
        //   this.isShow = true;
        //   // this.infoObj = resultObj.userInfo;
        //   this.$store.commit("userInfo", resultObj.userInfo);
        // } else {
          
        if(resultObj.companyList && resultObj.companyList.length>0){
          this.hasData = true
          for(let i = 0;i<resultObj.companyList.length;i++){
            let num = {
              text:resultObj.companyList[i].enterprisename,
              value:resultObj.companyList[i].companyid
            }
            this.option1.push(num)
          }
          this.$nextTick(() => {
            this.value = this.option1[0].value
            this.loginShow = true
            sessionStorage.setItem('allData',JSON.stringify(resultObj.companyList))
            sessionStorage.setItem('comid',this.value)
            let companyInfo = JSON.stringify(resultObj.companyList[0]);
            sessionStorage.setItem("companyInfo", companyInfo);//登录默认显示第一个企业
            sessionStorage.setItem('option1',JSON.stringify(this.option1))
            this.isLogin = true
          })
        }else{
          this.isLogin = true
          this.hasData = false
        }
      }
      Toast.clear()
    },

    handleShow(data) {
      this.companyName = data;
      this.isShow = false;
      sessionStorage.isBind = true;
      sessionStorage.removeItem("searchInfo");
      sessionStorage.setItem("searchInfo", JSON.stringify([]));
      Toast("绑定成功!");
    }
  }
};
</script>

<style scoped>
.head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 16px;
  height: 0.96rem;
  font-weight: bold;
  background-color: #fff;
}

.title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  position: relative;
  font-size: 0.32rem;
  color: #000;
}
.head>>>.van-dropdown-menu__item{
  justify-content:initial !important;
  position: relative;  
}
.head>>>.van-dropdown-menu__title::after{
    /* top: 14% !important;
    right: 2px !important;
    border: 5px solid !important; */
    display: none
}
.colors{
  color:#1989fa;
}
.arrow-down{
  font-size: 14px;
  position: absolute;
  top: 0.28rem;
  right:0;
  z-index: 1000
}
.head>>>.van-cell__title{
  word-wrap: break-word !important;
  word-break: normal !important;
}
.head>>>.van-dropdown-menu__title{
  display: inline-block;
  width: 100%;
}
.head .login {
  font-size: 0.28rem;
  text-align: center;
  color: #3d91ff;
}
</style>
