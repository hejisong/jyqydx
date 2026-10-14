function trim(value) {
	  // native trim is way faster: http://jsperf.com/angular-trim-test
	  // but IE doesn't have it... :-(
	  // TODO: we should move this into IE/ES5 polyfill
	  if (!String.prototype.trim) {
	      return isString(value) ? value.replace(/^\s\s*/, '').replace(/\s\s*$/, '') : value;
	  }
	  return isString(value) ? value.trim() : value;
}

function isString(value) {
	return typeof value === 'string';
}

//非空，最大长度
function vali(value,valuedescrip,required,maxlength){
	var message="";
	if(required==true){
	 	if(value==null||value==undefined||trim(value).length==0){
			message=message+"请输入"+valuedescrip+"！<br>";
		}
	}
	if(maxlength!=false){
	 	if(value!=null&&value!=undefined&&trim(value).length>maxlength){
			message=message+valuedescrip+"长度不能超过"+maxlength+"个字符！<br>";
		}
	}

 	return message;
 }
//必须为数字，数字最大值 
function vali_number(value,valuedescrip,number,maxnumber){
	var message="";
 	if(number==true){
	 	if(value!=null&&value!=undefined&&isNaN(value)){
			message=message+valuedescrip+"请输入数字！<br>";
		}
	}
	if(maxnumber!=false){
	 	if(value!=null&&value!=undefined&&value>maxnumber){
			message=message+valuedescrip+"必须小于等于"+maxnumber+"<br>";
		}
	 	if(value!=null&&value!=undefined&&value<0){
			message=message+valuedescrip+"必须大于等于0<br>";
		}
	}
 	return message;
 }
//扣分必须为数字，数字最大值 
function vali_score(value,valuedescrip,number,maxnumber){
	var message="";
 	if(number==true){
	 	if(value!=null&&value!=undefined&&isNaN(value)){
			message=message+valuedescrip+"请输入数字！<br>";
		}
	}
	if(maxnumber!=false){
	 	if(value!=null&&value!=undefined&&value>maxnumber){
			message=message+valuedescrip+"必须小于等于"+maxnumber+"<br>";
		}
	 	if(value!=null&&value!=undefined&&value<(-maxnumber)){
			message=message+valuedescrip+"必须大于等于-"+maxnumber+"<br>";
		}
	}
 	return message;
 }
 //日期比较
 function vali_date(date_small,date_smallscrip,date_big,date_bigscrip){
	 var message="";
	 if(date_smallscrip!=null&&date_big!=null&&date_smallscrip!=undefined&&date_big!=undefined){
		 var datesmall = new Date(date_small.replace("-","/"));
		 var datebig = new Date(date_big.replace("-","/"));
 		if( datebig.getTime() <= datesmall.getTime()){
 			message=message+date_bigscrip+"不能早于等于"+date_smallscrip+"！<br>";
		}
 	}
	return message;
 }
 
 function vali_date1(date_small,date_smallscrip,date_big,date_bigscrip){
	 var message="";
	 if(date_smallscrip!=null&&date_big!=null&&date_smallscrip!=undefined&&date_big!=undefined){
		 var datesmall = new Date(date_small.replace("-","/"));
		 var datebig = new Date(date_big.replace("-","/"));
 		if( datesmall.getTime() < datebig.getTime()){
 			message=message+date_smallscrip+"不能早于"+date_bigscrip+"！且不在本单位的履历信息的时间段内<br>";
		}
 	}
	return message;
 }
 
 function vali_date2(date_small,date_smallscrip,date_big,date_bigscrip){
	 var message="";
	 if(date_smallscrip!=null&&date_big!=null&&date_smallscrip!=undefined&&date_big!=undefined){
		 var datesmall = new Date(date_small.replace("-","/"));
		 var datebig = new Date(date_big.replace("-","/"));
 		if( datesmall.getTime() > datebig.getTime()){
 			message=message+date_smallscrip+"不能晚于"+date_bigscrip+"！<br>";
		}
 	}
	return message;
 }
 
 function vali_file(e,desc){
	var message="";
	if($(e).length==0){
		message="请上传"+desc+"附件！<br>";
	}
	return message;
}


function alertFormValidation_orguser(){
	var bool=true;
	var username = $("#org_username").val();
	var loginname = $("#org_loginname").val();
	var orgname = $("#org_organname").val();
	var linknum = $("#org_linknum").val();
	var linktel = $("#org_linktel").val();
	var linkemail = $("#org_linkemail").val();
	var datatype=$("#org_datatype").val();
	var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	var reg1 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	var message="";
	if(datatype=='1'){
		message= vali(username,"姓名",true,20)
			+vali(loginname,"登录名",true,20)
			+vali(linktel,"手机号码",true,50)
			+vali_number(linktel,"手机号码" ,true,false);
	}else{
		message= vali(username,"姓名",true,20)
			+vali(loginname,"登录名",true,20)
			+vali(linktel,"手机号码",true,50)
			+vali_number(linktel,"手机号码" ,true,false)
			+vali(linknum,"身份证号码",true,50)
			/*+vali_number(linknum,"身份证号码" ,true,false)*/
			+vali(linkemail,"联系人电子邮箱",true,50);
	}


	if(message!=""){
		bootbox.alert(message);
		bool=false
	}
	if(datatype=='1'){
		if(linktel!=null && linktel!="" && linktel!= undefined &&!(/^1(3|4|5|7|8)\d{9}$/.test(linktel))){
			bootbox.alert("请正确填写手机号码！");
			bool=false
		}
	}else{
		if(linkemail!=null && linkemail!="" && linkemail!= undefined && reg.test(linknum)==false){
			bootbox.alert("请正确填写身份证号！");
			bool=false
		}
		if(linktel!=null && linktel!="" && linktel!= undefined &&!(/^1(3|4|5|7|8)\d{9}$/.test(linktel))){
			bootbox.alert("请正确填写手机号码！");
			bool=false
		}
		if(linkemail!=null && linkemail!="" && linkemail!= undefined && reg1.test(linkemail)==false){
			bootbox.alert("请正确填写电子邮箱格式！");
			bool=false
		}
	}

	return bool;
}

function alertFormValidation_organ(){
	var bool=true;
	var orgname=$("#orgname").val();
	var linkman=$("#linkman").val();
	var linkphone=$("#linkphone").val();
	var linkemail=$("#linkemail").val();
	var reg1 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

	var message=  vali(orgname,"企业名称	",true,50)
		+vali(linkman,"联系人姓名",true,10)
		+vali(linkphone,"联系人手机",true,20)
		+vali_number(linkphone,"联系人手机" ,true,false)
		+vali(linkemail,"联系人电子邮箱",true,50);
	if(message!=""){
		bootbox.alert(message);
		bool=false
	}
	if(linkphone!=null && linkphone!="" && linkphone!= undefined &&!(/^1(3|4|5|7|8)\d{9}$/.test(linkphone))){
		bootbox.alert("请正确填写联系人手机！");
		bool=false
	}
	if(linkemail!=null && linkemail!="" && linkemail!= undefined &&reg1.test(linkemail)==false){
		bootbox.alert("请正确填写电子邮箱格式！");
		bool=false
	}
	return bool;
}


//申报材料
function mValidate(){
	var bool=true;
	var message= ""
	var trs=$("#materialcontent").find("tr[name='materialinfo']");
	$.each(trs, function(index,tr){
		var materialname=$(tr).children("td:eq(1)").children("input").val();
		var prem="材料信息"+(index+1)+"：";
		message=message+vali(materialname,prem+"材料名称",true,200,"materialname");

	})
	if(message!=""){
		bootbox.alert(message);
		bool=false
	}
	return bool;

}

