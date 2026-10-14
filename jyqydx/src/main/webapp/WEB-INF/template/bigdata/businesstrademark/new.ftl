[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/businesstrademark.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','商标信息'] modelcode=["targetdata","businesstrademark"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/businesstrademark/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.id??]<input type="hidden" name="id" value="${info.id }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 商标名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="trademarkname" name="trademarkname" value="${info.trademarkname }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="companyname" name="companyname" value="${info.companyname }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="reg_number" name="reg_number" value="${info.reg_number }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 状态</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="status" name="status" value="${info.status }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申请日期<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="apply_date" id="apply_date" data-date-format="yyyy-MM-dd" readonly
								   value="${info.apply_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">专用权期限<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="period" name="period" value="${info.period }" class="col-xs-12">
						</div>
					</div>


				</div>

				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 代理人名称</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="agent" name="agent" value="${info.agent }" class="col-xs-12">
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
			url:base+"/businesstrademark/save",
			data:$("#formid").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/businesstrademark/list";
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