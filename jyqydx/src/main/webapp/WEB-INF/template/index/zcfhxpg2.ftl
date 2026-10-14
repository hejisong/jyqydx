
[#include "common/head.ftl"]
<body>
<div class="wrapper">
    <div class="wrapper_bg">
        <img class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
        <div class="wrapper_search">
            <div class="wrapper_search_title">政策符合性评估</div>
            <div class="wrapper_search_position">
                <input type="hidden" id="search_companyid" value="${search_companyid}">
                <input type="hidden" id="hiddentype"  value="3">
                <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;font-size: 16px;" id="search_companyname" value="${search_companyname}">
                <div class="assess" onclick="searchCompanydata()">开始评估</div>
            </div>
        </div>
    </div>


    <div class="wrapper_content">
        <div class="wrapper_content_main">
            <ul class="tab">
                <li class="tab_item">
                    <img class="tab_item_img" src="${base}/assets/images/图标2.png" alt=""href="${base}/zcfhxpg">
                    <a style="color: #B2B2B2;text-decoration:none;font-size: 15px;" onclick="searchplycomresult('1')" href="javascript:void(0);">恭喜您！以下政策免申即享</a>
                    <span class="tab_item_count">[#if withoutnum??]${withoutnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item">
                    <img class="tab_item_img" src="${base}/assets/images/图标备份.png" alt=""href="${base}/zcfhxpg1">
                    <a style="color: #B2B2B2;text-decoration:none;font-size: 15px;" onclick="searchplycomresult('2')" href="javascript:void(0);">补充材料即可兑现</a>
                    <span class="tab_item_count">[#if replenishnum??]${replenishnum}[#else]0[/#if]</span>
                </li>
                <li class="tab_item active" onclick="f">
                    <img class="tab_item_img" src="${base}/assets/images/图标3备份.png" alt="">
                    <a style="color: black;text-decoration:none;font-size: 15px;" onclick="searchplycomresult('3')" href="javascript:void(0);">企业资质能力提升后可兑现</a>
                    <span class="tab_item_count">[#if improving??]${improving}[#else]0[/#if]</span>
                </li>
                <li class="tab_item">
                    <img class="tab_item_img" src="${base}/assets/images/图标4备份.png" alt=""href="${base}/zcfhxpg3">
                    <a style="color: #B2B2B2;text-decoration:none;font-size: 15px;" onclick="searchplycomresult('4')" href="javascript:void(0);">可享受政策预告</a>
                    <span class="tab_item_count">[#if prednum??]${prednum}[#else]0[/#if]</span>
                </li>
            </ul>

            <table class="VirusTable">
                <thead>
                <tr>
                    <th>
                        <div>序号</div>
                    </th>
                    <th>
                        <div>政策文件</div>
                    </th>
                    <th>
                        <div>政策大类</div>
                    </th>
                    <th>
                        <div>政策小类</div>
                    </th>
                    <th>
                        <div>政策条件</div>
                    </th>
                    <th>
                        <div>政策类别</div>
                    </th>
                    <th>
                        <div>政策兑现</div>
                    </th>
                    <th>
                        <div></div>
                    </th>
                </tr>
                </thead>
                <tbody>
                [#if policylists??]
                    [#list policylists as policylist]
                        <tr>
                            <td>${policylist_index+1}</td>
                            <td>${policylist.policyname}</td>
                            <td>${policylist.policycategoryname}</td>
                            <td>${policylist.policysubclassname}</td>
                            <td>${policylist.policyconditions}[#if policylist.patentname??](${policylist.patentname})[/#if]</td>
                            <td>[#list policyfilelists as list][#if list.plfidid==policylist.plfidid]${list.policytype}[/#if][/#list]</td>
                            <td>${policylist.rdescription}</td>
                            <td>
                                <div class="checkbtn" style="cursor: pointer;" onclick="opendxtxxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看当前差距</div>
                            </td>
                        </tr>
                    [/#list]
                [/#if]
[#--                <tr>--]
[#--                    <td>1</td>--]
[#--                    <td>建邺区关于促进和平发展建邺区关于促进和平发展</td>--]
[#--                    <td>落户奖励</td>--]
[#--                    <td>开办奖励</td>--]
[#--                    <td>实缴到位注册资本达...</td>--]
[#--                    <td>奖励30万</td>--]
[#--                    <td>--]
[#--                        <div class="checkbtn" style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看当前差距</div>--]
[#--                    </td>--]
[#--                </tr>--]
[#--                <tr>--]
[#--                    <td>2</td>--]
[#--                    <td>建邺区关于促进和平发展建邺区关于促进和平发展</td>--]
[#--                    <td>经济贡献奖</td>--]
[#--                    <td>股权投资企业</td>--]
[#--                    <td>第1-3年</td>--]
[#--                    <td>对区域经济贡献100对区域经济贡献100%</td>--]
[#--                    <td>--]
[#--                        <div class="checkbtn" style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看当前差距</div>--]
[#--                    </td>--]
[#--                </tr>--]
[#--                <tr>--]
[#--                    <td>3</td>--]
[#--                    <td>建邺区关于促进和平发展建邺区关于促进和平发展</td>--]
[#--                    <td>经济贡献奖</td>--]
[#--                    <td>股权投资企业</td>--]
[#--                    <td>第1-3年</td>--]
[#--                    <td>对区域经济贡献100对区域经济贡献100%</td>--]
[#--                    <td>--]
[#--                        <div class="checkbtn">查看兑现明细</div>--]
[#--                    </td>--]
[#--                </tr>--]
[#--                <tr>--]
[#--                    <td>4</td>--]
[#--                    <td>建邺区关于促进和平发展建邺区关于促进和平发展</td>--]
[#--                    <td>经济贡献奖</td>--]
[#--                    <td>股权投资企业</td>--]
[#--                    <td>第1-3年</td>--]
[#--                    <td>对区域经济贡献100对区域经济贡献100%</td>--]
[#--                    <td>--]
[#--                        <div class="checkbtn">查看兑现明细</div>--]
[#--                    </td>--]
[#--                </tr>--]
                </tbody>
            </table>
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
</script>
</html>