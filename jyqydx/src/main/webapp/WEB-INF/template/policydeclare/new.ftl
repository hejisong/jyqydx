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
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 通知标题<span style="color: red;">*</span> </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<input type="text" id="declarename" name="declarename" value="${policydeclare.declarename }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发布单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="hidden" id="releaseorgid" name="releaseorgid" value="${policydeclare.releaseorgid }" readonly>
							<input type="text" id="releaseorgname" name="releaseorgname" value="${policydeclare.releaseorgname }" class="col-xs-12" >
							[#--<select class="form-control"  style="height: 34px;" name="releaseorgid" id="releaseorgid">
								[#list organinfos as org]
								<option value="${org.id}" [#if policydeclare.releaseorgid==org.id] selected [/#if]>${org.orgname}</option>
								[/#list]
							</select>--]
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发布日期 <span style="color: red;">*</span></label>
					<div class="col-xs-3">

						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="releasedate" id="releasedate" data-date-format="yyyy-MM-dd" readonly
								   value="${policydeclare.releasedate}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>

					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报开始时间 <span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="declare_start_date" id="declare_start_date" data-date-format="yyyy-MM-dd" readonly
								   value="${policydeclare.declare_start_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报结束时间 <span style="color: red;">*</span></label>
					<div class="col-xs-3">

						<div class="input-group date form_datetime ">
							<input class="form-control input-inline" style="height: 34px;"  placeholder=""  name="declare_end_date" id="declare_end_date" data-date-format="yyyy-MM-dd" readonly
								   value="${policydeclare.declare_end_date}" style="border-right: 0px; background-color: white;">

							<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
						</div>

					</div>
				</div>
				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 是否公开</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right" style="padding-top: 4px;">
							<input name="ispublic" type="radio" value="1" [#if policydeclare.ispublic!='0'] checked [/#if]>公开</input>
							<input name="ispublic" type="radio" value="0" [#if policydeclare.ispublic=='0'] checked [/#if]>不公开</input>
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 适用园区</label>
					<input type="hidden" id="applyparkid" name="applyparkid" value="${policydeclare.applyparkid }" class="col-xs-12">
					<div class="col-xs-4">
						<div class="block input-icon input-icon-right" style="width: 75%;float: left;">
							<input type="text" id="applyparkname" name="applyparkname" value="${policydeclare.applyparkname }" class="col-xs-12">

						</div>
						<div class="block input-icon input-icon-right" style="width: 20%;float: left;">
							<button  class="btn btn-sm btn-info" type="button" onclick="openOrg();">选择</button>
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报政策<span style="color: red;">*</span></label>
						<input type="hidden" id="policyfileid" name="policyfileid" value="${policydeclare.policyfileid }" class="col-xs-12">
						<div class="col-xs-8">
							<div class="block input-icon input-icon-right">
								<input type="text" id="policyfilename" name="policyfilename" readonly value="${policydeclare.policyfilename }" class="col-xs-12">
							</div>
						</div>
						<button class="btn btn-sm btn-info" type="button" onclick="openfile_dialog();">选择</button>


				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 附件上传</label>
					<div id="proofFile" class="btn btn-xs btn-success" style="margin-left:0px;margin-right:0px;">添加附件</div>
					<span id="fujian_list">
						[#if policydeclare.uploadfileid??]
							[#list policydeclare.uploadfileList as uploadfile]
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
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 通知内容<span style="color: red;">*</span></label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea style="display:none;" name="filecontent" id="filecontent">${policydeclare.filecontent}</textarea>
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



	function saveinfo() {
		var declare_start_date=$("#declare_start_date").val();
		var declare_end_date=$("#declare_end_date").val();
		if(declare_end_date<declare_start_date){
			bootbox.alert("申报结束时间不能早于申报开始时间！");
			return;
		}else{
			$("#loading_state").val("1");

			var content = myEditor.getContent();
			$("#filecontent").val(content);

			$.ajax({
				type:"post",
				url:base+"/policydeclare/save",
				data:$("#policydeclareformid").serialize(),
				async:true,
				success:function(data){

					if(data=="SUCCESS"){
						bootbox.alert("保存成功！", function() {
							window.location.href=base + "/policydeclare/list?state=1";
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