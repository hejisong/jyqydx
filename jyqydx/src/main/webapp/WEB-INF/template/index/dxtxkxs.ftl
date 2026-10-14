[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">


<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div class="wrapper_content_main"  >
        <div style="margin-top: 10px;width: 60%;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">政策查询-政策预告-查看详情</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 1000px;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #FFF2F2;border-radius: 6px; ">
            <img style="position: absolute;margin-top: 16px;padding-left:15px" src="${base}/assets/images/说明.png">
            <input style="border-radius: 50px;background: #3D91FF;width: 0px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: #DC3838;font-size: 14px;padding: 0" value="!">
            <a style="margin-left: 20px;font-size: 14px;color: #DC3838;">说明：以下政策贵单位已经满足条件，但尚未到约定的兑现时间，需要省市配套资金到位。</a>
        </div>
        <div style="margin-top: 10px">
            <span style="font-weight:bold;font-size:16px;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 1000px;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span style="color: #3F93FF;font-size:16px;">政策文件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策大类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policycategoryname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策小类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policysubclassname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策条件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyconditions}[#if plycomresultdetailentity.patentname??](${plycomresultdetailentity.patentname})[/#if]</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策奖励：</span><span style="font-size: 15px;">${plycomresultdetailentity.rdescription}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">申报条件：</span><span style="font-size: 15px;">${policydetail.declareconditions}</span>
            </div>
        </div>
    </div>
    </div>
</div>
[#--    <div style="width: 1000px;height: 720px;background: #FFFFFF;border-radius: 6px;margin-left: 20% ;padding: 10px 100px">--]

[#--        <div style="margin-top: 20px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="1">--]
[#--            <a style="">申请报告</a>--]
[#--            <img style="position: absolute;margin-top: -9px;padding-left: 820px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="2">--]
[#--            <a style="">营业执照（统一信用代码，企业名称）市场监管局可查询</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 484px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="3">--]
[#--            <a style="">股权投资（基金管理机构）章程或合伙协议（协议规模，首期出资规模</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 388px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="4">--]
[#--            <a style="">管理协议（组织架构）</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 724px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 70px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="5">--]
[#--            <a style="width: 828px;height: 40px;display:inline-block;vertical-align:middle;">基金到位资金证明（公司制股权投资基金：最近一期验资报告【实缴到位注册资本】；合伙制股权投资基金：2.合伙人实缴出资明确书3.银行托管协议【确认盖章】4托管资金到账确认函【确认盖章】5.银行 回单【确认盖章】）</a>--]
[#--            <img style="position: absolute;margin-top: -9px;padding-left: 56px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="6">--]
[#--            <a style="">登记备案证明（备案编码，可以去中国基金业协会查询）</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 484px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="7">--]
[#--            <a style="">产基金募集合法合规说明</a>--]
[#--            <img style="position: absolute;margin-top: 8px;margin-left: 708px" src="${base}/assets/images/djsc.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="8">--]
[#--            <a style="">获得奖励之日起10年内不迁离南京的承诺书</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 578px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="9">--]
[#--            <a style="">声明书（同意将奖励基金划入管理企业，若为基金公司自行管理则将奖励资金划入基金公司）</a>--]
[#--            <img style="position: absolute;margin-top: -9px;margin-left: 228px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 70px;background: #F6F9FE;border-radius: 6px; ">--]
[#--            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="10">--]
[#--            <a style="width: 828px;height: 40px;display:inline-block;vertical-align:middle;">天使投资机构除以上材料外，还需提供已投资早期企业情况和相关证明材料，且已投资金额不低于实缴到位注册资本或实际募集到位资金的30%。</a>--]
[#--            <img style="position: absolute;margin-top: -9px;padding-left: 56px" src="${base}/assets/images/ypp.png">--]
[#--        </div>--]
[#--    </div>--]


</div>


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">
    var base = '${base}';
    function searchplycomresult(type){
        var search_companyid=$("#companyid").val();
        if(search_companyid==null||search_companyid==undefined){
            search_companyid="";
        }
        var searchName=$("#search_companyname").val();
        window.location.href=base+"/zcfhxpg?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
    }
</script>
</html>