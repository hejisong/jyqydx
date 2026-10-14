<!-- 遮挡层 -->
<div class="dialog" id="org_dialog" style="display:none;height:430px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>用户信息</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeorg_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:300px;overflow-y:auto;">
								<form class="form-horizontal" role="form" id="tformid" action="${base}/sys/organinfo/save" method="post">

									<input type="hidden" name="id" id="t_id" value="${organinfo.id }">

									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构名称 </label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_orgname" name="orgname" value="${organinfo.orgname }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构简称 </label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_displayname" name="displayname" value="${organinfo.displayname }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构代码 </label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_orgcode" name="orgcode" value="${organinfo.orgcode }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 上级部门 </label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_superdepartment" name="superdepartment" value="" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 联系人 </label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_linkman" name="linkman" value="${organinfo.linkman }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门办公电话</label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_telephonenumber" name="telephonenumber" value="${organinfo.telephonenumber }" class="col-xs-12">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 邮箱</label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_mail" name="mail" value="${organinfo.mail }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 通讯地址</label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_orgaddr" name="orgaddr" value="${organinfo.orgaddr }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门邮政编码</label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_postalcode" name="postalcode" value="${organinfo.postalcode }" class="col-xs-12">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门传真号码</label>
										<div class="col-xs-5">
											<div class="block input-icon input-icon-right">
												<input type="text" id="t_facsimiletelephonenumber" name="facsimiletelephonenumber" value="${organinfo.facsimiletelephonenumber }" class="col-xs-12">
											</div>
										</div>
									</div>

									<div class="clearfix form-actions" style="text-align: center;">
										<button class="btn btn-sm btn-info" type="button" onclick="saveaddOrg();">保 存</button>
										<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
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
function openOrg() {
 	$("#fullbg").show();
  	$("#org_dialog").show();
}
//关闭用户弹出框
function closeorg_dialog() {
	clearorg_dialog();
	$("#fullbg,#org_dialog").hide();
}
//清理表单信息
function clearorg_dialog() {

	$("#t_id").val("");
	$("#t_superorganid").val("");
	$("#t_orgname").val("");
	$("#t_displayname").val("");
	$("#t_orgcode").val("");
	$("#t_superdepartment").val("");
	$("#t_linkman").val("");
	$("#t_telephonenumber").val("");
	$("#t_mail").val("");
	$("#t_orgaddr").val("");
	$("#t_postalcode").val("");
	$("#t_facsimiletelephonenumber").val("");
}
//保存用户信息
function saveorg_dialog() {
	var formflag=true;
	//var organid = $("#org_organid").val();
	var orgname = $("#orgname").val();
	var loginname = $("#loginname").val();
	var orgname = $("#organname").val();
	var linknum = $("#linknum").val();
	var linktel = $("#linktel").val();
	var linkemail = $("#linkemail").val();
	var rolecode = $("#rolecode").val();
	if(orgname ==null || orgname==""){
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
    			url:base + "/sys/orgmgr/save",
    			data:/* $("#iform").serialize() */
    			 {
    				//organid:organid,
    				orgname:orgname,
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
    						window.location.href=base + "/sys/orgmgr/list";
        					closeorg_dialog();
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