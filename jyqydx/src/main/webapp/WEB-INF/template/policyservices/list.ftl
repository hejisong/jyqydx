[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['政策服务管理','政策兑现审核'] modelcode=["zcfwgl","zcfwsh"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"  action="${base}/policyservices/policyservices" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="enterprisename" name="enterprisename" value="${enterprisename}" class="col-xs-12">
			</div>
[#--			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">统一社会信用代码</label>--]
[#--			<div class="col-xs-2">--]
[#--				<input type="text" id="uscreditcode" name="uscreditcode" value="${uscreditcode}" class="col-xs-12">--]
[#--			</div>--]
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">按时间检索</label>
			<div class="col-xs-1">
				<div class="input-group date form_datetime ">
					<input class="form-control input-inline" placeholder=""  name="begintime" id="begintime" data-date-format="yyyy-MM-dd" readonly
						   value="${begintime}" style="border-right: 0px; background-color: white;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;width: 20px">~</label>
			<div class="col-xs-1">
				<div class="input-group date form_datetime ">
					<input class="form-control input-inline" placeholder=""  name="endtime" id="endtime" data-date-format="yyyy-MM-dd" readonly
						   value="${endtime}" style="border-right: 0px; background-color: white;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">审批状态</label>
			<div class="col-xs-1">
				<select class="form-control input-inline" name="cashflag"  id="cashflag" style="height: 28px;padding: 2px">
					<option value="" [#if cashflag=='']selected="selected"[/#if]>全部</option>
					<option value="0" [#if cashflag=='0']selected="selected"[/#if]>已匹配</option>
					<option value="1" [#if cashflag=='1']selected="selected"[/#if]>待审批</option>
					<option value="7" [#if cashflag=='7']selected="selected"[/#if]>审批中</option>
					<option value="2"[#if cashflag=='2']selected="selected"[/#if]>审批通过</option>
					<option value="4"[#if cashflag=='4']selected="selected"[/#if]>审核不通过</option>
					<option value="3"[#if cashflag=='3']selected="selected"[/#if]>已退回</option>
					<option value="6"[#if cashflag=='6']selected="selected"[/#if]>待支付</option>
					<option value="5"[#if cashflag=='5']selected="selected"[/#if]>已支付</option>

				</select>
			</div>
			<div class="col-xs-3"  style="text-align: center;" >
			      <button class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				[#--<button style="margin-left: 30px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openImport();">批量通过</button>--]
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="exportinfo();">导出</button>

				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>
		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					[#--	<th style="width:4%;text-align:center;"><input name="checkall" id="checkall" type="checkbox" onclick="checkAll();"></th>--]
						<th style="width:5%;">序号</th>
						<th style="width:14%;">企业名称</th>
						<th style="width:10%;">统一社会信用代码</th>
						[#--<th style="width:14%;">政策文件</th>--]
						<th style="width:14%;">政策类型</th>
						<th style="width:20%;">政策条件</th>
						<th style="width:10%;">政策奖励</th>
						<th style="width:6%;">申请金额(元)</th>
						<th style="width:8%;">匹配日期</th>
						<th style="width:6%;">状态</th>
						<th style="width:6%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as plylist ]
					<tr >
					[#--	<td>${plylist_index+1}</td>
						<td>${plylist.enterprisename}</td>
						<td>${plylist.uscreditcode}</td>
						--][#--<td>${plylist.policyname}</td>--][#--
						<td>${plylist.policycategoryname}[#if plylist.policysubclassname??&&plylist.policysubclassname!=''] (${plylist.policysubclassname}) [/#if]</td>
						<td>${plylist.policyconditions}</td>
						<td>${plylist.rdescription}</td>
						<td>[#if plylist.flowprocess??&&plylist.flowprocess.SJSPJE??]${plylist.flowprocess.SJSPJE}[#else] ${plylist.amount} [/#if]</td>

						<td style="white-space: normal;line-height: 30px;">[#if plylist.cashflag=='1' ]待审批[#elseif plylist.cashflag=='2']审批通过
							[#elseif plylist.cashflag=='3']已退回[#elseif plylist.cashflag=='4']已驳回
							[#elseif plylist.cashflag=='5']已支付[#elseif plylist.cashflag=='6']兑现完成[/#if]</td>
						<td>[#if plylist.updatetime??]${(plylist.updatetime?string('yyyy-MM-dd')) }[/#if]</td>
						<td>
							<a class="btn btn-xs btn-info btn-width" href="${base}/policyservices/check?searchId=${plylist.plycomresultdetailid}">查 看</a>
						</td>--]

						<td>${plylist_index+1}</td>
						<td>${plylist[1]}</td>
						<td>${plylist[2]}</td>
						[#--<td>${plylist[3]}</td>--]
						<td>${plylist[4]}[#if plylist[5]??&&plylist[5]!=''] (${plylist[5]}) [/#if]</td>
						<td>${plylist[6]}</td>
						<td>${plylist[7]}</td>
						<td>[#if plylist[12]??&&plylist[12].SJSPJE??]${plylist[12].SJSPJE}[#else] ${plylist[8]} [/#if]</td>
						<td>[#if plylist[10]??]${(plylist[10]?string('yyyy-MM-dd')) }[/#if]</td>
						<td style="white-space: normal;line-height: 30px;">[#if plylist[9]=='0' ]已匹配[#elseif plylist[9]=='1' ]待审批[#elseif plylist[9]=='7']审批中[#elseif plylist[9]=='2']审批通过
							[#elseif plylist[9]=='3']已退回[#elseif plylist[9]=='4']审核不通过
							[#elseif plylist[9]=='5']已支付[#elseif plylist[9]=='6']待支付[/#if]</td>

						<td>
							<a class="btn btn-xs btn-info btn-width" href="${base}/policyservices/check?searchId=${plylist[0]}">查 看</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policyservices/policyservices?enterprisename=${enterprisename}&uscreditcode=${uscreditcode}&begintime=${begintime}&endtime=${endtime}&cashflag=${cashflag}&"]
		[#include "common/pagemodel.ftl"]
	</div>
	
</div>
<script type="text/javascript">
	$(function(){
		initTime();
	});
	function initTime(){
		$(".form_datetime").datetimepicker({
			autoclose: true,
			isRTL: Metronic.isRTL(),
			format: "yyyy-mm-dd",
			minuteStep : 1,
			minView : 2,
			pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
			language: 'zh_CN'
		});
	}
	function checkAll(){
		var check=$("input[name='checktable']");
		for(i=0;i<check.length;i++){
			if($("#checkall")[0].checked==false){
				check[i].checked=false;
			}else{
				check[i].checked=true;
			}
		}

	}
	
	function resetinfo() {
       $("#enterprisename").val('');
		$("#begintime").val('');
		$("#endtime").val('');
		$("#cashflag").val('');
		$("#infofrom").submit();
	}
	
	
	function exportinfo() {
		var enterprisename= $("#enterprisename").val();
		var begintime=$("#begintime").val();
		var endtime=$("#endtime").val();
		var cashflag=$("#cashflag").val();
		window.location.href = '${base}/policyservices/infoExport?enterprisename='+enterprisename+"&begintime="+begintime+"&endtime="+endtime+"&cashflag="+cashflag;
		return ;
	}
</script>
[/@layout.main]