<div id="fullbg" style="width:100%;height:100%;display:none;"></div>
<!-- 遮挡层 -->
<div class="dialog" id="orderperson" style="display:none;height:400px;width:610px;">
    <div>
        <div class="navbar-header pull-left" style="background-color: #3f83e8;width: 610px;">
            <a href="#" style="color: white;" class="navbar-brand"><small>选择代办人员</small></a>
            <a style="position: absolute;right: 1px;" class="navbar-brand" onclick="closeperson()"><img src="${base}/assets/images/gb.png"></img></a>
        </div>
        <div class="hidebox-hr" style="width: 600px;margin-left: 10px;">
            <table id="sample-table-project" class="table table-striped table-bordered table-hover" style="margin-top: 10px;margin-left: -5px;">
                <thead>
                <tr>
                    <th width="10%;" style="text-align: center;"></th>
                    <th width="20%;" style="text-align: center;">姓名</th>
                    <th width="30%;" style="text-align: center;">所属部门</th>


                </tr>
                </thead>
                <tbody id="resultcontent">
                [#list users as  user]
                    <tr>
                        <td style="text-align: center;">
                            <input type="radio" name="manid" value="${user.id}">
                        </td>
                        <td style="text-align: center;">${user.username}</td>
                        <td style="text-align: center;">${user.unitname}</td>
                    </tr>
                [/#list]
                </tbody>
            </table>

        </div>
        <div class="boxbtn" style="text-align: center;">

            <a class="btn   btn-xs btn-width  btn-info" style="width: 50px;height: 35px;" href="javascript:void(0);" onclick="selectman();">确认</a>
            <a class="btn  btn-xs btn-width  btn-info" style="width: 50px;height: 35px;" href="javascript:void(0);" onclick="closeperson();">取消</a>
        </div>
    </div>
</div><br>
<div>
    <a href="javascript:void(0);"  onclick="showperson();"></a>
</div>
<script type="text/javascript">


    //打开用户弹出框
    function showperson() {

        $("#fullbg").show();
        $("#orderperson").show();
    }
    //关闭用户弹出框
    function closeperson() {

        $("#fullbg,#orderperson").hide();
    }


    function selectman(){
        var manid= $("input[name='manid']:checked").val();
        var man= $("input[name='manid']:checked").parent().parent().find("td:eq(1)").text();
        $("#agencymanid").val(manid);
        $("#agencyman").val(man);
        closeperson();

    }


</script>