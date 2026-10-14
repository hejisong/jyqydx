[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','permissionmgr'] otherjs=jss sername=['系统管理','权限管理']]
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<a class="btn btn-xs btn-info" href="${base}/xyjsmgr/sys/sysinfo/permission/new" style="margin-bottom:10px;">添加权限</a>
			<table id="sample-table-1"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th></th>
						<th>名称</th>
						<th>权限</th>
						<th>地址</th>
						<th>描述</th>
  						<th></th>
					</tr>
				</thead>
				<tbody>
				[#list permissionlist as permission]
					<tr>
						<td>${permission_index+1 }</td>
						<td>${permission.name }</td>
						<td>${permission.permission }</td>
						<td>${permission.value }</td>
 						<td>${permission.description }</td>
						<td>
							<a class="btn btn-xs btn-info"  	href="${base}/xyjsmgr/sys/sysinfo/permission/edit/${permission.id}">编 辑</a>
 							<a class="btn btn-xs btn-danger"  href="javascript:void(0)" onclick="deletper('${permission.id}');"  > 删 除</a>
						</td>
					</tr>
					[/#list]
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
function deletper(id){
	if (confirm("确认删除此条权限?")) {
		window.location = base+"/xyjsmgr/sys/sysinfo/permission/delete/"+id ;	
	}
 }
</script>
[/@layout.main]