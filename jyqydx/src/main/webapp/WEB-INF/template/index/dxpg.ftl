
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">



<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main" style="width: 70%;" >
            <ul class="tab">
                <li class="tab_item [#if enjoypolicycategory=='1']active[/#if]">

                <img class="tab_item_img" src="${base}/assets/images/图标2.png" alt=""href="${base}/zcfhxpg">
                    <a style="color:[#if enjoypolicycategory=='1']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresultinfo('${search_companyid}','1','${search_companyname}')" href="javascript:void(0);">恭喜您！以下政策免申即享</a>
                    <span class="tab_item_count">[#if withoutnum??]${withoutnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='2']active[/#if]">

                <img class="tab_item_img" src="${base}/assets/images/图标备份.png" alt=""href="${base}/zcfhxpg1">
                    <a style="color:[#if enjoypolicycategory=='2']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresultinfo('${search_companyid}','2','${search_companyname}')" href="javascript:void(0);">补充材料即可兑现</a>
                    <span class="tab_item_count">[#if replenishnum??]${replenishnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='3']active[/#if]">

                <img class="tab_item_img" src="${base}/assets/images/图标3备份.png" alt="">
                    <a style="color: [#if enjoypolicycategory=='3']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresultinfo('${search_companyid}','3','${search_companyname}')" href="javascript:void(0);">企业资质能力提升后可兑现</a>
                    <span class="tab_item_count">[#if improving??]${improving}[#else]0[/#if]</span>
                </li>
                <li class="tab_item [#if enjoypolicycategory=='4']active[/#if]">

                <img class="tab_item_img" src="${base}/assets/images/图标4备份.png" alt=""href="${base}/zcfhxpg3">
                    <a style="color:[#if enjoypolicycategory=='4']black[#else] #B2B2B2[/#if];text-decoration:none;font-size: 15px;" onclick="searchplycomresultinfo('${search_companyid}','4','${search_companyname}')" href="javascript:void(0);">可享受政策预告</a>
                    <span class="tab_item_count">[#if prednum??]${prednum}[#else]0[/#if]</span>
                </li>
            </ul>

            <div style="height: 490px;overflow-y: auto;">

            <table class="VirusTable">
                <thead>
                <tr>
                    <th style="width: 5%;">
                        <div>序号</div>
                    </th>
                    <th style="width: 20%;">
                        <div>政策文件</div>
                    </th>
                    <th style="width: 10%;">
                        <div>政策大类</div>
                    </th>
                    <th style="width: 10%;">
                        <div>政策小类</div>
                    </th>
                    <th style="width: 20%;">
                        <div>政策条件</div>
                    </th>
                    <th style="width: 15%;">
                        <div>政策类别</div>
                    </th>
                    <th style="width: 20%;">
                        <div>政策兑现</div>
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
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policyname}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.policycategoryname}</td>
                            <td style="white-space: normal;line-height: 30px;text-align: left;">${policylist.policysubclassname}</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.policyconditions}[#if policylist.patentname??](${policylist.patentname})[/#if]</td>
                            <td style="white-space: normal;line-height: 30px;">[#list policyfilelists as list][#if list.plfidid==policylist.plfidid]${list.policytype}[/#if][/#list]</td>
                            <td style="white-space: normal;line-height: 30px;">${policylist.rdescription}</td>
                            <td>  <div class="checkbtn" style="cursor: pointer;margin-left: 12px;width: 80px;" onclick="opendxtxkxs('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看详情</div></td>

                        </tr>
                    [/#list]
                [/#if]


                </tbody>
            </table>
        </div>
    <div style="margin-left: 15px;margin-top: 10px;">
        [#assign pagepath="${base}/dxpg?searchName=${search_companyname}&companyid=${search_companyid}&enjoypolicycategory=${enjoypolicycategory}&"]
        [#include "common/pagemodel.ftl"]
    </div>
        </div>

    </div>


</div>
[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
[#--选择企业--]

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
    //查看查看需补充的材料
    function searchplycomresultinfo(companyid,enjoypolicycategory,enterprisename) {
        window.location.href=base+"/dxpg?searchName="+enterprisename+"&companyid="+companyid+"&enjoypolicycategory="+enjoypolicycategory;
    }

    function indexewmlogin() {
        base = '${base}';
        window.location.href=base+"/indexewmlogin";
    }

    function searchpolicyfife() {
        window.location.href=base+"/newpolicy";
    }

    function searchPolicydeclare() {
        window.location.href=base+"/policydeclare";
    }

    function dxwarn(type) {
        var search_companyid=$("#companyid").val();
        if(search_companyid==null||search_companyid==undefined){
            search_companyid="";
        }
        var searchName=$("#search_companyname").val();
        if(searchName==null||searchName==undefined){
            searchName="";
        }
        window.location.href=base+"/dxwarn?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
    }


    //查看详情
    function opendxtxkxs(companyid,searchId) {
        bootbox.alert("请先登录再查看详情！");
    }
</script>
</html>