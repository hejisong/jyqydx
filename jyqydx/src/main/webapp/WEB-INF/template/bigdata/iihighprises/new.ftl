[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/iihighprises.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','高新技术企业认定'] modelcode=["targetdata","iihighprises"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/iihighprises/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.iiheeid??]<input type="hidden" name="iiheeid" value="${info.iiheeid }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="enterprisename" name="enterprisename" value="${info.enterprisename }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 认定年份<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="recognitionyear" name="recognitionyear" onblur="setYear();" value="${info.recognitionyear }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 证书编号<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="certificateno" name="certificateno" value="${info.certificateno }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">失效年份</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="validityterm" readonly name="validityterm" value="${info.validityterm }" class="col-xs-12">
						</div>
					</div>
				</div>

			[#--	<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">有效期</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="validityterm" name="validityterm" value="${info.validityterm }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 有效期截止日期</label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="expirydate" id="expirydate" data-date-format="yyyy-MM-dd" readonly
								   value="${info.expirydate}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>

				</div>--]

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 批准机关<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="approvalauthority" name="approvalauthority" value="${info.approvalauthority }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册地址</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="zcdz" name="zcdz" value="${info.zcdz }" class="col-xs-12">
						</div>
					</div>

				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">所得税征管机关</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="income_tax_agency" name="income_tax_agency" value="${info.income_tax_agency }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">经济性质</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="economic_nature" name="economic_nature" value="${info.economic_nature }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">领域A</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="area_a" name="area_a" value="${info.area_a }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">领域B</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="area_b" name="area_b" value="${info.area_b }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">行政区划</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="administrative_divisions" name="administrative_divisions" value="${info.administrative_divisions }" class="col-xs-12">
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">附件</label>
					<div id="proofFile" class="btn btn-xs btn-success" style="margin-left:0px;margin-right:0px;">添加附件</div>
					<span id="fujian_list">
						[#if info.enclosure??]
							<span>
							<input type="hidden" name="enclosurename" id="enclosurename" value="${info.enclosurename}">
						    <input type="hidden" name="enclosure" id="enclosure" value="${info.enclosure}">
							<a  href='${base}/common/file/download/${info.enclosure }'target='_blank'>${info.enclosurename}</a>
							<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>
						</span>
						[/#if]
					</span>

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

       function setYear() {
           var recognitionyear=$("#recognitionyear").val();
           $("#validityterm").val(parseInt(recognitionyear)+3)
	   }

		function saveinfo() {
			$("#loading_state").val("1");
			$.ajax({
				type:"post",
				url:base+"/iihighprises/save",
				data:$("#formid").serialize(),
				async:true,
				success:function(data){

					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/iihighprises/list";
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

		//证书附件
	function fileUpload() {
		var uploader = WebUploader.create({
			auto : true,
			swf : base + '/assets/js/common/webuploader/Uploader.swf',
			server : base + '/common/file',
			fileSingleSizeLimit : 20 * 1024 * 1024, // 5 M
			fileVal : "upfile",
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : '#proofFile',
			duplicate : true
			// 只允许选择图片文件。
			//accept : {
			//title : 'docs',
			//extensions : 'doc,docx',
			//mimeTypes : 'image/*'
			//}

		});
		//uploadAccept
		//uploader.on( 'uploadAccept', function( object , ret  ) {
		//});
		uploader.on('uploadSuccess',function(file, response) {
			var info = eval("(" + response._raw + ")");
			if (typeof (info['state']) != 'undefined') {
				if (info['state'] == 'SUCCESS') {
					var $divs = $("#fujian_list div");
					var name = info['name'];
					var size = info['fileSize'];
					var id = info['id'];
					var html = "";
					html = html
							+"<span>"
							+ "<input type='hidden' name='enclosurename'  id='enclosurename' value='"+name+"'>"
							+ "<input type='hidden' name='enclosure'   id='enclosure' value='"+id+"'>"
							+ "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
							+ "<span> </span>"
							+ "<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>"
							+"</span>"
					$("#fujian_list").html(html);

				} else {
					bootbox.alert('上传出错');
				}
			} else {
				bootbox.alert('上传出错');
			}

		});

		uploader.on('uploadError', function(file) {
			bootbox.alert('上传出错');
		});
		uploader.on('error', function(type) {
			if ("F_EXCEED_SIZE" == type) {
				bootbox.alert('图片大小不能超过20M！');
				return;
			}
			if ("Q_TYPE_DENIED" == type) {
				bootbox.alert('文件类型不正确！');
				return;
			}

		});
	}

	function deletefujianinfo(ele){
		$(ele).parent().remove();
	}


	</script>
[/@layout.main]
