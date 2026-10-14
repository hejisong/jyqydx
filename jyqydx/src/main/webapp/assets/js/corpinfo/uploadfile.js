/**********************************************************************
 * 文件上传
 * *********************************************************************
 * js:
  "assets/js/common/webuploader/webuploader.js","assets/js/corpinfo/uploadfile.js"
 * *********************************************************************
 * 单文件上传：
 <script type="text/javascript">
	$(function() {
		onefileUpload();
	});
</script>
 * 多文件上传：
 <script type="text/javascript">
	$(function() {
		morefileUpload();
	});
</script>
*********************************************************************
 * html:
  <div class="form-group">
 <label class="control-label col-xs-1 form_padding"></label>
 <div id="proofFile" class="col-xs-1 form_padding" style="margin-left:0px;margin-right:0px;">添加附件</div>
 </div>
 <div class="form-group">
 <label class="control-label col-xs-1 form_padding">附件信息</label>
 <div class="col-xs-10 form_padding">
 		<table class="col-xs-1 form_padding table table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="sample_1_info" style="margin-top:0px;">
 		<tbody id="lawcontent2">
 			<tr style="height:35px;">
 				<td id="fujian_list"> 
   				</td>
 			</tr>
 		</tbody>
 	</table>
 </div>
 </div>
 * 
 * **********************************************************************/


function oneifhasfile(e1,e2){
	var id=e1;
	var name=e2;
	if(id!=null&&id!="" ){
		var html = "";
		html = html
			+ "<tr style='height:35px;'>"
			+ "<td style='width: 80%;'>"
				+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
				+ "<input type='hidden' id='fujianfileid' name='fujianfileid'  value='"+id+"'>"
				+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
				+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
				+ "<span> </span>"
				+ "<a style='color:red'  href='javascript:void(0)' onclick='onedeletefujian(this)'>[删除]</a>"
			+ "</td>"
			+ "</tr>"
		$("#fujian_list").append(html);
		$("#proofFile").hide();
	}
}
function onefileUpload() {
	var uploader = WebUploader.create({
		auto : true,
        swf: base + '/assets/js/common/webuploader/Uploader.swf',
		server : base + '/common/file',
		fileSingleSizeLimit : 20 * 1024 * 1024, // 5 M
		fileVal : "upfile",
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#proofFile',
		duplicate : true,
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
	uploader
			.on(
					'uploadSuccess',
					function(file, response) {
						var info = eval("(" + response._raw + ")");
						if (typeof (info['state']) != 'undefined') {
							if (info['state'] == 'SUCCESS') {
								var $divs = $("#fujian_list div");
								var name = info['name'];
								var size = info['fileSize'];
								var id = info['id'];
								var html = "";
									html = html
										+ "<tr style='height:35px;'>"
										+ "<td style='width: 80%;'>"
											+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
											+ "<input type='hidden' id='fujianfileid' name='fujianfileid'  value='"+id+"'>"
											+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
											+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
											+ "<span> </span>"
											+ "<a style='color:red'  href='javascript:void(0)' onclick='onedeletefujian(this)'>[删除]</a>"
										+ "</td>"					
										+ "</tr>"
									$("#fujian_list").append(html);
									$("#proofFile").hide();
								 
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

function onedeletefujian(ele){
	$(ele).parent().parent().remove();
	$("#proofFile").show();
}
 
 

function morefileUpload() {
	var uploader = WebUploader.create({
		auto : true,
        swf: base + '/assets/js/common/webuploader/Uploader.swf',
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
	uploader
			.on(
					'uploadSuccess',
					function(file, response) {
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
	 									+ "<tr style='height:35px;'>"
	 									+ "<td style='width: 80%;'>"
	 										+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
	 										+ "<input type='hidden' id='fujianfileids' name='fujianfileids'  value='"+id+"'>"
	 										+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
	 										+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
	 										+ "<span> </span>"
	 										+ "<a style='color:red' href='javascript:void(0)' onclick='moredeletefujian(this)'>[删除]</a>"
	 									+ "</td>"
	 									+ "</tr>"
									$("#fujian_list div:last").after(html);
								} else {
									html = html
										+ "<tr style='height:35px;'>"
										+ "<td style='width: 80%;'>"
											+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
											+ "<input type='hidden' id='fujianfileids' name='fujianfileids'  value='"+id+"'>"
											+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
											+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
											+ "<span> </span>"
											+ "<a style='color:red' href='javascript:void(0)' onclick='moredeletefujian(this)'>[删除]</a>"
										+ "</td>"						
										+ "</tr>"
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
function moredeletefujian(ele){
	 $(ele).parent().parent().remove();
 	
} 

//-----------------------------------------
function ifhasfile(e1,e2){
	var id=e1;
	var name=e2;
	if(id!=null&&id!="" ){
		var html = "";
		html = html
			+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
			+ "<input type='hidden' id='fujianfileid' name='fujianfileid'  value='"+id+"'>"
				+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
				+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
				+ "<span> </span>"
		$("#fujian_list").html(html);
	}
}

function fileUpload() {
	var uploader = WebUploader.create({
		auto : true,
        swf: base + '/assets/js/common/webuploader/Uploader.swf',
		server : base + '/common/file',
		fileSingleSizeLimit : 20 * 1024 * 1024, // 10 M
		fileVal : "upfile",
		pick : '#proofFile',
		duplicate : true
	});
	uploader
			.on(
					'uploadSuccess',
					function(file, response) {
						var info = eval("(" + response._raw + ")");
						if (typeof (info['state']) != 'undefined') {
							if (info['state'] == 'SUCCESS') {
								var $divs = $("#fujian_list");
								var name = info['name'];
								var size = info['fileSize'];
								var id = info['id'];
								var html = "";
									html = html
										+ "<input type='hidden' id='fujianfilename' name='fujianfilename' value='"+name+"'>"
										+ "<input type='hidden' id='fujianfileid' name='fujianfileid'  value='"+id+"'>"
											+ "<span><i class='fa fa-file-o'></i>" + name + "</span>"
											+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>[预览]</a>"
											+ "<span> </span>"
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




 