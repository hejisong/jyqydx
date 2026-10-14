[#assign jss=["assets/js/jquery.validate.min.js"  ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['政策管理','政策文件管理'] modelcode=["qyzc","policyfilemgr"] otherjs=jss othercss=css]
[#include "policyfilemgr/tanch_import.ftl"]
[#include "common/loading.ftl"]
<div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="detailform"  action="${base}/policyfilemgr/policydetail/${policyfile.plfidid}" method="get">
			<div class="form-group" style="text-align: center;">
				<div class="col-xs-11" >
					<label  style="text-align:center;font-size: 20px;font-weight: 600;">${policyfile.policyname}</label>
					<label style="margin-left: 20px;">[#if policyfile.fwtitanic??&&policyfile.fwtitanic!='']文号：${policyfile.fwtitanic} [/#if]</label>

				</div>

			</div>


			<div class="form-group">
				<div class="col-xs-6"></div>
				<div class="col-xs-6" style="text-align: center;">
					<input type="checkbox" id="isdelete" [#if isdelete=='1'] checked [/#if] onclick="querydetail();">包含作废
					<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/policyfilemgr/newdetail/${policyfile.plfidid}">新 增</a>
					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="confirmMore();">批量确认</button>
					<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/assets/file/政策明细导入模板.xlsx" target="_blank">导入表格模板下载</a>
					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openImport('${policyfile.plfidid}');">导入</button>
					<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="exportinfo('${policyfile.plfidid}');">导出</button>
					<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/policyfilemgr/list?infotype=1">返 回</a>
				</div>
			</div>


			<div class="form-group">

			</div>
		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th style="width:5%;"><input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"></th>
					<th style="width:5%;">序号</th>
					<th style="width:15%;">政策大类</th>
					<th style="width:15%;">政策小类</th>
					<th style="width:24%;">政策条件</th>
					<th style="width:20%;">奖励描述</th>
					[#--<th style="width:5%;">奖励金额</th>--]
					[#--<th style="width:8%;">兑付路径</th>
					<th style="width:8%;">审批单位</th>--]
				[#--	<th style="width:8%;">参与评定部门</th>--]
					[#--<th style="width:25%;">申报条件</th>--]
					<th style="width:7%;">状态</th>
					<th style="width:9%;">操作</th>
				</tr>
				</thead>
				<tbody>
				[#list page.content as detail ]
					<tr >
						<td>[#if detail.state=='1'][#else]<input type="checkbox" name="check" id="check" value="${detail.pldtailid}" onclick="check();">[/#if]</td>
						<td>${detail_index+1}</td>
						<td>${detail.nplcategory}</td>
						<td>${detail.plsubclassname}</td>
						<td>${detail.plconditions}</td>
						<td>${detail.rdescription}</td>
						[#--<td>[#if detail.ramount==0] [#else]${detail.ramount}[/#if]</td>--]
						[#--<td>${detail.thpath}</td>
						<td>${detail.pevaluationdepartment}</td>--]
					[#--	<td>${detail.feaunit}</td>--]
						[#--<td>${detail.declareconditions}</td>--]
						<td>[#if detail.state=='1']已确认[#else]未确认[/#if]</td>
						<td>
							[#if detail.state==''||detail.state=='0']
							     <a  href="javascript:void(0);"  onclick="confirm('${detail.pldtailid}');">确认</a>
								 <a  href="${base}/policyfilemgr/material/${detail.pldtailid}"  >编辑</a>
							[#elseif detail.state=='1']
								 <a  href="javascript:void(0);"  onclick="notconfirm('${detail.pldtailid}');">取消确认</a>
							[/#if]

							[#if detail.state=='2']
								<a  href="javascript:void(0);" style="color: red;margin-left: 2px;" onclick="cancledeleteinfo('${detail.pldtailid}');">取消作废</a>
							[#else]
							  <a  href="javascript:void(0);" style="color: red;margin-left: 2px;" onclick="deleteinfo('${detail.pldtailid}');">作废</a>
							[/#if]

						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policyfilemgr/policydetail/${policyfile.plfidid}?"]
		[#include "common/pagemodel.ftl"]
	</div>
	
</div>
<input type="hidden" id="formstate"  value="Y">
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


	function deleteinfo(id) {
		bootbox.confirm("作废该政策明细?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/confirm/"+id+"/2";
			}

		})

	}

	function cancledeleteinfo(id) {
		bootbox.confirm("取消作废该政策明细?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/confirm/"+id+"/0";
			}

		})
	}

	function confirm(id) {
		bootbox.confirm("确认该政策明细?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/confirm/"+id+"/1";
			}

		})

	}

	function notconfirm(id) {
		bootbox.confirm("取消确认该政策明细?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/confirm/"+id+"/0";
			}

		})

	}
	
	function confirmMore() {
		var pldtailids="";
		var state="1";
		var check=$("input[name='check']");
		for(i=0;i<check.length;i++){
			if(check[i].checked==true){
				pldtailids +=check[i].value+",";
			}
		}

		if(pldtailids==""){
			bootbox.alert("请选择要确认的信息！");
		} else{
			bootbox.confirm("确认信息?",function (result) {
				if(result==true){
					$("#loading_state").val("1");
					$.ajax({
						type:"post",
						url:base + "/policyfilemgr/confirmmore",
						async:true,
						data:{pldtailids:pldtailids,state:state},
						success:function(data){
							if(data=="SUCCESS"){
								bootbox.alert("确认成功！");
								window.location.reload();
							}else{
								bootbox.alert("确认失败！");
							}
						},
						error:function(){
							bootbox.alert("数据加载失败");
						}
					});

				}

			} )
		}
	}

	function notpunishinfo(id) {
		bootbox.confirm("确认撤销发布该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policyfilemgr/updatestate/"+id+"/3";
			}

		})

	}


	function exportinfo(plfidid) {
		window.location.href = '${base}/policyfilemgr/detailExport/'+plfidid;
		return ;
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


	function querydetail() {
		var url=$("#detailform").attr("action");
		var isd=$("#isdelete");
		if($(isd)[0].checked==true){

			window.location.href=url+"?isdelete=1";
		}else{
			window.location.href=url+"?isdelete=0";
		}

	}

</script>
[/@layout.main]