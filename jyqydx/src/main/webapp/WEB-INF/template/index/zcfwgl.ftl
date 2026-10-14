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
[#--    <link rel="stylesheet"  href="${base}/assets/css/zhemutanchukuang.css">--]
[#--    <link rel="stylesheet"  href="${base}/assets/css/datetimepicker.css">--]
[#--    <link rel="stylesheet"  href="${base}/assets/css/bootstrap.min.css">--]
[#--    <link rel="stylesheet"  href="${base}/assets/css/ace.min.css">--]
    <script src="${base}/assets/js/jquery-1.12.4.min.js"></script>
    <style>
        input{
            padding: 0;
        }


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
            <a style="padding:5px 15px;height:25px;font-size: 16px;margin-left: 30%;background-color: #F1F2F3;border: 0px;color:#4696FF;border-radius:15px;display:inline-block">
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
    </div>
</div>
<body>
<div class="wrapper">
    <div class="wrapper_bg">
        <img class="wrapper_bg_img" src="${base}/assets/images/bg1.png" alt="">
        <div class="wrapper_search">
            <div class="wrapper_search_title">政策符合性评估</div>
            <div class="wrapper_search_position">
                <input  type="text" placeholder="请输入企业名称或统一社会信用代码" style="padding-left:24px;width: 500px;height: 50px;">
                <div class="assess">开始评估</div>
            </div>
        </div>
    </div>


    <div class="wrapper_content">
        <div class="wrapper_content_main">
            <span class="tab" style="background-color: #EBEEF5">
              <a style="font-size: 14px;text-align: center;margin-top: 10px">关键字搜索<input style="background-color: white;height: 20px;margin-left: 15px"></a>
                <a style="font-size: 14px;text-align: center;margin-top: 10px;margin-left: 15px">按时间检索<input style="background-color: white;height: 20px;margin-left: 15px"><a style="margin-top: 10px;margin-left: 15px">--</a><input style="margin-top: 10px;background-color: white;height: 20px;margin-left: 15px">
                    <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 15px;background-color: #3D90FF;color: white;height: 35px">搜索</button></a>

                <br/>

            </span>
[#--            <label class="col-xs-1 control-label no-padding-right" style="text-align:right;">开始时间</label>--]
[#--            <div class="col-xs-1">--]
[#--                <div class="input-group date form_datetime ">--]
[#--                    <input class="form-control input-inline" placeholder="按营业执照输入"  name="startdate" id="startdate" data-date-format="yyyy-MM-dd" readonly--]
[#--                           value="${(.now?string('yyyy-MM-dd'))!}" style="border-right: 0px; background-color: white;">--]

[#--                    <span class="input-group-btn">--]
[#--									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">--]
[#--													<img src="${base}/assets/images/rl.png" style="float:left;">--]
[#--									</button>--]
[#--								</span>--]
[#--                </div>--]
[#--            </div>--]
[#--            <label class="col-xs-1 control-label no-padding-right" style="text-align:right;">结束时间</label>--]
[#--            <div class="col-xs-1">--]
[#--                <div class="input-group date form_datetime">--]
[#--                    <input class="form-control input-inline" placeholder="按营业执照输入"  name="enddate" id="enddate" data-date-format="yyyy-MM-dd" readonly--]
[#--                           value="${(.now?string('yyyy-MM-dd'))!}" style="border-right: 0px; background-color: white;">--]

[#--                    <span class="input-group-btn">--]
[#--									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 25px;">--]
[#--													<img src="${base}/assets/images/rl.png" style="float:left;">--]
[#--									</button>--]
[#--								</span>--]
[#--                </div>--]
[#--            </div>--]
            <span class="tab" style="background-color: #EBEEF5">
                <a style="font-size: 14px;text-align: center;margin-top: 0px">审批状态<select style="background-color: white;height: 30px;margin-left: 30px;width: 168px">
                        <option>待审核</option>
                    </select>
                  <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 15px;background-color: #3D90FF;color: white;height: 35px">批量通过</button>
                  <button style="border:0;font-size: 14px;text-align: center;margin-top: 5px;margin-left: 15px;background-color: #3D90FF;color: white;height: 35px">导出</button></a>


            </span>
            <table class="VirusTable">
                <thead>
                <tr>
                    <th>
                        <div>序号</div>
                    </th>
                    <th>
                        <div>企业名称</div>
                    </th>
                    <th>
                        <div>政策文件</div>
                    </th>
                    <th>
                        <div>政策大类</div>
                    </th>
                    <th>
                        <div>政策小类</div>
                    </th>
                    <th>
                        <div>政策条件</div>
                    </th>
                    <th>
                        <div>政策奖励</div>
                    </th>
                    <th>
                        <div></div>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>南京*******公司</td>
                    <td>关于建邺</td>
                    <td>落户奖励</td>
                    <td>开办奖励</td>
                    <td>实缴到位注册资本达...</td>
                    <td>奖励30万</td>
                    <td>
                        <div class="checkbtn"style="cursor: pointer;" onclick="window.open('${base}/spxq','_self')">审批</div>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>镇江*******公司</td>
                    <td>关于建邺</td>
                    <td>经济贡献奖</td>
                    <td>股权投资企业</td>
                    <td>第1-3年</td>
                    <td>对区域经济贡献100对区域经济贡献100%</td>
                    <td>
                        <div class="checkbtn"style="cursor: pointer;" onclick="window.open('${base}/spxq1','_self')">审批</div>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>



    </div>


</div>
[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<script type="text/javascript">
    $(function(){
        initTime();
    });
    function initTime(){
        $(".form_datetime").datetimepicker({
            autoclose: true,
            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd",
            minuteStep : 1,
            minView : 2,
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
            language: 'zh_CN'
        });
    }
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