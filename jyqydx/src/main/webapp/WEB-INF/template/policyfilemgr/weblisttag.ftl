
[#include "common/head.ftl"]
<body style="margin: 0">



<input type="hidden" id="content" value="${content}">


<div style="width: 50%;float: left;margin-left: 8%;">
	<div >
		<table  id="sample_1" style="width: 100%;" >
			<thead>
			<tr>
				<th width="28%"></th>
				<th width="50%"></th>
				<th width="22%"></th>
			</tr>
			</thead>
			<tbody >

			[#list policyFiles as file]
				<tr >
					<td align="left" style="height: 80px;line-height: 36px;" colspan="3" >
						<a style="font-size: 16px;cursor:pointer;text-decoration: none;color: black;" href="${base}/policyfilemgr/webshow/${file.plfidid}?key=${content}">${file.policyname}</a>

					</td>
				</tr>
				<tr >
					<td style="text-align: left;font-size: 13px;color: #6096EC;"> 文号：${file.fwtitanic}</td>
					<td style="text-align: left;font-size: 13px;color: #6096EC;"> 发文单位：${file.fwunits}</td>
					<td style="text-align: left;font-size: 13px;color: #BDBDBD;"> 生效日期：${file.releasetime}</td>
				</tr>


			[/#list]

			</tbody>
		</table>
	</div>


</div>
<div style="width: 38%;float: left;margin-left: 20px;">
	<p style="text-align: center;font-size: 28px;font-weight: 600;">奖励政策明细</p>
	<table id="sample-table-1" class="table table-striped table-bordered table-hover">
		<thead>
		<tr>

			<th style="width:10%;">序号</th>
			<th style="width:30%;">政策类别</th>
			<th style="width:30%;">政策条件</th>
			<th style="width:20%;">政策奖励</th>
			<th style="width:10%;">操作</th>
		</tr>
		</thead>
		<tbody>
		[#list details as detail ]
			<tr >
				<td>${detail_index+1}</td>
				<td>${detail.nplcategory}[#if detail.plsubclassname??&&detail.plsubclassname!='']（${detail.plsubclassname}）[/#if]</td>
				<td>${detail.plconditions}</td>
				<td>${detail.rdescription}</td>
				<td>
					<a  href="javascript:void(0);"  onclick="apply('${detail.pldtailid}');">申报</a>
				</td>
			</tr>
		[/#list]

		</tbody>
	</table>
</div>



<div  style="text-align: center;padding-bottom: 20px;width: 100%;">
	<button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="history.go(-1)">返 回</button>
</div>



</body>

<script type="text/javascript">
	var base = '${base}';


	function apply(pldtailid) {
		var companyid=$("#companyid").val();
		var isauthor=$("#isauthor").val();
		if(companyid!=undefined&&companyid!=null&&companyid!=""){
			//查询是否有权限
			if(isauthor=='0'||isauthor=='1'){
                //查询该政策是否已经申报过了
				$.ajax({
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
				});
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

</script>
</html>