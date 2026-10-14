
<div id="userbind" hidden
     style="background-color:rgba(0,0,0,0 );width:500px;height:161px;position: fixed;z-index:9999;left: 710px;top:380px">
    <div style="float: left;background-color:white;">
        <img style="margin: 10px 15px 0 15px" src="${base}/assets/images/位图.png"/><br/>
        <span style="padding-bottom:5px;width: 144px;height: 20px;font-family: PingFangSC-Semibold;font-size: 14px;color: #333333;text-align: center;font-weight: 600;position: absolute;background-color: white">建邺政策秒兑</span>
    </div>
</div>


<script type="text/javascript">
    var check=0;
    function showuserbind() {
        if(check==0){
            $("#userbind").attr("style","display:none;");
            check=1;
        }else{
            $("#userbind").attr("style","display:block;background-color:rgba(0,0,0,0 );width:195px;height:161px;position: fixed;z-index:9999;left: 1725px;top:380px");
            check=0;
        }
    }
</script>