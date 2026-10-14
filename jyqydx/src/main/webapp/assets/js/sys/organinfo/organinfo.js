$(function(){
	FormValidation.init();
});
var FormValidation = function() {
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
				orgname : {
					required : true,
					maxlength : 100
				},
				orgcode : {
					required : true,
					maxlength : 10
				},				
				linkman : {
					required : true,
					maxlength : 20
				},
				linkphone : {
					required : true,
					maxlength : 20
				}
			},
			messages : {
				orgname : {
					required :"请输入机构名称！",
					maxlength :"机构名称长度过长！"
				},
				orgcode : {
					required : "请输入机构代码！",
					maxlength : "机构代码长度过长！"
				},
				linkman : {
					required : "请输入联系人！",
					maxlength : "联系人长度过长！"
				},
				linkphone : {
					required : "请输入联系方式！",
					maxlength : "联系方式长度过长！"
				}
			},
			invalidHandler: function (event, validator) { //display error alert on form submit   
				$('.alert-danger', $('.login-form')).show();
			},
			highlight: function (e) {
				$(e).closest('.form-group').addClass('has-error');
			},
	
			success: function (e) {
				//$(e).closest('.form-group').addClass('has-info').removeClass('has-error');
				var divs = $("#errorMessage div");
				var isnull =0;
				for(var i=0;i<divs.length;i++){
					if(divs.eq(i).text()!=""){
						isnull++;
					}else{
						var id = divs.eq(i).attr("id").split("_")[1];
						$("#"+id).parent().parent().parent().removeClass().addClass("form-group"); 
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
					submitform();
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