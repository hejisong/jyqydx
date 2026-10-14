[#macro main modelcode sername otherjs othercss ]
<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta charset="utf-8" />
		<title> 南京建邺“秒触发”政策服务系统</title>
		<meta name="keywords" content=" 南京建邺“秒触发”政策服务系统" />
		<meta name="description" content=" 南京建邺“秒触发”政策服务系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
[#--		<meta http-equiv="X-Frame-Options" content="DENY"/> --]
		<meta name="renderer" content="webkit"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<link rel="stylesheet"  href="${base}/assets/css/index.css">
		<link rel="shortcut icon" href="${base}/assets/images/favicon1.ico" type="image/x-icon" />
		<link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
		<link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
		<link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
		<link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />

		<link rel="stylesheet"  href="${base}/assets/css/dvslider.css">
		<link rel="stylesheet"  href="${base}/assets/css/sliderx.css">
		<link rel="stylesheet" src="${base}/assets/css/datetimepicker.css"></link>

		<!-- 解决ie9以下浏览器兼容性问题   -->
       <!-- <script src="${base}/assets/js/respond.min.js"></script>-->
       <!-- <script src="${base}/assets/js/excanvas.min.js"></script>-->
		<script src="${base}/assets/js/jquery-3.4.1.min.js"></script>
		<script src="${base}/assets/js/jquery.jqprint-0.3.js"></script>
		<script src="${base}/assets/js/jquery-migrate-1.4.1.min.js"></script>
		<script src="${base}/assets/js/Des.js"></script>
		<script src="${base}/assets/js/bootbox.min.js" type="text/javascript"></script>
		  	<script src="${base}/assets/js/bootstrap.js" type="text/javascript"></script>
		<script src="${base}/assets/js/url.js"></script>
		<script src="${base}/assets/js/common/socketIo.js" type="text/javascript"></script>
				<style>


.footer{height:100px;padding-top:15px;color:#828080;line-height:28px;text-align:center;}
.footer a{color:#828080;}
.visiterNum div{line-height:20px;height:20px;}
.icon_ICP{display:inline-block;width:24px;height:28px;background-position:0 -90px; vertical-align:middle;}
.copyRight{display:block;width:100%;text-align:center;}


</style>
	</head>
	<body>
	<div style="width: 70%;margin-left: 5%;margin-top: 15px;height: 100px ;">
		<div>
			<div >
				<img style="vertical-align:middle;padding-right: 20px;margin-top: 10px" src="${base}/assets/images/logo.png">
				<span style="width: 285px;height: 28px;font-family: PingFangSC-Semibold;font-size: 28px;color: #333333;letter-spacing: 0;font-weight: 600;margin-top: 32px;top: 20px;position: relative">南京建邺“秒触发”政策服务系统</span>
				<span style="width: 285px;height: 28px;font-family: PingFangSC-Semibold;font-size: 28px;color: #548FF7;letter-spacing: 0;font-weight: 600;margin-top: 32px;top: 20px;position: relative">后台管理系统</span>

			</div>
		</div>

	</div>
		<script type="text/javascript">
		var base = '${base}';
		</script>

		<div class="main-container" style="width:100%;height: 100%;margin: auto;background-image: url(${base}/assets/images/bg.png);background-size: 100% 100%;">

			[#--<div class="page-content" style="padding:2px 0px 0px 0px;">
				[#include "common/page-bread-web.ftl"]
			</div>--]
			<div style="max-width: 1080px;min-height:76vh;padding:10px 10px;margin: auto;overflow-y:auto;">

					[#nested/]

			</div>

			[#--<div class="web_footer" style="background-color: #f6f9fa;width: 1024px;margin: auto;">
				<div class="xhy-fe" style="background-color: #f6f9fa;width: 1024px">
					<div class="xhy-fmes centerWidth" style="width: 1024px">
						<div class="xhy-flinka underlines" style="width: 1024px">
							<a href="">联系我们</a><span>|</span><a href="">网站地图</a>
							<span>|</span><a href="">版权申明</a>
						</div>

					</div>
				</div>
			</div>--]


		</div>
	[#include "common/foot.ftl"]
	</body>
		[#list otherjs as map]
			<script src="${base}/${map}" type="text/javascript"></script>
		[/#list] 
</html>
[/#macro]