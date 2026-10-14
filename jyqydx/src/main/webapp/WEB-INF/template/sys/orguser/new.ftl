[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[@layout.main modelcode=["sysmgr","gl_orguser"] otherjs=jss othercss=css sername=['系统管理','用户管理']]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
			 <div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
				<form class="form-horizontal" role="form" id="formid" >
				[#if valid_error??]
					<div class="alert alert-danger display-hide"
						style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
						<li>${error.defaultMessage}</li> [/#list]
					</div>
			     [/#if]
				<input type="hidden" id="id" name="id" value="${user.id}"/>
				<input type="hidden" id="loginname" name="loginname" value="${user.loginname}"/>
				<input type="hidden" id="username" name="username" value="${user.username}"/>
				<input type="hidden" id="linknum" name="linknum" value="${user.linknum}"/>
<!-- 				<input type="hidden" id="rolecode" name="rolecode" value="${rolecode}"/> -->
			    <table class="table-form table-form-bordered table-bg">
			    <tbody>
			<!--  <tr>
			 	<td class="td-center" style="width:15%;">姓名</td>
				<td style="width:35%;" ><input type="text" id="username" name="username" value="${user.username }" class="form-control formtd-width80"></td>
				<td class="td-center" style="width:15%;">身份证号</td>
				<td style="width:35%;" ><input type="text" id="linknum" name="linknum" value="${user.linknum}" class="form-control formtd-width80"></td>
				<td style="width:15%;" ></td>
			 </tr> -->
			 <tr>
			 	<td class="td-center" style="width:15%;">手机号码</td>
				<td style="width:35%;" ><input type="text" id="linktel" name="linktel" value="${user.linktel }" class="form-control formtd-width80"></td>
				<td class="td-center" style="width:15%;">联系邮箱</td>
				<td style="width:35%;" ><input type="text" id="linkemail" name="linkemail" value="${user.linkemail}" class="form-control formtd-width80"></td>
				<td style="width:15%;" ></td>
			 </tr>
			 [#if rolelist??&&rolelist?size>0]
				<tr>
					<td class="td-center" style="width:15%;">账号角色类型</td>
					<td style="width:35%;">
						<select class="form-control formtd-width" name="rolecode" id="rolecode">	
						    [#list rolelist as role]
							    <option value="${role.rolecode}" [#if rolecode==role.rolecode] selected="selected"[/#if]>${role.rolename}</option>
							   	[/#list]						 						   	
						</select>
					</td>
					<td class="td-center" style="width:15%;">是否接受和发送短信</td>
				<td style="width:35%;" >
					<select class="form-control formtd-width" name="accept_sms" id="accept_sms">	
						   <option value=""  [#if user.accept_sms=='' ] selected="selected"[/#if]></option>
						   <option value="1" [#if user.accept_sms=='1'] selected="selected"[/#if]>是</option>
						   <option value="2" [#if user.accept_sms=='2'] selected="selected"[/#if]>否</option> 	 						   	
					</select>
				</td>
					<td style="width:15%;" ></td>
				</tr>
			 [/#if]
			    </tbody>
			    </table>
				<div class="clearfix form-actions" style="text-align:center;">
					<button class="btn   btn-xs btn-width  btn-info" type="button" id="submitid" onclick="submitform()">保 存</button>
					<button class="btn   btn-xs btn-width  btn-info" type="button" onclick="history.go(-1)">取 消</button>
				</div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
/*  $(function(){
	chooseEvaltype();	
});  

  function chooseEvaltype(){
	 var corptype = $("#corp_type").val();
	 if(corptype=="1"){	
		  $("option[name='evaltype0']").show();
		  $("option[name='evaltype0']")[0].selected = true;
		  $("option[name='evaltype1']").hide();
		  $("option[name='evaltype2']").hide();
		  $("option[name='evaltype3']").show();
	  }else {	
		  $("option[name='evaltype0']").hide();
		  $("option[name='evaltype1']").show();
		  $("option[name='evaltype1']")[0].selected = true;
		  $("option[name='evaltype2']").show();
		  $("option[name='evaltype3']").show();
	  }
}  */
  

function submitform(){
	var formflag=true;
	var username = $("#username").val();
	var linknum = $("#linknum").val();
	var linktel = $("#linktel").val();
	var loginname = $("#loginname").val();
	var linkemail = $("#linkemail").val();
	var rolecode = $("#rolecode").val();
	var userId = $("#id").val();
	var  re = /^1\d{10}$/    //正则表达式
	var accept_sms = $("#accept_sms").val();
	if(accept_sms==1){
		 if (re.test(linktel)==false) { //判断字符是否是11位数字
			bootbox.alert("已选择接受或发送短信，请输入正确的手机号码！");
		 	return;
		}
	}
	if(linktel==null || linktel==""){
		bootbox.alert("请输入手机号码！");
	}else if(linkemail==null || linkemail==""){
		bootbox.alert("请输入联系人电子邮箱！");
	}else{
		if(formflag){
			formflag=false;
			$.ajax({
				type:"post",
				url:base + "/sys/usermgr/save",
				data:
				 {
					username:username,
					loginname:loginname,
					linktel:linktel,
					linknum:linknum,
					linkemail:linkemail,
					rolecode:rolecode,
					accept_sms:accept_sms,
					userId:userId
				} ,
				dataType:"text",
				success: function(data){
					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/sys/usermgr/list";
							closeUser_dialog();
						});
						
					}else{
						bootbox.alert("保存失败！");
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
[/@layout.main]