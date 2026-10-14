[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','万平方米展览项目'] modelcode=["targetdata","squareproject"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/squareproject/list" method="get">

			<div class="form-group">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text"  id="companyname" name="companyname" value="${companyname}" class="col-xs-12">
			</div>


				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">年度</label>
				<div class="col-xs-1">
					<select class="form-control" id="year" name="year" style="display: inline-block;height: 34px;">
						<option value="" [#if year == '' ] selected="selected"[/#if]>全部</option>
						[#list years as y]
							<option value="${y}" [#if year == y ] selected="selected"[/#if]>${y}</option>
						[/#list]
					</select>
				</div>


				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">展会名称</label>
				<div class="col-xs-2">
					<input type="text" id="name_of_fair" name="name_of_fair" value="${name_of_fair}" class="col-xs-12">
				</div>



				<div class="col-xs-3"  style="text-align: center;" >
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" href="${base}/squareproject/new">新 增</a>

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
						<th style="width:20%;">企业名称</th>
						<th style="width:10%;">年份</th>
						<th style="width:20%;">展会名称</th>
						<th style="width:10%;">展会时间</th>
						<th style="width:10%;">类别</th>
						<th style="width:15%;">展会地点</th>
						<th style="width:10%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>
						<td>${info.companyname}</td>
						<td>${info.years}</td>
						<td>${info.name_of_fair}</td>

						<td>${info.exhibition_time}</td>
						<td>${info.category}</td>
						<td>${info.exhibition_location}</td>

						<td>
							<a  href="${base}/squareproject/edit/${info.id}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.id}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/squareproject/list?companyname=${companyname}&year=${year}&name_of_fair=${name_of_fair}&"]
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
		$("#year").val('');
		$("#name_of_fair").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/squareproject/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]