[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['一企来专窗','预约代办'] modelcode=["yqlzc","orderagency"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
				<form name="findlist"  id="infoform" action="${base}/businesshare/orderagency/checklist" method="get">
					<div class="form-group" style="display: flex;align-items: center;">
						<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">预约人</label>
						<div class="col-xs-2">
							<input type="text" id="orderman" name="orderman" value="${orderman }" class="col-xs-12">
						</div>
						<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">预约企业</label>
						<div class="col-xs-2">
							<input type="text" id="ordercompany" name="ordercompany" value="${ordercompany}" class="col-xs-12">
						</div>
						<div class="col-xs-3" style="text-align: center;">
						<button class="btn btn-xs btn-info btn-width" type="submit" >查 询 </button>

						<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
						</div>
					</div>
				</form>
		</div>
		<div class="col-xs-12">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th style="width: 5%;">序号</th>
						<th style="width: 8%;">代办人</th>
						<th style="width: 8%;">预约人</th>
						<th style="width: 24%;">预约企业</th>
						<th style="width: 13%;">联系电话</th>
						<th style="width: 14%;">预约时间</th>
						<th style="width: 20%;">备注</th>
						[#--<th style="width: 10%;">状态</th>--]
					[#--	<th style="width: 10%;">操作</th>--]
					</tr>
					</thead>
					<tbody>
					[#list page.content  as info]
							<tr>
								<td>${info_index+1}</td>
								<td>${info.agencyman}</td>
								<td>${info.orderman}</td>
								<td>${info.ordercompany}</td>
								<td>${info.contact}</td>
								<td>${(info.ordertime?string('yyyy-MM-dd HH:mm:ss')) }</td>
								<td>${info.remark}</td>
								[#--<td>[#if info.state=='0']未处理[#else]已处理[/#if]</td>--]
								[#--<td>
									<a  href="${base}/businesshare/orderagency/show/${file.plfidid}">查看</a>
								</td>--]
							</tr>
					[/#list]
					</tbody>
				</table>
			</div>

			[#assign pagepath="${base}/businesshare/orderagency/checklist?orderman=${orderman}&ordercompany=${ordercompany}&"]
			[#include "common/pagemodel.ftl"]
		</div>
	</div>


	<script type="text/javascript">
		function resetinfo() {
			$("#orderman").val('');
			$("#ordercompany").val('');
			$("#infoform").submit();
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
