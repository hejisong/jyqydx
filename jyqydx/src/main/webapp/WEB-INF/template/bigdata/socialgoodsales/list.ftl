[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业指标管理','社会消费品零售总额'] modelcode=["targetdata","socialgoodsales"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/socialgoodsales/list" method="get">

			<div class="form-group">


			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="company_name" name="company_name" value="${company_name}" class="col-xs-12">
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

				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;width: 16%;">社会消费品零售总额（万元）</label>
				<div class="col-xs-1">
					<input type="text" id="min_sales" name="min_sales" value="${min_sales}" class="col-xs-12"  >
				</div>

				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;width: 5%;">至</label>
				<div class="col-xs-1">
					<input type="text" id="max_sales" name="max_sales" value="${max_sales}" class="col-xs-12"  >
				</div>




				<div class="col-xs-3" style="width: 20%;text-align: center;" >
					<button  class="btn btn-xs btn-info btn-width" type="button" onclick="checknumber();">查 询</button>
					<a style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" href="${base}/socialgoodsales/new">新 增</a>

					<button style="margin-left:20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
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
						<th style="width:8%;">年度</th>
						<th style="width:14%;">社会消费品零售总额（万元）</th>
						<th style="width:10%;">社会消费品零售总额增幅</th>
						<th style="width:10%;">分组</th>
						<th style="width:10%;">计量单位</th>
						<th style="width:9%;">报告期</th>
						<th style="width:9%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>
						<td>${info.companyname}</td>
						<td>${info.year}</td>
						<td>${info.totall_sales}</td>
						<td>${info.total_sales_increase}</td>
						<td>${info.grouping}</td>
						<td>${info.measureunit}</td>
						<td>${info.reporting_period}</td>
						<td>
							<a  href="${base}/socialgoodsales/edit/${info.id}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.id}');" href="javascript:void(0);">删除</a>
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/socialgoodsales/list?company_name=${company_name}&year=${year}&min_sales=${min_sales}&max_sales=${max_sales}&"]
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


	function checknumber() {
		var minsale_income=$("#min_sales").val();
		var maxsale_income=$("#max_sales").val();
		var reg = /^\d+(?=\.{0,1}\d+$|$)/;
		var flag=true;
		if(minsale_income!=null&&minsale_income!=""&&minsale_income!=undefined){
			if(!reg.test(minsale_income)){
				flag=false;
			}
		}
		if(flag){
			if(maxsale_income!=null&&maxsale_income!=""&&maxsale_income!=undefined){
				if(!reg.test(maxsale_income)){
					flag=false;
				}
			}
		}
		if(flag){
			$("#infofrom").submit();
		}else{
			bootbox.alert('社会消费品零售总额只能输入数字，小数点后只能保留两位!');
			return;
		}


	}

	function resetinfo() {
		$("#company_name").val('');
		$("#year").val('');
		$("#min_sales").val('');
		$("#max_sales").val('');
		$("#infofrom").submit();
	}


	function deleteinfo(id) {
		bootbox.confirm("确认删除该信息?",function (result) {
			if(result==true){
				window.location.href=base + "/socialgoodsales/updatestate/"+id+"/0";
			}

		})

	}






</script>
[/@layout.main]