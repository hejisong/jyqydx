[#assign jss=[]] 
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','解锁账号'] modelcode=["sysmgr","gl_unlock"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal"  action="${base}/sys/onlineuser/unlock/list" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">登录名</label>
			<div class="col-xs-2">
				<input type="text" id="orgname" name="orgname" value="${orgname }" class="col-xs-12">
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">机构名称</label>
			<div class="col-xs-2">
				<select class="form-control" id="orgcode" name="orgcode" style="display: inline-block;width: 100%;height: 34px;">
					[#list organinfos as org]
						<option value="${org.id}" [#if org.id==orgcode]
							selected="selected" [/#if]>${org.orgname}</option>
					[/#list]


				</select>
			</div>



			<div class="col-xs-1"></div>
			<div class="col-xs-2">
			      <button class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
			</div>
		</form>
	</div>
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5%;text-align:center;">序号</th>
						<th style="width:10%;">姓名</th>
						<th style="width:10%;">登录名</th>
						<th style="width:10%;">手机号码</th>
						<th style="width:12%;">联系邮箱</th>
						<th style="width:15%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list list as user]
				
					<tr id="${user.loginname}">
						<td style="text-align:center;">${user_index+1}</td>
						<td >${user.username }</td>
						<td>${user.loginname }</td>
						<td>${user.linktel }</td>
						<td>${user.linkemail}</td>
						<td>
						 <a class=" "  href="javascript:void(0)" onclick="unlock(this)" id="${user.id}">解锁帐号</a>							
						 </td> 
					</tr>
					[/#list]
				</tbody>
			</table>
		</div>
	</div>
	
</div>
<script type="text/javascript">


function unlock(ele){	
	var id =$(ele).attr("id");
	var mess="";
	bootbox.confirm("确认解锁该用户信息?",function (result) {
		if(result==true){
			$.ajax({
				type:"post",
				url:base + "/sys/onlineuser/unlock/"+id+"/Y",
				async:true,
				success:function(data){
					if(data=="SUCCESS"){
						bootbox.alert("帐号解锁成功！");
						window.location.href=base + "/sys/onlineuser/unlock/list";
					}else{
						bootbox.alert("帐号解锁失败！");
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