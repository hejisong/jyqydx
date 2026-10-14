<template>
  <div class="aptitude-list">
    <robot-btn />
    <Head :show="true" title="智能匹配详情" />
    <p style="margin:0.2rem 0;font-size:0.3rem;font-weight:bold">政策信息</p>
    <div class="policy-item">
      <p class="policy">政策文件：{{policyInfo.policyname}}</p>
      <p class="policy">政策大类：{{policyInfo.policycategoryname}}</p>
      <p class="policy" v-if="policyInfo.policysubclassname">政策小类：{{policyInfo.policysubclassname}}</p>
      <p class="policy">政策条件：{{policyInfo.policyconditions}}</p>
      <p class="policy">政策奖励：{{policyInfo.rdescription}}</p>
      <p class="policy" v-if="policyInfo.patentname">专利名称：{{policyInfo.patentname}}</p>
      <p class="policy" >申报条件：{{policyInfo.policydeclar}}</p>
      
    </div>
    <p style="margin:0.2rem 0;font-size:0.3rem;font-weight:bold" v-if="isExhibition">资料信息</p>
    <div class="aptitude-item" v-for="(item, index) in list" :key="index">
      <div style="display:flex;align-items:center;">
        <div class="left">
          <div class="serial">{{ index + 1 }}</div>
          <div class="award">
            <div>{{ item.materialname }}</div>
            <!-- <div class="file">TypeScript.pdf</div> -->
          </div>
        </div>                
      </div>

      <div class="uploadImage">
        <div v-for="(item2,index2) in item.uploadfiles" :key="index2">
          <ul  class="img-content" v-if="item2.fileext == 'jpg' || item2.fileext == 'png' || item2.fileext == 'blob'">
            <img  :src="imgUrl+item2.path" style="width:100%;height:100%;" alt @click="handlePreview(item.uploadfiles)" />
          </ul>
          <ul v-else>
            <p style="font-size:12px;text-decoration: underline;color:#1989fa" @click="downloadFile(item2.path)">{{ item2.filename }}</p>
          </ul>
        </div>
      </div>
      <p style="margin:0;padding:0" v-if="!noShow" @click="getId(item.plyfileid)">
        <van-uploader multiple :max-count="6" :after-read="afterRead" />
      </p>
      
      <!-- <div v-if="fileList.length > 0">
        <van-uploader v-model="fileList" multiple />
      </div>
      <img v-else src="@/assets/imgs/upload.png" alt @click="handleUpload" /> -->
      
      
    </div>
    <div class="isChecked"><van-checkbox v-model="checked">线下提供纸质材料</van-checkbox></div>
    <div class="operate-btn" >
      <div class="btn storage" @click="storage">暂&nbsp;&nbsp;存</div>
      <div class="btn submit" @click="submit">提&nbsp;&nbsp;交</div>
    </div>

    <!-- 上传图片弹窗 -->
    <van-overlay :show="show" @click="show = false">
      <div class="wrapper" @click.stop>
        <div class="block">
          <div class="chooseimg">
            选择图片
            <img src="../../assets/imgs/close.png" alt @click="show = false" />
          </div>
          <van-uploader v-model="fileList" multiple :max-count="6" :after-read="afterRead" />
          <div class="bound" @click="handleSubmit">提&nbsp;&nbsp;交</div>
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>
import { baseUrl,imgUrl } from "../../network/baseUrl";
import RobotBtn from "../../components/robot/RobotBtn";
import Head from "../../components/head/Head";
import {compressImage} from '../../utils/as'
import { Overlay, Uploader, ImagePreview, Toast } from "vant";
import Exif from "exif-js";

export default {
    name: "policyDetail",
    components: {
        RobotBtn,
        Head
    },
    data() {
        return {
        checked:false,
        policyInfo:{},
        list:[],
        show: false,
        ids:'',
        fileList: [
            // "https://img01.yzcdn.cn/vant/leaf.jpg",
            // "https://img01.yzcdn.cn/vant/leaf.jpg",
            // "https://img01.yzcdn.cn/vant/leaf.jpg"
        ],
        fileId: [],
        detailId: ""
        };
    },
    mounted(){
        let id = this.$route.query.id
        this.getListData(id)
        let box = document.getElementById('tab-bar')
        box.style.visibility="hidden";
    },
    destroyed(){
        let box = document.getElementById('tab-bar')
        box.style.visibility="visible";
        Toast.clear()
    },
    computed: {
        isExhibition() {
        return this.list.length > 0;
        },
        isShowBtn() {
        return (
            this.$store.state.category == "2" || this.$store.state.category == "3"
        );
        }
    },
    methods: {
        async delFile(val){
            Toast.loading({
                message: "加载中...",
                forbidClick: true,
                duration:0,
            });
            console.log(val)
            let resultData = await this.$axios.post(
                baseUrl + '/interface/policyfile/deletefujian/'+val.id
            );
            console.log(resultData)
            const { result, resultObj } = resultData.data;
            if (result) {
                setTimeout(()=>{
                    let id = this.$route.query.id
                    this.getListData(id)
                },1000)
            }
        },
        async getListData(id) {
            Toast.loading({
                message: "加载中...",
                forbidClick: true,
                duration:0,
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
                    policysubclassname,
                    policyInfo,
                    policydeclar
                } = resultObj.plycomresultdetailEntity;
                    this.policyInfo = {
                    rdescription,
                    policycategoryname,
                    policyconditions,
                    policyname,
                    policysubclassname,
                    policyInfo,
                    policydeclar
                };
                this.list = resultObj.plyfileEntityList;
                this.getList()
            }
        },
        getList(){
            if(this.list.length>0){
                for(let i = 0;i<this.list.length;i++){
                if(this.list[i].uploadfiles.length>0){
                    for(let j= 0 ;j<this.list[i].uploadfiles.length;j++){
                    this.list[i].uploadfiles[j].path = baseUrl+this.list[i].uploadfiles[j].path
                    }
                }
                }
            }
            console.log(this.list)
        },
        getId(val){
          console.log(val,'=========>')
          this.ids = val
        },
        handlePreview(val) {
          console.log(val)
          let arr = []
          for(let i = 0;i<val.length;i++){
              arr.push(val[i].path)
          }
          ImagePreview(arr);
        },
        // 点击文件上传
        handleUpload() {
          this.show = true;
          console.log("文件上传");
        },
        async afterRead(file) {
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
            this.entData(data.id)
          } else {
            Toast.clear();
            Toast("图片上传服务器失败，请重新上传!");
          }
        },
        // 暂存
        async entData(val){
          let data2  = JSON.parse(sessionStorage.getItem('companyInfo'))
          if(data2.islegalperson == '2'){
            // Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
            return
          }
          let data = {
              fujianlistid:this.ids,
              plycomresultid: this.$route.query.id,
              upfileids:val
          }
          console.log(data)
          let resultData = await this.$axios.get(
              baseUrl + "/interface/policyfile/saveuploadfiles",{
              params: data
              }
          );
          console.log(resultData)
          Toast.loading({
              message: "加载中...",
              forbidClick: true,
              duration:0,
          });
          const { result, resultObj } = resultData.data;
          if(result){
              Toast.clear()
              let id = this.$route.query.id
              this.getListData(id)
          }
        },
        async submit(){
          let data2  = JSON.parse(sessionStorage.getItem('companyInfo'))
          if(data2.islegalperson == '2'){
            Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
            return
          }
          if(!this.checked){
            if(this.list.length>0){
              for(let i = 0;i<this.list.length;i++){
                if(this.list[i].uploadfiles.length<1){
                  Toast("请把各项材料补充完整！");
                  return
                }
              }
            }
          }
            Toast.loading({
                message: "加载中...",
                forbidClick: true,
                duration:0,
            });
            let data = {
                searchId: this.$route.query.id,
                issubmitoffline:this.checked?'1':'0'
            }
            console.log('提交------》',data)      
            let resultData = await this.$axios.get(
                baseUrl + "/interface/policyfile/savefilesinasset",{
                params: data
                }
            );
            console.log(resultData)
            const { result, resultObj,errorMsg } = resultData.data;
            if(result){
                Toast("提交成功！");
                setTimeout(()=>{
                    Toast.clear();
                    this.$router.replace({name:'newIndex'})
                },1500)
            }else{
                Toast(errorMsg);
            }
            setTimeout(()=>{
                Toast.clear();
            },1500)
        },
        storage(){
          let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
          if(data.islegalperson == '2'){
            Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
            return
          }
          Toast.loading({
              message: "加载中...",
              forbidClick: true,
              duration:0,
          });
          setTimeout(()=>{
              Toast.clear();
              Toast("暂存成功！");
          },1000)
        },
        // 点击提交
        handleSubmit() {
          this.show = false;
        }
    }
};
</script>
<style>
.van-overlay {
  z-index: 10;
}
</style>

<style scoped>
.aptitude-list {
  padding-bottom: 0.98rem;
  padding-right: 0.25rem;
  padding-left:0.25rem;
  /* border-bottom: 10px solid #f7f9fb; */
}

.aptitude-list:last-child {
  border-bottom: none;
}

.aptitude-list .aptitude-item {
  display: flex;
  flex-direction: column;
  /* justify-content: space-between;
  align-items: center; */
  margin-bottom: 0.24rem;
  padding: 0.16rem 0.28rem;
  background-color: #f6f9fe;
  border-radius: 8px;
  box-sizing: border-box;
}

.aptitude-item .left {
  display: flex;
  margin-right: 0.65rem;
}

.aptitude-item img {
  width: 1.1rem;
  height: 1.1rem;
}

.policy-item {
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

.left .serial {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.26rem;
  margin-right: 0.2rem;
  width: 0.4rem;
  height: 0.4rem;
  background-color: #3d91ff;
  border-radius: 100%;
  color: #fff;
}

.uploadImage {
  display: flex;
  flex-wrap: wrap;
  margin-top: 0.2rem;
}

.uploadImage .img-content {
  display: flex;
  flex-wrap: wrap;
  width: 1.5rem;
  position: relative;
  height: 1.5rem;
  margin-right: 0.3rem;
  margin-bottom: 0.2rem;
}

.left .award {
  margin-left: 0.1rem;
  text-align: justify;
  width: 3.8rem;
  word-wrap: break-word;
  font-size: 0.29rem;
  /* font-weight: bold; */
  color: #666;
}

.file {
  margin-top: 0.16rem;
  font-style: oblique;
  font-size: 0.24rem;
  font-weight: lighter;
  text-decoration: underline;
}
.isChecked{
  font-size: 12px;
}
.right {
  width: 60px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  font-size: 0.28rem;
  background-color: #56bfa0;
  color: #fff;
}

/* 选择图片弹窗 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  padding: 0.2rem;
  width: 6rem;
  background-color: #fff;
  border-radius: 4px;
}

.block > .chooseimg {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.36rem;
  font-size: 0.3rem;
}

.chooseimg img {
  width: 0.4rem;
  height: 0.4rem;
}

.bound {
  margin-top: 0.4rem;
  width: 100%;
  height: 0.8rem;
  line-height: 0.8rem;
  background-color: #4288ff;
  font-size: 0.28rem;
  color: #fff;
  text-align: center;
  border-radius: 6px;
}

.operate-btn {
  display: flex;
  position: fixed;
  left: 0;
  bottom: 0rem;
  width: 100%;
  height: 0.9rem;
}

.operate-btn .btn {
  flex: 1;
  line-height: 0.9rem;
  text-align: center;
  font-size: 0.32rem;
  color: #fff;
  border-right: 1px solid #fff;
}

.operate-btn .btn:last-child {
  border-right: none;
}

.storage {
  background-color: #4288ff;
}

.submit {
  background-color: #4288ff;
}
</style>
