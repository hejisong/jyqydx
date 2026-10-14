<div class="side-left" style="background-color: white;">
[#if userinfo.user.usertype == "1"]
	<div class="sidebar">
		<ul class="nav nav-list">
			[#if userinfo.menus??]
				[#list userinfo.menus as menu1]
					<li class="limargin1">
						[#if menu1.url??]
						<a href="${base}/${menu1.url}" [#if menu1.menucode == modelcode[0]]style="background-color:white;"[/#if]>
							<span class="menu-text font15-weight" style="padding-left: 5px;">${menu1.menudesc}</span>
						</a>
						[#else]
						<a href="javascript:void(0)" class="dropdown-toggle" [#if menu1.menucode == modelcode[0]]style="background-color:white;"[/#if]>
							<span class="menu-text font15-weight" style="padding-left: 5px;">${menu1.menudesc}</span>
							[#if menu1.menucode == modelcode[0]]
							<img src="${base}/assets/images/a1.png" style="margin-right: 2px;margin-top: 17px;float:right;"></img>
							[#else]
							<img src="${base}/assets/images/a2.png" style="margin-right: 5px;margin-top: 10px;float:right;"></img>
							[/#if]
						</a>
						[/#if]
						[#if menu1.menus?? && menu1.menus?size>0]
						<ul class="submenu" [#if menu1.menucode == modelcode[0]]style="display:block;"[#else]style="display:none;"[/#if]>
							[#list menu1.menus as menu2]
								[#if menu2.url??]
								<li [#if menu2.menucode == modelcode[1]]class="active2 limargin1"[#else]class="limargin1"[/#if]>
									[#if menu2.url?contains("#/app/")]
										<a href="${vbase}/${menu2.url}" class="font15-weight" target="_blank" style="[#if menu2.menucode == modelcode[1]]color:black;[/#if]padding-left: 36px;">
											<span>${menu2.menudesc}</span>
										</a>
									[#else]
										<a href="${base}/${menu2.url}" class="font15-weight" style="[#if menu2.menucode == modelcode[1]]color:black;[/#if]padding-left: 36px;">
											<span>${menu2.menudesc}</span>
										</a>
									[/#if]

								[#else]
								<li class="limargin1">
									<a href="javascript:void(0)" class="font15-weight dropdown-toggle" style="padding-left: 36px;">
										<span>${menu2.menudesc}</span>
										<img src="${base}/assets/images/a1.png" style="margin-right: 12px;margin-top: 10px;float:right;"></img>
									</a>
								[/#if]
								[#if menu2.menus?? && menu2.menus?size>0]
								<ul class="submenu" [#if menu2.menucode==modelcode[1]]style="display:block;"[#else]style="display:none;"[/#if]>
									[#list menu2.menus as menu3]
									<li [#if menu3.menucode == modelcode[2]]class="active2 limargin1"[#else]class="limargin1"[/#if]>
										[#if menu3.url?contains("#/app/")]
											<a href="${vbase}/${menu3.url}" class="font15-weight" target="_blank" style="padding-left: 26px;[#if menu3.menucode == modelcode[2]]color:black;[/#if]">
												<span>${menu3.menudesc}</span>
											</a>
										[#else]
											<a href="${base}/${menu3.url}" class="font15-weight" style="padding-left: 26px;[#if menu3.menucode == modelcode[2]]color:black;[/#if]">
												<span>${menu3.menudesc}</span>
											</a>
										[/#if]

									</li>
									[/#list]
								</ul>
								[/#if]
							</li>
							[/#list]
						</ul>
						[/#if]
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	[#else]
	<div class="sidebar">
		<ul class="nav nav-list">
			<li class="limargin1">
				<a href="${base}/index" [#if home?? && home == 'home']style="color:blue;"[/#if]>
					<span class="menu-text font15-weight" style="padding-left: 5px;">首页</span>
				</a>
			</li>
			[#if userinfo.menus??]
				[#list userinfo.menus as menu1]
					<li class="limargin1">
						<a href="javascript:void(0)" class="dropdown-toggle" [#if menu1.menucode == modelcode[0]]style="background-color:white;"[/#if]>
							<span class="menu-text font15-weight" style="padding-left: 5px;">${menu1.menudesc}</span>
							 [#if menu1.menucode == modelcode[0]]
							 	<img src="${base}/assets/images/a1.png" style="margin-right: 2px;margin-top: 17px;float:right;"></img>
							 [#else]
								<img src="${base}/assets/images/a2.png" style="margin-right: 5px;margin-top: 17px;float:right;"></img>
							[/#if]
						</a>
						[#if menu1.menus?? && menu1.menus?size>0]
						<ul class="submenu" [#if menu1.menucode == modelcode[0]]style="display:block;"[#else]style="display:none;"[/#if]>
							[#list menu1.menus as menu2]
								[#if menu2.url??]
								<li [#if menu2.menucode == modelcode[1]]class="active2 limargin1"[#else]class="limargin1"[/#if]>
									[#if menu2.url?contains("#/app/")]
										<a href="${vbase}/${menu2.url}" class="font15-weight" target="_blank" style="[#if menu2.menucode == modelcode[1]]color:blue;[/#if] padding-left:26px;" [#if menu2.menucode=='offmap'] target="_blank" [/#if]>
											<span>${menu2.menudesc}</span>
										</a>
									[#else]
										<a href="${base}/${menu2.url}" class="font15-weight" style="[#if menu2.menucode == modelcode[1]]color:blue;[/#if] padding-left:26px;" [#if menu2.menucode=='offmap'] target="_blank" [/#if]>
											<span>${menu2.menudesc}</span>
										</a>
									[/#if]

								[#else]
								<li class="limargin1">
									<a href="javascript:void(0)" class="font15-weight dropdown-toggle" style="padding-left: 26px;" >
										<span>${menu2.menudesc}</span>
										[#if menu2.menucode == modelcode[1]]
							 				<img src="${base}/assets/images/a1.png" style="margin-right: 9px;margin-top: 10px;float:right;"></img>
							 			[#else]
											<img src="${base}/assets/images/a2.png" style="margin-right: 12px;margin-top: 7px;float:right;"></img>
										[/#if]
									</a>
								[/#if]
								[#if menu2.menus?? && menu2.menus?size>0]
								<ul class="submenu" [#if menu2.menucode==modelcode[1]]style="display:block;"[#else]style="display:none;"[/#if]>
									[#list menu2.menus as menu3]
									<li [#if menu3.menucode == modelcode[2]]class="active2 limargin1"[#else]class="limargin1"[/#if]>
										[#if menu3.url?contains("#/app/")]
											<a href="${vbase}/${menu3.url}" class="font15-weight" target="_blank" style="padding-left:40px;margin-left:0px;[#if menu3.menucode == modelcode[2]]color:black;[/#if]">
												<span>${menu3.menudesc}</span>
											</a>
										[#else]
											<a href="${base}/${menu3.url}" class="font15-weight" style="padding-left:40px;margin-left:0px;[#if menu3.menucode == modelcode[2]]color:black;[/#if]">
												<span>${menu3.menudesc}</span>
											</a>
										[/#if]

									</li>
									[/#list]
								</ul>
								[/#if]
							</li>
							[/#list]
						</ul>
						[/#if]
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
[/#if]
</div>
  <script type="text/javascript">
	$(function(){
		var height = 0;
		var width = 0;
		$(".dropdown-toggle").bind("click",function(){
			height = parseInt($(this).children(":last").css("margin-top"));
			width =  parseInt($(this).children(":last").css("margin-right"));
			if($(this).siblings(".submenu").css("display")=="block"){
				height = height - 3;
				width = width + 3;
				$($(this).children()[1]).attr("src",base+"/assets/images/a2.png");
			}else{
				height = height + 3;
				width = width - 3;
				$($(this).children()[1]).attr("src",base+"/assets/images/a1.png");
			}
			$(this).children(":last").css("margin-top",height+"px");
			$(this).children(":last").css("margin-right",width+"px");
			$(this).siblings(".submenu").slideToggle("fast");
		});
	});
 </script>