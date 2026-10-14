[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','generalcodemgr'] otherjs=jss sername=['系统管理','数据字典']]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<form class="form-horizontal" role="form" id="formid" action="${base}/xyjsmgr/sys/generalcodename/save">
				<input type="hidden" id="codetableid" name="codetableid" value="${generalcodename.codetableid }"/>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">类别名称</label>
					<div class="col-xs-5">
						<input type="text" id="codetablename" name="codetablename" value="${generalcodename.codetablename }" class="col-xs-12">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">类别说明 </label>
					<div class="col-xs-5">
						<input type="text" id="codetabledesc" name="codetabledesc" value="${generalcodename.codetabledesc }"class="col-xs-12">
					</div>
				</div>
				<div class="form-group" >
						<label class="col-xs-3 control-label no-padding-right" style="text-align:right;">是否启用</label>
								<div class="col-xs-5">
										<i class="fa"></i> <input type="radio" name="isused" id="isused" value="0" [#if generalcodename.isused!=1] checked="checked" [/#if]>是
										 <input type="radio" name="isused" id="isused" value="1" [#if generalcodename.isused==1] checked="checked" [/#if]>否
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
<script type="text/javascript">
function submitform(){
	    var formflag = true;
	    if(formflag){
	    	formflag=false;
	    	$.ajax({
		        type: "POST",
		        url:"${base}/xyjsmgr/sys/generalcodename/save",
		        data:$('#formid').serialize(),
		        error: function(request) {
		            bootbox.alert("Connection error");
		        },
		        success: function(data) {
		        	if(data=="SUCCESS"){
		        		bootbox.alert("保存成功", function() {
		        			window.location.href="${base}/xyjsmgr/sys/generalcodename";
						});
		        		
		        	}else if(data=="FAIL"){
		        		bootbox.alert("类别名唯一");
		        	}	           
		        }
		    });
	    }
}
</script>
[/@layout.main]