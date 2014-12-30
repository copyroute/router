<#import "/spring.ftl" as spring />
<#import "/components/googleAdSense.ftl" as google />

<div id="footer">
	<table width="100%" border="1" >
	<tr>
		<td align="center">
			<@google.printGoogleAd/>
			<@google.printGoogleAd/>				
		</td>
	</tr>	
</div>
		
<div id="hidden" style="display: none;">
	<a href="/sitemap.xml" id="frontpage"> Sitemap </a>
	<a href="#" id="toggleDescriptionsButton" class="" >Toggle Descriptions</a>	
	<input 	id="searchPageSizeBox" type="textbox" value="10" class=" ui-corner-all ui-button" />
</div>
		