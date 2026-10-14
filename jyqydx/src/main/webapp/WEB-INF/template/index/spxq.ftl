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
    <link rel="stylesheet"  href="${base}/assets/css/index.css">
    <link rel="stylesheet"  href="${base}/assets/css/sliderx.css">
    <script src="${base}/assets/js/modernizr.min.js"></script>
    <script src="${base}/assets/js/jquery-1.12.4.min.js"></script>
    <script src="${base}/assets/js/dvslider.min.js"></script>
    <script src="${base}/assets/js/sliderx.js"></script>
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
            <a style="padding:5px 15px;height:25px;font-size: 16px;margin-left: 10%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block">
                <img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png">企业绑定</a>
        </div>
    </div>
    <div style="margin-bottom: 0px;margin-top: 15px">
        <a style="width: 32px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none" href="${base}/">首页</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #3D91FF;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchplycomresult('1')">政策查询</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchwddxlist()">我的兑现</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchplycomresult('6')">兑现提醒</a>
        <input type="hidden" id="isauthor" value="${companyList[0].islegalperson}">
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchauthor()">企业授权</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/znwd">智能问答</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: #333333;letter-spacing: 0;font-weight: 600;text-decoration:none"href="${base}/yqlzc">一企来专窗</a>
        <br/>
        <input style="width: 47px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 -8px" readonly>
        <input style="width: 73px;height: 4px; background: #3D91FF;border: 0;padding:0;margin: 0 0 0 10px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 10px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
        <input style="width: 73px;height: 4px; background: #fff;border: 0;padding:0;margin: 0 0 0 11px" readonly>
    </div>
</div>
<body style="width: 100%;margin: 0">
<div class="wrapper_bg">
    <img class="wrapper_bg_img" src="${base}/assets/images/mypolicy.png" alt="">
    <div class="wrapper_search" style="top: 15%">
        <div class="wrapper_search_title">政策符合性评估</div>
        <div class="wrapper_search_position">
            <input type="text" placeholder="请输入企业名称或统一社会信用代码" style="width: 500px;height: 50px;">
            <div class="assess">开始评估</div>
        </div>
    </div>
</div>

<div style="width: 100%;height: 700px;background: #F3F7FA; padding-top: 20px;">
    <div style="width: 1000px;height: 600px;background: #FFFFFF;border-radius: 6px;margin-left: 20% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 1000px;height: 50px;border-radius: 6px; ">

            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">审批详情</a>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="1">
            <a style="">申请报告</a>
            <img style="position: absolute;margin-top: -9px;padding-left: 700px" src="${base}/assets/images/ypp.png">
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 800px;background-color: #3D90FF;color: white;height: 30px">导出</button>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="2">
            <a style="">营业执照（统一信用代码，企业名称）市场监管局可查询</a>
            <img style="position: absolute;margin-top: -9px;margin-left: 364px" src="${base}/assets/images/ypp.png">
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 464px;background-color: #3D90FF;color: white;height: 30px">导出</button>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="3">
            <a style="">股权投资（基金管理机构）章程或合伙协议（协议规模，首期出资规模</a>
            <img style="position: absolute;margin-top: -9px;margin-left: 268px" src="${base}/assets/images/ypp.png">
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 368px;background-color: #3D90FF;color: white;height: 30px">导出</button>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: #F6F9FE;border-radius: 6px; ">
            <input style="border-radius: 50px;background: #3D91FF;width: 20px;height: 20px;border: 0px;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0" value="4">
            <a style="">管理协议（组织架构）</a>
            <img style="position: absolute;margin-top: -9px;margin-left: 604px" src="${base}/assets/images/ypp.png">
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 704px;background-color: #3D90FF;color: white;height: 30px">导出</button>
        </div>
        <br/>
        <div style="border-top:2px dotted #e5e5e5;padding-top: 10px;width: 1000px;height: 50px;border-radius: 6px; ">

            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 16px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 600;margin-left: 20px">部门审查意见</a>
        </div>
        <div style="margin-top: 0px;width: 1000px;height: 80px;border-radius: 6px; ">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">审查意见*</a>
            <textarea style="position: absolute;margin-left: 20px;width: 800px;height: 50px">同意。</textarea>
        </div>
        <div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">审核时间*</a>
            <input style="position: absolute;margin-left: 20px;width: 150px;height: 22px;border: 1px solid #9eb4c9" value="2021-03-02 15:00:00">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 500px">审核人*</a>
            <input style="position: absolute;margin-left: 20px;width: 150px;height: 22px;border: 1px solid #9eb4c9" value="张三">
        </div>
        <div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
            <a style="width: 64px;height: 22px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;margin-left: 10px">备注&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a>
            <input style="position: absolute;margin-left: 20px;width: 800px;height: 22px;border: 1px solid #9eb4c9;padding: 0">
        </div>
        <div style="margin-top: 0px;width: 1000px;height: 50px;border-radius: 6px; ">
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 300px;background-color: #3D90FF;color: white;height: 30px">同意</button>
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 100px;background-color: #3D90FF;color: white;height: 30px">驳回</button>
            <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 100px;background-color: #3D90FF;color: white;height: 30px">返回</button>
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
</script>
</html>