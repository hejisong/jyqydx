<div id="chidebg"></div>
<div id="chidebox" >
    <div>
        <div class="navbar-header pull-left" style="background-color: #3f83e8;width: 600px;">
            <a href="#" style="color: white;" class="navbar-brand"><small>选择企业</small></a>
        </div>
        <div class="hidebox-hr" style="width: 600px;">
            <table id="sample-table-project" class="table table-striped table-bordered table-hover" style="margin-top: 10px;margin-left: -4px;">
                <thead>
                <th></th>
                <th>企业名称</th>
                </thead>
                <tbody id="resultcontent">
                [#list companyList as  company]
                    <tr>
                        <td style="text-align: center;">
                            <input type="radio" name="t_companyid" [#if company.companyid==logincompany.companyid] checked [/#if]value="${company.companyid}"  >

                        </td>
                        <td style="text-align: center;">${company.enterprisename}</td>
                    </tr>
                [/#list]
                </tbody>
            </table>

        </div>
        <div class="boxbtn">

            <a class="btn   btn-xs btn-width  btn-info" style="width: 50px;height: 35px;" href="javascript:void(0);" onclick="selectcorp();">确认</a>
            <a class="btn  btn-xs btn-width  btn-info" style="width: 50px;height: 35px;" href="javascript:void(0);" onclick="hidebox();">取消</a>
        </div>
    </div>
</div><br>
<div>
    <a href="javascript:void(0);"  onclick="showperson();"></a>
</div>
<script type="text/javascript">
    function showcorp() //显示隐藏层和弹出层
    {
        var hideobj=document.getElementById("chidebg");
        hidebg.style.display="block"; //显示隐藏层
        hidebg.style.height=document.body.clientHeight+"px"; //设置隐藏层的高度为当前页面高度
        document.getElementById("chidebox").style.display="block"; //显示弹出层
    }
    function hidebox() //去除隐藏层和弹出层
    {
        document.getElementById("chidebg").style.display="none";
        document.getElementById("chidebox").style.display="none";
    }
    function selectcorp(){
        var companyid= $("input[name='t_companyid']:checked").val();
        var companyname= $("input[name='t_companyid']:checked").parent().parent().find("td:eq(1)").text();
        $("#companyid").val(companyid);
        $("#search_companyid").val(companyid);
        $("#headcorp").html(companyname);
       /* document.getElementById("hidebg").style.display="none";
        document.getElementById("hidebox").style.display="none";*/
        window.location.href=base+"/changecorp/"+companyid;

    }
</script>