 function cancleshow(ele){
	 var type=$(ele).attr("name");
	 var infoid=$(ele).attr("id");
	 if(type=='6'){
		 var index=$(ele).val();
	 }
	 bootbox.confirm("确认要撤销发布该信息?",function (result) {
			if(result==true){
				$.ajax({
					 url : base+ "/xyjsmgr/corpmgr/corpinfosearch/cancleshow/"+type+"/"+infoid,
					 type : "POST",
					 async : true,
					 datatype:'text',
					 success : function(data) {
						 var html="";
						 if(type=='1'){
							 html="<button class='btn btn-xs btn-info' type='button' onclick='show(this);'  id='"+infoid+"' name='"+type+"' style='float: right;'>发布</button>"
						 }
						 else if(type=='6'){
							 html="<button class='btn btn-xs btn-info'  type='button' onclick='showBg2("+index+")'>查看详情</button>&nbsp;"
							      +"<button class='btn btn-xs btn-info' type='button' onclick='show(this);'  id='"+infoid+"' name='"+type+"'>发布</button>"
						 }else{
							 html="<button class='btn btn-xs btn-info' type='button' onclick='show(this);'  id='"+infoid+"' name='"+type+"'>发布</button>"
						 }
						 var td=$(ele).parent();
						 $(td).html("");
						 $(td).html(html);
						 var state=$("#"+infoid);
						 state.html("撤销发布");
						 bootbox.alert("撤销发布成功！");
					 },
					 error : function() {
					 bootbox.alert("撤销发布失败！");
					 }
					 }); 
			}
		 
	 })
	 
 }
 
 function show(ele){
	 var type=$(ele).attr("name");
	 var infoid=$(ele).attr("id");
	 if(type=='6'){
		 var index=$(ele).val();
	 }
	 bootbox.confirm("确认要发布该信息?",function (result) {
			if(result==true){
				$.ajax({
					 url : base+ "/xyjsmgr/corpmgr/corpinfosearch/show/"+type+"/"+infoid,
					 type : "POST",
					 async : true,
					 datatype:'text',
					 success : function(data) {
						 var html="";
						 if(type=='1'){
							 html="<button class='btn btn-xs btn-info' type='button' onclick='cancleshow(this);'  id='"+infoid+"' name='"+type+"' style='float: right;'>撤销发布</button>"
						 }
						 else if(type=='6'){
							 html="<button class='btn btn-xs btn-info'  type='button' onclick='showBg2("+index+")'>查看详情</button>&nbsp;"
						         +"<button class='btn btn-xs btn-info' type='button' onclick='cancleshow(this);'  id='"+infoid+"' name='"+type+"'>撤销发布</button>"
						 }else{
							 html="<button class='btn btn-xs btn-info' type='button' onclick='cancleshow(this);'  id='"+infoid+"' name='"+type+"'>撤销发布</button>"
						 }
						 var td=$(ele).parent();
						 $(td).html("");
						 $(td).html(html);
						 var state=$("#"+infoid);
						 state.html("审核通过");
						 bootbox.alert("发布成功！");
					 },
					 error : function() {
					 bootbox.alert("发布失败！");
					 }
					 });
			}
		 
	 })
	
 }
 
 
 
 function xmcancleshow(ele){
	 var type=$(ele).attr("name");
	 var infoid=$(ele).attr("id");
	 bootbox.confirm("确认要撤销发布该信息?",function (result) {
			if(result==true){
				 $.ajax({
					 url : base+ "/xyjsmgr/corpmgr/xminfosearch/cancleshow/"+type+"/"+infoid,
					 type : "POST",
					 async : true,
					 datatype:'text',
					 success : function(data) {
						 var html="";
						 if(type=="0"){
							 html="<a class='btn btn-xs btn-success' href='"+base+"/xyjsmgr/corpmgr/xminfosearch/bdxxInfoshow/"+infoid+"' target='_blank'>查看</a>&nbsp;"
							 +"<button class='btn btn-xs btn-info' type='button' onclick='xmshow(this);'  id='"+infoid+"' name='"+type+"'>发布</button>"
						 }else{
							 html="<a class='btn btn-xs btn-success' href='"+base+"/xyjsmgr/corpmgr/xminfosearch/xmjbxxInfoshow/"+infoid+"' target='_blank'>查看详细</a>&nbsp;"
							 +"<button class='btn btn-xs btn-info' type='button' onclick='xmshow(this);'  id='"+infoid+"' name='"+type+"'>发布</button>"
						 }
						 var td=$(ele).parent();
						 $(td).html("");
						 $(td).html(html);
						 var state=$("#"+infoid);
						 state.html("撤销发布");
						 bootbox.alert("撤销发布成功！");
					 },
					 error : function() {
					 bootbox.alert("撤销发布失败！");
					 }
					 });
			}
		
	 })
 }
 
 function xmshow(ele){
	 var type=$(ele).attr("name");
	 var infoid=$(ele).attr("id");
	 bootbox.confirm("确认要发布该信息?",function (result) {
			if(result==true){
				$.ajax({
					 url : base+ "/xyjsmgr/corpmgr/xminfosearch/show/"+type+"/"+infoid,
					 type : "POST",
					 async : true,
					 datatype:'text',
					 success : function(data) {
						 var html="";
						 if(type=="0"){
							 html="<a class='btn btn-xs btn-success' href='"+base+"/xyjsmgr/corpmgr/xminfosearch/bdxxInfoshow/"+infoid+"' target='_blank'>查看</a>&nbsp;"
							 +"<button class='btn btn-xs btn-info' type='button' onclick='xmcancleshow(this);'  id='"+infoid+"' name='"+type+"'>撤销发布</button>"
						 }else{
							 html="<a class='btn btn-xs btn-success' href='"+base+"/xyjsmgr/corpmgr/xminfosearch/xmjbxxInfoshow/"+infoid+"' target='_blank'>查看详细</a>&nbsp;"
							 +"<button class='btn btn-xs btn-info' type='button' onclick='xmcancleshow(this);'  id='"+infoid+"' name='"+type+"'>撤销发布</button>"
						 }
						 var td=$(ele).parent();
						 $(td).html("");
						 $(td).html(html);
						 var state=$("#"+infoid);
						 state.html("审核通过");
						 bootbox.alert("发布成功！");
					 },
					 error : function() {
					 bootbox.alert("发布失败！");
					 }
					 });
			}
		 
	 })
 }
 
 
 
 
 
 
 
 
 