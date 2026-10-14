[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['一企来专窗','投诉与咨询'] modelcode=["yqlzc","complaint"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">

				<form name="findlist"  id="infoform"  action="${base}/businesshare/complaint/checklist" method="get">
					<div class="form-group" style="display: flex;align-items: center;">
						<label class="col-xs-2 control-label no-padding-right" style="text-align:right;">单位名称</label>
						<div class="col-xs-2">
							<input type="text" id="companyname" name="companyname" value="${companyname }" class="col-xs-12">
						</div>
						<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">标题</label>
						<div class="col-xs-2">
							<input type="text" id="title" name="title" value="${title}" class="col-xs-12">
						</div>


						<div class="col-xs-3" style="text-align: center;" >
							<button class="btn btn-xs btn-info btn-width" type="submit" >查 询 </button>
							<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
						</div>
					</div>
				</form>
		</div>
			<div class="col-xs-12">
				<div class="table-responsive">
				<table id="sample-table-1"
					   class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th style="width: 5%;">序号</th>
						<th style="width: 20%;">单位名称</th>
						<th style="width: 12%;">联系方式</th>
						<th style="width: 25%;">标题</th>
						<th style="width: 15%;">发表时间</th>
						<th style="width: 6%;">浏览量</th>
						<th style="width: 8%;">状态</th>
						<th style="width: 9%;">操作</th>
					</tr>
					</thead>
					<tbody>
					[#list page.content  as info]
							<tr>
								<td>${info_index+1}</td>
								<td style="text-align: left;">${info.companyname}</td>
								<td>${info.mobile}</td>
								<td style="text-align: left;"><a style="font-size: 14px;" href="${base}/businesshare/complaintcheckshow/${info.id}">${info.title}</a></td>
								<td>${info.createtime}</td>
								<td>${info.readcount}</td>
								<td>[#if info.state=='0']未回复[#elseif info.state=='2']已回复[/#if]</td>
								<td>
									<a [#if info.state=='0'][#else]disabled[/#if] class="btn btn-xs btn-info" href="${base}/businesshare/complaintcheck/${info.id}">回 复</a>
[#--									<button class="btn btn-xs btn-danger" onclick="deleteOrder('${organinfo.id}');" >删 除</button>--]
								</td>
							</tr>
					[/#list]
					</tbody>
				</table>
			</div>

			[#assign pagepath="${base}/businesshare/complaint/checklist?companyname=${companyname}&title=${title}&infotype=${infotype}&"]
			[#include "common/pagemodel.ftl"]
		</div>
	</div>


	<script type="text/javascript">
		function resetinfo() {
			$("#companyname").val('');
			$("#title").val('');
			$("#infoform").submit();
		}
		function deleteOrg(id){
			bootbox.confirm("确认删除该机构?",function (result) {
				if(result==true){
					window.location.href=base + "/sys/organinfo/delete/"+id;
				}

			})
		}

	function doOrder(orderid) {
		bootbox.confirm("确认处理代办业务?",function (result) {
			if(result==true){
				window.location.href=base + "/businesshare/orderagency/doorder/"+orderid;
			}

		})
	}

	</script>
[/@layout.main]
