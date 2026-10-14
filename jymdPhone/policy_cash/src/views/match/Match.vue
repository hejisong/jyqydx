<template>
  <div class="match">
    <Head :show="true" title="现存差距详情" />

    <div class="module">
      <div class="difference">
        <img src="@/assets/imgs/statistics.png" alt />
        当前差距
      </div>
      <!-- <div class="income">
        <div>
          1.营业收入达到
          <span class="num">1000</span> 万元
        </div>
        <div>
          企业当前营业收入
          <span class="num">800万元</span>
        </div>
      </div>-->
      <div class="income">
        <div>
          实缴到位注册资本达
          <div>
            <span class="num">1000万元—1亿元</span>（不含1亿元）
          </div>
        </div>
        <div>
          当前实缴注册资本
          <span class="num capital">950万元</span>
        </div>
      </div>
    </div>
    <div class="module">
      <div class="difference">
        <!-- <img src="@/assets/imgs/suited.png" alt /> -->
        政策信息
      </div>
      <list :list="plyfileEntityList" :policyInfo="policyInfo" />
    </div>
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import List from "../../components/list/List";
import { baseUrl } from "../../network/baseUrl";
import { Toast, Empty } from "vant";

export default {
  name: "Match",
  components: {
    Head,
    List
  },
  data() {
    return {
      policyInfo: {},
      plyfileEntityList: []
    };
  },
  created() {
    const { id } = this.$route.query;
    console.log(id);
    this.getMatchData(id);
  },
  methods: {
    async getMatchData(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/show",
        {
          params: {
            plycomresultdetailid: id
          }
        }
      );

      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        let {
          rdescription,
          policycategoryname,
          policyconditions,
          policyname,
          patentname
        } = resultObj.plycomresultdetailEntity;
        this.policyInfo = {
          rdescription,
          policycategoryname,
          policyconditions,
          policyname,
          patentname
        };
        this.plyfileEntityList = resultObj.plyfileEntityList;
      }
      console.log("获取匹配详情数据", resultData);
    }
  }
};
</script>

<style scoped>
.match {
  padding-bottom: 0.98rem;
  /* background-color: #f7f9fb; */
}

.module {
  margin-bottom: 0.1rem;
  padding: 12px 16px;
  background: #fff;
}

.module:last-child {
  margin-bottom: 0;
}

.module > .difference {
  display: flex;
  align-items: center;
  margin-bottom: 0.2rem;
  font-size: 0.32rem;
  font-weight: bold;
}

.difference > img {
  margin-right: 0.24rem;
  width: 0.4rem;
  height: 0.4rem;
}

.module > .income {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.2rem;
  padding: 0.3rem 0.2rem;
  border: 1px solid #3d91ff;
  border-radius: 6px;
  font-size: 0.28rem;
}

.module > .income:last-child {
  margin-bottom: 0;
}

.income > div:nth-child(2) {
  width: 1.9rem;
  word-wrap: break-word;
}

.income .num {
  color: #3d91ff;
}

.income .capital {
  color: #fc011a;
}
</style>
