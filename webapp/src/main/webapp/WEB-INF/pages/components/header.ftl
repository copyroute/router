<#import "/spring.ftl" as spring />

	<div id="header" class="row-fluid" >
	
		<div id="logoDiv" class="span1">
			<a href="/" id="frontpage">
				<img id="logo" src="/resources/images/copyroute-logo.png" alt="CopyRoute Logo">
			</a>
		</div>
		<div id="connectionDiv" class="span2">
			<a href="/" id="frontpage">
				 <h1> 
				 	CopyRoute.com
				 </h1>
				<div id="connectionStatus" class="connectionStatus "></div>
			</a>
		</div>

		<div id="spacerDiv" class="span5">
		</div>
		
		<div id="searchDiv" class="span2">
			<input  id="searchBox" type="textbox" value="${term!''}" class="ui-corner-all " />	
		</div>
		<div id="searchButtonDiv" class="span1">
			<a 	href="#" id="searchButton" class="ui-button ui-corner-all" >
			  		Search
			 </a>	
		</div>
	</div>	
	
	