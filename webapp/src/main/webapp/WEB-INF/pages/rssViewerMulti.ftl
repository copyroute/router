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
		<div class="span1 col-xs-1">
			<#include "/components/categoryDisplay.ftl" /> 	
		</div>
	
		<!-- News Display -->
		<div class="span11  col-xs-11">
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
	