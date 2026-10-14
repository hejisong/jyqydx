$(function(){
//	var $p = $("#printpage");
//	if($p!=undefined&&$p.length>0){
//		printPage();
//	}
	$(".btn-white").bind("click",function(ele){
		var href = $(this).attr("href");
		if(href!=undefined){
			window.location.href = href;
		}
		
	});
	$(".btn-active").bind("click",function(ele){
		var href = $(this).attr("href");
		if(href!=undefined){
			window.location.href = href;
		}
	});
});
var parseDatetime = function(millis){
	if(millis==null){
		return "";
	}
	var nowDate = new Date();
	var d = new Date();
	d.setTime(millis);
	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	var dateHtml = year+"-"+month+"-"+day;
	return dateHtml;
};
var parseDatetimehhmmss = function(millis){
	if(millis==null){
		return "";
	}
	var nowDate = new Date();
	var d = new Date();
	d.setTime(millis);
	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	var hour = d.getHours();
 	var minute = d.getMinutes();
	var second = d.getSeconds();
	if(month<10){
		month="0"+month;
	}
	if(day<10){
		day="0"+day;
	}
	if(hour<10){
		hour="0"+hour;
	}
	if(minute<10){
		minute="0"+minute;
	}
	if(second<10){
		second="0"+second;
	}
	var dateHtml = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return dateHtml;
};
//var printPage = function(){
//	var url = base + "/common/print"
//	html2canvas( $("body") ,{  		
//		onrendered: function(canvas){
//			$("#printpage").attr("href",canvas.toDataURL())
//		}
//	});
//}