//var cometd = $.cometd;
//
//
//	// FeedList  =========================================================
//	function changeCSS(){
//		$("#page-style").attr(
//				"href", 
//				"./css/" + $('#styleSheetSelect').find('option:selected').text() + "/jquery-ui.css"
//				);
//	}
//
//
//	// FeedList  =========================================================
//	function addFeed(feedTitle, feedLink){
//		cometd.publish('/service/add', { 
//			title: feedTitle,
//			link: feedLink
//		});
//	}
//	function dispatchPlayList(message){
//		var html = 	
//			'<a width="100%" href="#" id="" class="label-font" style="" ' + 
//			'onClick=\'searchDataSource("' + message.data.feedUri  + '"); return false; \' >' + 
//				'<div id="feedItemType" width="100%" class="ui-corner-all" >' + 
//					message.data.feedName + 
//				'</div>' + 
//			'</a><hr/>';
//		$('#feedListToolBar').append( html );
//	}
//
//	// Search  =========================================================
//	function searchDataSource(inFeedUri){
//		searchNews( "", "", "", inFeedUri, "0", $("input#searchPageSizeBox").val()  ); 
//	}
//	
//	function searchNews(titleSearch, descriptionSearch, tagSearch, feedSearch, pageNumberSearch, pageSizeSearch){
//
//		var op = "";
//		if( titleSearch == "" && descriptionSearch == "" && tagSearch == "" && feedSearch =="")
//			op = 'default';
//		else
//			op = 'search';
//		
//		cometd.publish('/service/rss', 
//				JSON.stringify(
//				{ 
//					title:titleSearch, 
//					description:descriptionSearch, 
//					tag:tagSearch, 
//					feed:feedSearch, 
//					operation:op,
//					pageNumber: pageNumberSearch,
//					pageSize: pageSizeSearch
//				}));
//	}
//
//	function dispatchRSS(message){ 
//
//		$('#banner').hide(1000);
//		$('#banner').show(1000);
//
//        var rssItem = JSON && JSON.parse(message.data) || $.parseJSON(message.data);    
//
//		// Remove Last Entry if larger than maxPageSize
//		var searchListSize = $('#searchList > div').size();
//		if(searchListSize >= $("input#searchPageSizeBox").val() ){
//			$('#searchList div:last-child').remove();
//		}
//
//		// Publish Data Item
//		var html =
//			'<div id="rssItem" ' +
//				'style="display:none;" class="rssItemType" ><hr/>' + 
//				'<b><a TARGET="_blank" href="' + rssItem.uri + '">' + rssItem.title +'</a></b><br/>' +
//				rssItem.description	+				
//			'</div>';
//
//		$('#searchList').prepend(html);            
//		$('#rssItem').show(2000);
//
//		// Publish Banner 
//		var banner =
//			'<div id="banner" style="display:none;" class="accordian" >' + 
//				'<b><a TARGET="_blank" href="' + rssItem.uri + '">' + rssItem.title +'</a></b><br/>' +
//			'</div>';
//
//		$('#feedScroller').html(banner);
//		$('#banner').show(2000);
//	}
//
//
//	// Chat  =========================================================
//	function chat(chatMessage){
//		cometd.publish('/service/chat', { 
//			content: chatMessage
//		});
//	}
//
//	// Dispatch Handler Function
//	function dispatchChat(message)
//	{ 
//		// var html = JSON && JSON.parse(message.data) || $.parseJSON(message.data);
//		var socialLinks = '<div class="label-font" style="background-color:rgba(150,50,150, 0.3); float:right; ">Item:</div>';
//		var chatListSize = $('#chatList > div').size();
//		var chatListSizeLabel = '<div class="label-font" style="background-color:rgba(150,50,150, 0.3); float:right; ">' + chatListSize + '</div>';
//
//		// Remove Last Entry if larger than maxPageSize
//		if(chatListSize >= $("input#chatPageSizeBox").val() ){
//			$('#chatList div:last-child').remove();
//		}
//
//		var html =
//			'<div id="chatItem" class="chatItemType" ><hr/>'	
//				+ chatListSizeLabel + socialLinks + 
//				'<b>' 	+ message.data.content 	+ 	'</b><br/>'	+
//			'</div>';
//
//		
//		$('#chatList').prepend(html);            
//		
//		var banner =
//			'<div id="banner" style="display:none;" class="accordian" >' + 
//				message.data.content +
//			'</div>';
//
//		$('#banner').hide(1000);
//		$('#feedScroller').html(banner);
//		$('#banner').show(1000);
//	}
//	
//	// Transaction =========================================================
//	function addTransaction(message){
//		cometd.publish('/service/transaction', { 
//			content: message
//		});
//	}
//	
//	// Dispatch Handler Function
//	function dispatchTransaction(message){
//		var html =
//			'<div id="transactionItem" class="transactionItem" ><hr/>'	
//				+ '<b>' + message.data.transaction + '</b><br/>' +
//			'</div>';
//		$('#transactionList').prepend(html);            		
//	}
//
//(function($)
//{
//    $(document).ready(function()
//    {
//        function _connectionEstablished(){$('#connectionStatus').append('<div>CometD Connection Established</div>');}
//        function _connectionBroken(){$('#connectionStatus').append('<div>CometD Connection Broken</div>');}
//        function _connectionClosed(){$('#connectionStatus').append('<div>CometD Connection Closed</div>');}
//
//        // Function that manages the connection status with the Bayeux server
//        var _connected = false;
//        function _metaConnect(message){
//            if (cometd.isDisconnected()){
//                _connected = false;
//                _connectionClosed();
//                return;
//            }
//            var wasConnected = _connected;
//            connected = message.successful === true;
//            if (!wasConnected && _connected){_connectionEstablished();}
//            else if (wasConnected && !_connected){_connectionBroken();}
//        }
//
//        // Function invoked when first contacting the server and when the server has lost the state of this client
//        function _metaHandshake(handshake){
//            if (handshake.successful === true){
//                cometd.batch(function()
//                {
//					//window.alert('Handshake:' + handshake);
//					//var auth = handshake.ext && handshake.ext.authentication;
//					//if (auth && auth.failed === true){
//						// Authentication failed, tell the user
//						//window.alert('Authentication failed!');
//					//}
//
//					// CometD Subscribers (Server-->Client) 
//                    cometd.subscribe('/chat', 			function(message) { dispatchChat( 			message );});	// 3
//                    cometd.subscribe('/transaction', 	function(message) { dispatchTransaction( 	message );});	// 
//
//					// Initial OnPageLoad FeedList (Client-->Server)
//                    cometd.subscribe('/playList', 		function(message) { dispatchPlayList( 		message );});	// 1
//					cometd.publish('/service/playList', { data:{ listName:"" } });
//
//					// Initial OnPageLoad Search (Client-->Server)
//                    cometd.subscribe('/rss', 			function(message) { dispatchRSS( 			message );});	// 0
//					cometd.publish('/service/rss', 
//						JSON.stringify(
//						{ 
//							title:"", 
//							description:"", 
//							tag:"", 
//							feed:"", 
//							operation:"default",
//							pageNumber: 1,
//							pageSize: 10
//						}));
//
//                });
//            }
//        }
//
//        // Disconnect when the page unloads
//        $(window).unload(function(){
//            cometd.disconnect(true);
//        });
//
//        var cometURL = location.protocol + "//" + location.host + config.contextPath + "/cometd";
//        cometd.configure({
//            url: cometURL,
//            logLevel: 'debug'
//        });
//
//        cometd.addListener('/meta/handshake', _metaHandshake);
//        cometd.addListener('/meta/connect', _metaConnect);
//        //cometd.handshake();
//
//		// Authentication failed, tell the user
//		// Register a listener to be notified of authentication success or failure
//		var username = 'foo';
//		var password = 'bar';
//		cometd.handshake({
//			ext: {
//				authentication: {
//				    user: username,
//				    credentials: password
//				}
//			}
//		});
//		// Authentication failed, tell the user
//		//window.alert('Authenticed- User:' + username +' Pass: '+ password);
//
//    });
//})(jQuery);
//
//
