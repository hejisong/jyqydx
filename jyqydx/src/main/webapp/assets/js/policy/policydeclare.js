
var myEditor;
$(function(){
    var content = "";
    if($("#filecontent")[0].childNodes[0]!=undefined){
        content = $("#filecontent")[0].childNodes[0].data;
    }
    var option = {
        initialContent:content,
        //initialContent:"<p>aaa<strong>aa</strong></p>",
        toolbars:[
            ['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
                'print', 'preview', 'searchreplace', 'help']
        ],
        minFrameHeight:220
    };
    myEditor = new baidu.editor.ui.Editor(option);
    myEditor.render("myEditor");
});




var FormValidation  = function() {
    var handleValidation = function() {
        var form = $('#policydeclareformid');
//		var error = $('.alert-danger', form);
//		var success = $('.alert-success', form);
        var formflag = true;
        form.validate({
            errorElement : 'div', //default input error message container
            errorClass : 'help-block help-block-error', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "", // validate all fields including form hidden input
            rules : {

                declarename : {
                    required : true,
                    maxlength  :200
                },

                releasedate : {
                    required : true
                } ,
                declare_start_date : {
                    required : true

                }  ,
                declare_end_date : {
                    required : true
                },
                policyfilename : {
                    required : true
                } ,
                myEditor : {
                    required : true
                }

            },
            messages : {

                declarename : {
                    required :"请输入通知标题！",
                    maxlength  :"通知标题长度不能超过200字符！"
                },

                releasedate : {
                    required :"请输入发布日期！"

                } ,
                declare_start_date : {
                    required : "请输入申报开始时间！",

                } ,
                declare_end_date : {
                    required : "请输入申报结束时间！"
                } ,
                policyfilename : {
                    required : "请选择申报政策！"
                } ,
                myEditor : {
                    required : "请输入通知内容！"
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





