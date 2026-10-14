<template>
  <div class="wrapper">
    <div class="indicate" v-for="(item,index) in dataList" :key="index">
      <div class="cash" @click="handleUnfold(item,index)">
        <div class="title">
          <div class="mark"></div>
          <div class="assess">
            {{item.title}}
            <span
              v-if="item.msg=='查看详情' && !item.isSelected"
            >以下政策贵单位已满足条件，但尚未到兑现的时间，需要省市配套资金到位。</span>
          </div>
        </div>
        <div class="count">
          <span>{{item.count}}</span>&nbsp;条
          <img
            src="@/assets/imgs/left.png"
            :class="{selected:item.isSelected , unselected :!item.isSelected}"
            alt
          />
        </div>
      </div>

      <div class="content" v-if="item.isSelected">
        <div class="explain" v-if="item.msg=='查看详情'">
          <div class="state">
            <i class="iconfont icon-gantanhao"></i>说明：以下政策贵单位已满足条件，但尚未到约定到兑现时间，需要省市配套资金到位。
          </div>
        </div>
        <div class="content-item" v-for="(items,index) in item.list" :key="index">
          <i class="iconfont icon-jianglijilu"></i>
          <div class="info">
            <div class="name" @click="handleCheckPolicy(items.uploadfile)">{{items.rdescription}}</div>
            <div class="award">
              {{items.policycategoryname}}
              <span>
                > {{items.policysubclassname ? items.policysubclassname : items.policyconditions}}
                <span
                  v-if="items.patentname"
                >({{items.patentname}})</span>
              </span>
            </div>
          </div>
          <div
            class="button"
            @click="handleToDetail(item.msg,items.plycomresultdetailid,items.enjoypolicycategory)"
          >{{item.msg}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
import { baseUrl } from "../../network/baseUrl";

export default {
  name: "Indicates",
  props: ["dataList", "showFlag"],
  data() {
    return {
      rotate: false,
      progressList: [
        {
          id: 1,
          title: "政策匹配",
          start: "2021.2.1",
          end: "2021.2.3",
          status: "已处理"
        },
        {
          id: 2,
          title: "奖励兑现",
          start: "2021.2.1",
          end: "",
          status: "待处理"
        },
        {
          id: 3,
          title: "凭据上传",
          start: "",
          end: "",
          status: "待处理"
        }
      ]
    };
  },
  created() {
    console.log(this.showFlag);
  },
  methods: {
    handleUnfold(item, index) {
      // this.dataList[index].isSelected = !this.dataList[index].isSelected;
      if (this.dataList[index].isSelected) {
        this.rotate = false;
        this.dataList[index].isSelected = false;
      } else {
        this.rotate = true;
        this.dataList[index].isSelected = true;
      }
    },

    handleToDetail(msg, id, category) {
      console.log(msg, id, category);
      this.$store.commit("SaveCategory", category);
      if (!this.showFlag) {
        Toast("暂无权限查看");
        return;
      }
      if (msg == "查看兑现明细") {
        // this.$router.push({
        //   path: "/progress",
        //   query: {
        //     isShow: true,
        //     dataList: this.progressList
        //   }
        // });
        this.$router.push({
          path: "/payment",
          query: {
            id
          }
        });
      } else if (msg == "查看补充材料") {
        this.$router.push({
          path: "/policy",
          query: {
            id
          }
        });
      } else if (msg == "查看现存差距") {
        this.$router.push({
          path: "/match",
          query: {
            id
          }
        });
      } else if (msg == "查看详情") {
        this.$router.push({
          path: "/policy",
          query: {
            isShow: true,
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
.unselected {
  transition: all 0.2s;
}

.selected {
  transform: rotate(-90deg);
  transition: all 0.2s;
}

.wrapper {
  height: 100%;
  /* padding-bottom: 0.98rem; */
  background-color: #f7f9fb;
}

.indicate {
  margin-bottom: 0.24rem;
  /* padding: 0 16px; */
  /* height: 500px; */
  background-color: #fff;
}

.indicate:last-child {
  margin-bottom: 0;
}

.indicate .cash {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.24rem 16px;
  /* height: 0.92rem; */
  /* background-color: pink; */
}

.cash .title {
  display: flex;
  align-items: center;
}

.cash .mark {
  margin-right: 0.24rem;
  width: 0.1rem;
  height: 0.32rem;
  background-color: #3d91ff;
}

.cash .assess {
  display: flex;
  flex-direction: column;
  font-size: 0.3rem;
  font-weight: bold;
}

.assess > span {
  font-size: 0.2rem;
  font-weight: normal;
  color: #cdb37e;
}

.cash .count {
  display: flex;
  align-items: center;
  font-size: 0.3rem;
}

.count > span {
  color: #fc4348;
}

.count img {
  margin-left: 0.1rem;
  width: 0.3rem;
  height: 0.3rem;
}

.count .iconfont {
  color: #bbbbbb;
}

.content {
  /* display: none; */
  padding: 12px 16px;
}

.content .content-item {
  display: flex;
  align-items: center;
  margin-bottom: 0.24rem;
  padding: 0.3rem;
  /* height: 2.4rem; */
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.content-item .iconfont {
  font-size: 0.48rem;
}

.content-item .info {
  margin-left: 0.34rem;
}

.content-item .button {
  margin-left: 0.1rem;
  width: 2.4rem;
  height: 0.64rem;
  line-height: 0.64rem;
  border-radius: 3px;
  background-color: #4288ff;
  font-size: 0.24rem;
  text-align: center;
  color: #fff;
}

.info > .name {
  margin-bottom: 0.1rem;
  width: 3.6rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 0.3rem;
  /* font-weight: bold; */
  color: #4a8dff;
}

.info > .award {
  width: 3.6rem;
  font-size: 0.26rem;
  color: #666666;
}

.award > span {
  color: #a0a0a0;
}

.explain {
  margin-bottom: 0.24rem;
}

.explain .state {
  display: flex;
  align-items: center;
  padding: 0.24rem 0.28rem;
  background-color: #faf7f1;
  border-radius: 6px;
  font-size: 0.28rem;
  color: #ceb47b;
}

.state .iconfont {
  margin-right: 0.16rem;
  font-size: 0.32rem;
  color: #ceb47b;
}
</style>
