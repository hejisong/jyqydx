
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">
<div class="wrapper_bg">
    <img class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
    <div class="wrapper_search" style="top: 15%">
        <div class="wrapper_search_title">政策符合性评估</div>
        <div class="wrapper_search_position">
            <input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
            <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;"  id="search_companyname" value="${search_companyname}">
            <div class="assess">开始评估</div>
        </div>
    </div>
</div>
[#--<div >--]
[#--    <img src="${base}/assets/images/znppxq.png" alt="">--]
[#--    <div style="position: absolute;margin-left: 44.5%;margin-top: -5%">--]
[#--        <div style="width: 204px;height: 48px;font-family: PingFangSC-Medium;font-size: 34px;color: #FFFFFF;">智能匹配详情</div>--]
[#--    </div>--]
[#--</div>--]
<div style="width: 100%;height: 1200px;background: #F3F7FA; padding-top: 20px;">
    <div style="width: 1000px;height: 1250px;background: #FFFFFF;border-radius: 6px;margin-left: 20% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">政策查询-免申即享-兑现明细</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 1000px;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px" src="${base}/assets/images/直通车.png">
            <span style="width: 98px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: #FFFFFF;margin-top: -39px;position:absolute">专属账户直通车</span>

        </div>

        <div style="margin-top: 20px">
            <span style="font-weight:bold;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 1000px;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span style="color: #3F93FF;">政策文件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">政策大类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policycategoryname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">政策小类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policysubclassname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">政策条件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyconditions}[#if plycomresultdetailentity.patentname??](${plycomresultdetailentity.patentname})[/#if]</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">政策奖励：</span><span style="font-size: 15px;">${plycomresultdetailentity.rdescription}</span>
            </div>
        </div>
        <div style="margin-top: 20px">
            <span style="font-weight:bold;">兑现流程</span>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">政策匹配</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021-03-01 12:26:23</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021-03-01 13:24:23</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="background: #3D91FF;width: 1px;height: 60px;border: 0px;margin: -10px 0 0 24px;text-align: center;color: white;font-size: 14px;padding: 0" readonly>

        </div>
        <div style="margin-top: 10px;width: 1000px;height:50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">奖励兑现</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021-03-01 13:28:23</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021-03-01 13:30:24</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="background: #3D91FF;width: 1px;height: 60px;border: 0px;margin: -10px 0 0 24px;text-align: center;color: white;font-size: 14px;padding: 0" readonly>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">票据提交</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021-03-02 09:35:32</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021-03-02 09:37:33</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>
        <div style="margin-top: 20px">
            <span style="font-weight:bold;">付款信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 1000px;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span style="color: #3F93FF;">付款单位：</span><span style="font-size: 15px;" id="paycompany">南京南大电子智慧型服务机</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">付款账户：</span><span style="font-size: 15px;" id="payaccount">18994891852</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">付款时间：</span><span style="font-size: 15px;" id="paytime">2020.03.12</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">付款金额：</span><span style="font-size: 15px;" id="payamount">200万元</span>
            </div>
        </div>
        <div style="margin-top: 20px">
            <span style="font-weight:bold;">收款信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 1000px;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span style="color: #3F93FF;">收款单位：</span><span style="font-size: 15px;" id="collectioncompany">江苏飞鸽传书有限公司收款</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">收款账户：</span><span style="font-size: 15px;"id="collectionaccount">18994891852</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">收款时间：</span><span style="font-size: 15px;"id="collectiontime">2020.03.12</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;">收款金额：</span><span style="font-size: 15px;"id="collectionamount">200万元</span>
            </div>
        </div>
    </div>


</div>


[#include "common/foot.ftl"]
</body>
<input type="hidden" id="plycomresultdetailid" value="${plycomresultdetailentity.plycomresultdetailid}">
[#--[#include "common/foot.ftl"]--]
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
    $(function(){
        var plycomresultdetailid=$("#plycomresultdetailid").val();
        // var plycomresultdetailid="c6b616687fe811eba48d005056a764f2";
        $.ajax({
            url : base + "/interface/plycomresultdetail/withoutdetail",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {plycomresultdetailid:plycomresultdetailid},
            success : function(data) {
                if(data.result){
                    var json = eval(data);
                    $.each(json, function (index, item) {
                        //循环获取数据
                        if(index=="resultObj"){
                            //付款
                            $("#paycompany").html(json[index].payment.paycompany);
                            $("#payaccount").html(json[index].payment.payaccount);
                            $("#paytime").html(json[index].payment.paytime);
                            $("#payamount").html(json[index].payment.payamount+"元");

                            // var paycompany = json[index].payment.paycompany;
                            // var payaccount = json[index].payment.payaccount;
                            // var paytime = json[index].payment.paytime;
                            // var payamount = json[index].payment.payamount;
                            //收款
                            $("#collectioncompany").html(json[index].payment.collectioncompany);
                            $("#collectionaccount").html(json[index].payment.collectionaccount);
                            $("#collectiontime").html(json[index].payment.collectiontime);
                            $("#collectionamount").html(json[index].payment.collectionamount+"元");
                            // var collectioncompany = json[index].payment.collectioncompany;
                            // var collectionaccount = json[index].payment.collectionaccount;
                            // var collectiontime = json[index].payment.collectiontime;
                            // var collectionamount = json[index].payment.collectionamount;
                        }
                        // var name = json[index].collectioncompany;
                        // var idnumber = json[index].collectionaccount;
                        // var sex = json[index].collectionamount;

                    });
                    // $("#username_1").val(arrays.collectioncompany);
                    // $("#linknum_1").val(arrays.collectionaccount);
                    // $("#linktel_1").val(arrays.collectionamount);
                }else{
                    bootbox.alert(data.errorMsg);
                }
            },
            error : function() {
                bootbox.alert("查询企业失败！");
            }
        });
    });

</script>
</html>