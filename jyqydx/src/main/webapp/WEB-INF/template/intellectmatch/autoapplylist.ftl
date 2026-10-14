[#assign jss=["assets/js/jquery.validate.min.js"  ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['智能匹配','免申即享政策匹配'] modelcode=["intellectmatch","autoapplymatch"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
  <div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<p style="text-align: center;font-size: 20px;font-weight: 600;">免申即享政策匹配</p>

			<div style="width: 100%;">

					<div style="width: 45%;float: left;margin-top: 20px;height: 400px;">

						<div class="col-xs-12" style="margin-bottom: 20px;">
							<label style="text-align: left;font-size: 18px;font-weight: 600;margin-left: 4px;">政策信息</label>
							<button style="margin-left: 50px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openfile()">选择政策</button>
						</div>
						<div style="height: 300px;overflow-y: auto;width: 100%;">
							<table id="sample-table-project" class="table table-striped table-bordered table-hover">
								<thead>
								<tr>

									<th style="width:20%;">政策文件</th>
									<th style="width:20%;">政策类型</th>
									<th style="width:30%;">政策条件</th>
									<th style="width:30%;">政策奖励</th>

								</tr>
								</thead>
								<tbody id="detailcontent">


								</tbody>
							</table>
						</div>
						<p style="padding-top: 5px;">已选择<span id="fileselect">0</span>条政策</p>



					</div>

					<div style="width: 8%;float: left;margin-top: 120px;">
						<img class="policyimg"  src="${base}/assets/images/policy/intellect.png">
						<button style="text-align: center;font-size: 18px;color:#1f60f5;margin-left: 20px;" type="button" onclick="imatch()">智能匹配</button>
					</div>



				<div style="width: 46%;float: left;margin-top: 20px;height: 400px;">
					<div class="col-xs-12" style="margin-bottom: 20px;">
					<label style="text-align: left;font-size: 18px;font-weight: 600;margin-left: 4px;">企业信息</label>
					<button style="margin-left: 50px;" class="btn btn-xs btn-info btn-width" type="button" onclick="openCorp()">选择企业</button>
					</div>

					<div style="height: 300px;overflow-y: auto;width: 100%;">
						<table id="sample-table-project" class="table table-striped table-bordered table-hover">
							<thead>
							<tr>
								<th style="width:40%;">企业名称</th>
								<th style="width:22%;">统一社会信用代码</th>
								<th style="width:22%;">注册资本</th>
								<th style="width:16%;">经营状态</th>


							</tr>
							</thead>
							<tbody id="corpcontent">


							</tbody>
						</table>
					</div>

					<p style="padding-top: 5px;">已选择<span id="corpselect">0</span>家企业</p>


				</div>



					</div>





			<div style="width: 100%;">

				<table id="sample-table-1" class="table table-striped table-bordered table-hover" style="margin-top: 230px;">
					<thead>
					<tr>
						<th style="width:5%;">序号</th>
						<th style="width:15%;">企业名称</th>
						<th style="width:10%;">统一社会信用代码</th>
						<th style="width:15%;">政策类型</th>
						<th style="width:25%;">政策条件</th>
						<th style="width:20%;">政策奖励</th>
						<th style="width:10%;">申请金额(元)</th>


					</tr>
					</thead>
					<tbody id="resultcontent">


					</tbody>
				</table>

				<div style=" height: 33px;"id="resultpagecontent">
					<table style="width:100%;">
						<tr>
							<td width="80%">第0页/共0页，总共0条记录</td>
							<td width="20%" style="text-align: right;">
								<button class="btn btn-xs btn-default" type="button"data-toggle="button" disabled="disabled">上一页</button>
								<button class="btn btn-xs btn-default" type="button"data-toggle="button" style="margin-left: 5px;"disabled="disabled">下一页</button>
							</td>
						</tr>
					</table>
				</div>

			</div>








			</div>

	</div>



	
</div>

	<form id="corpform">


		<input type="hidden" id="corpname" name="corpname"  >
		<input type="hidden" id="corptagid" name="corptagid"  >
		<input type="hidden" id="corptype" name="corptype"  >
		<input type="hidden" id="status" name="status"  >
		<input type="hidden" id="minrecapital" name="minrecapital"  >
		<input type="hidden" id="maxrecapital" name="maxrecapital"  >
		<input type="hidden" id="startdate" name="startdate"  >
		<input type="hidden" id="enddate" name="enddate"  >


	</form>


	<form id="msmatchform">
		<input type="hidden" id="pldtailids" name="pldtailids"  >
		<input type="hidden" id="companyids" name="companyids"  >


	</form>

	[#include "intellectmatch/t_detail.ftl"]
	[#include "intellectmatch/t_company.ftl"]

<script type="text/javascript">
	$(function(){
		initTime();
	});
	function initTime(){
		$(".form_datetime").datetimepicker({
			autoclose: true,
			isRTL: Metronic.isRTL(),
			format: "yyyy-mm-dd",
			minuteStep : 1,
			minView : 2,
			pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
			language: 'zh_CN'
		});
	}





	function imatch() {
		$("#loading_state").val("1");
		var pldtailids=$("#pldtailids").val();
		if(pldtailids==null||pldtailids==""){
			bootbox.alert("请选择政策！");
			return;
		}
		var companyids=$("#companyids").val();
		if(companyids==null||companyids==""){
			bootbox.alert("请选择企业！");
			return;
		}
		/*var corpids=$("#msmatchform").find("input[name='corpids']");
		var corpidslist = new Array();
		$.each(corpids, function(e,corpid){
			corpidslist.push($(corpid).val());
		})*/

		var data = {
			pldtailids:pldtailids,
			companyids:companyids,
			page:0
		};

		$.ajax({
			type:"POST",
			url:base+"/intellectmatch/msmatch" ,
			data:data,
			dataType:"json",
			async:true,
			success:function(data){
				$("#loading_state").val("0")
				initResult(data);
			},
			error:function(){
				bootbox.alert("数据加载失败");
			}
		});
	}

	function imatchpage(page) {
		var pldtailids=$("#pldtailids").val();
		var companyids=$("#companyids").val();


		var data = {
			pldtailids:pldtailids,
			companyids:companyids,
			page:page
		};

		$.ajax({
			type:"POST",
			url:base+"/intellectmatch/msmatch" ,
			data:data,
			dataType:"json",
			async:true,
			success:function(data){
				$("#loading_state").val("0")
				initResult(data);
			},
			error:function(){
				bootbox.alert("数据加载失败");
			}
		});
	}


	function initResult(data) {
		var html="";
		$.each(data.content,function(i,detail){
			var nplcategory="";
			var policyconditions="";
			var rdescription="";
			var uscreditcode="";
			var amount="";
			var inputtime="";
			var state="";
			if(detail.uscreditcode!=null){
				uscreditcode=detail.uscreditcode;
			}

			if(detail.policycategoryname!=null){
				nplcategory=detail.policycategoryname;
			}
			if(detail.policysubclassname!=null&&detail.policysubclassname!=''){
				nplcategory=nplcategory+"("+detail.policysubclassname+")";
			}
			if(detail.policyconditions!=null){
				policyconditions=detail.policyconditions;
			}
			if(detail.rdescription!=null){
				rdescription=detail.rdescription;
			}
			if(detail.amount!=null){
				amount=detail.amount;
			}
			if(detail.inputtime!=null){
				inputtime=detail.inputtime;
			}
			var cashflag=detail.cashflag;
			if(cashflag=='1'){
				state="待审批";
			}else if(cashflag=='2'){
				state="审批通过";
			}else if(cashflag=='3'){
				state="已退回";
			}else if(cashflag=='4'){
				state="审核不通过";
			}else if(cashflag=='5'){
				state="已支付";
			}else if(cashflag=='6'){
				state="已上传凭证";
			}else if(cashflag=='7'){
				state="审批中";
			}else{
				state="已匹配";
			}

			html+='<tr>'
					+'<td style="text-align: center;">'+(i+1)+'</td>'
					+'<td style="text-align: center;">'+detail.enterprisename+'</td>'
					+'<td style="text-align: center;">'+uscreditcode+'</td>'
					+'<td style="text-align: center;">'+nplcategory+'</td>'
					+'<td style="text-align: center;">'+policyconditions+'</td>'
					+'<td style="text-align: center;">'+rdescription+'</td>'
					+'<td style="text-align: center;">'+amount+'</td>'
					//+'<td style="text-align: center;">'+format(inputtime)+'</td>'
					// +'<td style="text-align: center;">'+state+'</td>'
			+'</tr>';


		});
		var totalPages = data.totalPages;
		var totalElements = data.totalElements;
		var nextpageflag = "";
		var beforepageflag = "";
		var currentpage = data.number;
		if(data.last){
			nextpageflag = "disabled";
		}else{
			nextpageflag = "onclick='imatchpage("+(currentpage+1)+")'";
		}
		if(data.first){
			beforepageflag = "disabled";
		}else{
			beforepageflag = "onclick='imatchpage("+(currentpage-1)+")'";
		}
		var pagecontent = "<table width='100%'>"
				+"<tr>"
				+"<td width='80%'>第"+(currentpage+1)+"页/共"+totalPages+"页，总共"+totalElements+"条记录</td>"
				+"<td width='20%' style='text-align:right;'>"
				+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' "+beforepageflag+">上一页</button>"
				+"<button class='btn btn-xs btn-info' type='button' data-toggle='button' style='margin-left: 5px;' "+nextpageflag+">下一页</button>"
				+"</td>"
				+"</tr>"
				+"</table>"
		$("#resultcontent").html(html);
		$("#resultpagecontent").html(pagecontent);
	}


	function format(shijianchuo) {
		if(shijianchuo!=null&&shijianchuo!=''){
			var time = new Date(shijianchuo);
			var y = time.getFullYear();
			var m = time.getMonth()+1;
			var d = time.getDate();
			var h = time.getHours();
			var mm = time.getMinutes();
			var s = time.getSeconds();
			// return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
			return y+'-'+add0(m)+'-'+add0(d);
		}
		return '';

	}

	function add0(m){return m<10?'0'+m:m }







	function companycheckAll(){
		var check=$("input[name='companycheck']");
		for(i=0;i<check.length;i++){
			if($("#companycheckAll")[0].checked==false){
				check[i].checked=false;
			}else{
				check[i].checked=true;
			}
		}

	}

	function companycheck(){
		var check=$("input[name='companycheck']");
		for(i=0;i<check.length;i++){
			if(check[i].checked==false){
				$("#companycheckAll")[0].checked=false
			}
		}
	}



</script>
[/@layout.main]