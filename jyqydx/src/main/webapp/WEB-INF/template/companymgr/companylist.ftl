[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['企业管理','企业基本信息'] modelcode=["companymgr","companyinfo"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"  action="${base}/companymgr/companylist" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2">
				<input type="text" id="enterprisename" name="enterprisename" value="${enterprisename}" class="col-xs-12">
			</div>
			<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">统一社会信用代码</label>
			<div class="col-xs-2">
				<input type="text" id="uscreditcode" name="uscreditcode" value="${uscreditcode}" class="col-xs-12">
			</div>

			<div class="col-xs-5" style="text-align: center;" >
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
			[#--	<button style="margin-left: 30px;" class="btn btn-xs btn-info btn-width" type="button" onclick="deleteRoot();">导出</button>--]

				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>


				<a class="btn btn-xs btn-info btn-width" href="${base}/companymgr/initcorps">更新数据（政务大数据）</a>


			</div>
		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5%;">序号</th>
						<th style="width:12%;">企业名称</th>
						<th style="width:10%;">统一社会信用代码</th>
						<th style="width:6%;">法定代表人</th>
						<th style="width:8%;">成立日期</th>
						<th style="width:54%;">经营范围</th>
						<th style="width:6%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as company ]
					<tr >
						<td>${company_index+1}</td>
						<td>${company.enterprisename}</td>
						<td>${company.uscreditcode}</td>
						<td>${company.lrepresentative}</td>
						<td>${company.dateincorporation}</td>
						<td>${company.naturebusiness}</td>
						<td>
							<a class="btn btn-xs btn-info btn-width" disabled="" href="${base}/companymgr/companylist">查 看</a>
						</td>


					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/companymgr/companylist?enterprisename=${enterprisename}&uscreditcode=${uscreditcode}&"]
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
		$("#uscreditcode").val('');
	}
</script>
[/@layout.main]