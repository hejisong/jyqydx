<div class="navbar navbar-default" style="height:75px;position: fixed;width: 100%;z-index:9995;background-color: #296FEE">
	<img src="${base}/assets/images/logo_白.png" style="margin-left: 20px;margin-top: 20px"></img>
		<sapn style="font-weight: 900;font-size: 30px;color: white;margin-left: 10px;padding-top: 15px;position: absolute;text-align: center"> 南京建邺“秒触发”政策服务系统</sapn>
	<sapn style="font-weight: 500;font-size: 26px;color: white;margin-left: 480px;padding-top: 18px;position: absolute;text-align: center"> 后台管理系统</sapn>
	<table style="float:right;margin-top:18px;margin-right:10px;">
		<tr><td>
			<font color="white" face="微软雅黑" size="2" style="font-size: 12px;">
				<img src="${base}/assets/images/person.png" style="margin-bottom:2px;margin-right:5px;"></img>欢迎登录：${userinfo.user.username}（${userinfo.user.unitname}）&nbsp;&nbsp;&nbsp;
			</font>
		</td></tr>
		<tr><td>
			<font color="white" face="微软雅黑" size="2" style="font-size: 12px;">
				<#--<img src="${base}/assets/images/key.png" style="margin-bottom:2px;margin-left:10px;"></img><a href="${base}/changepwd" style="color:white;font-weight: normal;margin-left:5px;">修改密码</a>-->
				<img src="${base}/assets/images/unlock.png" style="margin-bottom:2px;margin-left:10px;"></img><a href="http://172.16.5.88/cas_server/" style="color:white;font-weight: normal;margin-left:5px;">退出</a>
			</font>
		</td></tr>
	</table>
</div>