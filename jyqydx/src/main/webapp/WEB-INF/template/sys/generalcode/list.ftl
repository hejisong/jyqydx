[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','generalcodemgr'] otherjs=jss sername=['系统管理','数据字典']]
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
			<form name="findlist" action="${base}/xyjsmgr/sys/generalcodename/findList" method="get">
			<div class="form-group flex">
					<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">类别名称</label>
					<div class="col-xs-2">
						<input type="text" id="codetablename" name="codetablename" value="${generalcodename.codetablename }" class="col-xs-12" style="height: 34px;">
					</div>
					<label class="col-xs-1 control-label no-padding-right" style="text-align:right;">类别说明</label>
					<div class="col-xs-2">
						<input type="text" id="codetabledesc" name="codetabledesc" value="${generalcodename.codetabledesc }" class="col-xs-12" style="height: 34px;">
					</div>
					
					<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding: 0;margin-right: 10px;">是否启用</label>
								<div class="col-xs-1" style="display: flex;align-items: center;">
										<i class="fa"></i> <input type="radio" name="isused" id="isused" value="0"  checked="checked" style="margin: 0 4px;">是&nbsp;&nbsp;&nbsp;
										 <input type="radio" name="isused" id="isused" value="1" style="margin: 0 4px;" >否
					</div>
					<button class="btn btn-xs btn-info btn-width" type="submit">查 询 <i class="m-icon-swapright m-icon-white"></i></button>
					<a class="btn btn-xs btn-info btn-width" href="${base}/xyjsmgr/sys/generalcodename/new">添加新类别</a>
				    <button class="btn btn-xs btn-info btn-width" type="button" onclick="flushCache()">刷新缓存 <i class="m-icon-swapright m-icon-white"></i></button>
				  [#--  <button class="btn btn-xs btn-info btn-width" type="button" onclick="flushQianyi()">刷新迁移数据 <i class="m-icon-swapright m-icon-white"></i></button>--]
				    
				</div>
				</form>
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th></th>
							<th>类别名称</th>
							<th>类别说明</th>
							<th>更新时间</th>							
							<th>操作</th>						
						</tr>
					</thead>
					<tbody>
										[#list page.content as per]
										<tr [#if per_index%2==0]class= "gradeX
											odd"[#else]class="gradeX even" [/#if] role="row">
											 <td class="sorting_1">${per_index+1}</td>
											<td>${per.codetablename }</td>
											<td>${per.codetabledesc }</td>
											<td>${(per.updatedate?string('yyyy-MM-dd hh :mm:ss')) }</td>									
											<td><a class="btn btn-xs btn-info" 
											href="${base}/xyjsmgr/sys/generalcodename/${per.codetableid}"><i class="fa fa-edit"></i>编 辑</a>
											<button class="btn btn-xs btn-danger"  onclick="deletegeneralcode(this);" value="${per.codetableid}">删 除</button>
											[#if per.isused=="0"]<button class="btn btn-xs btn-warning" onclick="forbidden(this)" value="${per.codetableid}">禁 用</button>
								            [#else]<button class="btn btn-xs btn-success" onclick="restart(this)" value="${per.codetableid}">启 用</button>
								            [/#if] 			
											<a class="btn btn-xs btn-success"  href="${base}/xyjsmgr/sys/generalcodename/manage/${per.codetableid}">代码管理</a></td>
											
										</tr>
										[/#list]
									</tbody>
				</table>
					[#assign pagepath="${base}/xyjsmgr/sys/generalcodename/findList?codetablename=${codetablename}&codetabledesc=${codetabledesc}&"]  
			  [#include "common/pagemodel.ftl"]
			</div>
			              
			
		</div>
	</div>
</div>
<script type="text/javascript">
function deletegeneralcode(ele){
	var codetableid=ele.value;
	 bootbox.confirm("确认删除该公共代码?",function (result) {
			if(result==true){
				window.location.href=base + "/xyjsmgr/sys/generalcodename/"+codetableid+"/delete";
			}
		 
  })
}
function forbidden(obj){
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/generalcodename/setgeneralcodename/"+obj.value+"/1",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/generalcodename";
        }
    });
}

function restart(obj){
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/generalcodename/setgeneralcodename/"+obj.value+"/0",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/generalcodename";
        }
    });
}

function flushCache(){
	   $.ajax({
	        type: "GET",
	        url:"${base}/xyjsmgr/sys/generalcodename/flushCache",
	        error: function(request) {
	            bootbox.alert("刷新缓存失败！");
	        },
	        success: function(data) {
	        	if(data=="SUCCESS"){
	                bootbox.alert("刷新缓存成功！");
 	        	}else{
 	        		bootbox.alert("刷新缓存失败！");
 	        	}
	        }
	    });
}
function  flushQianyi(){
	   $.ajax({
	        type: "GET",
	        url:"${base}/xyjsmgr/sys/generalcodename/flushQianyi",
	        error: function(request) {
	            bootbox.alert("迁移数据补全失败！");
	        },
	        success: function(data) {
	        	if(data=="SUCCESS"){
	                bootbox.alert("迁移数据补全成功！");
	        	}else{
	        		bootbox.alert("迁移数据补全失败！");
	        	}
	        }
	    });
	}

 </script>
[/@layout.main]
