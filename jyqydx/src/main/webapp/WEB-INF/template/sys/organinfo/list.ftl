[#assign jss=["assets/js/validate.js","assets/js/sys/organinfo/organtree.js"
,"assets/js/sys/organinfo/ui-tree.js","assets/js/sys/organinfo/jstree.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/global/plugins/jstree/style.min.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','机构管理'] modelcode=["sysmgr","OrganManage"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
   [#-- [#include "sys/organinfo/addorg_tanchu.ftl"]--]
	<div class="row">

			<div style="height:650px;width: 36%;overflow-y: auto;float: left;">
				<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:622px;">
					<legend style="width: 50px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">机构树
                       [#-- <button class="btn btn-xs btn-info" type="button" onclick="addOrg();">新增</button>
                        <button class="btn btn-xs btn-danger" type="button" onclick="deleteOrg();">删除</button>--]
                    </legend>

                    <input type="hidden" name="superorganid" id="superorganid" >
			          [#include "sys/organinfo/organtree.ftl"]
				</fieldset>
		</div>


		<div style="height:650px;width: 60%;overflow-y: auto;float: left;margin-left: 20px;">
			<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;">
				<legend style="width: 60px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">机构概况</legend>
				<form class="form-horizontal" role="form" id="formid" action="${base}/sys/organinfo/save" method="post">
					[#if valid_error??]
						<div class="alert alert-danger display-hide" style="display: block;">
							<button class="close" data-close="alert"></button>
							[#list valid_error as error]
								<li>${error.defaultMessage}</li> [/#list]
						</div>
					[/#if]
					<input type="hidden" name="id" id="id" value="${organinfo.id }">

					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构名称 </label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="orgname" name="orgname" value="${organinfo.orgname }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构简称 </label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="displayname" name="displayname" value="${organinfo.displayname }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构代码 </label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="orgcode" name="orgcode" value="${organinfo.orgcode }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 上级部门 </label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input  type="hidden" id="superdepartment" name="superdepartment" value="{organinfo.orgcode }" >
								<input type="text" id="superorg" name="superorg" value="" class="col-xs-12" readonly>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 联系人 </label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="linkman" name="linkman" value="${organinfo.linkman }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门办公电话</label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="telephonenumber" name="telephonenumber" value="${organinfo.telephonenumber }" class="col-xs-12">
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 邮箱</label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="mail" name="mail" value="${organinfo.mail }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 通讯地址</label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="orgaddr" name="orgaddr" value="${organinfo.orgaddr }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门邮政编码</label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="postalcode" name="postalcode" value="${organinfo.postalcode }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 部门传真号码</label>
						<div class="col-xs-5">
							<div class="block input-icon input-icon-right">
								<input type="text" id="facsimiletelephonenumber" name="facsimiletelephonenumber" value="${organinfo.facsimiletelephonenumber }" class="col-xs-12">
							</div>
						</div>
					</div>

					<div class="clearfix form-actions" style="text-align: center;">
						<button class="btn btn-sm btn-info" type="button" onclick="saveOrg();">保 存</button>
						<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
					</div>
				</form>
			</fieldset>
		</div>
	</div>


	<script type="text/javascript">
		function deleteOrg(id){
			bootbox.confirm("确认删除该机构?",function (result) {
				if(result==true){
					window.location.href=base + "/sys/organinfo/delete/"+id;
				}

			})
		}





		function toorgan(organid) {
			$.ajax({
				url : base + "/sys/organinfo/findOrg/"+organid,
				type : "POST",
				async : false,
				cache : false,
				dataType : "json",
				success : function(data) {
					$("#id").val(data.id);
					$("#superorganid").val(data.id);
					$("#orgname").val(data.orgname);
					$("#displayname").val(data.displayname);
					$("#orgcode").val(data.orgcode);
					$("#superdepartment").val(data.superdepartment);
					$("#superorg").val(data.superorg);
					$("#linkman").val(data.linkman);
					$("#telephonenumber").val(data.telephonenumber);
					$("#mail").val(data.mail);
					$("#orgaddr").val(data.orgaddr);
					$("#postalcode").val(data.postalcode);
					$("#facsimiletelephonenumber").val(data.facsimiletelephonenumber);

				},
				error : function() {
				}
			});
		}

		function saveOrg() {
			var organid = $("#id").val();
			if(organid==""||organid==undefined){
				bootbox.alert("未选择机构");
				return false;
			}
			//部门办公电话
			var telephonenumber=$("#telephonenumber").val();
			if(telephonenumber!=null&&telephonenumber!=''&&telephonenumber!=undefined){
				if(!checkTel(telephonenumber)){
					bootbox.alert("请填写正确的电话号码！11位数字的手机号码或如025-88888888格式的固定电话号码！");
					return false;
				}
			}


			//邮箱
			var mail=$("#mail").val();
			if(mail!=null&&mail!=''&&mail!=undefined){
				if(!checkMail(mail)){
					bootbox.alert("请填写正确的邮箱！如a@b.xxx");
					return false;
				}
			}




			//部门邮政编码
			var postalcode=$("#postalcode").val();
			if(postalcode!=null&&postalcode!=''&&postalcode!=undefined){
				if(!checkPostcode(postalcode)){
					bootbox.alert("请填写正确的邮政编码！邮政编码应为6位的数字！");
					return false;
				}
			}



			//部门传真号码
			var facsimile=$("#facsimiletelephonenumber").val();
			if(facsimile!=null&&facsimile!=''&&facsimile!=undefined){
				if(!checkFacsimile(facsimile)){
					bootbox.alert("请填写正确的传真号码！传真格式为:XXX-12345678或XXXX-1234567或XXXX-12345678！");
					return false;
				}
			}







			$.ajax({
				url : base + "/sys/organinfo/updateorgan",
				type : "POST",
				async : false,
				dataType : "text",
				data : $('#formid').serialize(),
				success : function(data) {
						$("a").filter(".jstree-clicked").html(
								'<i class="jstree-icon jstree-themeicon"></i> '
								+ $("#orgname").val());
						if(data=="SUCCESS"){
							bootbox.alert("保存成功！");
						}else{
							bootbox.alert("保存失败！");
						}
				}
			});
		}





		function checkTel(tel){
			var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
			var isMob=/^\d{11}$/;
			if(isMob.test(tel)||isPhone.test(tel)){
				return true;
			}
			else{
				return false;
			}
		}


		function checkMail(mail){
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
			if(reg.test(mail)){
				return true;
			}
			else{
				return false;
			}
		}



		function checkPostcode(postcode){
			var reg = new RegExp("^[0-9]{6}$"); //正则表达式
			if(reg.test(postcode)){
				return true;
			}
			else{
				return false;
			}
		}

		function checkFacsimile(facsimile){
			var reg = new RegExp("^(\\d{3,4}-)?\\d{7,8}$"); //正则表达式
			if(reg.test(facsimile)){
				return true;
			}
			else{
				return false;
			}
		}





		function addOrg() {
            var superorganid = $("#superorganid").val();
            if(superorganid==""){
                bootbox.alert("请选择机构！");
                return;
            }else{
                openOrg();
            }

        }



        function saveaddOrg(ele){
            $("#addorgan tbody").html("");
            $.ajax({
                url : base + "/sys/msaorgan/" + $("#superorganid").val() + "/queryaddunit",
                type : "POST",
                async : false,
                dataType : "json",
                success : function(data) {

                    $(ele).next().click();
                },
                error : function() {
                }
            });
        }

        function addnewOrgan(){
            var organlevel = $("#organlevelid").val();
            var superorganid = $("#superorganid").val();
            $("#organresetid").click();
            $("#organlevelid").val(organlevel);
            $("#superorganid").val(superorganid);
            $.ajax({
                url : base + "/sys/msaorgan/" + $("#superorganid").val() + "/initcode",
                type : "POST",
                async : false,
                dataType : "text",
                success : function(data) {
                    if(data=="error"){
                        bootbox.alert("机构编号,机构代码生成失败");
                    }else{
                        var info = eval("(" + data + ")");
                        $("#new_organid").val(info.organid);
                        $("#new_msacode").val(info.msacode);
                        $("#addorganModalid").click();
                        $("#addmsaorganclose").click();
                    }
                },
                error : function() {
                }
            });

        }

	</script>
[/@layout.main]
