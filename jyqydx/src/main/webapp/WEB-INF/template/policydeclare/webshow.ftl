
[#include "common/head.ftl"]
<body style="margin: 0">



<div style="width: 100%;margin-top: -15px;background-color: #f8f4fd;">

	<div style="width: 70%;margin-left: 17%;padding-top: 10px;">
	<div style="margin-top: 10px;">
		<p style="text-align: center;font-size: 20px;font-weight: 600;">${policydeclare.declarename}</p>

		<p style="font-size: 18px;">
			<span >申报时间范围：[#if policydeclare.declare_start_date??]${(policydeclare.declare_start_date?string('yyyy-MM-dd')) }[/#if]~[#if policydeclare.declare_end_date??]${(policydeclare.declare_end_date?string('yyyy-MM-dd')) }[/#if] </span>
			<span style="position: absolute;left: 1000px;" >适用园区：${policydeclare.applyparkname}</span>
		</p>
		<p style="font-size: 18px;">
			<span>发布单位：${policydeclare.releaseorgname}</span>
			<span style="position: absolute;left: 1000px;" >发布日期：[#if policydeclare.releasedate??]${(policydeclare.releasedate?string('yyyy-MM-dd')) }[/#if]</span>
		</p>


	</div>

	<div style="background: #0a6aa1;height: 60px;padding: 15px 0px 0px 10px;margin-top: 8px;">
		<a style="color:black ;font-size: 18px;text-decoration: none;" href="javascript:void(0);">通知原文</a>
		<a style="color:  white;margin-left: 20px;font-size: 18px;text-decoration: none;"  href="${base}/policydeclare">返回</a>

	</div>

	<div style="background: whitesmoke;padding: 0px 5px 0px 10px ;">
		${content}

	</div>

<div>
	<label  style="text-align: left;font-size: 16px;"> 附件</label>
	[#if policydeclare.uploadfileid??]
		[#list policydeclare.uploadfileList as uploadfile]
			<span>
							<a style="font-size: 16px;"  href='${base}/common/file/download/${uploadfile.id }'target='_blank'>${uploadfile.filename}</a>
						</span>
		[/#list]
	[/#if]
</div>

</div>




<div  style="text-align: center;padding-bottom: 20px;margin-top: 20px;">

	<button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="apply('${policydeclare.declareid}');">我要申报</button>
	<button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;margin-left: 20px;" class="btn btn-xs btn-primary" type="button" onclick="history.go(-1)">返 回</button>
</div>
</div>
[#include "common/foot.ftl"]
</body>

<script type="text/javascript">
function apply(declareid) {

	var companyid=$("#companyid").val();
	window.location.href=base + "/policydeclare/applylist/"+declareid+"?companyid="+companyid;

}


</script>
</html>