<#import "/spring.ftl" as spring />


<div class="banner item">
	<table width="100%" border="0" align="center" valign="top">
		<tr align="center" valign="top">					
			<td align="center" valign="top">
					<hr/>
					<div class="bannerTitle" >			
						<a TARGET="_self" href="/category/${rssItem.category}">${rssItem.category}</a>
						 | 
						<a TARGET="_self" href="/company/${rssItem.company}">${rssItem.company}</a>
						|
						<a TARGET="_self" href="/company/${rssItem.company}/${rssItem.channel}">${rssItem.channel}</a>
					</div>
					<div class="bannerHeader">
						<hr/>
						<a TARGET="_blank" href="/id/${rssItem.id}">
							<#escape x as x?html>
								${rssItem.title}
							</#escape>  
						</a>
						<hr/>
					</div>
					<div class="bannerDescription"> 
						${rssItem.description}
					</div> 
				</td>			
		</tr>
	</table>	
</div>

