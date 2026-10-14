[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/policy/policydeclare.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js",
"assets/ueditor/ueditor.config.js","assets/ueditor/ueditor.all.js","assets/ueditor/lang/zh-cn/zh-cn.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['政策管理','政策申报通知'] modelcode=["qyzc","policydeclare"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="policydeclareformid" action="${base}/policydeclare/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if policydeclare.declareid??]<input type="hidden" name="declareid" value="${policydeclare.declareid }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 通知标题 </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<input type="text" id="declarename" readonly name="declarename" value="${policydeclare.declarename }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发布单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="hidden" id="releaseorgid" name="releaseorgid" value="${policydeclare.releaseorgid }" readonly>
							<input type="text" id="releaseorgname" readonly name="releaseorgname" value="${policydeclare.releaseorgname }" class="col-xs-12" >
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发布日期 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="releasedate" readonly name="releasedate" value="${policydeclare.releasedate }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报开始时间 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="declare_start_date" readonly name="declare_start_date" value="${policydeclare.declare_start_date }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报结束时间 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="declare_end_date" readonly name="declare_end_date" value="${policydeclare.declare_end_date }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 是否公开</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right" style="padding-top: 4px;">
							<input readonly name="ispublic" type="radio" value="1" [#if policydeclare.ispublic!='0'] checked [/#if]>公开</input>
							<input readonly name="ispublic" type="radio" value="0" [#if policydeclare.ispublic=='0'] checked [/#if]>不公开</input>
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 适用园区</label>
					<input type="hidden" id="applyparkid" readonly name="applyparkid" value="${policydeclare.applyparkid }" class="col-xs-12">
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right" >
							<input type="text" id="applyparkname" readonly name="applyparkname" value="${policydeclare.applyparkname }" class="col-xs-12">

						</div>

					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报政策</label>
						<input type="hidden" id="policyfileid" name="policyfileid" readonly value="${policydeclare.policyfileid }" class="col-xs-12">
						<div class="col-xs-8">
							<div class="block input-icon input-icon-right">
								<input type="text" id="policyfilename" name="policyfilename" readonly readonly value="${policydeclare.policyfilename }" class="col-xs-12">
							</div>
						</div>


				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 附件上传</label>
					<div class="col-xs-8">

						[#if policydeclare.uploadfileid??]
							[#list policydeclare.uploadfileList as uploadfile]
								<span>
							<input type="hidden" name="uploadfilename" id="uploadfilename" value="${uploadfile.filename}">
						    <input type="hidden" name="uploadfileid" id="uploadfileid" value="${uploadfile.id}">
							<a  href='${base}/common/file/download/${uploadfile.id }'target='_blank'>${uploadfile.filename}</a>
						</span>
							[/#list]
						[/#if]
					</div>



				</div>




				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 通知内容</label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea style="display:none;" name="filecontent" id="filecontent">${policydeclare.filecontent}</textarea>
							<textarea id="myEditor" name="myEditor"  style="height:420px;" readonly="readonly"></textarea>
						</div>
					</div>
				</div>

				<div class="clearfix form-actions" style="text-align: center;">
					<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
				</div>
			</form>
		</div>
	</div>
	<input type="hidden" id="formstate"  value="Y">
	[#include "policydeclare/t_file.ftl"]
	[#include "policydeclare/t_park.ftl"]
<script type="text/javascript">
	$(function(){
		FormValidation.init();
		initTime();
		fileUpload();
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


</script>
[/@layout.main]