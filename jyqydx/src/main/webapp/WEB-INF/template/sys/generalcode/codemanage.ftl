[#assign jss=[]] 
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['sysmgr','generalcodemgr'] otherjs=jss sername=['系统管理','数据字典']]



<div class="portlet box blue-madison">
	<div class="portlet-title">
		<input type="hidden" id="codetableid" name="codetableid" value="${generalcodename.codetableid}"/>
		<div class="col-xs-3">
			<a class="btn btn-xs btn-info" data-toggle="modal" style="margin-bottom: 10px" href="${base}/xyjsmgr/sys/generalcodename/newgeneralcode/${generalcodename.codetableid}">添加新代码
			</a>
			<a class="btn btn-xs btn-info btn-width" data-toggle="modal" style="margin-bottom: 10px" href="${base}/xyjsmgr/sys/generalcodename">返 回
			</a>
		</div>
	</div>
	
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th></th>
							<th>类别名称</th>
							<th>代码名称</th>
							<th>代码</th>
							<th>更新时间</th>
							<th>操作</th>						
						</tr>
					</thead>
					<tbody>
										[#list page.content as per]
										<tr [#if per_index%2==0]class= "gradeX
											odd"[#else]class="gradeX even" [/#if] role="row">
											 <td class="sorting_1">${per_index+1}</td>
											<td>${per.generalcodename.codetablename }</td>
											<td>${per.codedesc }</td>
											<td>${per.ucode }</td>									
											<td>${(per.updatedate?string('yyyy-MM-dd hh :mm:ss')) }</td>
											<td><a class="btn btn-xs btn-info" 
											href="${base}/xyjsmgr/sys/generalcodename/editgeneralcode/${generalcodename.codetableid}/${per.codeid}"><i class="fa fa-edit"></i>编 辑</a> 
											<button class="btn btn-xs btn-danger"  onclick="deletegeneralcode(this);" value="${per.codeid}">删 除</button>
											[#if per.isdisabled=="0"]<button class="btn btn-xs btn-warning" onclick="forbidden(this)" value="${per.codeid}">禁 用</button>
								            [#else]<button class="btn btn-xs btn-success" onclick="restart(this)" value="${per.codeid}">启 用</button>
								            [/#if]
										</tr>
										[/#list]
									</tbody>
				</table>
	
	
						
					          
				</div>
				
<script type="text/javascript">
	
	function deletegeneralcode(ele){
		var codetableid=$("#codetableid").val();
		var codeid=ele.value;
		 bootbox.confirm("确认删除该公共代码?",function (result) {
				if(result==true){
					window.location.href=base + "/xyjsmgr/sys/generalcodename/"+codetableid+"/deletegeneralcode/"+codeid;
				}
			 
	})
}

function forbidden(obj){
	var codetableid=$("#codetableid").val();
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/generalcodename/setgeneralcode/"+codetableid+"/"+obj.value+"/1",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/generalcodename/manage/"+codetableid;
        }
    });
}

function restart(obj){
	var codetableid=$("#codetableid").val();
	$.ajax({
        type: "POST",
        url:"${base}/xyjsmgr/sys/generalcodename/setgeneralcode/"+codetableid+"/"+obj.value+"/0",
        error: function(request) {
            bootbox.alert("Connection error");
        },
        success: function(data) {
        	window.location.href="${base}/xyjsmgr/sys/generalcodename/manage/"+codetableid;
        }
    });
}
</script>
[/@layout.main]
