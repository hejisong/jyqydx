<template>
  <div class="accredit">
    <!-- <Head :show="true"  :noRegiste="noRegiste" :shenbao='true' @linkTo='linkTo' title="申报专栏" /> -->
    <van-nav-bar
      title="申报专栏"
      
      right-text="申报"
      
      
      @click-right="linkTo"
    />
    <div class="accredit_list">
      <div v-if="urlList.length > 1">
        <van-cell v-for="(item,index) in urlList" :key="index" @click="pdfClick(item,index)">
          <template #title>
            <div class="start">
              <img style='width:0.5rem;height:0.5rem;' src="@/assets/imgs/icon_pdf.png" alt="">
              <span
              style="margin-left: 0.25rem"
              :class="index==active2?'custom-title':''" 
              :style="'color:'+index == active2?'#1989fa':''">{{ item.names }}</span>
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
        <PDF
          ref="cpdf"
          @closepdf="closepdf"
          v-if="isshowpdf"
          :showNavbar="showNavbar"
          :isPageShow="isPageShow"
          :pdfurl="pdfUrl"
          :title="titlename"
          @pdferr="pdferr"
          :maxscale="4"
          :minscale="0.6"
          :scale="1.1"
        ></PDF>
      </van-popup>
      
      
    </div>
      
  </div>
</template>

<script>
import Head from "../../components/head/Head";
import { baseUrl } from "../../network/baseUrl";
import PDF from "../../components/pdf/PDF";
import { Toast, Empty } from "vant";

export default {
  name: "declareDetail",
  components: {
    Head,
    PDF
  },
  data() {
    return {      
      content:'',
      listString:'',
      show:false,
      urlList:[],
      pdfUrl:'',
      showNavbar: false,
      titlename:'',
      active2:0,
      isPageShow: true,
      isshowpdf:false,
      noRegiste:''
    };
  },
  created() {
    this.content = this.unescape(this.$route.query.content)
     this.noRegiste = sessionStorage.getItem('noRegiste')
    this.listString = this.$route.query.listString
  },
  mounted(){
    let id = this.$route.query.id.split(',')
    let name = this.$route.query.name.split(',')
    if(id.length>0){
      // this.isshowpdf = true
      for(let i = 0;i<id.length;i++){
        let obj = {
          names:name[i],
          url: baseUrl+'/doc/upload_'+id[i]+'.pdf'
        }
        this.urlList.push(obj)
      }
      console.log('========',this.urlList)
      // this.pdfUrl = this.urlList[0].url
      // this.show = true
    }
    let box = document.getElementById('tab-bar')
    box.style.visibility="hidden";
  },
  destroyed(){
    let box = document.getElementById('tab-bar')
    box.style.visibility="visible";
  },
  methods: {
    closepdf() {
      this.isshowpdf = false;
    },
    pdferr(errurl) {
      console.log(errurl);
    },
    onClickRight(item,index){
      this.$router.go(-1)
    },
    pdfClick(id,index){
      console.log(id,index)
      this.pdfUrl = ''
      this.active2 = index
      this.isshowpdf = false
      this.$nextTick(() => {
        this.isshowpdf = true
        this.pdfUrl = id.url
      });
      console.log(this.pdfUrl)
    },
    linkTo(){
      let data  = JSON.parse(sessionStorage.getItem('companyInfo'))
      if(!data){
        Toast("您还没有企业，无法操作！");
        return
      }
      let _this = this
      if(data.islegalperson == '2'){
        Toast("暂无权限，请到企业授权界面申请授权后再进行操作！");
        return
      }
      let timestamp =Date.parse(new Date());
      let entTime = this.$route.query.entTime
      let starTime = this.$route.query.starTime
      console.log(timestamp)
      if(timestamp>=starTime && timestamp<= entTime){
        _this.$router.push(
          {
              path: "/createdDeclare",
              query: {
                  id:this.listString
              }
          }
        )
      }else{
        Toast('已过期！')
      }
      
    },
    unescape (html) {
        return html
        // .replace(html ? /&(?!#?\w+;)/g : /&/g, '&amp;')
        // .replace(/&lt;/g, "<")
        // .replace(/&gt;/g, ">")
        // .replace(/&quot;/g, "\"")
        // .replace(/&#39;/g, "\'")
        // .replace(/&amp;nbsp;/g, " ")
    }
  }
};
</script>

<style scoped>
    .accredit_list {
        padding: 12px 16px;
        height: 100%;
    }
    .start{
      display: flex;
      align-items: center;
      line-height: 18px;
    }
    .custom-title{
      color: #1989fa
    }
</style>