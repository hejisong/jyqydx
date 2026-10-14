<!-- 遮挡层 -->
<div class="dialog" id="user_dialog" style="display:none;height:660px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #428bca;">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>用户信息</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeUser_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:550px;overflow-y:auto;">
								<form id="userinfo">
								<table class="search-table-form">
									<tbody>
									     <tr>
											<td class="td-center" style="width:30%">姓名</td>
											<td style="width:40%;">
												 <input type="hidden" name="id" id="org_userid" class="form-control">
												 <input type="hidden" name="organid" id="org_organid" class="form-control">
												 <input type="hidden" name="organname" id="org_organname" class="form-control">
												<input type="hidden" name="datatype" id="org_datatype" value="" class="form-control">
												 <input name="username" id="org_username" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<!-- <tr><td colspan="3">&nbsp;</td></tr> -->
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">登录名</td>
											<td style="width:40%;">
												 <input name="loginname" id="org_loginname" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">身份证号</td>
											<td style="width:40%;">
												 <input name="linknum" id="org_linknum" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">手机号码</td>
											<td style="width:40%;">
												 <input name="linktel" id="org_linktel" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										<tr style="margin-top:20px;">
											<td class="td-center" style="width:30%">联系邮箱</td>
											<td style="width:40%;">
												 <input name="linkemail" id="org_linkemail" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>
										 <tr style="margin-top:20px;">
											 <td class="td-center" style="width:30%">角色</td>
											 <td style="width:60%;">
												[#-- <select class="form-control" id="org_roleid" name="roleid" style="display: inline-block;width: 84%;">
													  [#list roles as role]
														 <option value="${role.id}" [#if role.rolecode== 'COMMONUSER']
															 selected="selected" [/#if]>${role.rolename}</option>
													  [/#list]


												 </select>--]
												 <table id="sample-table-1" class="table table-striped table-bordered table-hover">
													 <thead>
													 <tr>
														 <th style="width:20%;text-align:center;"></th>
														 <th style="width:80%;">角色</th>
													 </tr>
													 </thead>
													 <tbody id="rolelist">
													 </tbody>
												 </table>
												 <p style="color:red;">不选择角色，默认角色为普通用户</p>
											 </td>
											 <td class="td-center" style="width:30%"></td>
										 </tr>
									</tbody>
								</table>
								</form>
							</div>
						</div>
						<div class="clearfix form-actions" style="text-align:center;">
							<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="saveUser_dialog()">提 交</button>
							<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeUser_dialog()">取 消</button>
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
	$("#org_userid").val("");
	$("#org_username").val("");
	$("#org_loginname").val("");
	$("#org_organname").val("");
	$("#org_linknum").val("");
	$("#org_linktel").val("");
	$("#org_linkemail").val("");
}

function editinfo(ele) {
	var userid=$(ele).attr("value");
	$.ajax({
		url:base + "/sys/organinfo/organ/users/edit",
		type : "POST",
		data:{userid:userid},
		async : false,
		dataType : "json",
		success: function(data){
			var user=data[0];
			var roles=data[1];
			var rolelist=data[2];
			if(user!=null){
				$("#org_organid").val(user.organid);
				$("#org_userid").val(user.id);
				$("#org_datatype").val(user.datatype);
				$("#org_username").val(user.username);
				$("#org_loginname").val(user.loginname);
				$("#org_organname").val(user.organname);
				$("#org_linknum").val(user.linknum);
				$("#org_linktel").val(user.linktel);
				$("#org_linkemail").val(user.linkemail);
                var rolehtml='';
				$.each(rolelist,function(i,r){
					var ischeck=false;
					rolehtml+='<tr>';
					if(roles!=null){

						$.each(roles,function(i,role){
							if(role.id==r.id){
								ischeck=true;
							}
						});


					}

					if(ischeck){
						rolehtml+='<td><input type="checkbox" name="roleid" checked value="'+r.id+'"></td>';
					}else{
						rolehtml+='<td><input type="checkbox"  name="roleid" value="'+r.id+'"></td>';
					}
					rolehtml+='<td>'+r.rolename+'</td></tr>';

				});



				$("#rolelist").html(rolehtml);
				$("#org_roleid").val(user.roleid);
				openUser_dialog();
			}
		},
		error:function(){
			bootbox.alert("数据加载失败！");
		}
	});
}

//保存用户信息
function saveUser_dialog() {
	var formflag=true;
	var organid=$("#org_organid").val();
	if(alertFormValidation_orguser()==true){

		if(formflag){
			formflag=false;
			// $("#loading_state").val("1");
			$.ajax({
				type:"post",
				url:base + "/sys/organinfo/organ/users/save",
				data:$("#userinfo").serialize(),
				dataType:"text",
				success: function(data){
					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							searchOrgUsers(organid);
							closeUser_dialog();
						});
						
					}else if(data=="noddper"){
						bootbox.alert("该人员在钉钉上未找到记录，请将手机号码更新为钉钉手机号！");
					} else{
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