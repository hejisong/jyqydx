[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['政策服务管理','政策申报通知'] modelcode=["zcfwgl","policydeclare"] otherjs=jss othercss=css]

  <div class="row">

     ${result}

	
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