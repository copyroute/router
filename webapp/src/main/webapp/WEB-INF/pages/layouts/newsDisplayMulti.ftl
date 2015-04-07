<#import "/spring.ftl" as spring />
<#import "/components/googleAdSense.ftl" as google />

    <div class="row-fluid text-center pull-center">		
			<div id="resizer-${Category}" class="categoryColumn span12">
				<div id="accordion-${Category}">

				    <div class="row-fluid well">
				    
				        <div class="span12">
							<a TARGET="_self" href="${ContextPath}/${SearchType}/${Category}"> 
								<div class="category" id="category-${Category}">
									${Category} : ${term!''}
								</div>
							</a>
						</div>	
					
					</div>
						
				    <div class="row-fluid well">
				        <div class="span12">
							<#include "/components/pagination.ftl" /> 	
						</div>	

				        <div class="span12">
							<!-- Category Columnns -->
							<div id="searchList-${Category}" class="searchListType " >
								<#list RssItems as rssItem> 
									<#include "/components/newsDiv.ftl">
								</#list>
							</div>
						</div>	

				        <div class="span12">
							<#include "/components/pagination.ftl" /> 	
						</div>	
					</div>
					
				    <div class="row-fluid well">
				        <div class="span12">
							<#include "/components/disqus.ftl" /> 	
						</div>	
					</div>
					
				</div>	
			</div>
	</div>
