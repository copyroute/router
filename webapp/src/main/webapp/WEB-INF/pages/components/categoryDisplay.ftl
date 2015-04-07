<#import "/spring.ftl" as spring />

<div class="headerwrap">
	<div class="navbar navbar-inverse" role="navigation" id="border-stuff">
		<div class="container categoryListPanel">
	        <!-- .btn-navbar is used as the toggle for collapsed navbar content 			-->
	        
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<!-- Everything you want hidden at 768px or less, place within here -->
			<div class="" id="center-nav">
				<ul class="nav nav-collapse" >
					<#list CategoryList as elementList> 
						<li>
							<a TARGET="_self" href="${ContextPath}/category/${elementList}"> 
								<div class="categoryItem ui-corner-all" id="category-${elementList}">
									<h2>${elementList}</h2>
								</div>
							</a>
						</li>
					</#list>
				</ul>
			</div>

		</div>								
	</div>			
</div>
