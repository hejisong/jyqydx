[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/accurateg/newcorptag.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['标签管理','企业精准性标签管理'] modelcode=["tagmgr","companyaccuratetag"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="tagformid" action="${base}/accuratetag/savenewcorptag" method="post">
				<input type="hidden" name="id" value="${accuratetag.id }">
				
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 企业名称<span style="color: red;">*</span> </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="hidden" name="cid" value="${accuratetag.cid }">
							<input type="text" id="companyname" name="companyname" value="${accuratetag.companyname }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 统一社会信用代码<span style="color: red;">*</span></label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="uscreditcode" name="uscreditcode" value="${accuratetag.uscreditcode }" class="col-xs-12">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">标签名称</label>
					<div class="col-xs-3">
						<input type="hidden" id="tagid" name="tagid" value="${accuratetag.tagid}">
						<input type="hidden" id="tagname" name="tagname" value="${accuratetag.tagname}">
						<select class="form-control input-inline" style="height: 34px;padding: 2px" id="selecttagname" onchange="checktagname(this)">
							[#list accuratetags as tag]
								<option value="${tag.tagid}" [#if accuratetag.tagid==tag.tagid] selected="selected"[/#if]>${tag.tagname}</option>
							[/#list]
						</select>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">年份</label>
					<div class="col-xs-3">
						<select class="form-control input-inline" style="height: 34px;padding: 2px" name="tagyear">
							[#list years as year]
								<option value="${year}" [#if accuratetag.tagyear==year] selected="selected"[/#if]>${year}</option>
							[/#list]
						</select>
					</div>
				</div>
				<div class="clearfix form-actions" style="text-align: center;">
					<button class="btn btn-sm btn-info" type="submit" >保 存</button>
					<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
				</div>
			</form>
		</div>
	</div>
	<input type="hidden" id="formstate"  value="Y">
<script type="text/javascript">
	$(function(){
		FormValidation.init();
		initTime();
	});
	function checktagname(ele){
		var tagid=$(ele).val();
		var tagname=$("#selecttagname option:selected").text();
		$("#tagid").val(tagid);
		$("#tagname").val(tagname);
	}



	function saveinfo() {
		$("#loading_state").val("1");
		$.ajax({
			type:"post",
			url:base+"/accuratetag/savenewcorptag",
			data:$("#tagformid").serialize(),
			async:true,
			success:function(data){

				if(data=="SUCCESS"){
					bootbox.alert("保存成功！", function() {
						window.location.href=base + "/accuratetag/corplist";
					});

				}else{
					$("#formstate").val("Y");
					bootbox.alert("保存失败");


				}
			},
			error:function(){
				$("#formstate").val("Y");
				bootbox.alert("数据加载失败");
			}
		});




	}



</script>
[/@layout.main]