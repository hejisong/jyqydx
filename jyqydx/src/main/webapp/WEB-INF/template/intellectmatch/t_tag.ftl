<!-- 遮挡层 -->
<div class="dialog" id="tag_dialog" style="display:none;height:600px;width:85%;overflow-y: auto;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="navbar navbar-default" style="background-color: #296FEE;">
						<div class="navbar-header pull-left" >
							<a href="#" class="navbar-brand"><small>选择标签</small></a>
						</div>
						<p class="close">
							<a class="navbar-brand" onclick="closetag()"><img src="${base}/assets/images/gb.png"></img></a>
						</p>
					</div>



					<div class="portlet-body form">
						<form class="form-horizontal" id="tagform"  >
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable" style="height:450px;overflow-y:auto;width: 100%;">
								<table id="sample-table-project" class="table table-striped table-bordered table-hover">
									<thead>
									<tr>
										<th style="width:15%;">政策大类</th>
										<th style="width:15%;">政策小类</th>
										<th style="width:20%;">政策条件</th>
										<th style="width:50%;">政策标签</th>


									</tr>
									</thead>
									<tbody id="detailcontent">

									</tbody>
								</table>
							</div>

							<p style="padding-top: 5px;">共<span id="totalsize">0</span>条记录</p>

							<div class="col-xs-12 control-label no-padding-right" style="text-align: center;margin-top: 15px;">
								<button class="btn btn-xs btn-info btn-width"   type="button" onclick="saveinfo();">确定</button>
								<button class="btn btn-sm"    type="button" onclick="closetag();">取消</button>
							</div>

						</div>
						</form>
					</div>
				</div>






			</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//打开用户弹出框
function openTag() {
    var pldtailid=$("#pldtailid").val();
	$.ajax({
		type:"POST",
		url:base+"/intellectmatch/policydetail" ,
		data:{pldtailid:pldtailid},
		dataType:"json",
		success:function(data){
			initdetail(data);
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});


 	$("#fullbg").show();
  	$("#tag_dialog").show();
}


function initdetail(data) {
	var html="";
	var totalsize=0;
	$.each(data[0],function(i,detail){
		totalsize=totalsize+1;
		var phtml="";
		var nplcategory="";
		var plsubclassname="";
		var plconditions="";
		if(detail.nplcategory!=null){
			nplcategory=detail.nplcategory;
		}
		if(detail.plsubclassname!=null){
			plsubclassname=detail.plsubclassname;
		}
		if(detail.plconditions!=null){
			plconditions=detail.plconditions;
		}
        var ptags=detail.atags;
        var tags=data[1];

		$.each(tags,function(j,tag){
			var flag=false;
			var tagname=tag.tagid+"-"+tag.tagname;
			for(k=0;k<ptags.length;k++){
				if(ptags[k].tagid==tag.tagid){
					flag=true;
					break;
				}
			}
			if(flag){
				phtml+='<input style="font-size: 15px;margin-left: 5px;" name="details['+i+'].atags['+j+'].tagid" type="checkbox" checked value="'+tagname+'" >'+tag.tagname;
			}else{
				phtml+='<input style="font-size: 15px;margin-left: 5px;" name="details['+i+'].atags['+j+'].tagid" type="checkbox"  value="'+tagname+'" >'+tag.tagname;
			}


		})

		html+='<tr>'
				+'<td style="text-align: center;">'+nplcategory+'</td>'
				+'<td style="text-align: center;">'+plsubclassname+'</td>'
		        +'<td style="text-align: center;">'+plconditions+'</td>'
				+'<td style="text-align: left;"><input type="hidden" value="'+detail.pldtailid+'" name="details['+i+'].pldtailid">'+phtml+'</td>'
		        +'</tr>';


	});



	$("#detailcontent").html(html);

	$("#totalsize").text(totalsize);

}

//关闭用户弹出框
function closetag() {
	$("#fullbg,#tag_dialog").hide();
}


function saveinfo() {
	// $("#forminfo").submit();
	// initinfo();
	$("#loading_state").val("1");
	$.ajax({
		type:"post",
		url:base + "/accuratetag/savepolicytag",
		async:true,
		data: $("#tagform").serialize(),
		success:function(data){
			if(data=="SUCCESS"){
				bootbox.confirm("保存成功！",function (result) {
					var pldtailids=$("#pldtailid").val();
					$.ajax({
						type:"POST",
						url:base+"/intellectmatch/querytag" ,
						data:{pldtailids:pldtailids},
						dataType:"json",
						success:function(data){
							inittag(data);
							closetag();
						},
						error:function(){
							bootbox.alert("数据加载失败");
						}
					});
				})

			}else{
				bootbox.alert("保存失败！");
			}
		},
		error:function(){
			bootbox.alert("数据加载失败");
		}
	});
}



function chooseTag() {
	var trs=$("#resulttag").find("tr");
	var html="";
	var tagids="";
	$.each(trs, function(e,tr){
		var checkbox=$(tr).children("td:eq(0)").find("input");
		if($(checkbox)[0].checked==true){
			var tagid=$(checkbox).val();
			var tagname=$(tr).children("td:eq(1)").text();
			tagids+=tagid+",";
			html+='<span style="margin-left: 10px;background-color: #E0EBFF;padding-left: 5px;padding-right: 5px;">'+tagname+'</span>';

		}

	})


	$("#tagname").html(html);
	$("#tagid").val(tagids);
	closetag();
}
</script>