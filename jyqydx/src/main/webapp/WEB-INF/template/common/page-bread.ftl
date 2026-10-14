<div class="content_title" style="display:none;">
	${sername[sername?size-1]}
</div>
[#if modelcode[0]!='home']
<div class="breadcrumbs" style="margin:0px -12px 10px -12px;">
	<ul class="breadcrumb">
	[#if userinfo.user.usertype == "1"]
		<img src="${base}/assets/images/sy.png" style="margin-right:5px;margin-top: 7px;float:left;"></img>
		[#list sername as ser]
		<a href="javascript:void(0);" style="color:#585858;" class="font15">${ser}</a>
		[#if ser_index < sername?size-1]
		<span style="padding:0px 4px;">&gt;</span>
		[/#if]
		[/#list]
	[#else]
		<img src="${base}/assets/images/sy.png" style="margin-right:5px;margin-top: 7px;float:left;"></img><a style="color:#585858;" href="${base}/xyjsmgr" class="font15">首页</a>
		[#list sername as ser]
		<span style="padding:0px 4px;">&gt;</span>
		<a href="javascript:void(0);" style="color:#585858;" class="font15">${ser}</a>
		[/#list]
	[/#if]
	</ul>
</div>
[/#if]