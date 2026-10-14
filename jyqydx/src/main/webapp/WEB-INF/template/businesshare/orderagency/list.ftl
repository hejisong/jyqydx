
[#include "common/head.ftl"]
<body>
<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
    <div class="wrapper_content_main" style="width: 74%;" >

        <form class="form-horizontal"  action="${base}/businesshare/orderagency/list" method="get">
            <input type="hidden" id="ordercompanyid" name="ordercompanyid" value="${ordercompanyid}">

            <label class="col-xs-3 control-label no-padding-right" style="text-align:right;padding-top: 6px;margin-top: 10px;">预约人</label>
            <div class="col-xs-2" style="margin-top: 10px;">
                <input type="text" id="orderman" name="orderman" value="${orderman}" class="col-xs-12">
            </div>
            <div class="col-xs-3" style="margin-top: 10px;text-align: center;" >
                <button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">搜  索</button>
                <a style="margin-left: 15px;" class="btn btn-xs btn-info btn-width"  href="${base}/yqlzcinfo/order?companyid=${ordercompanyid}">返 回</a>
            </div>
        </form>

        <div style="height: 470px;overflow-y: auto;width: 100%;">
            <table class="VirusTable" >
                <thead>
                <tr>
                    <th style="width: 5%;"><div>序号</div></th>
                    <th style="width: 10%;"><div>代办人</div></th>
                    <th style="width: 10%;"><div>预约人</div></th>
                    <th style="width: 15%;"><div>联系电话</div></th>
                    <th style="width: 15%;"><div>预约时间</div></th>
                    <th style="width: 25%;"><div>备注</div></th>
                    <th style="width: 10%;"><div>状态</div></th>
                </tr>
                </thead>
                <tbody>
                    [#list page.content as info]
                        <tr>
                            <td>${info_index+1}</td>
                            <td>${info.agencyman}</td>
                            <td>${info.orderman}</td>
                            <td>${info.contact}</td>
                            <td>${(info.ordertime?string('yyyy-MM-dd HH:mm:ss')) }</td>
                            <td>${info.remark}</td>
                            <td>[#if info.state=='0']未处理[#else]已处理[/#if]</td>
                        </tr>
                    [/#list]

                </tbody>
            </table>
        </div>
        <div style="margin-top: 20px;margin-left: 15px;">
            [#assign pagepath="${base}/businesshare/orderagency/list?&orderman=${orderman}&"]
            [#include "common/pagemodel.ftl"]
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





    function saveOrder() {
        var manid=$("#agencymanid").val();
        var contact=$("#contact").val();
        if(manid==null||manid==''||manid==undefined){
            bootbox.alert("请选择代办人员！");
            return;
        } else if(contact==null||contact==''||contact==undefined){
            bootbox.alert("请填写联系方式！");
            return;
        }else{
            $("#formid").submit();
        }


    }


</script>
</html>