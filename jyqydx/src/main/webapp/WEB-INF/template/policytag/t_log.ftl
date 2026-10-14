<!-- 遮挡层 -->
<div class="dialog" id="log_dialog" style="display:none;height:600px;width:1000px;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>企业标签任务执行日志</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closelog()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>


					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:462px;overflow-y:auto;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th width="6%;" style="text-align: center;">序号</th>
										<th width="29%;" style="text-align: center;">任务执行开始时间</th>
										<th width="29%;" style="text-align: center;">任务执行结束时间</th>
										<th width="18%;" style="text-align: center;">处理企业数量</th>
										<th width="18%;" style="text-align: center;">更新标签企业数量</th>

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
function openlog() {
	querylog(0)
 	$("#fullbg").show();
  	$("#log_dialog").show();
}
//关闭用户弹出框
function closelog() {
	$("#fullbg,#log_dialog").hide();
}



function querylog(page) {
	var tasktype="4";
	$.ajax({
		type:"POST",
		url:base+"/policytag/querylog" ,
		data:{tasktype:tasktype,page:page},
		dataType:"json",
		success:function(data){
			initlog(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});
}
function initlog(data) {
	var html="";

	$.each(data.content,function(i,obj){
		var plfidid=obj.plfidid;
		html+='<tr>'
				+'<td style="text-align: center;">'+(i+1)+'</td>'
				+'<td style="text-align: center;">'+format(obj.begintime)+'</td>'
		        +'<td style="text-align: center;">'+format(obj.endtime)+'</td>'
				+'<td style="text-align: center;">'+obj.datacount+'家</td>'
				+'<td style="text-align: center;">'+obj.updatedatacount+'家</td></tr>';

	});
	var totalPages = data.totalPages;
	var totalElements = data.totalElements;
	var nextpageflag = "";
	var beforepageflag = "";
	var currentpage = data.number;
	if(data.last){
		nextpageflag = "disabled";
	}else{
		nextpageflag = "onclick='querylog("+(currentpage+1)+")'";
	}
	if(data.first){
		beforepageflag = "disabled";
	}else{
		beforepageflag = "onclick='querylog("+(currentpage-1)+")'";
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


function format(shijianchuo) {
	if(shijianchuo!=null&&shijianchuo!=''){
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	}
	return '';

}

function add0(m){return m<10?'0'+m:m }




</script>