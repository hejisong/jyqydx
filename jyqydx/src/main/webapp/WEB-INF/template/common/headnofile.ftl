<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8"/>
    <title>南京建邺“秒触发”政策服务系统</title>
    <link rel="stylesheet"  href="${base}/assets/css/index.css">
    <link rel="shortcut icon" href="${base}/assets/images/favicon1.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
    <link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />
    <link rel="stylesheet" href="${base}/assets/css/tabs.css">
    <link rel="stylesheet"  href="${base}/assets/css/dvslider.css">
    <link rel="stylesheet"  href="${base}/assets/css/sliderx.css">
    <link rel="stylesheet" src="${base}/assets/css/datetimepicker.css"></link>
    <link rel="stylesheet" href="${base}/assets/css/zhemutanchukuang.css" />
    [#--    <script src="${base}/assets/js/jquery-1.12.4.min.js"></script>--]
    <script src="${base}/assets/js/jquery-3.4.1.min.js"></script>
    <script src="${base}/assets/js/bootstrap.min.js"></script>
    <script src="${base}/assets/js/bootbox.js"></script>
    <script src="${base}/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
    <script src="${base}/assets/js/modernizr.min.js"></script>
    <script src="${base}/assets/js/dvslider.min.js"></script>
    <script src="${base}/assets/js/sliderx.js"></script>
    <!-- 政策查询-->
    <script charset="UTF-8"src="${base}/assets/js/policy/policysearch.js"></script>

    <link rel="stylesheet" href="${base}/assets/js/common/webuploader/webuploader.css">
    <script src="${base}/assets/js/common/webuploader/webuploader.js"></script>
    <script type="text/javascript">
        $(function(){
            $('.dvslider').dvslider({
                autoChange: true,
                pager: true,
                nav: true,
                count: true,
                swiping: true
            });
        });
    </script>
    <style>




    </style>
</head>

<div>

    <div class="dvslider" style="height: 185px;">

        <div class="logo03"> <a target="_blank" href="http://www.njjy.gov.cn"><img src="${base}/assets/images/zflogo.png"/></a></div>



        <input type="hidden" id="companyid" value="${logincompany.companyid}">
        [#--<input type="hidden" id="companyid" value="a3ca45bd0d99cc558aabcef03cfabbad">--]
        <input type="hidden" id="usercompanyid" value="${logincompany.id}">


        [#if isReletedCompanyFlag??&&isReletedCompanyFlag]
            <div style="position: absolute;top: 29px;right: 100px;z-index: 8888;width: 45%;text-align: right;">
                <a style="padding:5px 15px;font-size: 16px;margin-left: 10%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;"  onclick="exitlogin()"><img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png"><span id="headcorp">${logincompany.enterprisename}</span>[#if logincompany.username??](${logincompany.username})[/#if]</a>
                [#if companyList?size>1]  <a style="margin-left:10px;padding:5px 15px;font-size: 16px;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="showcorp()">切换</a> [/#if]

                <a style="margin-left:10px;padding:5px 15px;font-size: 16px;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="exitlogin()">退出</a>
            </div>

        [#else]
            <div style="position: absolute;top: 29px;right: 250px;z-index: 8888;width: 45%;text-align: right;">
                <a style="width: 80px;padding-left: 10px;padding-right:10px;height:25px;font-size: 14px;margin-left: 30%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="indexewmlogin()"><img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/login.png">登录</a>
            </div>
        [/#if]

        <div style="z-index: 998;position: absolute;width: 100%;padding-top: 70px;text-align: center;font-size: 30px;color: white;">
            [#if infotype=='8']最新政策
            [#elseif infotype=='3']我的兑现
            [#elseif infotype=='2']我的政策
            [#elseif infotype=='9']政策申报
            [#elseif infotype=='5']企业授权
            [#elseif infotype=='7']一企来专窗
            [#elseif infotype=='6']智能问答 [#else]政策性评估[/#if]
        </div>


        [#if ispg=='4'||infotype=='3']
            <div style="z-index: 888;position: absolute;width: 100%;padding-top: 47px">
                <input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
                <input type="hidden" id="hiddentype"  value="1">
                <input style="border: 0px;height: 40px;width: 30%;margin-left: 35%;margin-top:75px;padding-left: 15px;font-size: 16px;border-radius: 5px;background: #fff;" placeholder="请输入企业名称或统一社会信用代码" id="search_companyname" value="${search_companyname}">
                <button style="height:34px;width:90px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: -94px;color: white;vertical-align:middle;position: absolute;bottom: 1%;font-size: 16px"  onclick="searchCompanydata()">开始评估</button>
            </div>
        [/#if]




        <div class="dvslider-slider" style="height: 185px;">


            <div class="dvslider-slide" style="height: 185px;">
                [#if infotype=='8']
                    <img style="width: 100%;height: 184px;" src="${base}/assets/images/policy/newpolicy.png"/>
                [#elseif infotype=='3']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/wddx.png" alt="">
                [#elseif infotype=='2']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/mypolicy.png" alt="">
                [#elseif infotype=='9']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
                [#elseif infotype=='5']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/qysq.png" alt="">
                [#elseif infotype=='7']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/yqlzc/yqlzc.png" alt="">
                [#elseif infotype=='6']
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/znwd.png" alt="">
                [#else]
                    <img style="width: 100%;height: 184px;" class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
                [/#if]
            </div>


        </div>



    </div>


    <div style="text-align: center;height: 46px;padding-top: 15px;">
        <a style="width: 32px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='1']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none;" href="${base}/">首页</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='8']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchpolicyfife()" href="javascript:void(0);">政策查询</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='9']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchPolicydeclare()" href="javascript:void(0);">政策申报</a>
           [#if logincompany.companyid??]
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='2']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchplycomresult('1')" href="javascript:void(0);">我的政策</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='3']#3D91FF[#else]#333333[/#if];;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchwddxlist()" href="javascript:void(0);">我的兑现</a>
        <input type="hidden" id="isauthor" value="${logincompany.islegalperson}">
        <a style="margin-left:20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='5']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchauthor()" href="javascript:void(0);">企业授权</a>
        [/#if]
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='6']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/znwd">智能问答</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='7']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/yqlzc">一企来专窗</a>
        <br/>
        [#if logincompany.companyid??]
            <input style="width: 47px;height: 4px; background: [#if infotype=='1']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 -8px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='8']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='9']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='2']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='3']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='5']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 11px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='6']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 11px" readonly>
            <input style="width: 84px;height: 4px; background: [#if infotype=='7']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 13px" readonly>
        [#else]
            <input style="width: 47px;height: 4px; background: [#if infotype=='1']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 -8px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='8']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='9']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 10px" readonly>
            <input style="width: 73px;height: 4px; background: [#if infotype=='6']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 11px" readonly>
            <input style="width: 84px;height: 4px; background: [#if infotype=='7']#3D91FF[#else]#fff[/#if]!important;border: 0;padding:0;margin: 0 0 0 13px" readonly>

        [/#if]

    </div>


    [#include "common/t_company.ftl"]
    [#include "index/selectcompany.ftl"]
    <script type="text/javascript">
        var base = '${base}';
        function indexewmlogin() {
            base = '${base}';
            window.location.href=base+"/indexewmlogin";
        }
        
        function searchpolicyfife() {
            window.location.href=base+"/newpolicy";
        }

        function searchPolicydeclare() {
            window.location.href=base+"/policydeclare";
        }

        function dxwarn(type) {
            var search_companyid=$("#search_companyid").val();
            if(search_companyid==null||search_companyid==undefined){
                search_companyid="";
            }
            var searchName=$("#search_companyname").val();
            if(searchName==null||searchName==undefined){
                searchName="";
            }
            window.location.href=base+"/dxwarn?&searchName="+searchName+"&companyid="+search_companyid+"&enjoypolicycategory="+type;
        }

    </script>

</div>