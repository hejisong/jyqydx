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
    <link rel="shortcut icon" href="${base}/assets/images/favicon1.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
    <link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />
    <link rel="stylesheet" href="${base}/assets/css/zhemutanchukuang.css" />
    <link rel="stylesheet"  href="${base}/assets/css/dvslider.css">
    <link rel="stylesheet"  href="${base}/assets/css/sliderx.css">
    <link rel="stylesheet" src="${base}/assets/css/datetimepicker.css"></link>
    <!-- -->


    <script src="${base}/assets/js/modernizr.min.js"></script>
    <script src="${base}/assets/js/jquery-1.12.4.min.js"></script>
    <script src="${base}/assets/js/dvslider.min.js"></script>
    <script src="${base}/assets/js/sliderx.js"></script>
    <script src="${base}/assets/js/bootstrap.min.js"></script>
    <script src="${base}/assets/js/bootbox.js"></script>
    <!-- 政策查询-->
    <script charset="UTF-8" src="${base}/assets/js/policy/policysearch.js"></script>

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
        /*html,*/
        .box .shadow a{
            font-size: 20px;
        }


    </style>
</head>



    <div class="dvslider">

        <div class="logo03" > <a target="_blank" href="http://www.njjy.gov.cn"><img  src="${base}/assets/images/zflogo.png"/></a></div>
        <div class="logo01"> <img src="${base}/assets/images/totallogo.png"/></div>



        <input type="hidden" id="companyid" value="${logincompany.companyid}">
        [#--<input type="hidden" id="companyid" value=" a3ca45bd0d99cc558aabcef03cfabbad">--]
        <input type="hidden" id="usercompanyid" value="${logincompany.id}">


        [#if nocorpFlag??&&nocorpFlag]

            <div style="position: absolute;top: 29px;right: 100px;z-index: 8888;width: 45%;text-align: right;">
                <a style="padding:5px 15px;font-size: 16px;margin-left: 10%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;"  onclick="exitlogin()"><img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png"> <span id="headcorp">${username}  </span> </a>
                <a style="margin-left:10px;padding:5px 15px;font-size: 16px;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="exitlogin()">退出</a>
            </div>


        [#else]

            [#if isReletedCompanyFlag??&&isReletedCompanyFlag]

                <div style="position: absolute;top: 29px;right: 100px;z-index: 8888;width: 45%;text-align: right;">
                    <a style="padding:5px 15px;font-size: 16px;margin-left: 10%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;"  onclick="exitlogin()"><img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/qytb.png"> <span id="headcorp">${logincompany.enterprisename}</span>[#if logincompany.username??](${logincompany.username})[/#if]    </a>
                    [#if companyList?size>1]  <a style="margin-left:10px;padding:5px 15px;font-size: 16px;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="showcorp()">切换</a> [/#if]

                    <a style="margin-left:10px;padding:5px 15px;font-size: 16px;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="exitlogin()">退出</a>
                </div>

            [#else]
                <div style="position: absolute;top: 29px;right: 250px;z-index: 8888;width: 45%;text-align: right;">
                    <a style="width: 80px;padding-left: 10px;padding-right:10px;height:25px;font-size: 14px;margin-left: 30%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;text-decoration: none;cursor:pointer;" onclick="indexewmlogin()"><img style="vertical-align:middle;padding-right: 5px;" src="${base}/assets/images/login.png">登录</a>
                </div>
            [/#if]


        [/#if]







        <a style="font-size: 30px;color: white;z-index: 999;position: absolute;padding-left: 39%;padding-top: 130px;text-decoration: none;">秒触发，云兑现，免申即享！</a>
        <a style="font-size: 20px;color: white;margin-left: 40%;z-index: 999;position: absolute;padding-top: 190px;text-decoration: none;">——&nbsp&nbsp网上随时兑&nbsp&nbsp免申及时享&nbsp&nbsp——</a>
        <div style="z-index: 998;position: absolute;width: 100%;padding-top: 180px">
            <input type="hidden" id="search_companyid" name="companyid" value="${logincompany.companyid}">
            <input type="hidden" id="hiddentype"  value="1">
            <input style="border: 0px;height: 40px;width: 30%;margin-left: 35%;margin-top:75px;padding-left: 15px;font-size: 16px;border-radius: 5px;background: #fff;" placeholder="请输入企业名称或统一社会信用代码" id="search_companyname" value="${logincompany.enterprisename}">
            [#--                       <button style="height:53px;width:63px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: -75px;color: white;vertical-align:middle;font-size: 16px"  onclick="logininzccxpglist()">开始<br />评估</button>--]
            <button style="height:34px;width:90px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: -94px;color: white;vertical-align:middle;position: absolute;bottom: 1%;font-size: 16px"  onclick="searchCompanydata()">开始评估</button>
        </div>
        <div class="dvslider-slider">


            <div class="dvslider-slide">
               <img src="${base}/assets/images/bigbj.png"/>
            </div>



            <div class="dvslider-slide">
                <img src="${base}/assets/images/p6.png"/>
            </div>


            <div class="dvslider-slide">
                <img src="${base}/assets/images/p5.png"/>
            </div>

            <div class="dvslider-slide">
                <img src="${base}/assets/images/p4.png"/>
            </div>

        </div>

    </div>















<div style="text-align: center;height: 49px;padding-top: 15px;">
        <a style="width: 32px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='1']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none;" href="${base}/">首页</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='8']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchpolicyfife()" href="javascript:void(0);">政策查询</a>
        <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='9']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchPolicydeclare()" href="javascript:void(0);">政策申报</a>
        [#if logincompany.companyid??]
            <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='2']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchplycomresult('1')" href="javascript:void(0);">我的政策</a>
            <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='3']#3D91FF[#else]#333333[/#if];;letter-spacing: 0;font-weight: 600;text-decoration:none" onclick="searchwddxlist()" href="javascript:void(0);">我的兑现</a>
            <input type="hidden" id="isauthor" value="${logincompany.islegalperson}">
            <a style="margin-left: 20px;width: 64px;height: 22px;font-family: PingFangSC-Medium;font-size: 16px;color: [#if infotype=='5']#3D91FF[#else]#333333[/#if];letter-spacing: 0;font-weight: 600;text-decoration:none"onclick="searchauthor()" href="javascript:void(0);">企业授权</a>
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


<body style="width: 100%;margin: 0">


<div style="background-color: #f8f4fd;height: 365px;padding-top: 30px;">

    <div style="text-align: center;" >
        [#--<img style="margin-left: 46%;margin-top: 36px;" src="${base}/assets/images/NEWS.png">--]
        <a style="width: 112px;height: 40px;font-family: PingFangSC-Semibold;font-size: 28px;color: #333333;letter-spacing: 0;text-align: right;font-weight: 600;vertical-align: bottom;text-decoration: none;">最新政策</a>
        <br/>
        <input readonly style="border: 0px;background-color: #3D91FF!important;width: 79px;height: 4px;margin-top: 0px;">
        <br/>
        <a style="font-size: 16px;color: #999999;font-family: PingFangSC-Regular;text-decoration: none;">建邺事  &nbsp; &nbsp;免申报 &nbsp; &nbsp;随时兑</a>
        <br/>

    </div>


<div class="control-box" style="margin-top: 20px;height: 196px;position:relative;">
    <a style="font-family: PingFangSC-Semibold;font-size: 14px;color: #999;margin-left: 59%;text-decoration:none;position: absolute;top: 0;right: 45px;" href="${base}/newpolicy">更多</a>
    <ul>
        <li class="prev">
            <img src="${base}/assets/images/syy.png" alt="prev">
        </li>
        <li class="next">
            <img src="${base}/assets/images/下一页备份.png" alt="next">
        </li>
    </ul>
    <div class="bigbox"style="cursor: pointer;" onclick="window.open('${base}/newpolicy','_self')">
        <div id="switcher" style="height: 190px;">
            <div class="content_1">

                [#assign n=0]
                [#list policyFiles as file]
                    [#assign n=n+1]

                    <div class="box" style="background-image: [#if n%4==1]url(${base}/assets/images/lb1.png)[#elseif n%4==2]url(${base}/assets/images/lb2.png) [#elseif n%4==3]url(${base}/assets/images/lb3.png)[#else] url(${base}/assets/images/lb4.png) [/#if];">
                        <div class="shadow">
                            <a > ${file.policyname}</a>
                        </div>
                    </div>
                [/#list]

            </div>
            <div class="content_2"></div>
        </div>
    </div>
</div>

[#--    <div style="text-align: center;margin-top: 30px;">
        <a style="width: 100px;padding-left: 10px;padding-right:10px;height:25px;font-size: 14px;background-color: #d1d4d6;border: 0px;color:#4696FF;border-radius:15px;display:inline-block;padding-top: 2px;" href="${base}/newpolicy">更多政策...</a>
    </div>--]
</div>
[#--选择企业--]
[#include "index/selectcompany.ftl"]
[#include "index/biglogo.ftl"]
[#include "index/userbind.ftl"]
[#include "common/t_company.ftl"]
<div style="margin-top: -18px;">
    [#include "common/foot.ftl"]
</div>

</body>


<script type="text/javascript">


var base = '${base}';
function indexewmlogin() {
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
</html>