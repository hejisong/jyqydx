<template>
  <div class="qustionFeedback">
    <Head :show="true" title="我要发表" />

    <van-cell-group>
      <van-field
        type="text"
        :label-width="labelwidth"
        label="单位名称"
        v-model="unit"
        placeholder="请输入单位名称"
        required
        clearable
        :error-message="errormessage.unit"
      />

      <van-field
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
        :label-width="labelwidth"
        v-model="title"
        :error-message="errormessage.title"
        rows="1"
        autosize
        required
        maxlength="36"
        clearable
        label="标题"
        type="textarea"
        placeholder="请输入标题"
      />

      <van-field
        :label-width="labelwidth"
        v-model="content"
        label="内容"
        type="textarea"
        maxlength="500"
        show-word-limit
        placeholder="请输入内容"
        rows="4"
        clearable
        :error-message="errormessage.content"
        required
        autosize
      />
    </van-cell-group>

    <!--提交-->
    <div class="btn-father">
      <button class="sumbit-button-right" @click="sumbit">发&nbsp;&nbsp;表</button>
    </div>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import { Toast, Dialog } from "vant";
import Head from "../../components/head/Head";

export default {
  components: {
    Head
  },
  data() {
    return {
      unit: "",
      phone: "",
      title: "",
      content: "",
      labelwidth: "125",
      errormessage: {
        unit: "",
        phone: "",
        title: "",
        content: ""
      }
    };
  },

  created() {
    if (this.$route.query && this.$route.query.id) {
      const { id } = this.$route.query;
      this.suggestionDetail(id);
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
    }
  },

  methods: {
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
        resultObj: { companyname, mobile, title, content }
      } = resultData.data;

      if (result) {
        Toast.clear();
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

    async submitRequest() {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/suggestions/save",
        {
          params: {
            companyname: this.unit,
            mobile: this.phone,
            title: this.title,
            content: this.content
          }
        }
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
.van-cell--required::before {
  left: 5px;
}

.qustionFeedback {
  height: 100%;
  background-color: #f1f1f1;
}

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
</style>
