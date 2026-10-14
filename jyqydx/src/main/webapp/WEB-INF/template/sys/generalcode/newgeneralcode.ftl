[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','generalcodemgr'] otherjs=jss sername=['系统管理','数据字典']]
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<form class="form-horizontal" role="form" id="formid" action="${base}/xyjsmgr/sys/generalcodename/savegeneralcode">
				<input type="hidden" id="codetableid" name="codetableid" value="${generalcodename.codetableid }"/>
				<input type="hidden" id="codeid" name="codeid" value="${generalcode.codeid }"/>
				<!--  -->
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">代码名称</label>
					<div class="col-xs-5">
						<input type="text" id="codedesc" name="codedesc" value="${generalcode.codedesc }" class="col-xs-12">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">代码</label>
					<div class="col-xs-5">
						<input type="text" id="ucode" name="ucode" value="${generalcode.ucode }"class="col-xs-12">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">顺序号 </label>
					<div class="col-xs-5">
						<input type="text" id="usedfrequency" name="usedfrequency" value="${generalcode.usedfrequency}"
							class="col-xs-12">
					</div>
				</div>
				<div class="form-group" >
						<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">是否启用</label>
								<div class="col-xs-5">
										<i class="fa"></i> <input type="radio" name="isdisabled" id="isdisabled" value="0" [#if generalcode.isdisabled!=1] checked="checked" [/#if]>是
										 <input type="radio" name="isdisabled" id="isdisabled" value="1" [#if generalcode.isdisabled==1] checked="checked" [/#if]>否
									</div>
				</div>
				<div class="clearfix form-actions" style="text-align:center;">
					<button class="btn btn-info" type="button" id="submitid" onclick="submitform()">保 存</button>
					<button class="btn" type="button" onclick="history.go(-1)">取 消</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function submitform(){
	var codetableid=$("#codetableid").val();
	var formflag = true;
	if(formflag){
		formflag=false;
		$.ajax({
	        type: "POST",
	        url:"${base}/xyjsmgr/sys/generalcodename/savegeneralcode",
	        data:$('#formid').serialize(),
	        error: function(request) {
	            bootbox.alert("Connection error");
	        },
	        success: function(data) {
	        	if(data=="SUCCESS"){
	        		bootbox.alert("保存成功", function() {
	        			window.location.href="${base}/xyjsmgr/sys/generalcodename/manage/"+codetableid;
					});
	        		
	        	}else if(data=="FAIL"){
	        		bootbox.alert("代码名称和代码唯一");
	        	}	           
	        }
	    });
	}
}
</script>
[/@layout.main]