$(function() {
	$("#tree_1").jstree();
	$("#tree_1").jstree(true).toggle_node("j1_1");
	FormValidation.init();
});


function showMsaorgan(organid, organname,organlevel) {
	$("#addMsaorgan").show();
	$("#addMasclerk").show();
	$("#organid").val(organid);
	$("#superorganid").val(organid);
	$("#organlevelid").val(organlevel);
	$("#organidnew").val(organid);
	$("#organnamenew").val(organname);
	$("#organidaddnew").val(organid);
	$("#organnameaddnew").val(organname);
	
	initMsaorgan();
	refreshMasclerksTable();
}


function showMsaorganTwo(organid, organname,organlevel) {
//	$("#addMsaorgan").show();
//	$("#addMasclerk").show();
	$("#organid").val(organid);
	$("#superorganid").val(organid);
	$("#organlevelid").val(organlevel);
	$("#organidnew").val(organid);
	$("#organnamenew").val(organname);
	$("#organidaddnew").val(organid);
	$("#organnameaddnew").val(organname);
	initMsaorgan();
//	refreshMasclerksTable();
}

function initMsaorgan(){
	$.ajax({
		url : base + "/sys/msaorgan/" + $("#organid").val() + "/show",
		type : "GET",
		async : false,
		cache : false,
		dataType : "json",
		success : function(data) {
			$("#organname").val(data.organname);
			$("#organaddress").val(data.organaddress);
			$("#linkman").val(data.linkman);
			$("#linkphone").val(data.linkphone);
			$("#jnfkdd").val(data.jnfkdd);
			$("#postalcode").val(data.postalcode);
			$("#portid").children("option").each(function(index, element){
				if(data.portid != "" && data.portid != "null"){
					if(element.value == data.portid){
						element.selected = "selected";
					}else{
						element.selected = "";
					}
				}else{
					if(element.value = ""){
						element.selected = "selected";
					}
				}
			});
			$("#fydw").val(data.fydw);
		},
		error : function() {
		}
	});
}

function msaorganSubmit() {
	var organid = $("#organid").val();
	if(organid==""||organid==undefined){
		bootbox.alert("未选择机构");
		return false;
	}
	$.ajax({
		url : base + "/sys/msaorgan/updatemsaorgan",
		type : "POST",
		async : false,
		dataType : "text",
		data : $('#msaorgan').serialize(),
		success : function(data) {
			if(pageorglevel!='4'){
				$("a").filter(".jstree-clicked").html(
						'<i class="jstree-icon jstree-themeicon"></i> '
								+ $("#organname").val());
				if(data=="success"){
					bootbox.alert("保存成功！");
				}else{
					bootbox.alert("保存失败！");
				}
			}else{
				window.location.href = base + "/sys/msaorgan";
			}
		}
	});
}

