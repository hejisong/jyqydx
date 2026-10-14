[#assign modelcodes=["sysmgr","servicemgr"]]
[#assign jss=[]]
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=modelcodes otherjs=jss sername=['系统管理','业务管理']]
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<label class="col-xs-12" >
				<a class="btn btn-xs btn-info" href="${base }/xyjsmgr/sys/sysinfo/menu/newfathermenu">添加父类业务</a>
				<input id="isshowforbidden"  type="checkbox" onclick="checkisshowforbidden();" [#if isforbidden??&&isforbidden=="1"]checked[/#if]><span class="lbl"> 显示禁用</span>
			</label>
			<table id="sample-table-1"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5%;"></th>
						<th style="width:10%;">业务名称</th>
						<th style="width:10%;">业务简称</th>
						<th style="width:10%;">业务编码</th>
						<th style="width:25%;">Url</th>
						<th style="width:10%;">排序号</th>
						<th style="width:20%;"></th>
					</tr>
				</thead>
				<tbody>
				[#list menulist as menu]
				[#if !menu.pid??]
					<tr>
						<td><button style="width: 50%" class="btn btn-xs btn-success" name="${menu.id }" fname="${menu.id }" onclick="showchildmenu(this)">+</button></td>
						<td>${menu.menudesc }</td>
						<td>${menu.shortname }</td>
						<td>${menu.menucode }</td>
						<td>${menu.url }</td>
						<td>${menu.orderno }</td>
						<td>
							<a class="btn btn-xs btn-info" href="${base }/xyjsmgr/sys/sysinfo/menu/editfathermenu/${menu.id}">编 辑
							</a>
							[#if menu.isforbidden=="0"]<button class="btn btn-xs btn-danger" onclick="confirmfisforbidden(this);" value="${menu.id }">禁 用
							</button>[/#if][#if menu.isforbidden=="1"]<button class="btn btn-xs btn-warning" onclick="confirmfunforbidden(this);" value="${menu.id }">启 用
							</button>[/#if]
							<a class="btn btn-xs btn-success" href="${base }/xyjsmgr/sys/sysinfo/menu/newchildmenu/${menu.id}">添加子类业务
							</a>
						</td>
					</tr>
					[#list menulist as menu2]
						[#if menu.id==menu2.pid]
						<tr hidden="hidden" name="pid${menu.id }" fname="pid${menu.id }">
							<td style="text-align: right;"><button style="width: 50%" class="btn btn-xs btn-success" name="${menu2.id }" fname="${menu.id }" onclick="showchildmenu(this)">+</button></td>
							<td>${menu2.menudesc }</td>
							<td>${menu2.shortname }</td>
							<td>${menu2.menucode }</td>
							<td>${menu2.url }</td>
							<td>${menu2.orderno }</td>
							<td>
								<a class="btn btn-xs btn-info" href="${base }/xyjsmgr/sys/sysinfo/menu/editchildmenu/${menu2.id}/${menu2.pid}">编 辑
								</a>
								[#if menu2.isforbidden=="0"]<button class="btn btn-xs btn-danger" onclick="confirmcisforbidden(this);" value="${menu2.id }">禁 用
								</button>[/#if][#if menu2.isforbidden=="1"]<button class="btn btn-xs btn-warning" onclick="confirmcunforbidden(this);" value="${menu2.id }">启 用
								</button>[/#if]
								<a class="btn btn-xs btn-success" href="${base }/xyjsmgr/sys/sysinfo/menu/newchildmenu/${menu2.id}">添加子类业务
							</a>
							</td>
						</tr>
						
						[#list menulist as menu3]
						[#if menu2.id==menu3.pid]
						<tr hidden="hidden" name="pid${menu2.id }" fname="pid${menu.id }">
							<td></td>
							<td>${menu3.menudesc }</td>
							<td>${menu3.shortname }</td>
							<td>${menu3.menucode }</td>
							<td>${menu3.url }</td>
							<td>
								[#if menu3.menutype=='1']
									公路
								[#elseif menu3.menutype=='2']
									水运
								[#elseif menu3.menutype=='0']
									其它
								[/#if]
							</td>
							<td>${menu3.orderno }</td>						
							<td>
								<a class="btn btn-xs btn-info" href="${base }/xyjsmgr/sys/sysinfo/menu/editchildmenu/${menu3.id}/${menu3.pid}">编 辑
								</a>
								[#if menu3.isforbidden=="0"]<button class="btn btn-xs btn-danger" onclick="confirmcisforbidden(this);" value="${menu3.id }">禁 用
								</button>[/#if][#if menu3.isforbidden=="1"]<button class="btn btn-xs btn-warning" onclick="confirmcunforbidden(this);" value="${menu3.id }">启 用
								</button>[/#if]
							</td>
						</tr>
						[/#if]
					[/#list]
					
						[/#if]
					[/#list]
					[/#if]
					[/#list]
				</tbody>
			</table>
		</div>
		<!-- /.table-responsive -->
	</div>
	<!-- /span -->
</div>
<script type="text/javascript">
function showchildmenu(ele){
	var fid = $(ele).attr("name");
	var ffid = $(ele).attr("fname");
	var html = "<button style='width: 50%' class='btn btn-xs btn-success' name='" + fid + "' fname='" + ffid + "' onclick='hidechildmenu(this)'>-</button>";
	$(ele).replaceWith(html);
	$("tr[name='pid"+ fid + "']").show();
}

function hidechildmenu(ele){
	var a = ele;
	var fid = $(ele).attr("name");
	var ffid = $(ele).attr("fname");
	var html = "<button style='width: 50%' class='btn btn-xs btn-success' name='" + fid + "' fname='" + ffid + "' onclick='showchildmenu(this)'>+</button>";
	$(ele).replaceWith(html);
	$("tr[name='pid"+ fid + "']").hide();
	$("tr[fname='pid"+ fid + "']").hide();
	$("button[fname='"+ fid + "']").each(function(index, element){
		$(element).attr("onclick","showchildmenu(this)")
		$(element).text("+");
	});
}

function confirmfisforbidden(ele){
	var a = ele;
	var id = a.value;
	$.ajax({
		type:"get",
		url:base+"/xyjsmgr/sys/sysinfo/menu/ischildmenuforbidden/" + id,
		dataType:"text",
		async:false,
		success:function(data){
			if(data=="FAIL"){
				bootbox.alert("请先禁用相关子类业务！");
			}if(data=="SUCCESS"){
				 bootbox.confirm("确认禁用父菜单?",function (result) {
						if(result==true){
							window.location.href= base + "/xyjsmgr/sys/sysinfo/menu/forbidmenu/"+id;
						}
			 		 
			    })
			}
		},
		error:function(){
		   bootbox.alert("报告出错！")
		}
	});
}

function confirmcisforbidden(ele){
	 bootbox.confirm("确认禁用子菜单?",function (result) {
			if(result==true){
				 var a = ele;
					var id = a.value;
		 		   window.location.href= base + "/xyjsmgr/sys/sysinfo/menu/forbidmenu/"+id;
			}
	 		   
	    })
}

function confirmfunforbidden(ele){
	 bootbox.confirm("确认启用菜单?",function (result) {
			if(result==true){
				 var a = ele;
					var id = a.value;
				   window.location.href= base + "/xyjsmgr/sys/sysinfo/menu/unforbidmenu/"+id;
			}
		   
 })
}

function confirmcunforbidden(ele){
	var a = ele;
	var id = a.value;
	$.ajax({
		type:"get",
		url:base+"/xyjsmgr/sys/sysinfo/menu/isfathermenuforbidden/" + id,
		dataType:"text",
		async:true,
		success:function(data){
			if(data=="FAIL"){
				bootbox.alert("请先启用父类业务！");
			}if(data=="SUCCESS"){
				 bootbox.confirm("确认启用菜单?",function (result) {
						if(result==true){
							window.location.href= base + "/xyjsmgr/sys/sysinfo/menu/unforbidmenu/"+id;
						}
					 
			    })
			}
		},
		error:function(){
		   bootbox.alert("报告出错！")
		}
	});
}



function checkisshowforbidden(){
	if($("#isshowforbidden").is(':checked')){
		window.location.href= base + "/xyjsmgr/sys/sysinfo/menu?isforbidden=1";
	}else{
		window.location.href= base + "/xyjsmgr/sys/sysinfo/menu";
	}
}
</script>
[/@layout.main]
