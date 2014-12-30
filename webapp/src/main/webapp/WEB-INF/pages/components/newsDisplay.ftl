<#import "/spring.ftl" as spring />
<#import "./googleAdSense.ftl" as google />

    <div class="row-fluid">
		<#list RssItems as elementList> 
			<#if RssItems?size <= 1 >
		        <div class="span12">
			<#else>
		        <div class="span4">
			</#if>
		
			<div id="resizer-${elementList.category}" class="categoryColumn ">
				<div id="accordion-${elementList.category}">
				
					<div id="show-hide-${elementList.category}" class="toggle" onclick="toggleColumns('${elementList.category}, false');" >X</div>
					<a TARGET="_self" href="${ContextPath}/${SearchType}/${elementList.category}"> 
						<div class="category" id="category-${elementList.category}">${elementList.category}</div>
					</a>
					
					<!-- Category Columnns -->
					<div id="searchList-${elementList.category}" class="searchListType " >
					<hr/>
						<#list elementList.items as rssItem> 
								
								<#if SearchType =="id" >
								<table>
									<tr><td width="50%">
										<#include "newsDiv.ftl">
									</td>
									<td width="50%">
										<@google.printGoogleAd/>
									</td></tr>
								</table>
								<#else>
									<#include "newsDiv.ftl">
								</#if>
						</#list>
					</div>
					
				</div>	
			</div>
		
		</div>
		</#list>
	</div>
