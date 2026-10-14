[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/validate.js","assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策管理','政策精准性标签管理'] modelcode=["qyzc","policyaccuratetag"] otherjs=jss othercss=css]
  <div class="row">
		<form class="form-horizontal" id="accuratetagform"   action="${base}/accuratetag/policylist" method="get">

			<div class="col-xs-12 search-bg">
				<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策级别</label>
				<div class="col-xs-1" style="margin-bottom: 20px;">
					<select class="form-control input-inline" name="flevel" id="flevel" style="height: 28px;padding: 2px" onchange="initfilelevel(this);">
						<option value="" [#if flevel==''] selected [/#if]>全部</option>
						<option value="1" [#if flevel=='1'] selected [/#if]>市级</option>
						<option value="2" [#if flevel=='2'] selected [/#if]>区级</option>
					</select>
				</div>


			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策文件</label>
			<div class="col-xs-3" style="margin-bottom: 20px;">
				<select class="form-control input-inline" name="plfidid" id="plfidid"   style="height: 28px;padding: 2px" onchange="initDl(this);">
					<option value="" [#if plfidid==''] selected [/#if]>全部</option>
					[#list policyfiles as policyfile]
						<option value="${policyfile.plfidid}" [#if plfidid==policyfile.plfidid] selected [/#if]>${policyfile.policyname}</option>
					[/#list]
				</select>
			</div>


			<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策大类</label>
			<div class="col-xs-2">
				<select class="form-control input-inline" name="dl" id="dl" style="height: 28px;padding: 2px" >
					<option value="" [#if dl==''] selected [/#if]>全部</option>
					[#list dls as d]
						<option value="${d}" [#if dl==d] selected [/#if]>${d}</option>
					[/#list]
				</select>
			</div>

			<div class="col-xs-3" style="text-align: center;">
			      <button  class="btn btn-xs btn-info btn-width" type="submit">查 询</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="saveinfo();">保 存</button>
				<button style="margin-left: 20px;" class="btn btn-xs btn-info btn-width" type="button" onclick="resetinfo()">重 置</button>
			</div>

			</div>
		</form>

	  <form class="form-horizontal" id="tagform"  action="${base}/accuratetag/policylist" method="get">

			<div class="col-xs-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>

								<th style="width:5%;">序号</th>
								<th style="width:10%;">政策大类</th>
								<th style="width:10%;">政策小类</th>
								<th style="width:20%;">政策条件</th>
								<th style="width:55%;">政策标签</th>

							</tr>
						</thead>
						<tbody id="taginfo">
						[#list page.content as detail ]
							<tr >
								<td>${detail_index+1}</td>
								<td>${detail[2]}</td>
								<td>${detail[3]}</td>
								<td>${detail[4]}</td>
								<td style="text-align: left;"><input type="hidden" value="${detail[0]}" name="details[${detail_index}].pldtailid">
									[#list tags as tag]
										<input style="font-size: 15px;margin-left: 5px;" name="details[${detail_index}].atags[${tag_index}].tagid" type="checkbox" [#list detail[5] as ptag] [#if ptag.tagid=tag.tagid]checked [/#if] [/#list]  value="${tag.tagid}-${tag.tagname}" >${tag.tagname}
									[/#list]
								</td>

							</tr>
						[/#list]

						</tbody>
			</table>
		</div>
		[#assign pagepath="${base}/accuratetag/policylist?plfidid=${plfidid}&dl=${dl}&"]
		[#include "common/pagemodel.ftl"]
	</div>

	  </form>


</div>
<script type="text/javascript">

	function resetinfo() {
		$("#flevel").val('');
		$("#plfidid").val('');
		$("#dl").val('');
		$("#accuratetagform").submit();

	}

	function saveinfo() {
		// $("#forminfo").submit();
		// initinfo();

		bootbox.confirm("确认保存?",function (result) {
			if(result==true){
				$("#loading_state").val("1");
				$.ajax({
					type:"post",
					url:base + "/accuratetag/savepolicytag",
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


	function initfilelevel(ele) {
		var filelevel=$(ele).val();
		$.ajax({
			type:"POST",
			url:base+"/policydeclare/queryf" ,
			data:{filelevel:filelevel},
			dataType:"json",
			success:function(data){
				initFile(data);
			},
			error:function(){
				bootbox.alert("数据加载失败");
			}
		});
	}


	function initFile(data) {
		var files=data[0];
		var dls=data[1];
		var htmlf='<option value="" [#if filelevel==""] selected [/#if]>全部</option>';
		var htmldl='<option value="" [#if dl==""] selected [/#if]>全部</option>';
		$.each(files,function(i,file){
			htmlf+='<option value="'+file.plfidid+'" >'+file.policyname+'</option>';
		});
		$.each(dls,function(i,dl){
			htmldl+='<option value="'+dl+'" >'+dl+'</option>';
		});
		$("#dl").html(htmldl);
		$("#plfidid").html(htmlf);
	}


	function initDl(ele) {
		var plfidid=$(ele).val();
		$.ajax({
			type:"POST",
			url:base+"/policydeclare/querydl" ,
			data:{plfidid:plfidid},
			dataType:"json",
			success:function(data){
				initDaLei(data);
			},
			error:function(){
				bootbox.alert("数据加载失败");
			}
		});
	}


	function initDaLei(data) {
		var dls=data[0];
		var htmldl='<option value="" [#if dl==""] selected [/#if]>全部</option>';
		$.each(dls,function(i,dl){
			htmldl+='<option value="'+dl+'" >'+dl+'</option>';
		});
		$("#dl").html(htmldl);

	}






</script>
[/@layout.main]