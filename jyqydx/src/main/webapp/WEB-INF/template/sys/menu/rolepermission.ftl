[#assign modelcodes=['sysmgr','rolemgr']]
[#assign jss=[]]
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=modelcodes otherjs=jss sername=['系统管理','角色管理','权限管理']]
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		 <div class="form-actions">
			<button class="btn btn-sm btn-info btn-width" type="button" onclick="savepermission();">提 交</button>
			<a class="btn btn-sm" type="button" href="${base}/xyjsmgr/sys/sysinfo/role?roletype=${role.roletype}">返回角色管理</a>
		</div>
		<span style="font-size:13px;color:red;">角色名称：${role.rolename},角色编码;${role.rolecode}</span>
		<form class="form-horizontal" role="form" id="formid" action="${base}/xyjsmgr/sys/sysinfo/menu/savepermission" method="post">
			<input type="hidden" id="roleid" name="roleid" value="${roleid}">
 			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th></th>
						<th>名称</th>
						<th>权限</th>
						<th>地址</th>
						<th>描述</th>
						<td><input type="checkbox" id="checkall"  onclick="checkallper();"></td>
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
 						<input class="permissionid"  id="permissionid"  name="permissionid" value="${permission.id }" type="checkbox"  [#list role_permissionlist as role_permission][#if role_permission.permission_id== permission.id ]checked[/#if][/#list]>
 						</td>
 					</tr>
					[/#list]
				</tbody>
			</table>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
function checkallper(){
	if ($("#checkall").attr("checked")) { 
		$(".permissionid").attr("checked", true);  
	}else{
		$(".permissionid").attr("checked", false);  
	}
 } 
 function savepermission(){
	 var formflag=true;
	 if(formflag){
		 formflag=false; 
		 $("#formid").submit();
	 }
 }
</script>
[/@layout.main]