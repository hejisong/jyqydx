
[#include "common/head.ftl"]
<body>
<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main"  style="overflow-y: auto;">
            <form  class="form-horizontal suggest" role="form" >

                <div class="form-group" style="padding-top: 20px;">
                    <p>投诉与咨询</p>

                </div>




                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 标题 </label>
                    <div class="col-xs-8">
                        <div class="block input-icon input-icon-right">
                            <input type="text" style="font-size: 16px;" readonly id="title" name="title" value="${suggest.title }" class="col-xs-12">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 内容</label>
                    <div class="col-xs-8">
                        <div class="block input-icon input-icon-right">
                            <textarea readonly   rows="6"  value="" class="form-control">${suggest.content }</textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 单位名称 </label>
                    <div class="col-xs-4">
                        <div class="block input-icon input-icon-right">
                            <input type="text"  style="font-size: 16px;" readonly id="companyname" name="companyname" value="${suggest.companyname }" class="col-xs-12">
                        </div>
                    </div>
                    <label class="col-xs-1 control-label no-padding-right" style="text-align: right;"> 联系方式 </label>
                    <div class="col-xs-3">
                        <div class="block input-icon input-icon-right">
                            <input readonly type="text" id="mobile" name="mobile" value="${suggest.mobile }" class="col-xs-12">
                        </div>
                    </div>

                </div>


                [#list replys as reply]
                    <div class="form-group">

                        <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> [#if reply.infotype=='1']回复[#else]追问[/#if]</label>
                        <div class="col-xs-8">
                            <div class="block input-icon input-icon-right">
                                <textarea readonly   rows="4"  value="" class="form-control">${reply.content }&nbsp;&nbsp;${(reply.replytime?string('yyyy-MM-dd HH:mm:ss')) }</textarea>
                            </div>
                        </div>
                    </div>
                [/#list]


            </form>

            <form class="form-horizontal suggest" role="form" id="formid" action="${base}/businesshare/suggest/questsave" method="post">
                <div class="form-group" style="display: none;" id="quest">
                    <input type="hidden" name="suggestid" value="${suggest.id}">
                    <input type="hidden" name="infotype" value="2">
                    <label class="col-xs-2 control-label no-padding-right" style="text-align: right;">追问</label>
                    <div class="col-xs-8">
                        <div class="block input-icon input-icon-right">
                            <textarea    rows="4" id="replaycontent" name="content" value="" class="form-control"></textarea>
                        </div>
                    </div>
                </div>

            </form>
            <div  style="text-align: center;">
                <button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary"  type="button" onclick="returninfo()">返 回</button>
            </div>

            </div>





    </div>


[#include "common/foot.ftl"]
</body>
<script type="text/javascript">


    function commitquest() {
        var replaycontent=$("#replaycontent").val();
        if(replaycontent==null||replaycontent==''||replaycontent==undefined){
            bootbox.alert("请填写追问内容！");
            return;
        } else{
            $("#formid").submit();
        }
    }

    function returninfo() {
        window.location.href=base+"/businesshare/complaint/list";
    }

    function quest() {
      $("#quest").show();
      $("#questinfo").hide();
      $("#saveinfo").show();
    }


</script>
</html>