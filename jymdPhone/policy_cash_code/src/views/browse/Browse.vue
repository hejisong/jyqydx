
<template>
  <div class="content">
    <Head :show="true" title="政策详情" />
    <div class="title van-hairline--bottom">
      <p class="titles" v-if="isSearch == ''">{{ titleData.policyname }}</p>
      <p class="titles" v-else v-html="titleData.policyname" ></p>
      <p>政策级别：{{ titleData.fileLevel == ''?'全部':(titleData.fileLevel == '1'?'市级':'区级') }}</p>
      <p>发文单位：{{ titleData.fwunits }}</p>
      <p>发布日期：{{ titleData.releasetime }}</p>
    </div>
    <div class="active">
      <van-tabs v-model="active" title-inactive-color="#999999" title-active-color='#3e92ff'>
        <van-tab title="政策原文" name="1"></van-tab>
        <van-tab title="奖励明细" name="2"></van-tab>
      </van-tabs>
    </div>
    <div class="contnt">
      <div class="list" v-show="active == '2'">
        <div class="lists van-hairline--bottom" v-for="(item,index) in childList" :key="index">
          <p class="listTile" v-if="isSearch == ''">{{ item.nplcategory }}</p>
          <p class="listTile" v-else v-html="item.nplcategory"></p>
          <p v-if="isSearch == ''">{{ item.rdescription }}</p>
          <p v-else v-html="item.rdescription"></p>
          <p v-if="isSearch == ''">{{ item.plconditions }}</p>
          <p v-else v-html="item.plconditions"></p>
          <p class="num" v-if="noRegiste != '1'">
            <!-- <span class="money">{{ item.ramount?'￥'+item.ramount:'--' }}</span> -->
            <span><van-button type="info" :disabled="item.sbstate == '1'" size="mini" @click="linkTo(item)">{{ item.sbstate != '1'?'申 报':'已申报' }}</van-button></span>
          </p>
        </div>
        <p class="Nodata" v-if="childList.length == 0">暂无数据</p>
      </div>
      <div class="htmlText" v-show="active == '1'">
        <div v-if="fileList.length > 0">
          <van-cell v-for="(item,index) in fileList" :key="index" @click="pdfClick(item,index)">
            <template #title>
              <div class="start">
                <img style='width:0.5rem;height:0.5rem;' src="@/assets/imgs/icon_pdf.png" alt="">
                <span
                style="margin-left: 0.25rem"
                :class="index==active2?'custom-title':''" 
                :style="'color:'+index == active2?'#1989fa':''">{{ item.filename }}</span>
                <!-- <span style='width:0.5rem;height:0.5rem;line-height: 0.5rem;'><van-icon color="#1989fa" v-if="index == active2" name="passed" /></span> -->
              </div>
            </template>
          </van-cell>
        </div>
        <van-popup
          v-model="isshowpdf"
          closeable        
          :style="{ height: '85%',width:'94%' }"
        >
          <!-- <PDF
            v-if="isshowpdf"
            :pdfURL="pdfurls"
            @closePdf='closepdf'
            :noRegiste='noRegiste'   
          ></PDF> -->
          <PDF
            ref="cpdf"
            @closepdf="closepdf"
            v-if="isshowpdf"
            :showNavbar="showNavbar"
            :isPageShow="true"
            :pdfurl="pdfurls"
            :title="titlename"
            :maxscale="4"
            :minscale="0.6"
            :scale="1.1"
          ></PDF>
        </van-popup>
        
        <p class="Nodata" v-if="fileList.length == 0">暂无数据</p>
      </div>
    </div>
    <!-- <van-popup v-model="showPdf">
      <div class="shoePdf">
        <p class="closePop">关闭</p>
        <PDF
          :pdfURL="pdfurls"
          :noRegiste='noRegiste'   
        ></PDF>
      </div>
    </van-popup> -->
  </div>
</template>

<script>
// import PDF from "../../components/pdf/PDF";
import PDF from "../../components/pdf/PDF";
import { baseUrl,imgUrl } from "../../network/baseUrl";
import Head from "../../components/head/Head";
import { Toast } from 'vant';

export default {
  name: "Browse",
  components: {
    PDF,
    Head
  },
  data() {
    return {
      baseUrl,
      active:0,
      titleData:{},
      childList:[],
      isSearch:'',
      active2:0,
      noRegiste:'',
      fileList:[],
      enterTime: "",
      leaveTime: "",
      name: "",
      dataList: {},
      pdfurls: "",
      isshowpdf: false,
      curpage_num: "",
      pdfInfo: {},
      showPdf:false,
      showNavbar: false,
      // titlename: "政策原文",
      titlename: "",
      currentdate: "",
      htmls:''
    };
  },
  watch:{
    active:function(val){
      // console.log(val)
      let obj = JSON.parse(this.$route.query.url)
      let type = obj.type
      if(type == '1'){//原文
        if(val == '1'){
          console.log('原文')
        }else{
          console.log('细则')
          setTimeout(()=>{
            this.showList(this.titleData)
          },100)
        }
      }else{//标签
        this.isshowpdf = false
        if(val == '1'){
          console.log('标签原文')
        }else{
          console.log('标签细则')
          setTimeout(()=>{
            this.getlabel()
          },100)
          
        }
      }
    }
  },
  created() {
    
  },
  mounted(){
    this.noRegiste = sessionStorage.getItem('noRegiste')
    let obj = JSON.parse(this.$route.query.url)
    this.isSearch = obj.isSearch
    console.log('obj------->',obj)
    this.fileList = obj.item.uploadfiles
    if(this.fileList.length == 1){
      this.pdfurls = imgUrl + this.fileList[0].path
      this.isshowpdf = true
    }
    console.log('pdfurls=======>',baseUrl,this.pdfurls)
    if(obj.type == '1'){
      this.active = obj.type
      this.titleData = obj.item
      // this.htmls = obj.item.filecontent;
    }else if(obj.type == '2'){
      this.active = obj.type
      this.titleData = obj.item
      this.tagid = obj.tagid
      // this.htmls = obj.item.filecontent;
    }
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
    // display
  },
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
  },
  // beforeRouteLeave(to, from, next) {
    // console.log(this.$refs.cpdf);
    // this.$refs.cpdf.close();
    // next();
  // },

  methods: {
    pdfClick(item,index){
      this.active2 = index
      this.$nextTick(() => {
        this.isshowpdf = true
        this.pdfurls = imgUrl + item.path
      });
    },
    async linkTo(ids){      
      const { isLogin } = sessionStorage;
      if(!isLogin){
        Toast("请登录!");
        return
      }
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      if(!data){
        Toast("您还没有企业，无法操作！");
        return
      }
      if(data.islegalperson == '2'){
        Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
        return
      }
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/apply",
        {
          params: {
            pldtailid:ids.pldtailid,
            companyid:data.companyid
          }
        }
      );
      console.log('=============>',resultData.data.errorMsg)
      let isTrue = resultData.data.result
      if(isTrue){
        let id = resultData.data.resultObj
        this.$router.push({
          path: "/policyDetail",
          query: {
            id: id,
          }
        });
      }else{
        Toast(resultData.data.errorMsg);
        return
      }
    },
    async getlabel(){
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      // if(!data){
      //   Toast('您还没有企业，无法操作！')
      //   return
      // }
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findPolicyFileByTagAndPlfId",
        {
          params: {
            tagid:this.tagid,
            plfidid:this.titleData.plfidid,
            companyid:data?data.companyid:''
          }
        }
      );
      console.log('=====>',resultData)
      const { result, resultObj } = resultData.data;
      if(result){
        if(this.isSearch != ''){
          for(let i = 0;i<resultObj.length;i++){
            resultObj[i].nplcategory = this.highLight(resultObj[i].nplcategory)
            resultObj[i].rdescription = this.highLight(resultObj[i].rdescription)
            resultObj[i].plconditions = this.highLight(resultObj[i].plconditions)
          }
        }
        this.childList = resultObj
        Toast.clear()      
      }
    },
    // 获取非标签的细则列表
    async showList(item){    
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      Toast.loading({
        message: "加载中...",
        forbidClick: true
      });
      let resultData = await this.$axios.get(
        baseUrl + "/interface/policyfile/findPolicydetailsByFileId",
        {
          params: {
            plfidid:item.plfidid,
            companyid:data?data.companyid:''
          }
        }
      );
      console.log('=====>',resultData)
      const { result, resultObj } = resultData.data;
      if(result){
        if(this.isSearch != ''){
          for(let i = 0;i<resultObj.length;i++){
            resultObj[i].nplcategory = this.highLight(resultObj[i].nplcategory)
            resultObj[i].rdescription = this.highLight(resultObj[i].rdescription)
            resultObj[i].plconditions = this.highLight(resultObj[i].plconditions)
          }
        }
        this.childList = resultObj
        Toast.clear()      
      }
    },
    // 搜索高亮
    highLight(title){
      if(this.isSearch != ''){
         // 如果标题中包含，关键字就替换一下
        if(title.includes(this.isSearch)){
          title = title.replace(
              this.isSearch, 
              // 这里是替换成html格式的数据，最好再加一个样式权重，保险一点
              '<font style="color:red!important;font-weight: 600">'+ '&nbsp;'+this.isSearch+'&nbsp;' +'</font>'
          )
          return title
        }
        // 不包含的话还用这个
        else{
          return title
        }
      }else if(this.bgcIndex != -1 && this.tabNum == 1){
        if(title.includes(this.labelListNum[this.bgcIndex].tagname)){
          title = title.replace(
              this.labelListNum[this.bgcIndex].tagname, 
              // 这里是替换成html格式的数据，最好再加一个样式权重，保险一点
              '<font style="color:red!important;font-size:14px;font-weight: 600">'+ '&nbsp;'+this.labelListNum[this.bgcIndex].tagname+'&nbsp;' +'</font>'
          )
          return title
        }
        // 不包含的话还用这个
        else{
          return title
        }
      }else{
        return title
      }
    },
    //水印
    watermark(setting) {
      var defaultSettings = {
        watermark_txt: "text",
        watermark_x: 20, //水印起始位置x轴坐标
        watermark_y: 40, //水印起始位置Y轴坐标
        watermark_rows: 30, //水印行数
        watermark_cols: 40, //水印列数
        watermark_x_space: 9, //水印x轴间隔
        watermark_y_space: 112, //水印y轴间隔
        watermark_color: "#FF1904", //水印字体颜色
        watermark_alpha: 0.2, //水印透明度
        watermark_fontsize: "14px", //水印字体大小
        watermark_font: "微软雅黑", //水印字体
        watermark_width: 100, //水印宽度
        watermark_height: 60, //水印长度
        watermark_angle: 20 //水印倾斜度数
      };
      //采用配置项替换默认值，作用类似jquery.extend
      if (arguments.length === 1 && typeof arguments[0] === "object") {
        var src = arguments[0] || {};
        var key;
        for (key in src) {
          if (
            src[key] &&
            defaultSettings[key] &&
            src[key] === defaultSettings[key]
          )
            continue;
          else if (src[key]) defaultSettings[key] = src[key];
        }
      }

      var oTemp = document.createElement("div");

      //获取页面最大宽度
      var page_width = Math.max(
        document.body.scrollWidth,
        document.body.clientWidth
      );

      //        var page_width =screen.availWidth;
      //获取页面最大长度
      var page_height = Math.max(
        document.body.scrollHeight,
        document.body.clientHeight
      );
      //        var page_height = screen.availHeight;
      console.log("page_width==", page_width, "page_height===", page_height);
      //如果将水印列数设置为0，或水印列数设置过大，超过页面最大宽度，则重新计算水印列数和水印x轴间隔
      if (
        defaultSettings.watermark_cols == 0 ||
        parseInt(
          defaultSettings.watermark_x +
            defaultSettings.watermark_width * defaultSettings.watermark_cols +
            defaultSettings.watermark_x_space *
              (defaultSettings.watermark_cols - 1)
        ) > page_width
      ) {
        defaultSettings.watermark_cols = parseInt(
          (page_width -
            defaultSettings.watermark_x +
            defaultSettings.watermark_x_space) /
            (defaultSettings.watermark_width +
              defaultSettings.watermark_x_space)
        );
        defaultSettings.watermark_x_space = parseInt(
          (page_width -
            defaultSettings.watermark_x -
            defaultSettings.watermark_width * defaultSettings.watermark_cols) /
            (defaultSettings.watermark_cols - 1)
        );
      }
      //如果将水印行数设置为0，或水印行数设置过大，超过页面最大长度，则重新计算水印行数和水印y轴间隔
      if (
        defaultSettings.watermark_rows == 0 ||
        parseInt(
          defaultSettings.watermark_y +
            defaultSettings.watermark_height * defaultSettings.watermark_rows +
            defaultSettings.watermark_y_space *
              (defaultSettings.watermark_rows - 1)
        ) > page_height
      ) {
        //          defaultSettings.watermark_rows =
        //            parseInt((defaultSettings.watermark_y_space
        //              + page_height - defaultSettings.watermark_y)
        //              / (defaultSettings.watermark_height + defaultSettings.watermark_y_space));
        defaultSettings.watermark_rows = parseInt(
          +page_height /
            (defaultSettings.watermark_height +
              defaultSettings.watermark_y_space)
        );
        //          defaultSettings.watermark_y_space =
        //            parseInt((page_height
        //              - defaultSettings.watermark_y
        //              - defaultSettings.watermark_height
        //              * defaultSettings.watermark_rows)
        //              / (defaultSettings.watermark_rows - 1));
      }
      console.log(
        "defaultSettings.watermark_rows====",
        defaultSettings.watermark_rows
      );
      var x;
      var y;
      for (var i = 0; i < defaultSettings.watermark_rows; i++) {
        y =
          defaultSettings.watermark_y +
          (defaultSettings.watermark_y_space +
            defaultSettings.watermark_height) *
            i;
        for (var j = 0; j < defaultSettings.watermark_cols; j++) {
          x =
            defaultSettings.watermark_x +
            (defaultSettings.watermark_width +
              defaultSettings.watermark_x_space) *
              j;

          var mask_div = document.createElement("div");
          mask_div.id = "mask_div" + i + j;
          mask_div.appendChild(
            document.createTextNode(defaultSettings.watermark_txt)
          );
          //设置水印div倾斜显示
          mask_div.style.webkitTransform =
            "rotate(-" + defaultSettings.watermark_angle + "deg)";
          mask_div.style.MozTransform =
            "rotate(-" + defaultSettings.watermark_angle + "deg)";
          mask_div.style.msTransform =
            "rotate(-" + defaultSettings.watermark_angle + "deg)";
          mask_div.style.OTransform =
            "rotate(-" + defaultSettings.watermark_angle + "deg)";
          mask_div.style.transform =
            "rotate(-" + defaultSettings.watermark_angle + "deg)";
          mask_div.style.visibility = "";
          mask_div.style.position = "absolute";
          mask_div.style.left = x + "px";
          mask_div.style.top = y + "px";
          mask_div.style.overflow = "hidden";
          mask_div.style.zIndex = "0";
          //mask_div.style.border="solid #eee 1px";
          mask_div.style.opacity = defaultSettings.watermark_alpha;
          mask_div.style.fontSize = defaultSettings.watermark_fontsize;
          mask_div.style.fontFamily = defaultSettings.watermark_font;
          mask_div.style.color = defaultSettings.watermark_color;
          mask_div.style.textAlign = "center";
          mask_div.style.width = defaultSettings.watermark_width + "px";
          mask_div.style.height = defaultSettings.watermark_height + "px";
          mask_div.style.display = "block";
          oTemp.appendChild(mask_div);
        }
      }
      document.getElementById("all").appendChild(oTemp);
    },

    closepdf() {
      this.isshowpdf = false;
    },

    pdferr(errurl) {
      console.log(errurl);
    }
  }
};
</script>

<style scoped>
.head {
  height: 0.6rem;
  /*background-color: #FFFFFF;*/
  text-align: center;
  line-height: 0.6rem;
  font-weight: bold;
  font-size: 0.3rem;
}
.closePop{
  position: absolute;
  right: 0;
  width: 100%;
  margin: 0;
  text-align: right;
  color: #ffffff;
  top: -0;  
  font-size: 12px;
  border:1px solid red;
}
.shoePdf{
  width: 6.5rem;
  position: relative;
  padding-top: 0.5rem;
  max-height: 10rem;
  overflow-y: auto;
  /* border:5px solid red; */
}
.title{
  padding: 0.25rem 0.3rem;
}
.title p,.lists p{
  font-size: 0.28rem;
  margin: 0;
  color:#666666;
  margin-bottom: 2px;
}
.title .titles,.lists .listTile{
  color: #333333;
  font-size: 0.35rem;
  margin-bottom: 5px;
}
.lists{
  padding: 0.3rem 0
}
.active>>>.van-tabs__line{
  background-color: #3e92ff
}
.custom-title{
  color: #1989fa
}
.contnt{
  padding: 0 0.28rem;
}
.active{
  width: 3rem;
  font-size: 0.3rem;
  margin-left: 0.15rem;
}
.list{
  /* height: 5rem; */
  margin-top: 0.2rem;
  background-color: #f9f9f9;
  /* border: 1px solid red; */
  padding: 0 0.3rem;
}
.htmlText{  
  background-color: #f9f9f9;
}
.Nodata{
  font-size: 0.4rem;
  color: gray;
  line-height: 2rem;
  text-align: center
}
.start{
  display: flex;
  align-items: center;
  line-height: 18px;
}

.list p{
  color: #9b9b9b
}
.pu-left{
  float: left;
}
.pu-right{
  float: right;
}
.clearfix:after{
  content:".";       
  display:block;       
  height:0;       
  clear:both;       
  visibility:hidden;       
}
.num{
  display: flex;
  align-items: center;
  justify-content: space-between;
  vertical-align: middle
}
.num .money{
  font-size: 0.33rem;
  font-weight: 600;
  color: #ff7855
}
</style>