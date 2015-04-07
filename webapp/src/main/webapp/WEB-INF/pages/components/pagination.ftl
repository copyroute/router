<#import "/spring.ftl" as spring />

<#if Category != 'Article'>
	<div class="pagination text-center pull-center">
	  <ul>
	
	
		<!-- Prev always "just works" -->
		<!-- Checks for negative values -->
		<#if Start lt 10>
		    <li><a href="${ContextPath}/${SearchType}/${Category}?start=0&size=${Size?number}&term=${term!''}">&lt</a></li>
		<#else>
		    <li><a href="${ContextPath}/${SearchType}/${Category}?start=${Start?number-Size?number}&size=${Size?number}&term=${term!''}">&lt</a></li>
		</#if>
		
		<!-- Spin out 10 pagination links -->
		<!-- This should probably be more dynamic -->
		<#assign x=3>
		<#list 0..x as i>
		    <li><a href="${ContextPath}/${SearchType}/${Category}?start=${Size?number*i}&size=${Size?number}&term=${term!''}">${i}</a></li>
		</#list>
		
		<!-- Next always "just works" -->
		    <li><a href="${ContextPath}/${SearchType}/${Category}?start=${Start?number+Size?number}&size=${Size?number}&term=${term!''}">&gt</a></li>
	
	
	  </ul>
	</div>
</#if>
