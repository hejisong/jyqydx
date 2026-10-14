
[#include "common/head.ftl"]
<body>


<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 1px;">
        <div class="wrapper_content_main" style="width: 74%;" >
            <ul class="tab">
                <li class="tab_item active" style="font-size: 16px;color: #999999!important;">
                    <img class="tab_item_img" src="${base}/assets/images/图标5.png" alt="">
                    我的兑现<span class="tab_item_count" style="margin-left: 5px;">${infocount}</span>
                </li>

            </ul>
            <div style="height: 470px;overflow-y: auto;">
            <table class="VirusTable">
                <thead>
                <tr>
                    <th style="width: 5%;text-align: center;">
                        <div>序号</div>
                    </th>
                  [#--  <th style="width: 20%;">
                        <div>政策文件</div>
                    </th>--]
                    <th style="width: 18%;text-align: center;">
                        <div>政策类型</div>
                    </th>

                    <th style="width: 23%;text-align: center;">
                        <div>政策条件</div>
                    </th>
                    <th style="width: 19%;text-align: center;">
                        <div>政策兑现</div>
                    </th>
                    <th style="width: 10%;text-align: center;">
                        <div>实际兑现金额</div>
                    </th>
                    <th style="width: 7%;text-align: center;">
                        <div>状态</div>
                    </th>
                    <th style="width: 11%;text-align: center;">
                        <div>匹配日期</div>
                    </th>
                    <th style="width: 7%;text-align: center;">
                        <div>操作</div>
                    </th>
                </tr>

                </thead>
                <tbody>
                [#if page.content??]
                    [#list page.content as policylist]
                        <tr>
                            <td style="white-space: normal;line-height: 30px;">${policylist_index+1}</td>
                           [#-- <td style="white-space: normal;line-height: 30px;">${policylist.policyname}</td>--]
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policycategoryname}[#if policylist.plsubclassname??&&policylist.plsubclassname!=''] (${detail.plsubclassname}) [/#if]</td>
                            [#--<td style="white-space: normal;line-height: 30px;">${policylist.policysubclassname}</td>--]
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policyconditions}[#if policylist.patentname??](${policylist.patentname})[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.rdescription}</td>
                            <td style="white-space: normal;line-height: 30px;text-align: center;">[#if policylist.cashflag=='2' || policylist.cashflag=='5' || policylist.cashflag=='6' ]${policylist.amount}[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;text-align: center;">[#if policylist.cashflag=='1' ]待审批[#elseif policylist.cashflag=='7']审批中[#elseif policylist.cashflag=='2']审批通过
                                [#elseif policylist.cashflag=='3']已退回[#elseif policylist.cashflag=='4']已驳回
                                [#elseif policylist.cashflag=='5']已支付[#elseif policylist.cashflag=='6']已上传凭证[/#if]</td>
                            <td style="text-align: center;">[#if policylist.inputtime??]${(policylist.inputtime?string('yyyy-MM-dd')) }[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;text-align: center;">
                                [#if policylist.cashflag=='2']  <button style="width:70px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;margin-right: 2px;" type="button" onclick="openupinfo('${policylist.companyid}','${policylist.plycomresultdetailid}')">上传凭据</button>[/#if]

                                <button style="width:50px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 3px;border: aliceblue;" type="button" onclick="openwoddxjd('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看</button>




                            </td>
                        </tr>

                    [/#list]
                [/#if]

                </tbody>
            </table>
            </div>
            <div style="margin-top: 34px;margin-left: 15px;">
                [#assign pagepath="${base}/wddx?searchName=${search_companyname}&companyid=${search_companyid}&"]
                [#include "common/pagemodel.ftl"]
            </div>
        </div>



    </div>

</div>
[#include "common/foot.ftl"]
    [#include "index/selectcompany.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<script type="text/javascript">
    $(function () {
        $("td").on("mouseenter",function() {
            if (this.offsetWidth < this.scrollWidth) {
                var that = this;
                var text = $(this).text();
                layer.tips(text, that,{
                    tips: 1,
                    time: 2000         //设置显示时间
                });
            }
        });
    })
    var base = '${base}';
    //查看进度
    function openwoddxjd(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
         // window.location.href=base+"/dxdetail?searchId="+searchId;
      if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/dxjd','_self');--]
                window.location.href=base+"/dxdetail?searchId="+searchId;
            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }


    function opendxjd(companyid,searchId) {
        // window.location.href=base+"/dxjd?searchId="+searchId;
        var hiddencompanyid=$("#companyid").val();
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/dxjd','_self');--]
                window.location.href=base+"/dxjd?searchId="+searchId;
            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }


    function openupinfo(companyid,searchId) {
            var hiddencompanyid=$("#companyid").val();
            // window.location.href=base+"/upinfo?searchId="+searchId;
            if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
                if(hiddencompanyid==companyid){
                    [#--window.open('${base}/znppxq','_self')--]
                    window.location.href=base+"/upinfo?searchId="+searchId;
                }else{
                    bootbox.alert("绑定的企业不符");
                }
            }else{
                bootbox.alert("请登录后继续查看");
            }

    }
</script>
</html>