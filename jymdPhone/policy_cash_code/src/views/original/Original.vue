<template>
  <div class="original">
    <Head :show="true" title="最新政策" />

    <!-- 筛选组件 -->
    <van-popup v-model="show" position="top" :style="{ height: '54%' }">
      <div class="filter_content">
        <div class="writ_type">政策类型</div>
        <div class="writ_type_list">
          <div
            class="writ_type_item"
            v-for="item in filterList"
            :key="item.id"
            :class="item.isSelected == true ? 'selectedStatus' : ''"
            @click="handleChoose(item)"
          >{{item.text}}</div>
        </div>

        <div>
          <div class="screen-title">发文时间</div>
          <div class="cheack_data">
            <p class="start_time" @click="selectMonth('start')">{{ startMonthTime }}</p>
            <p>~</p>
            <p class="end_time" @click="selectMonth('end')">{{ endMonthTime }}</p>
          </div>
        </div>
      </div>
      <div class="filter_btn">
        <div class="btn rest" @click="handleRest">重置</div>
        <div class="btn confirm" @click="handleConfirm">确定</div>
      </div>
    </van-popup>

    <!--年月日组件-->
    <van-popup v-model="showYearMonthDay" position="bottom" get-container="body">
      <van-datetime-picker
        v-model="currentDateDay"
        type="date"
        title="选择年月日"
        :min-date="minDate"
        :max-date="maxDate"
        :formatter="formatter"
        @cancel="showYearMonthDay=false"
        @confirm="confirmTimeDay"
      />
    </van-popup>

    <van-search
      v-model="searchValue"
      show-action
      shape="round"
      placeholder="请输入政策原文名称"
      @search="onSearch"
    >
      <template #action>
        <img
          style="width:0.32rem;height:0.32rem"
          src="../../assets/imgs/筛选@3x.png"
          alt
          @click="show = true"
        />
      </template>
    </van-search>
    <div class="labelList">
      <ul class="nowrap">
        <span class="label" :class="bgcIndex == i?'labelBgc':''" @click="labelClick(i,item)" v-for="(item,i) in labelListNum" :key="i">{{ item.tagname }}</span>
        <!-- <van-tag text-color="red" v-for="i in 10" style="margin-right:10px" :key="i" round plain type="primary">标签{{ i }}</van-tag> -->
      </ul>
    </div>
    <van-tabs @click="onClick" v-model="tabNum" class="van-hairline--bottom" color="#1989fa" v-if="tabShow">
      <van-tab title="原文" name="0"></van-tab>
      <van-tab title="细则" name="1"></van-tab>
    </van-tabs>
    <div class="original_list" v-if="writList.length > 0 && tabNum == '0'">
      <div class="original_item" v-for="item in writList" :key="item.id">
        <img src="../../assets/imgs/zhengce.png" alt />
        <div class="original_info">
          <div
            class="original_info_title"
            @click="handleToCheck(item.filecontent)"
          ><span v-html="highLight(item.policyname)"></span></div>
          <div class="original_info_writ">
            <span>文号：{{item.fwtitanic}}</span>
            <span class="time">{{item.releasetime}}</span>
          </div>
          <div class="original_info_unit">发布单位：{{item.fwunits}}</div>
          <div class="original_info_unit" style="margin-top:5px">
            <van-tag style="margin:0px 5px 6px 0px" v-for="(item2,index2) in item.tags" :key="index2" plain type="primary">{{ item2[1] }}</van-tag>
          </div>
          
        </div>
      </div>
    </div>
    <div class="original_list" v-if="tabNum == '1'">
      <div class="formChild van-hairline--surround" v-for="(item,index) in formList" :key="index">
        <ul class="van-hairline--top-bottom" style="height:34px;line-height: 34px">
          <li class="clearfix">
            <span class="pu-left">{{ index+1 }}、</span>
            <span class="pu-right" style="color:#1989fa" @click="linkTo(item[0])">申报</span>
          </li>
        </ul>
        <ul><span class="titleLabel"> 政策类别：</span><span v-html="item[3] ? highLight(item[2])+'('+ highLight(item[3])+')':highLight(item[2])"></span></ul>
        <ul><span class="titleLabel">政策条件：</span> <span v-html="highLight(item[4])"></span></ul>
        <ul><span class="titleLabel">政策奖励：</span><span v-html="highLight(item[4])"></span></ul>
      </div>
    </div>

    <van-empty description="暂无数据" v-if="writList.length < 1 && tabNum == '0'" />
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";

import Head from "../../components/head/Head";

import { Toast, Notify, Empty,Tag } from "vant";

export default {
  name: "Original",
  components: {
    Head
  },
  data() {
    return {
      tabShow:false,
      tabNum:'0',
      show: false,
      searchValue: "",
      labelListNum:[],
      beginTime: "",
      formList:[],
      endTime: "",
      bgcIndex:'-1',
      currentDate: new Date(),
      currentDateDay: new Date(),
      minDate: new Date(new Date().getFullYear() - 50, 0, 1),
      maxDate: new Date(new Date().getFullYear(), 12, 0),
      yearMonthDayType: "",
      startMonthTime: "请选择日期",
      endMonthTime: "请选择日期",
      endTimeDay: "请选择日期",
      startTimeDay: "请选择日期",
      beginCheckDate: "", //检查时间（开始）
      endCheckDate: "", //检查时间（结束）
      showYearMonthDay: false, //责令整改期限（年月日组件）
      writList: [],
      filterList: [
        {
          id: 1,
          text: "人力资源"
        },
        {
          id: 2,
          text: "市场开拓"
        },
        {
          id: 3,
          text: "税收"
        },
        {
          id: 4,
          text: "产业发展"
        },
        {
          id: 5,
          text: "转型升级"
        },
        {
          id: 6,
          text: "投融资"
        },
        {
          id: 7,
          text: "科技创新"
        },
        {
          id: 8,
          text: "其他"
        }
      ]
    };
  },
  created() {
    this.getPolicyData(this.searchValue, this.beginTime, this.endTime);
    this.getLabelList()
  },
  mounted(){
    
    const { isLogin } = sessionStorage;
    if(isLogin){      
      console.log('已登录')
    }else{
      console.log('未登录')
    }
  },
  watch: {
    searchValue(val, oldVal) {
      if(val && val != ''){
        this.tabShow = true
        this.bgcIndex = -1
        this.tabNum = 0
        this.getPolicyData(val, this.beginTime, this.endTime);
      }
      if(val == ''){
        this.tabShow = false
        this.tabNum = '0'
        this.getPolicyData(this.searchValue, this.beginTime, this.endTime);
      }
      if(val == '' && this.bgcIndex != -1){
        this.tabShow = true
      }
    },
  },

  methods: {
    async linkTo(ids){
      const { isLogin } = sessionStorage;
      if(!isLogin){
        Toast("请登录!");
        return
      }
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/apply",
        {
          params: {
            pldtailid:ids,
            companyid:data.companyid
          }
        }
      );
      console.log('=============>',resultData.data.errorMsg)
      let isTrue = resultData.data.result
      if(isTrue){
        let id = resultData.data.resultObj
        console.log(id,'[[[[[[[[[[')
        this.$router.push({
          path: "/policyDetail",
          query: {
            id: id,
          }
        });
      }else{
        Toast(resultData.data.errorMsg);
        return
      }
    },
    // 细则
    async getFiles(val){
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findPolicydetails",
        {
          params: {
            content:val
          }
        }
      );
      Toast.clear();
      this.formList = resultData.data.resultObj
      console.log('细则=====>',resultData)
    },
    onClick(name, title){
      console.log(name,title)
      this.tabNum = name
      if(name == 1 && this.bgcIndex == -1){
        this.getFiles(this.searchValue)
      }else if(name == 1 && this.bgcIndex != -1){
        this.getFiles(this.labelListNum[this.bgcIndex].tagname)
      }else if(name == 0){
        // this.getPolicyData(this.searchValue, this.beginTime, this.endTime);
        console.log('kkkkkkk')
      }
    },
    tagsClick(val){

    },
    // 获取标签对应的列表
    labelClick(i,val){
      this.searchValue = ''
      if(i == this.bgcIndex){
        this.bgcIndex = -1
        this.tabNum = 0
        this.getPolicyData(this.searchValue, this.beginTime, this.endTime);
        this.tabShow = false
      }else{
        this.bgcIndex = i
        this.getLabelData(val)
        this.getFiles(val.tagname)
        this.tabShow = true
        setTimeout(()=>{
          this.tabNum = 0
        },10)
      }
    },
    // 获取标签对应的列表
    async getLabelData(val){
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      let resultData = await this.$axios.get(
        baseUrl + "/interface/tag/queryPolicyFileByCompany",
        {
          params: {
            companyid: data ? data.companyid:'',//
            tagid:val.tagid,
          }
        }
      );
      this.writList = resultData.data.resultObj
      Toast.clear();
      console.log(resultData)
    },
    // 获取标签列表
    async getLabelList(){
      const { isLogin } = sessionStorage;
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/tag/queryTagByCompany",
        {
          params: {
            companyid: isLogin?data.companyid:'',//
          }
        }
      );

      console.log("已标签列表----》", resultData);
      Toast.clear();
      this.labelListNum = resultData.data.resultObj
      console.log('[[[[[[[',this.labelListNum)
    },
    async getPolicyData(searchValue, beginTime, endTime) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findAll",
        {
          params: {
            policyname: searchValue,
            beginDate: beginTime,
            endDate: endTime
          }
        }
      );

      console.log("政策列表-------》", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {        
        Toast.clear();
        this.writList = Array.isArray(resultObj) ? resultObj : [];
      }
    },

    onSearch() {
      console.log("搜索参数----", this.searchValue);
    },

    handleToCheck(urlObj) {
      if (urlObj == null) {
        Toast("暂无政策原文!");
      } else {
        this.$router.push({
          path: "/browse",
          query: {
            url: urlObj
          }
        });
      }
    },

    handleChoose(item) {
      this.$forceUpdate();
      if (!item.isSelected) {
        this.filterList.map(item => {
          item.isSelected = false;
        });
        item.isSelected = true;
      } else if (item.isSelected) {
        item.isSelected = false;
      }
    },

    //选择月份
    selectMonth(type) {
      this.showYearMonthDay = true;
      if (type == "start") {
        this.yearMonthDayType = type;
      } else if (type == "end") {
        this.yearMonthDayType = type;
      }
    },
    // 搜索高亮
    highLight(title){
      if(this.searchValue != ''){
         // 如果标题中包含，关键字就替换一下
        if(title.includes(this.searchValue)){
          title = title.replace(
              this.searchValue, 
              // 这里是替换成html格式的数据，最好再加一个样式权重，保险一点
              '<font style="color:red!important;font-size:14px;font-weight: 600">'+ '&nbsp;'+this.searchValue+'&nbsp;' +'</font>'
          )
          return title
        }
        // 不包含的话还用这个
        else{
          return title
        }
      }else if(this.bgcIndex != -1 && this.tabNum == 1){
        if(title.includes(this.labelListNum[this.bgcIndex].tagname)){
          title = title.replace(
              this.labelListNum[this.bgcIndex].tagname, 
              // 这里是替换成html格式的数据，最好再加一个样式权重，保险一点
              '<font style="color:red!important;font-size:14px;font-weight: 600">'+ '&nbsp;'+this.labelListNum[this.bgcIndex].tagname+'&nbsp;' +'</font>'
          )
          return title
        }
        // 不包含的话还用这个
        else{
          return title
        }
      }else{
        return title
      }
    },
    // 重置
    handleRest() {
      this.$forceUpdate();
      this.filterList.map(item => {
        item.isSelected = false;
      });

      this.startMonthTime = "请选择日期";
      this.endMonthTime = "请选择日期";
      this.beginTime = "";
      this.endTime = "";
    },

    // 确定
    handleConfirm() {
      this.$forceUpdate();
      var chooseItem = this.filterList.filter(item => item.isSelected == true);
      if (chooseItem.length > 0) {
        this.currentValue = chooseItem[0].text;
      } else {
        this.currentValue = "";
      }

      console.log(
        "currentValue",
        this.currentValue,
        this.beginTime,
        this.endTime
      );

      this.show = false;
      this.getPolicyData(this.searchValue, this.beginTime, this.endTime);
    },

    confirmTimeDay(time) {
      console.log(time);
      this.showYearMonthDay = false;
      this.currentDate = new Date(time); //初始化

      if (this.yearMonthDayType == "start") {
        this.startMonthTime = this.parseTime(time, "{y}-{m}-{d}");
        this.beginTime = this.parseTime(time, "{y}-{m}-{d}");
      } else if (this.yearMonthDayType == "end") {
        this.endMonthTime = this.parseTime(time, "{y}-{m}-{d}");
        this.endTime = this.parseTime(time, "{y}-{m}-{d}");
      }

      let dateBegin = new Date(this.startMonthTime.replace(/-/g, "/")); //将-转化为/，使用new Date
      let dateEnd = new Date(this.endMonthTime.replace(/-/g, "/")); //将-转化为/，使用new Date
      let timedifference = dateBegin.getTime() - dateEnd.getTime(); //开始时间和结束时间相减

      if (this.yearMonthDayType == "start") {
        if (timedifference > 0) {
          Notify({
            message: "开始日期不能晚于结束日期",
            duration: 5000,
            background: "#1989fa"
          });

          this.startMonthTime = "请选择日期";
          this.beginTime = "";
        } else {
        }
      } else if (this.yearMonthDayType == "end") {
        if (timedifference > 0) {
          Notify({
            message: "结束日期不能早于开始日期",
            duration: 5000,
            background: "#1989fa"
          });
          this.endMonthTime = "请选择日期";
          this.endTime = "";
        } else {
        }
      }
    },

    parseTime(time, cFormat) {
      if (arguments.length === 0) {
        return null;
      }
      const format = cFormat || "{y}-{m}-{d} {h}:{i}:{s}";
      let date;
      if (typeof time === "object") {
        date = time;
      } else {
        if (("" + time).length === 10) time = parseInt(time) * 1000;
        date = new Date(time);
      }
      const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
      };
      const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
        let value = formatObj[key];
        if (key === "a")
          return ["一", "二", "三", "四", "五", "六", "日"][value - 1];
        if (result.length > 0 && value < 10) {
          value = "0" + value;
        }
        return value || 0;
      });
      return time_str;
    },

    //年月组件初始化
    formatter(type, val) {
      if (type === "year") {
        return `${val}年`;
      } else if (type === "month") {
        return `${val}月`;
      } else if (type === "day") {
        return `${val}日`;
      }
      return val;
    }
  }
};
</script>

<style scoped>
/* 组件搜索框样式 */
.van-search{
  padding-bottom: 6px !important;
}
.labelList {
  padding-bottom:10px;
  display: flex;
  margin: 0;
  border-bottom: 1px solid #eee;  
}
.nowrap{
  display: flex;
  width: 96%;
  margin-left: 2%;
  white-space: nowrap;
  overflow-y: hidden;
  overflow-x: scroll;
}
.van-search__action {
  padding: 0 15px;
  display: flex;
}

.original .van-cell {
  padding: 6px 10px !important;
}

/* 筛选组件样式 */

.writ_type_list {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-top: 0.24rem;
}

.writ_type_item {
  margin-bottom: 0.18rem;
  width: 3.2rem;
  height: 0.8rem;
  line-height: 0.8rem;
  text-align: center;
  font-size: 0.26rem;
  color: #333;
  background-color: #f2f2f2;
  border-radius: 3px;
}

.filter_content {
  position: relative;
  max-height: 12rem;
  overflow: auto;
  padding: 0.3rem 0.4rem;
}

.writ_type {
  font-size: 0.28rem;
  color: #333;
}

.screen-title {
  margin: 0.1rem 0;
  font-size: 0.28rem;
}

.cheack_data {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.25rem;
  font-size: 0.28rem;
}

.cheack_data > .start_time,
.cheack_data > .end_time {
  background-color: #f2f2f2;
  padding: 0.2rem 0.55rem;
  border-radius: 3px;
  color: #aaaaaa;
}

.filter_btn {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  align-items: center;
  width: 100%;
  height: 0.8rem;
  border-top: 1px solid #eee;
}

.filter_btn .btn {
  flex: 1;
  line-height: 0.8rem;
  font-size: 0.32rem;
  text-align: center;
}

.btn.rest {
  color: #333;
  background: #fff;
}

.btn.confirm {
  color: #fff;
  background-color: #5390ff;
}

/* 原文列表样式 */

.original {
  padding-bottom: 0.98rem;
}

.original_list {
  padding: 12px 16px;
}

.original_item {
  display: flex;
  align-items: center;
  margin-bottom: 0.24rem;
  padding: 0.28rem 0.32rem;
  /* height: 2.4rem; */
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.original_item > img {
  width: 0.4rem;
  height: 0.4rem;
}

.original_info {
  margin-left: 0.32rem;
}

.original_info .original_info_title {
  width: 5.3rem;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  font-size: 0.28rem;
  font-weight: 500;
  color: #4288ff;
}

.original_info_writ {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0.02rem 0;
  font-size: 0.24rem;
  font-weight: 400;
  color: #666;
}
.titleLabel{
  color: #606266
}
.formChild{
  font-size: 14px;
  width: 96%;
  margin: auto auto 0.35rem auto;
  padding: 0 0.2rem 0.1rem 0.2rem;
  border-radius: 5px;
  box-shadow:rgba(0, 0, 0, 0.2) 1px 1px 5px 1px;
}
.formChild ul{
  padding: 0.1rem;
  margin-bottom: 0.2rem;
}
.original_info_writ .time {
  color: #999;
}

.original_info_unit {
  font-size: 0.24rem;
  font-weight: 400;
  color: #666;
}

.writ_type_item.selectedStatus {
  outline: 1px solid #5390ff;
  background: #e0ebff;
  color: #5390ff;
}
.label{
  margin-right:10px;
  padding:3px 10px;
  /* border:1px solid red; */
  background-color: #f7f8fa;
  color: #B4B4B4;
  border-radius: 15px;
  font-size:12px
}
.labelBgc{
  background-color: #1989fa;
  color: #ffffff;
}
.pu-left{
  float: left;
}
.pu-right{
  float: right;
}
.clearfix:after{
  content:".";       
  display:block;       
  height:0;       
  clear:both;       
  visibility:hidden;       
}
</style>