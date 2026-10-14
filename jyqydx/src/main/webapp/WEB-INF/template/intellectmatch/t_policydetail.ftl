<!-- 遮挡层 -->
<div class="dialog" id="file_dialog" style="display:none;height:600px;width:85%;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>政策文件</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closefile()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策文件</label>
									<div class="col-xs-4" style="margin-bottom: 20px;">
										<select class="form-control input-inline" name="plfidid" id="plfidid"   style="height: 34px;padding: 2px" >
											<option value="" [#if plfidid==''] selected [/#if]>全部</option>
											[#list policyfiles as policyfile]
												<option value="${policyfile[0]}" >${policyfile[1]}</option>
											[/#list]
										</select>
									</div>

									<div class="col-xs-3 control-label no-padding-right" style="text-align: center;">
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="queryfile();">查 询</button>
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="chooseinfo();">确定</button>
										<button class="btn btn-sm"  type="button" onclick="closefile();">取 消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="portlet-body form" >
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:450px;overflow-y:auto;width: 100%;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover" >
									<thead>
									<tr>
										<th style="width:5%;"><input type="checkbox" name="policycheckAll" id="policycheckAll" onclick="policycheckAll();"></th>
										<th style="width:25%;">政策文件</th>
										<th style="width:20%;">政策类型</th>
										<th style="width:35%;">政策条件</th>
										<th style="width:35%;">政策奖励</th>


									</tr>
									</thead>
									<tbody id="policycontent" >

									[#list details as detail ]
										<tr >
											<td><input type="checkbox" name="policycheck" id="policycheck" value="${detail.pldtailid}" value1="${detail.policyname}" value2="${detail.plfidid}"  onclick="policycheck();"></td>
											<td>${detail.policyname}</td>
											<td>${detail.nplcategory}[#if detail.plsubclassname??&&detail.plsubclassname!=''](${detail.plsubclassname})[/#if]</td>

											<td>${detail.plconditions}</td>
											<td>${detail.rdescription}</td>
										</tr>
									[/#list]
									</tbody>
								</table>

							</div>

							<p style="padding-top: 5px;">共<span id="filetotalsize">${details?size}</span>条记录</p>

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
function openfile() {

 	$("#fullbg").show();
  	$("#file_dialog").show();
}
//关闭用户弹出框
function closefile() {
	$("#fullbg,#file_dialog").hide();
}



function queryfile() {
	var plfidid=$("#plfidid").val();
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/queryfile" ,
		data:{plfidid:plfidid},
		dataType:"json",
		async:true,
		success:function(data){
			$("#loading_state").val("0")
			initDetail(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});
}


function initDetail(details) {
	$("#policycheckAll")[0].checked=false;
	var html="";
	$("#filetotalsize").text(details.length);
	$.each(details,function(i,detail){
		var nplcategory="";
		var plconditions="";
		var rdescription="";
		if(detail.nplcategory!=null){
			nplcategory=detail.nplcategory;
		}
		if(detail.plsubclassname!=null&&detail.plsubclassname!=''){
			nplcategory=nplcategory+"("+detail.plsubclassname+")";
		}
		if(detail.plconditions!=null){
			plconditions=detail.plconditions;
		}
		if(detail.rdescription!=null){
			rdescription=detail.rdescription;
		}
		html+='<tr>'
				+'<td style="text-align: center;"><input type="checkbox" name="policycheck" id="policycheck"  onclick="policycheck();" value="'+detail.pldtailid+'" value1="'+detail.policyname+'" value2="'+detail.plfidid+'"></td>'
				+'<td style="text-align: center;">'+detail.policyname+'</td>'
				+'<td style="text-align: center;">'+nplcategory+'</td>'
				+'<td style="text-align: center;">'+plconditions+'</td>'
				+'<td style="text-align: center;">'+rdescription+'</td>'
				+'</tr>';


	});



	$("#policycontent").html(html);
}




function chooseinfo() {
	var files=$("#policycontent").find("tr");
	var pldtailids="";
	var policynames="";
	var plfidids="";
	$.each(files, function(e,star){
		var checkbox=$(star).children("td:eq(0)").find("input");
		if($(checkbox)[0].checked==true){
			var pldtailid=$(checkbox).val();
			var policyname=$(checkbox).attr("value1");
			var plfidid=$(checkbox).attr("value2");
			pldtailids+=pldtailid+",";
			if(policynames.indexOf(policyname)==-1){
				policynames+=policyname+",";
			}
			if(plfidids.indexOf(plfidid)==-1){
				plfidids+=plfidid+",";
			}


		}

	})
	if(policynames!=""){
		policynames=policynames.substr(0,policynames.length-1);
	}else{
		bootbox.alert("请选择政策！")
		return;
	}

	$("#policyfilename").html(policynames);
	$("#pldtailid").val(pldtailids);
	$("#policyfileid").val(plfidids);
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/querytag" ,
		data:{pldtailids:pldtailids},
		dataType:"json",
		success:function(data){
			inittag(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});

	closefile();
}

function inittag(tags) {
	var html="";
	var tagids="";
	$.each(tags, function(e,tag){

		html+='<span style="padding-right: 12px;">'+tag[1]+'</span>';
		tagids+=tag[0]+",";

	})

	$("#tagname").html(html);
	$("#tagid").val(tagids);
}




function policycheckAll(){
	var check=$("input[name='policycheck']");
	for(i=0;i<check.length;i++){
		if($("#policycheckAll")[0].checked==false){
			check[i].checked=false;
		}else{
			check[i].checked=true;
		}
	}

}

function policycheck(){
	var check=$("input[name='policycheck']");
	for(i=0;i<check.length;i++){
		if(check[i].checked==false){
			$("#policycheckAll")[0].checked=false
		}
	}
}


</script>