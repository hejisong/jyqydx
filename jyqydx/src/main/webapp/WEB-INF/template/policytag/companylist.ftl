[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['标签管理','企业标签管理'] modelcode=["tagmgr","companytag"] otherjs=jss othercss=css]
  <div class="row">
		<form class="form-horizontal"  id="forminfo"  action="${base}/policytag/corplist" method="get">

			<div class="col-xs-12 search-bg">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-3" style="margin-bottom: 20px;">
				<input type="text" id="enterprisename" name="enterprisename" value="${enterprisename}" class="col-xs-12">
			</div>


			<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">统一社会信用代码</label>
			<div class="col-xs-2">
				<input type="text" id="uscreditcode" name="uscreditcode" value="${uscreditcode}" class="col-xs-12">
			</div>

			<div class="col-xs-4" style="text-align: center;" >
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="saveinfo();">保 存</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openlog();">任务执行日志</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>

			</div>
		</form>

	<div class="col-xs-12">
		<div class="table-responsive">
			<form class="form-horizontal" id="tagcorpform" >
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:20%;">企业名称</th>
						<th style="width:12%;">统一社会信用代码</th>
						<th style="width:65%;">企业标签</th>

					</tr>
				</thead>
				<tbody id="taginfo">
				[#list page.content as company ]
					<tr >
						<td>${company_index+1}</td>
						<td>${company.enterprisename}</td>
						<td>${company.uscreditcode}</td>
						<td style="text-align: left;"><input type="hidden" value="${company.companyid}" name="companys[${company_index}].companyid">
							[#list tags as tag]
								<input style="font-size: 15px;margin-left: 5px;" name="companys[${company_index}].ptags[${tag_index}].tagid" type="checkbox" [#list company.ptags as ptag] [#if ptag.tagid=tag.tagid]checked [/#if] [/#list]  value="${tag.tagid}" >${tag.tagname}
							[/#list]
						</td>

					</tr>
				[/#list]

				</tbody>

			</table>
			</form>
		</div>
		[#assign pagepath="${base}/policytag/corplist?enterprisename=${enterprisename}&uscreditcode=${uscreditcode}&"]
		[#include "common/pagemodel.ftl"]
	</div>


</div>
	[#include "policytag/t_log.ftl"]
<script type="text/javascript">
	function resetinfo() {
		$("#enterprisename").val('');
		$("#uscreditcode").val('');
		$("#forminfo").submit();

	}


	function saveinfo() {
		// $("#forminfo").submit();
		// initinfo();


		bootbox.confirm("确认保存?",function (result) {
			if(result==true){
				$("#loading_state").val("1");
				$.ajax({
					type:"post",
					url:base + "/policytag/savecorptag",
					async:true,
					data: $("#tagcorpform").serialize(),
					success:function(data){
						if(data=="SUCCESS"){
							bootbox.confirm("保存成功！",function (result) {
								window.location.reload();
							})

						}else{
							bootbox.alert("保存失败！");
						}
					},
					error:function(){
						bootbox.alert("数据加载失败");
					}
				});
			}

		})


	}


</script>
[/@layout.main]