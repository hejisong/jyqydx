[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策管理','政策申报通知'] modelcode=["qyzc","policydeclare"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="infofrom"   action="${base}/policydeclare/list" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">通知标题</label>
			<div class="col-xs-2">
				<input type="text" id="declarename" name="declarename" value="${declarename}" class="col-xs-12">
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;width: 10%;">申报开始时间</label>
			<div class="col-xs-1" style="width: 9%;">
				<div class="input-group date form_datetime ">
					<input class="form-control input-inline" placeholder=""  name="startdate" id="startdate" data-date-format="yyyy-MM-dd" readonly
						   value="[#if startdate??]${(startdate?string('yyyy-MM-dd')) }[/#if]" style="border-right: 0px; background-color: white;height: 32px;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">申报结束时间</label>
			<div class="col-xs-1" style="width: 9%;">
				<div class="input-group date form_datetime ">
					<input  class="form-control input-inline" placeholder=""  name="enddate" id="enddate" data-date-format="yyyy-MM-dd" readonly
						   value="[#if enddate??]${(enddate?string('yyyy-MM-dd')) }[/#if]" style="border-right: 0px; background-color: white;height: 32px;">

					<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
				</div>
			</div>

			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;width: 5%;">状态</label>
			<div class="col-xs-1">
				<select class="form-control input-inline" name="state" id="state" style="height: 34px;padding: 2px">
					<option value="" [#if state==''] selected [/#if]>全部</option>
					<option value="1" [#if state=='1'] selected [/#if]>已保存</option>
					<option value="2" [#if state=='2'] selected [/#if]>已发布</option>
					<option value="3" [#if state=='3'] selected [/#if]>撤销发布</option>
					<option value="0" [#if state=='0'] selected [/#if]>已删除</option>
				</select>


			</div>
			<div class="col-xs-3" style="text-align: center;" >
			      <button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				  <a style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" href="${base}/policydeclare/new">新 增</a>

				<button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>
		</form>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:25%;">通知标题</th>
						<th style="width:15%;">申报时间范围</th>
						<th style="width:16%;">适用园区</th>
						<th style="width:10%;">发布单位</th>
						<th style="width:8%;">发布日期</th>
						<th style="width:8%;">状态</th>
						<th style="width:14%;">操作</th>
					</tr>
				</thead>
				<tbody>
				[#list page.content as info ]
					<tr >
						<td>${info_index+1}</td>
						<td>${info.declarename}</td>
						<td>[#if info.declare_start_date??]${(info.declare_start_date?string('yyyy-MM-dd')) }[/#if]~[#if info.declare_end_date??]${(info.declare_end_date?string('yyyy-MM-dd')) }[/#if]</td>
						<td>${info.applyparkname}</td>
						<td>${info.releaseorgname}</td>
						<td>[#if info.releasedate??]${(info.releasedate?string('yyyy-MM-dd')) }[/#if]</td>
						<td>[#if info.state='1' ]已保存
							[#elseif info.state='2' ]已发布
							[#elseif info.state='3' ]撤销发布[#elseif info.state='0' ]已删除[/#if]</td>
						<td>

							[#if info.state='0']
								<a  href="${base}/policydeclare/show/${info.declareid}">查看</a>

							[#else]

							<a  href="${base}/policydeclare/materialmgr/${info.declareid}">申报材料管理</a>
							[#if info.state='1'||info.state='3']
							<a  onclick="punishinfo('${info.declareid}');" href="javascript:void(0);">发布</a>
							[#elseif info.state='2']
								<a  onclick="notpunishinfo('${info.declareid}');" href="javascript:void(0);">撤销发布</a>
							[/#if]
							<a  href="${base}/policydeclare/edit/${info.declareid}">编辑</a>
							<a  style="color: red;" onclick="deleteinfo('${info.declareid}');" href="javascript:void(0);">删除</a>
							[/#if]
						</td>
					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policydeclare/list?declarename=${declarename}&startdate=${(startdate?string('yyyy-MM-dd'))!}&enddate=${(enddate?string('yyyy-MM-dd'))!}&state=${state}&"]
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


	function resetinfo() {

		$("#declarename").val('');
		$("#startdate").val('');
		$("#enddate").val('');
		$("#state").val('');
		$("#infofrom").submit();
	}

</script>
[/@layout.main]