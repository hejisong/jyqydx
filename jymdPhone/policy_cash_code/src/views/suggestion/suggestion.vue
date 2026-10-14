<template>
  <div class="suggestion">
    <van-nav-bar      
      :title="type !='complaint'?'意见和建议':'投诉'"      
    />
    <!-- 悬浮按钮 -->
    <div class="suggestion-btn"  @click="handleToPublish">
      <img src="../../assets/imgs/wyfb@3x.png" alt />
      <span>我要发表</span>
    </div>

    <div class="suggestion-bg">
      <img src="../../assets/imgs/bg@3x.png" alt="">
    </div>
    <div class="suggestion-search">
        <van-search v-model="searchValue" placeholder="请输入关键词进行搜索" />
      </div>
    <div class="suggestion-list" style="margin-top:0.4rem">
      <template v-if="suggestionList && suggestionList.length > 0">
        <div
          class="suggestion-list-item"
          v-for="item in suggestionList"
          :key="item.id"
          @click="handleToDetail(item)"
        >
          <div class="title">{{item.title}}</div>
          <div class="time"><span>{{item.createtime ? (item.createtime.split(' ')[0]):''}}</span>
          <span>
            <van-tag :color="item.state == '0'?'#ffe1e1':''" :text-color="item.state == '0'?'gray':''" :type="item.state == '0'?'':(item.state == '1'?'warning':'primary')">
              {{ item.state == '0'?'未处理':(item.state == '1'?'处理中':'已处理') }}</van-tag>
            </span>
          </div>
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
      companyid: !sessionStorage.getItem("companyInfo")?'':JSON.parse(sessionStorage.getItem("companyInfo")).companyid,
      type:'',
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
    this.type = this.$route.query.type
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
    onClickLeft(){
      this.$router.go(-1)
    },
    // 获取意见列表数据
    async getSuggestionData(searchValue) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let data2 = JSON.parse(sessionStorage.getItem("companyInfo"))
      let user = JSON.parse(sessionStorage.getItem("userInfo"))
      let resultData = await this.$axios.get(
        baseUrl + "/interface/suggestions/list",
        {
          params: {
            title: searchValue,
            // mobile:user.phone,
            companyid:data2?data2.companyid:'',//
            infotype:this.type != 'complaint' ? '1':'2'
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
    // 追问
    handleToDetail(item) {
      this.$router.push({
        path: "/publish",
        query: {
          id: item.id,
          flag:"edit",
          type:this.type
        }
      });
    },
    // 新增发表
    handleToPublish() {
      let data = JSON.parse(sessionStorage.getItem("companyInfo"))
      if(!data){
        Toast('您还没有企业，无法操作！')
        return
      }
      let type = this.type ? 'com':''
      this.$router.push({
        path: "/publish",
        query: {
          type:type
        }
      });
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
body,html{
  width: 100%;
  height: 100%;
}
.suggestion {
  
  padding-bottom: 0.98rem;
  width: 100%;
  height: 100%;
  
  box-sizing: border-box;
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
  /* position: absolute;top: 0;left: 0; */
  width: 100%;
  height: 4rem;
  /* background-size:cover; */
  /* z-index: -1; */
  /* background-image: url('../../assets/imgs/bg@3x.png'); */
}

.suggestion-bg img{
  width: 100%;
  height: 100%;
  z-index: -1
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
  width: 91%;
  position: absolute;
  left: 4.5%;
  top: 80px;
  right: 4.5%;
  /* margin: 0.3rem auto 0.2rem auto; */
  height: 1.34rem;
}

.suggestion-search >>> .van-search {
  background: none;
  padding: 10px 0;
}

.van-search__content {
  border-radius: 4px;
}

.van-search .van-cell {
  padding: 0.2rem 0.16rem 0.2rem 0;
}

.suggestion-list {
  box-sizing: border-box;
  position: absolute;
  padding: 0.4rem;
  background-color: #fff;
  border-radius: 6px;
  width: 91%;
  left: 4.5%;
  top: 135px;
  right: 4.5%;
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
  display: flex;
  justify-content: space-between;
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