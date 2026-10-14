[#assign jss=["assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/jquery.validate.min.js"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','permissionmgr'] otherjs=jss sername=['系统管理','权限管理']]
<div class="row">
	<div class="col-xs-12">
		<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
		<form class="form-horizontal" role="form" id="formid" action="${base}/xyjsmgr/sys/sysinfo/permission/update" method="post">
			<input type="hidden"  id="id" name="id" value="${permission.id }"  >
 			<input type="hidden"  id="createdate" name="createdate" value="${(permission.createdate ?string('yyyy-MM-dd HH:mm:ss'))!}"  >
 			<table id="sample-table-1" class="table-form table-form-bordered table-bg">
				<tbody>
					<tr>
						<td class="td-center" style="width:30%">名称<span style="color: red;float:right;">*</span></td>
						<td style="width:40%"><input class="form-control" type="text" id="name" name="name" value="${permission.name }" > </td>
						<td style="width:30%"></td>
					</tr>
					<tr>
						<td class="td-center" style="width:30%">权限<span style="color: red;float:right;">*</span></td>
						<td style="width:40%"><input class="form-control" type="text" id="permission" name="permission" value="${permission.permission }" > </td>
						<td style="width:30%"></td>
					</tr>
					<tr>
						<td class="td-center" style="width:30%">地址<span style="color: red;float:right;">*</span></td>
						<td style="width:40%"><input class="form-control" type="text" id="value" name="value" value="${permission.value }" > </td>
						<td style="width:30%"></td>
					</tr>
					 <tr>
						<td class="td-center" style="width:30%">描述<span style="color: red;float:right;">*</span></td>
						<td style="width:40%"><input class="form-control" type="text" id="description" name="description" value="${permission.description }" > </td>
						<td style="width:30%"></td>
					</tr>
					 <tr>
						<td class="td-center" style="width:30%">角色 </td>
						<td style="width:40%">
								<table id="sample-table-project" class="table   table-bordered  ">				
 									<tbody >
									 <tr >
										<th style="width:5%;"><input type="checkbox" id="checkall"  onclick="checkallrole();"> </th>
										<th>角色名</th>
										<th>角色描述</th>
										<th>角色类型</th>
									</tr>
									[#list rolelist as role]
									<tr>
										<td><input type="checkbox" class="roleid" name="roleid" value="${role.id }"
										[#if role_permissionlist??] [#list role_permissionlist as role_permission][#if role_permission.role_id== role.id  ]checked[/#if][/#list][/#if]></td>
										<td>${role.rolename }</td>
 										<td>${role.description }</td>
										<td>[#if role.roletype=='1'] 公路
											[#elseif role.roletype=='2'] 水运
											[#elseif role.roletype=='3'] 其它
											[/#if]
										</td>
									</tr>
									[/#list]
									</tbody>
								</table>
						</td>
						<td style="width:30%"></td>
					</tr>
				</tbody>
			</table>
 			<div class="clearfix form-actions" style="text-align: center;">
				<button class="btn   btn-xs btn-width  btn-info" type="submit" id="submitid">保 存</button>
				<button class="btn   btn-xs btn-width  btn-info" type="button" onclick="history.go(-1)">取 消</button>
			</div>
		</form>
	</div>
</div>
 <script type="text/javascript">
 function checkallrole(){
	if ($("#checkall").attr("checked")) { 
		$(".roleid").attr("checked", true);  
	}else{
		$(".roleid").attr("checked", false);  
	}
 }
 $(function(){
 	  	FormValidation.init();
	});
//校验
var FormValidation  = function() {
	var handleValidation = function() {
		var form = $('#formid');
		var error = $('.alert-danger', form);
		var success = $('.alert-success', form);
		var formflag = true;
		form.validate({
			errorElement : 'div', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			rules : {
				name : {
					required : true,
 					maxlength  :32
 				} ,
 				permission : {
					required : true,
 					maxlength  :50
 				} ,
 				value : {
					required : true,
 					maxlength  :256
 				} ,
 				description : {
					required : true,
 					maxlength  :256
 				}
			},
			messages : {
				name : {
					required : "请输入名称！",
 					maxlength  :"名称长度不能大于32字符！"
 				} ,
 				permission : {
					required : "请输入权限！", 
 					maxlength  :"权限长度不能大于50字符！"
 				} ,
 				value : {
					required : "请输入地址！", 
 					maxlength  :"地址长度不能大于256字符！"
 				} ,
 				description : {
					required : "请输入描述！",  
 					maxlength  :"描述长度不能大于256字符！"
 				}
			},
			invalidHandler: function (event, validator) { //display error alert on form submit   
				$('.alert-danger', $('.login-form')).show();
			},
			highlight: function (e) {
				$(e).attr("style","border-color: #f09784;");
				$(e).parent().parent().prev().css("color","#f09784");
			},
			success: function (e) {
				//$(e).closest('.form-group').addClass('has-info').removeClass('has-error');
				var divs = $("#errorMessage div");
				var isnull =0;
				for(var i=0;i<divs.length;i++){
					if(divs.eq(i).text()!=""){
						isnull++;
					}else{
						var id = divs.eq(i).attr("id").substr(4);
						$("#"+id).attr("style","");
						$("#"+id).parent().parent().prev().css("color","");
					}
				}
				if(isnull==0){
					$("#errorMessage").hide();
				}
			},
	
			errorPlacement: function (error, element) {
				if(element.is(':checkbox') || element.is(':radio')) {
					var controls = element.closest('div[class*="col-"]');
					if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
					else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
				}
				else if(element.is('.select2')) {
					error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
				}
				else if(element.is('.chosen-select')) {
					error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
				}
				else{
					var errormessage = $("#errorMessage");
					var divs = $("#errorMessage div");
					var errorname = element.attr("name");
					var html = "<div  id='err_"+errorname+"'>"+error.text()+"</div>"
					var addorreplace = true;
					for(var i=0;i<divs.length;i++){
						if(divs.eq(i).attr("id")==('err_'+errorname)){
							addorreplace= false;
							divs.eq(i).html(error.text());
							errormessage.show();
						}
					}
					if(addorreplace){
						errormessage.append(html);
						errormessage.show();
					}
					
				} 
			},
            submitHandler:function (form) {
            	success.show();
                error.hide(); 
                if(formflag){
                	formflag = false;
                	form.submit();
                }
            }
		});
	}
	return {
		init : function() {
			handleValidation();
		}
	};
}();

</script> 
[/@layout.main]
