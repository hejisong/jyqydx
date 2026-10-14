<div id="fullbg" style="width:100%;height:100%;display:none;"></div>
<!-- 遮挡层 -->
<div class="dialog" id="selectcompanyinfo" style="display:none;height:490px;width:820px;">
    <div>
        <div style="vertical-align: middle;">
            <div class="modal-body" style="padding: 0px;">
                <div style="margin-bottom: 0px; border: 0px;">
                    <div class="navbar navbar-default" style="background-image: none;background-color: #4289FF;">
                        <div class="navbar-header pull-left">
                            <a style="font-size: 16px;" href="#" class="navbar-brand"><small>选择企业</small></a>
                        </div>
                        <p class="close">
                            <a class="navbar-brand" onclick="closeselectcompany_dialog()"><img src="${base}/assets/images/gb.png"></img></a>
                        </p>
                    </div>
                    <div class="portlet-body form">
                        <div style="padding: 0;">
                            <div class="table-scrollable" style="height:420px;overflow-y:auto;width: 800px;margin-left: 10px;">
                                <table class="search-table-form">
                                    <thead>
                                    <tr style="margin-top:20px;font-size: 15px;">
                                        <th style="width:45%;text-align: center">企业名称</th>
                                        <th style="width:35%;text-align: center">社会统一信用代码</th>
                                        <th style="width:10%;text-align: center">法定代表人</th>
                                        <th style="width:10%;text-align: center">操作</th>
                                    </tr>

                                    </thead>
                                    <tbody id="companysdata">
                                    </tbody>
                                </table>
                                <div class="clearfix form-actions" style="text-align:center;width: 800px">

                                    <button class="btn  btn-xs btn-width  btn-info" type="button" onclick="closeselectcompany_dialog()">取 消</button>
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
    //打开弹出框
    function openselectcompany_dialog() {
       /* var hideobj=document.getElementById("companybg");
        hidebg.style.display="block"; //显示隐藏层
        hidebg.style.height=document.body.clientHeight+"px"; //设置隐藏层的高度为当前页面高度
        document.getElementById("companybox").style.display="block"; //显示弹出层*/

        $("#fullbg").show();
        $("#selectcompanyinfo").show();
    }
    //关闭弹出框
    function closeselectcompany_dialog() {
       /* document.getElementById("companybg").style.display="none";
        document.getElementById("companybox").style.display="none";*/
        $("#fullbg,#selectcompanyinfo").hide();
    }
    //企业查询弹出框
    function searchCompanydata(){
        var companyid=$("#companyid").val();
        var selectcompanydata=$("#search_companyname").val();
        if(selectcompanydata==null||selectcompanydata==undefined||selectcompanydata==""){
            bootbox.alert("请输入企业名称或统一社会信用代码！");
            return false;
        }


        $.ajax({
            url : base + "/searchcompanybyname",
            type : "GET",
            async : false,
            datatype : 'json',
            data : {searchName:selectcompanydata},
            success : function(data) {
                if(data[0]==1){
                    openselectcompany_dialog();
                    //当前二维码已经成功使用
                    var companylist=new Array();
                    companylist = data[1];
                    if(companylist.length>0){
                        $("#companysdata").html("");
                        var html="";
                        for(var i=0;i<companylist.length;i++){
                            var uscreditcode="";
                            var lrepresentative="";
                            if(companylist[i].uscreditcode!=null){
                                uscreditcode=companylist[i].uscreditcode;
                            }
                            if(companylist[i].lrepresentative!=null){
                                lrepresentative=companylist[i].lrepresentative;
                            }
                            html+="<tr style=\"margin-top:20px;font-size: 15px;\">"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+companylist[i].enterprisename+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+uscreditcode+"</td>"
                                +"<td class=\"td-center\" style=\"width:25%;text-align: center\">"+lrepresentative+"</td>"
                                +"<td class=\"td-center\" style=\"width:10%;text-align: center\">"
                                +"<div style=\"height:20px;width:50px;border: 0px;border-radius:5px;background-color: #0c91e5;color: white;vertical-align:middle;font-size: 12px;\" onclick=\"checkcompanyinfo('"+companylist[i].companyid+"','"+companylist[i].enterprisename+"')\">选择</div></td>"
                            html+=" </tr>";
                        }

                        $("#companysdata").html(html);
                    }
                }else if(data[0]==2){
                    var companyEntity=data[1];
                    if(companyEntity.companyid==companyid){
                        window.location.href=base+"/zcfhxpg?searchName="+data[1].enterprisename+"&companyid="+data[1].companyid+"&enjoypolicycategory=1";

                    }else{
                        window.location.href=base+"/dxpg?searchName="+data[1].enterprisename+"&companyid="+data[1].companyid+"&enjoypolicycategory=1";
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
    function checkcompanyinfo(companyid,enterprisename){
        closeselectcompany_dialog();
        window.location.href=base+"/dxpg?searchName="+enterprisename+"&companyid="+companyid+"&enjoypolicycategory=1";
    }

</script>