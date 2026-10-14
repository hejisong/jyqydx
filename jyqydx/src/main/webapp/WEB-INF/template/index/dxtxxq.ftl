
[#include "common/headnofile.ftl"]
<body style="width: 100%;margin: 0">



<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 60%;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">政策查询-提升能力-当前差距</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;right: 23%;position: absolute;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
        </div>
        <div style="margin-top: 10px">
            <span style="font-weight:bold;font-size:16px;">政策信息</span>
        </div>
        <div style="padding:15px;margin-top: 10px;width: 100%;background: #F7F9FF;border-radius: 6px; ">
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
    </div>
    <br/>
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width:100%;height: 50px;border-radius: 6px; ">
            <img  style="vertical-align:middle;" src="${base}/assets/images/x1.png">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">当前差距</a>
        </div>
[#--        <div style="margin-top: 0px;width: 1000px;height: 50px;background: #FFFFFF;border-radius: 6px;border: 1px solid #3D91FF;  ">--]
[#--            <input style="border-radius: 50px;background: #FFFFFF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="" >--]
[#--            <a style="">1.营业收入达</a>--]
[#--            <a style="font-family: PingFangSC-Semibold;font-size: 14px;color: #3D91FF; ">1000</a>--]
[#--            <a style="">万元</a>--]
[#--            <a style="margin-left: 550px">企业当前营业收入</a>--]
[#--            <a style="ont-family: PingFangSC-Semibold;font-size: 14px;color: #3D91FF;">800 万</a>--]
[#--        </div>--]
        <div style="margin-top: 10px;width: 100%;height: 50px;background: #FFFFFF;border-radius: 6px;border: 1px solid #3D91FF;font-size: 16px;  ">
            <input style="border-radius: 50px;background: #FFFFFF;width: 20px;height: 20px;border: 0px;margin-top:15px;text-align: center;color: white;font-size: 14px;padding: 0" value="" >
            <a style="text-decoration: none;">${plycomresultdetailentity.policyconditions}</a>
[#--            <a style="font-family: PingFangSC-Semibold;font-size: 14px;color: #3D91FF; ">1—5 亿元</a>--]
[#--            <a style="">（不含 5 亿元）</a>--]
            <a style="margin-left: 20px;text-decoration: none;">企业${gxcwbqytjj.year}年业务收入 </a>
            <a style="ont-family: PingFangSC-Semibold;color: red;text-decoration: none;">${gxcwbqytjj.sale_income}万元</a>
        </div>
    </div>
    <br/>

    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 100%;height: 50px;border-radius: 6px; ">
            <img  style="vertical-align:middle;" src="${base}/assets/images/已匹配.png">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">资料信息</a>
        </div>
        [#--[#if plyfileEntityList??]
            [#list plyfileEntityList as plyfiles]
                <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px;display: flex;align-items: center; position: relative; ">
                    <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0;" value="${plyfiles_index+1}">

                    [#if plyfiles.status==1]
                        <img style="position: absolute;right: 40px;" src="${base}/assets/images/djsc.png">
                    [#else]
                        <img style="position: absolute;right: 40px;" src="${base}/assets/images/ypp.png">
                    [/#if]
                    <a style="width:800px; overflow: auto;display: inline-block;">${plyfiles.materialname}</a>

                </div>
            [/#list]
        [/#if]--]
        <div id="plyfilesData">
            [#if plyfileEntityList??]
                [#list plyfileEntityList as plyfiles]
                    <div style="margin-top: 10px;width:100%;border-radius: 6px;background: #F7F9FF;align-items: center; float: left;" name="upfiles">
                        <div style="margin: 15px;width: 100%;float: left;font-size: 16px;">
                            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0;"
                                   value="${plyfiles_index+1}">
                            <a style=" overflow: auto;text-decoration: none;">${plyfiles.materialname}</a>
                            [#if plyfiles.status==1]
                               [#-- <div class="img" style="float: right;margin-right: 30px;margin-top: 10px" name="proofFile${plyfiles_index}">
                                    <img style="width: 61px;height: 34px;" src="${base}/assets/images/djsc.png">
                                </div>--]
                                <div class="img" style="float: right;margin-right: 30px;margin-top: 10px;">
                                    <img style="float: right;" src="${base}/assets/images/wpp.png">
                                </div>
                            [#else]
                                <div class="img" style="float: right;margin-right: 30px;margin-top: 10px;">
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
                                            <span name="fjlist" style="height: 30px;line-height: 30px;">
                                                <input type="checkbox" name="newfileids" checked="checked" style="display:none"
                                                       value="${files.id}">
                                                <input type="checkbox" name="newfilename" checked="checked" style="display:none"
                                                       value="${files.filename}">
                                                <span style="margin-left: 15px;display:inline-block;width:800px;"><i
                                                            style="font-size: 20px;text-align: center;"></i>
                                                 ${files.filename}
                                                </span>

                                                 <div style="float: right;margin-right: 30px;margin-top: 10px;position: absolute;right: 24.2%;margin-top: -29px;">
                                                <a class="sel_btn" style="text-align: center;height: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"  href="${base}/doc/upload_${files.id}.${files.fileext}" target="_blank"><i  style="font-size: 20px;text-align: center;"></i>预览</a>
                                                <a class="sel_btn" style="text-align: center;height: 30px;margin-left: 5px;background-color:red;color:#ffffff;border-radius: 3px;" href="javascript:void(0)" onclick="deletefujian('${files.id}')"><i
                                                            style="font-size: 20px;text-align: center;"></i>删除</a>
                                               </div>

                                                [#--<a class="sel_btn"
                                                   style="float: right;margin-right: 30px;background-color:#3d91ff;color:#ffffff;"
                                                   href="${base}/common/files/fujian/${files.id}" target="_blank"><i
                                                            style="font-size: 20px;text-align: center;"></i>预览</a>
                                                <a class="sel_btn"
                                                   style="float: right;margin-right: 15px;background-color:red;color:#ffffff;"
                                                   href="javascript:void(0)" onclick="deletefujian(this)"><i
                                                            style="font-size: 20px;text-align: center;"></i>删除</a>--]
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
        <div style="padding-top: 50px;text-align: center;">
            <button  style="border: none;background: white;"></button>
        </div>

    </div>


</div>


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
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
</script>
</html>