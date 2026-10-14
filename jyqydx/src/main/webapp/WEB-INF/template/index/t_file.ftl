<!-- 遮挡层 -->
<div class="dialog" id="file_dialog" style="display:none;height:800px;width:1000px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>政策文件</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closefile_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:700px;overflow-y:auto;">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
									<tr>
										<td style="width: 4%;"><img  style="padding-top: 10px;" class="wrapper_bg_img" src="${base}/assets/images/index/sq.png" alt=""></td>
										<td style="width: 46%;font-size: 13px;">政策文件名称</td>
										<td style="width: 10%;font-size: 13px;">拆解细则条数</td>
										<td style="width: 10%;font-size: 13px;">免申即享类</td>
										<td style="width: 10%;font-size: 13px;">补充兑现类</td>
										<td style="width: 10%;font-size: 13px;">提升兑现类</td>
										<td style="width: 10%;font-size: 13px;">条件兑现类</td>
									</tr>

									[#list policyfilesall as policyfile]
										<tr>
											<td><img  style="padding-top: 10px;" class="wrapper_bg_img" src="${base}/assets/images/index/sq.png" alt=""></td>
											<td style="font-size: 13px;">${policyfile.policyname}</td>
											<td style="font-size: 13px;">${policyfile.detailnum}</td>
											<td style="font-size: 13px;">${policyfile.withoutnum}</td>
											<td style="font-size: 13px;">${policyfile.replenishnum}</td>
											<td style="font-size: 13px;">${policyfile.improving}</td>
											<td style="font-size: 13px;">${policyfile.prednum}</td>
										</tr>

									[/#list]


								</table>
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
function openFile() {
 	$("#fullbg").show();
  	$("#file_dialog").show();
}
//关闭用户弹出框
function closefile_dialog() {
	$("#fullbg,#file_dialog").hide();
}



</script>