<!-- 遮挡层 -->
<div class="dialog" id="park_dialog" style="display:none;height:600px;width:650px;overflow-y: auto;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>选择机构</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeorg()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>



				</div>



				<div class="col-md-12">

					<div class="portlet-body" style="background-color: #fff;">


						<div class="jstree" id="tree_1" role="tree"
							 aria-activedescendant="j1_1">
							<ul class="jstree-container-ul">
								[#list organs as organ]
									[#if  organ.superdepartment == '-1']
										<li class="jstree-node" role="treeitem" aria-selected="false">
											<i class="jstree-icon"></i> <a class="jstree-anchor"
																		   href="javascript:void(0)" onclick="toorgan('${organ.id}','${organ.orgname}')"> ${organ.orgname} </a>
											<ul class="jstree-children" role="group">
												[#list organs as organTwo] [#if  organTwo.superdepartment == organ.id]
													<li class="jstree-node" role="treeitem" aria-selected="false">
														<i class="jstree-icon"></i> <a class="jstree-anchor"
																					   href="javascript:void(0)" onclick="toorgan('${organTwo.id}','${organTwo.orgname}')"> ${organTwo.orgname} </a>
														<ul class="jstree-children" role="group">
															[#list organs as organThree]
																[#if  organThree.superdepartment == organTwo.id]
																	<li class="jstree-node" role="treeitem" aria-selected="false">
																		<i class="jstree-icon"></i> <a class="jstree-anchor"
																									   href="javascript:void(0)" onclick="toorgan('${organThree.id}','${organThree.orgname}')"> ${organThree.orgname} </a>
																		<ul class="jstree-children" role="group">
																			[#list organs as organFour]
																				[#if  organFour.superdepartment == organThree.id]
																					<li class="jstree-node" role="treeitem" aria-selected="false">
																						<i class="jstree-icon"></i> <a class="jstree-anchor"
																													   href="javascript:void(0)"
																													   onclick="toorgan('${organFour.id}','${organFour.orgname}')">
																							${organFour.orgname} </a>
																					</li> [/#if] [/#list]
																		</ul>
																	</li> [/#if] [/#list]
														</ul>
													</li> [/#if] [/#list]
											</ul>
										</li>

									[/#if]
								[/#list]
							</ul>
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
function closeorg() {
	$("#fullbg,#park_dialog").hide();
}



function toorgan(orgainid,organname) {
   $("#oporgname").val(organname);
	$("#oporgid").val(orgainid);
	closeorg();

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
	closeorg();
}
</script>