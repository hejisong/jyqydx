<template>
  <div class="aptitude-list">
    <!-- <p style="margin:0.2rem 0;font-size:0.3rem;font-weight:bold">政策信息</p> -->
    <div class="policy-item">
      <p class="policy">政策文件：{{policyInfo.policyname}}</p>
      <p class="policy">政策大类：{{policyInfo.policycategoryname}}</p>
      <p class="policy" v-if="policyInfo.policysubclassname">政策小类：{{policyInfo.policysubclassname}}</p>
      <p class="policy">政策条件：{{policyInfo.policyconditions}}</p>
      <p class="policy">政策奖励：{{policyInfo.rdescription}}</p>
      <p class="policy" v-if="policyInfo.patentname">专利名称：{{policyInfo.patentname}}</p>
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

        <img v-if="item.status == '1'" src="@/assets/imgs/upload.png" alt />
        <img v-else src="@/assets/imgs/matched.png" alt />
      </div>

      <div class="uploadImage">
        <div class="img-content" v-for="(item,index) in fileList" :key="index">
          <img :src="item" style="width:100%;height:100%;" alt @click="handlePreview" />
        </div>
      </div>

      <!-- <div v-if="fileList.length > 0">
        <van-uploader v-model="fileList" multiple />
      </div>-->
      <!-- <img v-else src="@/assets/imgs/upload.png" alt @click="handleUpload" /> -->
    </div>

    <!-- <div class="operate-btn" v-if="isShowBtn">
      <div class="btn storage">暂&nbsp;&nbsp;存</div>
      <div class="btn submit">提&nbsp;&nbsp;交</div>
    </div> -->

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
import { baseUrl } from "../../network/baseUrl";
import { Overlay, Uploader, ImagePreview, Toast } from "vant";
import Exif from "exif-js";

export default {
  name: "List",
  props: ["list", "policyInfo"],

  data() {
    return {
      show: false,
      // fileList: [
      //   "https://img01.yzcdn.cn/vant/leaf.jpg",
      //   "https://img01.yzcdn.cn/vant/leaf.jpg",
      //   "https://img01.yzcdn.cn/vant/leaf.jpg"
      // ],
      fileId: [],
      detailId: ""
    };
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
    handlePreview() {
      ImagePreview(this.fileList);
    },
    // 点击文件上传
    handleUpload() {
      this.show = true;
      console.log("文件上传");
    },
    async afterRead(file) {
      var formData = new FormData(); //构造一个 FormData，把后台需要发送的参数添加
      formData.append("upfile", file.file); //接口需要传的参数
      var uploadFile = formData;
      console.log(uploadFile);
      Toast.loading({
        message: "上传中...",
        forbidClick: true
      });
      let resultData = await this.$axios.post(
        baseUrl + "/common/files",
        uploadFile,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }
      );
      Toast.clear();
      if (resultData.status === 200) {
        console.log("上传图片返回值", resultData);
        let fileInfo = eval("(" + resultData.data + ")");
        this.fileList[this.fileList.length - 1].assetid = fileInfo.id;
        console.log("图片集合", this.fileList);
      } else {
        Toast.clear();
        Toast("图片上传服务器失败，请重新上传");
      }
      this.imgPreview(file); //文件流
    },

    // 压缩图片
    compress(img, Orientation) {
      console.log("拍照----------Orientation", Orientation);

      let canvas = document.createElement("canvas");
      let ctx = canvas.getContext("2d");
      //瓦片canvas
      let tCanvas = document.createElement("canvas");
      let tctx = tCanvas.getContext("2d");
      // let initSize = img.src.length;
      let width = img.width;
      let height = img.height;
      //如果图片大于四百万像素，计算压缩比并将大小压至400万以下
      let ratio;
      if ((ratio = (width * height) / 4000000) > 1) {
        // console.log("大于400万像素");
        ratio = Math.sqrt(ratio);
        width /= ratio;
        height /= ratio;
      } else {
        ratio = 1;
      }
      canvas.width = width;
      canvas.height = height;
      //    铺底色
      ctx.fillStyle = "#fff";
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      //如果图片像素大于100万则使用瓦片绘制
      let count;
      if ((count = (width * height) / 1000000) > 1) {
        // console.log("超过100W像素");
        count = ~~(Math.sqrt(count) + 1); //计算要分成多少块瓦片
        //      计算每块瓦片的宽和高
        let nw = ~~(width / count);
        let nh = ~~(height / count);
        tCanvas.width = nw;
        tCanvas.height = nh;
        for (let i = 0; i < count; i++) {
          for (let j = 0; j < count; j++) {
            tctx.drawImage(
              img,
              i * nw * ratio,
              j * nh * ratio,
              nw * ratio,
              nh * ratio,
              0,
              0,
              nw,
              nh
            );
            ctx.drawImage(tCanvas, i * nw, j * nh, nw, nh);
          }
        }
      } else {
        ctx.drawImage(img, 0, 0, width, height);
      }
      //修复ios上传图片的时候 被旋转的问题
      if (Orientation != "" && Orientation != 1) {
        switch (Orientation) {
          case 6: //需要顺时针（向左）90度旋转
            this.rotateImg(img, "left", canvas);
            break;
          case 8: //需要逆时针（向右）90度旋转
            this.rotateImg(img, "right", canvas);
            break;
          case 3: //需要180度旋转
            this.rotateImg(img, "right", canvas); //转两次
            this.rotateImg(img, "right", canvas);
            break;
        }
      }
      //进行最小压缩
      let ndata = canvas.toDataURL("image/jpeg", 0.1);
      tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;
      return ndata;
    },

    imgPreview(file) {
      let self = this;
      // 看支持不支持FileReader

      if (Array.isArray(file)) {
        file.forEach(item => {
          // let Orientation
          // //去获取拍照时的信息，解决拍出来的照片旋转问题
          // Exif.getData(file.file, function () {
          //   Orientation = Exif.getTag(this, 'Orientation')
          // })
          if (!item.file || !window.FileReader) return;
          if (/^image/.test(item.file.type)) {
            // 创建一个reader
            let reader = new FileReader();
            // 将图片2将转成 base64 格式
            reader.readAsDataURL(item.file);
            // 读取成功后的回调
            reader.onloadend = function() {
              // console.log(this.result);
              let result = this.result;
              let img = new Image();
              img.src = result;
              //判断图片是否大于2M
              if (this.result.length <= 2048 * 1024) {
                self.headerImage = this.result;
                self.postImg(file);
              } else {
                img.onload = function() {
                  let data = self.compress(img);
                  self.headerImage = data;
                  //上传
                  self.postImg(file);
                };
              }
            };
          }
        });
      } else {
        let Orientation;
        //去获取拍照时的信息，解决拍出来的照片旋转问题
        Exif.getData(file.file, function() {
          Orientation = Exif.getTag(this, "Orientation");
          // console.log('拍照----------Orientation',Orientation

          if (!file.file || !window.FileReader) return;
          if (/^image/.test(file.file.type)) {
            // 创建一个reader
            let reader = new FileReader();
            // 将图片2将转成 base64 格式
            reader.readAsDataURL(file.file);
            // 读取成功后的回调
            reader.onloadend = function() {
              // console.log(this.result);
              let result = this.result;
              let img = new Image();
              img.src = result;
              //判断图片是否大于2M
              if (this.result.length <= 2048 * 1024) {
                self.headerImage = this.result;
                self.postImg(file);
              } else {
                img.onload = function() {
                  let data = self.compress(img, Orientation);
                  self.headerImage = data;
                  //上传
                  self.postImg(file);
                };
              }
            };
          }
        });
      }
    },
    // 旋转图片
    rotateImg(img, direction, canvas) {
      //最小与最大旋转方向，图片旋转4次后回到原方向
      const min_step = 0;
      const max_step = 3;
      if (img == null) return;
      //img的高度和宽度不能在img元素隐藏后获取，否则会出错
      let height = img.height;
      let width = img.width;
      let step = 2;
      if (step == null) {
        step = min_step;
      }
      if (direction == "right") {
        step++;
        //旋转到原位置，即超过最大值
        step > max_step && (step = min_step);
      } else {
        step--;
        step < min_step && (step = max_step);
      }
      //旋转角度以弧度值为参数
      let degree = (step * 90 * Math.PI) / 180;
      let ctx = canvas.getContext("2d");
      switch (step) {
        case 0:
          canvas.width = width;
          canvas.height = height;
          ctx.drawImage(img, 0, 0);
          break;
        case 1:
          canvas.width = height;
          canvas.height = width;
          ctx.rotate(degree);
          ctx.drawImage(img, 0, -height);
          break;
        case 2:
          canvas.width = width;
          canvas.height = height;
          ctx.rotate(degree);
          ctx.drawImage(img, -width, -height);
          break;
        case 3:
          canvas.width = height;
          canvas.height = width;
          ctx.rotate(degree);
          ctx.drawImage(img, -width, 0);
          break;
      }
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
  bottom: 0.98rem;
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
