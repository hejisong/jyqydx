[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策服务管理','政策兑现支付'] modelcode=["zcfwgl","zcdxzf"] otherjs=jss othercss=css ]
    [#include "common/loading.ftl"]
    <style>

        .div-inline{ display:inline}


    </style>
    <div style="width: 100%;height: auto;background: #F3F7FA; padding-top: 20px;">
        <div style="width: 80%;height: auto;background: #FFFFFF;border-radius: 6px;margin-left: 10% ;padding: 10px 100px">
            <div style="margin-top: 20px" class="div-check">
                <span >政策信息</span>
            </div>
            <div style="padding:15px;margin-top: 10px;width: 100%;background: #F7F9FF;border-radius: 6px; ">
                <div style="">
                    <span class="span-check">政策文件：</span><span style="font-size: 15px;">${plydetail.policyname}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">政策大类：</span><span style="font-size: 15px;">${plydetail.policycategoryname}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">政策小类：</span><span style="font-size: 15px;">${plydetail.policysubclassname}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">政策条件：</span><span style="font-size: 15px;">${plydetail.policyconditions}[#if plydetail.patentname??](${plydetail.patentname})[/#if]</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">政策奖励：</span><span style="font-size: 15px;">${plydetail.rdescription}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">申报条件：</span><span style="font-size: 15px;">${policydetail.declareconditions}</span>
                </div>
            </div>


            <div style="margin-top: 20px" class="div-check">
                <span >兑现流程</span>
            </div>


            [#list flowprocesslist as flowprocess]
                <div style="margin-top: 5px;width: 100%;background: white;border-radius: 6px; ">
                    <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
                    <div class="div-inline" style="font-size: 16px;color: [#if flowprocess.processstate=="0"]#7f8184 [#elseif flowprocess.processstate=="1" ]#51C0A0[#else]#3F93FF[/#if];margin-left: 20px;font-weight:600;display:inline-block;width: 130px;margin-bottom: 30px;">${flowprocess.flowname}
                        [#if flowprocess.processstate=="0"] [#else]
                            [#if flowprocess.fid=='5']<span id="bulu" ><a onclick="showdiv('bulu');" style="text-decoration: none;" href="javascript:void(0);">+</a> </span>
                            [#elseif flowprocess.fid=='1'||flowprocess.fid=='6']<span id="huiqian" ><a onclick="showdiv('huiqian');" style="text-decoration: none;" href="javascript:void(0);">+</a></span>
                            [#elseif (flowprocess.fid=='2'||flowprocess.fid=='7')&& flowprocess.processstate=="2"]<span id="upinfo" ><a onclick="showdiv('upinfo');" style="text-decoration: none;" href="javascript:void(0);">+</a></span>
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
                                                   href="${base}/doc/upload_${files.id}.${files.fileext}" target="_blank"><i style="font-size: 20px;text-align: center;"></i>预览</a>

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
                                        <td>[#if info.auditstate=='0']审批中[#elseif info.auditstate=='1']同意[#elseif info.auditstate=='2']驳回[#else]退回   [/#if]</td>
                                        <td>${info.auditidea}</td>
                                        <td>${info.deptname}[#if info.auditstate=='0']中[#elseif info.auditstate=='1']通过[#elseif info.auditstate=='2']驳回[#else]退回  [/#if]</td>
                                        <td>[#if info.audittime??]${(info.audittime?string('yyyy-MM-dd HH:mm:ss')) }[/#if]</td>
                                    </tr>
                                    [/#list]

                                    </tbody>
                                </table>
                            </div>

                        [/#if]
                    [#elseif flowprocess.fid=='2'||flowprocess.fid=='7']
                        <div  id="upinfodiv" style="margin-top: 10px;width: 92%;height: auto;border-radius: 6px;margin-left: 50px;margin-top: -25px;display: none;  background: #F7F9FF;padding:5px;">
                            [#if uploadfiles??]
                                <label class="policyfont" style="color: #3F93FF;">附件信息:</label>
                                [#list uploadfiles as uploadfile]
                                    <span style="[#if uploadfile_index>0]margin-left: 70px; [/#if] font-size: 15px;" > ${uploadfile.filename}
                                          <a  class="btn btn-xs btn-info "  style="margin-top: 10px;" href="${base}/common/file/download/${uploadfile.id }" target="sss_blank">预览</a> </span> </br>

                                [/#list]
                            [/#if]
                        </div>
                    [/#if]

                </div>


            [/#list]



            <div style="margin-top: 20px" class="div-check">
                <span >收款信息</span>
            </div>
            <div style="padding:15px;margin-top: 10px;width: 100%;background: #F7F9FF;border-radius: 6px; ">
                <div style="">
                    <span class="span-check">收款单位：</span><span style="font-size: 15px;" id="collectioncompany">${company.enterprisename}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">收款账户：</span><span style="font-size: 15px;"id="collectionaccount">${company.account}</span>
                </div>

            </div>


            <div style="margin-top: 20px" class="div-check">
                <span >付款信息</span>
            </div>
            <div style="padding:15px;margin-top: 10px;width:100%;background: #F7F9FF;border-radius: 6px; height: 220px;">
                <div style="">
                    <span class="span-check">付款单位：</span><span style="font-size: 15px;" id="paycompany">${payname}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款账户：</span><span style="font-size: 15px;" >${payaccount}</span>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">付款金额（元）：</span><span style="font-size: 15px;" >${plydetail.amount}</span>
                </div>

                <div style="margin-top: 15px;">
                    <label style="width: 55px;float: left;" class="span-check"><span style="color: red;">*</span>备注：</label>
                    <input type="hidden" id="paymoney" value="${plydetail.amount}" >
                    <input type="hidden" id="outpayname" value="${payname}" >
                    <input type="hidden" id="payaccount" value="${payaccount}" >
                    <textarea style="float: left;width: 90%;" rows="4" class="form-control" id="remark" ></textarea>
                </div>

            </div>

            <div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
                <button style="border:0;font-size: 20px;text-align: center;margin-top: 10px;margin-left: 300px;background-color: #3D90FF;color: white;width:100px;height: 40px;border-radius: 6px;"  onclick="payamount()">支 付</button>
                <button style="border:0;font-size: 20px;text-align: center;margin-top: 10px;margin-left: 200px;background-color: #3D90FF;color: white;width:100px;height: 40px;border-radius: 6px;" onclick="javascript:history.back(-1);">返 回</button>
            </div>
        </div>


    </div>
    <script type="text/javascript">
        $(function () {
            initTime();
        });

        function initTime() {
            $(".form_datetime").datetimepicker({
                autoclose: true,
                isRTL: Metronic.isRTL(),
                format: "yyyy-mm-dd hh:mm:ss",
                minuteStep: 0,
                minView: 0,
                pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
                language: 'zh_CN'
            });
        }

        function payamount(){
            // bootbox.alert("qid:"+qid);
            var qid='${plydetail.plycomresultdetailid}';
            var paymoney=$("#paymoney").val();
            var remark=$("#remark").val();
            var outpayname=$("#outpayname").val();
            var payaccount=$("#payaccount").val();
            if(remark==''){
                bootbox.alert("必须填写备注信息！");
            }else{
                $("#loadingbg").show();
                $("#loading").show();
                $.ajax({
                    url : base + "/policyservices/payamount",
                    type : "POST",
                    async : true,
                    timeout:30000,
                    datatype : 'json',
                    data : {qid:qid,paymoney:paymoney,remark:remark,outpayname:outpayname,payaccount:payaccount},
                    success : function(data) {
                        if(data[0]==1){
                            if(data[1]=="ok"){
                                var url=data[2];
                                // window.location.href=url;
                                // var url=base+"/image/TRANS20210413200729020AUTO555.pdf";

                                bootbox.confirm("支付成功！是否查看付款回执单？",function (result) {
                                    if(result==true){
                                        window.open(url);
                                    }else{
                                        window.location.href=base+"/policyservices/pplaceonfiledetail/"+qid;
                                    }

                                })

                                window.location.href=base+"/policyservices/pplaceonfiledetail/"+qid;


                            }
                        }else if(data[1]=="error"){
                            bootbox.alert(data[0]);
                        }
                    },
                    error : function() {
                        bootbox.alert("支付失败！");
                    }
                });

            }

        }

        function showdiv(infotype) {
        var html='<a onclick="hiddiv(\''+infotype+'\');" href="javascript:void(0);">-</a>';
            $("#"+infotype).html(html);
            $("#"+infotype+"div").show();

        }

        function hiddiv(infotype) {
            var html='<a onclick="showdiv(\''+infotype+'\');" href="javascript:void(0);">+</a>';
            $("#"+infotype).html(html);
            $("#"+infotype+"div").hide();

        }

    </script>
[/@layout.main]