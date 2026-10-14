[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策服务管理','政策兑现审核'] modelcode=["zcfwgl","zcfwsh"] otherjs=jss othercss=css]

	<div style="width: 100%;background: #F3F7FA; padding-top: 20px;">
		  <div style="width: 80%;background: #FFFFFF;border-radius: 6px;margin-left:10% ;padding: 10px 100px;">

            <div style="margin-top: 10px;width: 100%;height: 50px;border-radius: 6px;text-align: center; ">
                <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 20px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">${plycomresultdetailentity.enterprisename}（[#if plycomresultdetailentity.uscreditcode??]${plycomresultdetailentity.uscreditcode}[#else] ${companyEntity.uscreditcode}[/#if]）</a>
            </div>

			  <div style="margin-top: 20px" class="div-check">
				  <span >政策信息</span>
			  </div>
			  <div style="padding:15px;margin-top: 10px;width: 100%;background: #F7F9FF;border-radius: 6px; ">
				  <div style="">
					  <span class="span-check">政策文件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyname}</span>
				  </div>
				  <div style="margin-top: 15px">
					  <span class="span-check">政策大类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policycategoryname}</span>
				  </div>
				  <div style="margin-top: 15px">
					  <span class="span-check">政策小类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policysubclassname}</span>
				  </div>
				  <div style="margin-top: 15px">
					  <span class="span-check">政策条件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyconditions}[#if plycomresultdetailentity.patentname??](${plycomresultdetailentity.patentname})[/#if]</span>
				  </div>
				  <div style="margin-top: 15px">
					  <span class="span-check">政策奖励：</span><span style="font-size: 15px;">${plycomresultdetailentity.rdescription}</span>
				  </div>
				  <div style="margin-top: 15px">
					  <span class="span-check">申报条件：</span><span style="font-size: 15px;">${policydetail.declareconditions}</span>
				  </div>
			  </div>


			  <div style="margin-top: 20px" class="div-check">
				  <span >附件信息</span>
			  </div>


			  <div id="plyfilesData">
				  [#if plyfileEntityList??]
					  [#list plyfileEntityList as plyfiles]
						  <div style="margin-top: 10px;width: 100%;border-radius: 6px;background: #F7F9FF;align-items: center; float: left;" name="upfiles">
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




			  <div style="margin-top: 20px" class="div-check">
				  <span >审批信息</span>
			  </div>

			  [#if flowauditinfos??]

			  <div style="margin-top: 10px;width: 100%;height: auto;border-radius: 6px; ">
				  <table id="sample-table-1" class="table table-striped table-bordered table-hover">
					  <thead>
					  <tr>
						  <th style="width:20%;background-color: #3d91ff;">审批部门</th>
						[#--  <th style="width:15%;background-color: #3d91ff;">审批人</th>
						  <th style="width:15%;background-color: #3d91ff;">审批金额</th>--]
						  <th style="width:15%;background-color: #3d91ff;">审批结果</th>
						  <th style="width:20%;background-color: #3d91ff;">审批意见</th>
						  <th style="width:15%;background-color: #3d91ff;">审批时间</th>
					  </tr>
					  </thead>
					  <tbody>
					  <tr >
						  [#list flowauditinfos as info]
						  <td>${info.deptname}</td>
						[#--  <td>${info.auditpername}</td>
						  <td>${info.auditamount}</td>--]
						  <td>[#if info.auditstate=='1']同意[#elseif info.auditstate=='2']驳回[#else]退回   [/#if]</td>
						  <td>${info.auditidea}</td>
						  <td>[#if info.audittime??]${(info.audittime?string('yyyy-MM-dd HH:mm:ss')) }[/#if]</td>
					  </tr>
					  [/#list]

					  </tbody>
				  </table>
			  </div>

			  [/#if]



			  <div  style="text-align: center;margin-top: 20px;margin-bottom:20px;">
				  <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 100px;background-color: #3D90FF;color: white;height: 30px;width: 50px;" onclick="javascript:history.back(-1);">返回</button>
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

    </script>
[/@layout.main]