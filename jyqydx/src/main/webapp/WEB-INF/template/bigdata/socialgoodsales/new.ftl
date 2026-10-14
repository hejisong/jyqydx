[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/bigdata/socialgoodsales.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['企业指标管理','社会消费品零售总额'] modelcode=["targetdata","socialgoodsales"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/socialgoodsales/save" method="post">
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
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 年度<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="year" name="year" value="${info.year }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">社会消费品零售总额（万元）<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="totall_sales" name="totall_sales" value="${info.totall_sales }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 社会消费品零售总额增幅<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="total_sales_increase" name="total_sales_increase" value="${info.total_sales_increase }" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 分组 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="grouping" name="grouping" value="${info.grouping }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">计量单位</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="measureunit" name="measureunit" value="${info.measureunit }" class="col-xs-12">
						</div>
					</div>


				</div>



				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 报告期</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="reporting_period" name="reporting_period" value="${info.reporting_period }" class="col-xs-12">
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
			url:base+"/socialgoodsales/save",
			data:$("#formid").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/socialgoodsales/list";
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