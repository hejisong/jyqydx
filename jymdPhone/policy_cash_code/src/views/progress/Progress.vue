<template>
  <div class="progress">
    <Head :show="true" title="兑现进度" />

    <robot-btn />
    <p style="margin-left:16px;font-size:0.3rem;font-weight:bold">政策信息</p>
    <div class="policy-item">
      <p class="policy">政策文件：{{policyInfo.policyname}}</p>
      <p class="policy">政策大类：{{policyInfo.policycategoryname}}</p>
      <p class="policy" v-if="policyInfo.policysubclassname">政策小类：{{policyInfo.policysubclassname}}</p>
      <p class="policy">政策条件：{{policyInfo.policyconditions}}</p>
      <p class="policy">政策奖励：{{policyInfo.rdescription}}</p>
      <p class="policy">申报条件：{{policyInfo.policydeclar}}</p>
      <p class="policy" v-if="policyInfo.patentname">专利名称：{{policyInfo.patentname}}</p>
    </div>

    <!-- <div class="exclusive" v-if="isShow=='true'">
      <img src="@/assets/imgs/exclusive.png" alt />
      <div class="direct">专属账户直通车</div>
    </div> -->

    <div class="g_con">
      <div class="expressRecord">
        <div class="expressRecord-single-close" v-for="(item,index) in dataList" :key="index">
          <!-- 左边子容器 -->
          <div class="expressRecord-single-noReach-online-top-close">
            <!-- 正在进行的时间轴上半个时间线 -->
            <div
              class="online-top-closing"
              :style="{ 'background':index == 0 ? '#fff' :'#dfebff' }"
            ></div>
            <!-- 正在进行的时间轴点 -->
            <div
              class="dot-closing"
              :style="{'borderColor':item.processstate == '1' ? '#3f93ff' :'#A9A9A9' }"
            ></div>
            <!-- 正在进行的时间轴下半个时间线 -->
            <div
              class="online-bottom"
              :style="{ 'background':index == dataList.length-1 ? '#fff' :'#dfebff' }"
            ></div>
          </div>

          <!-- 右边子容器 -->
          <div
            style="margin-bottom:0.15rem"
            :style="{'background':item.processstate == '1' ? '#fff' :'#FBFBFB','boxShadow':item.processstate == '1' ? '0px 0px 8px rgba(0, 0, 0, 0.1)' : '' }"
          >
          <div class="expressRecord-text">
            <div class="content">
              <div class="upload">
                 <div
                class="title"
                :style="{'color':item.processstate == '2' ? '#3f93ff':(item.processstate == '0'?'#ACACAC':'#07c160')}"
                >{{item.flowname}}</div>
                <div class="title" v-if="item.flowname == '凭据上传' && item.processstate == '1' && islegalperson != '' && islegalperson != '2'"><van-button size="mini" type="info" @click="uploadFile(item)">上传</van-button></div>
                <div class="title" v-if="item.flowname == '企业资料补录' && (item.processstate == '1' || item.processstate == '2')"><van-button size="mini" type="info" @click="linkFile(item.processstate)">{{ item.processstate == '1'?'补录':'查看' }}</van-button></div>
              </div>
              <div
                class="time"
                :style="{'color':item.processstate == '1' ? '#51c0a0' :'#898989' }"
              >{{ getItem(item) }}</div>
              <!-- <div
                class="time"
                :style="{'color':item.processstate == '1' ? '#e98c82' :'#898989' }"
              >结束时间：{{item.updatetime}}</div> -->
            </div>
            <div
              class="status"
              :style="{'color':item.processstate == '2' ? '#3f93ff':(item.processstate == '0'?'#ACACAC':'#07c160') ,'background':item.processstate == '1' ? '#F6F9FE' : '#fff'}"
            >{{item.processstate == '0'?'待处理':(item.processstate == '1'?'处理中':'已处理')}}</div>
          </div>
          <div class="imgLists" v-if="item.flowname == '凭据上传' ">
            <img @click="imgShow = true" v-for="item in images" :key="item" :src="item" alt="">
          </div>
          <div class="detail" v-if="item.flowauditinfos.length>0">
            <ul v-for="(item3,index3) in item.flowauditinfos" :key="index3" :style="'display:'+(item.isShow?'none':'inherit')">
              <li>节点名称：<span class="typeColor">{{ item3.deptname }}</span></li>
              <li>审核状态：<span class="typeColor">{{ getState(item3) }}</span></li>
              <!-- <li>&nbsp;&nbsp;&nbsp;审批人：<span class="typeColor">{{ item3.auditpername }}</span></li> -->
              <!-- <li>审批金额：<span class="typeColor">{{ item3.auditamount }}</span></li> -->
              <li>审批结果：<span class="typeColor">{{ item3.auditstate == '1'?'通过':(item3.auditstate == '2' ?'驳回':(item3.auditstate == '3'?'退回':'')) }}</span></li>
              <li>审批意见：<span class="typeColor">{{ item3.auditidea }}</span></li>
              <li>审批时间：<span class="typeColor">{{ item3.audittime }}</span></li>
            </ul>
          </div>
          <p v-if="item.flowauditinfos.length>0" class="showDetail"><van-icon @click="isShowClick(index)" :name="!item.isShow?'arrow-up':'arrow-down'" /></p>
        </div>
          <!-- 相对父级容器绝对定位的日期 -->
          <!-- <div class="expressRecord-dating">
            <div class="expressRecord-date-text">昨天</div>
            <div class="expressRecord-date-time">20:39</div>
          </div>-->
        </div>
      </div>
    </div>
    <van-dialog v-model="show" title="凭据上传" :confirm-button-text='"提交"' @confirm='submitFile' show-cancel-button>
      <div class="imgList">
        <van-uploader  v-model="fileList" @delete='deletefile' accept="image/*" multiple :after-read="afterRead" :max-count="countMax" />
      </div>
    </van-dialog>
    <van-image-preview v-model="imgShow" :images="images" />
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import RobotBtn from "../../components/robot/RobotBtn";
import {compressImage} from '../../utils/as'
import { baseUrl,imgUrl } from "../../network/baseUrl";
import { Step, Steps, Toast } from "vant";

export default {
  name: "Progress",
  components: {
    Head,
    RobotBtn
  },
  data() {
    return {
      showDetail:false,
      dataList: [],
      islegalperson:'',
      applyid:'',
      countMax: 100,
      imgShow:false,
      images: [],
      fileList: [],
      nameList: [],
      idList: [],
      auditid:'',
      fileArr: [],
      show:false,
      isShow: false,
      policyInfo: {}
    };
  },
  created() {
    const { dataList, isShow, id } = this.$route.query;
    let { islegalperson } = JSON.parse(sessionStorage.getItem("companyInfo"))
    this.islegalperson = islegalperson
    // this.dataList = dataList
    this.isShow = isShow;
    this.getPolicyData(id);
    this.getData(id)
  },
  methods: {
    linkFile(val){
      const { dataList, isShow, id } = this.$route.query;
      if(this.islegalperson == '2' && this.islegalperson && val == '1'){
        Toast('暂无权限，请到企业授权界面申请授权后再进行操作！')
        return
      }
      this.$router.push({
        path: "/policy",
        query: {
          id,
          showBtn:false,
          showBtn:val == '1'?true:false,
          noShow:val == '1'?false:true
        }
      });
    },
    getItem(name){
      if(name.flowname == '政策匹配' && name.processstate != '0'){
        return '匹配时间：'+name.createtime
      }else if(name.flowname == '企业资料补录' && name.processstate != '0'){
        return '补录时间：'+name.createtime
      }else if(name.flowname == '部门审批(会签)' && name.processstate != '0'){
        return '审批时间：'+name.createtime
      }else if(name.flowname == '凭据上传' && name.processstate != '0'){
        return '上传时间：'+name.createtime
      }else if(name.flowname == '奖励兑现' && name.processstate != '0'){
        return '兑现奖励时间：'+name.createtime
      }
    },
    getState(item3){
      if(item3.auditstate == '0'){
        return item3.deptname + '中'
      }else if(item3.auditstate == '1'){
        return item3.deptname + '通过'
      }else if(item3.auditstate == '2'){
        return item3.deptname + '驳回'
      }else{
        return item3.deptname + '退回'
      }      
    },
    deletefile(file,fileList){
      this.nameList.splice(fileList.index, 1)
      this.idList.splice(fileList.index, 1)
    },
    async submitFile(){
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      const { dataList, isShow, id } = this.$route.query;
      let nameList = []
      let idList= []
      console.log(this.fileList)
      let data = {
        uploadfilename:this.nameList.join(','),
        uploadfileid:this.idList.join(','),
        auditid:this.auditid,
        applyid:this.applyid,
        plycomresultdetailid:id
      }
      console.log(data)
      let resultData = await this.$axios.post(
        baseUrl + "/interface/flow/updateCredential",data
      );
      console.log('sub------->',resultData);
      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        Toast("上传成功");
        this.fileList = []
        this.nameList = []
        this.applyid = ''
        this.idList = []
        setTimeout(()=>{
          const { dataList, isShow, id } = this.$route.query;   
          this.getData(id)
        },1000)
      }else{
        Toast("上传失败！");
      }
    },
    afterRead(file) {
      console.log(file)
      let sizes = file.file.size / 1024 / 1024 < 1
      if(!sizes){
        //压缩图片上传
        compressImage(file.content).then(result => {
          console.log('压缩后的结果', result); // result即为压缩后的结果
          console.log('压缩前大小', file.file.size);
          console.log('压缩后大小', result.size);
          this._uploadFile(result)
        })
      }else{
        this._uploadFile(file.file)
      }
      
    },
    async _uploadFile(file){
      Toast.loading({
        message: "上传中...",
        forbidClick: true,
        duration:0
      });
      let params = new FormData();
      params.append("upfile", file);
      let resultData = await this.$axios.post(
        imgUrl + "/common/files",
        params,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }
      );
      console.log(resultData)
      if (resultData.status === 200) {
        let data = eval("(" + resultData.data + ")");
        console.log('上传成功',data)
        this.nameList.push(data.name)
        this.idList.push(data.id)
        Toast.clear();        
        Toast("上传成功");
      } else {
        Toast.clear();
        Toast("图片上传服务器失败，请重新上传!");
      }
    },
    uploadFile(item){
      console.log(item)
      this.auditid = item.auditid
      this.applyid = item.applyid
      this.show = true
    },
    isShowClick(index){
      this.dataList[index].isShow = !this.dataList[index].isShow
      
    },
    async getPolicyData(id) {
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

      console.log(resultData);

      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        this.policyInfo = resultObj.plycomresultdetailEntity;
      }
    },
    async getData(id) {
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/plycomresultdetail/planlist",
        {
          params: {
            plycomresultdetailid: id
          }
        }
      );

      console.log('planlist------->',resultData);

      const { result, resultObj } = resultData.data;
      if (result) {
        Toast.clear();
        let arr = []
        this.images = []
        for(let i = 0;i<resultObj.length;i++){
          resultObj[i].isShow = false
          arr.push(resultObj[i])
          if(resultObj[i].flowname == '凭据上传' && resultObj[i].uploadfileid){
            //  this.images = ""+resultObj[i].uploadfileid.split(',')
             let idlist = resultObj[i].uploadfileid.split(',')
             let typeList = resultObj[i].uploadfilename.split(',')
             for(let j = 0;j<idlist.length;j++){
               let str = imgUrl+'/doc/upload_'+idlist[j]+'.'+typeList[j].split('.').pop()
               this.images.push(str)
             }
             console.log(this.images)
          }
        }
        this.dataList = arr;
       
      }
    },
  }
};
</script>

<style scoped>
.progress {
  width: 100%;
  height: 100%;
  padding-bottom: 0.98rem;
  background-color: #fff;
}
.upload{
  display: flex;
justify-content:space-between;
}
.policy-item {
  margin-left: 16px;
  margin-right: 16px;
  margin-bottom: 0.24rem;
  padding: 0.04rem 0.28rem;
  background-color: #f6f9fe;
  border-radius: 8px;
  font-size: 0.28rem;
  color: #666;
}

.policy-item .policy {
  margin: 0.1rem 0;
}

.exclusive {
  position: relative;
  margin-top: 0.2rem;
  padding: 0 16px;
}

.exclusive > img {
  width: 100%;
  height: 1.1rem;
}

.exclusive > .direct {
  position: absolute;
  top: 38%;
  left: 22%;
  transform: translateY(-40%);
  font-size: 0.3rem;
  color: #fff;
}

.g_con {
  width: 100vw;
  overflow-x: hidden;
}

.topExpress {
  width: 7.1rem;
  height: 1.54rem;
  background: #fff;
  margin: 0.1rem auto;
  display: flex;
  border-radius: 5px;
}

.topExpress-left {
  width: 1rem;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.topExpress-right {
  font-size: 0.26rem;
  color: #333;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  align-items: flex-start;
  padding: 0.2rem 0;
}

.topExpress-right-middle {
  font-size: 0.22rem;
}

.topExpress-right-bottom {
  font-size: 0.2rem;
  color: #666;
}

.expressRecord {
  width: 7rem;
  padding-bottom: 2rem;
  background: #fff;
  margin: 0 auto;
  border-radius: 5px;
}

.expressRecord-getAddress {
  width: 100%;
  font-size: 0.22rem;
  color: #999;
  display: flex;
}

.expressRecord-top {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.imgLists img{
  width: 50px;
  height: 50px;
  margin-left: 0.25rem;
}
.getAddress-icon {
  width: 0.3rem;
  height: 0.3rem;
  border-radius: 50%;
  background: #999;
  font-size: 0.2rem;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 0.8rem;
}

.getAddress-text {
  margin-left: 0.2rem;
}

.noReach-online-top-close {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 1.08rem;
}

.expressRecord-single-close {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  position: relative;
}

.expressRecord-single-noReach-online-top-close {
  display: flex;
  flex-direction: column;
}

.online-top-closing {
  width: 1px;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 0.6rem;
}

.online-top-close {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 1.08rem;
}

.dot-closing {
  width: 0.28rem;
  height: 0.28rem;
  border-radius: 100%;
  margin-left: 0.44rem;
  margin-top: 0.06rem;
  margin-bottom: 0.06rem;
  background: #fff;
  border: 2px solid#3F95FF;
}

.imgList{
  padding: 0.4rem 0.3rem;
}

.dot-close {
  width: 0.3rem;
  height: 0.3rem;
  border-radius: 100%;
  margin-left: 0.9rem;
  margin-top: 0.06rem;
  margin-bottom: 0.06rem;
  background: #fff;
  border: 2px solid #a9a9a9;
}

.online-bottom {
  width: 0.02rem;
  height: 0.8rem;
  background: #dfebff;
  margin-left: 0.6rem;
}

.online-bottom-start {
  width: 0.02rem;
  height: 0.8rem;
  /* background: #999; */
  margin-left: 1.08rem;
}

.expressRecord-text {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 5.8rem;
  /* padding: 12px; */
  box-sizing: border-box;
  /* margin-left: 0.3rem; */
  background: #f6f9fe;
  border-radius: 6px;
  /* box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1); */
}

.content {
  margin: 0.24rem 0.28rem;
  height: 100%;
}

.content .title {
  font-size: 0.32rem;
  font-weight: bold;
  color: #3f93ff;
}

.content .time {
  margin-top: 0.04rem;
  font-size: 0.24rem;
  /* color: #898989; */
}

.time.start {
  color: #51c0a0;
}

.time.end {
  color: #e98c82;
}

.status {
  margin-right: 0.28rem;
  width: 1.2rem;
  height: 0.64rem;
  line-height: 0.64rem;
  text-align: center;
  background: #fff;
  border-radius: 6px;
  font-size: 0.26rem;
  font-weight: 600;
  color: #3f93ff;
}

.expressRecord-statusing {
  font-size: 0.26rem;
  color: #333;
}

.expressRecord-status-addressing {
  font-size: 0.22rem;
  color: #333;
}

.expressRecord-status {
  font-size: 0.26rem;
  color: #999;
}

.expressRecord-status-address {
  font-size: 0.22rem;
  color: #999;
}

.expressRecord-dating {
  position: absolute;
  height: 100%;
  /* top: 0;
  bottom: 0; */
  left: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: #333;
}

.expressRecord-date {
  position: absolute;
  height: 100%;
  /* top: 0;
  bottom: 0; */
  left: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: #999;
}

.expressRecord-date-text {
  font-size: 0.24rem;
}

.expressRecord-date-time {
  font-size: 0.18rem;
}
.detail{
  font-size: 12px;
  padding: 0rem 0.28rem;
  margin: 0
}
.typeColor{
  color: rgb(137, 137, 137);
  margin-left: 0.2rem;
}
.showDetail{
  font-size: 12px;
  width: 100%;
  text-align: center;
  margin: 0;
  background-color: #f6f9fe
}
.detail ul{
  margin-bottom: 0.3rem;
  background-color: #fff;
  padding: 0.15rem;
}
.detail ul li{
  color:rgb(108, 108, 108)
}
</style>
