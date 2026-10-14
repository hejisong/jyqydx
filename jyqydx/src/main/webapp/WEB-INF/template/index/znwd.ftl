
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">

<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>
<div style="width: 100%;height: 689px;background: #f8f4fd; padding-top: 20px;">
    <div style="margin-top: 5px;width: 60%;height: 73px;background: #FFFFFF;border-radius: 6px; margin-left: 23%;border-bottom: 5px solid #F3F3F3;">
        <img style="margin-top: 17px;padding-left: 20px" src="${base}/assets/images/jiqiren.png">

        <a style="font-family: PingFangSC-Medium;font-size: 26px;color: #333333;letter-spacing: 0;font-weight: 500;margin-left: 10px;position: absolute;padding-top: 15px">智能问答</a>
        <button style="width: 100px;height: 40px;border: 1px solid #519DFE;border-radius: 6px;ont-family: PingFangSC-Medium;;margin-top: 15px;position: absolute;right:19%;padding: 0px;font-size: 16px;color: #519DFE;letter-spacing: 0;font-weight: 500;background-color: rgba(0,0,0,0)" readonly onclick="javascript:history.back(-1);">返回</button>
    </div>
    <iframe style="float: left;width: 60%;height: 580px;border: 0px; margin-left: 23%" src="https://h5-alimebot.dingtalk.com/intl/index.htm?sourceType=ding_talk&from=DEFCYOnKKb0OKvr0E3UhjF7rZhc">

    </iframe>


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