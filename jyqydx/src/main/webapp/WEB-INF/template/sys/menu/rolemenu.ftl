[#assign modelcodes=['sysmgr','rolemgr']]
[#assign jss=[]]
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=modelcodes otherjs=jss sername=['系统管理','角色管理']]
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<div class="form-actions">
			<button class="btn btn-sm btn-info btn-width" type="button" onclick="menuconfirm();">提 交</button>
			<a class="btn btn-sm" type="button" href="${base}/xyjsmgr/sys/sysinfo/role?roletype=${role.roletype}">返回角色管理</a>
		</div>
		<span style="font-size:13px;color:red;">角色名称：${role.rolename},角色编码;${role.rolecode}</span>
		<form action="${base }/xyjsmgr/sys/sysinfo/menu" method="post" id="rolemenuform">
		<input name="roleid" value="${roleid }" type="hidden" id="roleid">
			<table id="sample-table-1"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center" style="width:5%;"></th>
						<th style="width:20%;">功能描述</th>
						<th style="width:20%;">菜单简称</th>
						<th style="width:15%;">菜单编码</th>
						<th style="width:25%;">Url</th>
						<th style="width:7%;">序号</th>
						<th style="width:8%;"></th>
					</tr>
				</thead>
				<tbody>
				[#list menulist as menu]
				[#if !menu.pid??]
					<tr>
						<td style="text-align: left;">
							<button style="width: 50%" class="btn btn-xs btn-success" name="${menu.id }" fname="${menu.id }" onclick="showchildmenu(this)">+</button>
						</td>
						<td>${menu.menudesc }</td>
						<td>${menu.shortname }</td>
						<td>${menu.menucode }</td>
						<td>${menu.url }</td>
						<td>${menu.orderno }</td>
						<td>
						<input  id="fmenuselect${menu.id }${menu_index+1}" onclick="checkChildmenu(this);" name="menuid" value="${menu.id }" type="checkbox"  [#list menuexidlist as menuex][#if menuex.id==menu.id]checked[/#if][/#list]>
						</td>
					</tr>
					[#list menulist as menu2]
						[#if menu.id==menu2.pid]
						<tr  name="pid${menu.id }" hidden="hidden" fname="pid${menu.id }">
							<td style="text-align: right;">
							<button style="width: 50%;" class="btn btn-xs btn-success" name="${menu2.id }" fname="${menu.id }"  onclick="showchildmenu(this)">+</button>
							</td>
							<td>${menu2.menudesc }</td>
							<td>${menu2.shortname }</td>
							<td>${menu2.menucode }</td>
							<td>${menu2.url }</td>
							<td>${menu2.orderno }</td>
							<td>
								<input id="cmenuselect${menu2.id }${menu2_index+1}" fid="${menu.id }" onclick="checkFatherAndChildmenu(this);" name="menuid" value="${menu2.id }" type="checkbox"  [#list menuexidlist as menuex][#if menuex.id==menu2.id]checked[/#if][/#list]>
								<input type="hidden" value="${menu2.pid }">
							</td>
						</tr>
						
						[#list menulist as menu3]
						[#if menu2.id==menu3.pid]
						<tr name="pid${menu2.id }" hidden="hidden" fname="pid${menu.id }">
							<td class="center"></td>
							<td>${menu3.menudesc }</td>
							<td>${menu3.shortname }</td>
							<td>${menu3.menucode }</td>
							<td>${menu3.url }</td>
							<td>${menu3.orderno }</td>
							<td>
								<input id="ccmenuselect${menu3.id }${menu3_index+1}" fid="${menu.id }" fcid="${menu2.id }" onclick="checkFathermenu(this);" name="menuid" value="${menu3.id }" type="checkbox"  [#list menuexidlist as menuex][#if menuex.id==menu3.id]checked[/#if][/#list]>
								<input type="hidden" value="${menu3.pid }">
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
			</form>
		</div>
		<!-- /.table-responsive -->
	</div>
	<!-- /span -->
</div>
<!-- /row -->
<script type="text/javascript">
function showchildmenu(ele){
	var fid = $(ele).attr("name");
	var ffid = $(ele).attr("fname");
	var html = "<button style='width: 50%' class='btn btn-xs btn-success' name='" + fid + "' fname='" + ffid + "' onclick='hidechildmenu(this)'>-</button>";
	$(ele).replaceWith(html);
	$("tr[name='pid"+ fid + "']").show();

}

function hidechildmenu(ele){
	var fid = $(ele).attr("name");
	var ffid = $(ele).attr("fname");
	var html = "<button style='width: 50%' class='btn btn-xs btn-success' name='" + fid + "' fname='" + ffid + "' onclick='showchildmenu(this)'>+</i></button>";
	$(ele).replaceWith(html);
	$("tr[name='pid"+ fid + "']").hide();
	$("tr[fname='pid"+ fid + "']").hide();
	$("button[fname='"+ fid + "']").each(function(index, element){
		$(element).attr("onclick","showchildmenu(this)")
		$(element).text("+");
	});
}

function checkChildmenu(ele){
	var fid = ele.value;
	var elements=$("input[fid^='"+ fid + "']");
	if(ele.checked==false){
	/*	$("input[fid^='"+ fid + "']").removeAttr("checked");*/

		for(var i=0; i<elements.length; i++){
			elements[i].checked=false;
		}

	}else{
		$("input[fid^='"+ fid + "']").attr("checked",true);
	}
}

function checkFathermenu(ele){
	if(ele.checked==true){
		var fid = $(ele).next().val();
		var ffid = $(ele).attr("fid");
		$("input[id^='fmenuselect"+ ffid + "']").attr("checked",'true');
		$("input[id^='cmenuselect"+ fid + "']").attr("checked",'true');
	}
}

function checkFatherAndChildmenu(ele){
	if(ele.checked==false){
		var fcid = ele.value;
		$("input[fcid^='"+ fcid + "']").removeAttr("checked");
	}
	if(ele.checked==true){
		var fid = $(ele).next().val();
		$("input[id^='fmenuselect"+ fid + "']").attr("checked",'true');
	}
}

function menuconfirm(){
	var formflag=true;
	if(formflag){
		formflag=false;
		$("#rolemenuform").submit();
	}
}
</script>
[/@layout.main]
