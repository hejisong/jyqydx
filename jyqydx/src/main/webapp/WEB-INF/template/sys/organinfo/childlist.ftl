[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','机构管理'] modelcode=["sysmgr","OrganManage"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<form name="findlist" action="${base}/sys/organinfo/list" method="get">
					<div class="form-group">
						<a style="float: left;" class="btn btn-sm btn-info" href="${base}/sys/organinfo/list" >返回</a>
						<p  style="text-align:center;font-size: 20px;font-weight: 600;">${organinfo.orgname}</p>


					</div>
				</form>
				<table id="sample-table-1"
					   class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th style="width: 5%;">序号</th>
						<th style="width: 20%;">机构名称</th>
						<th style="width: 15%;">机构编码</th>
						<th style="width: 10%;">联系人</th>
						<th style="width: 15%;">联系号码</th>
						<th style="width: 15%;">更新时间</th>
						<th style="width: 20%;">操作</th>
					</tr>
					</thead>
					<tbody>
					[#list organinfos  as organinfo]
							<tr>
								<td>${organinfo_index+1}</td>
								<td>${organinfo.orgname }</td>
								<td>${organinfo.orgcode }</td>
								<td>${organinfo.linkman }</td>
								<td>${organinfo.linkphone }</td>
								<td>${(organinfo.updatedate?string('yyyy-MM-dd hh:mm:ss')) }</td>
								<td>
									<a class="btn btn-xs btn-info" href="${base }/sys/organinfo/childorg/${organinfo.id}">下级机构</a>
									<a class="btn btn-xs btn-info" href="${base }/sys/organinfo/edit/${organinfo.id}">编 辑</a>
									<button class="btn btn-xs btn-danger" onclick="deleteOrg('${organinfo.id}');" >删 除</button>
								</td>
							</tr>
					[/#list]
					</tbody>
				</table>
			</div>


		</div>
	</div>


	<script type="text/javascript">
		function deleteOrg(id){
			bootbox.confirm("确认删除该机构?",function (result) {
				if(result==true){
					window.location.href=base + "/sys/organinfo/delete/"+id;
				}

			})
		}

		function flushCache(){
			$.ajax({
				type: "GET",
				url:"${base}/xyjsmgr/sys/generalcodename/flushCache",
				error: function(request) {
					bootbox.alert("刷新缓存失败！");
				},
				success: function(data) {
					if(data=="SUCCESS"){
						bootbox.alert("刷新缓存成功！");
					}else{
						bootbox.alert("刷新缓存失败！");
					}
				}
			});
		}
		function  flushQianyi(){
			$.ajax({
				type: "GET",
				url:"${base}/xyjsmgr/sys/generalcodename/flushQianyi",
				error: function(request) {
					bootbox.alert("迁移数据补全失败！");
				},
				success: function(data) {
					if(data=="SUCCESS"){
						bootbox.alert("迁移数据补全成功！");
					}else{
						bootbox.alert("迁移数据补全失败！");
					}
				}
			});
		}

	</script>
[/@layout.main]
