<!DOCTYPE html>
<html lang="zh-CN" class="no-js" style="text-align: center;">
<head>
    <meta charset="utf-8">
    <title>南京建邺“秒触发”政策服务系统</title>
    <link rel="stylesheet" href="${base}/assets/css/common.css">
<#--    <link rel="stylesheet" href="${base}/assets/css/ueditor.css">-->
    <link rel="stylesheet" href="${base}/assets/css/zhemutanchukuang.css">
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
    <link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
    <link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />
    <script src="${base}/assets/js/jquery-3.4.1.min.js"></script>
    <script src="${base}/assets/js/bootstrap.min.js"></script>
    <script src="${base}/assets/js/bootbox.js"></script>
</head>
<style>
    tr.focus{
        background-color: #ff0000;
    }
</style>
<body style="background-color: #F3F7FA">
<div style="width: 100%;margin-top: 15px ">
    <div>
        <div>
            <a style="width: 285px;height: 28px;font-family: PingFangSC-Semibold;font-size: 20px;color: #333333;letter-spacing: 0;font-weight: 600;"><img style="vertical-align:middle;padding-right: 20px" src="${base}/assets/images/jylg.png">南京建邺“秒触发”政策服务系统</a>

        </div>

    </div>
</div>
<div style="width: 100%;margin-top: 15px ">
    <div>
        <div>
            <a style="width: 285px;height: 28px;font-family: PingFangSC-Semibold;font-size: 20px;color: #333333;letter-spacing: 0;font-weight: 600;"><img style="vertical-align:middle;padding-right: 20px" src="${base}/assets/images/wdnj.png">我的南京账号</a>
        </div>
    </div>
</div>
<div  style="height: auto; text-align: center;margin-top: 15px">
    <div  style="border-radius:15px;margin:0 auto;border:1px solid #000;width:500px;height:550px;">
        <p style="margin-top: 20px;font-size: 18px;">二维码登录</p>
<#--        <div  class="qr" style="height: auto;" onclick="openUser_dialog(${company.companyid})">-->
<#--        </div>-->
        <div  class="qr" style="height: auto;" >
        </div>
        <p style="margin-top: 20px;margin-bottom:10px;font-size: 18px;">使用"我的南京"扫描二维码登录！</p>
    </div>
</div>

</body>
<script type="text/javascript">
    // var uid ="ufjKG7ssLc";
    var qid="";
    var base = '${base}';
    $(function(){
        $.ajax({
            url : base + "/createewm",
            type : "POST",
            async : false,
            datatype : 'json',
            success : function(data) {
                var imgname = data[0];
                var ewmurl = base+"/ewmimages/"+data[1];
                qid="";
                qid = data[2];
                if(imgname=="0"){
                    var img = new Image();
                    img.src = ewmurl;
                    img.onload=function(){
                        $('.qr').html(img);
                        //二维码登陆
                        ewmlogin();
                    };
                }else{
                    bootbox.alert(ewmurl);
                }
            },
            error : function() {
                bootbox.alert("刷新二维码失败！");
            }
        });
    })
    function ewmlogin(){
        // bootbox.alert("qid:"+qid);
        $.ajax({
            url : base + "/ewmlogin",
            type : "POST",
            async : false,
            timeout:30000,
            datatype : 'json',
            data : {qid:qid},
            success : function(data) {
                if(data[0]==101){
                    //当前二维码已经成功使用
                    // window.location.href=base+"/indexewmlogin2/"+qid;
                    // bootbox.alert(data[1])
                        if(data[1]=="ok"){
                            //登录成功返回首页
                            window.location.href=base+"/companyinresultlogin";
                        }else{
                            if(data[2]=='3'){
                                bootbox.alert("该人员无所在企业，无法进行绑定！");
                            }else{
                                searchewmloginbyqid();
                                //打开企业绑定
                                var company=data[3];
                                $("#tag_1").val(data[2]);
                                openUser_dialog(company);
                            }

                        }
                }else if(data[0]==102){
                    setTimeout('ewmlogin()', 5000);
                }else if(data[0]==103){
                    //当前二维码已经失效
                    //window.location.reload();
                    setTimeout('ewmlogin()', 5000);
                }else{
                    setTimeout('ewmlogin()', 5000);
                }
            },
            error : function() {
                bootbox.alert("扫描二维码登陆失败，请刷新页面后重试！");
            }
        });
    }




</script>
<!-- 遮挡层 -->
<div class="dialog" id="user_dialog" style="display:none;height:420px;width:880px;">
    <div>
        <div style="vertical-align: middle;">
            <div class="modal-body" style="padding: 0px;">
                <div style="margin-bottom: 0px; border: 0px;">
                    <div class="navbar navbar-default" style="background-image: none;background-color: #4289FF;">
                        <div class="navbar-header pull-left">
                            <a href="#" class="navbar-brand"><small>企业绑定</small></a>
                        </div>
                        <p class="close">
                            <a class="navbar-brand" onclick="closeUser_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
                        </p>
                    </div>
                    <div class="portlet-body form">
                        <div style="padding: 0;">
                            <div class="table-scrollable" style="height:auto;overflow-y:auto;width: 880px">
                                    <table class="search-table-form" id="companys">
                                        <thead>
                                        <tr style="margin-top:20px;">
                                            <th style="width:25%;text-align: center;font-size: 16px;">企业名称</th>
                                            <th style="width:25%;text-align: center;font-size: 16px;">社会统一信用代码</th>
                                            <th style="width:25%;text-align: center;font-size: 16px;">法定代表人</th>
                                           <#-- <th style="width:10%;text-align: center;font-size: 16px;">操作</th>-->
                                        </tr>
                                       <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 80%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入搜索关键词" name="companyname" id="companyname">
                                        <button style="height:30px;width:63px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;vertical-align:middle;font-size: 16px" onclick="searchCompany()">搜索</button>
                                        </thead>
                                        <tbody id="companysdata">

                                        </tbody>
                      <#--                  <tbody >
                                        <tr style="margin-top:20px;">
                                            <td class="td-center" style="width:25%;text-align: center"></td>
                                            <td class="td-center" style="width:25%;text-align: center"></td>
                                            <td class="td-center" style="width:25%;text-align: center"></td>
                                            <td class="td-center" style="width:10%;text-align: center"></td>
                                        </tr>
                                        <tr style="margin-top:20px;">
                                            <td class="td-center" style="width:25%;text-align: right;font-size: 16px">法人手机号码</td>
                                            <td class="td-center" style="width:25%;text-align: center">
                                                <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入企业法人手机号码" id="comphone">
                                            </td>
                                            <td class="td-center" style="width:25%;text-align: left">
&lt;#&ndash;                                                //onclick="sendPhoneMessage()"&ndash;&gt;
                                                <button style="margin-top:5px;height:35px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px"  class="feachBtn">获取验证码</button>
                                            </td>
                                            <td class="td-center" style="width:10%;text-align: center"></td>
                                        </tr>
                                        <tr style="margin-top:20px;">
                                            <td class="td-center" style="width:25%;text-align: right;font-size: 16px">短信验证码</td>
                                            <td class="td-center" style="width:25%;text-align: center">
                                                <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入短信验证码" id="commess">
                                            </td>
                                            <td class="td-center" style="width:25%;text-align: left">
                                                &lt;#&ndash;<button style="margin-top:2px;height:30px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px">获取验证码</button>&ndash;&gt;
                                            </td>
                                            <td class="td-center" style="width:10%;text-align: center"></td>
                                        </tr>
                                        </tbody>-->
                                    </table>
                                    <div class="clearfix form-actions" style="text-align:center;width: 880px">
                                        <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="checkPhoneMessage()">绑 定</button>
<#--                                        <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeUser_dialog()">取 消</button>-->
                                        <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="returnindex()">取 消</button>

                                    </div>
                            </div>
<#--                            <div class="table-scrollable" style="height:auto;overflow-y:auto;width: 880px">-->
<#--                                <table class="search-table-form" id="authorizeds">-->
<#--                                    <thead>-->
<#--                                    <tr style="margin-top:20px;">-->
<#--                                        <th style="width:40%;text-align: center;font-size: 16px;">被授权人</th>-->
<#--                                        <th style="width:40%;text-align: center;font-size: 16px;">手机号</th>-->
<#--                                        <th style="width:10%;text-align: center;font-size: 16px;">操作</th>-->
<#--                                    </tr>-->
<#--                                    <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 40%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入人员名称" name="personname" id="personname">-->
<#--                                    <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 40%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入人员手机号" name="phonenum" id="phonenum">-->
<#--                                    <button style="margin-top:18px;height:30px;width:63px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;vertical-align:middle;font-size: 16px" onclick="searchcompanyuserlist()">搜索</button>-->
<#--                                    </thead>-->
<#--                                    <tbody id="companyusersdata">-->
<#--                                    </tbody>-->
<#--                                    <tbody >-->
<#--                                    <tr style="margin-top:20px;">-->
<#--                                        <td class="td-center" ></td>-->
<#--                                        <td class="td-center" ></td>-->
<#--                                        <td class="td-center" ></td>-->
<#--                                    </tr>-->
<#--                                    <tr style="margin-top:20px;">-->
<#--                                        <input type="hidden" id="authorphonenum">-->
<#--                                        <td class="td-center" style="text-align: right;font-size: 16px">被授权人</td>-->
<#--                                        <td class="td-center" >-->
<#--                                            <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="被授权人" id="authorizedperson">-->
<#--                                        </td>-->
<#--                                        <td class="td-center" style="width:25%;text-align: left">-->
<#--                                            &lt;#&ndash;                                                //onclick="sendPhoneMessage()"&ndash;&gt;-->
<#--                                            <button style="margin-top:5px;height:35px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px"  onclick="saveauthor()">授 权</button>-->
<#--                                        </td>-->
<#--                                    </tr>-->
<#--                                    </tbody>-->
<#--                                </table>-->
<#--&lt;#&ndash;                                <div class="clearfix form-actions" style="text-align:center;width: 800px">&ndash;&gt;-->
<#--&lt;#&ndash;                                    <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="checkPhoneMessage()">授 权</button>&ndash;&gt;-->
<#--&lt;#&ndash;                                    <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeUser_dialog()">取 消</button>&ndash;&gt;-->
<#--&lt;#&ndash;                                </div>&ndash;&gt;-->
<#--                            </div>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="comhiddenfrom">
    <input type="hidden" id="companyid_1" name="companyid">
    <input type="hidden" id="username_1" name="username">
    <input type="hidden"  id="linknum_1" name="linknum">
    <input type="hidden"  id="linktel_1" name="linktel">
    <input type="hidden"  id="tag_1" name="tag">
</form>




<script type="text/javascript">
    function searchewmloginbyqid(){
        $.ajax({
            url : base + "/searchewmloginbyqid",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {qid:qid},
            success : function(data) {
                if(data[0]==1){
                    $("#username_1").val(data[1].username);
                    $("#linknum_1").val(data[1].linknum);
                    $("#linktel_1").val(data[1].linktel);
                }else{
                    bootbox.alert(data.errorMsg);
                }
            },
            error : function() {
                bootbox.alert("查询企业失败！");
            }
        });
    }
     // $(document).ready(function () {
     //     $("#companys>tbody>tr>div").on("click", function () {
     //      $(this).parent().parent().find("tr.focus").toggleClass("focus");//取消原先选中行
     //         $(this).toggleClass("focus");//设定当前行为选中行
     //     });
     // });
    var base = '${base}';
    //打开用户弹出框
    function openUser_dialog(company) {
        $("#companyid_1").val(company.companyid);
        var phoneno="";
        var lrepresentative="";
        if(company.phoneno!=null){
            phoneno=company.phoneno;
            $("#comphone").val(phoneno);
        }

        if(company.lrepresentative!=null){
            lrepresentative=company.lrepresentative;
        }
       var html="";

        html+="<tr  style=\"margin-top:20px;\">"
            +"<td id='"+company.companyid+"' class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+company.enterprisename+"</td>"
            +"<td class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+company.uscreditcode+"</td>"
            +"<td class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+lrepresentative+"</td>"
            +"<td class=\"td-center\" style=\"width:10%;text-align: center;font-size: 15px;\">"
         /*   +"<div class=\"companyse\" style=\"height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;margin:0 auto;font-size: 15px;\" onclick=\"checkcompany('"+phoneno+"','"+company.companyid+"')\">选择</div></td>"*/
        html+=" </tr>";
        $("#companysdata").html(html);

        $("#fullbg").show();
        $("#user_dialog").show();
    }
    //关闭用户弹出框
    function closeUser_dialog() {
        //   clearUser_dialog();
        $("#user_dialog").hide();
    }
    function returnindex() {
        $("#user_dialog").hide();
        window.location.href=base+"/companyinresultlogin";
    }
    function searchCompany(){
        var companyname=$("#companyname").val();
        $.ajax({
            url : base + "/searchCompanyandPhone",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {enterprisename:companyname},
            success : function(data) {
                if(data[0]==1){
                    //
                    var companylist=new Array();;
                    companylist = data[1];
                    if(companylist.length>0){
                        $("#companysdata").html("");
                        var html="";
                        for(var i=0;i<companylist.length;i++){
                            var phoneno="";
                            var uscreditcode="";
                            if(companylist[i][3]!=null){
                                phoneno=companylist[i][3];
                            }
                            if(companylist[i][2]!=null){
                                uscreditcode=companylist[i][2];
                            }
                            html+="<tr style=\"margin-top:20px;\">"
                            +"<td id='"+companylist[i][0]+"' class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+companylist[i][1]+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+uscreditcode+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center;font-size: 15px;\">"+phoneno+"</td>"
                                +"<td class=\"td-center\" style=\"width:10%;text-align: center;font-size: 15px;\">"
                                +"<div class=\"companyse\" style=\"height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;margin:0 auto;font-size: 15px;\" onclick=\"checkcompany('"+companylist[i][4]+"','"+companylist[i][0]+"')\">选择</div></td>"
                            html+=" </tr>";
                        }

                        $("#companysdata").html(html);
                    }
                }else{
                    bootbox.alert(data[1]);
                }
            },
            error : function() {
                bootbox.alert("查询企业失败！");
            }
        });
    }
    //选择企业
    function checkcompany(phone,companyid){
        if(phone=="null"){
            bootbox.alert("未查询到手机号")
            phone="";
        }
        $("#comphone").val(phone);
        $("#comphone").text(phone);


        $("#companyid_1").val(companyid);
        $("#"+companyid).attr("style","color:#48b6f7;width:25%;text-align: center;font-size: 15px;");



    }
    // 点击获取验证码操作
    $('.feachBtn').click(function () {
        var phoneNumber = $("#comphone").val().trim();
        if (phoneNumber == '') {
            bootbox.alert('手机号不能为空');
            return false;
        } else {
            // var count = 60;
            // for(var i=0;i<61;i++){
            //     setTimeout(countcheck(count), 1000);
            //      count--;
            //
            // }
            // $('.feachBtn').attr('disabled', true);
            $('.feachBtn').css({
                background: '#d8d8d8',
                color: '#707070',
            });
            $('.feachBtn').text('重新获取');
           sendPhoneMessage();
        }
    });
    // function countcheck(count){
    //     if (count === 0) {
    //         $('.feachBtn').text('重新发送').removeAttr('disabled');
    //         $('.feachBtn').css({
    //             background: '#3CB371',
    //             color: '#fff',
    //         });
    //     } else {
    //         $('.feachBtn').attr('disabled', true);
    //         $('.feachBtn').css({
    //             background: '#d8d8d8',
    //             color: '#707070',
    //         });
    //         $('.feachBtn').text('重新获取' + count);
    //     }
    // }
//发送验证码
    function sendPhoneMessage(){
        var comphone=$("#comphone").val();
        if(comphone!=""&&comphone!=undefined&&comphone!="null"){
            $.ajax({
                url : base + "/interface/wdnj/sendPhoneMessage",
                type : "GET",
                async : false,
                datatype : 'json',
                data : {phone:comphone},
                success : function(data) {
                    if(data.result){
                        bootbox.alert(data.errorMsg);
                    }else{
                        bootbox.alert(data.errorMsg);
                    }
                },
                error : function() {
                    bootbox.alert("查询企业失败！");
                }
            });
        }else{
            bootbox.alert("法人手机号不能为空")
        }
    }
    function checkPhoneMessage(){
        var username=$("#username_1").val();
        var linknum=$("#linknum_1").val();
        var linktel=$("#linktel_1").val();
        var companyid=$("#companyid_1").val();
        var islegalperson=$("#tag_1").val();
        $.ajax({
            url : base + "/interface/wdnj/pccheckPhoneMessage",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {username:username,linknum:linknum,linktel:linktel,companyid:companyid,islegalperson:islegalperson},
            success : function(data) {
                if(data.result){
                    //绑定企业返回首页
                    bootbox.confirm("绑定成功！请扫码登录！",function (result) {
                        window.location.href=base+"/indexewmlogin";
                    })

                    // window.location.href=base+"/companyinresultlogin";
                    // bootbox.alert(data.errorMsg);
                }else{
                    bootbox.alert(data.errorMsg);
                }
            },
            error : function() {
                bootbox.alert("绑定企业失败！");
            }
        });
    }

//查询企业用户信息
    function searchcompanyuserlist(){
        var personname=$("#personname").val();
        var phonenum=$("#phonenum").val();
        $.ajax({
            url : base + "/account/authorizedrecord/searchcompanyuserlist",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {personname:personname,phonenum:phonenum},
            success : function(data) {
                if(data[0]==1){
                    //
                    var companyuserlist=new Array();;
                    companyuserlist = data[1];
                    if(companyuserlist.length>0){
                        $("#companyusersdata").html("");
                        var html="";
                        for(var i=0;i<companyuserlist.length;i++){
                            html+="<tr style=\"margin-top:20px;\">"
                                +"<td class=\"td-center\" style=\"text-align: center;font-size: 15px;\">"+companyuserlist[i][0]+"</td>"
                                +"<td class=\"td-center\" style=\"text-align: center;font-size: 15px;\">"+companyuserlist[i][1]+"</td>"
                                +"<td class=\"td-center\" style=\"text-align: center;font-size: 15px;\">"
                                +"<div class=\"companyse\" style=\"height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;margin:0 auto;font-size: 15px;\" onclick=\"checkcompanyuser('"+companyuserlist[i][0]+"','"+companyuserlist[i][1]+"')\">选择</div></td>"
                            html+=" </tr>";
                        }

                        $("#companyusersdata").html(html);
                    }
                }else{
                    bootbox.alert(data[1]);
                }
            },
            error : function() {
                bootbox.alert("查询企业用户失败！");
            }
        });
    }
    //选择被授权人
    function checkcompanyuser(authorizedperson,phonenum){
        $("#authorizedperson").val(authorizedperson);
        $("#authorizedperson").text(authorizedperson);
        $("#authorphonenum").val(phonenum);
    }
    //保存授权
    function saveauthor() {
        var authorizedperson=$("#authorizedperson").val();
        if(authorizedperson==""||authorizedperson=="undefined"){
            bootbox.alert("请选择授权人");
            return;
        }
        var phonenum=$("#authorphonenum").val();
        // var companyid="a3ca45bd0d99cc558aabcef03cfabbad";
        var companyid=$("#companyid_1").val();
        if(companyid==""||companyid=="undefined"){
            bootbox.alert("请先绑定企业");
            return;
        }
        console.log(phonenum);
        $.ajax({
            url : base + "/account/authorizedrecord/saveauthorpersoninfo",
            type : "POST",
            async : false,
            datatype : 'json',
            data : {authorizedperson:authorizedperson,phonenum:phonenum,companyid:companyid},
            success : function(data) {
                if(data[0]=="0"){
                    bootbox.alert("保存失败")
                }else{
                    bootbox.alert("保存成功")
                }
            },
            error : function() {
                bootbox.alert("保存失败")
            }
        });
    }

</script>
</html>