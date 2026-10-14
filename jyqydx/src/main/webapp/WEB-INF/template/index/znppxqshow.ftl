
[#include "common/headnofile.ftl"]
[#include "common/loading.ftl"]
<body >

<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div style="width: 100%;background-color: #f8f4fd;padding-bottom: 10px;">
    <div style="width: 65%;background: #FFFFFF;border-radius: 6px;margin-left: 18% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 60%;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 400px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">政策查询-补充材料-材料信息</a>
            <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 1000px;margin-top: 5px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
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
                                                <span style="margin-left: 15px;display:inline-block;width:800px;font-size: 15px;">${files.filename}

                                                </span>
                                                <a class="sel_btn" style="float: right;text-align: center;margin-right: 30px;height: 30px;background-color:#3d91ff;color:#ffffff;border-radius: 3px;"  href="${base}/doc/upload_${files.id}.${files.fileext}" target="_blank"><i  style="font-size: 20px;text-align: center;"></i>预览</a>

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
            <input type="radio" name="issubmitoffline" value="1" [#if plycomresultdetailentity.issubmitoffline=='1'] checked[/#if] readonly>是
            <input type="radio" name="issubmitoffline" value="0" [#if plycomresultdetailentity.issubmitoffline!='1'] checked[/#if] readonly>否
        </div>


        <div style="padding-top: 50px;text-align: center;">
            <button style="font-weight:bold;border: 0;background-color: #3D91FF;color: white;padding: 12px;width: 75px;height: 50px;font-size: 20px;margin-top: 25px;"  onclick="javascript:history.back(-1);">返回</button>
        </div>
    </div>


</div>


[#include "common/foot.ftl"]
</body>










<script type="text/javascript">

</script>
</html>