<!-- 遮挡层 -->
<div class="dialog" id="tag_dialog" style="display:none;height:300px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #428bca;">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>标签信息</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closetag()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:350px;overflow-y:auto;">
								<table class="search-table-form">
									<tbody>
									     <tr>
											<td class="td-center" style="width:38%">标签名称</td>
											<td style="width:40%;">
												 <input type="hidden" name="tagid" id="tag_tagid" class="form-control">

												 <input name="tagname" id="tag_tagname" class="form-control formtd-width80">
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>

									</tbody>
								</table> 
								<div class="clearfix form-actions" style="text-align:center;">
									<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="savetag()">提 交</button>
				 					<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closetag()">取 消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//打开用户弹出框
function opentag(tagid,tagname) {
	$("#tag_tagid").val(tagid);
	$("#tag_tagname").val(tagname);
 	$("#fullbg").show();
  	$("#tag_dialog").show();
}
//关闭用户弹出框
function closetag() {
	cleartag();
	$("#fullbg,#tag_dialog").hide();
}
//清理表单信息
function cleartag() {
	$("#tagname").val("");
	$("#tagid").val("");

}


//保存用户信息
function savetag() {
	var tagid = $("#tag_tagid").val();
	var tagname = $("#tag_tagname").val();
	if (tagname == "" || tagname == null || tagname == undefined) {
		bootbox.alert("请输入标签名称！");
	} else {
		$.ajax({
			type: "post",
			url: base + "/policytag/savetag",
			data: {
				tagid: tagid,
				tagname: tagname
			},
			dataType: "text",
			success: function (data) {
				if (data == "SUCCESS") {
					bootbox.alert("保存成功！", function () {
						window.location.reload();
					});

				}  else {
					bootbox.alert("标签已存在，请重新输入！");
				}
			},
			error: function () {
				bootbox.alert("保存失败！");
			}
		});
	}

}



</script>