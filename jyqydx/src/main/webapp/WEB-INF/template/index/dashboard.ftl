[#assign jss=["assets/js/date-time/bootstrap-timepicker.min.js","assets/js/common/echarts/echarts.js","assets/js/Basestat.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout] 
[@layout.main modelcode=['home'] otherjs=jss othercss=css]
<div  class="row"  style="background-color: #e8eef1;">

	<div  class="indexdiv">
		<div class="index indexa" >
			<div style="width: 79%;float:left;">
				<div class="indexpolicy">政策文件（个）</div>
				<div class="indexcount">${filetotal}</div>
			</div>

			<div style="float:left;">
				 <img style="padding-top: 18px;" class="wrapper_bg_img" src="${base}/assets/images/index/policy.png" alt="">
			</div>
		</div>

		<div class="index indexa" >
			<div style="width: 79%;float:left;">
				<div class="indexpolicy">企业数量（家）</div>
				<div class="indexcount">${corptotal}</div>
			</div>

			<div style="float:left;">
				<img style="padding-top: 18px;" class="wrapper_bg_img" src="${base}/assets/images/index/corpcount.png" alt="">
			</div>

		</div>

		<div class="index indexa" >
			<div style="width: 79%;float:left;">
				<div class="indexpolicy">已兑现政策数（条）</div>
				<div class="indexcount">${plycomresultcount}</div>
			</div>

			<div style="float:left;">
				<img style="padding-top: 18px;" class="wrapper_bg_img" src="${base}/assets/images/index/policybf.png" alt="">
			</div>

		</div>

		<div class="index indexa" >
			<div style="width: 79%;float:left;">
				<div class="indexpolicy">未兑付资金（万元）</div>
				<div class="indexcount">${notpaytotal}</div>
			</div>

			<div style="float:left;">
				<img style="padding-top: 18px;" class="wrapper_bg_img" src="${base}/assets/images/index/policymoney.png" alt="">
			</div>

		</div>

		<div class="index indexa" style="width: 19.6%;" >
			<div style="width: 79%;float:left;">
				<div class="indexpolicy">已兑付资金（万元）</div>
				<div class="indexcount">${paytotal}</div>
			</div>

			<div style="float:left;">
				<img style="padding-top: 18px;" class="wrapper_bg_img" src="${base}/assets/images/index/policymoney.png" alt="">
			</div>

		</div>

	</div>

	<div  class="indexdiv">
		<div class="index indexb" >
			<div style="width: 100%;">
				<div style="width: 92%;float:left;">
					<div class="indexd" >类型列表</div>
				</div>

				<div style="float:left;width: 8%;">
					<img  style="padding-top: 10px;" class="wrapper_bg_img" onclick="openFile();" src="${base}/assets/images/index/more.png" alt="">
				</div>

			</div>



			<div style="padding: 5px; margin-top: 3px;width: 100%;padding-top: 46px;">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<tr style="font-weight: bold;">

						<td style="width: 46%;font-size: 13px;">政策文件名称</td>
						<td style="width: 10%;font-size: 13px;">拆解细则条数</td>
						<td style="width: 10%;font-size: 13px;">免申即享类</td>
						<td style="width: 10%;font-size: 13px;">补充兑现类</td>
						<td style="width: 10%;font-size: 13px;">提升兑现类</td>
						<td style="width: 10%;font-size: 13px;">条件兑现类</td>
					</tr>

					[#list policyfiles as policyfile]
					<tr>

						<td style="font-size: 13px;">${policyfile.policyname}</td>
						<td style="font-size: 13px;">${policyfile.detailnum}</td>
						<td style="font-size: 13px;">${policyfile.withoutnum}</td>
						<td style="font-size: 13px;">${policyfile.replenishnum}</td>
						<td style="font-size: 13px;">${policyfile.improving}</td>
						<td style="font-size: 13px;">${policyfile.prednum}</td>
					</tr>

					[/#list]


				</table>
			</div>


		</div>

		<div class="index indexbb" >
			<div class="indexd" >企业政策兑现排行</div>
			<div class="chart" id="chart_corp" style="padding: 0px; position: relative;height:230px;margin-top: -24px;"></div>

		</div>

		<div class="index indexbb" style="width: 32%;">
			<div class="indexd" >企业政策兑现资金排行（万元）</div>
			<div class="chart" id="chart_money" style="padding: 0px; position: relative;height:230px;margin-top: -24px;"></div>

		</div>

	</div>

	<div  class="indexdiv">

		<div class="index indexb" >
			<div class="indexd" >兑现过程</div>


			<table style="margin-top: 30px;margin-left: 20px;">
				<tr style="height: 55px;">
					<td style="font-size: 14px;color:#4b9bec; ">${cash_ydata1}万元</br><span style="color: #BDBDBD;">免申即享类</span></td>
					<td style="font-size: 14px;color:#f5823e;">${cash_ydata2}万元</br><span style="color: #BDBDBD;">补充兑现类</span></td>
				</tr>

				<tr style="height: 55px;">
					<td  style="font-size: 14px;color:#8d3ef5;width: 100px;">${cash_ydata3}万元</br><span style="color: #BDBDBD;">提升兑现类</span></td>
					<td style="font-size: 14px;color:#30bb27; width: 100px;">${cash_ydata4}万元</br><span style="color: #BDBDBD;">条件兑现类</span></td>

				</tr>
			</table>
            <div  class="chart" id="chart_cash" style="padding: 0px; position: relative;height:200px;margin-top: -160px;margin-left: 26%;width: 400px;" ></div>


		</div>

		<div class="index indexc" style="width: 63.5%;">
			<div class="indexd" >兑现过程-企业数量（家）</div>
			<div class="chart" id="chart_cashcorp" style="padding: 0px; position: relative;height:300px;margin-top: -24px;"></div>
		</div>

	</div>


	<div  class="indexdiv" >
		<div class="index indexb">
			<div class="indexd" >审批统计</div>
			<div class="chart" id="chart_check" style="padding: 0px; position: relative;height:220px;margin-top: -22px;"></div>
		</div>

		<div class="index indexc" style="width: 63.5%;">
			<div class="indexd" >资金兑付统计</div>
			<div style="width: 54%;float: left;min-width: 54%;">


					<table style="margin-top: 10px;margin-left: 20px;">
						[#assign filecount=0]
						[#list policy_data as policy]
							[#assign filecount=filecount+1]
							<tr style="height: 34px;">
								[#if policy_index==0]<td style="font-size: 12px;color: #4b9bec;">${policy_ydata1}万元</br><span style="color: #BDBDBD;">${policy_xdata1}</span></td>
								[#elseif policy_index==1]<td style="font-size: 12px;color: #f5823e;">${policy_ydata2}万元</br><span style="color: #BDBDBD;">${policy_xdata2}</span></td>
								[#elseif policy_index==2]<td style="font-size: 12px;color: #8d3ef5;">${policy_ydata3}万元</br><span style="color: #BDBDBD;">${policy_xdata3}</span></td>
								[#elseif policy_index==3]<td style="font-size: 12px;color: #30bb27;">${policy_ydata4}万元</br><span style="color: #BDBDBD;">${policy_xdata4}</span></td>
								[#elseif policy_index==4]<td style="font-size: 12px;color: #282eea;">${policy_ydata5}万元</br><span style="color: #BDBDBD;">${policy_xdata5}</span></td>

								[/#if]
							</tr>

						[/#list]

					</table>




					<div  class="chart" id="chart_cash1" style="padding: 0px; position: absolute;height:200px;margin-top:[#if filecount==1]-69[#elseif filecount==2]-102[#elseif filecount==3]-137[#elseif filecount==4]-173[#elseif filecount==5]-207[/#if]px ;margin-left: 8%;width: 400px;" ></div>









			</div>


			<div style="width: 46%;float: left;min-width: 46%;">

				<table style="margin-top: 20px;">
					<tr style="height: 55px;">
						<td style="font-size: 12px;color:#4b9bec; ">${org_xdata1}万元</br><span style="color: #BDBDBD;">科技局</span></td>
						<td style="font-size: 12px;color:#f5823e;">${org_xdata2}万元</br><span style="color: #BDBDBD;">发改委</span></td>
						<td style="font-size: 12px;color:#8d3ef5;">${org_xdata3}万元</br><span style="color: #BDBDBD;">人社局</span></td>
					</tr>

					<tr style="height: 55px;">
						<td  style="font-size: 12px;color:#30bb27;width: 70px;">${org_xdata4}万元</br><span style="color: #BDBDBD;">金融监管局</span></td>
						<td style="font-size: 12px;color:#282eea; width: 70px;">${org_xdata5}万元</br><span style="color: #BDBDBD;">市场监管局</span></td>

					</tr>
				</table>






				<div  class="chart" id="chart_cash2" style="padding: 0px; position: relative;height:200px;margin-top: -150px;margin-left:18%;width: 400px;" ></div>
			</div>
		</div>
	</div>

</div>
	[#include "index/t_file.ftl"]
<script>

	var corp_xdata = ${corp_xdata};
	var corp_ydata = ${corp_ydata};

	var money_xdata = ${money_xdata};
	var money_ydata = ${money_ydata};
	var cash_xdata = [
		{name: '免申即享类',value: ${cash_ydata1}},
		{name: '补充兑现类',value: ${cash_ydata2}},
		{name: '提升兑现类',value: ${cash_ydata3}},
		{name: '条件兑现类',value: ${cash_ydata4}}];

	var cashcorp_xdata =  ["已兑现","兑现审核","补充材料","政策匹配"];
	var cashcorp_ydata =[${cashcorp_ydata1},${cashcorp_ydata2},${cashcorp_ydata3},${cashcorp_ydata4}];

	var checkdata_x= ["科技局","发改委","人社局","金融监管局","市场监管局"];

	var checkdata_y1=[${checkdata_y1},${checkdata_y2},${checkdata_y3},${checkdata_y4},${checkdata_y5}];
	var checkdata_y2=[${checkdata_y11},${checkdata_y22},${checkdata_y33},${checkdata_y44},${checkdata_y55}];


	var payFile=${payFileum};
	var policy_xdata = ${policy_data};

	var org_xdata = [
		{name: '科技局',value: ${org_xdata1}},
		{name: '发改委',value: ${org_xdata2}},
		{name: '人社局',value: ${org_xdata3}},
		{name: '金融监管局',value: ${org_xdata4}},
		{name: '市场监管局',value: ${org_xdata5}}];

	$(function(){
		Basestat.createBar('chart_corp','','','','',corp_xdata,corp_ydata,'','单位/条','#3398DB');
		Basestat.createBar('chart_money','','','','',money_ydata,money_xdata,'','单位/万元','#2fdb64');
		// Basestat.createBarH('chart_money','','','',money_ydata,money_xdata,'单位/万元','企业名称');
		Basestat.createPieMe('chart_cash','','','',cash_xdata);
		Basestat.createLine('chart_cashcorp','','','',cashcorp_xdata,cashcorp_ydata,'','单位/家');
		Basestat.createBarMore('chart_check','2020','2019',checkdata_x,checkdata_y1,checkdata_y2);
		Basestat.createPieMe('chart_cash1','','','',policy_xdata);
		Basestat.createPieMe('chart_cash2','','','',org_xdata);

	});


</script>













[/@layout.main]
