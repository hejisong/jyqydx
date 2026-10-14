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
function vali(value,valuedescrip,required,maxlength,valueid){
	var message="";
	if(required==true){
	 	if(value==""||value==undefined||trim(value).length==0){
			message=message+"请输入"+valuedescrip+"！<br>";
			$("#"+valueid).attr("style","border-color: #f09784;");
		}
	}
	if(maxlength!=false){
	 	if(value!=null&&value!=undefined&&trim(value).length>maxlength){
			message=message+valuedescrip+"长度不能超过"+maxlength+"个字符！<br>";
			$("#"+valueid).attr("style","border-color: #f09784;");
		}
	}

 	return message;
 }
//必须为数字，数字最大值 
function vali_number(value,valuedescrip,number,maxnumber){
	var message="";
 	if(number==true){
	 	if(value!=""&&value!=undefined&&isNaN(value)){
			message=message+valuedescrip+"请输入数字！<br>";
		}
	}
	if(maxnumber!=false){
	 	if(value!=""&&value!=undefined&&value>maxnumber){
			message=message+valuedescrip+"必须小于等于"+maxnumber+"<br>";
		}
	 	if(value!=""&&value!=undefined&&value<0){
			message=message+valuedescrip+"必须大于等于0<br>";
		}
	}
 	return message;
 }
//扣分必须为数字，数字最大值 
function vali_score(value,valuedescrip,number,maxnumber,evaltype,valueid){
	var message="";
 	if(number==true){
 		if(value==""){
 			message=message+valuedescrip+"请输入扣分！<br>";
 			$("#"+valueid).attr("style","border-color: #f09784;");
 		}
 		else if(value!=""&&value!=undefined&&isNaN(value)){
			message=message+valuedescrip+"请输入数字！<br>";
			$("#"+valueid).attr("style","border-color: #f09784;");
		}
	 	else if(evaltype=="1"||evaltype=="2"||evaltype=="3"){
	 		if(value>0){
	 			message=message+valuedescrip+"扣分请输入负值！<br>"
	 			$("#"+valueid).attr("style","border-color: #f09784;");
	 		}
	 	}
	}
	if(maxnumber!=false){
	 	if(value!=""&&value!=undefined&&value>maxnumber){
			message=message+valuedescrip+"必须小于等于"+maxnumber+"<br>";
			$("#"+valueid).attr("style","border-color: #f09784;");
		}
	 	if(value!=""&&value!=undefined&&value<(-maxnumber)){
			message=message+valuedescrip+"必须大于等于-"+maxnumber+"<br>";
			$("#"+valueid).attr("style","border-color: #f09784;");
		}
	}
 	return message;
 }
 //日期比较
 function vali_date(date_small,date_smallscrip,date_big,date_bigscrip){
	 var message="";
	 if(date_small!=""&&date_big!=""&&date_small!=undefined&&date_big!=undefined){
		 var datesmall = new Date(date_small.replace("-","/"));
		 var datebig = new Date(date_big.replace("-","/"));
 		if( datebig.getTime() <= datesmall.getTime()){
 			message=message+date_bigscrip+"不能早于等于"+date_smallscrip+"！<br>";
 			$("#"+date_big).attr("style","border-color: #f09784;");
		}
 	}
	return message;
 }
 
//只能选择一个有效期
function vali_two_end_date(value1,valuedescrip,value2){
	 	var message="";
	 	if(((value1==null||value1==undefined||trim(value1).length==0)&&(value2==null||value2==undefined||trim(value2).length==0))
	 	||((value1!=null&&value1!=undefined&&trim(value1).length!=0)&&(value2!=null&&value2!=undefined&&trim(value2).length!=0))){
	 		message=message+"请选择"+valuedescrip+"(长期或者日期只能选择一个)"+"！<br>";
	 	}
	 	return message;
}
 //附件上传
 function vali_file(value,valuedescrip){
 	var message=""
 	if(value==null||value==undefined||trim(value).length==0){
 		message=message+"请上传"+valuedescrip+"！<br>";
 	}
 	return message;
 }
 
 function vali_file1(value,valuedescrip,required,maxlength){
	 	var message=""
	 	if(required){
	 		if(value==null||value==undefined||trim(value).length==0){
		 		message=message+"请上传"+valuedescrip+"！<br>";
		 	}	
	 	}	
	 	
	 	if(value !=null&&value !=""&&value.length>maxlength){
	 		message=message+valuedescrip+"最多不能超过6个！<br>";
	 	}
	 	return message;
	 }
 
 function valiselect(value,valuedescrip){
	 	var message=""
	 	 	if(value==null||value==undefined||trim(value).length==0){
	 	 		message=message+"请选择"+valuedescrip+"！<br>";
	 	 	}
	 	 	return message;
	 }


