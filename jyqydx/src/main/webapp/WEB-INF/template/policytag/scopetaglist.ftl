[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['标签管理','经营范围标签管理'] modelcode=["tagmgr","scopetag"] otherjs=jss othercss=css]
  <div class="row">


		<form class="form-horizontal"  id="forminfo"  action="${base}/companymgr/scopetaglist" method="get">
			<div class="col-xs-12 search-bg">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">经营范围</label>
			<div class="col-xs-3">
				<input type="text" id="scope" name="scope" value="${scope}" class="col-xs-12">
			</div>

			<div class="col-xs-5" style="text-align: center;">
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="addinfo();">新 增</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="saveinfo();">保 存</button>

			</div>


	       </div>
		</form>


	<div class="col-xs-12">
		<div class="table-responsive">
			<form class="form-horizontal" id="tagform"  >
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5%;">序号</th>
						<th style="width:16%;">经营范围</th>
						<th style="width:80%;">标签</th>

					</tr>
				</thead>
				<tbody>
				<input type="hidden" value="标签" name="tag">
				[#list page.content as businessscope ]
					<tr >
						<td>${businessscope_index+1}</td>
						<td>${businessscope.businessscope}</td>
						<td><input type="hidden" value="${businessscope.id}" name="scopetags[${businessscope_index}].id">
							[#list tags as tag]
								<input style="font-size: 15px;margin-left: 5px;" name="scopetags[${businessscope_index}].tag" type="checkbox" [#list businessscope.tags as stag] [#if stag=tag.tagid]checked [/#if] [/#list]  value="${tag.tagid}" >${tag.tagname}
							[/#list]

						</td>


					</tr>
				[/#list]

				</tbody>
			</table>
			</form>
		</div>
		[#assign pagepath="${base}/companymgr/scopetaglist?scope=${scope}&"]
		[#include "common/pagemodel.ftl"]
	</div>


  </div>
	[#include "policytag/t_scope.ftl"]
<script type="text/javascript">
	function saveinfo() {
		// $("#forminfo").submit();
		// initinfo();

		bootbox.confirm("确认保存?",function (result) {
			if(result==true){
				$("#loading_state").val("1");
				$.ajax({
					type:"post",
					url:base + "/companymgr/savetag",
					async:true,
					data: $("#tagform").serialize(),
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
	
	function resetinfo() {
       $("#scope").val('');
       $("#forminfo").submit();
	}
</script>
[/@layout.main]