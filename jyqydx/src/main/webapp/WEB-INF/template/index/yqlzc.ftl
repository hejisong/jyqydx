
[#include "common/head.ftl"]
<body>


    <div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

    <div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main"  >

           <div> <img style="align: middle;margin-left: 10px;margin-top: 5px;"  src="${base}/assets/images/yqlzc/suggest.png" onclick="opensuggest()">
               <img style="align: middle;"  src="${base}/assets/images/yqlzc/complaint.png" onclick="opencomplaint()">
               <img style="align: middle;"  src="${base}/assets/images/yqlzc/order.png" onclick="openorder()">
               <img style="align: middle;"  src="${base}/assets/images/yqlzc/tel.png" onclick="openinfo()"></div>


            <div style="padding-top: 30px;">
[#--                <img src="${base}/assets/images/syy.png" alt="prev">--]
                <img style="align: middle;margin-left: 10px;margin-top: 5px;"  src="${base}/assets/images/yqlzc/p1.png" >
                <img style="align: middle;"  src="${base}/assets/images/yqlzc/p2.png" >
                <img style="align: middle;"  src="${base}/assets/images/yqlzc/p3.png" >
[#--                <img src="${base}/assets/images/下一页备份.png" alt="next">--]
            </div>







        </div>






    </div>


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
[#--选择企业--]
[#include "index/selectcompany.ftl"]
<script type="text/javascript">

    function opensuggest(){
        window.location.href=base+"/businesshare/suggest/list";
    }

    function opencomplaint() {
        window.location.href=base+"/businesshare/complaint/list";
    }

    function openorder() {
        var companyid=$("#companyid").val();
        if(companyid!=undefined&&companyid!=null&&companyid!=""){
            window.location.href=base+"/yqlzcinfo/order?companyid="+companyid;
        }else{
            bootbox.alert("请先登录再预约代办！");
        }



    }


</script>
</html>