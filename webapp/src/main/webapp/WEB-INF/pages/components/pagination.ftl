<#import "/spring.ftl" as spring />

<#if Category != 'Article'>
	<div class="pagination text-center pull-center cr-pagination">
	  <ul class="cr-pagination">
	
	
		<!-- Prev always "just works" -->
		<!-- Checks for negative values -->
		<#if Start lt 10>
		    <li class="cr-pagination"><a href="${ContextPath}/${SearchType}/${Category}?start=0&size=${Size?number}&term=${term!''}">&lt</a></li>
		<#else>
		    <li class="cr-pagination"><a href="${ContextPath}/${SearchType}/${Category}?start=${Start?number-1}&size=${Size?number}&term=${term!''}">&lt</a></li>
		</#if>
		
		<!-- Spin out pagination links -->
		<#assign x=10>
		<#list 0..x as i>
		    <li class="cr-pagination"><a href="${ContextPath}/${SearchType}/${Category}?start=${i}&size=${Size?number}&term=${term!''}">${i}</a></li>
		</#list>
		
		<!-- Next always "just works" -->
		    <li class="cr-pagination"><a href="${ContextPath}/${SearchType}/${Category}?start=${Start?number+1}&size=${Size?number}&term=${term!''}">&gt</a></li>
	
	  </ul>
	</div>
</#if>
