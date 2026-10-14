[#assign modelcodes=["sysmgr","servicemgr"]]
[#assign jss=[]] [#import
"common/layout.ftl" as layout] [@layout.main modelcode=modelcodes
otherjs=jss sername=['系统管理','业务管理']]
<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" role="form" [#if menu.id??]action="${base }/xyjsmgr/sys/sysinfo/menu/update"[#else]
		action="${base }/xyjsmgr/sys/sysinfo/menu/save"[/#if] method="post">
		<input type="hidden" name="isforbidden" [#if menu.isforbidden??]value="${menu.isforbidden }"[#else]value="0"[/#if]>
		[#if menu.id??]<input type="hidden" name="id" value="${menu.id }">[/#if]
		[#if fmenu??]<input type="hidden" name="pid" value="${fmenu.id }">
			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;">父菜单 </label>
				<div class="col-xs-5">
					<input type="text" value="${fmenu.menudesc }" readonly="readonly" class="col-xs-12">
				</div>
			</div>
		[/#if]
			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;"> 业务名称 </label>
				<div class="col-xs-5">
					<input type="text" id="menudesc" name="menudesc" value="${menu.menudesc }" class="col-xs-12">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;"> 业务简称 </label>
				<div class="col-xs-5">
					<input type="text" id="shortname" name="shortname" value="${menu.shortname }" class="col-xs-12">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;"> 业务编码 </label>
				<div class="col-xs-5">
					<input type="text" id="menucode" name="menucode" value="${menu.menucode }"
						class="col-xs-12">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;"> URL </label>

				<div class="col-xs-5">
					<input type="text" id="url" name="url" value="${menu.url }"
						class="col-xs-12">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label no-padding-right" style="text-align:right;"> 顺序 </label>
				<div class="col-xs-5">
					<input type="text" id="orderno" name="orderno" value="${menu.orderno }"
						class="col-xs-12">
				</div>
			</div>
			<div class="clearfix form-actions" style="text-align:center;">
					<button class="btn btn-sm btn-info" type="submit">保 存</button>
					<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
			</div>
		</form>
	</div>
</div>
[/@layout.main]
