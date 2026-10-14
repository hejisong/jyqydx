[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/date-time/bootstrap-datetimepicker.min.js",
"assets/js/validate.js","assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout] 
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['政策管理','政策文件管理'] modelcode=["qyzc","policyfilemgr"] otherjs=jss othercss=css]
	<div class="row">
		<div class="col-xs-12">
			<div id="errorMessage" class="alert alert-block alert-success" style="display: none"></div>
			<form class="form-horizontal" role="form" id="formid" action="${base}/policyfilemgr/detailsave" method="post">
			
				[#if policydetail.pldtailid??]<input type="hidden" name="pldtailid" value="${policydetail.pldtailid }">[/#if]
				<input type="hidden" name="plfidid" value="${policydetail.plfidid }">
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策大类名称 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="nplcategory" name="nplcategory" value="${policydetail.nplcategory}" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策小类名称 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="plsubclassname" name="plsubclassname" value="${policydetail.plsubclassname}" class="col-xs-12">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 政策条件 <span style="color: red;">*</span></label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea rows="5" id="plconditions" name="plconditions" value="${policydetail.plconditions}" class="form-control">${policydetail.plconditions}</textarea>
						</div>
					</div>

				</div>
				<div class="form-group">

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 奖励描述<span style="color: red;">*</span> </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea rows="4" id="rdescription" name="rdescription" value="${policydetail.rdescription}" class="form-control">${policydetail.rdescription}</textarea>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 奖励金额（元） </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="ramount" name="ramount" value="${policydetail.ramount}" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 兑付路径 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="thpath" name="thpath" value="${policydetail.thpath}" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 审批单位 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="pevaluationdepartment" name="pevaluationdepartment" value="${policydetail.pevaluationdepartment }" class="col-xs-12">
						</div>
					</div>
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 参与评定部门 </label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="fwunits" name="fwunits" value="${policydetail.fwunits }" class="col-xs-12">
						</div>
					</div>
				</div>
				[#--				新增兑现金额上限、下限--]
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">下限(元)</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="minamount" name="minamount" value="${policydetail.minamount }" class="col-xs-12">
						</div>
					</div>

					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">上限(元)</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="maxamount" name="maxamount" value="${policydetail.maxamount }" class="col-xs-12">
						</div>
					</div>
				</div>
[#--				排序号--]
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;">排序号</label>
					<div class="col-xs-3">
						<div class="block input-icon input-icon-right">
							<input type="text" id="serialnum" name="serialnum" value="${policydetail.serialnum }" class="col-xs-12">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报条件 </label>
					<div class="col-xs-8">
						<div class="block input-icon input-icon-right">
							<textarea rows="10" id="declareconditions" name="declareconditions" value="${policydetail.declareconditions }" class="form-control">${policydetail.declareconditions }</textarea>
						</div>
					</div>

				</div>

				<div class="form-group">
					<label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 申报材料 </label>
					<div class="col-xs-8">

						<button class="btn btn-xs btn-info" type="button" onclick="addmaterial();">新 增</button>
						[#--<table id="sample-table-1" class="table table-striped table-bordered table-hover">
							<thead>
							<tr>

								<th style="width:5%;">序号</th>
								<th style="width:40%;">材料名称</th>
								<th style="width:45%;">附件模板</th>

								<th style="width:10%;">操作</th>
							</tr>
							</thead>
							<tbody>
							[#list plyfiles as plyfile ]
								<tr >
									<td>${plyfile_index+1}</td>
									<td>${plyfile.materialname}</td>
									<td>
										[#if plyfile.modelfileid??]
											<span name="fjlist">
												 <a  href='${base}/common/file/download/${plyfile.modelfileid }'target='_blank'>${plyfile.modelfilename}</a>
												 <a style='color:red' href='javascript:void(0)' onclick='$(this).parent().remove();'>[删除]</a>
                                            </span>
										[/#if]

									</td>

									<td>
										<a  href="javascript:void(0);" style="color: red;" onclick="deleteinfo();">删除</a>
									</td>
								</tr>
							[/#list]

							</tbody>
						</table>--]

						<table id="sample-table-1" class="table table-striped table-bordered table-hover">
							<thead>
							<tr>

								<th style="width:8%;">序号</th>
								<th style="width:44%;">材料名称</th>
								<th style="width:40%;">模板附件</th>
								<th style="width:8%;">操作</th>
							</tr>
							</thead>
							<tbody id='materialcontent'>
							[#list plyfiles as material ]
								<tr name="materialinfo">
									<td>${material_index+1}
										<input type="hidden" id="materiallists[${material_index}].sn" name="materiallists[${material_index}].sn" value="${material_index+1}">
										<input type="hidden" id="materiallists[${material_index}].listid" name="materiallists[${material_index}].listid" value="${material.listid }">

									</td>
									<td>
										<input class="form-control width-95" type="text" id="materiallists[${material_index}].materialname" name="materiallists[${material_index}].materialname" value="${material.materialname}"></td>
									<td style="text-align: left;"><div id="proofFile${material_index}" class="btn btn-xs btn-success" style="margin-left: 0px; margin-right: 0px;">添加附件</div>
										<span id="fujian_list${material_index}">
								[#if material.modelfileid??]
									<span name="fjlist">
									 <input type="hidden" id="materiallists[${material_index}].modelfileid" name="materiallists[${material_index}].modelfileid" value="${material.modelfileid}">
									 <input type="hidden" id="materiallists[${material_index}].modelfilename" name="materiallists[${material_index}].modelfilename" value="${material.modelfilename}">
                                    <a  href='${base}/common/file/download/${material.modelfileid }'target='_blank'>${material.modelfilename}</a>
                                    <a style='color:red' href='javascript:void(0)' onclick='$(this).parent().remove();'>[删除]</a>
                                </span>
								[/#if]

							</span></td>
									<td>
										<a style="color: red;"  href="javascript:void(0);"  onclick="deleteM(this)">删除</a>

									</td>
								</tr>
							[/#list]

							</tbody>
						</table>

					</div>

				</div>




				<div class="clearfix form-actions" style="text-align: center;">
					<button class="btn btn-sm btn-info" type="button" onclick="saveinfo();">保 存</button>
					<button class="btn btn-sm" type="button" onclick="cancle('${policydetail.plfidid}');">取 消</button>
				</div>
			</form>


			<div hidden="true">
				<table id="materialdome">
					<tr name="materialinfo">
						<td style="text-align: center;">${materialsize}
						</td>
						<td style="text-align: center;"><input type="text" class="form-control " style="width: 98%;"></td>
						<td style="text-align: left;">
							<div id="proofFile${materialindex}" class="btn btn-xs btn-success"
								 style="margin-left: 0px; margin-right: 0px;">添加附件</div> <span
									id="fujian_list${materialindex}"> </span>
						</td>
						<td style="text-align: center;"><a class="btn-red" href="javascript:void(0)" onclick="deleteM(this);">删除</a></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
<script type="text/javascript">
	$(function(){
		initTime();
		var tr=$("#materialcontent").find("tr[name='materialinfo']");
		for(var i=0;i<tr.length;i++){
			fileUpload(i);
		}
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




	function saveinfo() {
		var plconditions=$("#plconditions").val();
		var rdescription=$("#rdescription").val();
		if(plconditions==""||plconditions==null||plconditions==undefined){
			bootbox.alert('请填写政策条件！');
			return;
		}
		if(rdescription==""||rdescription==null||rdescription==undefined){
			bootbox.alert('请填写奖励描述！')
			return;
		}else{
			if(mValidate()){
				$("#formid").submit();
			}
		}

	}


	function deleteM(e) {
		$(e).parent().parent().remove();
		var tr=$("#materialcontent").find("tr[name='materialinfo']");
		for(var i=0;i<tr.length;i++){
			$(tr[i]).children("td:eq(0)").text(i+1);
			var html='<input type="hidden" class="form-control " name="materiallists['+i+'].sn" id="materiallists['+i+'].sn"  value="'+(i+1)+'"   >';
			$(tr[i]).children("td:eq(0)").append(html);
			$(tr[i]).children("td:eq(0)").children("input").attr("name","materiallists["+i+"].sn").attr("id","materiallists["+i+"].sn").val(i+1);
			$(tr[i]).children("td:eq(1)").children("input").attr("name","materiallists["+i+"].materialname").attr("id","materiallists["+i+"].materialname");
			$(tr[i]).children("td:eq(2)").children("span").children("span").children("input:eq(0)").attr("name","materiallists["+i+"].modelfilename").attr("id","materiallists["+i+"].modelfilename");
			$(tr[i]).children("td:eq(2)").children("span").children("span").children("input:eq(1)").attr("name","materiallists["+i+"].modelfileid").attr("id","materiallists["+i+"].modelfileid");
		}
	}



	//添加
	function addmaterial() {
		//校验上一条资格信息
		var dome = $("#materialcontent tr:last");
		var newdemo=$("#materialdome tr:last").clone();
		var index=parseInt(dome.children("td:eq(0)").text());
		if(isNaN(index)){
			index=0;
		}
		newdemo.children("td:eq(0)").text(index+1);
		var html='<input type="hidden" class="form-control " name="materiallists['+index+'].sn" id="materiallists['+index+'].sn"  value="'+(index+1)+'"   >';
		newdemo.children("td:eq(0)").append(html);
		newdemo.children("td:eq(0)").children("input").attr("name","materiallists["+index+"].sn").attr("id","materiallists["+index+"].sn").val(index+1);
		newdemo.children("td:eq(1)").children("input").attr("name","materiallists["+index+"].materialname").attr("id","materiallists["+index+"].materialname");
		newdemo.children("td:eq(2)").children("span").text("");
		newdemo.children("td:eq(2)").children("div").attr("id","proofFile"+index);
		newdemo.children("td:eq(2)").children("span").attr("id","fujian_list"+index);
		newdemo.children("td:eq(3)").children("a").attr("onclick","deleteM(this);");
		newdemo.attr("id","demo"+(index+1));
		$("#materialcontent").append(newdemo);
		fileUpload(index);
	}



	function cancle(plfidid) {
		window.location.href=base + "/policyfilemgr/policydetail/"+plfidid;
	}

	function fileUpload(e) {
		var uploader = WebUploader.create({
			auto : true,
			swf: base + '/assets/js/common/webuploader/Uploader.swf',
			server : base + '/common/file',
			fileSingleSizeLimit : 20 * 1024 * 1024, // 5 M
			fileVal : "upfile",
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : "#proofFile"+e,
			duplicate : true
			// 只允许选择图片文件。
			//accept : {
			//title : 'docs',
			//extensions : 'doc,docx',
			//mimeTypes : 'image/*'
			//}

		});
		//uploadAccept
		//uploader.on( 'uploadAccept', function( object , ret  ) {
		//});
		uploader
				.on(
						'uploadSuccess',
						function(file, response) {
							var info = eval("(" + response._raw + ")");
							if (typeof (info['state']) != 'undefined') {
								if (info['state'] == 'SUCCESS') {
									var $divs = $("#fujian_list"+e+" div");
									var name = info['name'];
									var size = info['fileSize'];
									var id = info['id'];
									var html = "";
									if ($divs.length > 0) {
										html = html
												+"<span name='fjlist'>"
												+ "<input type='hidden' name='materiallists["+e+"].modelfilename' id='materiallists["+e+"].modelfilename' value='"+name+"'>"
												+ "<input type='hidden' name='materiallists["+e+"].modelfileid' id='materiallists["+e+"].modelfileid'  value='"+id+"'>"
												+ "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
												+ "<span> </span>"
												+ "<a style='color:red' href='javascript:void(0)' onclick='moredeletefujian(this)'>[删除]</a>"
												+"</span>"
										$("#fujian_list"+e+" div:last").html(html);
									} else {
										html = html
												+"<span name='fjlist'>"
												+ "<input type='hidden' name='materiallists["+e+"].modelfilename'  id='materiallists["+e+"].modelfilename' value='"+name+"'>"
												+ "<input type='hidden' name='materiallists["+e+"].modelfileid'   id='materiallists["+e+"].modelfileid' value='"+id+"'>"
												+ "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
												+ "<span> </span>"
												+ "<a style='color:red' href='javascript:void(0)' onclick='moredeletefujian(this)'>[删除]</a>"
												+"</span>"
										$("#fujian_list"+e).html(html);
									}

								} else {
									bootbox.alert('上传出错');
								}
							} else {
								bootbox.alert('上传出错');
							}

						});

		uploader.on('uploadError', function(file) {
			bootbox.alert('上传出错');
		});
		uploader.on('error', function(type) {
			if ("F_EXCEED_SIZE" == type) {
				bootbox.alert('图片大小不能超过20M！');
				return;
			}
			if ("Q_TYPE_DENIED" == type) {
				bootbox.alert('文件类型不正确！');
				return;
			}

		});
	}


	function moredeletefujian(ele){
		$(ele).parent().remove();
		var fujianfileids=$('#fujianfileids').val();
		$('#checkfileids').val(fujianfileids);
	}


</script>
[/@layout.main]