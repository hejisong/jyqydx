<template>
  <!-- 当用户未绑定时弹出弹窗 -->
  <van-overlay :show="isShow" @click="show = false">
    <div class="wrapper" @click.stop>
      <div class="block">
        <van-search v-model="searchValue" show-action placeholder="请输入企业名称" @search="onSearch">
          <template #action>
            <div @click="onSearch">搜索</div>
          </template>
        </van-search>

        <van-cell-group>
          <van-field v-model.trim="phone" type="tel" label="手机号码" placeholder="请输入手机号码" />
          <van-field
            v-model.trim="code"
            type="number"
            center
            label="短信验证码"
            placeholder="请输入短信验证码"
            maxlength="8"
          >
            <template #button>
              <van-button size="small" type="info" @click="getCode">
                {{
                isRun ? `${runTime}s后再发送` : `获取验证码`
                }}
              </van-button>
            </template>
          </van-field>
        </van-cell-group>
        <div class="bound" @click="handleBound">立即绑定</div>
      </div>
    </div>
  </van-overlay>
</template>

<script>
import {
  Field,
  Overlay,
  Search,
  Toast,
  ToastSearch,
  Collapse,
  CollapseItem
} from "vant";
import { baseUrl } from "../../network/baseUrl";

export default {
  name: "Popup",
  props: ["isShow", "infoObj"],

  data() {
    return {
      phone: "",
      code: "",
      searchValue: "",
      companyid: "",
      show: false,
      isRun: false,
      runTime: 60
    };
  },
  created() {
    const { companyid, phoneno, enterprisename } = JSON.parse(
      sessionStorage.getItem("searchInfo")
    );
    // const { companyid, phoneno, enterprisename } = this.$store.state.searchInfo;
    this.phone = phoneno ? phoneno : "";
    this.companyid = companyid;
    this.searchValue = enterprisename;
    console.log(companyid, phoneno, enterprisename);
  },
  mounted() {},

  computed: {
    // phone() {
    //   let userPhone = this.infoObj.linktel;
    //   return userPhone
    //     ? userPhone.replace(userPhone.substring(3, 7), "****")
    //     : "";
    // }
  },

  methods: {
    // 获取验证码
    async getCode() {
      if (this.isRun) return;
      if (!this.phone) {
        Toast("手机号不能为空！");
        return;
      }
      if (!/^1\d{10}$/.test(this.phone)) {
        Toast("请保证手机号码正确！");
        return;
      }

      // 验证码倒计时开始
      this.isRun = true;
      this.timer = setInterval(() => {
        if (this.runTime === 0) {
          this.runTime = 60;
          this.isRun = false;
          clearInterval(this.timer);
          return;
        }
        this.runTime--;
      }, 1000);
      let result = await this.$axios.get(
        baseUrl + "/interface/wdnj/sendPhoneMessage",
        {
          params: {
            phone: this.phone
          }
        }
      );

      console.log("获取验证码返回值", result);
    },

    // 立即绑定
    async handleBound() {
      // const { userName, idNumber, phone } = this.infoObj;
      // console.log("弹出", this.infoObj);
      const { userName, idNumber, phone } = this.$store.state.userInfo;
      console.log(userName, idNumber, phone);
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/checkPhoneMessage",
        {
          params: {
            phone: this.phone,
            mess: this.code,
            username: userName,
            linknum: idNumber,
            linktel: phone,
            companyid: this.companyid
          }
        }
      );

      const { result, errorMsg, resultObj } = resultData.data;

      console.log("立即绑定返回值", resultData);
      if (result) {
        sessionStorage.isBind = true;
        this.$emit("handleShow", resultObj.companyList[0].enterprisename);
        let companyInfo = JSON.stringify(resultObj.companyList[0]);

        sessionStorage.setItem("companyInfo", companyInfo);

        // console.log("companyid", resultObj.companyList[0].companyid);
      } else {
        Toast(errorMsg);
      }
    },

    // 搜索
    onSearch() {
      if (this.searchValue) {
        this.$router.push({
          path: "/choosecompany",
          query: {
            searchValue: this.searchValue
          }
        });
      }
    }
  }
};
</script>

<style>
.van-overlay {
  z-index: 10;
}

.van-field__label {
  margin-right: 0px;
}

.van-field__button {
  padding-left: 0;
}

.van-field::after {
  border-bottom: 1px solid #ebedf0;
}

.block .van-cell {
  padding: 8px !important;
}

.van-search__content {
  padding-left: 0;
}

.van-collapse-item__content {
  padding: 0 16px;
}
</style>

<style scoped>
/* 未绑定弹出浮层 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  padding: 0.2rem;
  width: 6.4rem;
  background-color: #fff;
  border-radius: 4px;
}

.bound {
  margin-top: 0.4rem;
  width: 100%;
  height: 0.8rem;
  line-height: 0.8rem;
  background-color: #4288ff;
  font-size: 0.28rem;
  color: #fff;
  text-align: center;
  border-radius: 6px;
}
</style>