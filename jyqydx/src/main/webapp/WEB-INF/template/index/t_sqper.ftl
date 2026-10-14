<div id="fullbg" style="width:100%;height:100%;display:none;"></div>
<!-- 遮挡层 -->
<div class="dialog" id="sqper_dialog" style="display:none;height:400px;width:800px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>选择授权人</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closesqper()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>



					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">

									<label class="col-xs-2 control-label no-padding-right" style="text-align:right;">姓名</label>
									<div class="col-xs-3">
										<input  id="personname" class="col-xs-10" type="text"  >

									</div>



									<div class="col-xs-2 control-label no-padding-right" style="text-align: left;">

										<button class="btn btn-xs btn-info btn-width"   type="button" onclick="querySqper();">搜 索</button>
									</div>

								</div>
							</div>
						</div>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:210px;overflow-y:auto;width: 100%;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th width="10%;" style="text-align: center;"></th>
										<th width="30%;" style="text-align: center;">姓名</th>
										<th width="60%;" style="text-align: center;">身份证号</th>

									</tr>
									</thead>
									<tbody id="sqpercontent">

									</tbody>
								</table>
							</div>


							<div  style="text-align: center;">
								<button class="btn btn-xs btn-info btn-width"  type="button" onclick="saveauthor();">授 权</button>
								<button class="btn btn-sm"  type="button" onclick="closesqper();">取 消</button>
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
function opensqper() {

 	$("#fullbg").show();
  	$("#sqper_dialog").show();
}
//关闭用户弹出框
function closesqper() {
	$("#fullbg,#sqper_dialog").hide();
}

//查询企业用户信息
function querySqper(){
	var personname=$("#personname").val();
	$.ajax({
		url : base + "/account/authorizedrecord/querySqper",
		type : "POST",
		async : false,
		datatype : 'json',
		data : {personname:personname},
		success : function(data) {
			var html="";
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				html+='<tr>';

				if(i==0){
					html+='<td style="text-align: center;"><input type="radio" name="authorizedperson" checked value="'+obj[0]+'"  value1="'+obj[2]+'"></td>';
				}else{
					html+='<td style="text-align: center;"><input type="radio"  value="'+obj[0]+'"  value1="'+obj[1]+'"></td>';
				}

				html+= '<td style="text-align: center;">'+obj[0]+'</td>'
						+'<td style="text-align: center;">'+obj[1]+'</td></tr>';
			}

			$("#sqpercontent").html(html);
		},
		error : function() {
			bootbox.alert("查询人员失败！");
		}
	});
}

//保存授权
function saveauthor() {

	var authorizedperson= $("input[name='authorizedperson']:checked").val();
	var pidNumber= $("input[name='authorizedperson']:checked").attr("value1");

	if(authorizedperson==""||authorizedperson=="undefined"){
		bootbox.alert("请选择授权人");
		return;
	}

	var companyid=$("#companyid").val();
	if(companyid==""||companyid=="undefined"){
		bootbox.alert("请先绑定企业");
		return;
	}

	$.ajax({
		url : base + "/account/authorizedrecord/saveauthinfo",
		type : "POST",
		async : false,
		datatype : 'json',
		data : {authorizedperson:authorizedperson,pidNumber:pidNumber,companyid:companyid},
		success : function(data) {
			if(data[0]=="0"){
				bootbox.alert("保存失败");
			}else if(data[0]=="3"){
				bootbox.alert("该人员已授权，请勿重复授权！");
			}
			else{
				bootbox.alert("保存成功")
				window.location.reload();
			}
		},
		error : function() {
			bootbox.alert("保存失败")
		}
	});
}




</script>