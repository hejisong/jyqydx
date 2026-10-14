[#macro main modelcode sername otherjs othercss ]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN" class="no-js">
<!--<![endif]-->
	<head>
		<meta charset="utf-8" />
		<title> 南京建邺“秒触发”政策服务系统</title>
		<meta name="keywords" content=" 南京建邺“秒触发”政策服务系统" />
		<meta name="description" content=" 南京建邺“秒触发”政策服务系统" />
        <meta name="renderer" content="webkit"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
[#--		<meta http-equiv="X-Frame-Options" content="DENY"/>--]

		<link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
		<link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
		<link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
		<link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />
		[#list othercss as map]
  			<link href="${base}/${map}" rel="stylesheet" type="text/css">
		[/#list]
		<!-- 解决ie9以下浏览器兼容性问题   -->
       <!-- <script src="${base}/assets/js/respond.min.js"></script>-->
       <!-- <script src="${base}/assets/js/excanvas.min.js"></script>-->
		<script src="${base}/assets/js/jquery-3.4.1.min.js"></script>

	</head>
	<body class="body-bg">
		<script type="text/javascript">
			var base = '${base}';
			var vbase='${vbase}';
		</script>
		[#include "common/page-header.ftl"]
		<div class="main-container" style="padding-top:75px;">
			[#include "common/page-left.ftl"]
			<div class="main-content">
				<div class="page-content">
					[#include "common/page-bread.ftl"]
					[#nested/]
				</div>
			</div>
		</div>
		[#include "common/bottom.ftl"]
	</body>
           <script src="${base}/assets/js/jquery.jqprint-0.3.js"></script>
		   <script src="${base}/assets/js/jquery-migrate-1.4.1.min.js"></script>
		   <script src="${base}/assets/js/metronic.js" type="text/javascript"></script>
		  	<script src="${base}/assets/js/bootbox.min.js" type="text/javascript"></script>
		  	<script src="${base}/assets/js/bootstrap.js" type="text/javascript"></script>
<script src="${base}/assets/js/common/socketIo.js" type="text/javascript"></script>
		[#list otherjs as map]
			<script src="${base}/${map}" type="text/javascript"></script>
		[/#list] 
	 <div id="fullbg"></div>
	 <div id="fullbg1"></div>
<script>


</script>
	 
</html>
[/#macro]