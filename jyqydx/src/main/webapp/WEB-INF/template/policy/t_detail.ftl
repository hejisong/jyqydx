<div id="fullbg" style="width:100%;height:100%;display:none;"></div>
<!-- 遮挡层 -->
<div class="dialog" id="file_dialog" style="display:none;height:650px;width:1000px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>政策文件</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closefile_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<label class="col-xs-2 control-label no-padding-right" style="text-align:right;">政策大类名称</label>
									<div class="col-xs-5">
										<input  id="nplcategory" class="col-xs-10" type="text"  >
									</div>

									<div class="col-xs-2 control-label no-padding-right" style="text-align: center;">
										<input type="hidden" id="f_plfidid" >
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="querydetail(0);">查 询</button>
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="closefile_dialog();">关 闭</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:500px;overflow-y:auto;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th style="width:30%;">政策类别</th>
										<th style="width:35%;">政策条件</th>
										<th style="width:25%;">政策奖励</th>
										<th style="width:10%;">操作</th>


									</tr>
									</thead>
									<tbody id="policyresultcontent">

									</tbody>
								</table>
							</div>
							<div style=" height: 33px;"id="resultpagecontent">
								<table style="width:100%;">
									<tr>
										<td width="80%">第0页/共0页，总共0条记录</td>
										<td width="20%" style="text-align: right;">
											<button class="btn btn-xs btn-default" type="button"data-toggle="button" disabled="disabled">上一页</button>
											<button class="btn btn-xs btn-default" type="button"data-toggle="button" style="margin-left: 5px;"disabled="disabled">下一页</button>
										</td>
									</tr>
								</table>
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
function opendetail(plfidid) {
	$("#f_plfidid").val(plfidid);
	querydetail(0)
 	$("#fullbg").show();
  	$("#file_dialog").show();
}
//关闭用户弹出框
function closefile_dialog() {
	$("#nplcategory").val("");
	$("#fullbg,#file_dialog").hide();
}




function querydetail(page) {
	var key=$("#key").val();
	var plfidid=$("#f_plfidid").val();
	var nplcategory=$("#nplcategory").val();
	var companyid=$("#companyid").val();
	var data = {
		plfidid:plfidid,
		nplcategory:nplcategory,
		key:key,
		companyid:companyid,
		page:page
	};
	$.ajax({
		type:"POST",
		url:base+"/policyfilemgr/querydetail" ,
		data:data,
		dataType:"json",
		success:function(data){
			initfile(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});
}




function initfile(data) {
	var html="";
	var key=$("#key").val();
	$.each(data.content,function(i,obj){
		var pldtailid=obj.pldtailid;
		var nplcategory=obj.nplcategory;
		var plsubclassname=obj.plsubclassname;
		var plconditions=obj.plconditions;
		var rdescription=obj.rdescription;
		if(key!=null&&key!=""&&key!=undefined){
			nplcategory=obj.nplcategorystr;
			plsubclassname=obj.plsubclassnamestr;
			plconditions=obj.plconditionsstr;
			rdescription=obj.rdescriptionstr;
		}

		if(plsubclassname!=''&&plsubclassname!=null){
			nplcategory=nplcategory+"("+plsubclassname+")";
		}

		html+='<tr>'
				+'<td style="text-align: center;">'+nplcategory+'</td>'
				+'<td style="text-align: center;">'+plconditions+'</td>'
				+'<td style="text-align: center;">'+rdescription+'</td>';
		if(obj.sbstate=='1'){
			html+='<td style="text-align: center;">已申报</td>'
					+'</tr>';
		}else{
			html+='<td style="text-align: center;"><a  href="javascript:void(0);"  onclick="apply(\''+pldtailid+'\');">申报</a></td>'
					+'</tr>';

		}




	});
	var totalPages = data.totalPages;
	var totalElements = data.totalElements;
	var nextpageflag = "";
	var beforepageflag = "";
	var currentpage = data.number;
	if(data.last){
		nextpageflag = "disabled";
	}else{
		nextpageflag = "onclick='querydetail("+(currentpage+1)+")'";
	}
	if(data.first){
		beforepageflag = "disabled";
	}else{
		beforepageflag = "onclick='querydetail("+(currentpage-1)+")'";
	}
	var pagecontent = "<table width='100%'>"
			+"<tr style='font-size: 14px;'>"
			+"<td width='80%'>第"+(currentpage+1)+"页/共"+totalPages+"页，总共"+totalElements+"条记录</td>"
			+"<td width='20%' style='text-align:right;'>"
			+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' "+beforepageflag+">上一页</button>"
			+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' style='margin-left: 5px;' "+nextpageflag+">下一页</button>"
			+"</td>"
			+"</tr>"
			+"</table>"
	$("#policyresultcontent").html(html);
	$("#resultpagecontent").html(pagecontent);
}



</script>