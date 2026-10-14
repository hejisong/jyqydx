[#assign jss=["assets/js/common/webuploader/webuploader.js","assets/ueditor/ueditor.config.js","assets/ueditor/ueditor.all.js"]]
[#import "common/layout.ftl" as layout] [@layout.main modelcode=['home']
otherjs=jss]
<table width="100%">
	<tr>
		<td><label class="control-label">公告内容</label></td>
	</tr>
	<tr>
		<td>
			<div class="form-group" style="margin-left:0px;margin-right:0px;">
				<div style="display:inline-block;width:100%"">
					<textarea style="display:none;" name="content" id="noticetext">${inspmessage.content}</textarea>
					<textarea id="myEditor" name="myEditor"  style="height:200px;" readonly="readonly"></textarea> 
				</div>
			</div>
		</td>
	</tr>
</table>
<div>
	<div id="proofFile" type="button" class="btn btn-xs btn-success">
		添加附件
	</div>
	<div id="fujian_list"></div>
</div>
<script type="text/javascript">
var myEditor;
$(function(){
	var content = "";
	if($("#noticetext")[0].childNodes[0]!=undefined){
		content = $("#noticetext")[0].childNodes[0].data;
	}
	var option = {
		initialContent:content, 
		//initialContent:"<p>aaa<strong>aa</strong></p>", 
		toolbars:[
            ['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
                'print', 'preview', 'searchreplace', 'help']
        ],
    	minFrameHeight:220
  	};
	myEditor = new baidu.editor.ui.Editor(option);
	myEditor.render("myEditor");
});
function setNoticetext(){
	var content = myEditor.getContent();
	var title = $("#noticetitle").val();
	if(title>49){
		bootbox.alert("公告标题太长，不能超过50个汉字，请重新输入！");
		reutrn;
	}
	if(content.length>999){
		bootbox.alert("公告内容太长，不能超过1000个汉字，请重新输入！");
		return;
	}
	$("#noticetext").val(myEditor.getContent());
	$("#noticeform").submit();
}

	$(function() {
		fileUpload();
	});
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
												+ "<div style='display: inline-block;line-height:30px;'>"
												+ "<input type='checkbox' name='newfileids' checked='checked' style='display:none' value='"+id+"'>"
												+ "<span><i class='fa fa-file-o'></i>"
												+ ""
												+ name
												+ "</span>"
												+ "<a href='"+base+"/common/"+id+"'target='_blank'>预览</a>"
												+ "<span> </span>"
												+ "<a href='javascript:void(0)' onclick='deletefujian(this)'>删除</a>"
												+ "</div>";
										$("#fujian_list div:last").after(html);
									} else {
										html = html
												+ "<div style='display: inline-block;line-height:30px;'>"
												+ "<input type='checkbox' name='newfileids'  checked='checked' style='display:none' display:none value='"+id+"'>"
												+ "<span><i class='fa fa-file-o'></i>"
												+ ""
												+ name
												+ "</span>"
												+ "<a href='"+base+"/common/"+id+"'target='_blank'>预览</a>"
												+ "<span> </span>"
												+ "<a href='javascript:void(0)' onclick='deletefujian(this)'>删除</a>"
												+ "</div>";
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
	function deletefujian(ele){
		var id = $(ele).parent().find("input").val();
		$.ajax({
			type:"get",
			url:base+"/common/delete/"+id,
			dataType:"text",
			async:false,
			success:function(data){
				if(data=="OK"){
					$(ele).parent().remove();
					bootbox.alert("删除成功！");
					
				}else{
					bootbox.alert("删除失败！");
				}
			},
			error:function(){
				bootbox.alert("删除失败！");
			}
		});
	}
</script>
[/@layout.main]
