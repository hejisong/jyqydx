
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0;">




<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left:17% ;padding: 10px 100px">
        <div style="margin-top: 10px;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">政策查询-兑现明细</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 54%;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <div style="margin-top: 10px;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;width: 113%;height: 50px;" src="${base}/assets/images/直通车.png">
            <span style="width: 132px;height: 20px;font-family: PingFangSC-Semibold;font-size: 16px;color: #FFFFFF;margin-top: -39px;position:absolute">专属账户直通车</span>

        </div>

        <div style="margin-top: 20px;">
            <span class="policyfont" style="font-weight:bold;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 99%;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span class="policyfont" style="color: #3F93FF;">政策文件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyname}</span>
            </div>
            <div style="margin-top: 15px">
                <span class="policyfont" style="color: #3F93FF;">政策大类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policycategoryname}</span>
            </div>
            <div style="margin-top: 15px">
                <span class="policyfont" style="color: #3F93FF;">政策小类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policysubclassname}</span>
            </div>
            <div style="margin-top: 15px">
                <span class="policyfont" style="color: #3F93FF;">政策条件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyconditions}[#if plycomresultdetailentity.patentname??](${plycomresultdetailentity.patentname})[/#if]</span>
            </div>
            <div style="margin-top: 15px">
                <span class="policyfont" style="color: #3F93FF;">政策奖励：</span><span style="font-size: 15px;">${plycomresultdetailentity.rdescription}</span>
            </div>
            <div style="margin-top: 15px">
                <span class="policyfont" style="color: #3F93FF;">申报条件：</span><span style="font-size: 15px;">${policydetail.declareconditions}</span>
            </div>
        </div>
        <div style="margin-top: 20px">
            <span class="policyfont" style="font-weight:bold;">兑现流程</span>
        </div>

        <div>



            [#list flowprocesslist as flowprocess]
                <div style="margin-top: 5px;width: 100%;background: white;border-radius: 6px; ">
                    <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
                    <div class="div-inline" style="font-size: 16px;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];margin-left: 20px;font-weight:600;display:inline-block;width: 130px;margin-bottom: 30px;">${flowprocess.flowname}
                        [#if flowprocess.processstate=="0"] [#else]
                            [#if flowprocess.fid=='5']<span id="bulu" ><a onclick="showdiv('bulu',this);" id="[#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if]" style="text-decoration: none;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];" href="javascript:void(0);">+</a> </span>
                            [#elseif flowprocess.fid=='1'||flowprocess.fid=='6']<span id="huiqian" ><a onclick="showdiv('huiqian',this);" id="[#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if]" style="text-decoration: none;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];" href="javascript:void(0);">+</a></span>
                            [#elseif (flowprocess.fid=='2'||flowprocess.fid=='7')&& flowprocess.processstate=="2"]<span id="upinfo" ><a onclick="showdiv('upinfo',this);" id="[#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if]" style="text-decoration: none;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];" href="javascript:void(0);">+</a></span>
                            [/#if]

                           [#-- [#if (flowprocess.fid=='2'||flowprocess.fid=='7')&&flowprocess.processstate=="1"] <div style="display: block;" id="buludiv"> 凭据附件 </div> [/#if]--]

                        [/#if]
                    </div>
                    <div class="div-inline" style="font-size: 12px;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];margin-left: 20px">[#if flowprocess.processstate=="0"][#else]
                    [#if flowprocess.fid='0'||flowprocess.fid='4']匹配时间：${(flowprocess.createtime?string('yyyy-MM-dd HH:mm:ss')) }
                    [#elseif flowprocess.fid='5']材料提交时间：${(flowprocess.updatetime?string('yyyy-MM-dd HH:mm:ss')) }
                    [#elseif flowprocess.fid='1'||flowprocess.fid='6']审批时间：${(flowprocess.updatetime?string('yyyy-MM-dd HH:mm:ss')) }
                    [#elseif (flowprocess.fid='7'||flowprocess.fid='2')&& flowprocess.processstate=="2" ]凭据上传时间：${(flowprocess.updatetime?string('yyyy-MM-dd HH:mm:ss')) }
                    [#elseif (flowprocess.fid='3'||flowprocess.fid='8')&& flowprocess.processstate=="2" ]兑现时间：${(flowprocess.updatetime?string('yyyy-MM-dd HH:mm:ss')) }[/#if][/#if]</div>
                 [#--   <div class="div-inline" style="font-size: 12px;color: #E98B81;margin-left: 20px">[#if flowprocess.processstate=="0"||flowprocess.processstate=="1"][#else]结束时间：${(flowprocess.updatetime?string('yyyy-MM-dd HH:mm:ss'))}[/#if]</div>--]
                    <div class="div-inline" style="font-size: 14px;color:[#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];position: absolute;right:25%;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder;margin-bottom: 5px;margin-top: 15px;">[#if flowprocess.processstate=="0"]待处理[#elseif flowprocess.processstate=="1"]处理中[#else]已处理[/#if]</div>

                    [#if flowprocess.fid=='5']
                        <div style="display: none;" id="buludiv">
                            [#if plyfileEntityList??]
                                [#list plyfileEntityList as plyfiles]
                                    <div style="margin-top: -16px;width: 93%;border-radius: 6px;background: #F7F9FF;align-items: center; float: left;margin-left: 50px;" name="upfiles">
                                        <div style="margin-left: 15px;width: 100%;float: left;">
                                            <input style="height:20px; line-height:33px; text-align:center;border-radius: 50px;background: #3D91FF;width: 20px;border: 0px;margin: 15px 0 0 15px;color: white;font-size: 14px;"
                                                   value="${plyfiles_index+1}">
                                            <span style="font-size: 15px;color: #0c91e5;">${plyfiles.materialname}</span>

                                        </div>
                                        [#if plyfiles.status==1]
                                            <div style="margin: 15px;width: 100%;float: left;">
                                <span style="float:left;" name="fujian_list${plyfiles_index}">
                                    <input type="hidden" name="fujianlistid"  value="${plyfiles.plyfileid}">
                                    [#if plyfiles.uploadfiles??]
                                        [#list plyfiles.uploadfiles as files]
                                            <span name="fjlist" style="height: 30px;line-height: 33px;">
                                                <span style="margin-left: 15px;display:inline-block;width:800px;font-size: 15px;">${files.filename}</span>
                                                <a class="sel_btn" style="float: right;width:50px;text-align: center;margin-right: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"
                                                   href="${base}/common/file/download/${files.id }" target="_blank"><i style="font-size: 20px;text-align: center;"></i>预览</a>

                                            </span></br>
                                        [/#list]
                                    [/#if]
	                            </span>
                                            </div>
                                        [/#if]
                                    </div>
                                [/#list]
                            [/#if]
                        </div>
                    [#elseif flowprocess.fid=='1'||flowprocess.fid=='6']

                        [#if flowauditinfos??]

                            <div id="huiqiandiv" style="width: 92%;height: auto;border-radius: 6px;margin-left: 50px;margin-top: -14px;display: none; background: #F7F9FF;">
                                <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="width:18%;background-color: #3d91ff;color: white;">审批节点</th>
                                        <th style="width:18%;background-color: #3d91ff;color: white;">审批结果</th>
                                        <th style="width:26%;background-color: #3d91ff;color: white;">审批意见</th>
                                        <th style="width:18%;background-color: #3d91ff;color: white;">审批状态</th>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        [#list flowauditinfos as info]
                                        <td>${info.deptname}</td>
                                        <td>[#if info.auditstate=='0'][#elseif info.auditstate=='1']通过[#elseif info.auditstate=='2']驳回[#else]退回   [/#if]</td>
                                        <td style="overflow: auto;">${info.auditidea}</td>
                                        <td>${info.deptname}[#if info.auditstate=='0']中[#elseif info.auditstate=='1']通过[#elseif info.auditstate=='2']驳回[#else]退回  [/#if]</td>
                                        <td>[#if info.audittime??]${(info.audittime?string('yyyy-MM-dd HH:mm:ss')) }[/#if]</td>
                                    </tr>
                                    [/#list]

                                    </tbody>
                                </table>
                            </div>

                        [/#if]
                    [#elseif flowprocess.fid=='2'||flowprocess.fid=='7']
                        <div  id="upinfodiv" style="margin-top: 10px;width: 92%;height: auto;border-radius: 6px;margin-left: 50px;margin-top: -13px;display: none;  background: #F7F9FF;padding:5px;padding: 15px;">
                            [#if uploadfiles??]
                                <label class="policyfont" style="color: #3F93FF;">附件信息:</label>
                                [#list uploadfiles as uploadfile]
                                      <span style="[#if uploadfile_index>0]margin-left: 70px; [/#if] font-size: 15px;" > ${uploadfile.filename}
                                          <a class="btn btn-xs btn-info "  style="background-color: #3d91ff !important;border-color: #3d91ff !important;height: 35px;padding-top: 4px;margin-top: 1px;width: 50px;"
                                             href="${base}/common/file/download/${uploadfile.id }" target="sss_blank">预览</a> </span> </br>



                                [/#list]
                            [/#if]
                        </div>
                    [/#if]

                </div>


            [/#list]

        </div>


        [#if payinfo??]

            <div style="margin-top: 20px" class="div-check">
                <span >付款信息</span>
            </div>
            <div style="padding:15px;margin-top: 10px;width:100%;background: #F7F9FF;border-radius: 6px; ">
                <div style="">
                    <span class="span-check">付款单位：</span><span style="font-size: 15px;" id="paycompany">${payinfo.outpayname}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款账户：</span><span style="font-size: 15px;" id="payaccount">${payinfo.outpayaccount}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款时间：</span><span style="font-size: 15px;" id="paytime">${payinfo.createtime}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款金额：</span><span style="font-size: 15px;" id="payamount">${payinfo.amount}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款备注：</span><span style="font-size: 15px;" >${payinfo.remark}</span>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">附件：</span><a style="font-size: 15px;" href="${payinfo.filename}" target="_blank">付款回执单</a>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">收款单位：</span><span style="font-size: 15px;" >${payinfo.inpayname}</span>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">收款账户：</span><span style="font-size: 15px;" >${payinfo.inpayaccount}</span>
                </div>


            </div>

        [/#if]
    </div>


</div>


[#include "common/foot.ftl"]
</div>
</div>
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
  /*  $(function(){
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
*/

    function showdiv(infotype,ele) {
        var color=$(ele).attr("id");
        var html='<a style="text-decoration: none;color: '+color+'"  id="'+color+'" onclick="hiddiv(\''+infotype+'\',this);" href="javascript:void(0);">-</a>';
        $("#"+infotype).html(html);
        $("#"+infotype+"div").show();

    }

    function hiddiv(infotype,ele) {
        var color=$(ele).attr("id");
        var html='<a style="text-decoration: none;color: '+color+'" id="'+color+'" onclick="showdiv(\''+infotype+'\',this);" href="javascript:void(0);">+</a>';
        $("#"+infotype).html(html);
        $("#"+infotype+"div").hide();

    }

</script>
</html>