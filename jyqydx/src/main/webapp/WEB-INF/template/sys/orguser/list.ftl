[#assign jss=[]] 
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','用户管理'] modelcode=["sysmgr","gl_orguser"] otherjs=jss othercss=css]
[#include "sys/gl/usermgr/usertanchu.ftl"]
  <div class="row">
  	<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal"  action="${base}/sys/usermgr/list" method="get">
		<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">姓名</label>
			<div class="col-xs-2">
				<input type="text" id="username" name="username" value="${username }" class="col-xs-12">						    										
			</div>
			<div class="col-xs-1"></div>
			<div class="col-xs-2">
			      <button class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a href="javascript:void(0)" class="btn btn-xs btn-info" onclick="addOrgUser()">新增人员</a>
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
						<th style="width:14%;">身份证号</th>
						<th style="width:10%;">手机号码</th>
						<th style="width:12%;">联系邮箱</th>
						<th style="width:12%;">角色</th>
						<th style="width:10%;">是否接受和发送短信</th>
						<th style="width:15%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as user]
				
					<tr>
						<td style="text-align:center;">${user_index+1}</td>
						<td >${user.username }</td>
						<td>${user.loginname }</td>
						<td>${user.linknum }</td>
						<td>${user.linktel }</td>
						<td>${user.linkemail}</td>
						<td>${user.rolename}</td>
						<td><input type="checkbox" id="${user.id }" onclick="checkboxOnclick('${user.id }')"  [#if user.accept_sms=='1'] checked="checked" [/#if] >
						
						</td>
						 <td>
						 <a class=" " href="${base}/sys/usermgr/toEdit/${user.id}">编辑 </a>
						 <a class=" "  href="javascript:void(0)" onclick="resetPassword(this)" id="${user.id}">初始化密码</a>	
						 <a class=" "  href="javascript:void(0)" onclick="unlock(this)" id="${user.id}">解锁帐号</a>
						 <a class="  btn-red" href="${base}/sys/usermgr/forbidden/${user.id}">删除 &nbsp; </a>
						 </td> 
					</tr>
					[/#list]
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-xs-12">
		[#assign pagepath="${base}/sys/usermgr/list?username=${username }&"]
		[#include "common/pagemodel.ftl"]
	</div>
</div>
<script type="text/javascript">
function addOrgUser(){
	/* var organid = $("#organid").val();
	if(organid==""||organid==undefined){
		bootbox.alert("请先选择机构！");
		return;
	} */
	openUser_dialog();
}

function resetPassword(ele){	
	var userid =$(ele).attr("id");
	bootbox.confirm("确认初始化该用户密码?",function (result) {
		if(result==true){
			$.ajax({
				type:"get",
				url:base + "/sys/usermgr/resetpassword/"+userid,
				dataType:"text",
				success: function(data){
					if(data="SUCCESS"){
						bootbox.alert("密码初始化成功！");
					}else{
						bootbox.alert("密码初始化失败！");
					}
				},
				error:function(){
					bootbox.alert("密码初始化失败！");
				}
			});
		}
		
 })
}


function checkboxOnclick(id){
	var check = $("#"+id).prop("checked");
	var linktel = $("#"+id).parent().prev().prev().prev().text();
	var  re = /^1\d{10}$/    //正则表达式
	if (re.test(linktel)==false) { //判断字符是否是11位数字
		bootbox.alert("手机号码不正确！请进行编辑！");
		$("#"+id).prop("checked",false);
		 return;
	}else{
		var checked;
		if(check){
			checked="1";
		}else{
			checked="2";
		}
		$.ajax({
			type:"post",
			url:base + "/sys/usermgr/sms",
			data:{id:id,checked:checked} ,
			dataType:"text",
			success: function(data){
				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/sys/usermgr/list";
					});
					
				}else{
					bootbox.alert("保存失败！");
				}
			},
			error:function(){
				bootbox.alert("保存失败！");
				$("#"+id).prop("checked",false);
			}
		});
	}
}

function unlock(ele){	
	var id =$(ele).attr("id");
	$.ajax({
				type:"post",
				url:base + "/xyjsmgr/unlock/"+id,
				async:true,  
				success:function(data){
					if(data=="SUCCESS"){
						bootbox.alert("帐号解锁成功！");
					}else{
						bootbox.alert("帐号解锁失败！");
					}
				},
				error:function(){
					 bootbox.alert("数据加载失败");
				}
			});
}
</script>
[/@layout.main]