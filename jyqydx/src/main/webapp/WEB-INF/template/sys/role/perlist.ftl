[#assign jss=["assets/js/validate.js","assets/js/sys/organinfo/organtree.js"
,"assets/js/sys/organinfo/ui-tree.js","assets/js/sys/organinfo/jstree.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/global/plugins/jstree/style.min.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main modelcode=['sysmgr','rolemgr'] otherjs=jss othercss=css sername=['系统管理','角色管理']]
[#include "sys/usermgr/usertanchu.ftl"]
 [#include "common/loading.ftl"]
<div class="row">
	<div style="height:380px;width: 98%;overflow-y: auto;margin-left: 20px;">
		<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:auto;">
			<legend style="width: 200px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">(${role.rolename})角色人员列表</legend>
			<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
				<form class="form-horizontal"  >
					<input type="hidden" id="roleid" name="roleid" value="${roleid}">
					<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">人员姓名</label>
					<div class="col-xs-2">
						<input type="text" id="u_username" name="u_username" value="${u_username}" class="col-xs-12">
					</div>
					<div class="col-xs-3" >
						<button style="margin-left: 50px;" class="btn btn-xs btn-info btn-width" type="button" onclick="searchpersbyroles();">搜  索</button>
					</div>
				</form>
			</div>
			<div class="col-xs-12">
				<div class="table-responsive" style="overflow-y:auto;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th style="width:10%;text-align:center;">序号</th>
							<th style="width:20%;">姓名</th>
							<th style="width:30%;">登录名</th>
							<th style="width:30%;">所属单位</th>
						</tr>
						</thead>
						<tbody id="roleusercontent">
						</tbody>
					</table>
					<div class="form-group" style="margin-top: -15px;">
						<label class="col-xs-6 control-label no-padding-right" style="text-align: left;" id="roleusercountid">总共0条记录</label>
					</div>
				</div>

			</div>

		</fieldset>
	</div>
	<p style="text-align: center;font-size: 18px;font-weight: 600;"> ${role.rolename}角色</p>

	<div style="height:500px;width: 28%;overflow-y: auto;float: left;">
		<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:500px;">
		<legend style="width: 70px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">单位信息</legend>

			[#include "sys/usermgr/organtree.ftl"]
		</fieldset>
	</div>
	<div style="height:480px;width: 70%;overflow-y: auto;float: left;margin-left: 20px;">
		<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:480px;">
		<legend style="width: 70px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">人员信息</legend>
			<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
			<form class="form-horizontal"  action="${base}/sys/organinfo/orguser" method="get">

				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">人员姓名</label>
				<div class="col-xs-2">
					<input type="text" id="u_username" name="u_username" value="${u_username}" class="col-xs-12">
				</div>
				<div class="col-xs-3" >
					<button style="margin-left: 50px;" class="btn btn-xs btn-info btn-width" type="button" onclick="queryUser();">搜  索</button>
				</div>
			</form>


		</div>
		<div class="col-xs-12">
			<div class="table-responsive" style="height:320px;overflow-y:auto;">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:10%;text-align:center;"><input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"></th>
							<th style="width:45%;">姓名</th>
							<th style="width:45%;">登录名</th>
						</tr>
					</thead>
					<tbody id="orgusercontent">
					</tbody>
				</table>
				<div class="form-group" style="margin-top: -15px;">
					<label class="col-xs-6 control-label no-padding-right" style="text-align: left;" id="orgusercountid">总共0条记录</label>
				</div>
			</div>

		</div>

			<div  style="text-align: center;margin-bottom: 30px;">
				<button class="btn btn-sm btn-info" type="button" onclick="saveinfo();">提 交</button>
				<a class="btn btn-sm btn-info" href="${base}/xyjsmgr/sys/sysinfo/role">返 回</a>
			</div>
		</fieldset>
	</div>

</div>


<script type="text/javascript">
	$(function(){
		searchpersbyroles();
	});
var mgrOrgUsers = function(organid){
	$("#org_organid").val(organid);
	searchOrgUsers(organid);


}





function queryUser() {
	var username = $("#u_username").val();
	var orgname = $("#orgname").val();
	var url = base + "/sys/organinfo/organ/findusers" ;
	$.ajax({
		type:"get",
		url:url,
		data:{
			username:username,
			orgname:orgname
		},
		dataType:"json",
		success: function(data){
			var userid=data[0];
			var organid1=data[1];
			var organid2=data[2];
			var organid3=data[3];

            if(organid3!=null){
				$("#"+organid3).parent().find("i").click();
			}
			if(organid2!=null){
				$("#"+organid2).parent().find("i").click();
			}
			if(organid1!=null){
				$("#"+organid1).click();
			}


		},
		error:function(){
			bootbox.alert("数据加载失败！");
		}
	});
}

function initUser(data) {
	$("#orgusercontent").html("");
	var html = "";
	var bhtml = "";
	$("#orgusercountid").html("总共"+data.length+"条记录");
	$.each(data,function(i,person){
		bhtml = "<a href='javascript:void(0)'  value='"+person[0]+"'  onclick='editinfo(this);'>编辑</a>";
		html = html + "<tr name='"+person[0]+"' >";
        var ischecked=person[3];

		if(ischecked==1){
			html = html +"<td style='width:5%;'><input type='checkbox' name='check' id='check' checked value='"+person[0]+"'></td>";
		}else{
			html = html +"<td style='width:5%;'><input type='checkbox' name='check' id='check'  value='"+person[0]+"'></td>";
		}

		html = html +"<td style='width:10%;' id='"+person[0]+"'>"+person[1]+"</td>"
				+"<td style='width:11%;'>"+person[2]+"</td>";


	});
	$("#orgusercontent").html(html);
}



var searchOrgUsers = function(organid){
	//var organid = $("input[name='organid_param']").val();
	if(organid==""){
		bootbox.alert("请选择单位！");
		return;
	}
	var url = base + "/xyjsmgr/sys/sysinfo/role/findpers" ;
    var roleid=$("#roleid").val();

	$.ajax({
		type:"post",
		url:url,
		data:{
			organid:organid,
			roleid:roleid
		},
		dataType:"json",
		success: function(data){
			initUser(data);
		},
		error:function(){
			bootbox.alert("数据加载失败！");
		}
	});
}
//根据角色id查询所属人员
var searchpersbyroles = function(){
	var url = base + "/xyjsmgr/sys/sysinfo/role/findpersbyrole" ;
	var roleid=$("#roleid").val();
	var u_username=$("#u_username").val();
	$.ajax({
		type:"post",
		url:url,
		data:{
			roleid:roleid,
			u_username:u_username
		},
		dataType:"json",
		success: function(data){
			initUserbyrole(data);
		},
		error:function(){
			bootbox.alert("数据加载失败！");
		}
	});
}

function initUserbyrole(data) {
	$("#roleusercontent").html("");
	var html = "";
	$("#roleusercountid").html("总共"+data.length+"条记录");
	$.each(data,function(i,person){
		html = html + "<tr name='"+person[0]+"' >";
		html = html +"<td style='width:5%;'>"+(i+1)+"</td>";
		html = html +"<td style='width:10%;' id='"+person[0]+"'>"+person[1]+"</td>"
				+"<td style='width:11%;'>"+person[2]+"</td>"
				+"<td style='width:11%;'>"+person[5]+"</td></tr>";


	});
	$("#roleusercontent").html(html);
}



function checkAll(){
	var check=$("input[name='check']");
	for(i=0;i<check.length;i++){
		if($("#checkAll")[0].checked==false){
			check[i].checked=false;
		}else{
			check[i].checked=true;
		}
	}

}

function check(){
	var check=$("input[name='check']");
	for(i=0;i<check.length;i++){
		if(check[i].checked==false){
			$("#checkAll")[0].checked=false
		}
	}
}

function saveinfo() {
	var ids="";
	var roleid=$("#roleid").val();
	var check=$("input[name='check']");
	for(i=0;i<check.length;i++){
		if(check[i].checked==true){
			ids +=check[i].value+"-1"+",";
		}else{
			ids +=check[i].value+"-0"+",";
		}
	}
	bootbox.confirm("确认提交?",function (result) {
		if(result==true){
			$("#loading_state").val("1");
			$.ajax({
				type:"post",
				url:base + "/xyjsmgr/sys/sysinfo/role/saveperrole",
				async:true,
				data:{ids:ids,roleid:roleid},
				success:function(data){
					if(data=="SUCCESS"){
						bootbox.alert("提交成功！");
						window.location.reload();
					}else{
						bootbox.alert("提交失败！");
					}
				},
				error:function(){
					bootbox.alert("数据加载失败");
				}
			});

		}

	} )

}


</script>
[/@layout.main]