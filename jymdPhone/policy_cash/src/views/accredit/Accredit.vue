<template>
  <div class="accredit">
    <Head :show="true" :addbtn="true" title="企业授权" @adduthorization="adduthorization" />

    <van-overlay :show="show" @click="show = false">
      <div class="wrapper" @click.stop>
        <div class="block">
          <div class="head">选择授权人</div>

          <div class="content">
            <van-search
              v-model="searchValue"
              shape="round"
              background="#f8f8f8"
              placeholder="搜索授权人姓名"
              @search="onSearch"
            />
            <div class="checkbox" v-if="personList && personList.length > 0">
              <van-radio-group v-model="radioValue">
                <van-cell-group>
                  <van-cell
                    v-for="item in personList"
                    :key="item.id"
                    :title="`${item.username} (${item.linktel})`"
                    clickable
                    @click="handleChoose(item)"
                  >
                    <template #right-icon>
                      <van-radio :name="item.id" />
                    </template>
                  </van-cell>
                </van-cell-group>
              </van-radio-group>
            </div>
            <div class="not-data" v-else>暂无数据</div>
          </div>

          <div class="bottom">
            <div class="btn cancel" @click="handleCancel">取消</div>
            <div class="btn confirm" @click="handleConfirm">确定</div>
          </div>
        </div>
      </div>
    </van-overlay>

    <div class="accredit_list" v-if="accreditList && accreditList.length > 0">
      <div class="accredit_item" v-for="item in accreditList" :key="item.id">
        <img src="../../assets/imgs/shouquan.png" alt />
        <div class="accredit_info">
          <div class="accredit_info_name">{{item.authorizedperson}}</div>
          <div class="accredit_info_comfirm">授权时间：{{item.authorizedtime | formatDate}}</div>
          <div
            class="accredit_info_cancel"
            v-if="item.cancellationtime"
          >取消时间：{{item.cancellationtime | formatDate}}</div>
        </div>
        <div
          class="accredit_btn"
          v-if="item.state == '2'"
          style="background:#F4F8FF;color:#4288FF;"
        >{{getState(item.state)}}</div>

        <div
          v-else
          class="accredit_btn"
          style="background:#4288FF;color:#fff;"
          @click="handleCancelAccredit"
        >{{getState(item.state)}}</div>
      </div>
    </div>

    <van-empty description="暂无数据" v-else />
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import { baseUrl } from "../../network/baseUrl";

import { Toast, Empty } from "vant";

export default {
  name: "Accredit",
  components: {
    Head
  },
  data() {
    return {
      show: false,
      searchValue: "",
      personList: [],
      result: [],
      companyid: "",
      accreditList: [],
      radioValue: "",
      username: "",
      linktel: ""
    };
  },
  created() {
    this.companyid = JSON.parse(
      sessionStorage.getItem("companyInfo")
    ).companyid;
    this.getAccreditData(this.companyid);
  },

  filters: {
    formatDate(date) {
      var date = new Date(date);
      var YY = date.getFullYear() + "-";
      var MM =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      var DD = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      var hh =
        (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      var mm =
        (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
        ":";
      var ss =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return YY + MM + DD + " " + hh + mm + ss;
    }
  },

  watch: {
    searchValue(val, oldVal) {
      this.searchValue = val;
      this.searchData(this.searchValue);
    }
  },

  methods: {
    // 获取授权列表数据
    async getAccreditData(companyid) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/authorized",
        {
          params: {
            companyid
          }
        }
      );

      console.log("result", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.accreditList = Array.isArray(resultObj) ? resultObj : [];
      }
    },

    getState(state) {
      return state == "2" ? "已取消" : "取消授权";
    },

    // 取消授权
    async handleCancelAccredit() {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/cancel",
        {
          params: {
            companyid: this.companyid
          }
        }
      );

      console.log("取消授权返回", resultData);
      const { result } = resultData.data;
      if (result) {
        Toast("取消授权成功");
        setTimeout(() => {
          this.getAccreditData(this.companyid);
        }, 500);
      }
    },

    // 搜索
    async searchData(searchValue) {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/employees",
        {
          params: {
            companyid: this.companyid,
            searchName: searchValue
          }
        }
      );
      const { result, resultObj } = resultData.data;
      if (result) {
        this.personList = resultObj;
      }
      console.log("搜索返回值", resultData);
    },

    // 单选点击事件
    handleChoose(item) {
      this.username = item.username;
      this.linktel = item.linktel;
    },

    adduthorization(flag) {
      this.show = flag;
      console.log("true", flag);
    },

    onSearch() {
      console.log("搜索参数----", this.searchValue);
    },

    // 取消
    handleCancel() {
      this.show = false;
    },

    // 确定
    async handleConfirm() {
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/save",
        {
          params: {
            companyid: this.companyid,
            username: this.username,
            linktel: this.linktel
          }
        }
      );

      console.log("确定返回值", resultData);
      const { result, errorMsg } = resultData.data;
      if (result) {
        Toast.success(errorMsg);
        setTimeout(() => {
          this.getAccreditData(this.companyid);
        }, 500);
        this.searchValue = "";
        this.radioValue = "";
        this.show = false;
      }
    }
  }
};
</script>

<style scoped>
.van-icon-search::before {
  content: "\e626";
}

.van-search__content {
  background-color: #fff !important;
}

.accredit {
  padding-bottom: 0.98rem;
}

.accredit_list {
  padding: 12px 16px;
}

.accredit_item {
  display: flex;
  align-items: center;
  margin-bottom: 0.24rem;
  padding: 0.28rem 0.32rem;
  /* height: 2.4rem; */
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.accredit_item > img {
  width: 0.4rem;
  height: 0.4rem;
}

.accredit_info {
  margin-left: 0.32rem;
  width: 4.2rem;
}

.accredit_info .accredit_info_name {
  font-size: 0.28rem;
  color: #4288ff;
}

.accredit_info .accredit_info_comfirm {
  margin: 0.08rem 0;
}

.accredit_info .accredit_info_comfirm,
.accredit_info .accredit_info_cancel {
  font-size: 0.24rem;
  color: #666;
}

.accredit_btn {
  width: 1.2rem;
  height: 0.6rem;
  line-height: 0.6rem;
  font-size: 0.24rem;
  color: #fff;
  text-align: center;
  background-color: #4288ff;
  border-radius: 2px;
}

/* 遮罩层样式 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  position: relative;
  width: 6.4rem;
  height: 7.5rem;
  background-color: #fff;
  border-radius: 6px;
}

.block .head {
  padding: 0 16px;
  height: 1rem;
  line-height: 1rem;
  font-size: 0.32rem;
  color: #3f94ff;
  font-weight: bold;
}

.block .content {
  width: 100%;
  /* height: calc(8.6rem - 2rem); */
  height: 5rem;
  background-color: #f8f8f8;
  border-top: 1px solid #ebedf0;
}

.checkbox {
  width: 100%;
  min-height: 0.8rem;
  max-height: 4rem;
  overflow-x: hidden;
  overflow-y: scroll;
}

.block .bottom {
  position: absolute;
  left: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  width: 100%;
  padding-left: 3.1rem;
  height: 1rem;
  line-height: 1rem;
  font-size: 0.32rem;
  color: #3f94ff;
  font-weight: bold;
}

.bottom .btn {
  margin-right: 0.16rem;
  width: 1.4rem;
  height: 0.6rem;
  line-height: 0.6rem;
  font-weight: normal;
  font-size: 0.28rem;
  text-align: center;
  border-radius: 3px;
}

.cancel {
  color: #999;
  background-color: #e9e9e9;
}

.confirm {
  color: #fff;
  background-color: #4288ff;
}

.not-data {
  font-size: 0.32rem;
  text-align: center;
  margin: 1rem 0;
  color: #ccc;
}
</style>