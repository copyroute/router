<#import "/spring.ftl" as spring />
<#import "/components/googleAdSense.ftl" as google />

<#macro pageContainer DomainName>

<!DOCTYPE html>
<head>
	
 	<title> ${DomainName} : ${PageTitle}</title>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="keywords" content="${DomainName},News,Entertainment,Real,Time,Live,Global,News,Source">
	<meta name="description" content="${PageTitle}">
	<meta name="author" content="${DomainName}">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link id="page-style" rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/${Theme}/jquery-ui.css" />
 	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">	
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/backgrid-0.3.5.css"/>
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/backgrid-select-all.css"/>
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/backgrid-paginator.css"/>
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/backgrid-filter.css"/>
	<link rel="stylesheet" type="text/css" href="${ContextPath}/resources/css/parent.css" />
	<!-- Use Google CDN where Available -->
 	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.22/angular.min.js"></script>
	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<!--script type="text/javascript" src="${ContextPath}/resources/js/bootstrap.min.js"></script-->
    <!--script type="text/javascript" src="${ContextPath}/resources/jquery/jquery.cookie.js"></script-->

    <!-- AMQP : rabbitmq_web_stomp connector-->
    <script type="text/javascript" src="${ContextPath}/resources/js/sockjs-0.3.min.js"></script>
    <script type="text/javascript" src="${ContextPath}/resources/js/stomp.min.js"></script>
    <script type="text/javascript" src="${ContextPath}/resources/js/websocket-client.js"></script>

     <!-- D3js Charts and Graphs
    <script async type="text/javascript" src="${ContextPath}/resources/js/d3.v3.min.js"></script>
    <script async type="text/javascript" src="${ContextPath}/resources/js/charts.js"></script>
 	-->

	<!-- Init  -->
    <script type="text/javascript">
        function OnLoad(){ 
            // Engine Loop Trigger
			// window.setInterval(Update, 5000); 
        }
        function Update(){ 
            // Engine Loop
        }

    </script>
    <script type="text/javascript">
        var config = {contextPath: '${ContextPath}'};
    </script>

	<@google.printGoogleAdScript DomainName/>
	
	     	
    <script type="text/javascript">
    
		//Temp-Queue Messages (Client-Specific)===========================
		function receive(message, headers)
		{
			alert("received" + message);
			if( message.headers.subscription == "/temp-queue/rss"){
				dispatchRSS(message, headers);
			}
			if( message.headers.subscription == "/temp-queue/history"){
				dispatchRSS(message, headers);
			}
		}
		
		function dispatchBanner(message, headers)
		{
			// Received RssItem from Server
		    var rssItem = JSON && JSON.parse(message.body) || $.parseJSON(message.body);   
			var banner = rssItem.bannerMessage;

			$('#bannerDisplay .banner:last-of-type').remove();
			$('#bannerDisplay').prepend(banner);
			$('#myCarousel').carousel(0);
		
		}
		
		function dispatchDiv(rssItem, searchListSelector)
		{	
			var html = rssItem.newsDiv;
			
			// Remove Last Entry if larger than maxPageSize
			var searchListSize = $(searchListSelector + ' > div').size();
			if(searchListSize >= $("input#searchPageSizeBox").val() ){
				$(searchListSelector + ' div:last-child').remove();
			}
			
			// Add New Entry
			$(searchListSelector).prepend(html); 						
			var rssSelector = '#rssItem-'+rssItem.id;
			
			// Animate
			var hitColor = $(".rssItemTypeBreakingNews").css("color");
			$('#rssItem-'+rssItem.id).effect("highlight", {color: hitColor}, 120000);
			$('#category-'+rssItem.category).effect("highlight", {color: hitColor}, 120000);		    
		    
		}
		
		function dispatchRSS(message, headers)
		{ 
			// Received RssItem from Server
		    var rssItem = JSON && JSON.parse(message.body) || $.parseJSON(message.body);   
		
		    // Append to Channels
			dispatchDiv(rssItem, '#searchList-'+rssItem.category );
		}
	    	    
		var channels = {};
		function subscribeToTopic(category, handler){
			id = client.subscribe("/topic/"+category, handler );		
			channels[category] = id;
		}
	    
		// Subscribe ==============================================
		function subscribe(x) {
		
			sessionId = JSON.stringify(x.headers.session);
				
			// Persistent Queue
			// id = client.subscribe("/queue/" + sessionId, print_text, headers);
		
			// Round Robin
			// id = client.subscribe("/amq/queue/engility.outputs.blocks", PublishStorage_AMQP );
		
			// Broadcast
			// id = client.subscribe("/topic/publishRssItem", 		dispatchRSS );
			id = client.subscribe("/topic/publishBanner", 		dispatchBanner );
			//alert(id);
			
			<#--list RssItems as elementList> 
				//subscribeToTopic("${elementList.category}", dispatchBanner);
				subscribeToTopic("${elementList.category}", dispatchRSS);
			</#list-->
			
			// Get Chat History
		//	client.send(chatHistoryExchange, chatHistoryHeaders, "notUsed");
		}
	    function redirect(url){
			$(location).attr('href',url);
	    	//window.location = url;
	    }    
		$(function() 
		{
			// Search  ===================================
			$('#searchButton').click(function(){
				var url = "/search/all?" + 
				"start=0"  + 
				"&size=" + $("input#searchPageSizeBox").val() +
				"&term=" + $("input#searchBox").val() ;
				redirect(url);
			});
			// Search Auto-Click : enter key-press on searchbox
			$('input#searchBox').keypress(function(e) {
			    if(e.which == 13) {
					jQuery(this).blur();
					jQuery('#searchButton').focus().click();
			    }
			});
	
			// Search Box ===================================
			$("#searchBox").focus(function() {
				$this = $(this);
				$this.select();
				// Work around Chrome's little problem
				$this.mouseup(function() {
					// Prevent further mouseup intervention
					$this.unbind("mouseup");
					return false;
				});
			});
	
		    			
			// PageLoad  ===================================
			$(window).load(function() {
			});
			
		});
	</script>

	
</head>

<body onload="OnLoad()" class="" >
	<!-- Header -->
	<#nested>			
		
</body>

</html>
</#macro>
