<template>
  <div class="head" :style="outStyle">
    <div class="title" @click="getinfo">{{companyName}}</div>
    <!-- <div class="title">{{companyName}}</div> -->

    <div class="login" @click="handleLogin">{{loginStatus}}</div>
    <!-- 弹窗组件 -->
    <Popup :isShow="isShow" @handleShow="handleShow" :infoObj="infoObj" />
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
      companyName: "南京建邺“秒触发”政策服务系统",
      loginStatus: "登录",
      isLogin: false,
      isShow: false,
      infoObj: {}
    };
  },

  computed: {
    outStyle() {
      return this.show ? { borderBottom: "1px solid #EEEEEE" } : {};
    }
  },

  created() {
    const { isLogin } = sessionStorage;
    // const { companyid } = this.$store.state.searchInfo;

    const { companyid } = JSON.parse(sessionStorage.getItem("searchInfo"));

    if (isLogin) {
      console.log("companyid", companyid);
      this.isShow = !!companyid;
      //JSON字符串转JSON对象
      let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      let companyInfo = JSON.parse(sessionStorage.getItem("companyInfo"));
      // console.log("userinfo", userInfo);
      // console.log("companyInfo", companyInfo);
      this.loginStatus = userInfo.userName;
      this.companyName = !this.isShow
        ? companyInfo.enterprisename
        : this.companyName;
    } else {
      this.loginStatus = "登录";
    }
  },
  methods: {
    getinfo() {
      const { isLogin } = sessionStorage;

      if (!isLogin) {
        console.log("第一次登录");
        this.$axios
          .get(
            baseUrl +
              "/interface/wdnj/decrypt?data=neUgEt2vrHtsFW1ShGqcVW5uEX3ErNPOz3Z6EydgY8JJQ7wIBjseRqVo%252Fqvu80IawFx91WzP%252Fqb5HgOqD9EamtkpZ44MX1w6wL1ExzgDWdJYy3aFbeH57FKiXJsf%252FtoK90Cy9TGQ5vHqWvG55PuwaDBbtH7LEdrQEvhzdHrw3JMMPmIhlq4vs3ZokQ9oLWLCa8j%252F5xCUj8zG8TM5f1iXu5UzbRSHu9h3Bob5ytY4dFC7eEqxfSAQosdJSRkLy6qeACIcj2vdpjfB5C4%252BszM4ElC1leYiCYWRw5xi7TcBW5GZ7YTb%252FYukkeZr4805DMLTsfHVXONqgrsODrHdk%252BHfEg%253D%253D"
          )
          .then(res => {
            console.log("返回参数", res.data);
            let { result, resultObj } = res.data;
            if (result) {
              sessionStorage.isLogin = true;
              sessionStorage.isBind = resultObj.isReletedCompanyFlag;
              this.loginStatus = resultObj.userInfo.userName;
              let userInfo = JSON.stringify(resultObj.userInfo);
              sessionStorage.setItem("userInfo", userInfo);
              Toast("登录成功!");
              // 如果未绑定企业
              if (!resultObj.isReletedCompanyFlag) {
                this.isShow = true;
                // this.infoObj = resultObj.userInfo;
                this.$store.commit("userInfo", resultObj.userInfo);
              } else {
                this.companyName = resultObj.companyList[0].enterprisename;
                let companyInfo = JSON.stringify(resultObj.companyList[0]);
                sessionStorage.setItem("searchInfo", JSON.stringify([]));
                sessionStorage.setItem("companyInfo", companyInfo);
              }
            }
          });
      }
    },
    handleLogin() {
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
          Toast("登录成功!");
        });

        // 如果未绑定企业
        if (!resultObj.isReletedCompanyFlag) {
          this.isShow = true;
          // this.infoObj = resultObj.userInfo;
          this.$store.commit("userInfo", resultObj.userInfo);
        } else {
          this.companyName = resultObj.companyList[0].enterprisename;
          let companyInfo = JSON.stringify(resultObj.companyList[0]);
          sessionStorage.setItem("searchInfo", JSON.stringify([]));
          sessionStorage.setItem("companyInfo", companyInfo);
        }
      }
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

.head .title {
  width: 5.1rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 0.32rem;
  color: #000;
}

.head .login {
  font-size: 0.28rem;
  text-align: center;
  color: #3d91ff;
}
</style>
