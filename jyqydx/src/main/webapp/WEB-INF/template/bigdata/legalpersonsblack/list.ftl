[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','法人黑名单'] modelcode=["targetdata","legalpersonsblack"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/legalpersonsblack/list" method="get">

			<div class="form-group">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" placeholder="企业名称或统一社会信用代码"  id="company_name" name="company_name" value="${company_name}" class="col-xs-12">
			</div>


				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">法人名称</label>
				<div class="col-xs-1">
					<input type="text" id="legalpersons" name="legalpersons" value="${legalpersons}" class="col-xs-12">
				</div>


				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">法人身份证号</label>
				<div class="col-xs-2">
					<input type="text" id="legalcardid" name="legalcardid" value="${legalcardid}" class="col-xs-12">
				</div>



				<div class="col-xs-3" style="text-align: center;" >
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" href="${base}/legalpersonsblack/new">新 增</a>

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
						<th style="width:8%;">法人名称</th>
						<th style="width:10%;">法人身份证号</th>
						<th style="width:20%;">企业名称</th>
						<th style="width:12%;">统一社会信用代码</th>
						<th style="width:10%;">类型</th>
						<th style="width:10%;">确认严重失信时间</th>
						<th style="width:15%;">认定单位</th>
						<th style="width:10%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>

						<td>${info.legalpersons}</td>
						<td>${info.legalcardid}</td>
						<td>${info.company_name}</td>
						<td>${info.uscreditcode}</td>
						<td>${info.type}</td>
						<td>${info.serious_confirm_date}</td>
						<td>${info.data_source_unit}</td>

						<td>
							<a  href="${base}/legalpersonsblack/edit/${info.id}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.id}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/legalpersonsblack/list?company_name=${company_name}&legalpersons=${legalpersons}&legalcardid=${legalcardid}&"]
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
		$("#company_name").val('');
		$("#legalpersons").val('');
		$("#legalcardid").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/legalpersonsblack/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]