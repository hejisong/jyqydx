
[#include "common/headnofile.ftl"]
[#include "common/loading.ftl"]
<body style="width: 100%;margin: 0">


<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 60%;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">我的兑现-上传凭据-凭据信息</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 1000px;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <input type="hidden" id="plycomresultid" name="plycomresultid" value="${plycomresultdetailentity.plycomresultdetailid}">
        <div style="margin-top: 20px">
            <span style="font-weight:bold;;font-size:16px;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 96.5%;background: #F7F9FF;border-radius: 6px; ">
            <div style="">
                <span style="color: #3F93FF;font-size:16px;">政策文件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策大类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policycategoryname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策小类：</span><span style="font-size: 15px;">${plycomresultdetailentity.policysubclassname}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策条件：</span><span style="font-size: 15px;">${plycomresultdetailentity.policyconditions}[#if plycomresultdetailentity.patentname??](${plycomresultdetailentity.patentname})[/#if]</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">政策奖励：</span><span style="font-size: 15px;">${plycomresultdetailentity.rdescription}</span>
            </div>
            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">申报条件：</span><span style="font-size: 15px;">${policydetail.declareconditions}</span>
            </div>

            <div style="margin-top: 15px">
                <span style="color: #3F93FF;font-size:16px">实际兑现金额：</span><span style="font-size: 15px;">${plycomresultdetailentity.amount}</span>
            </div>
        </div>

        <div style="margin-top: 20px" class="div-check">
            <span >审批信息</span>
        </div>

        [#if flowauditinfos??]

            <div style="margin-top: 10px;width: 100%;height: auto;border-radius: 6px; ">
                <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="width:20%;background-color: #3d91ff;">审批部门</th>
                        <th style="width:15%;background-color: #3d91ff;">审批结果</th>
                        <th style="width:20%;background-color: #3d91ff;">审批意见</th>
                        <th style="width:15%;background-color: #3d91ff;">审批时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr >
                        [#list flowauditinfos as info]
                        <td>${info.deptname}</td>
                        [#--  <td>${info.auditpername}</td>
                          <td>${info.auditamount}</td>--]
                        <td>[#if info.auditstate=='1']同意[#elseif info.auditstate=='2']驳回[#else]退回   [/#if]</td>
                        <td>${info.auditidea}</td>
                        <td>${(info.audittime?string('yyyy-MM-dd HH:mm:ss')) }</td>
                    </tr>
                    [/#list]

                    </tbody>
                </table>
            </div>

        [/#if]

        <div style="margin-top: 20px" class="div-check">
            <span >凭据信息</span>
        </div>


        <form action="${base}/saveupinfo" method="post" id="upinfoform">
            <input type="hidden" name="applyid" value="${flowprocess.applyid}">
            <input type="hidden" id="state" name="state" >

        <div style="padding:15px;margin-top: 10px;width: 96.5%;background: #F7F9FF;border-radius: 6px; ">

            <label style="color: #3F93FF;font-size:16px;"> 凭据附件：</label>

            <div id="proofFile" class="btn btn-xs btn-success" style="margin-left:0px;margin-right:0px;">添加附件</div>
            <span id="fujian_list">
                [#if uflowprocess.uploadfileid??]
                    <span>
                        <input type="hidden" name="uploadfilename" id="uploadfilename" value="${uflowprocess.uploadfilename}">
                        <input type="hidden" name="uploadfileid" id="uploadfileid" value="${uflowprocess.uploadfileid}">
                        <a href="${base}/jyqydx/common/file/download/${uflowprocess.uploadfileid}" target="_blank">${uflowprocess.uploadfilename}</a>
                        <a style="color:red" href="javascript:void(0)" onclick="deletefujianinfo(this)">[删除]</a></span>

                [/#if]

					</span>
        </div>



        <div style="padding-top: 50px;text-align: center;">
            <button style="font-weight:bold;border: 0;background-color: #3D91FF;color: white;padding: 12px;width: 75px;height: 50px;font-size: 20px;margin-left: 15px" type="button" onclick="postfujian('0')">暂存</button>
            <button style="font-weight:bold;border: 0;background-color: #3D91FF;color: white;padding: 12px;width: 75px;height: 50px;font-size: 20px;margin-left: 15px" type="button" onclick="postfujian('1')">提交</button>
        </div>

        </form>


    </div>


</div>


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<input type="hidden" id="issubmit" value="0">

<script type="text/javascript">
    var base = '${base}';

    $(function(){
        fileUpload();
    });


 function postfujian(state) {

     if(isauthor()){
         $("#state").val(state);
         var uploadfileid=$("#uploadfileid").val();
         if(uploadfileid==''||uploadfileid==null||uploadfileid==undefined){
             bootbox.alert('请上传凭据附件！');
             return;
         }else{
             $("#upinfoform").submit();
         }
     }else{
         bootbox.alert("当前登录人没有权限操作，请先授权再操作！");
     }






 }

    function isauthor() {
        var usercompanyid=$("#usercompanyid").val();
        var isauther=false;
        $.ajax({
            url : base + "/isauthor",
            type : "POST",
            async : false,
            datatype : 'json',
            data : {usercompanyid:usercompanyid},
            success : function(data) {
                if(data=="0"||data=='1'){
                    isauther=true;
                    return isauther;
                }else{
                    return isauther;
                }
            },
            error : function() {
                bootbox.alert("数据加载失败！");
            }
        });
        return isauther;
    }



    //证书附件
    function fileUpload() {
        var uploader = WebUploader.create({
            auto : true,
            swf : base + '/assets/js/common/webuploader/Uploader.swf',
            server : base + '/common/file',
            fileSingleSizeLimit : 20 * 1024 * 1024, // 5 M
            fileVal : "upfile",
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick : '#proofFile',
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
        uploader.on('uploadSuccess',function(file, response) {
            var info = eval("(" + response._raw + ")");
            if (typeof (info['state']) != 'undefined') {
                if (info['state'] == 'SUCCESS') {
                    var $divs = $("#fujian_list div");
                    var name = info['name'];
                    var size = info['fileSize'];
                    var id = info['id'];
                    var html = "";
                    if ($divs.length > 0) {
                        html = html
                            +"<span>"
                            + "<input type='hidden' name='uploadfilename' id='uploadfilename' value='"+name+"'>"
                            + "<input type='hidden' name='uploadfileid' id='uploadfileid'  value='"+id+"'>"
                            + "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
                            + "<span> </span>"
                            + "<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>"
                            +"</span>"
                        $("#fujian_list div:last").after(html);
                    } else {
                        html = html
                            +"<span>"
                            + "<input type='hidden' name='uploadfilename'  id='uploadfilename' value='"+name+"'>"
                            + "<input type='hidden' name='uploadfileid'   id='uploadfileid' value='"+id+"'>"
                            + "</td> <td><a  href='"+base+"/common/file/download/"+id+"'target='_blank'>"+name+"</a>"
                            + "<span> </span>"
                            + "<a style='color:red' href='javascript:void(0)' onclick='deletefujianinfo(this)'>[删除]</a>"
                            +"</span>"
                        $("#fujian_list").append(html);
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

    function deletefujianinfo(ele){
        $(ele).parent().remove();
    }


</script>
</html>