<template>
  <div class="accredit">
    <Head :show="true" :addbtn="true" title="企业授权" @adduthorization="adduthorization" />

    <van-overlay :show="show" @click="show = false" closeable>
      <div class="wrapper" @click.stop >
        <div class="block" v-if="jurisdiction">
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
                    :title="`${item.username} (${getCarId(item.linknum)})`"
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
        <div class="jurisdiction" v-if="!jurisdiction">
          <div class="titles"> <span>{{ isCancel ? '申请权限':'取消权限' }}</span>
            <span @click="handleCancel"><van-icon name="cross" /></span>
          </div>
          <van-form>
            <van-field              
              v-model="legalNum"
              name="授权人号码"
              label="授权人号码"
              :readonly="legalNum == ''?false:true"
              placeholder="授权人号码"
              :rules="[{ required: true, message: '请填写授权人号码' }]"
            />
            
            <van-field
              v-model="legalCode"
              name="验证码"
              label="验证码"
              placeholder="验证码"
            >
              <template #button>
                <van-button size="small" type="primary" :disabled='isRun' @click="getCode" :loading='loading' loading-text="获取中...">{{ isRun ?times+'s后再发送':'发送验证码' }}</van-button>
              </template>
            </van-field>
            <div style="margin: 16px;">
              <van-button round block type="info" :loading="btnLoading" @click="onSubmit" loading-text="请稍后...">提交</van-button>
            </div>
          </van-form>
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
      legalNum:'',
      loading:false,
      times:60,
      legalCode:'',
      btnLoading:false,
      show: false,
      isCancel:false,
      times2:0,
      searchValue: "",
      personList: [],
      jurisdiction:true,
      result: [],
      companyid: "",
      accreditList: [],
      radioValue: "",
      isRun:false,
      username: "",
      linktel: "",
      linknum:''
    };
  },
  created() {
    console.log('================>>>>>',this.$store.state.userInfo)
    this.companyid = JSON.parse(
      sessionStorage.getItem("companyInfo")
    ).companyid;
    // this.companyid = '4028079a79a285850179a28611360000'
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
      this.searchData(val);
    }
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
      if(this.legalCode == ''){
        Toast('请输入验证码！')
        return
      }
      this.btnLoading = true
      let resultData = await this.$axios.get(
        baseUrl + "/interface/wdnj/checkPhoneMessage",
        {
          params: {
            companyid:this.companyid,
            phone : this.legalNum,
            mess : this.legalCode
          }
        }
      );
      const { result, resultObj } = resultData.data;
      console.log(resultData.data);
      if (result) {
        this.legalCode = ''
        if(this.isCancel){
          this.btnLoading = false
          let allList = JSON.parse(sessionStorage.getItem('allData'))
          for(let i = 0;i<allList.length;i++){
            if(allList[i].companyid == this.companyid){
              allList[i].islegalperson = '0'
              sessionStorage.setItem("allData",JSON.stringify(allList));
            }
          }        
          let data2 = JSON.parse(sessionStorage.getItem("companyInfo"))
          data2.islegalperson = '0'
          this.username = data2.username
          this.times = 60
          this.isRun = false
          this.linknum = data2.linknum
          sessionStorage.setItem("companyInfo",JSON.stringify(data2));
          this.handleConfirm()
        }else{
          this.btnLoading = false
          let allList = JSON.parse(sessionStorage.getItem('allData'))
          for(let i = 0;i<allList.length;i++){
            if(allList[i].companyid == this.companyid){
              allList[i].islegalperson = '0'
              sessionStorage.setItem("allData",JSON.stringify(allList));
            }
          }        
          let data2 = JSON.parse(sessionStorage.getItem("companyInfo"))
          data2.islegalperson = '2'
          this.username = data2.username
          this.times = 60
          this.isRun = false
          this.linknum = data2.linknum
          sessionStorage.setItem("companyInfo",JSON.stringify(data2));
          this.subCreated()
        }
        
      }else{
        Toast('验证码校验失败')
        this.btnLoading = false
      }
    },
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
    getCarId(val){
      if(val){
        let str = val.replace(/(\w{7})\w*(\w{4})/, '$1******$2');
        return str
      }
    },
    getState(state) {
      return state == "2" ? "已取消" : "取消授权";
    },

    // 取消授权
    async handleCancelAccredit() {
      this.isCancel = false
      let { islegalperson } = JSON.parse(sessionStorage.getItem("companyInfo"))
      if(islegalperson == '1'){
        this.subCreated()
      }else{
        this.show = true
        this.jurisdiction = false
        this.getCompanyData()
      }
    },
    async subCreated(){
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
        this.show = false
        setTimeout(() => {
          Toast("取消授权成功");
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
      this.linknum = item.linknum;
    },

    adduthorization(flag) {
      this.isCancel = true
      let { islegalperson } = JSON.parse(sessionStorage.getItem("companyInfo"))
      if(islegalperson == '0'){
        Toast("您已被授权！");
      }else if(islegalperson == '1'){
        this.show = flag;
        this.jurisdiction = true
      }else{
        this.show = flag;
        this.jurisdiction = false
        this.getCompanyData()
      }
      console.log(islegalperson =='0'?'被授权人':(islegalperson =='1'?'法人':'普通用户'))
      // this.getCompanyData()
    },

    onSearch() {
      console.log("搜索参数----", this.searchValue);
    },

    // 取消
    handleCancel() {
      this.show = false;
      this.legalCode = ''
      // this.times = 60
      // this.isRun = false
      // clearInterval(this.times2);
    },

    // 确定
    async handleConfirm() {
      let data = {
        companyid: this.companyid,
        username: this.username,
        linknum: this.linknum
      }
      console.log(data)
      let resultData = await this.$axios.get(
        baseUrl + "/interface/company/save",
        {
          params: data
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
        this.isRun = false
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

.jurisdiction{
  position: relative;
  width: 6.4rem;
  background-color: #fff;
  border-radius: 6px;
  padding: 0.2rem 0;
}
.jurisdiction .titles{
  padding: 0 16px;
  height: 1rem;
  line-height: 1rem;
  font-size: 0.32rem;
  color: #3f94ff;
  font-weight: bold;
  display: flex;
  justify-content: space-between
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

.block .head{
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