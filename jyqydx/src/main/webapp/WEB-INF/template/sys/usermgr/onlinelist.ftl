[#assign jss=["assets/js/validate.js"]]
[#assign css=["assets/css/zhemutanchukuang.css"]] 
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['系统管理','在线用户'] modelcode=["sysmgr","OnlineUser"] otherjs=jss othercss=css]
 [#include "common/loading.ftl"]
	<div class="row">
		<div class="col-xs-12">
			<div class="table-responsive">
				<form name="findlist" id="infoform" action="${base}/sys/onlineuser/list" method="get">
					<div class="form-group flex">
						<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">登录名</label>
						<div class="col-xs-2">
							<input type="text" id="loginname" name="loginname" value="${loginname }" class="col-xs-12">
						</div>
						<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">机构名称</label>
						<div class="col-xs-2">
							<input type="text" id="organname" name="organname" value="${organname }" class="col-xs-12">

						</div>

						<button class="btn btn-xs btn-info btn-width" type="submit" style="margin-left: 50px;">查 询 <i class="m-icon-swapright m-icon-white"></i></button>

						<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>

                        <button class="btn btn-xs btn-info btn-width" type="button" style="margin-left: 20px;" onclick="refrlash();">刷 新 <i class="m-icon-swapright m-icon-white"></i></button>

					</div>
				</form>
				<table id="sample-table-1"
					   class="table table-striped table-bordered table-hover">
					<thead>
					<tr>
						<th style="width: 5%;">序号</th>
						<th style="width: 10%;">登录名</th>
						<th style="width: 10%;">用户名</th>
						<th style="width: 15%;">所属机构</th>
						<th style="width: 15%;">IP地址</th>
						<th style="width: 15%;">登录时间</th>
						<th style="width: 15%;">最后访问时间</th>
						<th style="width: 15%;">访问页面</th>
					</tr>
					</thead>
					<tbody>
					[#list page.content  as user]
							<tr>
								<td>${user_index+1}</td>
								<td>${user.loginname }</td>
								<td>${user.username }</td>
								<td>${user.unitname }</td>
								<td>${user.loginlog.loginip}</td>
								<td>[#if user.loginlog??&&user.loginlog!=null ]${(user.loginlog.logintime?string('yyyy-MM-dd HH:mm:ss')) }[/#if]</td>
								<td>${(user.lastloginlog.logintime?string('yyyy-MM-dd HH:mm:ss')) }</td>
								<td>${user.lastloginlog.url}</td>

							</tr>
					[/#list]
					</tbody>
				</table>
			</div>

            [#assign pagepath="${base}/sys/onlineuser/list?loginname=${loginname}&organname=${organname}&"]
            [#include "common/pagemodel.ftl"]
		</div>
	</div>


	<script type="text/javascript">

		function resetinfo() {
			$("#loginname").val('');
			$("#organname").val('');
			$("#infoform").submit();
		}

        function refrlash() {
            window.location.reload();
        }
        
        
		function deleteOrg(id){
			bootbox.confirm("确认删除该机构?",function (result) {
				if(result==true){
					window.location.href=base + "/sys/organinfo/delete/"+id;
				}

			})
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
