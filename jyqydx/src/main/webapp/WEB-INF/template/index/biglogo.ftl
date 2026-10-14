
<div id="biglogo"
     style="background-color:rgba(0,0,0,0 );width:195px;height:161px;position: fixed;z-index:8887;left: 1725px;top:257px;display: none">
    <div style="float: left;background-color:white;">
        <img style="margin: 10px 15px 0 15px" src="${base}/assets/images/位图.png"/><br/>
        <span style="padding-bottom:5px;width: 144px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;text-align: center;font-weight: 600;position: absolute;background-color: white">建邺政策秒兑</span>
    </div>
</div>
<div style="background-color:rgba(0,0,0,0 );width:195px;height:161px;position: fixed;z-index:9999;left: 1725px;top:257px">

    <div style="float: left;background-color:white;margin-top: 94px;z-index:19999;position: absolute;right:12px;"
         onclick="showBiglogo()">
        <img style="margin: 10px 15px 0 15px" src="${base}/assets/images/二维码.png"/><br/>
        <span style="padding-bottom:11px;width: 50px;height: 14px;font-family: PingFangSC-Regular;font-size: 10px;color: #333333;letter-spacing: 0;text-align: center;font-weight: 400;position: absolute;background-color: white">二维码</span>

    </div>
</div>

<script type="text/javascript">
    var check=1;
    //showBiglogo();
    function showBiglogo() {
        if(check==0){
            $("#biglogo").attr("style","display:block;background-color:rgba(0,0,0,0 );width:195px;height:161px;position: fixed;z-index:9999;left: 1725px;top:380px");
            check=1;
        }else{
            $("#biglogo").attr("style","display:none;");
            check=0;

        }
    }
</script>