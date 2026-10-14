

[#include "common/head.ftl"]
<body style="margin: 0">

<div style="width: 100%;margin-top: -15px;background-color: #f8f4fd;">

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
			<span>政策类型：<a style="padding-top: 4px;font-size: 18px;margin-left: 10px;text-decoration: none;color: [#if tagid=='']#6096EC;[#else] black[/#if];" href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/2?tagid=">全部</a>
				[#list tags as tag]<a style="padding-top: 4px;font-size: 18px;margin-left: 10px;text-decoration: none;color: [#if tagid==tag[0]]#6096EC;[#else] black[/#if];" href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/2?key=${key}&tagid=${tag[0]}">${tag[1]}</a>[/#list] </span>
		</p>
	</div>

	<div style="background: #0a6aa1;height: 60px;padding: 15px 0px 0px 10px;margin-top: 8px;">
		<a style="color:[#if querytype=='1']black [#else] white[/#if];font-size: 18px;text-decoration: none;" href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/1?key=${key}&tagid=${tagid}">政策原文</a>

		<a style="color: [#if querytype=='2']black [#else] white[/#if];margin-left: 20px;font-size: 18px;text-decoration: none;"  href="${base}/policyfilemgr/webshow/${policyfile.plfidid}/2?key=${key}&tagid=${tagid}&companyid=${logincompany.companyid}">奖励明细</a>

		<a style="color:  white;margin-left: 20px;font-size: 18px;text-decoration: none;"  href="${base}/newpolicy">返回</a>

	</div>

	<div style="background: whitesmoke;padding: 0px 5px 0px 10px ;">
		${content}

	</div>



	<div>
		<label  style="text-align: left;font-size: 16px;"> 附件</label>
		[#if policyfile.uploadfileid??]
			[#list policyfile.uploadfileList as uploadfile]
				<span>
							<a style="font-size: 16px;"  href='${base}/common/file/download/${uploadfile.id }'target='_blank'>${uploadfile.filename}</a>
						</span>
			[/#list]
		[/#if]
	</div>
</div>




<div  style="text-align: center;padding-bottom: 20px;">
	<button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="returninfo();">返 回</button>
</div>
</div>
[#include "common/foot.ftl"]

</body>

<script type="text/javascript">


function returninfo() {
	window.location.href=base + "/newpolicy";
}


</script>
</html>