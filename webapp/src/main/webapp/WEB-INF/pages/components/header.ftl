<#import "/spring.ftl" as spring />


<div class="container-fluid">
<div id="header" class="navbar navbar-default navbar-inverse navbar-fixed-top row-fluid" role="navigation">


		<div id="connectionDiv" class="span2 col-xs-2 ">
			<a href="/" id="frontpage">
				 <h1> CopyRoute</h1>
			</a>
		</div>
		<div id="searchDiv" class="span4 col-xs-4 text-right pull-right">

			<!-- Use Google's  "Did You Mean" feature to implement Auto-complete -->
			<!-- Ref : http://ahoj.io/how-to-steal-google-s-did-you-mean-feature -->
			<!-- EG : http://suggestqueries.google.com/complete/search?output=firefox&client=firefox&hl=en-US&q=green%20bay -->
			<input  id="searchBox" type="text" value="${term!''}" class="typeahead ui-corner-all form-control" placeholder="Search"/>	
			<a 	href="#" id="searchButton" class="ui-button ui-corner-all " ><span class="icon-search"></span></a>	
		</div>		
        

		
	
</div>
</div>
	