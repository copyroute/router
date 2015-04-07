$(function() {

	var columns = [
	  {
	    name: "id", // The key of the model attribute
	    label: "ID", // The name to display in the header
	    editable: false, // By default every cell in a column is editable, but *ID* shouldn't be
	    cell: "string" 
	  }, 
	  {
	    name: "category",
	    label: "category",
	    cell: "string" 
	  }, 
	  {
	    name: "company",
	    label: "company",
	    cell: "string" 
	  }, 
	  {
	    name: "channel",
	    label: "channel",
	    cell: "string" 
	  }, 
	  {
	    name: "tags",
	    label: "tags",
	    cell: "string" 
	  }, 
	  {
	    name: "uri",
	    label: "uri",
	    cell: "string" 
	  }];
	
	var ClickableRow = Backgrid.Row.extend({
	  events: {
	    "click": "onClick"
	  },
	  onClick: function () {
	    Backbone.trigger("rowclicked", this.model);
	  }
	});

	Backbone.on("rowclicked", function (model) {
	  //alert("Select : " + model);
	});
	
	var Territory = Backbone.Model.extend( {
		initialize: function () {
		    Backbone.Model.prototype.initialize.apply(this, arguments);
		    this.on("change", function (model, options) {
		    if (options && options.save === false) return;
		      model.save();
		    });
		  },
	
		  idAttribute: "id",
    
		  // Lets create function which will return the custom URL based on the method type
		  getCustomUrl: function (method) {
			  switch (method) {
			  	case 'read':
	                return '/playlist';
	                break;
	            case 'create':
	                return '/playlist/' + this.id;
	                break;
	            case 'update':
	                return '/playlist/' + this.id;
	                break;
	            case 'delete':
	                return '/playlist/' + this.id;
	                break;
	        }
	    },
	    // Now lets override the sync function to use our custom URLs
	    sync: function (method, model, options) {
	        options || (options = {});
	        options.url = this.getCustomUrl(method.toLowerCase());
	        
	        // Lets notify backbone to use our URLs and do follow default course
	        return Backbone.sync.apply(this, arguments);
	    }
		}
	);
	
	var PageableTerritories = Backbone.PageableCollection.extend({
	  model: Territory,
	  url: "/playlist",
	  parse: function (data) {
   		return data.playList.dataSources;
	  },
	  state: {pageSize: 15},
	  mode: "client" // page entirely on the client side
	});
	var pageableTerritories = new PageableTerritories();

	// Set up a grid to use the pageable collection
	var pageableGrid = new Backgrid.Grid({
		row: ClickableRow,
		columns: [{
	    // enable the select-all extension
	    name: "",
	    cell: "select-row",
	    headerCell: "select-all"
	  }].concat(columns),
	  collection: pageableTerritories
	});
	// Render the grid
	var $example2 = $("#example-2-result");
	$example2.append(pageableGrid.render().el);

	$('#customer-delete-button').click(function () {
	  var selectedModels = pageableGrid.getSelectedModels();
	  _.each(selectedModels, function (model) {
	    model.destroy();
	  });
	});

	
	// Initialize the paginator
	var paginator = new Backgrid.Extension.Paginator({
	  collection: pageableTerritories
	});
	// Render the paginator
	$example2.after(paginator.render().el);

	// Initialize a client-side filter to filter on the client
	// mode pageable collection's cache.
	var filter = new Backgrid.Extension.ClientSideFilter({
	  collection: pageableTerritories,
	  fields: ['channel']
	});
	// Render the filter
	$example2.before(filter.render().el);

	// Add some space to the filter and move it to the right
	$(filter.el).css({float: "right", margin: "20px"});

	// Add 2nd Filter
	var filter2 = new Backgrid.Extension.ClientSideFilter({
		  collection: pageableTerritories,
		  fields: ['company']
		});
	$example2.before(filter2.render().el);
	$(filter2.el).css({float: "right", margin: "20px"});

	// Add 3rd Filter
	var filter3 = new Backgrid.Extension.ClientSideFilter({
		  collection: pageableTerritories,
		  fields: ['category']
		});
	$example2.before(filter3.render().el);
	$(filter3.el).css({float: "right", margin: "20px"});

	
	// Fetch some data
	pageableTerritories.fetch({reset: true});
});
