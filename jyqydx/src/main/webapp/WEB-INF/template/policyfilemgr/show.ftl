[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js",
"assets/ueditor/ueditor.config.js","assets/ueditor/ueditor.all.js","assets/ueditor/lang/zh-cn/zh-cn.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['政策管理','政策文件管理'] modelcode=["qyzc","policyfilemgr"] otherjs=jss othercss=css]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="policyfileform" action="${base}/policyfilemgr/save" method="post">

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策名称  </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<input type="text" id="policyname" name="policyname" readonly value="${policyfile.policyname }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 发文单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fwunits" name="fwunits" readonly value="${policyfile.fwunits }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 文号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fwtitanic" name="fwtitanic" readonly value="${policyfile.fwtitanic }" class="col-xs-12">
						</div>
					</div>


				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 生效日期  </label>
					<div class="col-xs-3">

						<div class="block input-icon input-icon-right">
							<input type="text" id="releasetime" name="releasetime" readonly value="${policyfile.releasetime }" class="col-xs-12">
						</div>

					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 失效日期</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="invalidtime" name="invalidtime" readonly value="${policyfile.invalidtime }" class="col-xs-12">
						</div>

					</div>

				</div>


				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策级别</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="filelevel" name="filelevel" readonly value="[#if policyfile.filelevel=='1'] 市级[#else]区级 [/#if]" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 维护单位</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="oporgname" name="oporgname" readonly value="${policyfile.oporgname}" class="col-xs-12">
						</div>
					</div>

				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 附件上传</label>
						[#if policyfile.uploadfileid??]
							[#list policyfile.uploadfileList as uploadfile]
							<a  href='${base}/common/file/download/${uploadfile.id }'target='_blank'>${uploadfile.filename}</a>
							[/#list]
						[/#if]


				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策内容 </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea style="display:none;" name="filecontent" id="filecontent">${policyfile.filecontent}</textarea>
							<textarea id="myEditor" name="myEditor"  style="height:420px;" readonly="readonly"></textarea>
						</div>
					</div>
				</div>

				<div class="clearfix form-actions" style="text-align: center;">
					<button class="btn btn-sm btn-info" type="button" onclick="history.go(-1)">返 回</button>
				</div>
			</form>
		</div>
	</div>
	<input type="hidden" id="formstate"  value="Y">
<script type="text/javascript">
	var myEditor;
	$(function(){
		var content = "";
		if($("#filecontent")[0].childNodes[0]!=undefined){
			content = $("#filecontent")[0].childNodes[0].data;
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




	$(function(){

	});

</script>
[/@layout.main]