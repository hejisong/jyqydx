<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8"/>
    <title>南京建邺“秒触发”政策服务系统</title>
    <meta name="keywords" content="南京建邺“秒触发”政策服务系统"/>
    <meta name="description" content="南京建邺“秒触发”政策服务系统"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    [#--		<meta http-equiv="X-Frame-Options" content="DENY"/> --]
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" href="${base}/assets/css/index.css">
    <link rel="stylesheet" href="${base}/assets/css/sliderx.css">
    <script src="${base}/assets/js/modernizr.min.js"></script>
    <script src="${base}/assets/js/jquery-1.12.4.min.js"></script>
    <script src="${base}/assets/js/dvslider.min.js"></script>
    <script src="${base}/assets/js/sliderx.js"></script>
    <!-- 政策查询-->
    <script charset="UTF-8"src="${base}/assets/js/policy/policysearch.js"></script>
    <script type="text/javascript">
        $(function () {
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
<div style="width: 70%;margin-left: 25%;margin-top: 15px ">
    <div>
        <div>
            <a style="width: 285px;
height: 28px;
font-family: PingFangSC-Semibold;
font-size: 20px;
color: #333333;
letter-spacing: 0;
font-weight: 600;"><img style="vertical-align:middle;padding-right: 20px" src="${base}/assets/images/jylg.png">南京建邺“秒触发”政策服务系统</a>
            [#if isReletedCompanyFlag??]
                <a style="padding:5px 15px;height:25px;font-size: 16px;margin-left: 10%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block" onclick="exitlogin()">
                    <img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png">${companyList[0].enterprisename}[#if companyList[0].username??](${companyList[0].username})[/#if]</a>
            [#else]
                <a style="padding:5px 15px;height:25px;font-size: 16px;margin-left: 30%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block"onclick="indexewmlogin()">
                    <img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png">登录</a>
            [/#if]
        </div>
    </div>
    <div style="margin-bottom: 0px;margin-top: 15px">
        <a style="width: 32px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none" href="${base}/">首页</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchplycomresult('1')">政策查询</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchwddxlist()">我的兑现</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchplycomresult('6')">兑现提醒</a>
        <input type="hidden" id="isauthor" value="${companyList[0].islegalperson}">
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchauthor()">企业授权</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/znwd">智能问答</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #3D91FF;letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/yqlzc">一企来专窗</a>
        <br/>
        <input style="width: 47px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 -8px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 10px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 10px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
        <input style="width: 73px;height: 4px; background: #3D91FF;border: 0;padding:0;margin: 0 0 0 11px" readonly>
    </div>
</div>
<body style="width: 100%;margin: 0;text-align:center;">
[#--<div class="wrapper_bg">--]
[#--    <img class="wrapper_bg_img" src="${base}/assets/images/mypolicy.png" alt="">--]
[#--    <div class="wrapper_search" style="top: 15%">--]
[#--        <div class="wrapper_search_title">政策符合性评估</div>--]
[#--        <div class="wrapper_search_position">--]
[#--            <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;">--]
[#--            <div class="assess">开始评估</div>--]
[#--        </div>--]
[#--    </div>--]
[#--</div>--]
<img  src="${base}/assets/images/yqlinfo.png" onclick="">
[#--<div style="width: 100%;height: 689px;background: #F3F7FA; padding-top: 20px;">--]
[#--    <div style="margin-top: 5px;width: 900px;height: 73px;background: #FFFFFF;border-radius: 6px; margin-left: 351px;border-bottom: 5px solid #F3F3F3;">--]
[#--        --]

[#--        <a style="font-family: PingFangSC-Medium;font-size: 26px;color: #333333;letter-spacing: 0;font-weight: 500;margin-left: 10px;position: absolute;padding-top: 15px">智能问答</a>--]
[#--        <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 950px;margin-top: 15px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="window.open('${base}/','_self')">返回</button>--]
[#--        <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;text-align: center;margin-left: 698px;margin-top: 15px;position: absolute;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>--]
[#--    </div>--]
[#--    <iframe style="float: left;width: 900px;height: 580px;border: 0px; margin-left: 351px" src="https://h5-alimebot.dingtalk.com/intl/index.htm?sourceType=ding_talk&from=DEFCYOnKKb0OKvr0E3UhjF7rZhc">--]

[#--    </iframe>--]
[#--</div>--]


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">
    var base = '${base}';

    function open(){
        window.location.href=base+"/yqlzcinfo";
    }
</script>
</html>