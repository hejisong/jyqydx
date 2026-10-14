<template>
  <div class="suggestion">
    <!-- 悬浮按钮 -->
    <div class="suggestion-btn" @click="handleToPublish" v-if="hideshow">
      <img src="../../assets/imgs/我要发表@3x.png" alt />
      <span>我要发表</span>
    </div>

    <div class="suggestion-bg">
      <img src="../../assets/imgs/bg@3x.png" alt />
      <div class="suggestion-title">意见和建议</div>
      <div class="suggestion-search">
        <van-search v-model="searchValue" placeholder="请输入关键词进行搜索" />
      </div>
    </div>

    <div class="suggestion-list">
      <template v-if="suggestionList && suggestionList.length > 0">
        <div
          class="suggestion-list-item"
          v-for="item in suggestionList"
          :key="item.id"
          @click="handleToDetail(item)"
        >
          <div class="title">{{item.title}}</div>
          <div class="time">{{item.createtime}}</div>
          <div class="enterprise">
            <div class="company">{{item.companyname}}</div>
            <div class="view">浏览量：{{item.readcount}}</div>
          </div>
        </div>
      </template>
      <div class="not-data" v-else>暂无数据</div>
    </div>
  </div>
</template>

<script>
import { baseUrl } from "../../network/baseUrl";
import { Search, Toast, Empty } from "vant";

export default {
  name: "Suggestion",
  data() {
    return {
      searchValue: "",
      suggestionList: [],
      docmHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      showHeight:
        document.documentElement.clientHeight || document.body.clientHeight,
      hideshow: true
    };
  },

  watch: {
    //监听显示高度
    showHeight() {
      if (this.docmHeight > this.showHeight) {
        //隐藏
        this.hideshow = false;
      } else {
        //显示
        this.hideshow = true;
      }
    },

    searchValue(val, oldVal) {
      this.getSuggestionData(val);
    }
  },

  created() {
    this.getSuggestionData(this.searchValue);
  },

  mounted() {
    // window.onresize监听页面高度的变化
    window.onresize = () => {
      return (() => {
        this.showHeight =
          document.documentElement.clientHeight || document.body.clientHeight;
      })();
    };
  },

  beforeDestroy() {
    window.onresize = null; //注销window.onresize事件
  },

  methods: {
    // 获取意见列表数据
    async getSuggestionData(searchValue) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/suggestions/list",
        {
          params: {
            title: searchValue
          }
        }
      );

      console.log("result", resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.suggestionList = Array.isArray(resultObj) ? resultObj : [];
      }
    },

    handleToDetail(item) {
      this.$router.push({
        path: "/publish",
        query: {
          id: item.id
        }
      });
    },

    handleToPublish() {
      this.$router.push("/publish");
    }
  }
};
</script>
<style >
.van-cell {
  align-items: baseline !important;
}
</style>

<style scoped>
.suggestion {
  padding-bottom: 0.98rem;
}

.suggestion-btn {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 1.28rem;
  height: 1.28rem;
  color: #3e92ff;
  border-radius: 50%;
  background-color: #ffffff;
  position: fixed;
  bottom: 2rem;
  right: 28px;
  z-index: 9999;
}

.suggestion-btn span {
  font-size: 0.3rem;
  transform: scale(0.7, 0.7);
  transform-origin: center top;
}

.suggestion-btn img {
  width: 0.6rem;
  height: 0.6rem;
}

.suggestion-bg {
  position: relative;
  width: 100%;
  height: 4rem;
}

.suggestion-bg img {
  width: 100%;
}

.suggestion-title {
  position: absolute;
  top: 16%;
  left: 50%;
  transform: translateX(-50%);
  font-size: 0.38rem;
  color: #fff;
  letter-spacing: 1px;
}

.suggestion-search {
  position: absolute;
  top: 33%;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
  height: 1.34rem;
}

.suggestion-search >>> .van-search {
  padding: 10px 24px;
  background: none;
}

.van-search__content {
  border-radius: 4px;
}

.van-search .van-cell {
  padding: 0.2rem 0.16rem 0.2rem 0;
}

.suggestion-list {
  position: absolute;
  margin: -62.5px 0.46rem 1.5rem 0.46rem;
  box-sizing: border-box;
  padding: 0.4rem;
  width: 6.58rem;
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 4px 2px rgba(223, 223, 223, 0.35);
  z-index: 999;
  overflow-y: auto;
  max-height: 9.66rem;
  min-height: 1.98rem;
}

.suggestion-list .suggestion-list-item {
  margin-bottom: 0.4rem;
  padding-bottom: 0.32rem;
  border-bottom: 1px solid #f8f8f8;
}

.suggestion-list-item:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.suggestion-list-item .title {
  width: 5.84rem;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  font-size: 0.28rem;
  color: #4288ff;
  font-weight: 500;
}

.suggestion-list-item .time {
  margin: 0.08rem 0;
  font-size: 0.24rem;
  color: #666666;
  font-weight: 400;
}

.enterprise {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 0.24rem;
  color: #666;
}

.enterprise .company {
  width: 3.4rem;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.enterprise .view {
  color: #999;
}

.not-data {
  font-size: 0.32rem;
  text-align: center;
  margin: 1rem 0;
  color: #ccc;
}
</style>