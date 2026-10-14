[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策服务管理','政策兑现归档'] modelcode=["zcfwgl","zcdxgd"] otherjs=jss othercss=css]
    <style>

        .div-inline{ display:inline}


    </style>
    <div style="width: 100%;height: auto;background: #F3F7FA; padding-top: 20px;">
        <div style="width: 80%;height: auto;background: #FFFFFF;border-radius: 6px;margin-left: 10% ;padding: 10px 100px">
            <div style="margin-top: 20px" class="div-check">
                <span >政策信息</span>
            </div>
            <div style="padding:15px;margin-top: 10px;width: 100%;background: #F7F9FF;border-radius: 6px; " >
                <div >
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
                                    <div style="margin-top: -20px;width: 100%;border-radius: 6px;background: #F7F9FF;align-items: center; float: left;margin-left: 50px;" name="upfiles">
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
                                                <a class="sel_btn" style="float: right;width:50px;text-align: center;margin-right: 30px;height: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"
                                                   href="${base}/common/file/download/${files.id}" target="_blank"><i style="font-size: 20px;text-align: center;"></i>预览</a>

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

                            <div id="huiqiandiv" style="margin-top: 10px;width: 79%;height: auto;border-radius: 6px;margin-left: 50px;margin-top: -10px;display: none; ">
                                <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批节点</th>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批结果</th>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批意见</th>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批状态</th>
                                        <th style="width:20%;background-color: #3d91ff;color: white;">审批时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        [#list flowauditinfos as info]
                                        <td>${info.deptname}</td>
                                        <td>[#if info.auditstate=='0'][#elseif info.auditstate=='1']通过[#elseif info.auditstate=='2']驳回[#else]退回   [/#if]</td>
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
                    <span class="span-check">付款时间：</span><span style="font-size: 15px;" id="paytime">${(payinfo.createtime?string('yyyy-MM-dd HH:mm:ss')) }</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款金额：</span><span style="font-size: 15px;" id="payamount">${payinfo.amount}</span>
                </div>
                <div style="margin-top: 15px">
                    <span class="span-check">付款备注：</span><span style="font-size: 15px;" id="payamount">${payinfo.remark}</span>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">附件：</span><a style="font-size: 15px;" href="${payinfo.filename}" target="_blank">付款回执单</a>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">收款单位：</span><span style="font-size: 15px;" id="payamount">${payinfo.inpayname}</span>
                </div>

                <div style="margin-top: 15px">
                    <span class="span-check">收款账户：</span><span style="font-size: 15px;" id="payamount">${payinfo.inpayaccount}</span>
                </div>


            </div>

            [/#if]
            <div style="margin-top: 0px;height: 50px;border-radius: 6px; ">
                <button style="border:0;font-size: 20px;text-align: center;margin-top: 10px;margin-left: 46%;background-color: #3D90FF;color: white;width:100px;height: 40px;border-radius: 6px;" onclick="javascript:history.back(-1);">返 回</button>
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
            $.ajax({
                url : base + "/policyservices/payamount",
                type : "POST",
                async : false,
                timeout:30000,
                datatype : 'json',
                data : {qid:qid},
                success : function(data) {
                    if(data[0]==1){
                        if(data[1]=="ok"){
                            var url=data[2];
                            // window.location.href=url;
                            // var url=base+"/image/TRANS20210413200729020AUTO555.pdf";
                            window.open(url);

                        }
                    }
                },
                error : function() {
                    bootbox.alert("！");
                }
            });
        }

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
[/@layout.main]