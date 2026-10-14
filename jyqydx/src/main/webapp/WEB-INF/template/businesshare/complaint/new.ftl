
[#include "common/head.ftl"]
<body>



<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main"  >

            <form  class="form-horizontal suggest" role="form" id="formid" action="${base}/businesshare/complaint/save" method="post">

                <div class="form-group" style="padding-top: 20px;">
                <p>投诉</p>

                </div>




                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 标题 <span
                                style="color: red;">*</span></label>
                    <div class="col-xs-8">
                        <div class="block input-icon input-icon-right">
                            <input style="font-size: 16px;" type="text" id="title" name="title" value="${suggest.title }" class="col-xs-12">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 内容<span
                                style="color: red;">*</span></label>
                    <div class="col-xs-8">
                        <div class="block input-icon input-icon-right">
                            <textarea   rows="10" id="t_content" name="content" value="${suggest.content }" class="form-control"></textarea>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 单位名称<span style="color: red;">*</span> </label>
                    <div class="col-xs-4">
                        <div class="block input-icon input-icon-right">
                            <input style="font-size: 16px;" type="text" id="companyname" name="companyname" value="${suggest.companyname }" class="col-xs-12">
                        </div>
                    </div>
                    <label class="col-xs-1 control-label no-padding-right" style="text-align: right;"> 联系方式<span style="color: red;">*</span> </label>
                    <div class="col-xs-3">
                        <div class="block input-icon input-icon-right">
                            <input style="font-size: 16px;" type="text" id="mobile" name="mobile" value="${suggest.mobile }" class="col-xs-12">
                        </div>
                    </div>

                </div>


                <div  style="text-align: center;">
                    <button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="saveSuggest()">提 交</button>
                    <button class="btn  btn-xs btn-width  btn-info" style="margin-left: 5px;border: aliceblue;border-radius: 20px;width: 80px;height: 35px;background-color: darkgrey!important;color: white;" type="button" onclick="returninfo()">取 消</button>
                </div>
            </form>




        </div>



    </div>


</div>
[#include "common/foot.ftl"]
</body>
<script type="text/javascript">
    $(function(){
        initTime();
    });
    function initTime(){
        $(".form_datetime").datetimepicker({
            autoclose: true,
            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd",
            minuteStep : 1,
            minView : 2,
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            language: 'zh_CN'
        });
    }





    function saveSuggest() {
        var companyname=$("#companyname").val();
        var mobile=$("#mobile").val();
        var title=$("#title").val();
        var content=$("#t_content").val();
         if(title==null||title==''||title==undefined){
            bootbox.alert("请填写标题！");
            return;
        }else if(content==null||content==''||content==undefined){
            bootbox.alert("请填写内容！");
            return;
        }else if(companyname==null||companyname==''||companyname==undefined){
             bootbox.alert("请填写单位名称！");
             return;
         } else if(mobile==null||mobile==''||mobile==undefined){
             bootbox.alert("请填写联系方式！");
             return;
         }else if (!checkTel(mobile)) {
             bootbox.alert("请填写正确的手机号码！");
             return;
         }else{
            $("#formid").submit();
        }


    }

    function checkTel(tel){
        var isMob=/^\d{11}$/;
        if(isMob.test(tel)){
            return true;
        }
        else{
            return false;
        }
    }


    function returninfo() {
        window.location.href=base+"/businesshare/complaint/list";
    }

</script>
</html>