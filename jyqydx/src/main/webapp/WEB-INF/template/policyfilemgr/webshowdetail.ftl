
[#include "common/head.ftl"]
<body style="margin: 0">
<div style="margin-top: -15px;background-color: #f8f4fd;">

<div style="width: 70%;margin-left: 17%;padding-top: 10px;">

	<div style="margin-top: 10px;">
		<p style="text-align: center;font-size: 20px;font-weight: 600;">${policyfile.policyname}</p>

		<p style="font-size: 18px;">
		    <span >政策级别：[#if policyfile.filelevel=='1']市级[#else]区级[/#if] </span>
			<span style="position: absolute;left: 800px;" >生效日期：${policyfile.releasetime}</span>
		</p>
		<p style="font-size: 18px;">
			<span>文号：${policyfile.fwtitanic} </span>
		    <span style="position: absolute;left: 800px;" >发文单位：${policyfile.fwunits}</span>
		</p>

		<p style="font-size: 18px;">
			<span>政策类型：
				 <input name="plfidid" id="plfidid"  type="hidden" value="${policyfile.plfidid}">
				 <input name="tagid" id="tagid"  type="hidden" value="${tagid}">
				 <input name="key" id="key"  type="hidden" value="${key}">
                <a style="padding-top: 4px;font-size: 18px;margin-left: 10px;text-decoration: none;color: [#if tagid=='']#6096EC[#else] black[/#if];" href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/2?tagid=">全部</a>
				[#list tags as tag]
					[#if tagidstrs??][#assign ishas=0][#list  tagidstrs as tid][#if tag[0]==tid]  [#assign ishas=1] [/#if][/#list][/#if]

					<a style="padding-top: 4px;font-size: 18px;margin-left: 10px;text-decoration: none;cursor:pointer;color: [#if ishas=1]#6096EC[#else] black[/#if];"  id="[#if ishas=1]1[#else] 0[/#if]" onclick="taginfo('${tag[0]}',this);" >${tag[1]}</a>[/#list] </span>
		</p>
	</div>

	<div style="background: #0a6aa1;height: 60px;padding: 15px 0px 0px 10px;margin-top: 8px;">
		<a style="color: [#if querytype=='1']black [#else] white[/#if];font-size: 18px;text-decoration: none;" href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/1?key=${key}">政策原文</a>

		<a style="color: [#if querytype=='2']black [#else] white[/#if];margin-left: 20px;font-size: 18px;text-decoration: none;"  href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/2?key=${key}&companyid=${logincompany.companyid}">奖励明细</a>

		<a style="color:  white;margin-left: 20px;font-size: 18px;text-decoration: none;"  href="${base}/newpolicy">返回</a>

	</div>


</div>
<div style="width: 70%;margin-left: 17%;background: whitesmoke;min-height: 380px;">
	<p style="text-align: center;font-size: 28px;font-weight: 600;">奖励政策明细</p>
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
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
		[#list details as detail ]
			<tr >

				<td>${detail_index+1}</td>
				<td>${detail.nplcategorystr}[#if detail.plsubclassname??&&detail.plsubclassname!=''] (${detail.plsubclassnamestr}) [/#if]</td>
				<td>${detail.plconditionsstr}</td>
				<td>${detail.rdescriptionstr}</td>
				<td>${detail.ramount}</td>

				<td>
					[#if detail.sbstate=='1' ]
					已申报
					[#else]
					<a  href="javascript:void(0);"  onclick="apply('${detail.pldtailid}');">申报</a>
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
						bootbox.alert("该政策已申报，请勿重复申报！");
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




function returninfo() {
	window.location.href=base + "/newpolicy";
}

function taginfo(tagid,e) {
	var tag=$("#tagid").val();
	var ischecked=$(e).attr("id");
	if(ischecked=="1"){
		$(e).attr("id","0");
		var tagids=tagid+",";
		tag=tag.replace(tagids,"");
		$(e).attr("color","balck");
	}else{
		$(e).attr("id","1");
		$(e).attr("color","#6096EC");
		tag+=tagid+",";
	}

	$("#tagid").val(tag);
	var plfidid=$("#plfidid").val();
	var key=$("#key").val();
	var tagid=$("#tagid").val();
	window.location.href=base +"/policyfilemgr/webshow/"+plfidid+"/2?key="+key+"&tagid="+tagid;
}


</script>
</html>