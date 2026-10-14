[#include "common/head.ftl"]


<body>

<div style="background-color: #f8f4fd;min-height: 16px;margin-top: -13px"></div>

    <div style="background-color: #f8f4fd;margin-top: 0px;min-height: 500px;">

        <div style="width:1080px;margin: auto; height: auto;">

            <div style="width: 95%;border:1px ;margin: auto;" id="newpolicy">
                <form action="${base}/newpolicy" method="get" id="fileinfo">
                    <div style="border:double; border-color: #aebcf3;margin-top: 0px;margin-bottom: 5px;">

                        <div style="width: 100%;margin-top: 10px;"> <span style="width:80px;margin-left: 10px;font-weight:bold;font-size: 15px">政策级别</span>
                            <a style="color: [#if level=='']#6096EC;[#else] black[/#if];text-decoration: none;margin-left: 8px;font-size: 14px;" href="javascript:void(0);" onclick="filelevel('')">全部</a>
                            <a style="color: [#if level=='1']#6096EC;[#else] black[/#if];text-decoration: none;margin-left: 8px;font-size: 14px;" href="javascript:void(0);" onclick="filelevel('1')">南京市</a>
                            <a style="color: [#if level=='2']#6096EC;[#else] black[/#if];text-decoration: none;margin-left: 8px;font-size: 14px;" href="javascript:void(0);" onclick="filelevel('2')">建邺区</a>
                            <input name="level" id="level"  type="hidden" value="${level}">
                        </div>


                      [#--  <div class="form-group">
                            <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 生效日期<span style="color: red;">*</span>  </label>
                            <div class="col-xs-3">

                                <div class="input-group date form_datetime ">
                                    <input class="form-control input-inline"  placeholder=""  name="releasetimestr" id="releasetime" data-date-format="yyyy-MM-dd" readonly
                                           value="${policyfile.releasetime}" style="border-right: 0px; background-color: white;height: 34px;">

                                    <span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
                                </div>

                            </div>
                            <label class="col-xs-2 control-label no-padding-right" style="text-align: right;"> 失效日期</label>
                            <div class="col-xs-3">

                                <div class="input-group date form_datetime ">
                                    <input class="form-control input-inline" placeholder=""  name="invalidtimestr" id="invalidtime" data-date-format="yyyy-MM-dd" readonly
                                           value="${policyfile.invalidtime}" style="border-right: 0px; background-color: white;height: 34px;">

                                    <span class="input-group-btn">
									<button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 34px;">
													<img src="${base}/assets/images/rl.png" style="float:left;">
									</button>
								</span>
                                </div>

                            </div>

                        </div>--]



















                        <div style="width: 100%;margin-top: 10px;margin-bottom: 5px;"><label style="width:67px;margin-left: 10px;font-weight:bold;font-size: 15px;float: left;padding-top: 8px;">生效日期</label>

                                <input name="startdate" id="startdate" style="border: 1px solid #D8D8D8;height: 27px;width: 13%;float: left;margin-top: 3px;" value="[#if startdate??]${(startdate?string('yyyy-MM-dd')) }[/#if]" type="date">
                                <button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 33px;width: 3%;float: left;margin-top: 3px;" onclick="resetDate('startdate');">X</button>


                            <label style="margin:0 20px;font-weight:bold;font-size: 15px;float: left;padding-top: 8px;">至</label>
                                <input name="enddate" id="enddate" style="border: 1px solid #D8D8D8;height: 27px;width: 13%;float: left;margin-top: 3px;" value="[#if enddate??]${(enddate?string('yyyy-MM-dd')) }[/#if]" type="date">
                            <button class="btn btn-primary date-set" type="button" style="padding: 0px;height: 33px;width: 3%;float: left;margin-top: 3px;"onclick="resetDate('enddate');">X</button>
                            <label style="width:60px;margin-left: 25px;font-weight:bold;font-size: 15px">关键字</label>
                            <input name="key"  id="key"  placeholder="多个关键字用、隔开" style="border: 1px solid #D8D8D8;height: 27px;width: 35%;" value="${key}" type="text">
                            <button style="width:50px;height: 40px;line-height:38px;text-align:center;background-color: #3D90FF;color: #fff;font-size: 14px;margin-left: 5px;border: aliceblue;" type="submit">搜索</button>
                        </div>


                        [#list tags as t]
                            [#if t_index==0]

                                <div style="width: 100%;margin-top: 10px;margin-bottom: 10px;"> <span style="width:80px;margin-left: 10px;font-weight:bold;font-size: 15px">政策标签</span>
                                    <input name="tagid" id="tagid"  type="hidden" value="${tagid}">
                                    <a style="color: [#if tagid=='']#6096EC;[#else] black[/#if];text-decoration: none;margin-left: 8px;font-size: 14px;" onclick="taginfo('',this);"   href="javascript:void(0);">全部</a>
                                    [#list t as tag]
                                        [#if tagidstrs??][#assign ishas=0][#list  tagidstrs as tid][#if tag.tagid==tid]  [#assign ishas=1] [/#if][/#list][/#if]
                                            <a style="color:  [#if ishas=1]#6096EC[#else] black[/#if];text-decoration: none;margin-left: 5px;font-size: 14px;cursor:pointer;"   id="[#if ishas=1]1[#else] 0[/#if]"   onclick="taginfo('${tag.tagid}',this);"   href="javascript:void(0);">${tag.tagname}</a>
                                    [/#list]
                                </div>

                             [#else]

                                 <div style="width: 100%;margin-top: 10px;margin-bottom: 10px;margin-left: 68px;"> <span style="width:80px;margin-left: 10px;font-weight:bold;font-size: 15px"></span>
                                     [#list t as tag]
                                         [#if tagidstrs??][#assign ishas=0][#list  tagidstrs as tid][#if tag.tagid==tid]  [#assign ishas=1] [/#if][/#list][/#if]
                                             <a style="color: [#if ishas=1]#6096EC[#else] black[/#if];text-decoration: none;margin-left: 5px;font-size: 14px;cursor:pointer"   id="[#if ishas=1]1[#else] 0[/#if]"   onclick="taginfo('${tag.tagid}',this);"   href="javascript:void(0);">${tag.tagname}</a>

                                     [/#list]
                                 </div>


                             [/#if]


                        [/#list]

                    </div>


            </form>
                        [#list page.content as file]
                            <div style="height:  ${file[8]}px;border:double; border-color: #aebcf3;padding: 5px;margin-top: 5px;">
                            <div style="height: 40px;font-weight: 600;background: white;padding-top: 3px;">
                                <a style="font-size: 20px;cursor:pointer;text-decoration: none;color: black;" href="${base}/policyfilemgr/webshow/${file[0]}/1?tagid=${tagid}&key=${key}">
                                   <span style="color: #3D90FF;">[#if file[6]=='1'] 【市级】 [#else]【区级】 [/#if]</span>   ${file[1]}</a>
                            </div>
                            <div style="padding-top: 10px;width: 100%;">
                                <div style="width: 25%;float: left;background: white;height: 30px;font-size: 15px;padding-top: 4px;color: grey;">&nbsp;&nbsp;文号：${file[2]}</div>
                                <div style="width: 5%;float: left;height: 30px;font-size: 15px;"></div>
                                <div style="width: 42%;float: left;background: white;height: 30px;font-size: 15px;padding-top: 4px;color: grey;">&nbsp;&nbsp;发文单位：${file[3]}</div>
                                <div style="width: 5%;float: left;height: 30px;font-size: 15px;"></div>
                                <div style="width: 20%;float: left;background: white;height: 30px;font-size: 15px;padding-top: 4px;color: grey;">&nbsp;&nbsp;文件生效日期：${file[4]}</div>
                                <div style="width: 3%;float: left;height: 30px;font-size: 15px;"></div>

                            </div>


                                  [#if file[7]??&&file[7]?size>0]

                                      [#assign tagcount=file[7]?size-1]


                                       [#list file[7] as policytag]
                                           [#if policytag_index==0]

                                               <div style="padding-top: 10px;width: 100%;">
                                                   <div style="width: 9%;float: left;background: white;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: #3D90FF;">&nbsp;&nbsp;政策标签： </div>
                                                   <div style="width: 72%;float: left;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;">&nbsp;
                                                       [#list policytag as tag]
                                                           <a style="padding-top: 4px;font-size: 15px;margin-left: 10px;text-decoration: none;background: #f7c49885;padding:5px;color: #ea5913;" href="${base}/policyfilemgr/webshow/${file[0]}/2?key=${key}&tagid=${tag[0]}">${tag[1]}</a>
                                                       [/#list]
                                                   </div>

                                                   <div style="width: 2%;float: left;height: 30px;font-size: 15px;"></div>

                                                       [#if file[8]==140]
                                                           <div style="width: 12%;float: left;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: white;background: #3D90FF;">
                                                                   &nbsp;&nbsp;<a href="javascript:void(0);" style="color: white;text-decoration: none;font-size: 15px;" onclick="opendetail('${file[0]}');">奖励政策明细</a>
                                                           </div>
                                                       [/#if]



                                                   <div style="width: 5%;float: left;height: 30px;font-size: 15px;"></div>

                                               </div>

                                               [#else]

                                                   <div style="padding-top: 10px;width: 100%;margin-left: 90px;">
                                                       <div style="width: 9%;float: left;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: #3D90FF;"></div>
                                                       <div style="width: 72%;float: left;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;">&nbsp;
                                                           [#list policytag as tag]
                                                               <a style="padding-top: 4px;font-size: 15px;margin-left: 10px;text-decoration: none;background: #f7c49885;padding:5px;color: #ea5913;" href="${base}/policyfilemgr/webshow/${file[0]}/2?key=${key}&tagid=${tag[0]}">${tag[1]}</a>

                                                           [/#list]
                                                       </div>

                                                       <div style="width: 2%;float: left;height: 30px;font-size: 15px;"></div>

                                                           [#if policytag_index==tagcount]
                                                               <div style="width: 12%;float: left;background: #3D90FF;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: white;">
                                                                   &nbsp;&nbsp;<a href="javascript:void(0);" style="color: white!important;text-decoration: none;font-size: 15px;" onclick="opendetail('${file[0]}');">奖励政策明细</a>
                                                               </div>
                                                           [/#if]


                                                       <div style="width: 5%;float: left;height: 30px;font-size: 15px;"></div>

                                                   </div>

                                               [/#if]



                                       [/#list]




                                  [#else]

                                      <div style="padding-top: 10px;width: 100%;">
                                          <div style="width: 9%;float: left;background: white;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: #3D90FF;">&nbsp;
                                              &nbsp;政策标签： </div>
                                          <div style="width: 72%;float: left;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;">&nbsp;</div>

                                          <div style="width: 2%;float: left;height: 30px;font-size: 15px;"></div>
                                          <div style="width: 12%;float: left;background: #3D90FF;height: 30px;font-size: 15px;padding-top: 4px;margin-top: 10px;color: white;">
                                              &nbsp;&nbsp;<a href="javascript:void(0);" style="color: white;text-decoration: none;font-size: 15px;" onclick="opendetail('${file[0]}');">奖励政策明细</a></div>

                                          <div style="width: 5%;float: left;height: 30px;font-size: 15px;"></div>

                                      </div>


                                  [/#if]













                            </div>
                        [/#list]

                    <div style="height: 59px;padding: 5px;margin-top: 5px;">
                    [#assign pagepath="${base}/newpolicy?startdate=${(startdate?string('yyyy-MM-dd'))! }&enddate=${(enddate?string('yyyy-MM-dd'))! }&tagid=${tagid}&level=${level}&"]
                    [#include "common/pagemodel.ftl"]
                </div>






            </div>

        </div>



    [#include "policy/t_detail.ftl"]
</div>
</div>
[#include "common/foot.ftl"]
</body>
<input type="hidden" id="search_companyid" name="companyid" value="${search_companyid}">
<input type="hidden" id="search_companyname" value="${search_companyname}">
<script type="text/javascript">
    $(function(){
        initTime();
    });
    var base = '${base}';
    function openinpolicyinfo(policyname){
        window.location.href="${base}/searchNewpolicyinfo?policyname="+policyname;

    }




    function initTime() {
        $(".form_datetime").datetimepicker({
            autoclose : true,
            format : "yyyy-MM-dd",
            minuteStep : 1,
            minView : 2,
            pickerPosition:'bottom-left',
            language : 'zh_CN'
        });
    }

    function resetDate(resetdate) {
        $("#"+resetdate).val("");
    }

    function filelevel(level) {
        $("#level").val(level);
        $("#fileinfo").submit();
    }

    function taginfo(tagid,e) {
        if(tagid==''){
            $("#tagid").val('');
            $("#fileinfo").submit();
        }else{
            var tag=$("#tagid").val();
            var ischecked=$(e).attr("id");
            if(ischecked=="1"){
                $(e).attr("id","0");
                var tagids=tagid+",";
                tag=tag.replace(tagids,"");
                $(e).attr("color","balck");
            }else{
                $(e).attr("id","1");
                $(e).attr("color","#6096EC");
                tag+=tagid+",";
            }

            $("#tagid").val(tag);
            $("#fileinfo").submit();
        }

    }

    function queryByKey() {
        var content=$("#content").val();
        if(content==null||content==""||content==undefined){
            bootbox.alert("请输入关键字！");
        }else{
            window.location.href="${base}/policyfilemgr/queryByKey?content="+content;
        }

    }


    function apply(pldtailid) {
        var companyid=$("#companyid").val();
        var isauthor=$("#isauthor").val();
        if(companyid!=undefined&&companyid!=null&&companyid!=""){
            //查询是否有权限
            if(isauthor=='0'||isauthor=='1'){
                //查询该政策是否已经申报过了
                $.ajax({
                    url : base + "/policyfilemgr/applycheck",
                    type : "POST",
                    async : false,
                    datatype : 'json',
                    data : {pldtailid:pldtailid,companyid:companyid},
                    success : function(data) {
                        if(data=="SUCCESS"){
                            bootbox.confirm("确认申报?",function (result) {
                                if(result==true){
                                    window.location.href=base + "/policyfilemgr/apply/"+pldtailid+"/"+companyid;
                                }

                            })
                        }else{
                            bootbox.alert("该政策已匹配，请勿重复申报！");
                        }
                    },
                    error : function() {
                        bootbox.alert("数据加载失败！");
                    }
                });
            }else{
                bootbox.alert("当前登录人没有权限操作，请先授权再操作！");
            }




        }else{
            bootbox.alert("请先登录再申报！");
        }



    }

    function isauthor() {
        var usercompanyid=$("#usercompanyid").val();
        var isauther=false;
        $.ajax({
            url : base + "/isauthor",
            type : "POST",
            async : false,
            datatype : 'json',
            data : {usercompanyid:usercompanyid},
            success : function(data) {
                if(data=="0"||data=='1'){
                    isauther=true;
                    return isauther;
                }else{
                    return isauther;
                }
            },
            error : function() {
                bootbox.alert("数据加载失败！");
            }
        });
        return isauther;
    }

</script>
</html>