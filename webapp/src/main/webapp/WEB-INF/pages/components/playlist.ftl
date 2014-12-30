<#import "/spring.ftl" as spring />
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%-- <%@ page isELIgnored="false"%> --%>
<script type="text/javascript">
	$(function() {
// 		// AddFeed  ===================================
// 		$('#addFeedButton').click(function(){
// 			addFeed( 
// 				$("input#addFeedTitle").val(), 
// 				$("input#addFeedLink").val() ); 
// 		});
		
// 		$(function(){
// 			  $('.dropdown-1').hide();
// 			  $('#menu').hover(function(){
// 			        $('.dropdown-1').slideDown('medium');
// 			  }, function(){
// 			        $('.dropdown-1').slideUp('medium');
// 			  });
// 			});


		$(window).load( function() {
			$('#playList').slideDown(333);			
		});
	});
</script>

<div id="playList" style="display:none; align:center" >

	<div class="portlet" align="center" >
		<div class="portlet-header" class=" ui-widget-header">PlayList</div>
		<div class="portlet-content"class=" ui-widget-content">
			<div id="feedListToolBar" class="portlet-content ui-corner-all ui-state-default" width="100%"></div>
		</div>
	</div>

</div>
