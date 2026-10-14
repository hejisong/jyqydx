<template>
  <div class="table-container">
    <div class="table-item" v-for="(item,index) in tableList" :key="index">
      <div class="top">
        <i class="iconfont icon-jianglijilu"></i>
        <div class="info">
          <div class="title" @click="handleCheckPolicy(item.uploadfile)">{{item.rdescription}}</div>
          <div class="award">
            <div class="incentive">
              <span class="policysubclassname">{{item.policycategoryname}}&nbsp;</span>
              <span
                class="policyconditions"
                v-if="item.policysubclassname"
              >>&nbsp;{{item.policysubclassname}}</span>
            </div>

            <!-- 兑现时间暂时隐藏 -->
            <!-- <span class="time" v-if="item.particularyear">{{item.particularyear}}</span> -->
          </div>
          <div
            style="margin-bottom:0.08rem; font-size:0.26rem; color: #a0a0a0;width:256px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis"
            v-if="item.patentname"
          >{{item.patentname}}</div>
        </div>
      </div>
      <div class="bottom">
        <div class="operation-btn">
          <div
            class="btn"
            @click="checkProgress(item.plycomresultdetailid,item.enjoypolicycategory,item.cashflag)"
          >
            <div class="detail">查看进度</div>
          </div>
          <div class="btn" @click="checkCash(item.enjoypolicycategory,item.plycomresultdetailid)">
            <div class="detail">查看兑现明细</div>
          </div>
        </div>
      </div>
      <img v-if="item.cashflag == 0" src="@/assets/imgs/cash.png" alt />
      <img v-else src="@/assets/imgs/stocks.png" alt />
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";

export default {
  name: "Table",
  props: {
    tableList: {
      type: Array,
      default: []
    }
  },
  data() {
    return {
      progressList: [
        {
          id: 1,
          title: "政策匹配",
          start: "2021-02-01",
          end: "2021-02-03",
          status: "已处理"
        },
        {
          id: 2,
          title: "企业资料补录",
          start: "2021-02-04",
          end: "",
          status: "待处理"
        },
        {
          id: 3,
          title: "部门审批（会签）",
          start: "",
          end: "",
          status: "待处理"
        },
        {
          id: 4,
          title: "奖励兑现",
          start: "",
          end: "",
          status: "待处理"
        },
        {
          id: 5,
          title: "凭据上传",
          start: "",
          end: "",
          status: "待处理"
        }
      ],
      completeList: [
        {
          id: 1,
          title: "政策匹配",
          start: "2021-02-01",
          end: "2021-02-03",
          status: "已处理"
        },
        {
          id: 2,
          title: "奖励兑现",
          start: "2021-02-03",
          end: "2021-02-04",
          status: "已处理"
        },
        {
          id: 3,
          title: "凭据上传",
          start: "2021-02-04",
          end: "",
          status: "未处理"
        }
      ]
    };
  },
  methods: {
    // 查看兑现进度
    checkProgress(id, cashflag) {
      if (cashflag === "1") {
        this.$router.push({
          path: "/progress",
          query: {
            isShow: true,
            id,
            dataList: this.completeList
          }
        });
      } else if (cashflag === "2") {
        this.$router.push({
          path: "/progress",
          query: {
            isShow: false,
            id,
            dataList: this.progressList
          }
        });
      }
    },

    // 查看兑现明细
    checkCash(flag, id) {
      console.log(flag, id);
      if (flag === "1") {
        this.$router.push({
          path: "/payment",
          query: {
            id
          }
        });
      } else if (flag === "2") {
        this.$router.push({
          path: "/policy",
          query: {
            id
          }
        });
      }
    },

    // 查看政策原文
    handleCheckPolicy(uploadfile) {
      console.log("查看政策原文", uploadfile);
      if (uploadfile && uploadfile.path) {
        const { path } = uploadfile;
        this.$router.push({
          path: "/browse",
          query: {
            url: path
          }
        });
      } else {
        Toast("暂无政策原文!");
      }
    }
  }
};
</script>

<style scoped>
.table-container {
  padding: 0 16px;
  padding-bottom: 0.98rem;
}

.table-container .table-item {
  position: relative;
  display: flex;
  flex-direction: column;
  margin-bottom: 0.24rem;
  padding: 0.12rem 0.4rem;
  height: 2.4rem;
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.table-item img {
  position: absolute;
  top: 0;
  right: 0;
  width: 0.8rem;
  height: 0.8rem;
}

.table-item .top {
  display: flex;
  flex: 1;
  align-items: center;
  border-bottom: 1.4px solid #f2f2f2;
}

.top .iconfont {
  font-size: 0.6rem;
}

.info {
  margin-left: 0.34rem;
}

.info > .title {
  width: 5rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 0.3rem;
  /* font-weight: bold; */
  color: #4a8dff;
}

.info > .award {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 0.28rem;
  color: #666666;
}

.award > .incentive {
  display: flex;
  align-items: center;
  font-size: 0.26rem;
}

.incentive .policysubclassname {
  display: inline-block;
  width: 1.5;
  white-space: nowrap;
  text-overflow: ellipsis;
  /* overflow: hidden; */
}

.incentive .policyconditions {
  display: inline-block;
  width: 2rem;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.award > .time {
  font-size: 0.26rem;
}

.award span {
  color: #a0a0a0;
}

.table-item .bottom {
  display: flex;
  flex: 1;
  align-items: center;
}

.bottom .operation-btn {
  display: flex;
  height: 0.88rem;
  width: 100%;
}

.operation-btn .btn {
  flex: 1;
  padding: 0 0.32rem;
  font-size: 0.26rem;
  color: #fff;
  border-radius: 2px;
  border-right: 1px solid #f2f2f2;
}

.operation-btn .btn:last-child {
  border-right: none;
}

.detail {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 0.15rem;
  height: 75%;
  border-radius: 3px;
  background-color: #4288ff;
}
</style>
