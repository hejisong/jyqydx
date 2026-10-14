<table id="sample-table-1" class="table table-bordered table-bg"  style="word-break:break-all; word-wrap:break-all;">
	<tbody>
		<tr><td class="td-center" style="text-align:left;">系统日志：</td></tr>
		[#list objectlist as log]
			<tr><td style="text-align:left;" >
			    [${log[0]}] &nbsp;${log[1]}&nbsp;${log[3]}
				</td>
			</tr>
		[/#list]
	</tbody>
</table>