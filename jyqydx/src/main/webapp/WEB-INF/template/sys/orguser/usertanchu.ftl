<!-- 遮挡层 -->
<div class="dialog" id="user_dialog" style="display:none;height:430px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>用户信息</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeUser_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:300px;overflow-y:auto;">
							<form class="form-horizontal"  id="iform" >								
								<table class="search-table-form">
									<tbody>
									     <tr>
											<td class="td-center" style="width:38%">姓名</td>
											<td style="width:40%;">
												 <input type="hidden" name="organid" id="org_organid" class="form-control">
												 <input name="username" id="username" value="${user.username }" class="form-control">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<!-- <tr><td colspan="3">&nbsp;</td></tr> -->
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">登录名</td>
											<td style="width:40%;">
												 <input name="loginname" id="loginname" value="${user.loginname }" class="form-control" >
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">身份证号</td>
											<td style="width:40%;">
												 <input name="linknum" id="linknum" value="${user.linknum }" class="form-control">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">手机号码</td>
											<td style="width:40%;">
												 <input name="linktel" id="linktel" value="${user.linktel }" class="form-control">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">联系邮箱</td>
											<td style="width:40%;">
												 <input name="linkemail" id="linkemail" value="${user.linkemail }" class="form-control">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										[#if rolelist??&&rolelist?size>0]
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">账号角色类型</td>
											<td style="width:40%;">
												<select class="bs-select form-control" name="rolecode" id="rolecode">	
												    [#list rolelist as role]
						 						    <option value="${role.rolecode}" [#if rolecode==role.rolecode] selected="selected"[/#if]>${role.rolename}</option>
						 						   	[/#list]						 						   	
												</select>
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										[/#if]
									</tbody>
								</table> 
								<div class="clearfix form-actions" style="text-align:center;">
									<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="saveUser_dialog()">提 交</button>
				 					<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeUser_dialog()">取 消</button>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//打开用户弹出框
function openUser_dialog() {
 	$("#fullbg").show();
  	$("#user_dialog").show();
}
//关闭用户弹出框
function closeUser_dialog() {
	clearUser_dialog();
	$("#fullbg,#user_dialog").hide();
}
//清理表单信息
function clearUser_dialog() {
	
	$("#username").val("");
	$("#loginname").val("");
	$("#organname").val("");
	$("#linknum").val("");
	$("#linktel").val("");
	$("#linkemail").val("");
	var options=$("#rolecode")[0].options;
	$(options[0]).attr("selected","selected");
}
//保存用户信息
function saveUser_dialog() {
	var formflag=true;
	//var organid = $("#org_organid").val();
	var username = $("#username").val();
	var loginname = $("#loginname").val();
	var orgname = $("#organname").val();
	var linknum = $("#linknum").val();
	var linktel = $("#linktel").val();
	var linkemail = $("#linkemail").val();
	var rolecode = $("#rolecode").val();
	if(username ==null || username==""){
		bootbox.alert("请填写姓名！");
	}else if(loginname ==null || loginname==""){
		bootbox.alert("请填写登录名！");
	}else if(linknum==null || linknum==""){
		bootbox.alert("请填写身份证号码！");
	}else if(linktel==null || linktel==""){
		bootbox.alert("请填写联系人电话！");
	}else if(linkemail ==null || linkemail==""){
		bootbox.alert("请填写联系人电子邮箱！");
	}else{
      if(formflag){
    	  formflag=false;
    	  $.ajax({
    			type:"post",
    			url:base + "/sys/usermgr/save",
    			data:/* $("#iform").serialize() */
    			 {
    				//organid:organid,
    				username:username,
    				loginname:loginname,
    				linktel:linktel,
    				linknum:linknum,
    				linkemail:linkemail,
    				rolecode:rolecode
    			} ,
    			dataType:"text",
    			success: function(data){
    				if(data=="SUCCESS"){
    					bootbox.alert("保存成功！", function() {
    						window.location.href=base + "/sys/usermgr/list";
        					closeUser_dialog();
    					});
    					
    				}else{
    					bootbox.alert("登录名已存在，请重新输入！");
    				}
    			},
    			error:function(){
    				bootbox.alert("保存失败！");
    			}
    		});
      }
	
	}
}
</script>