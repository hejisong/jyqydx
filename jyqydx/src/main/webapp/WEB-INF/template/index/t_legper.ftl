<div id="fullbg" style="width:100%;height:100%;display:none;"></div>
<!-- 遮挡层 -->
<div class="dialog" id="file_dialog" style="display:none;height:400px;width:1000px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>非法人授权</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closelegper()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<label class="col-xs-12 control-label no-padding-right" style="text-align:center;font-size: 16px;color: red;">非法人进行授权或者取消授权操作，需先由法人进行手机验证码验证，操作人需向法人获取验证码进行授权或取消授权操作！</label>



								</div>
							</div>
						</div>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<table class="search-table-form" id="companys">
								<input type="hidden" id="au_id">
								<input type="hidden" id="au_state">
								<tbody >
								<tr style="margin-top:20px;">
									<td class="td-center" style="width:25%;text-align: right;font-size: 16px" id="ssqq">授权人</td>
									<td class="td-center" style="width:25%;text-align: center">
										<input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #b1b2b7;" readonly id="sq_username" >
									</td>
									<td class="td-center" style="width:25%;text-align: left">

									</td>
									<td class="td-center" style="width:10%;text-align: center"></td>
								</tr>
								<tr style="margin-top:20px;">
									<td class="td-center" style="width:25%;text-align: right;font-size: 16px">法人手机号码</td>
									<td class="td-center" style="width:25%;text-align: center">
										<input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #b1b2b7;" placeholder="请输入企业法人手机号码" id="t_comphone" value="${company.phoneno}">
									</td>
									<td class="td-center" style="width:25%;text-align: left">
										<button style="margin-top:5px;height:35px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px"  class="feachBtn">获取验证码</button>
									</td>
									<td class="td-center" style="width:10%;text-align: center"></td>
								</tr>
								<tr style="margin-top:20px;">
									<td class="td-center" style="width:25%;text-align: right;font-size: 16px">短信验证码</td>
									<td class="td-center" style="width:25%;text-align: center">
										<input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #b1b2b7;" placeholder="请输入短信验证码" id="t_commess">
									</td>
									<td class="td-center" style="width:25%;text-align: left">
									</td>
									<td class="td-center" style="width:10%;text-align: center"></td>
								</tr>
								</tbody>
							</table>

							<div class="clearfix form-actions" style="text-align:center;width: 880px">
								<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="setAu()"><span id="shouquan">授 权</span></button>


								<button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closelegper()">取 消</button>

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
function openlegper() {
	findper();
}

//关闭用户弹出框
function closelegper() {
	$("#fullbg,#file_dialog").hide();
}

function findper() {
	$.ajax({
		url : base + "/account/authorizedrecord/querydqSqper",
		type : "POST",
		async : false,
		datatype : 'json',
		data : {},
		success : function(data) {
			$("#sq_username").val(data[0]);
			$("#fullbg").show();
			$("#file_dialog").show();
		},
		error : function() {
			bootbox.alert("查询人员失败！");
		}
	});
}






// 点击获取验证码操作
$('.feachBtn').click(function () {
	var phoneNumber = $("#t_comphone").val().trim();
	if (phoneNumber == '') {
		bootbox.alert('手机号不能为空');
		return false;
	} else {
		// var count = 60;
		// for(var i=0;i<61;i++){
		//     setTimeout(countcheck(count), 1000);
		//      count--;
		//
		// }
		// $('.feachBtn').attr('disabled', true);
		$('.feachBtn').css({
			background: '#d8d8d8',
			color: '#707070',
		});
		$('.feachBtn').text('重新获取');
		sendPhoneMessage();
	}
});


//发送验证码
function sendPhoneMessage(){
	var comphone=$("#t_comphone").val();
	var key = $("#pwdsalt").val();
	//加密方法
	var comphoneDes = strEnc(comphone, key);

	if(comphone!=""&&comphone!=undefined&&comphone!="null"){
		$.ajax({
			url : base + "/interface/wdnj/pcsendPhoneMessage",
			type : "GET",
			async : false,
			datatype : 'json',
			data : {phone:comphoneDes},
			success : function(data) {
				if(data.result){
					bootbox.alert(data.errorMsg);
				}else{
					bootbox.alert(data.errorMsg);
				}
			},
			error : function() {
				bootbox.alert("验证码发送失败！");
			}
		});
	}else{
		bootbox.alert("法人手机号不能为空")
	}
}






function setAu() {
	var companyid=$("#companyid").val();
	if(companyid==""||companyid=="undefined"){
		bootbox.alert("请先绑定企业");
		return;
	}
	var phone=$("#t_comphone").val();
	var mess=$("#t_commess").val()
	var authorizedid=$("#au_id").val();
	var state=$("#au_state").val();
	if(phone==""||phone==undefined||phone=="null"){
		bootbox.alert("手机号不能为空！");
		return;
	}
	if(mess==""||mess==undefined||mess=="null"){
		bootbox.alert("请输入验证码！");
		return;
	}
	var message="取消授权";
	if(state=="1"){
		message="授权";
	}
	$.ajax({
		url : base + "/account/authorizedrecord/legupstate",
		type : "GET",
		async : false,
		datatype : 'json',
		data : {phone:phone,mess:mess,authorizedid:authorizedid,state:state,companyid:companyid},
		success : function(data) {
			if(data.result){
				bootbox.confirm(message+"成功！",function (result) {
					window.location.reload();
				})

			}else{
				bootbox.alert(data.errorMsg);
			}
		},
		error : function() {
			bootbox.alert("数据加载失败！");
		}
	});
}







</script>