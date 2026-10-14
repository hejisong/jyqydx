[#macro sort_q sort][#if (sort)??][#list sort.iterator() as order][#if order_index != 0]&[/#if]sort=${order.property},${order.direction}[/#list][/#if][/#macro]
	<div class="col-md-4" style="float:left;margin-bottom:20px;margin-top: 20px">
		<div class="dataTables_info" id="sample_2_info" role="status" aria-live="polite">
			总共${page.totalElements}条记录，
			[#if page.totalElements%page.size==0]
           		[#if page.totalElements ==0 ]
            		${pageable.offset/page.size+1}/${(page.totalElements/page.size)?int+1}页
            		[#assign pages = (page.totalElements/page.size)?int +1]
            	[#else]
            		${pageable.offset/page.size+1}/${(page.totalElements/page.size)?int}页
            		[#assign pages = (page.totalElements/page.size)?int]
            	[/#if]		
			[#else]
				${pageable.offset/page.size+1}/${(page.totalElements/page.size)?int +1}页
				[#assign pages = (page.totalElements/page.size)?int +1]			
			[/#if]
		</div>
	</div>
	<div class="col-md-8" style="float:right;margin-bottom:20px;">
		<div class="dataTables_paginate paging_simple_numbers" id="sample_2_paginate" style="float:right;margin:0;">
			<ul class="pagination">
				<li class="prev"><a href="${pagepath}page=0&[@sort_q pageable.sort /]">首页</a></li>
				[#if page.hasPrevious()]
					<li tabindex="0" class="paginate_button previous" id="sample_2_previous" aria-controls="sample_2"><a href="${pagepath}page=${page.number-1}&[@sort_q pageable.sort /]">上一页</a></li>
				[#else]
					<li tabindex="0" class="paginate_button previous disabled" id="sample_2_previous" aria-controls="sample_2"><a href="javascript:void(0)">上一页</a></li>
				[/#if]
				
				[#if pages >4 ]
					[#if pageable.offset/page.size>1 && pageable.offset/page.size<(pages -2)]
						[#list pageable.offset/page.size-2.. pageable.offset/page.size+2 as m]
							<li tabindex="0" class="paginate_button [#if pageable.offset/page.size =m]active[/#if]" aria-controls="sample_2">
								<a href="${pagepath}page=${m}&[@sort_q pageable.sort /]">${m+1}</a>
							</li>
						[/#list]
					[/#if]
					[#if pageable.offset/page.size<2 ]
						[#list 0.. 4 as m]
							<li tabindex="0" class="paginate_button [#if pageable.offset/page.size =m]active[/#if]" aria-controls="sample_2">
								<a href="${pagepath}page=${m}&[@sort_q pageable.sort /]">${m+1}</a>
							</li>
						[/#list]
					[/#if]
					[#if pageable.offset/page.size>(pages-3) ]
						[#list pages-5.. pages-1 as m]
							<li tabindex="0" class="paginate_button [#if pageable.offset/page.size =m]active[/#if]" aria-controls="sample_2">
								<a href="${pagepath}page=${m}&[@sort_q pageable.sort /]">${m+1}</a>
							</li>
						[/#list]
					[/#if]
				[/#if]
				[#if pages <5 ]
					[#list 0.. pages-1?int as m]
						<li tabindex="0" class="paginate_button [#if pageable.offset/page.size =m]active[/#if]" aria-controls="sample_2">
							<a href="${pagepath}page=${m}&[@sort_q pageable.sort /]">${m+1}</a>
						</li>
					[/#list]
				[/#if]
			
				[#if page.hasNext()]
					<li tabindex="0" class="paginate_button next" id="sample_2_next" aria-controls="sample_2"><a href="${pagepath}page=${page.number + 1}&[@sort_q pageable.sort /]">下一页</a></li>
                [#else]
                	<li tabindex="0" class="paginate_button next disabled" id="sample_2_next" aria-controls="sample_2"><a href="javascript:void(0)">下一页</a></li>
                [/#if]
                [#if page.totalElements%page.size=0]
                 	<li class="next"><a href="${pagepath}page=${(page.totalElements/page.size)?int-1}&[@sort_q pageable.sort /]">末页</a></li>
				[#else]
					 <li class="next"><a href="${pagepath}page=${(page.totalElements/page.size)?int}&[@sort_q pageable.sort /]">末页</a></li>
				[/#if]	
			</ul>
		</div>
	</div>				