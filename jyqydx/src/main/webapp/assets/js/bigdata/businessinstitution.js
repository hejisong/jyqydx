
var FormValidation  = function() {
    var handleValidation = function() {
        var form = $('#formid');
//		var error = $('.alert-danger', form);
//		var success = $('.alert-success', form);
        var formflag = true;
        form.validate({
            errorElement : 'div', //default input error message container
            errorClass : 'help-block help-block-error', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "", // validate all fields including form hidden input
            rules : {

                enterprisename : {
                    required : true,
                    maxlength  :200
                },

                recapital : {
                    required : true,
                    maxlength  :200
                } ,
                businessregisnum : {
                    required : true,
                    maxlength  :200

                }  ,
                lrepresentative : {
                    required : true,
                    maxlength  :200
                },
                registrationauthority : {
                    required : true,
                    maxlength  :200
                } ,

                dateincorporation : {
                    required : true
                },
                operationstatus : {
                    required : true,
                    maxlength  :200
                },
                uscreditcode : {
                    required : true,
                    maxlength  :200
                }

            },
            messages : {

                enterprisename : {
                    required :"请输入企业名称！",
                    maxlength  :"企业名称长度不能超过200字符！"
                },

                recapital : {
                    required :"请输入注册资本！",
                    maxlength  :"注册资本长度不能超过200字符！"
                } ,
                lrepresentative : {
                    required :"请输入企业法定代表人！",
                    maxlength  :"企业法定代表人长度不能超过200字符！"
                },
                businessregisnum : {
                    required :"请输入工商注册号！",
                    maxlength  :"工商注册号长度不能超过200字符！"

                }  ,
                registrationauthority : {
                    required :"请输入登记机关！",
                    maxlength  :"登记机关长度不能超过200字符！"
                } ,

                dateincorporation : {
                    required :"请输入成立日期！"
                },
                operationstatus : {
                    required :"请输入经营状态！",
                    maxlength  :"经营状态长度不能超过200字符！"
                } ,
                uscreditcode : {
                    required :"请输入统一社会信用代码！",
                    maxlength  :"统一社会信用代码长度不能超过200字符！"
                }


            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.login-form')).show();
            },
            /*highlight: function (e) {
                $(e).closest('.form-group').addClass('has-error');
            },*/
            highlight: function (e) {
                $(e).attr("style","border-color: #f09784;");
                $(e).parent().prev().css("color","#f09784");
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
                        $("#"+id).parent().prev().css("color","");
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
                if(formflag){
                    formflag = false;
                    saveinfo();
                }else{
                    $("#formstate").val("Y");
                    formflag = true;
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





