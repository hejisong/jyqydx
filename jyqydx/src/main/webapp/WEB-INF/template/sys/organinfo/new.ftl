[#assign jss=["assets/js/jquery.validate.min.js","/assets/js/sys/organinfo/organinfo.js" ]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css"]]
[@layout.main sername=['系统管理','机构管理'] modelcode=["sysmgr","OrganManage"] otherjs=jss othercss=css]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/sys/organinfo/save" method="post">
				[#if valid_error??]
					<div class="alert alert-danger display-hide" style="display: block;">
						<button class="close" data-close="alert"></button>
						[#list valid_error as error]
							<li>${error.defaultMessage}</li> [/#list]
					</div>
				[/#if]
				[#if organinfo.id??]<input type="hidden" name="id" value="${organinfo.id }">[/#if]
				
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构名称 </label>
					<div class="col-xs-5">
						<div class="block input-icon input-icon-right">
							<input type="text" id="orgname" name="orgname" value="${organinfo.orgname }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 机构代码 </label>
					<div class="col-xs-5">
						<div class="block input-icon input-icon-right">
							<input type="text" id="orgcode" name="orgcode" value="${organinfo.orgcode }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 联系人 </label>
					<div class="col-xs-5">
						<div class="block input-icon input-icon-right">
							<input type="text" id="linkman" name="linkman" value="${organinfo.linkman }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 联系方式</label>
					<div class="col-xs-5">
						<div class="block input-icon input-icon-right">
							<input type="text" id="linkphone" name="linkphone" value="${organinfo.linkphone }" class="col-xs-12">
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
<script type="text/javascript">


</script>
[/@layout.main]