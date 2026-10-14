[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/groupinformation.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','集团信息'] modelcode=["targetdata","groupinformation"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/groupinformation/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.groupid??]<input type="hidden" name="groupid" value="${info.groupid }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 集团全称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="group_name" name="group_name" value="${info.group_name }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 集团简称</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="group_abbreviation" name="group_abbreviation" value="${info.group_abbreviation }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 文书编号<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="instrument_number" name="instrument_number" value="${info.instrument_number }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 联系人 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="personnel" name="personnel" value="${info.personnel }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">联系电话</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="telephone" name="telephone" value="${info.telephone }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">名称核准号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="approva_number" name="approva_number" value="${info.approva_number }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 核准日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="approved_date" id="approved_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.approved_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 集团登记证号<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registration_number" name="registration_number" value="${info.registration_number }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册总资本</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registered_ttotal_capital" name="registered_ttotal_capital" value="${info.registered_ttotal_capital }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册资本<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registered_capital" name="registered_capital" value="${info.registered_capital }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">母公司名称</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="pcompany_name" name="pcompany_name" value="${info.pcompany_name }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">母公司住所</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="pcompany_domicile" name="pcompany_domicile" value="${info.pcompany_domicile }" class="col-xs-12">
						</div>
					</div>

				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">母公司经济性质</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="pcompany_economic_nature" name="pcompany_economic_nature" value="${info.pcompany_economic_nature }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">母公司注册资本</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="pcompany_registered_capital" name="pcompany_registered_capital" value="${info.pcompany_registered_capital }" class="col-xs-12">
						</div>
					</div>

				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">成员总数</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="members_number" name="members_number" value="${info.members_number }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">成立日期<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="establishdate" id="establishdate" data-date-format="yyyy-MM-dd" readonly
								   value="${info.establishdate}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>

				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">登记机关<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registration_authority" name="registration_authority" value="${info.registration_authority }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">所属管区</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="belong_district" name="belong_district" value="${info.belong_district }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">代理标志</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="agency_logo" name="agency_logo" value="${info.agency_logo }" class="col-xs-12">
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
				url:base+"/groupinformation/save",
				data:$("#formid").serialize(),
				async:true,
				success:function(data){

					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/groupinformation/list";
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