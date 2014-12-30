

// Vars ==============================================
var user = 'guest'; 
var pass = 'guest';
var sessionId = "";

//Chat ============================================================
//=================================================================
function InitChatBox(){
	$('#first form').submit(submit);	// Form Submit Function
	$('#first input').focus(boxOnFocus);	// Text Box Focus Function
	$('#first input').focus();
	$('#first input').val("");
}

//Submit Box
function submit() {
	var inp  = $('#first input');
	client.send( chatQueue, {}, inp.val());
	inp.val('');
	return false;
};
function print_text (message, headers) {
	var div  = $('#first div');
	headers = (headers === undefined) ? '' : JSON.stringify(headers);
    div.append($("<code>").text(message.body + ' ' + headers));
    div.scrollTop(div.scrollTop() + 10000);	
};
function debug(message, headers) {
	var div  = $('#second div');
	headers = (headers === undefined) ? '' : JSON.stringify(headers);
    div.append($("<code>").text(message + ' ' + headers));
    div.scrollTop(div.scrollTop() + 10000);	
};
var boxOnFocus = function() {
	var has_had_focus = false;
	if (!has_had_focus) {
		has_had_focus = true;
		$(this).val("");
	}
};



//=====================================================================================
//Destinations
//https://www.rabbitmq.com/stomp.html
//http://jmesnil.net/stomp-websocket/doc/
//
//The STOMP specification does not prescribe what kinds of destinations a broker must support, 
//instead the value of the destination header in SEND and MESSAGE frames is broker-specific. 
//The RabbitMQ STOMP adapter supports a number of different destination types:
//
//	/exchange -- 			SEND to arbitrary routing keys and SUBSCRIBE to arbitrary binding patterns;
//	/queue -- 				SEND and SUBSCRIBE to queues managed by the STOMP gateway;
//	/amq/queue -- 			SEND and SUBSCRIBE to queues created outside the STOMP gateway;
//	/topic -- 				SEND and SUBSCRIBE to transient and durable topics;
//	/temp-queue/ -- 		create temporary queues (in reply-to headers only).
//=====================================================================================
//How to compose apps using WebSockets
//http://www.rabbitmq.com/blog/2012/02/23/how-to-compose-apps-using-websockets/
//https://github.com/sockjs/websocket-multiplex/blob/master/multiplex_client.js
//
//Assuming you can have only a single connection to a given host, and multiple modules wanting to 
//send and receive data?  You need multiplexing: combining data from multiple sources into a single connection. 
//=====================================================================================


//Connection State ==============================================
function _connectionEstablished(){$('#connectionStatus').html('<div>Connected</div>');}
function _connectionBroken(){
	$('#connectionStatus').html('<div>Connection Broken</div>');
	var hitColor = $(".rssItemTypeBreakingNews").css("color");	
	$('#connectionStatus').effect("highlight", {color: hitColor}, 120000);
}
function _connectionClosed(){$('#connectionStatus').html('<div>Connection Closed</div>');}

//Stomp.js boilerplate
//var ws = new SockJS('http://' + window.location.hostname + ':15674/stomp');
var ws = new SockJS('http://copyroute.com:15674/stomp');
var client = Stomp.over(ws);

function on_connect(x) {
	subscribe(x);
    _connectionEstablished();
};
var on_error = function(error){
	_connectionBroken();
	console.log(error.headers.message);
	//alert("Error: " + error);
};
var on_disconnect = function(){
	id = console.log("disconnected");
	_connectionClosed();	
};
var on_debug = function(message, headers){
	debug(message, headers);
};
var on_receive = function(message, headers){
	receive(message, headers);
};

//SockJS does not support heart-beat: disable heart-beats
client.heartbeat.outgoing = 0;
client.heartbeat.incoming = 0;

// Debug & Disconnect 
client.onreceive = on_receive;
client.debug = on_debug;
client.disconnect = on_disconnect;

// Connect ...
client.connect(user, pass, on_connect, on_error, '/');



//var chatHeaders = { 'content-type':'text/plain'};
//var chatQueue = "/topic/chat";
//var chatHistoryExchange = '/exchange/penguins.exchange/history';
//var chatHistoryHeaders = {
//		'content-type':'text/plain', 
//		'reply-to':'/temp-queue/history'
//		};
//function GetHistory_AMQP(){
//	client.send(chatHistoryExchange, chatHistoryHeaders, "notUsed");
//}

////CSS =========================================================
//function changeCSS(){
//	$("#page-style").attr("href", "./css/" + $('#styleSheetSelect').find('option:selected').text() + "/jquery-ui.css");
//}


////Search  =========================================================
//function searchDataSource(inFeedUri)
//{
//	searchNews( "", "", "", inFeedUri, "0", $("input#searchPageSizeBox").val()  ); 
//}
//
//function searchNews(in_title, in_description, in_tag, in_feed, in_pageNumber, in_pageSize)
//{
//	var op = "search";
//
////	'content-type':'application/json',
////	'content-type':'text/plain',
//	var searchHeaders = {
//			'reply-to':'/temp-queue/rss',
//			'priority':'0', 
//			'persistent':'true', 
//			'content-encoding':'UTF-8', 
//			'content-type':'text/plain'
//		};
//
//	var searchQuery =JSON.stringify(
//		{ 
//			title:in_title, 
//			description:in_description, 
//			tag:in_tag, 
//			feed:in_feed, 
//			operation:op,
//			pageNumber:in_pageNumber,
//			pageSize:in_pageSize
//		});
//
//	// Send Message : 
//	// ACTION : Multi-faceted search
//	// This may be superceeded by Alfresco/Solr search
////	alert(searchQuery);
////	client.send('/exchange/penguins.exchange/rssSearch', searchHeaders, in_title);
////	client.send('/exchange/penguins.exchange/rssSearch', {}, searchQuery);
////	client.send('/exchange/penguins.exchange/rssSearch', searchHeaders, JSON.stringify("testing"));
//	client.send('/exchange/penguins.exchange/rssSearch', searchHeaders, '');
//}



