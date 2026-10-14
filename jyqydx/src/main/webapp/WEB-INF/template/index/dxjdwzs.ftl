
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">
<div >
    <img src="${base}/assets/images/znppxq.png" alt="">
    <div style="position: absolute;margin-left: 44.5%;margin-top: -5%">
        <div style="width: 204px;height: 48px;font-family: PingFangSC-Medium;font-size: 34px;color: #FFFFFF;">智能匹配详情</div>
    </div>
</div>
<div style="width: 100%;height: 700px;background: #F3F7FA; padding-top: 20px;">
    <div style="width: 1000px;height: 660px;background: #FFFFFF;border-radius: 6px;margin-left: 20% ;padding: 10px 100px">
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <img style="margin-left: -70px;padding-top: 5px" src="${base}/assets/images/图标4.png">
            <a style="width: 98px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: black;font-size: 16px;position:absolute;padding-top: 4px;margin-left: 20px">兑现进度</a>

        </div>
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">--]
[#--            <img style="margin-left: -70px" src="${base}/assets/images/直通车.png">--]
[#--            <span style="width: 98px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: #FFFFFF;margin-top: -39px;position:absolute">专属账户直通车</span>--]

[#--        </div>--]
[#--        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">--]

[#--        </div>--]
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">政策匹配</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021.2.1</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021.2.1</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="background: #3D91FF;width: 1px;height: 60px;border: 0px;margin: -10px 0 0 24px;text-align: center;color: white;font-size: 14px;padding: 0" readonly>

        </div>
        <div style="margin-top: 10px;width: 1000px;height:50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">奖励兑现</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021.2.1</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021.2.1</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="background: #3D91FF;width: 1px;height: 60px;border: 0px;margin: -10px 0 0 24px;text-align: center;color: white;font-size: 14px;padding: 0" readonly>
        </div>
        <div style="margin-top: 10px;width: 1000px;height: 50px;background: white;border-radius: 6px; ">
            <input style="border-radius: 50px;width: 16px;height: 16px;border: 2px solid #3F95FF;margin: 15px 0 0 15px;text-align: center;color: white;font-size: 14px;padding: 0"readonly>
            <a style="font-size: 16px;color: #3F93FF;margin-left: 30px;font-weight:600">票据提交</a>
            <a style="font-size: 12px;color: #51C0A0;margin-left: 30px">开始时间：2021.2.1</a>
            <a style="font-size: 12px;color: #E98B81;margin-left: 30px">结束时间：2021.2.1</a>
            <a style="font-size: 14px;color: #3F93FF;margin-left: 300px;background-color: #F6F9FE;padding: 5px 10px;font-weight: bolder">已处理</a>
        </div>

    </div>


</div>


[#include "common/foot.ftl"]
</body>
[#--[#include "common/foot.ftl"]--]
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">
    var base = '${base}';
</script>
</html>