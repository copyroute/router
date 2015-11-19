<#import "/spring.ftl" as spring />

	<div class="navbar navbar-inverse categoryListPanel" role="navigation" id="border-stuff">
		<#--<div class="container-fluid ">-->
	        <!-- .btn-navbar is used as the toggle for collapsed navbar content 			-->

            <div class="navbar-header">
                <a class="btn btn-navbar" data-toggle="collapse" data-target="#myNavbar2">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
            </div>
			<!-- Everything you want hidden at 768px or less, place within here -->
            <#--<div class="collapse navbar-collapse" >-->
				<ul class="nav navbar-nav navbar-right" id="myNavbar2">
					<#list CategoryList as elementList>
						<li class="categoryItemListItem ui-corner-all">
							<a TARGET="_self" href="${ContextPath}/category/${elementList}">
								<div class="categoryItem " id="category-${elementList}">
									<h2>${elementList}</h2>
								</div>
							</a>
						</li>
					</#list>
				</ul>
            <#--</div>-->

		<#--</div>-->
	</div>

