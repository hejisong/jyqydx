[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/legalpersonsblack.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','法人黑名单'] modelcode=["targetdata","legalpersonsblack"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/legalpersonsblack/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.id??]<input type="hidden" name="id" value="${info.id }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 法人名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="legalpersons" name="legalpersons" value="${info.legalpersons }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 法人身份证号<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="legalcardid" name="legalcardid" value="${info.legalcardid }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="company_name" name="company_name" value="${info.company_name }" class="col-xs-12">
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


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 类型</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="type" name="type" value="${info.type }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 确认严重失信时间<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"   name="serious_confirm_date" id="serious_confirm_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.serious_confirm_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>


				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 认定单位</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="recognized_unit" name="recognized_unit" value="${info.recognized_unit }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 数据来源单位</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="data_source_unit" name="data_source_unit" value="${info.data_source_unit }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">主要失信事实<span style="color: red;">*</span></label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea type="text" id="major_untrustworthy_facts" name="major_untrustworthy_facts"  class="form-control" rows="10">${info.major_untrustworthy_facts}</textarea>
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
				url:base+"/legalpersonsblack/save",
				data:$("#formid").serialize(),
				async:true,
				success:function(data){

					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/legalpersonsblack/list";
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