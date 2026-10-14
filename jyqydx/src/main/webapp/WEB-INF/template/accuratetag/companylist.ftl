[#assign jss=["assets/js/jquery.validate.min.js"  ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['标签管理','企业精准性标签管理'] modelcode=["tagmgr","companyaccuratetag"] otherjs=jss othercss=css]
[#include "accuratetag/tanch_import.ftl"]
[#include "common/loading.ftl"]
  <div class="row">
		<form class="form-horizontal"   action="${base}/accuratetag/corplist" method="get">

			<div class="col-xs-12 search-bg">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">企业名称</label>
			<div class="col-xs-2" style="margin-bottom: 20px;">
				<input type="text" id="companyname" name="companyname" value="${companyname}" class="col-xs-12">
			</div>


			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">年份</label>
			<div class="col-xs-1">
				<select class="form-control input-inline" style="height: 34px;padding: 2px" name="tagyear">
					<option value=""  [#if tagyear==''] selected="selected"[/#if] >全部</option>
					[#list years as year]
						<option value="${year}" [#if tagyear==year] selected="selected"[/#if]>${year}</option>
					[/#list]
				</select>
			</div>

				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">标签名称</label>
				<div class="col-xs-2">
					<select class="form-control input-inline" style="height: 34px;padding: 2px" id="tagname" name="tagname" >
						<option value=""  [#if tagname==''] selected="selected"[/#if]>全部</option>
						[#list accuratetags as tag]
							<option value="${tag.tagname}" [#if tagname==tag.tagname] selected="selected"[/#if]>${tag.tagname}</option>
						[/#list]
					</select>
				</div>

			<div class="col-xs-4" style="text-align: center;" >
				<button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/accuratetag/newcorptag">新 增</a>

				<a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/assets/file/企业精准化标签导入模板.xlsx" target="_blank">导入表格模板下载</a>

				<button style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openImport();">导入</button>

				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>

			</div>
		</form>
	  <form class="form-horizontal" id="tagcorpform"  >
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:30%;">企业名称</th>
						<th style="width:20%;">统一社会信用代码</th>
						<th style="width:15%;">年份</th>
						<th style="width:30%;">标签名称</th>

					</tr>
				</thead>
				<tbody id="taginfo">
				[#--[#assign index=0]
				[#list infos as info ]
					[#list info.tags as tag ]
						[#assign index=index+1]
					<tr >
						<td>${index}</td>
						[#if tag_index==0]
						<td rowspan="${info.tags?size}">${tag[1]}</td>
						<td rowspan="${info.tags?size}">${tag[2]}</td>
						[/#if]

						<td >${tag[3]}</td>
						<td>${tag[4]}</td>

					</tr>
					[/#list]
				[/#list]--]


				[#list page.content as info ]

					<tr >
						<td>${info_index+1}</td>

						<td>${info[1]}</td>
						<td>${info[2]}</td>
						<td>${info[3]}</td>
						<td>${info[4]}</td>

					</tr>
					[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/accuratetag/corplist?companyname=${companyname}&tagyear=${tagyear}&tagname=${tagname}&"]
		[#include "common/pagemodel.ftl"]
	</div>
	  </form>

</div>
	<input type="hidden" id="formstate"  value="Y">
<script type="text/javascript">
	function resetinfo() {
		$("#enterprisename").val('');
		$("#uscreditcode").val('');
		$("#tagcorpform").submit();

	}


	function saveinfo() {
		// $("#forminfo").submit();
		// initinfo();
		$("#loading_state").val("1");
		$.ajax({
			type:"post",
			url:base + "/accuratetag/savecorptag",
			async:true,
			data: $("#tagcorpform").serialize(),
			success:function(data){
				if(data=="SUCCESS"){
					bootbox.confirm("保存成功！",function (result) {
						window.location.reload();
					})

				}else{
					bootbox.alert("保存失败！");
				}
			},
			error:function(){
				bootbox.alert("数据加载失败");
			}
		});
	}


</script>
[/@layout.main]