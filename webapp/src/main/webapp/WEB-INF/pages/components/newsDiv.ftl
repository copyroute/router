<#import "/spring.ftl" as spring />

<!-- RSSItems -->
<div id="rssItem-${rssItem.id}" class="rssItemType" >			
	<div id="rssItemHeader" class="rssItemHeader">

		<table id="rssHeaderTable" width="100%">
		<tr valign="top">
		
			<td valign="top" align="left">
				<b>
					<a TARGET="_self" href="/company/${rssItem.company!''}">
						${rssItem.company!''}
					</a>
				</b>
			</td>
			<td valign="top" align="right">
			</td>
		</tr>
		<tr valign="top" colspan="2">
			<td valign="top" align="left" colspan='2'>
					<a TARGET="_self" href="/company/${rssItem.company!''}/${rssItem.channel!''}">
						${rssItem.channel!''}
					</a>																
			</td>
		</tr>
		</table>		

	</div>
	<hr/>
	<div id="rssItemTitle" class="rssItemTitle">
		<h3>
			<#if SearchType =="id" >
				<a TARGET="_self" href="${rssItem.uri!''}"> 
					<#--escape x as x?html>
					</#escape-->  
						${rssItem.title!''} 
				</a>
			<#else> 
				<a TARGET="_blank" href="/id/${rssItem.id!''}"> 
					<#--escape x as x?html>
					</#escape-->  
						${rssItem.title!''} 
				</a>
			</#if>  
		</h3>
	</div>
	<br/>
	<div id="rssItemDescription" class="rssItemDescription">
		${rssItem.description!''}
	</div>
	<hr/>
	<div class="rssItemDate">
		${rssItem.date} 							
	</div>
</div>
