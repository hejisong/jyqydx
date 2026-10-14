[#assign jss=["assets/js/validate.js","assets/js/sys/organinfo/organtree.js"
,"assets/js/sys/organinfo/ui-tree.js","assets/js/sys/organinfo/jstree.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/global/plugins/jstree/style.min.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','用户管理'] modelcode=["sysmgr","usermgr"] otherjs=jss othercss=css]
[#include "sys/usermgr/usertanchu.ftl"]
 [#include "common/loading.ftl"] 
<div class="row">
	<div style="height:680px;width: 28%;overflow-y: auto;float: left;">
		<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:680px;">
		<legend style="width: 70px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">单位信息</legend>	

			[#include "sys/usermgr/organtree.ftl"]
		</fieldset>
	</div>
	<div style="height:680px;width: 70%;overflow-y: auto;float: left;margin-left: 20px;">
		<fieldset	style="border: 1px solid silver; padding: 5px; margin: 0px;height:680px;">
		<legend style="width: 70px;margin-bottom: 0px;font-size: 15px;border-bottom:0; color: blue;">人员信息</legend>
			<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
			<form class="form-horizontal"  action="${base}/sys/organinfo/orguser" method="get">
				[#--<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">机构名称</label>
				<div class="col-xs-2">
					<input type="text" id="orgname" name="orgname" value="${orgname}" class="col-xs-12">
				</div>--]


				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">人员姓名</label>
				<div class="col-xs-2">
					<input type="text" id="u_username" name="u_username" value="${u_username}" class="col-xs-12">
				</div>
				<div class="col-xs-3" >
					<button style="margin-left: 50px;" class="btn btn-xs btn-info btn-width" type="button" onclick="queryUser();">查 询</button>
				[#--	<a href="javascript:void(0)" class="btn btn-xs btn-info btn-width" onclick="addOrgUser()">新增人员</a>--]
				</div>
			</form>


		</div>
		<div class="col-xs-12">
			<div class="table-responsive" style="height:570px;overflow-y:auto;">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width:5%;text-align:center;">序号</th>
							<th style="width:6%;">姓名</th>
							<th style="width:10%;">登录名</th>
							<th style="width:15%;">身份证号</th>
							<th style="width:13%;">手机号码</th>
							<th style="width:15%;">联系邮箱</th>
							<th style="width:22%;">角色</th>
							<th style="width:14%;">操作</th>
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

		</fieldset>
	</div>
</div>
	<input id="finduserid" type="hidden" value="">
<script type="text/javascript">

var mgrOrgUsers = function(organid){
	$("#org_organid").val(organid);
	searchOrgUsers(organid);


}
var searchOrg = function(organid){
	//var infoid=$("#infoid").val();
	$.ajax({
        type: "get",
        url:base + "/sys/organinfo/toEdit/"+organid,
        data:{},
        dataType:"json",
        success: function(data) {
        	$("#orgname").val(data.orgname);
			$("#orgcode").val(data.orgcode);
        	$("#linkman").val(data.linkman);
        	$("#linkphone").val(data.linkphone);
        	$("#id").val(data.id);
        }
    });
}
 var deleteOrg=function(){
	 var organid = $("#org_organid").val();
	bootbox.confirm("确认删除?",function (result) {
		if(result==true){
			window.location.href=base + "/sys/organinfo/organ/"+organid+"/delete";
		}
		 
 })
}

var addOrg =function(){
	if(alertFormValidation_organ()==true){
		var orgname=$("#orgname").val();
		var linkman=$("#linkman").val();
	    var linkphone=$("#linkphone").val();
	    var linkemail=$("#linkemail").val();
		$.ajax({
	        type: "POST",
	        url:base +"/sys/organinfo/organ",
	        data:$('#form').serialize(),
	        dataType:"text",
	        error: function(request) {
	            bootbox.alert("保存失败");
	        },
	        success: function(data) {
	        	if(data=="SUCCESS"){
	        		bootbox.alert("保存成功", function() {
	        			window.location.href=base+"/sys/organinfo/orguser";
					});
	        		
	        	}else if(data=="FAIL"){
	        		bootbox.alert("保存失败!");
	        	}	           
	        }
	    });
	}
 	
}



var editOrg =function(){
	var organid = $("#id").val();
	var orgname=$("#orgname").val();
	var linkman=$("#linkman").val();
    var linkphone=$("#linkphone").val();
    var linkemail=$("#linkemail").val();
	 if(organid=="" || organid==null){
	    bootbox.alert("请选择要修改的单位");
	 }else{
			if(alertFormValidation_organ()==true){
				$.ajax({
			        type: "POST",
			        url:base +"/sys/organinfo/organ",
			        data:$('#form').serialize(),
			        dataType:"text",
			        error: function(request) {
			            bootbox.alert("保存失败");
			        },
			        success: function(data) {
			        	if(data=="SUCCESS"){
			        		bootbox.alert("保存成功", function() {
			        			window.location.href=base+"/sys/organinfo/orguser";
							});
			        		
			        	}else if(data=="FAIL"){
			        		bootbox.alert("保存失败!");
			        	}	           
			        }
			    });
			}
	}
 
}
var nextPage = function(ele){
	var p = $("#orgcontentid").attr("value");
	var ps = $("#orgpages").val();
	var pt = $("#orgtotals").val();
	var num = 0;
	if(p<ps){
		p = parseInt(p) + 1;
		$("tr[name^='tr_org']").hide();
		for(var i =1;i<21;i++){
			num = (p-1)*20+i;
			if(num<=pt){
				$("tr[name='tr_org"+num+"']").show();
			}
		}
	}
	if(p==ps){
		$(ele).attr("disabled","disabled");
	}
	$(ele).prev().removeAttr("disabled");
	var p = $("#orgcontentid").attr("value",p);
}
var backPage = function(ele){
	var p = $("#orgcontentid").attr("value");
	var ps = $("#orgpages").val();
	var pt = $("#orgtotals").val();
	if(p>1){
		p = parseInt(p) - 1;
		$("tr[name^='tr_org']").hide();
		for(var i =1;i<21;i++){
			$("tr[name='tr_org"+((p-1)*20+i)+"']").show();
		}
	}
	if(p==1){
		$(ele).attr("disabled","disabled");
	}
	$(ele).next().removeAttr("disabled");
	var p = $("#orgcontentid").attr("value",p);
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
            $("#finduserid").val(userid);

			//关闭所有机构数
			var lis=$("#tree_1").find("li[class='jstree-node jstree-node jstree-open']");
			$.each(lis,function(j,li){
               $(li).attr("class","jstree-node jstree-node jstree-closed");
				$(li).find("i:eq(0)").click();
			})




            if(organid3!=null){
				$("#"+organid3).parent().find("i:eq(0)").click();
			}
			if(organid2!=null){
				$("#"+organid2).parent().find("i:eq(0)").click();
			}

			// setTimeout("inituserinfo("+organid1+")", 1000);

			if(organid1!=null){
				$("#"+organid1).click();
			}

		/*	if(userid!=null&&userid!=""){
				setTimeout("inituserinfo("+userid+")", 1000);
			}*/

		},
		error:function(){
			bootbox.alert("数据加载失败！");
		}
	});
}


function inituserinfo(userid) {
	$("#"+userid).attr("style","color:green;");
}


function initUser(data) {
	$("#orgusercontent").html("");
	var html = "";
	var bhtml = "";
	var finduserid=$("#finduserid").val();

	$("#orgusercountid").html("总共"+data.length+"条记录");
	$.each(data,function(i,e){
		var stylehtml="";
		if(finduserid!=null&&finduserid!=''&&finduserid==data[i][0]){
			stylehtml="color:#3db73d;"
		}
		bhtml = "<a href='javascript:void(0)'  value='"+data[i][0]+"'  onclick='editinfo(this);'>编辑</a>";
		/*+ "<a href='javascript:void(0)' style='margin-left: 5px;color: #3fbf78;'  onclick='resetPassword(this);'>初始化密码</a>";*/

	/*	if(data[i][7]==0){
			/!* <a href='javascript:void(0)' onclick='edituser(this);'>编辑</a> *!/
			bhtml += "<a class='btn-red' style='margin-left: 5px;' href='javascript:void(0)' onclick='forbidden(this);'>注销</a>";
		}else{
			bhtml += "<a class='btn-red' style='margin-left: 5px;' href='javascript:void(0)' onclick='useable(this);'>启用</a>";
		}*/
		/*if(data[i][8]=='N'){
			/!* <a href='javascript:void(0)' onclick='edituser(this);'>编辑</a> *!/
			bhtml += "<a style='margin-left: 2px;' value='"+data[i][0]+"' value1='Y'  href='javascript:void(0)' onclick='unlock(this);'>解锁帐号</a>";
		}else{
			bhtml += "<a style='margin-left: 2px;' value='"+data[i][0]+"' value1='N'   href='javascript:void(0)' onclick='unlock(this);'>锁定帐号</a>";
		}*/




		html = html + "<tr  name='"+data[i][0]+"' >"
				+"<td  style='width:5%;'>"+(i+1)+"</td>"
				+"<td style='width:10%;"+stylehtml+"' id='"+data[i][0]+"'>"+data[i][1]+"</td>"
				+"<td style='width:11%;'>"+data[i][2]+"</td>";

		if(data[i][3]!=null){
			html = html +"<td style='width:13%;'>"+data[i][3]+"</td>";
		}else{
			html = html +"<td style='width:13%;'></td>";
		}
		if(data[i][4]!=null){
			html = html +"<td style='width:13%;'>"+data[i][4]+"</td>";
		}else{
			html = html +"<td style='width:13%;'></td>";
		}
		if(data[i][5]!=null){
			html = html +"<td style='width:13%;'>"+data[i][5]+"</td>";
		}else{
			html = html +"<td style='width:13%;'></td>";
		}
		html = html
				+"<td style='width:12%;'>"+data[i][6]+"</td>"
				+"<td style='width:12%;'>"+bhtml+"</td></tr>";
	});
	$("#orgusercontent").html(html);
}



var searchOrgUsers = function(organid){
	//var organid = $("input[name='organid_param']").val();
	if(organid==""){
		bootbox.alert("请选择单位！");
		return;
	}
	var url = base + "/sys/organinfo/organ/users/" + organid;
	var username = $("input[name='username_param']").val();
	var isvalid = "";
	 /*  if($("input[name='isvalid_param']")[0].checked){
		isvalid = "1";
	}   */
	$.ajax({
		type:"get",
		url:url,
		data:{
			username:username,
			isvalid:isvalid
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
function resetPassword(obj){	
	var userid=$(obj).parent().parent().attr("name");
	bootbox.confirm("确认初始化该用户密码?",function (result) {
		if(result==true){
			$.ajax({
				type:"get",
				url:base + "/sys/organinfo/organ/users/resetpassword/"+userid,
				dataType:"text",
				success: function(data){
					if(data="SUCCESS"){
						bootbox.alert("密码初始化成功！");
					}else{
						bootbox.alert("密码初始化失败！");
					}
				},
				error:function(){
					bootbox.alert("密码初始化失败！");
				}
			});
		}
		
 })
}


function forbidden(obj){
	var userid=$(obj).parent().parent().attr("name");
	var organid=$("#org_organid").val();
	bootbox.confirm("确认注销该用户！",function (result) {
		if(result==true){
			$.ajax({
				type:"get",
				url:base + "/sys/organinfo/organ/users/forbidden/"+userid+"/1",
				dataType:"text",
				success: function(data){
					if(data="SUCCESS"){
						searchOrgUsers(organid);
						bootbox.alert("注销成功！");
					}else{
						bootbox.alert("注销失败！");
					}
				},
				error:function(){
					bootbox.alert("注销失败！");
				}
			});
		}
		
	})
}
function useable(obj){
	var organid=$("#org_organid").val();
	var userid=$(obj).parent().parent().attr("name");
	bootbox.confirm("确认启用该用户！",function (result) {
		if(result==true){
			$.ajax({
				type:"get",
				url:base + "/sys/organinfo/organ/users/forbidden/"+userid+"/0",
				dataType:"text",
				success: function(data){
					if(data="SUCCESS"){
						searchOrgUsers(organid);
						bootbox.alert("启用成功！");
					}else{
						bootbox.alert("启用失败！");
					}
				},
				error:function(){
					bootbox.alert("启用失败！");
				}
			});
		}
		
	})
}
function edituser(obj){
	var organid = $("#org_organid").val();
	if(organid==""||organid==undefined){
		bootbox.alert("请先选择单位！");
		return;
	}
	var userid=$(obj).parent().parent().attr("name");
	var username = $(obj).parent().prev().prev().prev().prev().prev().prev().html();
	var loginname = $(obj).parent().prev().prev().prev().prev().prev().html();
	$("#org_userid").val(userid);
	$("#org_username").val(username);
	$("#org_loginname").val(loginname);
	openUser_dialog();
}
function addOrgUser(){
	var organid = $("#org_organid").val();
	if(organid==""||organid==undefined){
		bootbox.alert("请先选择单位！");
		return;
	}
	openUser_dialog();
}
function addOrganinfo(){
	openOrg_dialog();
}
function editOrganinfo(organid,organname,organaddr){
	$("#organ_id").val(organid);
	$("#organ_name").val(organname);
	$("#organ_addr").val(organaddr);
	openOrg_dialog();
}


function unlock(obj){
	var organid=$("#org_organid").val();
	var id=$(obj).attr("value");
	var isunlock=$(obj).attr("value1");
	var mess="";
	if(isunlock=='N'){
		mess="锁定";
	}else{
		mess="解锁";
	}
	// var organid=$("#id").val();

	bootbox.confirm("确认"+mess+"该用户信息?",function (result) {
		if(result==true){
			$.ajax({
				type:"post",
				url:base + "/sys/onlineuser/unlock/"+id+"/"+isunlock,
				async:true,
				success:function(data){
					if(data=="SUCCESS"){
						searchOrgUsers(organid);
						bootbox.alert("帐号"+mess+"成功！");
					}else{
						bootbox.alert("帐号"+mess+"失败！");
					}
				},
				error:function(){
					bootbox.alert("数据加载失败");
				}
			});
		}

	})

}
</script>
[/@layout.main]