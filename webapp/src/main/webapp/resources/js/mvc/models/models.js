// Models are where actual data is kept. They can also be used
// for communicating between the server and the client through
// methods like save() and fetch().
var models = {};


//=======================================================================
//=======================================================================
//url : function() {
//	return host; 
//}	 
// Lets create function which will return the custom URL based on the method type
//getCustomUrl: function (method) {
//	switch (method) {
//	case 'read':
//		return host;						
//		//return '/list.json';
//		break;
//	case 'create':
//		return '/list/' + this.id;
//		break;
//	case 'update':
//		return '/list/' + this.id;
//		break;
//	case 'delete':
//		return '/list/' + this.id;
//		break;
//	}
//}, 
// Override the sync function to use our custom URLs
//sync: function (method, model, options) {
//	options || (options = {});
//	options.url = this.getCustomUrl(method.toLowerCase());
//	// Lets notify backbone to use our URLs and do follow default course
//	return Backbone.sync.apply(this, arguments);
//}


