[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/businessinstitution.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','工商信息'] modelcode=["targetdata","businessinstitution"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/businessinstitution/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.companyid??]<input type="hidden" name="companyid" value="${info.companyid }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="enterprisename" name="enterprisename" value="${info.enterprisename }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 曾用名 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="nameusedbefore" name="nameusedbefore" value="${info.nameusedbefore }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 注册资本<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="recapital" name="recapital" value="${info.recapital }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 地址</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="companyregisaddress" name="companyregisaddress" value="${info.companyregisaddress }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业法定代表人<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="lrepresentative" name="lrepresentative" value="${info.lrepresentative }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 法定代表人手机号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="phoneno" name="phoneno" value="${info.phoneno }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 工商注册号<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="businessregisnum" name="businessregisnum" value="${info.businessregisnum }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 纳税人识别号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tpidentificationnum" name="tpidentificationnum" value="${info.tpidentificationnum }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 经营范围 </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea  id="naturebusiness" name="naturebusiness" value="" class="form-control" rows="6">${info.naturebusiness }</textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 营业开始日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="term_startdate" id="term_startdate" data-date-format="yyyy-MM-dd" readonly
								   value="${info.term_startdate}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 营业结束日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="term_enddate" id="term_enddate" data-date-format="yyyy-MM-dd" readonly
								   value="${info.term_enddate}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
				</div>


				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 登记机关<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="registrationauthority" name="registrationauthority" value="${info.registrationauthority }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 成立日期<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="dateincorporation" id="dateincorporation" data-date-format="yyyy-MM-dd" readonly
								   value="${info.dateincorporation}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>


				</div>



				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 注销日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="end_date" id="end_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.end_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 核准日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="columcheck_date" id="columcheck_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.columcheck_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
				</div>


				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 经营状态<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="operationstatus" name="operationstatus" value="${info.operationstatus }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 组织机构号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="organcode" name="organcode" value="${info.organcode }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 统一社会信用代码<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="uscreditcode" name="uscreditcode" value="${info.uscreditcode }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 省份缩写 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="province" name="province" value="${info.province }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 城市编码</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="city" name="city" value="${info.city }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业分类 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="typesenterprises" name="typesenterprises" value="${info.typesenterprises }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 是否上市</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right" style="padding-top: 4px;">
							<input name="is_quoted" type="radio" value="1" [#if info.is_quoted!='0'] checked [/#if]>是</input>
							<input name="is_quoted" type="radio" value="0" [#if info.is_quoted=='0'] checked [/#if]>否</input>
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
		$("#loading_state").val("1");
		$.ajax({
			type:"post",
			url:base+"/businessinstitution/save",
			data:$("#formid").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/businessinstitution/list";
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




</script>
[/@layout.main]