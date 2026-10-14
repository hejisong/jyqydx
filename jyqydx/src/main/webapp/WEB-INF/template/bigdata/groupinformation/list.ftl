[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','集团信息'] modelcode=["targetdata","groupinformation"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/groupinformation/list" method="get">

			<div class="form-group">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">集团名称</label>
			<div class="col-xs-2">
				<input type="text" id="groupname" name="groupname" value="${groupname}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">母公司名称</label>
			<div class="col-xs-2">
				<input type="text" id="pcompanyname" name="pcompanyname" value="${pcompanyname}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">集团登记证号</label>
			<div class="col-xs-2">
				<input type="text" id="registrationnumber" name="registrationnumber" value="${registrationnumber}" class="col-xs-12">
			</div>


			<div class="col-xs-3" >
			      <button style="margin-left: 30px;" class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/groupinformation/new">新 增</a>

				<button style="margin-left: 30px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>

			</div>
		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:19%;">集团全称</th>
						<th style="width:9%;">集团登记证号</th>
						<th style="width:11%;">注册资本</th>
						<th style="width:9%;">成立日期</th>
						<th style="width:12%;">登记机关</th>
						<th style="width:19%;">母公司名称</th>
						<th style="width:8%;">成员总数</th>
						<th style="width:8%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>
						<td>${info.group_name}</td>

						<td>${info.registration_number}</td>

						<td>${info.registered_capital}</td>
						<td>${info.establishdate}</td>
						<td>${info.registration_authority}</td>
						<td>${info.pcompany_name}</td>
						<td>${info.members_number}</td>
						<td>
							<a  href="${base}/groupinformation/edit/${info.groupid}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.groupid}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/groupinformation/list?groupname=${groupname}&pcompanyname=${pcompanyname}&registrationnumber=${registrationnumber}&"]
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
		$("#groupname").val('');
		$("#pcompanyname").val('');
		$("#registrationnumber").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/groupinformation/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]