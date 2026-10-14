<!-- 遮挡层 -->
<div class="dialog" id="result_dialog" style="display:none;height:600px;width:85%;overflow-y: auto;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>匹配结果详情</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeResult()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>



					<div class="portlet-body form">
						<p id="rtitle" style="margin-top: 10px;font-size: 18px;text-align: center;"></p>
						<form class="form-horizontal" id="tagform"  >
							<div style="padding: 0px 10px 0px 10px;">
								<div class="table-scrollable" style="height:450px;overflow-y:auto;width: 100%;">
									<table id="sample-table-project" class="table table-striped table-bordered table-hover">
										<thead>
										<tr>
											<th style="width:5%;">序号</th>
											<th style="width:20%;">企业名称</th>
											<th style="width:10%;">匹配政策数量</th>
											<th style="width:20%;">政策类型</th>
											<th style="width:35%;">政策条件</th>




										</tr>
										</thead>
										<tbody id="detailtcontent">

										</tbody>
									</table>
								</div>



							</div>
						</form>
					</div>
				</div>

				<div class="col-xs-12 control-label no-padding-right" style="text-align: center;margin-top: 15px;">
					<button class="btn btn-xs btn-info btn-width"   type="button" onclick="closeResult();">关 闭</button>
				</div>

				</div>






			</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//打开用户弹出框
function openResult() {


 	$("#fullbg").show();
  	$("#result_dialog").show();
}
//关闭用户弹出框
function closeResult() {


	$("#fullbg,#result_dialog").hide();
}







</script>