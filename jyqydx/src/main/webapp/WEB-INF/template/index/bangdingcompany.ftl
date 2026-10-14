<link rel="stylesheet" href="${base}/assets/css/common.css">
<link rel="stylesheet" href="${base}/assets/css/ueditor.css">
<link rel="stylesheet" href="${base}/assets/css/zhemutanchukuang.css">
<link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css"  />
<link rel="stylesheet" href="${base}/assets/css/font-awesome/css/font-awesome.min.css"  />
<link rel="stylesheet" href="${base}/assets/css/ace.min.css?v=1" />
<link rel="stylesheet" href="${base}/assets/css/centit.css?v=2" />
<script src="${base}/assets/js/bootstrap.min.js"></script>
<script src="${base}/assets/js/bootbox.js"></script>
<!-- 遮挡层 -->
<div class="dialog" id="user_dialog" style="display:none;height:430px;width:800px;">
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
                            <div class="table-scrollable" style="height:420px;overflow-y:auto;width: 800px">
                                <table class="search-table-form">
                                    <thead>
                                    <tr style="margin-top:20px;">
                                        <th style="width:25%;text-align: center">企业名称</th>
                                        <th style="width:25%;text-align: center">社会统一信用代码</th>
                                        <th style="width:25%;text-align: center">法定代表人</th>
                                        <th style="width:10%;text-align: center">操作</th>
                                    </tr>
                                    <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 80%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入搜索关键词" name="companyname" id="companyname">
                                    <button style="margin-top:18px;height:30px;width:63px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;vertical-align:middle;font-size: 16px" onclick="searchCompany()">搜索</button>
                                    </thead>
                                    <tbody id="companysdata">
                                    <tr style="margin-top:20px;">
                                        <td class="td-center" style="width:25%;text-align: center">企业名称</td>
                                        <td class="td-center" style="width:25%;text-align: center">社会统一信用代码 </td>
                                        <td class="td-center" style="width:25%;text-align: center">法定代表人</td>
                                        <td class="td-center" style="width:10%;text-align: center">操作</td>
                                    </tr>
                                    <tr style="margin-top:20px;">
                                        <td class="td-center" style="width:25%;text-align: center">企业1</td>
                                        <td class="td-center" style="width:25%;text-align: center">123123123 </td>
                                        <td class="td-center" style="width:25%;text-align: center">张三</td>
                                        <td class="td-center" style="width:10%;text-align: center"><button style="height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;font-size: 12px;">选中</button></td>
                                    </tr>
                                    <tr style="margin-top:20px;">
                                        <td class="td-center" style="width:25%;text-align: center">企业1</td>
                                        <td class="td-center" style="width:33%;text-align: center"> 123123123</td>
                                        <td class="td-center" style="width:25%;text-align: center">张三</td>
                                        <td class="td-center" style="width:10%;text-align: center"><button style="height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;font-size: 12px;">选中</button></td>
                                    </tr>
                                    </tbody>
                                    <tbody >
                                    <tr style="margin-top:20px;">
                                        <td class="td-center" style="width:25%;text-align: right;font-size: 16px">手机号码</td>
                                        <td class="td-center" style="width:25%;text-align: center">
                                            <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入手机号码" id="comphone">
                                        </td>
                                        <td class="td-center" style="width:25%;text-align: left">
                                            <button style="margin-top:5px;height:35px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px" onclick="sendPhoneMessage()">获取验证码</button>
                                        </td>
                                        <td class="td-center" style="width:10%;text-align: center"></td>
                                    </tr>
                                    <tr style="margin-top:20px;">
                                        <td class="td-center" style="width:25%;text-align: right;font-size: 16px">短信验证码</td>
                                        <td class="td-center" style="width:25%;text-align: center">
                                            <input style="background-color:#F5F8F8;border: 0px;height: 35px;width: 100%;margin-left: 0;margin-top:2%;padding-left: 15px;font-size: 16px;border-radius: 5px;color: #C9CACD" placeholder="请输入短信验证码" id="commess">
                                        </td>
                                        <td class="td-center" style="width:25%;text-align: left">
                                            <#--                                                <button style="margin-top:2px;height:30px;width:100px;border: 0px;border-radius:5px;background-color: #0c91e5;margin-left: 15px;color: white;font-size: 16px">获取验证码</button>-->
                                        </td>
                                        <td class="td-center" style="width:10%;text-align: center"></td>
                                    </tr>

                                    </tbody>
                                </table>
                                <div class="clearfix form-actions" style="text-align:center;width: 800px">
                                    <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="checkPhoneMessage()">绑 定</button>
                                    <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeUser_dialog()">取 消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var base = '${base}';
    //  $(function(){openUser_dialog()})
    //打开用户弹出框
    function openUser_dialog() {
        $("#fullbg").show();
        $("#user_dialog").show();
    }
    //关闭用户弹出框
    function closeUser_dialog() {
        //   clearUser_dialog();
        $("#user_dialog").hide();
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
                    //当前二维码已经成功使用
                    var companylist=new Array();;
                    companylist = data[1];
                    if(companylist.length>0){
                        $("#companysdata").html("");
                        var html="";
                        for(var i=0;i<companylist.length;i++){
                            html+="<tr style=\"margin-top:20px;\">"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+companylist[i][1]+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+companylist[i][2]+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+companylist[i][3]+"</td>"
                                +"<td class=\"td-center\" style=\"width:10%;text-align: center\">"
                                +"<div style=\"height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;font-size: 12px;\" onclick=\"checkcompany('"+companylist[i][4]+"')\">选中</div></td>"
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
    function checkcompany(a){
        $("#comphone").val(a);
        $("#comphone").text(a);
    }
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
        var phone=$("#comphone").val();
        var mess=$("#commess").val()
        if(phone==""||phone==undefined||phone=="null"){
            bootbox.alert("手机号不能为空！");
            return;
        }
        if(mess==""||mess==undefined||mess=="null"){
            bootbox.alert("请输入验证码！");
            return;
        }
        $.ajax({
            url : base + "/interface/wdnj/checkPhoneMessage",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {phone:phone,mess:mess},
            success : function(data) {
                if(data.result){
                    window.location.href=base+"/companyinresultlogin";
                    //bootbox.alert(data.errorMsg);
                }else{
                    bootbox.alert(data.errorMsg);
                }
            },
            error : function() {
                bootbox.alert("绑定企业失败！");
            }
        });
    }

</script>