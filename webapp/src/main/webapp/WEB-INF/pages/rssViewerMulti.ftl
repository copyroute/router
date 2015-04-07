<#import "/spring.ftl" as spring />
<#import "/templates/pageTemplate.ftl" as page />

<@page.pageContainer DomainName>

<!-- Header -->
<#include "/components/header.ftl" />	

<!-- Headline Spacer -->
<div id="bannerSpacer"></div>	

	
<div id="bodyDiv" class="row-fluid pull-center" >	
	<div class="container-fluid">
	
		<!-- Category Display -->
		<div class="span2 col-xs-2">
			<#include "/components/categoryDisplay.ftl" /> 	
		</div>
	
		<!-- News Display -->
		<div class="span10  col-xs-10">
			<#include "/layouts/newsDisplayMulti.ftl" /> 		
		</div>
	</div>
		
	<!-- Footer -->
	<#include "/components/footer.ftl" /> 			
	<hr/>

</div>

<!-- Hidden Page Variables -->
<#include "/components/hidden.ftl" /> 				

</@page.pageContainer>
	