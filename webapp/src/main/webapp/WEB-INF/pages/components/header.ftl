<#import "/spring.ftl" as spring />


<div class="container-fluid">
<div id="header" class="navbar navbar-default navbar-inverse navbar-fixed-top row-fluid" role="navigation">
	
		<div id="logoDiv" class="navbar-brand span1 col-xs-1">
			<a href="/" id="frontpage">
				<img id="logo" src="/resources/images/copyroute-logo.png" alt="CopyRoute Logo">
			</a>
		</div>

		<div id="connectionDiv" class="span1 col-xs-1 ">
			<a href="/" id="frontpage">
				 <h1> CopyRoute</h1>
			</a>
		</div>
		<div id="connectionDiv" class="span1 col-xs-1 text-right pull-right">
			<div id="connectionStatus" class="connectionStatus "></div>
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
	