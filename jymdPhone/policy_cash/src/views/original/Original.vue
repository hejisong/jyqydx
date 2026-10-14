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
            :class="item.isSelected == true? 'selectedStatus' : ''"
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

    <div class="original_list" v-if="writList.length > 0">
      <div class="original_item" v-for="item in writList" :key="item.id">
        <img src="../../assets/imgs/zhengce.png" alt />
        <div class="original_info">
          <div
            class="original_info_title"
            @click="handleToCheck(item.uploadfile)"
          >{{item.policyname}}</div>
          <div class="original_info_writ">
            <span>政策文号：{{item.fwtitanic}}</span>
            <span class="time">{{item.releasetime}}</span>
          </div>
          <div class="original_info_unit">发布单位：{{item.fwunits}}</div>
        </div>
      </div>
    </div>

    <van-empty description="暂无数据" v-else />
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";

import Head from "../../components/head/Head";

import { Toast, Notify, Empty } from "vant";

export default {
  name: "Original",
  components: {
    Head
  },
  data() {
    return {
      show: false,
      searchValue: "",
      beginTime: "",
      endTime: "",
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
  },

  watch: {
    searchValue(val, oldVal) {
      this.getPolicyData(val, this.beginTime, this.endTime);
    }
  },

  methods: {
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

      console.log("result", resultData);
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
            url: urlObj.path
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
.van-search {
  border-bottom: 1px solid #eee;
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
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
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
</style>