<#import "/spring.ftl" as spring />
<#import "./pageTemplate.ftl" as page />

<@page.pageContainer DomainName>
	
	<!-- Header -->
	<#include "/components/header.ftl" />	
	

	<!-- Category Display -->
	<#include "/components/categoryDisplay.ftl" /> 	

	<!-- Headline Spacer -->
	<div id="bannerSpacer"></div>

		
	<!-- News Display -->
	<#include "/components/newsDisplay.ftl" /> 		
	<hr/>
	
	<!-- Footer -->
	<#include "/components/footer.ftl" /> 			
	<hr/>

</@page.pageContainer>
	