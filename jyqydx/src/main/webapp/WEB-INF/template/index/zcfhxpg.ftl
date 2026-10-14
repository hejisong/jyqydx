
[#include "common/head.ftl"]
<body>
<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 1px;">
        <div class="wrapper_content_main" style="width: 72%;" >

            <ul class="tab">
                <li class="tab_item [#if enjoypolicycategory=='1']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标2.png" alt="" href="${base}/zcfhxpg">
                    <a style="color: [#if enjoypolicycategory=='1']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresult('1')" href="javascript:void(0);">恭喜您！以下政策免申即享</a>
                    <span class="tab_item_count">[#if withoutnum??]${withoutnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='2']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标备份.png" alt=""href="${base}/zcfhxpg1">
                    <a style="color: [#if enjoypolicycategory=='2']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresult('2')" href="javascript:void(0);">补充材料即可兑现</a>
                    <span class="tab_item_count">[#if replenishnum??]${replenishnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='3']active[/#if]" >
                    <img class="tab_item_img" src="${base}/assets/images/图标3备份.png" alt="">
                    <a style="color: [#if enjoypolicycategory=='3']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresult('3')" href="javascript:void(0);">企业资质能力提升后可兑现</a>

                    <span class="tab_item_count">[#if improving??]${improving}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='4']active[/#if]">
                    <img class="tab_item_img" src="${base}/assets/images/图标4备份.png" alt=""href="${base}/zcfhxpg3">
                    <a style="color: [#if enjoypolicycategory=='4']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresult('4')" href="javascript:void(0);">可享受政策预告</a>
                    <span class="tab_item_count">[#if prednum??]${prednum}[#else]0[/#if]</span>
                </li>
            </ul>

            <div style="height: 490px;overflow-y: auto;">

            <table class="VirusTable"  >
                <thead>
                <tr>
                    <th style="width: 5%;">
                        <div>序号</div>
                    </th>
                    <th style="width: 20%;">
                        <div>政策文件</div>
                    </th>
                    <th style="width: 20%;">
                        <div>政策类型</div>
                    </th>

                    <th style="width: 24%;">
                        <div>政策条件</div>
                    </th>

                    <th style="width: 21%;">
                        <div>政策奖励</div>
                    </th>
                    <th style="width: 12%;">
                        <div>操作</div>
                    </th>
                </tr>
                </thead>
                <tbody>
                [#if page.content??]
                    [#list page.content as policylist]
                        <tr>
                            <td style="white-space: normal;line-height: 30px;">${policylist_index+1}</td>
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policyname}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.policycategoryname}[#if policylist.plsubclassname??&&policylist.plsubclassname!=''] (${detail.plsubclassname}) [/#if]</td>
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policyconditions}[#if policylist.patentname??](${policylist.patentname})[/#if]</td>
                            [#--<td style="white-space: normal;line-height: 30px;">[#list policyfilelists as list][#if list.plfidid==policylist.plfidid]${list.policytype}[/#if][/#list]</td>--]
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.rdescription}</td>
                            <td style="white-space: normal;line-height: 30px;text-align: center;">
                                [#if enjoypolicycategory=='2']
                                   [#-- <div class="checkbtn" style="cursor: pointer;margin-left: 8px;width: 68px;" onclick="openznppxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">补充材料</div>--]
                                    <button  class="webbtn"  style="width:70px;"  type="button" onclick="openznppxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">补充材料</button>
                                    [#--<div class="checkbtn" style="cursor: pointer;margin-left: 18px;width: 46px;margin-top: 5px;" onclick="deleteinfo('${policylist.companyid}','${policylist.plycomresultdetailid}')">删除</div>--]

                                    <button class="webbtn"  style="width:50px;"  type="button" onclick="deleteinfo('${policylist.companyid}','${policylist.plycomresultdetailid}')">删除</button>
                                    [#elseif enjoypolicycategory=='3']
                                  [#--  <div class="checkbtn" style="cursor: pointer;margin-left: 5px;width: 80px;" onclick="opendxtxxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看当前差距</div>--]
                                        <button  class="webbtn"  style="width:91px;"  type="button" onclick="opendxtxxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看当前差距</button>
                                [#elseif enjoypolicycategory=='4']
                                [#--    <div class="checkbtn" style="cursor: pointer;margin-left: 12px;width:68px;" onclick="opendxtxkxs('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看详情</div>--]
                                    <button  class="webbtn"  style="width:70px;"  type="button" onclick="opendxtxkxs('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看详情</button>
                                    [#else]
                            [#-- <div class="checkbtn" style="cursor: pointer;width: 80px;margin-left: 5px;" onclick="opendxjd('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看兑现明细</div>--]

                                    <button class="webbtn"  style="width:91px;" type="button" onclick="opendxjd('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看兑现明细</button>
                                [/#if]
                            </td>
                        </tr>
                    [/#list]
                [/#if]

                </tbody>
            </table>
            </div>
            <div style="margin-left: 15px;margin-top: 10px;">
                [#assign pagepath="${base}/zcfhxpg?searchName=${search_companyname}&companyid=${search_companyid}&enjoypolicycategory=${enjoypolicycategory}&"]
                [#include "common/pagemodel.ftl"]
            </div>


        </div>



    </div>


</div>
[#include "common/foot.ftl"]
</body>
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
        // window.location.href=base+"/dxdetail?searchId="+searchId;
        var hiddencompanyid=$("#companyid").val();
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


    //查看查看需补充的材料
    function openznppxq(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
        var isauthor=$("#isauthor").val();
        // window.location.href=base+"/znppxq?searchId="+searchId;
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                [#--window.open('${base}/znppxq','_self')--]
                //查看是否有权限操作
                if(author()){
                    window.location.href=base+"/znppxq?searchId="+searchId;
                } else{
                    bootbox.confirm("当前登录人没有权限操作，只能查看相关信息，如需进行补充材料操作请先授权！",function (result) {
                        if(result==true){
                            window.location.href=base+"/znppxqshow?searchId="+searchId;
                        }

                    })
                }


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
        // window.location.href=base+"/dxtxxq?searchId="+searchId;
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
        // window.location.href=base+"/dxtxkxs?searchId="+searchId;
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

    function deleteinfo(companyid,searchId) {
        var hiddencompanyid=$("#companyid").val();
        if(hiddencompanyid!=undefined&&hiddencompanyid!=null&&hiddencompanyid!=""){
            if(hiddencompanyid==companyid){
                bootbox.confirm("确认删除?",function (result) {
                    if(result==true){
                        window.location.href=base+"/deleteznppxq?searchId="+searchId;
                    }

                })

            }else{
                bootbox.alert("绑定的企业不符");
            }
        }else{
            bootbox.alert("请登录后继续查看");
        }
    }


    function author() {
        var usercompanyid=$("#usercompanyid").val();
        var isauther=false;
        $.ajax({
            url : base + "/isauthor",
            type : "POST",
            async : false,
            datatype : 'json',
            data : {usercompanyid:usercompanyid},
            success : function(data) {
                if(data=="0"||data=='1'){
                    isauther=true;
                    return isauther;
                }else{
                    return isauther;
                }
            },
            error : function() {
                bootbox.alert("数据加载失败！");
            }
        });
        return isauther;
    }



</script>
</html>