[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/gxcwbqytjj.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','财务信息'] modelcode=["targetdata","gxcwbqytjj"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/gxcwbqytjj/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if info.id??]<input type="hidden" name="id" value="${info.id }">[/#if]
				
				<div class="form-group">


					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="companyname" name="companyname" value="${info.companyname }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 年份<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="year" name="year" value="${info.year }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">营业总收入（元）<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="sale_income" name="sale_income" value="${info.sale_income }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 负债总额（元）</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="debit_amount" name="debit_amount" value="${info.debit_amount }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 净利润（元） </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="net_amount" name="net_amount" value="${info.net_amount }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">所有者权益合计</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="profit_reta" name="profit_reta" value="${info.profit_reta }" class="col-xs-12">
						</div>
					</div>


				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 利润总额（元）</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="profit_total" name="profit_total" value="${info.profit_total }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 纳税总额（元）</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="tax_total" name="tax_total" value="${info.tax_total }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 资产总额（元）</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="total_equity" name="total_equity" value="${info.total_equity }" class="col-xs-12">
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
			url:base+"/gxcwbqytjj/save",
			data:$("#formid").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/gxcwbqytjj/list";
					});

				}else if(data=="error"){
					$("#formstate").val("Y");
					bootbox.alert("该年度的财务信息已存在，请勿重复录入！");
				} else{
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
					if ($divs.length > 0) {
						html = html
								+"<span>"
								+ "<input type='hidden' name='uploadfilename' id='uploadfilename' value='"+name+"'>"
								+ "<input type='hidden' name='uploadfileid' id='uploadfileid'  value='"+id+"'>"
								+ "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
								+ "<span> </span>"
								+ "<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>"
								+"</span>"
						$("#fujian_list div:last").after(html);
					} else {
						html = html
								+"<span>"
								+ "<input type='hidden' name='uploadfilename'  id='uploadfilename' value='"+name+"'>"
								+ "<input type='hidden' name='uploadfileid'   id='uploadfileid' value='"+id+"'>"
								+ "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
								+ "<span> </span>"
								+ "<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>"
								+"</span>"
						$("#fujian_list").append(html);
					}

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