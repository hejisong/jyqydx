<!-- 遮挡层 -->
<div class="dialog" id="info_dialog" style="display:none;height:350px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #428bca;">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>标签信息</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeinfo()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<form id="scopeform">
							<div class="table-scrollable" style="height:350px;overflow-y:auto;">

								<table class="search-table-form">
									<tbody>
									     <tr>
											<td class="td-center" style="width:15%">经营范围</td>
											<td style="width:80%;">
												 <input type="hidden" name="id" id="info_id" class="form-control">

												<textarea name="businessscope" id="info_businessscope" class="form-control " rows="4"></textarea>
											</td>
											<td class="td-center" style="width:30%"></td>
										</tr>

										 <tr>
											 <td class="td-center" style="width:15%">标签</td>
											 <td style="width:80%;">

												 [#list tags as tag]
													 <input style="font-size: 15px;margin-left: 5px;" name="tag" id="info_tag" type="checkbox"  value="${tag.tagid}" >${tag.tagname}
												 [/#list]
											 </td>
											 <td class="td-center" style="width:30%"></td>
										 </tr>

									</tbody>
								</table> 
								<div class="clearfix form-actions" style="text-align:center;">
									<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="savescope()">提 交</button>
				 					<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeinfo()">取 消</button>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//打开用户弹出框
function addinfo() {
 	$("#fullbg").show();
  	$("#info_dialog").show();
}
//关闭用户弹出框
function closeinfo() {
	clearinfo();
	$("#fullbg,#info_dialog").hide();
}
//清理表单信息
function clearinfo() {
	$("#info_businessscope").val("");
	$("#infoid").val("");

}


//保存用户信息
function savescope() {
	var businessscope = $("#info_businessscope").val();
	var tag = $("#info_tag").val();
	if (businessscope == "" || businessscope == null || businessscope == undefined) {
		bootbox.alert("请输入标签名称！");
	} else {
		$.ajax({
			type: "post",
			url: base + "/companymgr/savescope",
			data:$("#scopeform").serialize(),
			dataType: "text",
			success: function (data) {
				if (data == "SUCCESS") {
					bootbox.alert("保存成功！", function () {
						window.location.reload();
					});

				}  else {
					bootbox.alert("经营范围已存在，请重新输入！");
				}
			},
			error: function () {
				bootbox.alert("保存失败！");
			}
		});
	}

}



</script>