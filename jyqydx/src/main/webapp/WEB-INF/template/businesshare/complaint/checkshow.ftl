[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['一企来专窗','投诉与咨询'] modelcode=["yqlzc","complaint"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				[#include "businesshare/complaint/showform.ftl"]
			</div>

			<div class="clearfix form-actions" style="text-align: center;">
				<button  class="btn btn-sm btn-info" type="button" onclick="history.go(-1)">返 回</button>
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
