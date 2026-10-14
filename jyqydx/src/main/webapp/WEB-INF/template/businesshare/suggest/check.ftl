[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['一企来专窗','意见与建议'] modelcode=["yqlzc","suggest"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				[#include "businesshare/suggest/showform.ftl"]
			</div>
			<form class="form-horizontal" role="form" id="replyformid" action="${base}/businesshare/suggest/replysave" method="post">
				<input type="hidden" name="suggestid" value="${suggest.id}">
				<input type="hidden" name="infotype" value="1">

				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 是否公开</label>
					<div class="col-xs-1">
						<div class="block input-icon input-icon-right">
							<select name="isshow" id="isshow" class="form-control" style="height: 34px;">
								<option value="1" [#if suggest.infotype=='1'] selected[/#if]>是</option>
								<option value="0" [#if suggest.infotype=='0'] selected[/#if]>否</option>
							</select>
						</div>
					</div>
				</div>

			<div class="form-group">
				<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 回复内容<span style="color: red;">*</span></label>
				<div class="col-xs-6">
					<div class="block input-icon input-icon-right">
						<textarea  id="replycontent" rows="4" name="content" value="" class="form-control"></textarea>
					</div>
				</div>
			</div>
			</form>
			<div class="clearfix form-actions" style="text-align: center;">
				<button class="btn btn-sm btn-info" type="button" id="submitid" onclick="saveReply();">回 复</button>
				<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
			</div>

		</div>
	</div>


	<script type="text/javascript">
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

	function saveReply() {
		var replycontent=$("#replycontent").val();
		if(replycontent==null||replycontent==''||replycontent==undefined){
			bootbox.alert("请填写回复内容！");
			return;
		} else{
           $("#replyformid").submit();
		}
	}

	</script>
[/@layout.main]
