[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策管理','政策申报通知'] modelcode=["qyzc","policydeclare"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
	  <form class="form-horizontal"  action="${base}/policydeclare/materialmgr/${id}" method="get">

		  <label class="col-xs-2 control-label no-padding-right" style="text-align:right;">政策文件</label>
		  <div class="col-xs-5" >

			  <select class="form-control input-inline" name="plfidid" style="height: 28px;padding: 2px" onchange="initDl(this);">
				  [#list policyfiles as policyfile]
					  <option value="${policyfile.plfidid}" [#if plfidid==policyfile.plfidid] selected [/#if]>${policyfile.policyname}</option>
				  [/#list]
			  </select>
		  </div>



		  <div class="col-xs-3"  >
			  <button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">搜  索</button>
			  <a style="margin-left: 10px;" class="btn btn-xs btn-info btn-width" href="${base}/policydeclare/list">返 回</a>
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
						<th style="width:16%;">政策大类名称</th>
						<th style="width:50%;">申报材料</th>
						<th style="width:8%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list dls as dl ]
					<tr >
						<td>${dl_index+1}</td>
						<td>${dl.policyname}</td>
						<td>${dl.nplcategory}</td>
						<td>[#if dl.materiallists??]
							[#list dl.materiallists as material]
							  ${material_index+1}.${material.materialname}&nbsp;
							[/#list]
						[/#if]</td>


						<td>
							<a  href="${base}/policydeclare/material/${policydeclare.declareid}/${dl.plfidid}/${dl.nplcategory}">修改申报材料</a>

						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>

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


	function deleteinfo(id) {
		bootbox.confirm("确认删除该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/0";
			}

		})

	}

	function punishinfo(id) {
		bootbox.confirm("确认发布该政策申报通知?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/2";
			}

		})

	}

	function notpunishinfo(id) {
		bootbox.confirm("确认撤销发布该政策申报通知?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/3";
			}

		})

	}

</script>
[/@layout.main]