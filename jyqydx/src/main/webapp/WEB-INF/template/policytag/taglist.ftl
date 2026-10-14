[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main sername=['政策管理','推荐标签信息'] modelcode=["qyzc","tag"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 search-bg" style="border: 1px solid #e5e5e5;padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
		<form class="form-horizontal" id="tagform"  action="${base}/policytag/taglist" method="get">
			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">标签名称</label>
			<div class="col-xs-3" style="margin-bottom: 20px;">
				<input type="text" id="tagname" name="tagname" value="${tagname}" class="col-xs-12">
			</div>




			<div class="col-xs-3" style="text-align: center;">
			      <button  class="btn btn-xs btn-info btn-width" type="submit">搜  索</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="opentag();">新 增</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>

		</form>

  </div>


	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:5%;">序号</th>
						<th style="width:57%;">标签名称</th>
						<th style="width:40%;">操作</th>

					</tr>
				</thead>
				<tbody>
				[#list page.content as tag ]
					<tr >
						<td>${tag_index+1}</td>
						<td>${tag.tagname}</td>

						<td>
							<a  href="javascript:void(0);" onclick="opentag('${tag.tagid}','${tag.tagname}')">编辑</a>
[#--							<a  style="color: red;" onclick="deleteinfo('${tag.tagid}');" href="javascript:void(0);">删除</a>--]
							[#if tag.state=="1"]
								<a  style="color: red;" onclick="stopinfo('${tag.tagid}');" href="javascript:void(0);">停用</a>
							[#else]
								<a  style="color: blue;" onclick="openinfo('${tag.tagid}');" href="javascript:void(0);">启用</a>
							[/#if]


						</td>

					</tr>
				[/#list]

				</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/policytag/taglist?tagname=${tagname}&"]
		[#include "common/pagemodel.ftl"]
	</div>


</div>

	[#include "policytag/t_tag.ftl"]
<script type="text/javascript">


	function resetinfo() {
		$("#tagname").val('');
		$("#tagform").submit();
	}

	function saveinfo() {
		// $("#forminfo").submit();
		// initinfo();
		bootbox.confirm("确认保存?",function (result) {
			if(result==true){
				$("#loading_state").val("1");
				$.ajax({
					type:"post",
					url:base + "/policytag/savetag",
					async:true,
					data: $("#tagform").serialize(),
					success:function(data){
						if(data=="SUCCESS"){
							bootbox.confirm("保存成功！",function (result) {
								window.location.reload();
							})

						}else{
							bootbox.alert("保存失败！");
						}
					},
					error:function(){
						bootbox.alert("数据加载失败");
					}
				});
			}

		})


	}

function deleteinfo(tagid) {
	bootbox.confirm("确认删除标签以及相关信息?",function (result) {
		if(result==true){
			window.location.href=base + "/policytag/deletetag/"+tagid;
		}

	})
}

	function openinfo(tagid) {
		bootbox.confirm("确认启用标签以及相关信息?",function (result) {
			if(result==true){
				window.location.href=base + "/policytag/opentag/"+tagid;
			}

		})
	}

	function stopinfo(tagid) {
		bootbox.confirm("确认停用标签以及相关信息?",function (result) {
			if(result==true){
				window.location.href=base + "/policytag/stoptag/"+tagid;
			}

		})
	}



</script>
[/@layout.main]