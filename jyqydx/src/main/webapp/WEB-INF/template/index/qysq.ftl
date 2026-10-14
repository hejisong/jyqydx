<link rel="stylesheet" href="${base}/assets/css/zhemutanchukuang.css" />
[#include "common/head.ftl"]
<body style="width: 100%;margin: 0">



<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

<div  style="background-color: #f8f4fd;margin-top: -13px;padding-bottom: 1px;">
        <div class="wrapper_content_main"  style="width: 74%;">
            <input type="hidden" id="pwdsalt" value="${pwdsalt}">

            <ul class="tab">
                <li class="tab_item">

                    <a style="color: black;text-decoration:none;font-size: 20px;" >当前被授权人：</a>
                    <input type="hidden" id="t_authorname" value="${authorname}">

                </li>
                <li class="tab_item">
                    <a style="color: #3D91FF;text-decoration:none;font-size: 25px;" >${authorname}</a>
                </li>

                [#if isauthor=='1']
                    <li class="tab_item" style="line-height: 26px;">
                        <button style="border: 0;background-color:#3D91FF;color: white; padding: 5px;margin-left: 10px;" onclick="sqinfo()">授权</button>
                    </li>
                    [#if authorname??&&authorname!='']
                        <li class="tab_item" style="line-height: 26px;">
                            <button style="border: 0;background-color:#3D91FF;color: white; padding: 5px;margin-left: 10px;" onclick="canclesqinfo('${auid}',1)">取消授权</button>
                        </li>
                    [/#if]
                    [#else]

                        <li class="tab_item" style="line-height: 26px;">
                            <button style="border: 0;background-color:#3D91FF;color: white; padding: 5px;margin-left: 10px;" onclick="sqinfo()">授权</button>
                        </li>
                        [#if authorname??&&authorname!='']
                            <li class="tab_item" style="line-height: 26px;">
                                <button style="border: 0;background-color:#3D91FF;color: white; padding: 5px;margin-left: 10px;" onclick="canclesqinfo('${auid}',2)">取消授权</button>
                            </li>

                        [/#if]


                [/#if]


            </ul>
            <div style="height: 470px;overflow-y: auto;">
            <table class="VirusTable"  style="margin-top: 0">
                <thead>
                <tr>
                    <th style="width: 6%;">
                        <div >序号</div>
                    </th>
                    <th style="width: 20%;">
                        <div>被授权人</div>
                    </th>
                    <th style="width: 20%;">
                        <div>授权时间</div>
                    </th>
                    <th style="width: 20%;">
                        <div>取消授权时间</div>
                    </th>

                    <th style="width: 20%;">
                        <div>状态</div>
                    </th>

                  [#--  <th style="width: 14%;">
                        <div>操作</div>
                    </th>--]
                </tr>
                </thead>
                <tbody>
                [#if page.content??]
                    [#list page.content as authorlist]
                        <tr>
                            <td>${authorlist_index+1}</td>
                            <td>${authorlist.authorizedperson}</td>
                            <td>${(authorlist.authorizedtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td>${(authorlist.cancellationtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td>[#if authorlist.state=='1']已授权[#elseif authorlist.state=='2']已取消[#else]未授权[/#if]</td>
                       [#--     <td id="${authorlist.authorizedid}">[#if authorlist.state=='1'] <button style="width:70px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="button" onclick="upState('${authorlist.authorizedid}','2')">取消授权</button>[#else] <button style="width:50px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="button" onclick="upState('${authorlist.authorizedid}','1')">授权</button>[/#if]</td> --]
                        </tr>
                    [/#list]
                [/#if]

                </tbody>
            </table>


        </div>
    <div style="margin-top: 34px;margin-left: 15px;">
                [#assign pagepath="${base}/qysq?searchId=${searchId}&"]
                [#include "common/pagemodel.ftl"]
            </div>


    </div>
</div>

</div>


[#include "common/foot.ftl"]
[#include "index/t_legper.ftl"]
[#include "index/t_sqper.ftl"]
</body>

<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">

    var base = '${base}';


    function sqinfo() {
        var isauthor=$("#isauthor").val();
        if(isauthor=='1'){//法人授权
            opensqper();
        }else{
            $("#au_state").val("1");
            openlegper();
        }
    }

function canclesqinfo(authorizedid,isauthor) {
        $("#shouquan").html("取消授权");
        $("#ssqq").text("当前授权人");
        if(isauthor==1){
            cancelauthor(authorizedid);
        }else{
            $("#au_id").val(authorizedid);
            $("#au_state").val("2");
            openlegper();
        }

}




    function cancelauthor(authorizedid) {

        $.ajax({
            url : base + "/account/authorizedrecord/authorcancel",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {authorizedid:authorizedid},
            success : function(data) {
                if(data[0]==1){
                    bootbox.alert(data[1]);
                    window.location.reload();
                }else{
                    bootbox.alert(data[1]);
                }
            },
            error : function() {
                bootbox.alert("取消授权失败！");
            }
        });
    }




    function upState(authorizedid,state) {
        var mess="取消授权";
        var isauthor=$("#isauthor").val();
        var html="";
        if(state=="1"){
            var mess="授权";
            if(isauthor=='1'){
                sq(authorizedid,state,mess);
            }else{
                openlegper(authorizedid,state);
            }
            // html='<button style="width:70px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="button" onclick="upState(\''+authorizedid+'\',\'2\')">取消授权</button>';
        }else{
            // html='<button style="width:50px;height: 29px;line-height:29px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="button" onclick="upState(\''+authorizedid+'\',\'1\')">授权</button>';

            if(isauthor=='1'){
                sq(authorizedid,state,mess);
            }else{
                openlegper(authorizedid,state);
            }

        }

    }


    function sq(authorizedid,state,mess) {
        $.ajax({
            url : base + "/account/authorizedrecord/upstate",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {authorizedid:authorizedid,state:state},
            success : function(data) {
                if(data=="SUCCESS"){
                    bootbox.confirm(mess+"成功！",function (result) {
                        window.location.reload();
                    })

                }else{
                    bootbox.alert(mess+"失败！");
                }
            },
            error : function() {
                bootbox.alert("数据加载失败！");
            }
        });
    }

</script>
</html>