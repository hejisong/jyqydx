[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','工商信息'] modelcode=["targetdata","businessinstitution"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/businessinstitution/list" method="get">
			<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="companyname" name="companyname" value="${companyname}" class="col-xs-12">
			</div>

			<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">统一社会信用代码</label>
			<div class="col-xs-2">
				<input type="text" id="uscreditcode" name="uscreditcode" value="${uscreditcode}" class="col-xs-12">
			</div>


			<div class="col-xs-4" style="text-align: center;">
			      <button class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" href="${base}/businessinstitution/new">新 增</a>

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
						<th style="width:20%;">企业名称</th>
						<th style="width:10%;">统一社会信用代码</th>
						[#--<th style="width:10%;">工商注册号</th>--]
						<th style="width:10%;">注册资本</th>
						<th style="width:8%;">成立日期</th>
						<th style="width:8%;">企业法定代表人</th>
						<th style="width:13%;">登记机关</th>
						<th style="width:8%;">经营状态</th>
						<th style="width:8%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td >${info_index+1}</td>
						<td > ${info.enterprisename}</td>
						<td>${info.uscreditcode}</td>
					[#--	<td>${info.businessregisnum}</td>--]
						<td>${info.recapital}</td>
						<td>${info.dateincorporation}</td>
						<td>${info.lrepresentative}</td>
						<td>${info.registrationauthority}</td>
						<td>${info.operationstatus}</td>
						<td>
							<a  href="${base}/businessinstitution/edit/${info.companyid}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.companyid}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/businessinstitution/list?companyname=${companyname}&uscreditcode=${uscreditcode}&"]
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

	function resetinfo() {

		$("#companyname").val('');
		$("#uscreditcode").val('');
		$("#is_quoted").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/businessinstitution/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]