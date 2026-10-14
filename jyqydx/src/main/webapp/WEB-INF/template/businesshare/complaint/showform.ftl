
				<form class="form-horizontal" role="form" id="formid" action="${base}/sys/suggest/save" method="post">

					<p style=" font-size: 22px;text-align: center; font-weight: 600;">投诉与咨询</p>

					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 标题 </label>
						<div class="col-xs-6">
							<div class="block input-icon input-icon-right">
								<input readonly type="text" id="title" name="title" value="${suggest.title }" class="col-xs-12">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 内容</label>
						<div class="col-xs-6">
							<div class="block input-icon input-icon-right">
								<textarea readonly id="content" rows="6" name="content" value="" class="form-control">${suggest.content }</textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 单位名称 </label>
						<div class="col-xs-3">
							<div class="block input-icon input-icon-right">
								<input readonly type="text" id="companyname" name="companyname" value="${suggest.companyname }" class="col-xs-12">
							</div>
						</div>
						<label class="col-xs-1 control-label no-padding-right" style="text-align: right;"> 联系方式 </label>
						<div class="col-xs-2">
							<div class="block input-icon input-icon-right">
								<input readonly type="text" id="mobile" name="mobile" value="${suggest.mobile }" class="col-xs-12">
							</div>
						</div>

					</div>

					[#if infotype=='checkshow']
						<div class="form-group">
							<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> 是否公开</label>
							<div class="col-xs-1">
								<div class="block input-icon input-icon-right">
									<input readonly type="text" id="isshow" name="isshow" value="[#if suggest.isshow='1']是[#else]否[/#if]" class="col-xs-12">
								</div>
							</div>
						</div>
					[/#if]
					[#list replys as reply]
						<div class="form-group">
							<label class="col-xs-3 control-label no-padding-right" style="text-align: right;"> [#if reply.infotype=='1']回复[#else]追问[/#if]</label>
							<div class="col-xs-6">
								<div class="block input-icon input-icon-right">
									<textarea readonly  rows="4" name="content" value="" class="form-control">${reply.content }&nbsp;&nbsp;${(reply.replytime?string('yyyy-MM-dd HH:mm:ss')) }</textarea>
								</div>
							</div>
						</div>
					[/#list]





				</form>
