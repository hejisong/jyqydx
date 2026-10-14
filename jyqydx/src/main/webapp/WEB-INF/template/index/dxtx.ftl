
[#include "common/head.ftl"]
<body>
<div class="wrapper">
    <div class="wrapper_bg">
        <img class="wrapper_bg_img" src="${base}/assets/images/mypolicy.png" alt="">
        <div class="wrapper_search" >
            <div class="wrapper_search_title">政策符合性评估</div>
            <div class="wrapper_search_position">
                <input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
                <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;" id="search_companyname" value="${search_companyname}">
                <div class="assess">开始评估</div>
            </div>
        </div>
    </div>


    <div class="wrapper_content">
        <div class="wrapper_content_main">
            <ul class="tab">
[#--                <li class="tab_item active">--]
[#--                    <img class="tab_item_img" src="${base}/assets/images/图标2.png" alt="">--]
[#--                    恭喜您！以下政策免申即享--]
[#--                    <span class="tab_item_count">4</span>--]
[#--                </li>--]
[#--                <li class="tab_item">--]
[#--                    <img class="tab_item_img" src="${base}/assets/images/图标备份.png" alt="">--]
[#--                    补充材料即可兑现--]
[#--                    <span class="tab_item_count">2</span>--]
[#--                </li>--]
                <li class="tab_item active">
                    <img class="tab_item_img" src="${base}/assets/images/图标3备份.png" alt="">
                    <a style="color: black;text-decoration:none" onclick="searchplycomresult('6')" href="javascript:void(0);">企业资质能力提升后可兑现</a>
                    <span class="tab_item_count">[#if improving??]${improving}[#else]0[/#if]</span>
                </li>
                <li class="tab_item">
                    <img class="tab_item_img" src="${base}/assets/images/图标4备份.png" alt="">
                    <a style="color: #B2B2B2;text-decoration:none" onclick="searchplycomresult('5')" href="javascript:void(0);"> 可享受政策预告</a>
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
                            <td>${policylist.policyconditions}</td>
                            <td>${policylist.rdescription}</td>
                            <td>
                                <div class="checkbtn" style="cursor: pointer;" onclick="opendxtxxq('${policylist.companyid}','${policylist.plycomresultdetailid}')">查看现存差距</div>
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
[#--                        <div class="checkbtn" style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看现存差距</div>--]
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
[#--                        <div class="checkbtn"style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看现存差距</div>--]
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
[#--                        <div class="checkbtn"style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看现存差距</div>--]
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
[#--                        <div class="checkbtn"style="cursor: pointer;" onclick="window.open('${base}/dxtxxq','_self')">查看现存差距</div>--]
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
<script type="text/javascript">
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