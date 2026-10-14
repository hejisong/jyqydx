function initContent(){
	var content = "";
	if($("#content")[0].childNodes[0]!=undefined){
		content = $("#content")[0].childNodes[0].data;
	}
	var option = {
		initialContent:content, 
		//initialContent:"<p>aaa<strong>aa</strong></p>", 
		toolbars:[
		            [ 'source', '|', 'undo', 'redo', '|',
		                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		                'directionalityltr', 'directionalityrtl', 'indent', '|',
		                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|']
		        ],
    	minFrameHeight:220
  	};
	myEditor = new baidu.editor.ui.Editor(option);
	myEditor.render("myEditor");
}
