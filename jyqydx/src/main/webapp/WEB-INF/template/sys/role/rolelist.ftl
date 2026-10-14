[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','rolemgr'] otherjs=jss sername=['系统管理','角色管理']]
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<a class="btn btn-xs btn-info" href="${base}/xyjsmgr/sys/sysinfo/role/new">添加角色</a>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5%;text-align:center;">序号</th>
						<th style="width:10%;text-align:center;">角色名</th>
						<th style="width:10%;text-align:center;">角色编码</th>
						<th style="width:33%;text-align:center;">角色描述</th>
						<th style="width:12%;text-align:center;">更新时间</th>
						<th style="width:10%;text-align:center;">主页面</th>
						<th style="width:20%;text-align:center;"></th>
					</tr>
				</thead>
				<tbody>
				[#list rolelist as role]
					<tr>
						<td>${role_index+1 }</td>
						<td>${role.rolename }</td>
						<td>${role.rolecode }</td>
						<td>${role.description }</td>
						<td>${(role.modify_Date?string('yyyy-MM-dd HH:mm:ss'))!}</td>
						<td>${role.mainpage }</td>
						<td>
							<a class="btn btn-xs btn-info"  href="${base}/xyjsmgr/sys/sysinfo/role/update/${role.id}">编 辑</a>
							[#if role.isforbidden=="0"]<button class="btn btn-xs btn-danger" onclick="forbidden(this,'${role.roletype}')" value="${role.id}">禁 用</button>
							[#else]<button class="btn btn-xs btn-warning" onclick="restart(this,'${role.roletype}')" value="${role.id}">启 用</button>
							[/#if]
							<a class="btn btn-xs btn-success"  href="${base}/xyjsmgr/sys/sysinfo/menu/${role.id}/setmenu">业务管理</a>
[#--							<a class="btn btn-xs btn-success"  href="${base}/xyjsmgr/sys/sysinfo/menu/${role.id}/setpermission">权限管理</a>--]
							<a class="btn btn-xs btn-success"  href="${base}/xyjsmgr/sys/sysinfo/role/setperson/${role.id}">人员管理</a>
						</td>
					</tr>
					[/#list]
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
function forbidden(obj,roletype){
	bootbox.alert(obj.value);
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/sysinfo/role/isforbidden/"+obj.value+"/1",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/sysinfo/role?roletype="+roletype;
        }
    });
}

function restart(obj,roletype){
	bootbox.alert(obj.value);
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/sysinfo/role/isforbidden/"+obj.value+"/0",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/sysinfo/role?roletype="+roletype;
        }
    });
}

</script>
[/@layout.main]