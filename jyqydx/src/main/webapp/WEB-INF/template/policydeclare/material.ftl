[#assign jss=["assets/js/jquery.validate.min.js" ,"assets/js/validate.js" ,"assets/js/common/webuploadernew/webuploader.js"]]
[#import "common/layout.ftl" as layout]
[#assign css=["assets/css/zhemutanchukuang.css","assets/css/datetimepicker.css"]]
[@layout.main sername=['政策管理','政策申报通知'] modelcode=["qyzc","policydeclare"] otherjs=jss othercss=css]
  <div class="row">
  <div class="col-xs-12 " style="padding-top: 15px; padding-bottom: 15px; margin-bottom: 10px;">
	  <p style="text-align: center;font-size: 20px;font-weight: 600;">${policyname}-${nplcategory}-申报材料清单</p>

	</div>

	<div class="col-xs-12">
		<div class="table-responsive">

			<button class="btn btn-xs btn-info" type="button" onclick="addmaterial();">新 增</button>
			<form id="materialinfo" action="${base}/policydeclare/materialsave" method="post">
				<input type="hidden" name="materialid" value="${material.materialid}">
				<input type="hidden" name="policyfileid" value="${material.policyfileid}">
				<input type="hidden" name="nplcategory" value="${material.nplcategory}">
				<input type="hidden" name="policyyear" value="${material.policyyear}">
				<input type="hidden" name="declareid" value="${declareid}">


			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>

						<th style="width:8%;">序号</th>
						<th style="width:48%;">材料名称</th>
						<th style="width:40%;">模板附件</th>
						<th style="width:8%;">操作</th>
					</tr>
				</thead>
				<tbody id='materialcontent'>
				[#list materiallists as material ]
					<tr name="materialinfo">
						<td>${material_index+1}
							<input type="hidden" id="materiallists[${material_index}].sn" name="materiallists[${material_index}].sn" value="${material_index+1}">
							<input type="hidden" id="materiallists[${material_index}].listid" name="materiallists[${material_index}].listid" value="${material.listid }">

						</td>
						<td>${material.materialname}
							<input type="hidden" id="materiallists[${material_index}].materialname" name="materiallists[${material_index}].materialname" value="${material.materialname}"></td>
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


			<div class="clearfix form-actions" style="text-align: center;">
				<button class="btn btn-sm btn-info" type="button" onclick="saveinfo();" >保 存</button>
				<button class="btn btn-sm" type="button" onclick="history.go(-1)">取 消</button>
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
	
</div>
<script type="text/javascript">
	$(function(){
		var tr=$("#materialcontent").find("tr[name='materialinfo']");
		for(var i=0;i<tr.length;i++){
			fileUpload(i);
		}
	});

	function saveinfo() {
	  if(mValidate()){
		$("#materialinfo").submit();
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


	function deletecert(ele,e){
		if(e==1){
			$(ele).parent().parent().remove();
		}else{
			bootbox.confirm("确认删除该材料信息?",function (result) {
				if(result==true){
					$("#loading_state").val("1");
					$.ajax({
						url : base+ "/xyjsmgr/gl/corp/personinfo/grxx/zgxx/delete/"+e,
						type : "POST",
						async : true,
						datatype:'json',
						success : function(data) {
							bootbox.alert("删除成功！", function() {
								location.reload();
							});

						},
						error : function() {
							bootbox.alert("删除失败！");
						}
					});
				}

			} )
		}

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



	function deleteinfo(id) {
		bootbox.confirm("确认删除该政策文件?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/0";
			}

		})

	}

	function punishinfo(id) {
		bootbox.confirm("确认发布该政策申报通知?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/2";
			}

		})

	}

	function notpunishinfo(id) {
		bootbox.confirm("确认撤销发布该政策申报通知?",function (result) {
			if(result==true){
				window.location.href=base + "/policydeclare/updatestate/"+id+"/3";
			}

		})

	}






</script>
[/@layout.main]