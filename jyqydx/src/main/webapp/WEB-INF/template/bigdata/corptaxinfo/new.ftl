[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/corptaxinfo.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','税务税款信息'] modelcode=["targetdata","corptaxinfo"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/corptaxinfo/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.id??]<input type="hidden" name="id" value="${info.id }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="companyname" name="companyname" value="${info.companyname }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 统一社会信用代码<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="uscreditcode" name="uscreditcode" value="${info.uscreditcode }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 机构全称<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fullname" name="fullname" value="${info.fullname }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 税种<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tax" name="tax" value="${info.tax }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">实缴税额(万元)<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tax_actually_paid" name="tax_actually_paid" value="${info.tax_actually_paid }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">行政区划</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="administrative_divisions" name="administrative_divisions" value="${info.administrative_divisions }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 注册地址<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registered_address" name="registered_address" value="${info.registered_address }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 登记日期<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="registration_date" id="registration_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.registration_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>

				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">主管税务机关</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tax_authorities" name="tax_authorities" value="${info.tax_authorities }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">纳税区县</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tax_district" name="tax_district" value="${info.tax_district }" class="col-xs-12">
						</div>
					</div>

				</div>






				<div class="clearfix form-actions" style="text-align: center;">
					<button class="btn btn-sm btn-info" type="submit" >保 存</button>
					<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
				</div>
			</form>
		</div>
	</div>
	<input type="hidden" id="formstate"  value="Y">
<script type="text/javascript">
	$(function(){
		FormValidation.init();
		initTime();
	});
	function initTime(){
		$(".form_datetime").datetimepicker({
			autoclose: true,
			isRTL: Metronic.isRTL(),
			format: "yyyy-mm-dd",
			minuteStep : 1,
			minView : 2,
			pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
			language: 'zh_CN'
		});
	}



	function saveinfo() {
		var telephone=$("#telephone").val();
		if(telephone!=null&&telephone!=''&&!checkTel(telephone)){
			bootbox.alert("请填写正确的电话号码！11位数字的手机号码或如025-88888888格式的固定电话号码！");
			return false;
		} else{
			$("#loading_state").val("1");
			$.ajax({
				type:"post",
				url:base+"/corptaxinfo/save",
				data:$("#formid").serialize(),
				async:true,
				success:function(data){

					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/corptaxinfo/list";
						});

					}else{
						$("#formstate").val("Y");
						bootbox.alert("保存失败");


					}
				},
				error:function(){
					$("#formstate").val("Y");
					bootbox.alert("数据加载失败");
				}
			});

		}




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

</script>
[/@layout.main]