// Views are responsible for rendering stuff on the screen (well,into the DOM).
// Typically views are instantiated for a model or a collection,
// and they watch for change events in those in order to automatically
// update the data shown on the screen.
var views = {};
 
//  ========================================
//  View
	// el - stands for element. Every view has a element associate in with HTML content will be rendered.
	//_.js templates have the following syntax : 	'_.template(templateString, [data], [settings])'
	// In the templateString you use the place holder <%= %> and <%- %> to dynamically insert data. 
	// The later allows for HTML escape while the first one doesn’t. 
	// Moreover, you can use <% %> to run any javascript code.
//  ========================================

