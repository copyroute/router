<#import "/spring.ftl" as spring />
<#import "/templates/adminTemplate.ftl" as page />

<@page.pageContainer DomainName>
	
	<!-- Header -->
	<#include "/components/header.ftl" />	
	

	<!-- Headline Spacer -->
	<div id="bannerSpacer"></div>

	<#--list Customers as customer>
		${customer.name}
	</#list-->
	<div class="row-fluid">
	
		<div class="span10">
			<div id="example-2-result" class="backgrid-container"></div>
			<button id="customer-delete-button">Delete Selected</button>
		</div>
			
	<script src="${ContextPath}/resources/js/jquery-1.11.0.js"></script>
	<script src="${ContextPath}/resources/js/underscore-1.6.0.js"></script>
	<script src="${ContextPath}/resources/js/backbone-1.1.2.js"></script>
	<script src="${ContextPath}/resources/js/backbone-pageable.js"></script>
	<script src="${ContextPath}/resources/js/backgrid-0.3.5.js"></script>
	<script src="${ContextPath}/resources/js/backgrid-select-all.js"></script>
	<script src="${ContextPath}/resources/js/backgrid-paginator.js"></script>
	<script src="${ContextPath}/resources/js/backgrid-filter.js"></script>
	
	<script src="${ContextPath}/resources/js/equifax-grid-customer.js"></script>
	
	<script>
	</script>
	</div>
		
	
	<!-- Footer -->
	<#include "/components/footer.ftl" /> 			
	<hr/>

</@page.pageContainer>
	