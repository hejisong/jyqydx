
[#include "common/head.ftl"]
<body>

<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 10px;">
        <div class="wrapper_content_main"  >
            <form class="form-horizontal suggest " role="form" id="formid" action="${base}/businesshare/orderagency/save" method="post">

                <div class="form-group" style="padding-top: 20px;">
                    <p>预约代办</p>

                </div>


                <div class="form-group">
                        <input type="hidden" name="agencymanid" id="agencymanid">
                        <input type="hidden" id="ordercompanyid" name="ordercompanyid" value="${companyid}">
                        <input placeholder="请选择代办人员" style="height: 46px;width: 400px;float: left;margin-left: 10%;" type="text" id="agencyman" name="agencyman" value="" readonly class="form-control">
                        <span class="input-group-btn" style="float: left;">
									<img src="${base}/assets/images/yqlzc/query.png" style="float:left;padding-top: 6px;margin-left: -34px;cursor: pointer;" onclick="showperson();">
								</span>

                        <input placeholder="请输入企业代理人联系电话" style="height: 46px;width: 400px;" type="text" id="contact" name="contact"  class="form-control">
                </div>




            <div class="form-group"  >
                    <input placeholder="请输入备注内容" style="height: 46px;width: 823px;margin-left: 10%;"  type="text" id="remark" name="remark"  >

            </div>

                [#if logincompany.islegalperson!='1']

                <div class="form-group">


                        <input placeholder="法人手机号" style="height: 46px;width: 400px;float: left;margin-left: 10%;" type="text" id="legtel" name="phone" value="${companyEntity.phoneno}" class="form-control">
                        <button style="margin-top:5px;height:35px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px;float: left;"  type="button" onclick="getCode();" id="b_feachBtn">获取验证码</button>
                        <p style="float: left;color: red;font-size: 16px;padding-top: 10px;margin-left: 5px;font-weight: 200;">(请输入法人手机号码，非法人需法人验证后方可操作！)</p>


                </div>

                <div class="form-group">


                        <input placeholder="验证码" style="height: 46px;width: 400px;float: left;margin-left: 10%;" type="text" id="legcommess" name="mess" value="" class="form-control">



                </div>
               [/#if]



                <div  style="width: 76%;float: left;height: 40px;padding-top: 60px;margin-left: 35%;">
                    <button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 80px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="saveOrder()">提 交</button>
                    <button class="btn  btn-xs btn-width  btn-info" style="margin-left: 5px;border: aliceblue;border-radius: 20px;width: 80px;height: 35px;background-color: darkgrey!important;color: white;" type="button" onclick="returninfo()">取 消</button>
                    <button style="border: aliceblue;border-radius: 20px;background-color: #3f83e8;width: 120px;height: 35px;color: white;" class="btn btn-xs btn-primary" type="button" onclick="showOrder()">查看我的预约</button>
                </div>




        </form>

        </div>

</div>

    </div>
<input type="hidden" id="pwdsalt" value="${pwdsalt}">
[#include "index/orderperson.ftl"]
[#include "common/foot.ftl"]
</body>
<script type="text/javascript">
    var base = '${base}';
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



    // 点击获取验证码操作
    function getCode() {
        var phoneNumber = $("#legtel").val().trim();
        if (phoneNumber == '') {
            bootbox.alert('手机号不能为空');
            return false;
        } else {

            $("#b_feachBtn").css({
                background: '#d8d8d8',
                color: '#707070',
            });
            $("#b_feachBtn").text('重新获取');
            sendPhoneMessage();
        }
    }


    //发送验证码
    function sendPhoneMessage(){
        var comphone=$("#legtel").val();
        var key = $("#pwdsalt").val();
        //加密方法
        var comphoneDes = strEnc(comphone, key);


        if(comphone!=""&&comphone!=undefined&&comphone!="null"){
            $.ajax({
                url : base + "/interface/wdnj/pcsendPhoneMessage",
                type : "GET",
                async : false,
                datatype : 'json',
                data : {phone:comphoneDes},
                success : function(data) {
                    if(data.result){
                        bootbox.alert(data.errorMsg);
                    }else{
                        bootbox.alert(data.errorMsg);
                    }
                },
                error : function() {
                    bootbox.alert("验证码发送失败！");
                }
            });
        }else{
            bootbox.alert("法人手机号不能为空");
        }
    }




    function saveOrder() {
        var manid=$("#agencymanid").val();
        var contact=$("#contact").val();
        var legtel=$("#legtel").val();
        var legcommess=$("#legcommess").val();
        if(manid==null||manid==''||manid==undefined){
            bootbox.alert("请选择代办人员！");
            return;
        } else if(contact==null||contact==''||contact==undefined){
            bootbox.alert("请填写联系方式！");
            return;
        }else if (!checkTel(contact)) {
            bootbox.alert("请填写正确的手机号码！");
            return;
        }else if(legtel==""||legtel==undefined||legtel=="null"){
            bootbox.alert("法人手机号不能为空");
            return;
        }else if(legcommess==null||legcommess==''||legcommess==undefined){
            bootbox.alert("请填写验证码！");
            return;
        }else{
            // $("#formid").submit();
            $.ajax({
                url : base + "/businesshare/orderagency/save",
                type : "post",
                async : false,
                datatype : 'json',
                data : $("#formid").serialize(),
                success : function(data) {
                    if(data.result){
                        bootbox.confirm("预约成功！",function (result) {
                            window.location.reload();
                        })

                    }else{
                        bootbox.alert(data.errorMsg);
                    }
                },
                error : function() {
                    bootbox.alert("数据加载失败！");
                }
            });
        }


    }

    function checkTel(tel){
        var isMob=/^\d{11}$/;
        if(isMob.test(tel)){
            return true;
        }
        else{
            return false;
        }
    }


    function showOrder() {
        var ordercompanyid=$("#ordercompanyid").val();
        window.location.href=base+"/businesshare/orderagency/list?ordercompanyid="+ordercompanyid;
    }

    function returninfo() {
        window.location.href=base+"/yqlzc";
    }



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
</html>