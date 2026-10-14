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
							<a class="navbar-brand" onclick="closefile_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<label class="col-xs-2 control-label no-padding-right" style="text-align:right;">政策文件名称</label>
									<div class="col-xs-5">
										<input  id="f_policyname" class="col-xs-10" type="text"  >
									</div>

									<div class="col-xs-3 control-label no-padding-right" style="text-align: center;">
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="queryfile(0);">查 询</button>
										<button class="btn btn-xs btn-info btn-width"  type="button" onclick="chooseinfo();">确 定</button>
										<button class="btn btn-sm"  type="button" onclick="closefile_dialog();">取 消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:450px;overflow-y:auto;width: 100%;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th width="10%;" style="text-align: center;"></th>
										<th width="90%;" style="text-align: center;">政策文件名称</th>


									</tr>
									</thead>
									<tbody id="resultcontent">

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
function openfile_dialog() {
	queryfile(0)
 	$("#fullbg").show();
  	$("#file_dialog").show();
}
//关闭用户弹出框
function closefile_dialog() {
	$("#fullbg,#file_dialog").hide();
}



function queryfile(page) {
	var filename=$("#f_policyname").val();
	var data = {
		filename:filename,
		page:page
	};
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/querypolicyfile" ,
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
	$.each(data.content,function(i,obj){
		var plfidid=obj.plfidid;
		html+='<tr>'
				+'<td style="text-align: center;"><input type="checkbox" value="'+plfidid+'"></td>'
				+'<td style="text-align: center;">'+obj.policyname+'</td></tr>';


	});
	var totalPages = data.totalPages;
	var totalElements = data.totalElements;
	var nextpageflag = "";
	var beforepageflag = "";
	var currentpage = data.number;
	if(data.last){
		nextpageflag = "disabled";
	}else{
		nextpageflag = "onclick='queryfile("+(currentpage+1)+")'";
	}
	if(data.first){
		beforepageflag = "disabled";
	}else{
		beforepageflag = "onclick='queryfile("+(currentpage-1)+")'";
	}
	var pagecontent = "<table width='100%'>"
			+"<tr>"
			+"<td width='80%'>第"+(currentpage+1)+"页/共"+totalPages+"页，总共"+totalElements+"条记录</td>"
			+"<td width='20%' style='text-align:right;'>"
			+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' "+beforepageflag+">上一页</button>"
			+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' style='margin-left: 5px;' "+nextpageflag+">下一页</button>"
			+"</td>"
			+"</tr>"
			+"</table>"
	$("#resultcontent").html(html);
	$("#resultpagecontent").html(pagecontent);
}


function chooseinfo() {
	var files=$("#resultcontent").find("tr");
	var plfidids="";
	var policynames="";
	$.each(files, function(e,star){
		var checkbox=$(star).children("td:eq(0)").find("input");
		if($(checkbox)[0].checked==true){
			var plfidid=$(checkbox).val();
			var policyname=$(star).children("td:eq(1)").text();
			plfidids+=plfidid+",";
			policynames+=policyname+",";

		}

	})
	if(policynames!=""){
		policynames=policynames.substr(0,policynames.length-1);
	}else{
		bootbox.alert("请选择政策！")
		return;
	}

	$("#policyfilename").html(policynames);
	$("#policyfileid").val(plfidids);
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/querytag" ,
		data:{plfidids:plfidids},
		dataType:"json",
		success:function(data){
			inittag(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});

	closefile_dialog();
}

function inittag(tags) {
	var html="";
	var tagids="";
	$.each(tags, function(e,tag){

		html+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">'+tag[1]+'</span>';
		tagids+=tag[0]+",";

	})

	$("#tagname").html(html);
	$("#tagid").val(tagids);
}
</script>