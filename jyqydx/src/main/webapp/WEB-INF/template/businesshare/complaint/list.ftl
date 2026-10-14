
[#include "common/head.ftl"]
<body>
<div style="background-color:  #F7F9FF;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color:  #F7F9FF;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main" style="width: 74%;" >

            <form class="form-horizontal"  action="${base}/businesshare/complaint/list" method="get">

                <label class="col-xs-3 control-label no-padding-right" style="text-align:right;padding-top: 6px;margin-top: 10px;">标题</label>
                <div class="col-xs-4" style="margin-top: 10px;">
                    <input type="text" id="title" name="title" value="${title}" class="col-xs-12">
                </div>
                <div class="col-xs-3" style="margin-top: 10px;text-align: center;" >
                    <button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">搜  索</button>
                    <a style="margin-left: 15px;" class="btn btn-xs btn-info btn-width"  href="${base}/yqlzc">返 回</a>
                </div>
            </form>

        <div style="position: absolute;left: 5%;">
            <button  class="btn btn-xs btn-primary bbt" type="button" onclick="addComplaint()"><img src="${base}/assets/images/suggest/suggest.png" style="float:left;padding-top: 0px;" >我要发表</button>

        </div>

            <div style="height: 470px;overflow-y: auto;width: 100%;">
            <table class="VirusTable" style="margin-top: 56px;">
                <thead>
                <tr>

                    <th style="width: 5%;"><div>序号</div></th>
                    <th style="width: 22%;"><div>单位名称</div></th>
                    <th style="width: 35%;"><div>标题</div></th>
                    <th style="width: 8%;"><div>浏览量</div></th>
                    <th style="width: 15%;"><div>发表时间</div></th>
                    <th style="width: 8%;"><div>状态</div></th>
                </tr>
                </thead>
                <tbody>
                    [#list page.content as info]
                        <tr>
                            <td>${info_index+1}</td>
                            <td >${info.companyname}</td>
                            <td style="text-align: left;"><a style="text-decoration: none;" href="${base}/businesshare/complaint/${info.id}">${info.title}</a></td>
                            <td>${info.readcount}</td>
                            [#--<td>${info.createtime}</td>--]
                            <td>${(info.createtime?string('yyyy-MM-dd')) }</td>
                            <td>[#if info.state='2']已处理 [#else]未处理 [/#if]</td>
                        </tr>
                    [/#list]

                </tbody>
            </table>
            </div>
            <div style="margin-top: 20px;margin-left: 15px;">
            [#assign pagepath="${base}/businesshare/complaint/list?&title=${title}&"]
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








    function addComplaint() {
        window.location.href=base+"/businesshare/complaint/add";
    }


</script>
</html>