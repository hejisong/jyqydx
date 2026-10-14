[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/policy/policyfile.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js",
"assets/ueditor/ueditor.config.js","assets/ueditor/ueditor.all.js","assets/ueditor/lang/zh-cn/zh-cn.js","assets/js/common/webuploadernew/webuploader.js",
"assets/js/sys/organinfo/organtree.js","assets/js/sys/organinfo/ui-tree.js","assets/js/sys/organinfo/jstree.min.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css","assets/global/plugins/jstree/style.min.css"]]
[@layout.main sername=['政策管理','政策文件管理'] modelcode=["qyzc","policyfilemgr"] otherjs=jss othercss=css]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="policyfileform" action="${base}/policyfilemgr/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if policyfile.plfidid??]<input type="hidden" name="plfidid" value="${policyfile.plfidid }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策名称 <span style="color: red;">*</span> </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<input type="text" id="policyname" name="policyname" value="${policyfile.policyname }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发文单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fwunits" name="fwunits" value="${policyfile.fwunits }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 文号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fwtitanic" name="fwtitanic" value="${policyfile.fwtitanic }" class="col-xs-12">
						</div>
					</div>


				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 生效日期<span style="color: red;">*</span>  </label>
					<div class="col-xs-3">

						<div class="input-group date form_datetime ">
							<input class="form-control input-inline"  placeholder=""  name="releasetimestr" id="releasetime" data-date-format="yyyy-MM-dd" readonly
								   value="${policyfile.releasetime}" style="border-right: 0px; background-color: white;height: 34px;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>

					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 失效日期</label>
					<div class="col-xs-3">

						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" placeholder=""  name="invalidtimestr" id="invalidtime" data-date-format="yyyy-MM-dd" readonly
								   value="${policyfile.invalidtime}" style="border-right: 0px; background-color: white;height: 34px;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>

					</div>

				</div>


				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策级别</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<select class="form-control"  style="height: 34px;" name="filelevel" id="filelevel">
								<option value="1" [#if policyfile.filelevel=='1'] selected [/#if]>市级</option>
								<option value="2" [#if policyfile.filelevel=='2'] selected [/#if]>区级</option>
							</select>
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 维护单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right" style="width: 88%;float: left;">
							<input type="hidden" name="oporgid" id="oporgid" value="${policyfile.oporgid }">
							<input type="text" id="oporgname" name="oporgname" value="${policyfile.oporgname }" class="col-xs-12">
						</div>

						<div class="block input-icon input-icon-right" style="width: 12%;float: left;">
							<button  class="btn btn-sm btn-info" type="button" onclick="openOrg();">选择</button>
						</div>
					</div>


				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 附件上传</label>
					<div id="proofFile" class="btn btn-xs btn-success" style="margin-left:0px;margin-right:0px;">添加附件</div>
					<span id="fujian_list">
						[#if policyfile.uploadfileid??]
							[#list policyfile.uploadfileList as uploadfile]
								<span>
							<input type="hidden" name="uploadfilename" id="uploadfilename" value="${uploadfile.filename}">
						    <input type="hidden" name="uploadfileid" id="uploadfileid" value="${uploadfile.id}">
							<a  href='${base}/common/file/download/${uploadfile.id }'target='_blank'>${uploadfile.filename}</a>
							<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>
						</span>
							[/#list]
						[/#if]
					</span>


				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策内容<span style="color: red;">*</span> </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea style="display:none;" name="filecontent" id="filecontent">${policyfile.filecontent}</textarea>
							<textarea id="myEditor" name="myEditor"  style="height:420px;" readonly="readonly"></textarea>
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
	[#include "policyfilemgr/t_org.ftl"]
	<input type="hidden" id="formstate"  value="Y">
<script type="text/javascript">
	$(function(){
		FormValidation.init();
		initTime();
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
	



	function saveinfo() {



		$("#loading_state").val("1");
		$("#myEditor").hide();
		var content = myEditor.getContent();
		$("#filecontent").val(content);
		$.ajax({
			type:"post",
			url:base+"/policyfilemgr/save",
			data:$("#policyfileform").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/policyfilemgr/list";
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
			duplicate : true,
			// 只允许选择图片文件。
			accept : {
			// title : 'docs',
			extensions : 'pdf',
			// mimeTypes : 'image/*'
			}

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
				bootbox.alert('文件类型不正确！只能上传pdf类型文件！');
				return;
			}

		});
	}

	function deletefujianinfo(ele){
		$(ele).parent().remove();
	}


</script>
[/@layout.main]