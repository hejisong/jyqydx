
//我的兑现返回政策评估列表
function searchwddxlist(){
    var search_companyid=$("#search_companyid").val();
    if(search_companyid==null||search_companyid==undefined){
        search_companyid="";
    }
    var searchName=$("#search_companyname").val();
    if(searchName==null||searchName==undefined){
        searchName="";
    }
    window.location.href=base+"/wddx?&searchName="+searchName+"&companyid="+search_companyid;
}
//政策查询返回政策评估列表
function searchplycomresult(type){
    var search_companyid=$("#companyid").val();
    if(search_companyid==null||search_companyid==undefined){
        search_companyid="";
    }
    var searchName=$("#search_companyname").val();
    if(searchName==null||searchName==undefined){
        searchName="";
    }
    window.location.href=base+"/zcfhxpg?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
}





//企业授权页面
function searchauthor(){
    var isauthor=$("#isauthor").val();
    if(isauthor==null||isauthor=="undefined"||isauthor==""){
          bootbox.alert("只有法人登录才能授权");
        return;
    }else{
        if(isauthor=="0"||isauthor=="2"){
              bootbox.alert("只有法人登录才能授权");
            return;
        }
    }
    var search_companyid=$("#search_companyid").val();
    if(search_companyid==null||search_companyid==undefined){
        search_companyid="";
    }
    window.location.href=base+"/qysq?searchId="+search_companyid;
}
//调用二维码登录界面
function indexewmlogin() {
    window.location.href=base+"/indexewmlogin";
}
//退出登录
function exitlogin(){
     bootbox.alert("即将退出登录");
    window.location.href=base+"/Exitlogin";
}
//循环创建上传
function initfileUploadCheckfile() {
    var trs=$("#plyfilesData").find("div[name='upfiles']");
    $(trs).each(function(index,elem) {
        var proofFile=$(elem).find("div[name='proofFile"+index+"']");
        var fujian_list=$(elem).find("span[name='fujian_list"+index+"']");
        console.log(index+":"+proofFile.val());
        if(proofFile.val()==undefined||proofFile.val()=="undefined"){
        }else{
            morefileUploadgrinfo(proofFile,fujian_list);
        }

    });
}
//附件上传
function morefileUploadgrinfo(proofFile,fujian_list) {

    var uploader = WebUploader.create({
        auto : true,
        swf: base + '/assets/js/common/webuploader/Uploader.swf',
        server : base + '/common/files',
        fileSingleSizeLimit : 20 * 1024 * 1024, // 5 M
        fileVal : "upfile",
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick : proofFile,
        duplicate : true
        // 只允许选择图片文件。
        //accept : {
        //title : 'docs',
        //extensions : 'doc,docx',
        //mimeTypes : 'image/*'
        //}

    });
    uploader
        .on(
            'uploadSuccess',
            function(file, response) {
                var info = eval("(" + response._raw + ")");
                if (typeof (info['state']) != 'undefined') {
                    if (info['state'] == 'SUCCESS') {
                        var name = info['name'];
                        var size = info['fileSize'];
                        var id = info['id'];
                        var html = '<span name="fjlist" style="height: 30px;line-height: 30px;">'
                            + "<input type='checkbox' name='newfileids' checked='checked' style='display:none' value='"+id+"'>"
                            + "<input type='checkbox' name='newfilename' checked='checked' style='display:none' value='"+name+"'>"
                            + "<span style='margin-left: 15px;display:inline-block;width:800px;'><i style='font-size: 20px;text-align: center;'></i>"
                            + "" + name
                            + "</span>"
                            +"<a class='sel_btn' style='float: right;margin-right: 30px;background-color:#3d91ff;color:#ffffff;' href='"+base+"/common/files/download/"+id+"'target='_blank'><i style='font-size: 20px;text-align: center;' ></i>预览</a>"
                            +"<span> </span>"
                            +"<a class='sel_btn' style='float: right;margin-right: 15px;background-color:red;color:#ffffff;' href='javascript:void(0)'  onclick='deletefujian(this)'><i style='font-size: 20px;text-align: center;'></i>删除</a>"
                            +'</span></br>';

                        $(fujian_list).append(html);
                        initfileUploadCheckfile();
                        savefileid();
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
//删除附件
function deletefujian(id){
    console.log(id);
    // $(ele).parent().remove();
    $.ajax({
        url : base + "/deletefujian",
        type : "POST",
        async : false,
        datatype : 'json',
        data : {id:id},
        success : function(data) {
            if(data[0]=="0"){
                // bootbox.alert("删除成功")
            }
            window.location.reload();
        },
        error : function() {
            // bootbox.alert("删除失败")
        }
    });

}
//暂存附件1
function savefileid() {
    var trs=$("#plyfilesData").find("div[name='upfiles']");
    $(trs).each(function(index,elem) {
        var proofFile=$(elem).find("div[name='proofFile"+index+"']");
        var fujianlists=$(elem).find("span[name='fujian_list"+index+"']");
            ssvefujiandata(fujianlists);
    });
}
//暂存附件2
function ssvefujiandata(fujianlists) {
    var plycomresultid=$("#plycomresultid").val();
    $(fujianlists).each(function(index,ele) {
        var fujianlistid=$(ele).find("input[name='fujianlistid']").val();
        var fileiddata=$(ele).find("input[name='newfileids']");
        /*var upfileids =[];
        $(fileiddata).each(function(){
            upfileids.push($(this).val());
        })*/
        var upfileids ="";
        $(fileiddata).each(function(){
            upfileids+=$(this).val()+",";
        })
        console.log(upfileids);
        $.ajax({
            url : base + "/saveuploadfiles",
            type : "POST",
            async : false,
            datatype : 'json',
            data : {fujianlistid:fujianlistid,plycomresultid:plycomresultid,upfileids:upfileids},
            success : function(data) {
                if(data[0]=="0"){
                    // bootbox.alert("保存失败")
                }else{
                    // bootbox.alert("保存成功")
                }
                window.location.reload();
            },
            error : function() {
                //alert("保存失败")
            }
        });
    });
}