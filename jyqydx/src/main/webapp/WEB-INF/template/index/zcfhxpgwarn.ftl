
[#include "common/head.ftl"]
<body>
<div class="wrapper" style="height: 800px;">
    <div class="wrapper_bg">
        <img class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
        <div class="wrapper_search">
            <div class="wrapper_search_title">政策符合性评估</div>
            <div class="wrapper_search_position">
                <input type="hidden" id="search_companyid" value="${search_companyid}">
                <input type="hidden" id="hiddentype"  value="1">
                <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;font-size: 16px;" id="search_companyname" value="${search_companyname}" >
                <div class="assess" onclick="searchCompanydata()">开始评估</div>
            </div>
        </div>
    </div>


    <div class="wrapper_content">
        <div class="wrapper_content_main" >

            <ul class="tab">
                <li class="tab_item [#if enjoypolicycategory=='1']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标2.png" alt="" href="${base}/zcfhxpg">
                    <a style="color: [#if enjoypolicycategory=='1']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="dxwarn('1')" href="javascript:void(0);">恭喜您！以下政策免申即享</a>
                    <span class="tab_item_count">[#if withoutnum??]${withoutnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='2']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标备份.png" alt=""href="${base}/zcfhxpg1">
                    <a style="color: [#if enjoypolicycategory=='2']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="dxwarn('2')" href="javascript:void(0);">补充材料即可兑现</a>
                    <span class="tab_item_count">[#if replenishnum??]${replenishnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='3']active[/#if]" >
                    <img class="tab_item_img" src="${base}/assets/images/图标3备份.png" alt="">
                    <a style="color: [#if enjoypolicycategory=='3']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="dxwarn('3')" href="javascript:void(0);">企业资质能力提升后可兑现</a>

                    <span class="tab_item_count">[#if improving??]${improving}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='4']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标4备份.png" alt=""href="${base}/zcfhxpg3">
                    <a style="color: [#if enjoypolicycategory=='4']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="dxwarn('4')" href="javascript:void(0);">可享受政策预告</a>
                    <span class="tab_item_count">[#if prednum??]${prednum}[#else]0[/#if]</span>
                </li>
            </ul>

            <div style="height: 450px;">

            <table class="VirusTable"  >
                <thead>
                <tr>
                    <th style="width: 5%;">
                        <div>序号</div>
                    </th>
                   [#-- <th style="width: 23%;">
                        <div>政策文件</div>
                    </th>--]
                    <th style="width: 15%;">
                        <div>政策大类</div>
                    </th>
                    <th style="width: 15%;">
                        <div>政策小类</div>
                    </th>
                    <th style="width: 22%;">
                        <div>政策条件</div>
                    </th>

                    <th style="width: 10%;">
                        <div>政策兑现</div>
                    </th>
                    <th style="width: 8%;">
                        <div>兑现金额</div>
                    </th>
                    <th style="width: 8%;">
                        <div>兑现状态</div>
                    </th>
                    <th style="width: 9%;">
                        <div>审批/兑现时间</div>
                    </th>
                    <th style="width: 10%;">
                        <div>操作</div>
                    </th>
                </tr>
                </thead>
                <tbody>
                [#if page.content??]
                    [#list page.content as policylist]
                        <tr>
                            <td style="white-space: normal;line-height: 30px;">${policylist_index+1}</td>
                         [#--   <td style="white-space: normal;line-height: 30px;">${policylist.policyname}</td>--]
                            <td style="white-space: normal;line-height: 30px;">${policylist.policycategoryname}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.policysubclassname}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.policyconditions}[#if policylist.patentname??](${policylist.patentname})[/#if]</td>
                            [#--<td style="white-space: normal;line-height: 30px;">[#list policyfilelists as list][#if list.plfidid==policylist.plfidid]${list.policytype}[/#if][/#list]</td>--]
                            <td style="white-space: normal;line-height: 30px;">${policylist.rdescription}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.amount}</td>
                            <td style="white-space: normal;line-height: 30px;">[#if policylist.cashflag=='1' ]已审批[#else]已兑现[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;">[#if policylist.cashtime??]${policylist.cashtime}[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;">

                                [#if enjoypolicycategory=='3']
                                    <div class="checkbtn" style="cursor: pointer;margin-left: 12px;" onclick="opendxtxxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看当前差距</div>
                                [#elseif enjoypolicycategory=='4']
                                    <div class="checkbtn" style="cursor: pointer;margin-left: 12px;" onclick="opendxtxkxs('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看详情</div>
                                [#else]
                                    <div class="checkbtn"style="cursor: pointer;width: 90px;margin-left: 10px;" onclick="opendxjd('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看兑现明细</div>
                                [/#if]
                            </td>
                        </tr>
                    [/#list]
                [/#if]

                </tbody>
            </table>
            </div>
            <div style="margin-top: 10px;">
                [#assign pagepath="${base}/dxwarn?searchName=${search_companyname}&companyid=${search_companyid}&enjoypolicycategory=${enjoypolicycategory}&"]
                [#include "common/pagemodel.ftl"]
            </div>


        </div>



    </div>


</div>
[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
[#--选择企业--]
[#include "index/selectcompany.ftl"]
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
    //查看兑换明细
    function opendxjd(companyid,searchId) {
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


    //政策查询返回政策评估列表
    function dxwarn(type){
        var search_companyid=$("#search_companyid").val();
        if(search_companyid==null||search_companyid==undefined){
            search_companyid="";
        }
        var searchName=$("#search_companyname").val();
        if(searchName==null||searchName==undefined){
            searchName="";
        }
        window.location.href=base+"/dxwarn?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
    }


    //查看查看需补充的材料
    function openznppxq(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
        // window.location.href=base+"/znppxq?searchId="+searchId;
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/znppxq','_self')--]
                window.location.href=base+"/znppxq?searchId="+searchId;
            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }

    //查看当前差距
    function opendxtxxq(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/dxtxxq','_self')--]
                window.location.href=base+"/dxtxxq?searchId="+searchId;
            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }

    //查看详情
    function opendxtxkxs(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/dxtxkxs','_self')--]
                window.location.href=base+"/dxtxkxs?searchId="+searchId;
            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }


</script>
</html>