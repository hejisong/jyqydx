<template>
  <div class="wrapper">
    <!-- <Head :show="true" title="最新政策" /> -->
    <div class="hearder">
        <van-nav-bar
            style="background-color:#4294ff"
            title="政策查询"
            
        />
    </div>
    <div class="search">       
        <van-search
            v-model="searchValue"
            show-action
            shape="round"
            background=""
            placeholder="请输入搜索关键词"
            @input="onSearch"
        >
        <template #action>
            <!-- <span @click="onSearch">搜索</span> -->
            <span style="margin-left:0.2rem" @click="reset">重置</span>
        </template>
        </van-search>
        <!-- <van-search v-model="searchValue" placeholder="请输入搜索关键词" /> -->
    </div>
    <div class="content">
        <div class="screen">
            <van-dropdown-menu active-color="#1989fa">
                <van-dropdown-item v-model="fileLevel" title="政策级别" @change="getPolicyData" :options="option1" />
                <span class="en"></span>
                <van-dropdown-item title="生效日期" ref="item" @open='open'>
                    <div class="cheack_data">
                        <p class="start_time" :class="yearMonthDayType == 'start'?'active':''"  @click="selectMonth('start')">{{ startMonthTime == ''?'请选择开始日期': startMonthTime}}</p>
                        <p>~</p>
                        <p class="end_time" :class="yearMonthDayType == 'end'?'active':''" @click="selectMonth('end')">{{ endMonthTime == ''?'请选择结束日期': endMonthTime}}</p>
                    </div>
                    <div class="entDate">
                        <!-- <van-button size="small" type="info">确定</van-button> -->
                    </div>
                    <van-datetime-picker
                        v-show="showYearMonthDay"
                        v-model="currentDate"
                        @change='choiceDate'
                        type="date"
                        :confirm-button-text='"搜索"'
                        @confirm='confirmDate("1")'
                        @cancel='confirmDate("2")'
                        :title="dateTitle"
                        :min-date="minDate"
                        :max-date="maxDate"
                    />
                </van-dropdown-item>
                <span class="en2"></span>
                <van-dropdown-item title="政策标签" ref="item2">
                  <div class="listStyle" v-if="labelListNum.length>0">
                    <van-checkbox-group v-model="tagid" @change='getPolicyData'>
                      <van-cell-group>
                        <van-cell
                          v-for="(item,index) in labelListNum"
                          clickable
                          :key="item.value"
                          :title="item.text"
                          @click="toggle(index)"
                        >
                          <template #right-icon>
                            <van-checkbox :name="item.value" ref="checkboxes" />
                          </template>
                        </van-cell>
                      </van-cell-group>
                    </van-checkbox-group>
                  </div>
                  <div v-else class="noData">
                    <van-divider>暂无数据</van-divider>
                  </div>
                </van-dropdown-item>
            </van-dropdown-menu>
        </div>
        <div class="scol">
            <div class="list van-hairline--bottom" v-for="(item,index) in writList" :key="index">
                <div class="title clearfix">
                    <span class="fileLeave"><van-tag class="pu-left" color="#ffa24e">{{ item.filelevel == ''?'全部':(item.filelevel == '1'?'市级':'区级') }}</van-tag></span>
                    <span class="detail" @click="handleToCheck(item,'1')" v-html="item.policyname"></span>
                </div>
                <div class="original_info_unit">
                    <van-tag color="#f1f7ff" text-color="#6cabff" @click="handleTags(item,item2,'2')" style="margin: 0 0.25rem 0.1rem 0" v-for="(item2,index2) in item.tags" :key="index2" >{{ item2[1] }}</van-tag>
                </div>
                <div class="original_info_writ">
                    <span>文号：{{ item.fwtitanic =='' ?'无':item.fwtitanic }}</span>
                    <span class="time">{{ item.releasetime }}</span>
                </div>
                <div class="original_info_unit">
                  <van-button type="info" size="mini" @click="showList(item)">奖励政策明细</van-button>
                </div>
                
            </div>
        
        </div>
    </div>
    <div class="commponents">
      <van-popup v-model="shows" :close-on-click-overlay='false' round>
          <prop-view :childList='childList' :noRegiste="noRegiste" @close='close'></prop-view>
          
      </van-popup>
    </div>
    <!-- <van-empty description="暂无数据" v-if="writList.length < 1 && tabNum == '0'" /> -->
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import propView from './components/propView'
import Head from "../../components/head/Head";

import { Toast, Notify, Empty,Tag } from "vant";

export default {
  name: "newIndex",
  components: {
    Head,
    propView
  },
  data() {
    return {
        shows:false,
        dateTitle:'选择开始时间',
        searchValue:'',//关键字
        fileLevel:'',//级别
        tagid:[],//标签id
        startMonthTime: "",
        noRegiste:'',
        endMonthTime: "",
        currentDate: new Date(),
        currentDateDay: new Date(),
        minDate: new Date(new Date().getFullYear() - 50, 0, 1),
        maxDate: new Date(new Date().getFullYear(), 12, 0),
        childList:[],
        option1: [
            { text: '全部', value: '' },
            { text: '市级', value: '1' },
            { text: '区级', value: '2'},
        ],


        tabShow:false,
        tabNum:'0',
        show: false,
        searchValue: "",
        labelListNum:[],
        beginTime: "",
        formList:[],
        endTime: "",
        bgcIndex:'-1',
        yearMonthDayType: "",
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
    this.getPolicyData();
    this.getLabelList()
  },
  mounted(){
    this.noRegiste = sessionStorage.getItem('noRegiste')   
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
    // display
  },
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
  },
  watch: {
    
  },

  methods: {
    open(){
      // this.startMonthTime = this.getDate(new Date())
    },
    toggle(index) {
      this.$refs.checkboxes[index].toggle();
      console.log(this.tagid)
    },
    reset(){
      this.searchValue = '',//关键字
      this.fileLevel = '',//政策级别 1市级 2区级
      this.endMonthTime = '',//结束时间
      this.tagid = [],//标签id
      this.startMonthTime = ''//开始时间
      this.getPolicyData()
    },
    close(){
      this.shows = false
    },
    async showList(item){
      console.log(item)
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      console.log(data)
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findPolicydetailsByFileId",
        {
          params: {
            plfidid:item.plfidid,
            companyid:data?data.companyid:''
          }
        }
      );
      console.log(resultData)
      const { result, resultObj } = resultData.data;
      if(result){
        this.childList = resultObj
        this.shows = true
        Toast.clear()
      }
      
    },
    confirmDate(val){
        if(val == '2'){
          this.startMonthTime  = ''
          this.endMonthTime  = ''
          this.yearMonthDayType = 'start'
        }else{
          if(this.endMonthTime == ''){
            Toast('请选择结束时间！');
            return
          }
          let star = new Date(this.startMonthTime.replace(/-/g,'/')).getTime()
          let end = new Date(this.endMonthTime.replace(/-/g,'/')).getTime()
          if(star > end){
            Toast('结束时间不能早于开始时间！');
            return
          }
          this.getPolicyData()
        }
        this.dateTitle = '选择开始时间'
        this.$refs.item.toggle();
    },
    onClickLeft(){
        this.$router.back()
    },
    choiceDate(picker){
        console.log('picker111==',picker)
        const formatValue = (picker.getValues()[0] + picker.getValues()[1] + picker.getValues()[2])
        const format = (picker.getValues()[0] +'-'+ picker.getValues()[1] +'-'+ picker.getValues()[2]).replace(/[\u4e00-\u9fa5]/g, '-')
        console.log('1==',picker.getValues())
        console.log('formatValue==',formatValue)
        console.log('format111==',format)
        if(this.yearMonthDayType == 'start'){
            this.startMonthTime = format
        }else if(this.yearMonthDayType == 'end'){
            this.endMonthTime = format
        }
    },
    
    // 获取标签列表
    async getLabelList(){
      const { isLogin } = sessionStorage;
      console.log(isLogin)
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/tag/queryTagByCompany",
        {
          params: {
            companyid: isLogin && data?data.companyid:'',//
          }
        }
      );

      console.log("已标签列表----》", resultData);
      const { result, resultObj } = resultData.data;
      if(result){
        this.labelListNum = []
        Toast.clear();
        if(resultObj.length>0){
          for(let i = 0;i<resultObj.length;i++){
            let obj = {
              text:resultObj[i].tagname,
              value:resultObj[i].tagid
            }
            this.labelListNum.push(obj)
          }
        }
        console.log('[[[[[[[',this.labelListNum)
      }
      
    },
    // 列表
    async getPolicyData() {       
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findAll",
        {
          params: {
            key: this.searchValue,//关键字
            fileLevel:this.fileLevel,//政策级别 1市级 2区级
            beginDate: this.startMonthTime,//结束时间
            tagid:this.tagid.join(','),//标签列表id
            endDate: this.endMonthTime//开始时间
          }
        }
      );

      console.log("政策列表-------》", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        if(this.searchValue != ''){
          for(let i = 0;i<resultObj.length;i++){
            resultObj[i].policyname = this.highLight(resultObj[i].policyname)
          }
        }
        Toast.clear();

        this.writList =  resultObj
      }
    },

    onSearch() {
        this.getPolicyData()
    //   console.log("搜索参数----", this.searchValue);
    },

    handleToCheck(urlObj,type) {
      if (urlObj.filecontent == null || urlObj.filecontent == '') {
        Toast("暂无政策原文!");
      } else {
        if(this.searchValue != ''){
          urlObj.filecontent = this.highLight(urlObj.filecontent)
        }
        let data = {
          item:urlObj,
          type:type,
          isSearch:this.searchValue != ''?this.searchValue:''
        }
        console.log('=========>',data)
        this.$router.push({
          path: "/browse?tagert=self",
          query: {
            url: JSON.stringify(data)
          }
        });
      }
    },
    handleTags(item,item2,type){
        if(this.searchValue != ''){
            item.filecontent = this.highLight(item.filecontent)
        }
        let data = {
          item:item,
          tagid:item2[0],
          isSearch:this.searchValue != ''?this.searchValue:'',
          type:type
        }
        this.$router.push({
          path: "/browse",
          query: {
            url: JSON.stringify(data)
          }
        });
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
        this.dateTitle = '选择开始时间'
        this.yearMonthDayType = type;
        if(this.startMonthTime != ''){
            let arr = this.startMonthTime.split('-')
            console.log(arr)
            this.currentDate = new Date(arr[0], (arr[1]-1), arr[2])
        }
        
      } else if (type == "end") {
        this.dateTitle = '选择结束时间'
        this.yearMonthDayType = type;
        if(this.endMonthTime != ''){
            let arr = this.endMonthTime.split('-')
            console.log(arr)
            this.currentDate = new Date(arr[0], (arr[1]-1), arr[2])
        }
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
              '<font style="color:red!important;font-weight: 600">'+ '&nbsp;'+this.searchValue+'&nbsp;' +'</font>'
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
    getDate(date){
      let y = date.getFullYear()
      let m = date.getMonth() + 1 < 10 ? ("0"+(date.getMonth() + 1)):date.getMonth() + 1
      let d = date.getDate() < 10 ? ('0' + date.getDate()):date.getDate()
      return y+'-'+m+'-'+d
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
    .wrapper{
        width: 100%;
        height: 100%;
        background-color: #4294ff;
        overflow-y: auto;
    }
    .commponents>>>.van-popup{
        background-color: transparent;
    }
    .hearder>>>.van-nav-bar__title{
        color: #ffffff;
    }
    .hearder>>>.van-hairline--bottom::after{
        display: none
    }
    /* .commponents{
        width: 80%;
        height: 5rem;
    } */
    .hearder>>>.van-nav-bar__text,.hearder>>>.van-icon-arrow-left{
        color: #ffffff;
    }
    .search{
        width:90%;
        /* height: ; */
        overflow: hidden;
        margin: 0 auto;
        border-radius: 5px;
    }
    .search>>>.van-button::before{
        background-color:transparent;
    }
    .search>>>.van-cell{
        padding: 0.1rem !important;
    }
    .content{
        width: 100%;
        background-color: #ffffff;
        border-top-left-radius: 0.3rem;
        border-top-right-radius: 0.3rem;
        margin-top: 0.45rem;
        padding: 0.2rem 0;
    }
    .scol{
        height: 11rem;
        overflow-y: auto;
    }
    .screen{
        width: 100%;
        margin: auto auto 0.2rem auto;
        position: relative;
    }
    .listStyle{
      min-height: 2rem;
      max-height: 4.8rem;
      overflow-y: auto;
    }
    .screen .en{
        display: inline-block;
        position: absolute;
        height: 0.35rem;
        top: 0.3rem;
        left: 34.9%;
        width: 1px;
        border-right: 1px solid #f0f0f0;
    }
    .screen .en2{
        display: inline-block;
        position: absolute;
        height: 0.35rem;
        top: 0.3rem;
        left: 69%;
        width: 1px;
        border-right: 1px solid #f0f0f0;
    }
    .screen>>>.van-dropdown-menu__bar{
        box-shadow:none
    }
    .list{
        width: 90%;
        margin: auto auto 0.4rem auto;
        padding: 0 0 0.3rem 0;
    }
    .original_info_unit>>>.van-tag{
        font-size: 0.28rem;
        line-height: 18px;
    }
    .detail{
        /* display: inline-block; */
        /* width: 85%; */
        font-size: 0.32rem;
        margin: 0;
        line-height: 21px !important;
        color: #333333;
        vertical-align: middle
    }
    .active{
      border: 1px solid #5390ff;
    }
    .detail>>>.van-tag--default{
        font-size: 14px;
        line-height: 18px;
    }
    .title{
        line-height: 0.38rem;
        
    }
    .fileLeave>>>.van-tag{
      line-height: 21px;
      /* border:1px solid red; */
    }
    .fileLeave{
        /* display: inline-block;
        width: 0.8rem; */
        text-align: center;
        margin: 0 5px 0 0;
        /* line-height: 0.36rem; */
        vertical-align: middle
    }
    .text_1{
        display: inline-block;
        white-space: nowrap; 
        overflow: hidden;
        text-overflow:ellipsis;
    }
    .original_info_writ {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin: 0.02rem 0;
        font-size: 0.28rem;
        font-weight: 400;
        color: #666666;
    }
    .entDate{
        text-align: right;
        margin-bottom: 0.3rem;
        padding-right: 0.35rem;
    }
</style>

<style scoped>
/* 组件搜索框样式 */
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
  padding: 0 0.25rem;
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
  font-size: 0.31rem;
  font-weight: 400;
  padding: 0 0.05rem;
  color: #666;
  margin:0.2rem 0 0rem 0;
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