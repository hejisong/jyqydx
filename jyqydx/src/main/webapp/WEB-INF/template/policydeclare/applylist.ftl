
[#include "common/head.ftl"]
<body style="margin: 0">

<div style="width: 100%;margin-top: -15px;background-color: #f8f4fd;">

	<div style="width: 70%;margin-left: 17%;padding-top: 20px;">
	<form class="form-horizontal"  action="${base}/policydeclare/applylist/${id}" method="get">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="companyid" value="${companyid}">
		<div class="col-xs-12">
		<label class="col-xs-2 control-label no-padding-right" style="text-align:right;padding-top: 6px;">政策文件</label>
		<div class="col-xs-9">

			<select class="form-control input-inline" name="plfidid" style="height: 28px;padding: 2px" onchange="initDl(this);">
				<option value="" [#if plfidid==''] selected [/#if]>全部</option>
				[#list policyfiles as policyfile]
				<option value="${policyfile.plfidid}" [#if plfidid==policyfile.plfidid] selected [/#if]>${policyfile.policyname}</option>
				[/#list]
			</select>
		</div>
		</div>
		<div class="col-xs-12">
		<label class="col-xs-2 control-label no-padding-right" style="text-align:right;margin-top: 10px;">政策大类</label>
		<div class="col-xs-4" style="margin-top: 10px;">

			<select class="form-control input-inline" name="dl" id="dl" style="height: 28px;padding: 2px" onchange="initXl(this);">
				<option value="" [#if dl==''] selected [/#if]>全部</option>
				[#list dls as d]
					<option value="${d}" [#if dl==d] selected [/#if]>${d}</option>
				[/#list]
			</select>
		</div>

		<label class="col-xs-1 control-label no-padding-right" style="text-align:right;padding-top: 6px;margin-top: 10px;">关键字</label>
		<div class="col-xs-4" style="margin-top: 10px;">
			<input type="text" id="key" name="key" value="${key}" class="col-xs-12">
		</div>
		<div class="col-xs-1" style="margin-top: 10px;" >
			<button style="margin-left: 15px;" class="btn btn-xs btn-info btn-width" type="submit">搜  索</button>
		</div>
		</div>
	</form>


</div>
</br>
<div style="width: 80%;margin-left: 12%;padding-top: 72px;">
	<p style="text-align: center;font-size: 28px;font-weight: 600;">奖励政策明细</p>
	<table id="sample-table-1" class="table table-striped table-bordered table-hover" style="background: whitesmoke;">
		<thead>
		<tr>

			<th style="width:5%;">序号</th>
			<th style="width:24%;">政策类别</th>
			<th style="width:27%;">政策条件</th>
			<th style="width:27%;">奖励描述</th>
			<th style="width:10%;">奖励金额</th>
			<th style="width:8%;">操作</th>
		</tr>
		</thead>
		<tbody>
		[#list alldetails as detail ]
			<tr >
				[#--<td>${detail_index+1}</td>
				<td>${detail.nplcategory}[#if detail.plsubclassname??&&detail.plsubclassname!=''] (${detail.plsubclassname}) [/#if]</td>
				<td>${detail.plconditions}</td>
				<td>${detail.rdescription}</td>
				<td>${detail.ramount}</td>
				<td>
					<a  href="javascript:void(0);"  onclick="apply('${detail.pldtailid}');">申报</a>
				</td>--]
				<td>${detail_index+1}</td>
				<td style="text-align: left;">${detail[1]}[#if detail[2]??&&detail[2]!=''] (${detail[2]}) [/#if]</td>
				<td>${detail[3]}</td>
				<td>${detail[4]}</td>
				<td>${detail[5]}</td>
				<td>
					[#if detail[6]=='1']
						已申报
					[#else]
					<a  href="javascript:void(0);"  onclick="apply('${detail[0]}');">申报</a>

					[/#if]
				</td>
			</tr>
		[/#list]
		</tbody>
	</table>
</div>



<div  style="text-align: center;padding-bottom: 20px;">
	<button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="returninfo();">返 回</button>
</div>
</div>

[#include "common/foot.ftl"]
</body>

<script type="text/javascript">


	function returninfo() {
		window.location.href=base + "/policydeclare";
	}

function apply(pldtailid) {
	var companyid=$("#companyid").val();

	if(companyid!=undefined&&companyid!=null&&companyid!=""){
		//查询是否有权限
		if(isauthor()){
			window.location.href=base + "/policyfilemgr/apply/"+pldtailid+"/"+companyid;
			//查询该政策是否已经申报过了
			/*$.ajax({
				url : base + "/policyfilemgr/applycheck",
				type : "POST",
				async : false,
				datatype : 'json',
				data : {pldtailid:pldtailid,companyid:companyid},
				success : function(data) {
					if(data=="SUCCESS"){
						bootbox.confirm("确认申报?",function (result) {
							if(result==true){
								window.location.href=base + "/policyfilemgr/apply/"+pldtailid+"/"+companyid;
							}

						})
					}else{
						bootbox.alert("该政策已匹配，请勿重复申报！");
					}
				},
				error : function() {
					bootbox.alert("数据加载失败！");
				}
			});*/
		}else{
			bootbox.alert("当前登录人没有权限操作，请先授权再操作！");
		}


	}else{
		bootbox.alert("请先登录再申报！");
	}



}

	function isauthor() {
		var usercompanyid=$("#usercompanyid").val();
		var isauther=false;
		$.ajax({
			url : base + "/isauthor",
			type : "POST",
			async : false,
			datatype : 'json',
			data : {usercompanyid:usercompanyid},
			success : function(data) {
				if(data=="0"||data=='1'){
					isauther=true;
					return isauther;
				}else{
					return isauther;
				}
			},
			error : function() {
				bootbox.alert("数据加载失败！");
			}
		});
		return isauther;
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
	var xls=data[1];
	var htmldl='<option value="" [#if dl==""] selected [/#if]>全部</option>';
	var htmlxl='<option value="" [#if xl==""] selected [/#if]>全部</option>';
	$.each(dls,function(i,dl){
		htmldl+='<option value="'+dl+'" >'+dl+'</option>';


	});

	$.each(xls,function(i,xl){
		if(i==0){
			htmlxl+='<option value="'+xl+'"  selected >'+xl+'</option>';
		}else{
			htmlxl+='<option value="'+xl+'"  >'+xl+'</option>';
		}


	});

	$("#dl").html(htmldl);
	$("#xl").html(htmlxl);

}


function initXl(ele) {
	var dl=$(ele).val();
	var plfidid=$("#plfidid").val();
	$.ajax({
		type:"POST",
		url:base+"/policydeclare/queryxl" ,
		data:{plfidid:plfidid,dl:dl},
		dataType:"json",
		success:function(data){
			initXiaoLei(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});
}


function initXiaoLei(data) {
	var xls=data;
	var htmlxl='<option value="" [#if xl==""] selected [/#if]>全部</option>';
	$.each(xls,function(i,xl){
		if(i==0){
			htmlxl+='<option value="'+xl+'"  selected >'+xl+'</option>';
		}else{
			htmlxl+='<option value="'+xl+'"  >'+xl+'</option>';
		}


	});
	$("#xl").html(htmlxl);

}


</script>
</html>