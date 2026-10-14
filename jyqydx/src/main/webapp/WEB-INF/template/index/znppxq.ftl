
[#include "common/headnofile.ftl"]
[#include "common/loading.ftl"]
<body >

<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 62%;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">我的政策-补充材料-材料信息</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;right: 23%;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <input type="hidden" id="plycomresultid" name="plycomresultid" value="${plycomresultdetailentity.plycomresultdetailid}">
        <input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
        <div style="margin-top: 20px">
            <span style="font-weight:bold;;font-size:16px;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 99%;background: #F7F9FF;border-radius: 6px; ">
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
        </div>

        <div style="margin-top: 20px">
            <span style="font-weight:bold;;font-size:16px;">资料信息</span>
        </div>

        <div id="plyfilesData" >
            [#if plyfileEntityList??]
                [#list plyfileEntityList as plyfiles]
                    <div style="margin-top: 10px;width:99%;border-radius: 6px;background: #F7F9FF;align-items: center; float: left;" name="upfiles">
                        <div style="margin: 15px;width: 100%;float: left;">
                            <input style="height:20px; line-height:33px; text-align:center;border-radius: 50px;background: #3D91FF;width: 20px;border: 0px;color: white;font-size: 14px;"
                                   value="${plyfiles_index+1}">
                            <span style="font-size: 15px;color: #0c91e5;">${plyfiles.materialname}</span>
                            [#if plyfiles.status==1]
                                <div class="img" style="float: right;margin-right: 30px;margin-top: 10px" name="proofFile${plyfiles_index}">
                                    <img style="width: 61px;height: 34px;" src="${base}/assets/images/djsc.png">
                                </div>
                            [#else]
                                <div class="img" style="float: right;">
                                    <img style="float: right;" src="${base}/assets/images/ypp.png">
                                </div>
                            [/#if]
                        </div>
                        [#if plyfiles.status==1]
                            <div style="margin: 15px;width: 100%;float: left;">
                                <span style="float:left;" name="fujian_list${plyfiles_index}">
                                    <input type="hidden" name="fujianlistid"  value="${plyfiles.plyfileid}">
                                    [#if plyfiles.uploadfiles??]
                                        [#list plyfiles.uploadfiles as files]
                                            <span name="fjlist" style="height: 30px;line-height: 33px;">
                                                <input type="checkbox" name="newfileids" id="newfileids" checked="checked" style="display:none"
                                                       value="${files.id}">
                                                <input type="checkbox" name="newfilename"  checked="checked" style="display:none"
                                                       value="${files.filename}">
                                                <span style="margin-left: 15px;display:inline-block;font-size: 15px;">${files.filename}

                                                </span>
                                                [#--<a class="sel_btn"  style="float: right;text-align: center;margin-right: 30px;height: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"  href="${base}/common/file/download/${files.id}" target="_blank"><i style="font-size: 20px;text-align: center;"></i>预览</a>--]

                                               <div style="float: right;margin-right: 30px;margin-top: 10px;position: absolute;right: 24.2%;margin-top: -29px;">
                                                <a class="sel_btn" style="text-align: center;height: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"  href="${base}/doc/upload_${files.id}.${files.fileext}" target="_blank"><i  style="font-size: 20px;text-align: center;"></i>预览</a>
                                                <a class="sel_btn" style="text-align: center;height: 30px;margin-left: 5px;background-color:red;color:#ffffff;border-radius: 3px;" href="javascript:void(0)" onclick="deletefujian('${files.id}')"><i
                                                            style="font-size: 20px;text-align: center;"></i>删除</a>
                                               </div>
                                            </span></br>
                                        [/#list]
                                    [/#if]
	                            </span>
                            </div>
                        [/#if]
                    </div>
                [/#list]
            [/#if]

        </div>

        <div style="padding-top: 10px;">
            <span style="font-weight:bold;font-size:16px;">是否线下提交纸质材料</span>
            <input  type="radio" name="issubmitoffline" value="1" [#if plycomresultdetailentity.issubmitoffline=='1'] checked[/#if]><span style="font-size:16px;">是</span>
            <input  type="radio" name="issubmitoffline" value="0" [#if plycomresultdetailentity.issubmitoffline!='1'] checked[/#if]><span style="font-size:16px;">否</span>
        </div>


        <div style="padding-top: 50px;text-align: center;">
            <button style="font-weight:bold;border: 0;background-color: #3D91FF;color: white;padding: 12px;width: 75px;height: 50px;font-size: 20px;margin-top: 25px;" onclick="savefileid()">暂存</button>
            <button style="font-weight:bold;border: 0;background-color: #3D91FF;color: white;padding: 12px;width: 75px;height: 50px;font-size: 20px;margin-left: 15px" onclick="postfujian()">提交</button>
        </div>
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
    function searchplycomresult(type){
        var search_companyid=$("#companyid").val();
        if(search_companyid==null||search_companyid==undefined){
            search_companyid="";
        }
        var searchName=$("#search_companyname").val();
        window.location.href=base+"/zcfhxpg?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
    }
    //加载上传功能
    window.onload = function () {initfileUploadCheckfile(); };

    function postfujian(){
        var issubmit=$("#issubmit").val();
        if(isauthor()) {
            if(issubmit=='0') {
                var applyid='${plycomresultdetailentity.plycomresultdetailid}';
                var issubmitoffline= $("input[name='issubmitoffline']:checked").val();
                var newfileids=$("#newfileids").val();
                if(issubmitoffline=='1'){

                    $("#issubmit").val("1")
                    saveinfo(applyid,issubmitoffline);


                }else{
                    if(newfileids==null||newfileids==""||newfileids==undefined){
                        bootbox.alert("请上传材料附件！");
                    }else{
                        $("#issubmit").val("1")
                        saveinfo(applyid,issubmitoffline);
                    }
                }

            }else {
                bootbox.alert("请勿重复提交数据！");
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

    function saveinfo(applyid,issubmitoffline) {

            $("#loadingbg").show();
            $("#loading").show();
            $.ajax({
                url : base + "/policyservices/savefilesinasset",
                type : "POST",
                async : true,
                datatype : 'json',
                data : {searchId:applyid,issubmitoffline:issubmitoffline},
                success : function(data) {
                    if(data[0]=="0"){
                        bootbox.alert("保存成功");
                        window.location.href=base+"/znppxq?searchId="+applyid;
                    }else{
                        bootbox.alert("保存失败");
                    }
                },
                error : function() {
                    bootbox.alert("保存失败");
                }
            });

    }




</script>
</html>