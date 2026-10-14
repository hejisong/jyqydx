<!-- 遮挡层 -->
<div class="dialog" id="park_dialog" style="display:none;height:600px;width:650px;overflow-y: auto;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>选择园区</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closepark()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>



					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:462px;overflow-y:auto;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th width="10%;" style="text-align: center;"></th>
										<th width="90%;" style="text-align: center;">园区名称</th>


									</tr>
									</thead>
									<tbody id="resultOrg">
									[#list organinfos as org]
										<tr>
											<td style="text-align: center;">
												<input type="checkbox" value="${org.id}" [#if policydeclare.applyparkid??&&policydeclare.applyparkid?contains(org.id)] checked [/#if]>
											</td>
											<td style="text-align: center;">${org.orgname}</td>

										</tr>
									[/#list]
									</tbody>
								</table>
							</div>

							<div class="col-xs-12 control-label no-padding-right" style="text-align: center;">
								<button class="btn btn-xs btn-info btn-width"   type="button" onclick="chooseOrg();">确定</button>
								<button class="btn btn-xs btn-info btn-width"   type="button" onclick="closepark();">取消</button>
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
function openOrg() {
 	$("#fullbg").show();
  	$("#park_dialog").show();
}
//关闭用户弹出框
function closepark() {
	$("#fullbg,#park_dialog").hide();
}






function chooseOrg() {
	var orgs=$("#resultOrg").find("tr");
	var orgids="";
	var orgnames="";
	$.each(orgs, function(e,org){
		var checkbox=$(org).children("td:eq(0)").find("input");
		if($(checkbox)[0].checked==true){
			var orgid=$(checkbox).val();
			var orgname=$(org).children("td:eq(1)").text();
			orgids+=orgid+",";
			orgnames+=orgname+",";

		}

	})
	if(orgnames!=""){
		orgnames=orgnames.substr(0,orgnames.length-1);
	}

	$("#applyparkname").val(orgnames);
	$("#applyparkid").val(orgids);
	closepark();
}
</script>