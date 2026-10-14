[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策管理','政策文件管理'] modelcode=["qyzc","policyfilemgr"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal"  id="infofrom" action="${base}/policyfilemgr/list" method="get">

			<div class="form-group">
				<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策名称</label>
				<div class="col-xs-2">
					<input type="text" id="policyname" name="policyname" value="${policyname}" class="col-xs-12">
				</div>
				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策内容</label>
				<div class="col-xs-2">
					<input type="text" id="content" name="content" value="${content}" class="col-xs-12">
				</div>

			</div>



			<div class="form-group">
				<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策级别</label>
				<div class="col-xs-2">
					<select class="form-control input-inline" name="filelevel" id="filelevel" style="height: 34px;padding: 2px">
						<option value="" [#if filelevel==''] selected [/#if]>全部</option>
						<option value="1" [#if filelevel=='1'] selected [/#if]>市级</option>
						<option value="2" [#if filelevel=='2'] selected [/#if]>区级</option>
					</select>
				</div>
				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">状态</label>
				<div class="col-xs-2">
					<select class="form-control input-inline" name="state" id="state" style="height: 34px;padding: 2px">
						<option value="" [#if state==''] selected [/#if]>全部</option>
						<option value="1" [#if state=='1'] selected [/#if]>已录入</option>
						<option value="2" [#if state=='2'] selected [/#if]>已发布</option>
						<option value="3" [#if state=='3'] selected [/#if]>撤销发布</option>
					</select>
				</div>

				<div class="col-xs-5" style="text-align: center;" >
					<button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
					<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/policyfilemgr/new">新 增</a>

					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="exportinfo();">导出政策</button>

					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="exportinfodetail();">导出政策及细则</button>

					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
				</div>
			</div>








		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:22%;">政策名称</th>
						<th style="width:12%;">发文单位</th>
						<th style="width:10%;">生效日期</th>
						<th style="width:15%;">文号</th>

						<th style="width:8%;">政策级别</th>
						<th style="width:8%;">政策状态</th>
						<th style="width:12%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as file ]
					<tr >
						<td>${file_index+1}</td>
						<td>${file.policyname}</td>
						<td>${file.fwunits}</td>
						<td>${file.releasetime}</td>
						<td>${file.fwtitanic}</td>
						<td>[#if file.filelevel='1' ]市级政策
							[#elseif file.filelevel='2' ]区级政策[/#if]</td>
						<td>[#if file.state='0' ]已删除
							[#elseif file.state='1' ]已录入
							[#elseif file.state='2' ]已发布[#elseif file.state='3' ]撤销发布[/#if]</td>
						<td>
							<a  href="${base}/policyfilemgr/policydetail/${file.plfidid}" >政策明细</a>
							[#if file.state='1'||file.state='3']
							<a  onclick="punishinfo('${file.plfidid}');" href="javascript:void(0);">发布</a>
							[#elseif file.state='2']
								<a  onclick="notpunishinfo('${file.plfidid}');" href="javascript:void(0);">撤销发布</a>
							[/#if]
							[#if file.state!='2']
							<a  href="${base}/policyfilemgr/edit/${file.plfidid}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${file.plfidid}');" href="javascript:void(0);">删除</a>
							[#else]
								<a  href="${base}/policyfilemgr/show/${file.plfidid}">查看</a>
							[/#if]
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policyfilemgr/list?policyname=${policyname}&content=${content}&filetype=${filetype}&filelevel=${filelevel}&state=${state}&"]
		[#include "common/pagemodel.ftl"]
	</div>
	
</div>
<script type="text/javascript">
	$(function(){
		initTime();
	});
	function initTime(){
		$(".form_datetime").datetimepicker({
			autoclose: true,
			isRTL: Metronic.isRTL(),
			format: "yyyy-mm-dd",
			minuteStep : 1,
			minView : 2,
			pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
			language: 'zh_CN'
		});
	}
	function checkAll(){
		var check=$("input[name='checktable']");
		for(i=0;i<check.length;i++){
			if($("#checkall")[0].checked==false){
				check[i].checked=false;
			}else{
				check[i].checked=true;
			}
		}

	}

	function deleteinfo(id) {
		bootbox.confirm("确认删除该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/updatestate/"+id+"/0";
			}

		})

	}

	function punishinfo(id) {
		bootbox.confirm("确认发布该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/updatestate/"+id+"/2";
			}

		})

	}

	function notpunishinfo(id) {
		bootbox.confirm("确认撤销发布该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/updatestate/"+id+"/3";
			}

		})

	}

	function resetinfo() {
		$("#policyname").val('');
		$("#content").val('');
		$("#filelevel").val('');
		$("#state").val('');
		$("#infofrom").submit();
	}


	function exportinfo() {
		var policyname=$("#policyname").val();
		var content=$("#content").val();
		var filelevel=$("#filelevel").val();
		var state=$("#state").val();
		var url=base+"/policyfilemgr/infoExport?policyname="+policyname+"&filelevel="+filelevel+"&content="+content+"&state="+state;
		window.location.href = url;

		return ;
	}

	function exportinfodetail() {
		var policyname=$("#policyname").val();
		var content=$("#content").val();
		var filelevel=$("#filelevel").val();
		var state=$("#state").val();
		var url=base+"/policyfilemgr/detailinfoExport?policyname="+policyname+"&filelevel="+filelevel+"&content="+content+"&state="+state;
		window.location.href = url;
		return ;
	}

</script>
[/@layout.main]