[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['政策服务管理','政策兑现归档'] modelcode=["zcfwgl","zcdxgd"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"  action="${base}/policyservices/policyplaceonfile" method="get">
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
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">状态</label>
			<div class="col-xs-1">
				<select class="form-control input-inline" name="cashflag" id="cashflag" style="height: 34px;padding: 2px">
					<option value="" [#if cashflag=='']selected="selected"[/#if]>全部</option>
					<option value="0" [#if cashflag=='0']selected="selected"[/#if]>已匹配</option>
					<option value="1"[#if cashflag=='1']selected="selected"[/#if]>待审批</option>
					<option value="7"[#if cashflag=='7']selected="selected"[/#if]>审批中</option>
					<option value="2"[#if cashflag=='2']selected="selected"[/#if]>审批通过</option>
					<option value="3"[#if cashflag=='3']selected="selected"[/#if]>已退回</option>
					<option value="4"[#if cashflag=='4']selected="selected"[/#if]>已驳回</option>
					<option value="6"[#if cashflag=='6']selected="selected"[/#if]>待支付</option>
					<option value="5"[#if cashflag=='5']selected="selected"[/#if]>已支付</option>
				</select>
			</div>
			<div class="col-xs-3" style="text-align: center;" >
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
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
						<th style="width:5%;">序号</th>
						<th style="width:10%;">企业名称</th>
						<th style="width:8%;">统一社会信用代码</th>
						<th style="width:8%;">企业收款账号</th>
				[#--		<th style="width:15%;">政策文件</th>--]
						<th style="width:14%;">政策类型</th>
						<th style="width:15%;">政策条件</th>
						<th style="width:10%;">政策奖励</th>
						<th style="width:6%;">兑现金额</th>
						<th style="width:6%;">匹配日期</th>
						<th style="width:6%;">状态</th>
						<th style="width:6%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as plylist ]
					<tr >
						<td>${plylist_index+1}</td>
						<td>${plylist[1]}</td>
						<td>${plylist[2]}</td>
						<td>${plylist[13]}</td>
						<td>${plylist[4]}[#if plylist[5]??&&plylist[5]!=''] (${plylist[5]}) [/#if]</td>
						<td>${plylist[6]}</td>
						<td>${plylist[7]}</td>
						<td>[#if plylist[12]??&&plylist[12].SJSPJE??]${plylist[12].SJSPJE}[#else] ${plylist[8]} [/#if]</td>
						<td>[#if plylist[10]??]${(plylist[10]?string('yyyy-MM-dd')) }[/#if]</td>
						<td style="white-space: normal;line-height: 30px;">[#if plylist[9]=='0' ]已匹配[#elseif plylist[9]=='1' ]待审批[#elseif plylist[9]=='7']审批中[#elseif plylist[9]=='2']审批通过
							[#elseif plylist[9]=='3']已退回[#elseif plylist[9]=='4']审核不通过
							[#elseif plylist[9]=='5']已支付[#elseif plylist[9]=='6']待支付[/#if]</td>

						<td>
							<a class="btn btn-xs btn-success" href="${base}/policyservices/pplaceonfiledetail/${plylist[0]}">查看
							</a>
						</td>
					</tr>
				[/#list]
[#--				[#list page.content as localtle]--]

[#--						<tr id="${localtle.objid}">--]
[#--							<td>1</td>--]
[#--							<td>贝优特技术有限公司</td>--]
[#--							<td>建邺区关于促进..</td>--]
[#--							<td>落户奖励</td>--]
[#--							<td>开办奖励</td>--]
[#--							<td>实缴到位注册资本达.…</td>--]
[#--							<td>奖励30万元</td>--]
[#--							<td>--]
[#--								<a class="btn btn-xs btn-info btn-width" href="${base }/policyservices/check">审 核</a>--]
[#--							</td>--]
[#--						</tr>--]
[#--						<tr id="${localtle.objid}">--]
[#--							<td>2</td>--]
[#--							<td>南京和电科技有限公司</td>--]
[#--							<td>建邺区关于促进…</td>--]
[#--							<td>经济贡献奖</td>--]
[#--							<td>股权投资企业</td>--]
[#--							<td>第1-3年</td>--]
[#--							<td>对区域经济贡献100%...</td>--]
[#--							<td>--]
[#--								<a class="btn btn-xs btn-info btn-width" href="${base }/policyservices/check1">审核</a>--]
[#--							</td>--]
[#--						</tr>--]
[#--					[/#list]--]
				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policyservices/policyplaceonfile?enterprisename=${enterprisename}&begintime=${begintime}&endtime=${endtime}&cashflag=${cashflag}&"]
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
			bootbox.alert("选中的数据存在已兑付的数据，不可批量支付！");
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
				bootbox.alert("！");
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
		window.location.href = '${base}/policyservices/onfileinfoExport?enterprisename='+enterprisename+"&begintime="+begintime+"&endtime="+endtime+"&cashflag="+cashflag;
		return ;
	}

</script>
[/@layout.main]