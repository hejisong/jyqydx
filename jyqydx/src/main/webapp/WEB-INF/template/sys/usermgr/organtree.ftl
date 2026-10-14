<div class="col-md-12">

	<div class="portlet-body" style="background-color: #fff;">


		<div class="jstree" id="tree_1" role="tree"
			aria-activedescendant="j1_1">
			<ul class="jstree-container-ul">
				[#list organs as organ]
					[#if  organ.superdepartment == '-1']
				<li class="jstree-node" role="treeitem" aria-selected="false">
					<i class="jstree-icon" id="i${organ.id}"></i> <a class="jstree-anchor" id="${organ.id}"
					href="javascript:void(0)" onclick="mgrOrgUsers('${organ.id}')"> ${organ.orgname} </a>
					<ul class="jstree-children" role="group">
						[#list organs as organTwo] [#if  organTwo.superdepartment == organ.id]
						<li class="jstree-node" role="treeitem" aria-selected="false">
							<i class="jstree-icon" id="i${organTwo.id}"></i> <a class="jstree-anchor" id="${organTwo.id}"
							href="javascript:void(0)" onclick="mgrOrgUsers('${organTwo.id}')"> ${organTwo.orgname} </a>
							<ul class="jstree-children" role="group">
								[#list organs as organThree] 
								[#if  organThree.superdepartment == organTwo.id]
								<li class="jstree-node" role="treeitem" aria-selected="false">
									<i class="jstree-icon" id="1${organThree.id}"></i> <a class="jstree-anchor" id="${organThree.id}"
									href="javascript:void(0)" onclick="mgrOrgUsers('${organThree.id}')"> ${organThree.orgname} </a>
									<ul class="jstree-children" role="group">
										[#list organs as organFour] 
										[#if  organFour.superdepartment == organThree.id]
										<li class="jstree-node" role="treeitem" aria-selected="false">
											<i class="jstree-icon" id="i${organFour.id}"></i> <a class="jstree-anchor" id="${organFour.id}"
											href="javascript:void(0)"
											onclick="mgrOrgUsers('${organFour.id}')">
												${organFour.orgname} </a>
										</li> [/#if] [/#list]
									</ul>
								</li> [/#if] [/#list]
							</ul>
						</li> [/#if] [/#list]
					</ul>
				</li>

               [/#if]
			  [/#list]
			</ul>
		</div>
	</div>
</div>
 
	
	