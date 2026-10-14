[#assign css=[]] [#import "common/layout_web.ftl" as layout]
[@layout.main modelcode=['home'] otherjs=jss othercss=css]
<style>
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0 1000px white inset !important;
    }

    /*img{
        width: 30px;
        height: 30px;
    }*/
    .form-group {
        margin-bottom: 30px;
    }

    .kuang {
        [#--background-image: url(${base}/assets/images/kuang.png);--]
        background-position: center;
        width: 800px;
        height: 360px;
        display: flex;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 30px;
    }

    .left {

        height: 100%;
        flex: 1;
        color: #fff;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 15%;
    }

    .right {

        height: 100%;
        flex: 1;
    }
    .user-border{
        display: flex;
        align-items: center;
        border-bottom: 1px solid #eee;
        padding-bottom: 8px;

    }

</style>
<div class="kuang" style="height: 400px">
    <div class="left" style="background-color: rgba(0,0,0,0)">
[#--        <div> welcome</div>--]
[#--        <div style="margin: 10px 0;"> “秒触发”政策服务系统</div>--]
    </div>
    <div class="right" style="background-color: white">
        <div style="width: 86%; margin: 0 auto;">
            <form class="form-horizontal" role="form" action="" method="post" style="padding: 20px 0;">
                <input type="hidden" id="pwdsalt" value="${pwdsalt}">
                <div class="form-group user-border" >
                    <img src="${base}/assets/images/登录标题.png" style="width: 148px;height: 50px;">
                </div>
                <div class="form-group user-border" >
                    <img src="${base}/assets/images/user.png" style="width: 18px;height: 18px;">
                    <input type="text" id="loginname" value="${loginname}"
                           style="width:270px; height: 30px;border: none;
                        text-indent: 10px;font-size: 15px;"
                           placeholder="请输入用户名">
                </div>
                <div class="form-group user-border" >
                    <img src="${base}/assets/images/pwd.png" style="width: 18px;height: 18px;"></label>
                    <input type="password" id="password" value="${password}"
                           style="width:270px; height: 30px;border: none;
                        text-indent: 10px;font-size: 15px;"
                           placeholder="请输入密码">
                </div>
                <div class="form-group user-border">
                    <img src="${base}/assets/images/yzm.png" style="width: 18px;height: 18px;"></label>
                    <input type="text" id="code" style="width: 197px;height: 30px;border: none;
               text-indent: 10px;font-size: 15px;" placeholder="请输入验证码">
                    <img id="img" onclick="changeImg();" style="cursor: pointer;float: right;width: 70px;
                 margin-right: 10px;height: 35px;" />
                </div>
                <input type="hidden" id="token"  name="token" value="${token!''}"/>
                <div class="form-group">
                    <button type="button" id="tologinid" class="btn btn-xs btn-primary"
                            style="border-radius: 5px;width: 100%;height: 40px;font-size: 15px;border-radius: 20px;">登 录</button>
                </div>
                <input type="hidden" id="industry" name="industry" value="1" >
            </form>
        </div>
    </div>
</div>


    <script type="text/javascript">
        $(function () {
            changeImg();
        })

        $("#tologinid").bind("click", function () {
            var code = $("#code").val();
            if (code.length <= 0) {
                bootbox.alert("请输入验证码！");
            } else {
                toLogin();
            }
        });

        $("input[id='code']").bind("keydown", function (event) {
            if (event.keyCode == '13') {
                $("#tologinid").click();
            }
        });
        var toLogin = function () {
            /*  var b = new Base64();
             var loginname=$("#loginname").val();
             var password=$("#password").val();
             var code=$("#code").val();
             var base64loginname = b.encode(loginname);
             var base64password = b.encode(password);  */
            var loginname = $("#loginname").val();
            //待加密字符串
            var password = $("#password").val();
            //第一个参数必须；第二个、第三个参数可选
            var key = $("#pwdsalt").val();
            //加密方法
            var passwordDes = strEnc(password, key);
            var code = $("#code").val();
            var industry = $("#industry").val();
            var token = $("#token").val();
            $.ajax({
                type: "post",
                url: base + "/login",
                cache: false,
                dataType: "text",
                data: {
                    loginname: loginname,
                    passwordDes: passwordDes,
                    code: code,
                    industry: industry,
                    token: token
                },
                success: function (data) {
                    if (data == "ok") {
                        window.location.href = base + "/index";
                    } else if (data == "errorCode") {
                        bootbox.alert("验证码错误！");
                        changeImg();
                    } else if (data == "errorNum") {
                        bootbox.alert("登录失败次数超过5次，请24小时后重新登录！");
                        changeImg();
                    } else if (data == "errorMsg") {
                        bootbox.alert("用户名密码输入有误！");
                        changeImg();
                    } else if (data == "nullCode") {
                        bootbox.alert("请重新输入验证码！");
                        changeImg();
                    } else if (data == "locked") {
                        bootbox.alert("该账号已锁定，请先解锁再登录！");
                        changeImg();
                    }else {
                        window.location.href = base + "/indexlogin";
                    }
                },
                error: function () {
                    bootbox.alert("登录失败，请重新登录！", function () {
                        window.location.href = base + "/indexlogin";
                    });

                }
            });

        }

        function changeImg() {
            $.ajax({
                url: base + "/reflushcode/1",
                type: "POST",
                async: false,
                datatype: 'json',
                success: function (data) {
                    var imgname = data[0];
                    var pwdsalt = data[1];
                    $("#img").attr("src", "${base}/image/" + imgname + ".png")
                    $("#pwdsalt").val(pwdsalt);
                },
                error: function () {
                    bootbox.alert("刷新验证码失败！");
                }
            });
        }
    </script>
    [/@layout.main]
