<div id="loadingbg" style="z-index:1051;display: none;"></div>
<div class="loadingdialog" id="loading" style="height: 80px;display: none;">
	<div>
		<div style="vertical-align: middle;">
			<div class="modal-body" style="padding: 0px;">
				<div style="margin-bottom: 0px; border: 0px;">
					<div class="portlet-body form">
						<div style="padding: 10px 10px 0px 10px;">
							<div class="table-scrollable">
				 				<div class="form-group" style="margin-right: 0px; margin-left: 0px; margin-bottom: 0px; display: inline-block; width: 100%;">
									<div class="col-xs-12" style="font-size: 28px;margin-top:5px;text-align: center;">数据处理中，请稍后......</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="loading_state" id="loading_state" value="0">

 <script type="text/javascript">
	 $(function(){
		 $("#loading").ajaxStart(function(){
			 if($("#loading_state").val()=="1"){
				 $("#loadingbg").show();
				 $(this).show();
			 }
		 });

		 $("#loading").ajaxStop(function(){
			 $("#loading_state").val("0")
			 $("#loadingbg").hide();
			 $(this).hide();
		 });
	 });
</script>
 