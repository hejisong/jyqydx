<template>
  <div v-show="isready" class="cpdf" @ievent="ievent">
    <div id="pdftitle" v-show="showNavbar" class="title_style">
      <p v-if="title.length<20" class="title">{{title}}</p>
      <marquee v-else class="title">{{title}}</marquee>
    </div>

    <div id="pinchImg" class="center">
      <!--<v-touch v-on:swipe="onSwipe">-->

      <canvas class="canvasstyle" id="the-canvas" @click="touchScreen"></canvas>
      <!--</v-touch>-->
    </div>

    <div id="pdfbottom" v-if="isPageShow" class="pagestyle">
      <!--<div id="pdfbottom" v-if="isPageShow" v-bind:class="{'pagestyle':showNavbar,'pagestyle_changes':!showNavbar}">-->
      <img
        style="width: 0.5rem;height: 0.6rem;padding: 0.1rem 0 0.1rem 0"
        src="../../assets/imgs/next@2x.png"
        @click="prev"
      />
      <span class="pagenum_style">{{page_num}}/{{page_count}}</span>
      <img
        style="width: 0.5rem;height: 0.6rem;padding: 0.1rem 0 0.1rem 0"
        src="../../assets/imgs/pre@2x.png"
        @click="next"
      />
      <!-- <pdf-bottom :showNavbar="showNavbar"></pdf-bottom> -->
    </div>
  </div>
</template>

<script>
import PDFJS from "../../../node_modules/pdfjs-dist/build/pdf.js";
import { Indicator, Toast } from "mint-ui";
// import PdfBottom from './pdf-bottom.vue'
import Vue from "vue";
import AlloyFinger from "alloyfinger/alloy_finger"; // 手势库
import AlloyFingerVue from "alloyfinger/vue/alloy_finger_vue";
Vue.use(AlloyFingerVue, {
  AlloyFinger
});

export default {
  components: {
    //      PdfBottom,
  },
  name: "PDF",
  props: {
    pdfurl: {
      default: "",
      type: String
    },
    isPageShow:{
      default: false,
      type: Boolean
    },
    title: {
      default: "",
      type: String
    },
    value: {},
    maxscale: {
      default: 2,
      type: Number
    },
    minscale: {
      default: 0.8,
      type: Number
    },
    scale: {
      default: 0.8,
      type: Number
    }
  },
  //['pdfurl', 'value'],
  data() {
    return {
      currflag: 0,
      currwidth: 0,
      currentScale: 1,
      showNavbar: true,
      pdfDoc: null, //pdfjs 生成的对象
      pageNum: 1, //
      pageRendering: false,
      pageNumPending: null,
      compuscale: 2, //放大倍数
      page_num: 0, //当前页数
      page_count: 0, //总页数
      // maxscale: 2, //最大放大倍数
      // minscale: 0.8, //最小放大倍数
      isready: false,
      lastTransX: 0 //上一次translateX值
    };
  },
  methods: {
    close() {
      console.log("关闭了");
      Indicator.close();
    },
    ievent(str) {
      console.log("type===>", str);
    },
    touchScreen() {
      this.showNavbar = !this.showNavbar;
    },
    getCurPAgeNum() {
      return this.page_num;
    },
    renderPage(num) {
      //渲染pdf

      let vm = this;
      this.pageRendering = true;
      let canvas = document.getElementById("the-canvas");

      // Using promise to fetch the page
      this.pdfDoc.getPage(num).then(function(page) {
        var viewport = page.getViewport(vm.compuscale);

        console.log("viewPort================", viewport);
        console.log(viewport.height, "============", viewport.width);

        canvas.width = viewport.width;
        canvas.height = viewport.height;

        sessionStorage.setItem("height", canvas.height);
        sessionStorage.setItem("width", canvas.width);

        console.log(canvas.width, "=================", canvas.height);

        // Render PDF page into canvas context

        vm.isready = true;
        var renderContext = {
          canvasContext: vm.ctx,
          viewport: viewport
        };
        var renderTask = page.render(renderContext);

        // Wait for rendering to finish
        renderTask.promise.then(function() {
          vm.pageRendering = false;
          if (vm.pageNumPending !== null) {
            // New page rendering is pending
            vm.renderPage(vm.pageNumPending);
            vm.pageNumPending = null;
          }
        });
      });
      vm.page_num = vm.pageNum;
    },
    addscale() {
      //放大
      if (this.compuscale >= this.maxscale) {
        return;
      }
      this.compuscale += 0.1;
      this.queueRenderPage(this.pageNum);
    },
    minus() {
      //缩小
      if (this.compuscale <= this.minscale) {
        return;
      }
      this.compuscale -= 0.1;
      this.queueRenderPage(this.pageNum);
    },
    prev() {
      //上一页
      let vm = this;
      if (vm.pageNum <= 1) {
        return;
      }
      vm.pageNum--;
      vm.queueRenderPage(vm.pageNum);
    },
    next() {
      //下一页
      let vm = this;
      if (vm.pageNum >= vm.page_count) {
        return;
      }
      vm.pageNum++;
      vm.queueRenderPage(vm.pageNum);
    },
    
    throwerr() {
      this.$emit("closepdf",true);      
    },
    queueRenderPage(num) {
      if (this.pageRendering) {
        this.pageNumPending = num;
      } else {
        this.renderPage(num);
      }
    },

    //初始化方法
    initPdf() {
      let vm = this;
      console.log("pdfurlztztzt===", vm.pdfurl);

      if (vm.pdfurl.indexOf(".pdf") > -1) {
        Indicator.open({
          spinnerType: "fading-circle"
        });
        console.log("走了", PDFJS);
        PDFJS.getDocument(vm.pdfurl)
          .then(function(pdfDoc_) {
            //初始化pdf
            console.log("pdfDoc_===", pdfDoc_);
            Indicator.close();
            if (pdfDoc_) {
              vm.pdfDoc = pdfDoc_;
              vm.page_count = vm.pdfDoc.numPages;
              vm.renderPage(vm.pageNum);
            } else {
              Indicator.close();
              vm.showNavbar = false;
              vm.isPageShow = false;
              Toast({
                message: "PDF文档打开失败",
                position: "middle",
                duration: 3000
              });
              vm.throwerr();
            }
          })
          .catch(function(err) {
            if (err) {
              console.log(err);
              Toast({
                message: "PDF文档打开失败",
                position: "middle",
                duration: 3000
              });
              Indicator.close();
              vm.throwerr();
            }
          });
      } else {
        vm.showNavbar = false;
        vm.isPageShow = false;
      }
    }
  },
  computed: {
    ctx() {
      let id = document.getElementById("the-canvas");
      return id.getContext("2d");
    },
    con(url) {
      console.log(url);
    }
  },
  mounted() {
    //加载pdf
    this.initPdf();
    //    	this.$watch('pdfurl',function(){
    //        console.log('urls改变了重新加载');
    //     		this.initPdf();
    //      });

    var pinchImg = document.getElementById("the-canvas");
    Transform(pinchImg);
    var initScale = 1;
    var that = this;
    new AlloyFinger(pinchImg, {
      multipointStart: function() {
        initScale = pinchImg.scaleX;
      },
      pinch: function(evt) {
        if (initScale * evt.zoom >= 1 && initScale * evt.zoom <= 2.5) {
          pinchImg.scaleX = pinchImg.scaleY = initScale * evt.zoom;
        } else if (initScale * evt.zoom > 2.5) {
          pinchImg.scaleX = pinchImg.scaleY = 2.5;
        } else {
          pinchImg.translateX = 0;
          pinchImg.translateY = 0;
          pinchImg.scaleX = pinchImg.scaleY = 1;
        }
      }
    });
    new AlloyFinger(pinchImg, {
      pressMove: function(evt) {
        //             console.log("pinchImg.scaleX====",pinchImg.scaleX,"pinchImg.scaleY===",pinchImg.scaleY);
        //          console.log("evt.deltaX=====", evt);
        if (
          evt.direction !== "Left" &&
          evt.direction !== "Right" &&
          evt.direction !== ""
        ) {
          if (pinchImg.translateX != 0 && pinchImg.translateY != 0) {
            if (pinchImg.translateX <= -295) {
              pinchImg.translateX = -295 + 0.001;
            } else if (pinchImg.translateX >= 295) {
              pinchImg.translateX = 295 - 0.001;
            }
            if (pinchImg.translateY <= -430) {
              pinchImg.translateY = -430 + 0.001;
            } else if (pinchImg.translateY >= 430) {
              pinchImg.translateY = 430 - 0.001;
            }
          }

          //          console.log("pinchImg.translateX====",pinchImg.translateX,"pinchImg.translateY===",pinchImg.translateY);
          if (pinchImg.scaleX > 1 || pinchImg.scaleY > 1) {
            if (
              pinchImg.translateX <= 295 &&
              pinchImg.translateX >= -295 &&
              pinchImg.translateY <= 430 &&
              pinchImg.translateY >= -430
            ) {
              pinchImg.translateX += evt.deltaX;
              pinchImg.translateY += evt.deltaY;
              //                console.log("X,Y距离后=====", pinchImg.translateX, pinchImg.translateY)
              evt.preventDefault();
            }
          } else {
            pinchImg.translateX = 0;
            pinchImg.translateY = 0;
            //              console.log("X,Y距离后=====", pinchImg.translateX, pinchImg.translateY)
            evt.preventDefault();
          }
        }
      }
    });
    var that = this;
    new AlloyFinger(pinchImg, {
      touchMove: function(evt) {
        //            console.log("evt========",evt);
        if (Math.abs(evt.deltaX) >= Math.abs(evt.deltaY)) {
          evt.preventDefault();
        }
      },
      swipe: function(evt) {
        //          console.log("evt========", evt);
        //          if (evt.timeStamp <= 50000) {
        /* if (evt.direction === "Left") {
              console.log("左滑滑")
              pinchImg.translateX = 0;
              pinchImg.translateY = 0;
              pinchImg.scaleX = pinchImg.scaleY = 1;
              that.next();
            } else if (evt.direction === "Right") {
              console.log("右滑")
              pinchImg.translateX = 0;
              pinchImg.translateY = 0;
              pinchImg.scaleX = pinchImg.scaleY = 1;
              that.prev();

            }*/
        //          }
        //          evt.timeStamp=0
      }
    });
  }
};
</script>

<style scoped>
.title_style {
  position: fixed;
  top: 0;
  z-index: 999;
  width: 100%;
  -moz-transform: none;
  -webkit-transform: none;
  -o-transform: none;
  -ms-transform: none;
  transform: none;
  /* background-color: rgba(0, 0, 0, 0.3); */
}

.title {
  font-size: 0.32rem;
  color: white;
  text-align: center;
  width: 100%;
  /*padding: 0.3rem 0 0.3rem;*/
  margin: 10px 0;
  /*overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;*/
}

.pagestyle {
  -moz-transform: none;
  -webkit-transform: none;
  -o-transform: none;
  -ms-transform: none;
  transform: none;
  width: 35%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  background-color: rgba(0, 0, 0, 0.5);
  /*background-color: red;*/
  border-radius: 20px;
  /*margin-top: -25%;*/
  position: fixed;
  bottom: 10%;
  left: 58%;
  margin-left: -25%;
}

.pagestyle_changes {
  -moz-transform: none;
  -webkit-transform: none;
  -o-transform: none;
  -ms-transform: none;
  transform: none;
  width: 35%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  background-color: rgba(0, 0, 0, 0.5);
  border-radius: 20px;
  /*margin-top: -8%;*/
  bottom: 4%;
  position: fixed;
  left: 60%;
  margin-left: -25%;
}

.pagenum_style {
  font-size: 0.28rem;
  padding: 0 0.3rem 0 0.3rem;
  color: white;
}

.canvasstyle {
  width: 99%;
  min-width: 99%;
  max-width: 300%;
}

.cpdf {
  top: 0;
  left: 0;
  /*background-color: rgba(0, 0, 0, .5);*/
  background-color: #aaaaaa;
  width: 100%;
  /* height: 100vh; */
  z-index: 2;
  /*display: flex;*/
  /*justify-content: center;*/
  /*align-items: center;*/
}

/*.pagenum {*/
/*font-size: 0.28rem;*/
/*display: inline-block;*/
/*line-height: 1;*/
/*white-space: nowrap;*/
/*cursor: pointer;*/
/*!*background: #fff;*!*/
/*!*border: 1px solid #bfcbd9;*!*/
/*color: #1f2d3d;*/
/*-webkit-appearance: none;*/
/*text-align: center;*/
/*-webkit-tap-highlight-color:rgba(0,0,0,0);!*取消点击效果*!*/
/*box-sizing: border-box;*/
/*outline: 0;*/
/*margin: 0;*/
/*padding: 8px 10px;*/
/*border-radius: 4px;*/
/*}*/
.cpdf .btn {
  font-size: 0.28rem;
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  cursor: pointer;
  /*background: #fff;*/
  border: 1px solid #bfcbd9;
  color: #1f2d3d;
  -webkit-appearance: none;
  text-align: center;
  box-sizing: border-box;
  outline: 0;
  margin: 0;
  padding: 8px 10px;
  border-radius: 4px;
}

/* 
.cpdf {
  width: 100vw;
  height: 100vh;
} */

.cpdf .center {
  position: relative;
  text-align: center;
  height: 95%;
  padding-top: 10px;
  overflow: auto;
}
</style>
