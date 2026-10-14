[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['政策服务管理','政策兑现支付'] modelcode=["zcfwgl","zcdxzf"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"  action="${base}/policyservices/policypayments" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="enterprisename" name="enterprisename" value="${enterprisename}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">按时间检索</label>
			<div class="col-xs-1">
				<div class="input-group date form_datetime ">
					<input class="form-control input-inline" placeholder=""  name="begintime" id="begintime" data-date-format="yyyy-MM-dd" readonly
						   value="${begintime}" style="border-right: 0px; background-color: white;height: 32px;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;width: 30px">~</label>
			<div class="col-xs-1">
				<div class="input-group date form_datetime ">
					<input class="form-control input-inline" placeholder=""  name="endtime" id="endtime" data-date-format="yyyy-MM-dd" readonly
						   value="${endtime}" style="border-right: 0px; background-color: white;height: 32px;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">支付状态</label>
			<div class="col-xs-1">
				<select class="form-control input-inline" name="cashflag" id="cashflag" style="height: 28px;padding: 2px">
					<option value="" [#if cashflag=='']selected="selected"[/#if]>全部</option>
					[#--<option value="0" [#if cashflag=='0']selected="selected"[/#if]>已匹配</option>
					<option value="1" [#if cashflag=='1']selected="selected"[/#if]>待审批</option>
					<option value="7" [#if cashflag=='7']selected="selected"[/#if]>审批中</option>
					<option value="4"[#if cashflag=='4']selected="selected"[/#if]>审核不通过</option>
					<option value="3"[#if cashflag=='3']selected="selected"[/#if]>已退回</option>--]
					<option value="2"[#if cashflag=='2']selected="selected"[/#if]>待上传凭证</option>
					<option value="6"[#if cashflag=='6']selected="selected"[/#if]>待支付</option>
					<option value="5"[#if cashflag=='5']selected="selected"[/#if]>已支付</option>

				</select>
			</div>
			<div class="col-xs-3"  style="text-align: center;">
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="payall();">批量支付</button>
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
						<th style="width:4%;text-align:center;"><input name="checkall" id="checkall" type="checkbox" onclick="checkAll();"></th>
						<th style="width:5%;">序号</th>
						<th style="width:10%;">企业名称</th>
						<th style="width:8%;">统一社会信用代码</th>
						<th style="width:8%;">企业收款账号</th>
				[#--		<th style="width:15%;">政策文件</th>--]
						<th style="width:8%;">政策类型</th>
						<th style="width:15%;">政策条件</th>
						<th style="width:8%;">政策奖励</th>
						<th style="width:8%;">实际兑现金额</th>
						<th style="width:8%;">匹配时间</th>
						<th style="width:8%;">状态</th>
						<th style="width:8%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as plylist ]
					<tr >


						<td style="text-align:center;">[#if plylist[9]??&&plylist[9]=='6']<input name="checktable" type="checkbox"  onclick="check();" value="${plylist[0]}"[#if plylist[9]=='6' && plylist[13]??&&plylist[13]!='' ] value1="1" [#else]value1="0" [/#if]> [/#if]</td>
						<td>${plylist_index+1}</td>
						<td>${plylist[1]}</td>
						<td>${plylist[2]}</td>
						<td>${plylist[13]}</td>
						[#--	<td>${plylist[3]}</td>--]
						<td>${plylist[4]}[#if plylist[5]??&&plylist[5]!=''] (${plylist[5]}) [/#if]</td>
						<td>${plylist[6]}</td>
						<td>${plylist[7]}</td>
						<td>[#if plylist[12]??&&plylist[12].SJSPJE??]${plylist[12].SJSPJE}[#else] ${plylist[8]} [/#if]</td>
						<td>[#if plylist[10]??]${(plylist[10]?string('yyyy-MM-dd')) }[/#if]</td>

						<td style="white-space: normal;line-height: 30px;">[#if plylist[9]=='0' ]已匹配[#elseif plylist[9]=='1' ]待审批[#elseif plylist[9]=='2']待上传凭证
							[#elseif plylist[9]=='3']已退回[#elseif plylist[9]=='4']审核不通过
							[#elseif plylist[9]=='5']已支付[#elseif plylist[9]=='6']待支付[/#if]</td>


						<td>
						[#if plylist[9]=='5']
							<a class="btn btn-xs btn-success" href="${base}/policyservices/pplaceonfiledetail/${plylist[0]}">查 看</a>
                       [#else]
							<a class="btn btn-xs btn-success" [#if plylist[9]=='6' && plylist[13]??&&plylist[13]!='' ] [#else]disabled[/#if]   href="${base}/policyservices/payinfo?mid=${plylist[0]}">支 付</a>
						[/#if]
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policyservices/policypayments?enterprisename=${enterprisename}&begintime=${begintime}&endtime=${endtime}&cashflag=${cashflag}&"]
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
	function check(){
		var check=$("input[name='checktable']");
		for(i=0;i<check.length;i++){
			if(check[i].checked==false){
				$("#checkall")[0].checked=false
			}
		}
	}
	//批量支付
	function payall(){
		var ids="";
		var checktables=$("input[name='checktable']");
		var isoperable=true;
		for(i=0;i<checktables.length;i++){
			if(checktables[i].checked==true){
				ids +=checktables[i].value+",";
				if($(checktables[i]).attr("value1")=='0'){
					isoperable=false;
				}
			}
		}
		if(ids==""){
			bootbox.alert("请选择要批量支付的数据");
		}else if(isoperable==false){
			bootbox.alert("选中的数据存在不可支付的数据，不可批量支付！");
		}else{
			bootbox.confirm("确认批量支付选中的数据?",function (result) {
				if(result==true){
					$.ajax({
						type:"post",
						url:base + "/policyservices/payall",
						async:true,
						data:{ids:ids},
						success:function(data){
							if(data=="SUCCESS"){
								bootbox.alert("批量支付成功！");
								window.location.reload();
							}else{
								bootbox.alert("批量支付失败！");
							}
						},
						error:function(){
							bootbox.alert("批量支付失败");
						}
					});
				}

			})
		}
	}

//支付按钮
	function payamount(qid){
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
				alert("！");
			}
		});
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
		window.location.href = '${base}/policyservices/payinfoExport?enterprisename='+enterprisename+"&begintime="+begintime+"&endtime="+endtime+"&cashflag="+cashflag;
		return ;
	}

</script>
[/@layout.main]