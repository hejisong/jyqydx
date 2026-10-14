<!-- 遮挡层 -->
<div class="dialog" id="corp_dialog" style="display:none;height:400px;width:50%;overflow-y: auto;">
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
								<div class="col-xs-8" style="font-size: 14px;padding-top: 4px;" >
									[#list tags as tag]
												<input  type="checkbox" name="corptag" value="${tag.tagid}" value1="${tag.tagname}" >${tag.tagname}
									[/#list]
								</div>

							</div>--]


							<div class="form-group" >
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">注册资本（万元）</label>
								<div class="col-xs-3">
									<input type="text" id="t_minrecapital"  class="col-xs-12"  >
								</div>

								<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">~</label>
								<div class="col-xs-3">
									<input type="text" id="t_maxrecapital"   class="col-xs-12"  >
								</div>
							</div>

							<div class="form-group" >
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">成立日期</label>
								<div class="col-xs-3">
									<div class="input-group date form_datetime ">
										<input class="form-control input-inline"    id="t_startdate" data-date-format="yyyy-MM-dd" readonly style="border-right: 0px; background-color: white;height: 34px;">

										<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
									</div>
								</div>

								<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">~</label>
								<div class="col-xs-3">
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

							<div class="form-group" >
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">企业类型</label>
								<div class="col-xs-8" style="font-size: 14px;padding-top: 4px;" >
									[@dic_list] [#list dic_Companytype as dic]
										<input  type="checkbox" name="corptype" value="${dic.codedesc}" >${dic.codedesc}
									[/#list] [/@dic_list]
								</div>
							</div>

							<div class="form-group" >
								<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">经营状态</label>
								<div class="col-xs-8" style="font-size: 14px;padding-top: 4px;" >
									[@dic_list] [#list dic_Operationstatus as dic]
										<input  type="checkbox" name="status" value="${dic.codedesc}" >${dic.codedesc}
									[/#list] [/@dic_list]

								[#--	[#list status as s]
										<input  type="checkbox" name="status" value="${s.codedesc}" >${s.codedesc}
									[/#list]--]
								</div>
							</div>
							<div class="col-xs-12 control-label no-padding-right" style="text-align: center;margin-top: 15px;">
								<button class="btn btn-xs btn-info btn-width"   type="button" onclick="chooseCorp();">确定</button>
								<button class="btn btn-sm"    type="button" onclick="closeCorp();">取消</button>
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






function chooseCorp() {

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

	if(minrecapital!=null&&minrecapital!=""&&maxrecapital!=null&&maxrecapital!=""){
		var min=parseInt(minrecapital);
		var max=parseInt(maxrecapital);
		if(min>max){
			bootbox.alert('注册资本最小值不能大于最大值!');
			return;
		}

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
			corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">'+$(corptag).attr("value1")+'</span>';
		}
	})

	if(minrecapital!=null&&minrecapital!=""&&minrecapital!=undefined){
		corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">注册资本大于'+minrecapital+'万元</span>';
		$("#minrecapital").val(minrecapital);
	}
	if(maxrecapital!=null&&maxrecapital!=""&&maxrecapital!=undefined){
		corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">注册资本小于'+maxrecapital+'万元</span>';
		$("#maxrecapital").val(maxrecapital);
	}
	if(startdate!=null&&startdate!=""){
		corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">成立日期大于'+startdate+'</span>';
		$("#startdate").val();
	}
	if(enddate!=null&&enddate!=""){
		corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">成立日期小于'+enddate+'</span>';
		$("#enddate").val();
	}

	var corptypes=$("#corpscopeform").find("input[name='corptype']");
	$.each(corptypes,function(i,c){
		if($(c)[0].checked==true){
			corptype+=$(c).val()+",";
			corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">'+$(c).val()+'</span>';
		}
	})
	var statuss=$("#corpscopeform").find("input[name='status']");
	$.each(statuss,function(i,s){
		if($(s)[0].checked==true){
			status+=$(s).val()+",";
			corptaghtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">'+$(s).val()+'</span>';
		}
	})

	$("#corpscope").html(corptaghtml);
	$("#corptagid").val(corptagids);
	$("#corptype").val(corptype);
	$("#status").val(status);
	closeCorp();
}
</script>