// ============================================
// PlayList View
// ============================================

views.PlayList = Backbone.View.extend({

    tagName: 'li',

    initialize: function(options) {
        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');

        // If the model changes we need to re-render
        this.model.bind('change', this.render);
    },

    render: function() {
        console.log("playlist render : " + this.model.get('title') );

        // Clear
        jQuery(this.el).empty();
        jQuery(this.el).append(jQuery(
            '<a href="#/playlist/'+  this.model.get('_id') +'"><div class="">'
            + this.model.get('title')
            //+ this.model.get('_keywords')
            + '</div>'
            + '</a>'

        ));
        // Write the table columns
        //jQuery(this.el).append(jQuery('<div>' + this.model.get('title') + '</div>'));
        //jQuery(this.el).append(jQuery('<p>' + this.model.get('description') + '</p>'));
        return this;
    }
});


views.PlayListView = Backbone.View.extend({

    // The collection will be kept here
    collection: null,
    el: '#playListItems',

    initialize: function(options){
        this.collection = options.collection;

        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');

        // Bind collection changes to re-rendering
        this.collection.bind('reset', this.render);
        this.collection.bind('add', this.render);
        this.collection.bind('remove', this.render);
    },
    template: _.template("<h3>Hello <%= who %></h3><% _.each(collection, function(model) { %><%= model %><% }); %>"),

    // $el - it's a cached jQuery object (el), in which you can use jQuery functions to push content. Like the Hello World in this case.
    render: function(){


        //alert ("Collection: " + this.collection);
        var element = jQuery(this.el);
        // Clear potential old entries first
        element.empty();

        // Go through the collection items
        this.collection.forEach(function(item) {
            //alert("here: " + item);

            var itemView = new views.PlayList({
                model: item
            });
            console.log("playlistview" + item);

            // Render the View
            element.append(itemView.render().el);
        });

        //this.$el.html(this.template({
        //	who: "Fred",
        //  collection: this.collection
        //}));

        return this;
    }
});

