[#assign jss=["assets/js/jquery.validate.min.js","/assets/js/sys/role/role.js" ]]
[#import "common/layout.ftl" as layout] 
[@layout.main
modelcode=['sysmgr','rolemgr'] otherjs=jss sername=['系统管理','角色管理']]
<div class="row">
	<div class="col-xs-12">
		<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
		<form class="form-horizontal" role="form" id="formid" action="${base}/xyjsmgr/sys/sysinfo/role/save" method="post">
			[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
						<li>${error.defaultMessage}</li> [/#list]
					</div>
			[/#if]
			[#if role.id??]<input type="hidden" name="id" value="${role.id }">[/#if]
			[#if role.isforbidden??]<input type="hidden" name="isforbidden"
				value="${role.isforbidden }">[/#if]
			<div class="form-group">
			[@spring.bind "role.rolename"/]
				<label class="col-xs-3 control-label no-padding-right" style="text- align: right;"> 角色名 </label>
				<div class="col-xs-5">
					<div class="block input-icon input-icon-right">
						<input type="text" id="rolename" name="rolename" value="${role.rolename }" class="col-xs-12">
					</div>
				</div>
			</div>
			<div class="form-group">
			[@spring.bind "role.rolecode"/]
				<label class="col-xs-3 control-label no-padding-right" style="text- align: right;"> 角色编码 </label>
				<div class="col-xs-5">
					<div class="block input-icon input-icon-right">
						<input type="text" id="rolecode" name="rolecode" value="${role.rolecode }" class="col-xs-12">
					</div>
				</div>
			</div>
			<div class="form-group">
			[@spring.bind "role.description"/]
				<label class="col-xs-3 control-label no-padding-right" style="text- align: right;"> 角色描述 </label>
				<div class="col-xs-5">
					<div class="block input-icon input-icon-right">
						<input type="text" id="description" name="description" value="${role.description }" class="col-xs-12">
					</div>
				</div>
			</div>
			<div class="form-group">
			[@spring.bind "role.mainpage"/]
				<label class="col-xs-3 control-label no-padding-right" style="text- align: right;"> 主页面</label>
				<div class="col-xs-5">
					<div class="block input-icon input-icon-right">
						<input type="text" id="mainpage" name="mainpage" value="${role.mainpage }" class="col-xs-12">
					</div>
				</div>
			</div>

			<div class="clearfix form-actions" style="text-align: center;">
				<button class="btn btn-sm btn-info" type="submit" id="submitid">保 存</button>
				<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
			</div>
		</form>
	</div>
</div>
  
[/@layout.main]
