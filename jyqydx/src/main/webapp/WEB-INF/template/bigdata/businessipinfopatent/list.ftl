[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','专利信息'] modelcode=["targetdata","businesspatent"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/businessipinfopatent/list" method="get">

			<div class="form-group">
			<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">专利名称</label>
			<div class="col-xs-2">
				<input type="text" id="patentname" name="patentname" value="${patentname}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="enterprisename" name="enterprisename" value="${enterprisename}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">专利申请号</label>
			<div class="col-xs-2">
				<input type="text" id="applicationnum" name="applicationnum" value="${applicationnum}" class="col-xs-12">
			</div>

			</div>

			<div class="form-group">

				<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">申请日期从</label>
				<div class="col-xs-2">
					<div class="input-group date form_datetime ">
						<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="apply_startdate" id="apply_startdate" data-date-format="yyyy-MM-dd" readonly
							   value="[#if apply_startdate??]${(apply_startdate?string('yyyy-MM-dd')) }[/#if]" style="border-right: 0px; background-color: white;">

						<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
					</div>
				</div>

				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">至</label>
				<div class="col-xs-2">
					<div class="input-group date form_datetime ">
						<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="apply_enddate" id="apply_enddate" data-date-format="yyyy-MM-dd" readonly
							   value="[#if apply_enddate??]${(apply_enddate?string('yyyy-MM-dd')) }[/#if]" style="border-right: 0px; background-color: white;">

						<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
					</div>
				</div>
			<div class="col-xs-4" style="text-align: center;" >
			      <button class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" href="${base}/businessipinfopatent/new">新 增</a>

				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
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
						<th style="width:20%;">专利名称</th>
						<th style="width:20%;">企业名称</th>
						<th style="width:13%;">专利申请号</th>
						<th style="width:10%;">申请日期</th>
						<th style="width:10%;">授权号</th>
						<th style="width:12%;">授权公布日</th>
						<th style="width:10%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>
						<td>${info.patentname}</td>
						<td>${info.enterprisename}</td>

						<td>${info.applicationnum}</td>
						<td>${info.applicationtime}</td>
						<td>${info.pubannomentno}</td>
						<td>${info.pubannomentday}</td>
						<td>
							<a  href="${base}/businessipinfopatent/edit/${info.companypatentid}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.companypatentid}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/businessipinfopatent/list?patentname=${patentname}&enterprisename=${enterprisename}&applicationnum=${applicationnum}&apply_startdate=${(apply_startdate?string('yyyy-MM-dd'))!}&apply_enddate=${(apply_enddate?string('yyyy-MM-dd'))!}&"]
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
		$("#enterprisename").val('');
		$("#patentname").val('');
		$("#applicationnum").val('');
		$("#apply_startdate").val('');
		$("#apply_enddate").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/businessipinfopatent/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]