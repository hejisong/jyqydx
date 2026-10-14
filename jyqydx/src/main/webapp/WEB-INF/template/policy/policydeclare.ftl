
[#include "common/head.ftl"]
<body>


<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

                        <div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
                            <div class="wrapper_content_main" style="width: 74%;margin-bottom: 0px;" >

                                        <form action="${base}/policydeclare" method="get">


                                            <div style="padding-top: 2px;margin-bottom: 2px;">


                                                <div style="width: 100%;margin-top: 15px;margin-bottom: 10px;margin-left: 20px;">
                                                    <span style="width:80px;margin-left: 25px;font-weight:bold;font-size: 15px">通知标题</span>
                                                    <input name="declarename"  id="declarename"   style="border: 1px solid #D8D8D8;height: 38px;width: 25%;" value="${declarename}" type="text">
                                                    <span style="width:80px;margin-left: 10px;font-weight:bold;font-size: 15px">申报开始时间</span>
                                                    <input name="startdate" style="border: 1px solid #D8D8D8;height: 38px;width: 16%;" value="[#if startdate??]${(startdate?string('yyyy-MM-dd')) }[/#if]" type="date">
                                                    <span style="width:80px;margin-left: 10px;font-weight:bold;font-size: 15px">申报结束时间</span>
                                                    <input name="enddate" style="border: 1px solid #D8D8D8;height: 38px;width: 16%;" value="[#if enddate??]${(enddate?string('yyyy-MM-dd')) }[/#if]" type="date">



                                                    <button style="width:50px;height: 40px;line-height:38px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="submit">搜索</button>
                                                </div>
                                            </div>
                                        </form>

                        <div style="height: 450px;">

                            <table class="VirusTable"  >
                                <thead>
                                <tr>
                                    <th style="width: 38%;">
                                        <div>标题</div>
                                    </th>
                                    <th style="width: 20%;">
                                        <div>申报时间范围</div>
                                    </th>
                                    <th style="width: 15%;">
                                        <div>发文时间</div>
                                    </th>
                                    <th style="width: 15%;">
                                        <div>适应园区</div>
                                    </th>

                                    <th style="width: 12%;">
                                        <div>操作</div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                [#if page.content??]
                                    [#list page.content as policydeclare]
                                        <tr>
                                            <td style="white-space: normal;line-height: 30px;"> <a style="font-size: 16px;cursor:pointer;text-decoration: none;" href="${base}/policydeclare/webshow/${policydeclare.declareid}">${policydeclare.declarename}</a></td>
                                            <td style="white-space: normal;line-height: 30px;">${(policydeclare.declare_start_date?string('yyyy-MM-dd')) }~${(policydeclare.declare_end_date?string('yyyy-MM-dd')) }</td>
                                            <td style="white-space: normal;line-height: 30px;">${(policydeclare.releasedate?string('yyyy-MM-dd')) }</td>
                                            <td style="white-space: normal;line-height: 30px;">${policydeclare.releaseorgname}</td>
                                            <td style="white-space: normal;line-height: 30px;">
                                                <a style="margin-left: 10px;"  onclick="apply('${policydeclare.declareid}');"  href="javascript:void(0);">申报</a>
                                                <a style="margin-left: 10px;"  href="${base}/policydeclare/webshow/${policydeclare.declareid}">查看</a>
                                            </td>
                                        </tr>
                                    [/#list]
                                [/#if]

                                </tbody>
                            </table>
                        </div>
                        <div style="margin-left: 15px;margin-top: 10px;">
                            [#if startdate??&&enddate??]
                               [#assign pagepath="${base}/policydeclare?declarename=${declarename}&startdate=${(startdate?string('yyyy-MM-dd')) }&enddate=${(enddate?string('yyyy-MM-dd')) }&"]
                            [#elseif startdate??]
                                [#assign pagepath="${base}/policydeclare?declarename=${declarename}&startdate=${(startdate?string('yyyy-MM-dd')) }&"]
                            [#elseif enddate??]
                                [#assign pagepath="${base}/policydeclare?declarename=${declarename}&enddate=${(enddate?string('yyyy-MM-dd')) }&"]
                            [#else]
                                [#assign pagepath="${base}/policydeclare?declarename=${declarename}&"]
                            [/#if]
                            [#include "common/pagemodel.ftl"]
                        </div>

                    </div>

                        </div>
                    </div>

                </div>
            </div>

        </div>


    </div>


</div>
[#include "common/foot.ftl"]
</body>
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">
    var base = '${base}';
    function openinpolicyinfo(policyname){
        window.location.href="${base}/searchNewpolicyinfo?policyname="+policyname;

    }

    function apply(declareid) {
        var companyid=$("#companyid").val();
        window.location.href=base + "/policydeclare/applylist/"+declareid+"?companyid="+companyid;



    }


    $(function(){
        initTime();
    });

    function initTime() {
        $(".form_datetime").datetimepicker({
            autoclose : true,
            format : "yyyy-MM-dd",
            minuteStep : 1,
            minView : 2,
            pickerPosition:'bottom-left',
            language : 'zh_CN'
        });
    }

    function queryByKey() {
        var content=$("#content").val();
        if(content==null||content==""||content==undefined){
            bootbox.alert("请输入关键字！");
        }else{
            window.location.href="${base}/policyfilemgr/queryByKey?content="+content;
        }

    }




</script>
</html>