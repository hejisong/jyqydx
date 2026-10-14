[#assign jss=["assets/js/jquery.validate.min.js","assets/js/ace-extra.min.js"]]
[#import "common/layout.ftl" as layout] 
[@layout.main
modelcode=['home'] otherjs=jss sername=['修改密码']]
<div class="row">
	<div class="col-xs-12">
	[#if errormsg??&&errormsg=="errormsg"]
	[#--	<div class="alert alert-block alert-success" style="text-align:center;color:red;margin-bottom:3px;font-size: 16px;">
			您的密码未修改，请立即修改密码(注：密码长度10至16位，必须包含数字、大小写字母和特殊字符，特殊字符仅限于!@#%，且不能出现空格！)
		</div>--]
	[#else]
		[#--<div class="alert alert-block alert-success" style="text-align:center;color:red;margin-bottom:3px;font-size: 16px;">
			注：密码长度10至16位，必须包含数字、大小写字母和特殊字符，特殊字符仅限于!@#%，且不能出现空格！
		</div>--]
	[/#if]
	
		<div id="errorMessage" class="alert alert-block alert-success" style="display:none;color:red;"></div>
		<form class="form-horizontal" role="form" id="changepwdformid"
			action="${base}/changepassword" method="post">
			<table class="table-form table-form-bordered2 table-bg">
				<tbody>
					<tr>
						<td class="td-center" style="width:35%">请输入原密码</td>
						<td style="width:35%"><input type="password" id="oldpassage" name="oldpassage"value="${oldpassage }" class="form-control"></td>
						<td class="td-center" style="width:30%"></td>
					</tr>
					<tr>
						<td class="td-center" style="width:35%">请输入新密码</td>
						<td style="width:35%"><input  type="password" id="passage" name="passage"
								value="${passage }" class="form-control"></td>
						<td class="td-center" style="width:30%"></td>
					</tr>
					<tr>
						<td class="td-center" style="width:35%">请确认新密码</td>
						<td style="width:35%"><input   type="password" id="conpassage" name="conpassage"
								value="${conpassage }" class="form-control"></td>
						<td class="td-center" style="width:30%"></td>
					</tr>
				</tbody>
			</table>
			<div class="clearfix form-actions" style="text-align: center;">
				<button class="btn btn-xs btn-info btn-width" type="button" onclick="changePwd()">提 交</button>
				<button class="btn btn-xs btn-info btn-width" type="button" onclick="history.go(-1)">取 消</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">

function checkpwd(){
	var password=$("#passage").val();
	
	var reg=/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#%]).{10,16}$/;
	var reg2=/^(?=.*?[A-Z]).{10,16}$/;
	var reg3=/^(?=.*?[a-z]).{10,16}$/;
	var reg4=/^(?=.*?[0-9]).{10,16}$/;
	var reg5=/^(?=.*?[!@#%]).{10,16}$/;
	var reg6=/^[a-zA-Z0-9!@#%]*$/;
	if(password.length>16){
		bootbox.alert("密码不得超过16位！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(password.length<10){
		bootbox.alert("密码不得少于10位！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg2.test(password)== false){
		bootbox.alert("密码必须含有大写字母！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg3.test(password)== false){
		bootbox.alert("密码必须含有小写字母！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg4.test(password)== false){
		bootbox.alert("密码必须含有数字！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg5.test(password)== false){
		bootbox.alert("密码必须含有特殊字符，特殊字符仅限于!@#%");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg5.test(password)== false){
		bootbox.alert("密码含有非法特殊字符，特殊字符仅限于!@#%");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}else if(reg.test(password)== false){
		bootbox.alert("密码安全级别太低，请输入10至16位，必须包含数字、大小写字母和特殊字符，特殊字符仅限于!@#%，且不能出现空格！");
		$("#passage")[0].value="";
		$("#passage").focus(); 
		return;
	}
}
function changePwd(){
	var oldpassage = $("#oldpassage").val();
	var passage = $("#passage").val();
	var conpassage = $("#conpassage").val();
	if(oldpassage==""||oldpassage==undefined){
		bootbox.alert("请输入原密码！");
		return;
	}else if(passage==""||oldpassage==undefined){
		bootbox.alert("请输入新密码！");
		return;
	}else if(conpassage==""||conpassage==undefined){
		bootbox.alert("请再次输入新密码！");
		return;
	}else if(passage!=conpassage){
		bootbox.alert("新密码两次输入不一致！");
		return;
	}
	$.ajax({
		type:"post",
		data:$("#changepwdformid").serialize(),
		url:base+"/savechangepwd",
		dataType:"text",
		async:false,
		success:function(data){
			if(data=="OK"){
				bootbox.alert("修改成功！", function() {
					window.location.href = base + "/logout";
				});
				
			}else{
				if(data=="noSafty"){
					bootbox.alert("密码安全级别太低，请输入10至16位，必须包含数字、大小写字母和特殊字符，特殊字符仅限于!@#%，且不能出现空格！");
				}
				else if(data=="erroroldpwd"){
					$("#oldpassage").val("");
					bootbox.alert("原密码输入错误，请重新输入！");
				}else if(data=="noOldpwd"){
					bootbox.alert("请输入原密码！");
				}else if(data=="noNewpwd"){
					bootbox.alert("请输入新密码！");
				}else if(data=="noConpwd"){
					bootbox.alert("请再次输入新密码！");
				}else if(data=="noConpwd"){
					bootbox.alert("请再次输入新密码！");
				}else if(data=="notequal"){
					$("#conpassage").val("");
					bootbox.alert("新密码两次输入不一致！");
				}else if(data=="equalpwd"){
					$("#conpassage").val("");
					$("#passage").val("");
					bootbox.alert("新密码不能和原密码相等！");
				}
			} 
		},
		error:function(){
			bootbox.alert("修改失败！");
		}
	});
}
</script>
[/@layout.main]
