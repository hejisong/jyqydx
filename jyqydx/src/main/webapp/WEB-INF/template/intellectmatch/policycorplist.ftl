[#assign jss=["assets/js/jquery.validate.min.js"  ,"assets/js/date-time/bootstrap-datetimepicker.min.js","assets/js/common/webuploadernew/webuploader.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['智能匹配','政策企业智能匹配'] modelcode=["intellectmatch","policycorpmatch"] otherjs=jss othercss=css]
[#include "common/loading.ftl"]
  <div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<p style="text-align: center;font-size: 20px;font-weight: 600;">政策企业智能匹配</p>

			<div class="form-group" style="margin-top: 20px;" >

				<div class="policydiv"><img class="policyimg" src="${base}/assets/images/policy/policyname.png"></div>
				<div class="policydivb sdiv" ><span class="policylable fw" >政策名称</span></div>
				<div class="policydivn sdiv">

					<span class="policylable"  id="policyfilename" name="policyfilename" ></span>
				</div>
				<div class="policydivb"><button class="policybutton"  type="button" onclick="openfile();">选择</button></div>

			</div>

			<div class="form-group" >
				<div class="policydiv"><img class="policyimg"  src="${base}/assets/images/policy/policytag.png"></div>
				<div class="policydivb sdiv" style="padding-top: 54px;"><span class="policylable fw" >政策标签</span></div>
				<div class="policydivn sdiv">

					<span class="policylable"  id="tagname" name="tagname" ></span>
				</div>
				<div class="policydivb"><button class="policybutton" onclick="openTag();">调整</button></div>
			</div>

			<div class="form-group" >
				<div class="policydiv"><img class="policyimg"  src="${base}/assets/images/policy/corpscope.png"></div>
				<div class="policydivb sdiv" style="padding-top: 54px;"><span class="policylable fw" >企业范围</span></div>
				<div class="policydivn sdiv">

					<span class="policylable"  id="corpscope" name="corpscope" ></span>
				</div>
				<div class="policydivb"><button class="policybutton" onclick="openCorp();" >筛选</button></div>
			</div>

			<div class="form-group" >
				<div class="policydiv"><img class="policyimg" src="${base}/assets/images/policy/imacth.png"></div>
				<div class="policydivb sdiv" style="padding-top: 54px;"><span class="policylable fw" >智能匹配</span></div>
				<div class="policydivn sdiv">
					<span class="policylable"  id="match" name="match" ></span>
				</div>
				<div class="policydivb"><button class="policybutton" onclick="match()">匹配</button></div>
			</div>

			<div class="form-group" >
				<div class="policydiv"><img class="policyimg"  src="${base}/assets/images/policy/result.png"></div>
				<div class="policydivb sdiv" style="padding-top: 54px;"><span class="policylable fw" >匹配结果</span></div>
				<div class="policydivn sdiv">
					<span class="policylable"  id="result" name="result" ></span>
				</div>
				<div class="policydivb"><button class="policybutton" onclick="openResult()">详情</button></div>
			</div>







			</div>

	</div>
	
</div>

	<form id="matchform">
		<input type="hidden" id="policyfileid" name="policyfileid"  >
		<input type="hidden" id="pldtailid" name="pldtailid"  >
		<input type="hidden" id="tagid" name="tagid"  >
		<input type="hidden" id="corptagid" name="corptagid"  >
		<input type="hidden" id="corptype" name="corptype"  >
		<input type="hidden" id="status" name="status"  >
		<input type="hidden" id="minrecapital" name="minrecapital"  >
		<input type="hidden" id="maxrecapital" name="maxrecapital"  >
		<input type="hidden" id="startdate" name="startdate"  >
		<input type="hidden" id="enddate" name="enddate"  >
	</form>
	[#include "intellectmatch/t_policydetail.ftl"]
	[#include "intellectmatch/t_tag.ftl"]
	[#include "intellectmatch/t_corp.ftl"]
	[#include "intellectmatch/t_result.ftl"]
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


	function match() {
		$("#loading_state").val("1");
		var tagid=$("#tagid").val();
		if(tagid==null||tagid==""){
			bootbox.alert("请选择政策！");
			return;
		}
		$.ajax({
			type:"POST",
			url:base+"/intellectmatch/match" ,
			data:$("#matchform").serialize(),
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
      var starttime=data[0];
      var corpcount=data[1];
      var resultcount=data[2];
      var endtime=data[3];
      var html='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">开始时间：'+starttime+'</span>'
			  +'<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">结束时间：'+endtime+'</span>'
			  +'<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">企业数量：'+corpcount+'家</span>';
		var rhtml='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">符合条件企业：'+resultcount+'家</span>';
		var rtitle="";
		if(data[5]==data[6]){
			rhtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">预估兑付资金约为：'+data[5]+'万元</span>';
			rtitle='匹配企业共'+resultcount+'家，预估兑付的资金约为：'+data[5]+'万元';
		}else{
			rhtml+='<span style="margin-left: 10px;padding-left: 5px;padding-right: 5px;">预估兑付资金范围：'+data[5]+'万元~'+data[6]+'万元</span>';
			rtitle='匹配企业共'+resultcount+'家，预估兑付的资金范围为：'+data[5]+'万元~'+data[6]+'万元';
		}


      $("#match").html(html);
	  $("#result").html(rhtml);
	  //结果详情
		var corps=data[4];
		var detailhtml="";
		$("#rtitle").html(rtitle);
		$.each(corps,function(j,corp){
			var companyid=corp[0];
			var companyname=corp[1];
			var detailscount=corp[2];
			var details=corp[3];

			$.each(details,function(i,detail){
				var nplcategory="";
				var plconditions="";
				if(detail[0]!=null){
					nplcategory=detail[0];
				}
				if(detail[1]!=null&&detail[1].length>0){
					nplcategory=nplcategory+"("+detail[1]+")";
				}
				if(detail[2]!=null){
					plconditions=detail[2];
				}

			/*	detailhtml+='<tr>'
						+'<td style="text-align: center;">'+companyname+'</td>'
						+'<td style="text-align: center;">'+nplcategory+'</td>'
						+'<td style="text-align: center;">'+plconditions+'</td>'
						/!*+'<td  style="text-align: center;">'+detail.corpcount+'</td>'*!/
						+'</tr>';*/
				detailhtml+='<tr>';
				if(i==0){
                    detailhtml+='<td rowspan="'+detailscount+'" style="text-align: center;">'+(j+1)+'</td>'
							+'<td rowspan="'+detailscount+'" style="text-align: center;">'+companyname+'</td>'
                            +'<td rowspan="'+detailscount+'" style="text-align: center;">'+detailscount+'</td>';


                }

				detailhtml+= '<td style="text-align: center;">'+nplcategory+'</td>'
						  +'<td style="text-align: center;">'+plconditions+'</td></tr>';


			})








		})
		$("#detailtcontent").html(detailhtml);
	}



</script>
[/@layout.main]