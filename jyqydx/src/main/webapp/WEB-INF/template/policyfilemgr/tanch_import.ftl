
<div class="dialog" id="importexceltanchu" style="height: 300px; width: 800px;">

	<input type="hidden" id="plfidid">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background:#428bca; ">
						<div class="navbar-header pull-left">
							<a href="#" class="navbar-brand"><small>导入政策明细</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closeImport()"><img
								src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>

					<div class="portlet-body form">



						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
								<div class="form-group"
									style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<label class="col-xs-2 control-label no-padding-right"
										style="text-align: right;">请上传文件</label>
									<div id="proofFile" class="btn btn-xs btn-success"
										style="margin-left: 0px; margin-right: 0px;">添加附件</div>
									<span id="fujian_list"> </span>
								</div>
							</div>
						</div>
						<br>
						<br>
						<br>

						<div class="clearfix form-actions" style="text-align: center;">
							<button class="btn btn-sm btn-info btn-width80" id="importdata" type="button" onclick="importExcel()">导入</button>

							<button class="btn btn-sm btn-width80" id="noimport" type="button" onclick="closeImport()">取 消</button>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</div>


<script type="text/javascript">
	function importExcel() {
        var plfidid=$("#plfidid").val();
		var uploadfileid = $("#uploadfileid").val();
		if(uploadfileid==""||uploadfileid==null||uploadfileid==undefined){
			bootbox.alert("请上传文件！");
			return;
		}

		$("#loading_state").val("1");
		$.ajax({
			type : "post",
			url : base + "/policyfilemgr/import",
			data : {uploadfileid:uploadfileid,plfidid:plfidid},
			async:true,  
			success : function(data) {
				var data0 = data[0];
				var data1 = data[1];
				if (data0 == "SUCCESS") {
					bootbox.alert("导入成功！共导入数据" + data1 + "条！", function() {
						closeImport();
						location.reload();
					});
					
				} else {
					bootbox.alert("导入失败！" + data1);
				}
			},
			error : function() {
				bootbox.alert("数据加载失败");
			}
		});
	}

	function openImport(plfidid) {
		$("#plfidid").val(plfidid);
		if ($("#formstate").val() == "Y") {
			$("#formstate").val("N");
		} else {
			return;
		}
		importfileUpload();
		$("#fullbg,#importexceltanchu").show();

	}
	function closeImport() {
		$("#fullbg,#importexceltanchu").hide();
		$("#formstate").val("Y");
	}

	function importfileUpload() {
		var uploader = WebUploader.create({
			auto : true,
			swf : base + '/assets/js/common/webuploader/Uploader.swf',
			server : base + '/common/file',
			fileSingleSizeLimit : 20 * 1024 * 1024, // 10 M
			fileVal : "upfile",
			pick : '#proofFile',
			duplicate : true,
			accept : {
				//title : 'docs',
				extensions : 'xls,xlsx',
			//mimeTypes : 'image/*'
			}

		});
		uploader
				.on(
						'uploadSuccess',
						function(file, response) {
							var info = eval("(" + response._raw + ")");
							if (typeof (info['state']) != 'undefined') {
								if (info['state'] == 'SUCCESS') {
									var $divs = $("#fujian_list");
									var name = info['name'];
									var size = info['fileSize'];
									var id = info['id'];
									var html = "<input type='hidden' id='uploadfilename' name='uploadfilename' value='"+name+"'>"
											+ "<input type='hidden' id='uploadfileid' name='uploadfileid'  value='"+id+"'>"
											+ "<a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"
											+ name + "</a>" + "<span> </span>"
									$("#fujian_list").html(html);

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
				bootbox.alert('文件大小不能超过20M！');
				return;
			}
			if ("Q_TYPE_DENIED" == type) {
				bootbox.alert('文件类型不正确！');
				return;
			}

		});
	}
</script>
 
 