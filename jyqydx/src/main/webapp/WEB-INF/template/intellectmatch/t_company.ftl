<!-- 遮挡层 -->
<div class="dialog" id="corp_dialog" style="display:none;height:600px;width:85%;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>选择企业范围</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeCorp()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>





					<div class="col-xs-12">
						<form class="form-horizontal" role="form" id="corpscopeform" action="${base}/addedcompany/save" method="post">
							[#--<div class="form-group" >
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">企业标签</label>
								<div class="col-xs-9" style="font-size: 14px;padding-top: 4px;" >
									[#list tags as tag]
												<input  type="checkbox" name="corptag" value="${tag.tagid}" value1="${tag.tagname}" >${tag.tagname}
									[/#list]
								</div>

							</div>--]


							<div class="form-group" style="margin-bottom: 2px;">
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册资本（万元）</label>
								<div class="col-xs-1">
									<input type="text" id="t_minrecapital"  class="col-xs-12"  >
								</div>

								<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;width: 50px;">~</label>
								<div class="col-xs-1">
									<input type="text" id="t_maxrecapital"   class="col-xs-12"  >
								</div>

								<label class="col-xs-1 control-label no-padding-right" style="text-align: right;">成立日期</label>
								<div class="col-xs-2">
									<div class="input-group date form_datetime ">
										<input class="form-control input-inline"    id="t_startdate" data-date-format="yyyy-MM-dd" readonly style="border-right: 0px; background-color: white;height: 34px;">

										<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
									</div>
								</div>

								<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;width: 50px;">~</label>
								<div class="col-xs-2">
									<div class="input-group date form_datetime ">
										<input class="form-control input-inline"  id="t_enddate" data-date-format="yyyy-MM-dd" readonly style="border-right: 0px; background-color: white;height: 34px;">

										<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
									</div>
								</div>
							</div>



							<div class="form-group" style="margin-bottom: 2px;">
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">企业类型</label>
								<div class="col-xs-9" style="font-size: 14px;padding-top: 4px;" >
									[@dic_list] [#list dic_Companytype as dic]
										<input  type="checkbox" name="corptype" value="${dic.codedesc}" >${dic.codedesc}
									[/#list] [/@dic_list]
								</div>
							</div>

							<div class="form-group" style="margin-bottom: 2px;">
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">经营状态</label>
								<div class="col-xs-4" style="font-size: 14px;padding-top: 4px;" >
									[@dic_list] [#list dic_Operationstatus as dic]
										<input  type="checkbox" name="status" value="${dic.codedesc}" >${dic.codedesc}
									[/#list] [/@dic_list]
								</div>
								<label class="col-xs-1 control-label no-padding-right" style="text-align: right;">企业名称</label>
								<div class="col-xs-2" style="font-size: 14px;padding-top: 4px;" >
										<input  type="text" id="corp_name"  class="form-control">
								</div>
								<div class="col-xs-3 control-label no-padding-right" style="text-align: center;">
									<button class="btn btn-xs btn-info btn-width"   type="button" onclick="querycorp();">查询</button>
									<button class="btn btn-xs btn-info btn-width"   type="button" onclick="chooseCorp();">确定</button>
									<button class="btn btn-sm"   type="button" onclick="closeCorp();">取消</button>
								</div>
							</div>


						</form>

						<div class="portlet-body form" >
							<div style="padding: 10px 10px 0px 10px;">
								<div class="table-scrollable" style="height:375px;overflow-y:auto;width: 100%;">

									<table id="sample-table-project" class="table table-striped table-bordered table-hover">
										<thead>
										<tr>
											<th style="width:10%;"><input type="checkbox" name="companycheckAll" id="companycheckAll" onclick="companycheckAll();"></th>
											<th style="width:30%;">企业名称</th>
											<th style="width:22%;">统一社会信用代码</th>
											<th style="width:22%;">注册资本</th>
											<th style="width:16%;">经营状态</th>


										</tr>
										</thead>
										<tbody id="companycontent">


										</tbody>
									</table>
								</div>
								<p style="padding-top: 5px;">共<span id="corptotalsize">0</span>条记录</p>
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
function openCorp() {


 	$("#fullbg").show();
  	$("#corp_dialog").show();
}
//关闭用户弹出框
function closeCorp() {
	$("#t_minrecapital").val(null);
	$("#t_maxrecapital").val(null);
	$("#t_startdate").val(null);
	$("#t_enddate").val(null);
	var corptags=$("#corpscopeform").find("input[name='corptag']");
	var corptypes=$("#corpscopeform").find("input[name='corptype']");
	var statuss=$("#corpscopeform").find("input[name='status']");
	$.each(corptags,function(i,corptag){
		$(corptag)[0].checked=false;
	})
	$.each(corptypes,function(i,corptype){
		$(corptype)[0].checked=false;
	})
	$.each(statuss,function(i,status){
		$(status)[0].checked=false;
	})

	$("#fullbg,#corp_dialog").hide();
}


function querycorp() {
	choose();
	var corpname=$("#corp_name").val();
	$("#corpname").val(corpname);
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/querycorp" ,
		data:$("#corpform").serialize(),
		dataType:"json",
		async:true,
		success:function(data){
			$("#loading_state").val("0")
			initCorp(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});

}

function initCorp(corps) {
	$("#companycheckAll")[0].checked=false;
	var html="";
	var totalsize=0;
	$.each(corps,function(i,corp){
		totalsize=totalsize+1;
		var uscreditcode="";
		var recapital="";
		var operationstatus="";
		if(corp.uscreditcode!=null){
			uscreditcode=corp.uscreditcode;
		}
		if(corp.recapital!=null){
			recapital=corp.recapital;
		}
		if(corp.operationstatus!=null){
			operationstatus=corp.operationstatus;
		}
		html+='<tr>'
				+'<td style="text-align: center;"><input type="checkbox" value="'+corp.companyid+'" value1="'+corp.enterprisename+'" value2="'+uscreditcode+'" value3="'+recapital+'" value4="'+operationstatus+'"  name="companycheck" id="companycheck"  onclick="companycheck();"></td>'
				+'<td style="text-align: center;">'+corp.enterprisename+'</td>'
				+'<td style="text-align: center;">'+uscreditcode+'</td>'
				+'<td style="text-align: center;">'+recapital+'</td>'
				+'<td style="text-align: center;">'+operationstatus+'</td>'
				+'</tr>';


	});



	$("#companycontent").html(html);
	$("#corptotalsize").text(totalsize);
}




function choose() {

	var corptagids="";
	var corptaghtml="";
	var corptype="";
	var status="";
	var minrecapital=$("#t_minrecapital").val();
	var maxrecapital=$("#t_maxrecapital").val();
	var reg =  /^\d+(?=\.{0,1}\d+$|$)/;
	var flag=true;
	if(minrecapital!=null&&minrecapital!=""&&minrecapital!=undefined){
		if(!reg.test(minrecapital)){
			flag=false;
		}
	}
	if(flag){
		if(maxrecapital!=null&&maxrecapital!=""&&maxrecapital!=undefined){
			if(!reg.test(maxrecapital)){
				flag=false;
			}
		}
	}
	if(!flag){
		bootbox.alert('注册资本只能输入数字!');
		return;
	}

	if(minrecapital!=null&&minrecapital!=""&&maxrecapital!=null&&maxrecapital!=""&&minrecapital>maxrecapital){
		bootbox.alert('注册资本最小值不能大于最大值!');
		return;
	}

	var startdate=$("#t_startdate").val();
	var enddate=$("#t_enddate").val();
	if(startdate!=null&&startdate!=""&&enddate!=null&&enddate!=""&&startdate>enddate){
		bootbox.alert('成立日期最小值不能大于最大值!');
		return;
	}

	var corptags=$("#corpscopeform").find("input[name='corptag']");
	$.each(corptags,function(i,corptag){
		if($(corptag)[0].checked==true){
			corptagids+=$(corptag).val()+",";

		}
	})

	if(minrecapital!=null&&minrecapital!=""&&minrecapital!=undefined){
		$("#minrecapital").val(minrecapital);
	}
	if(maxrecapital!=null&&maxrecapital!=""&&maxrecapital!=undefined){
		$("#maxrecapital").val(maxrecapital);
	}
	if(startdate!=null&&startdate!=""){
		$("#startdate").val();
	}
	if(enddate!=null&&enddate!=""){
		$("#enddate").val();
	}

	var corptypes=$("#corpscopeform").find("input[name='corptype']");
	$.each(corptypes,function(i,c){
		if($(c)[0].checked==true){
			corptype+=$(c).val()+",";
		}
	})
	var statuss=$("#corpscopeform").find("input[name='status']");
	$.each(statuss,function(i,s){
		if($(s)[0].checked==true){
			status+=$(s).val()+",";
		}
	})

	$("#corpscope").html(corptaghtml);
	$("#corptagid").val(corptagids);
	$("#corptype").val(corptype);
	$("#status").val(status);

}


function chooseCorp() {
	var html="";
	var corphtml="";
	var companys=$("#companycontent").find("tr");
	var companyids="";
	var index=0;
	var corpselect=0;
	$.each(companys, function(e,company){
		var checkbox=$(company).children("td:eq(0)").find("input");
		if($(checkbox)[0].checked==true){
			corpselect=corpselect+1;
			var companyid=$(checkbox).val();
			var enterprisename=$(checkbox).attr("value1");
			var uscreditcode=$(checkbox).attr("value2");
			var recapital=$(checkbox).attr("value3");
			var operationstatus=$(checkbox).attr("value4");
			corphtml+='<input type="hidden" name="corpids"  value="'+companyid+'">';
			companyids+=companyid+",";
			html+='<tr>'
					+'<td style="text-align: center;">'+enterprisename+'</td>'
					+'<td style="text-align: center;">'+uscreditcode+'</td>'
					+'<td style="text-align: center;">'+recapital+'</td>'
					+'<td style="text-align: center;">'+operationstatus+'</td>'
					+'</tr>';
			index=index+1;

		}

	})
	if(companyids==""){
		bootbox.alert("请选择企业信息！");
	} else{
		$("#companyids").val(companyids);
		$("#corpcontent").html(html);
        // $("#msmatchform").append(corphtml);
	}


	$("#corpselect").html(corpselect);
	closeCorp();
}
</script>