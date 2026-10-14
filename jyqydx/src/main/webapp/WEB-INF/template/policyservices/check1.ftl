[#assign jss=["assets/js/common/webuploader/webuploader.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js"]]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[#import "common/layout.ftl" as layout]
[@layout.main sername=['政策服务管理','审核界面'] modelcode=["zcfwgl","zcfwgl"] otherjs=jss ]

	<div style="width: 100%;height: 900px;background: #F3F7FA; padding-top: 20px;">
		<div style="width: 1000px;height: 850px;background: #FFFFFF;border-radius: 6px;margin-left: 20% ;padding: 10px 100px">
			<div style="margin-top: 10px;width: 1000px;height: 50px;border-radius: 6px; ">

				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">审批详情</a>
			</div>
			<div style="margin-top: 10px;width: 800px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
				<input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="1">
				<a style="">申请报告</a>
				<img style="position: absolute;margin-top: -9px;margin-left: 500px" src="${base}/assets/images/ypp.png">
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 600px;background-color: #3D90FF;color: white;height: 30px">导出</button>
			</div>
			<div style="margin-top: 10px;width: 800px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
				<input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="2">
				<a style="">营业执照（统一信用代码，企业名称）市场监管局可查询</a>
				<img style="position: absolute;margin-top: -9px;margin-left: 250px" src="${base}/assets/images/ypp.png">
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 350px;background-color: #3D90FF;color: white;height: 30px">导出</button>
			</div>
			<div style="margin-top: 10px;width: 800px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
				<input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="3">
				<a style="">股权投资（基金管理机构）章程或合伙协议（协议规模，首期出资规模</a>
				<img style="position: absolute;margin-top: -9px;margin-left: 180px" src="${base}/assets/images/ypp.png">
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 277px;background-color: #3D90FF;color: white;height: 30px">导出</button>
			</div>
			<div style="margin-top: 10px;width: 800px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
				<input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="4">
				<a style="">管理协议（组织架构）</a>
				<img style="position: absolute;margin-top: -9px;margin-left: 433px" src="${base}/assets/images/ypp.png">
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 528px;background-color: #3D90FF;color: white;height: 30px">导出</button>
			</div>
			<br/>
			<div style="border-top:2px dotted #e5e5e5;padding-top: 10px;width: 1000px;height: 50px;border-radius: 6px; ">

				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">部门审查意见</a>
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 80px;border-radius: 6px; ">
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">审查意见*</a>
				<input style="position: absolute;margin-left: 24px;width: 800px;height: 22px;border: 1px solid #9eb4c9;padding: 0" value="同意。">
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
				<span style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: -74px">审核时间*</span>
				<div class="col-xs-1">
					<div class="input-group date form_datetime ">

						<input class="form-control input-inline"  name="startdate" id="startdate" data-date-format="yyyy-MM-dd hh:mm:ss" readonly
							   value="${(.now?string('yyyy-MM-dd hh:mm:ss'))!}" style="border-right: 0px; background-color: white;width: 150px;margin-left: 95px">

						<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
					</div>
				</div>
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 500px">审核人*</a>
				<input style="position: absolute;margin-left: 20px;width: 150px;height: 22px;border: 1px solid #9eb4c9" value="张三">
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">备注&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a>
				<textarea style="position: absolute;margin-left: 20px;width: 800px;height: 50px"></textarea>

			</div>
			<br/>
			<div style="border-top:2px dotted #e5e5e5;padding-top: 10px;width: 1000px;height: 50px;border-radius: 6px; ">

				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">财务审查意见</a>
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 80px;border-radius: 6px; ">
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">审查意见*</a>
				<input style="position: absolute;margin-left: 24px;width: 800px;height: 22px;border: 1px solid #9eb4c9;padding: 0" value="同意。">
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
				<span style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: -74px">审核时间*</span>
				<div class="col-xs-1">
					<div class="input-group date form_datetime ">

						<input class="form-control input-inline"  name="startdate" id="startdate" data-date-format="yyyy-MM-dd hh:mm:ss" readonly
							   value="${(.now?string('yyyy-MM-dd hh:mm:ss'))!}" style="border-right: 0px; background-color: white;width: 150px;margin-left: 95px">

						<span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
					</div>
				</div>
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 500px">审核人*</a>
				<input style="position: absolute;margin-left: 20px;width: 150px;height: 22px;border: 1px solid #9eb4c9" value="张三">
			</div>
			<div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
				<a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">备注&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a>
				<textarea style="position: absolute;margin-left: 20px;width: 800px;height: 50px"></textarea>

			</div>
			<div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 300px;background-color: #3D90FF;color: white;height: 30px">同意</button>
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 100px;background-color: #3D90FF;color: white;height: 30px">驳回</button>
				<button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 100px;background-color: #3D90FF;color: white;height: 30px">返回</button>
			</div>
		</div>


	</div>
    <script type="text/javascript">
        $(function () {
            initTime();
        });

        function initTime() {
            $(".form_datetime").datetimepicker({
                autoclose: true,
                isRTL: Metronic.isRTL(),
                format: "yyyy-mm-dd",
                minuteStep: 1,
                minView: 2,
                pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
                language: 'zh_CN'
            });
        }

    </script>
[/@layout.main]