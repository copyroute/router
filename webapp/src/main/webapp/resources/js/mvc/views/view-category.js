// ============================================
// Category View
// ============================================


views.Category = Backbone.View.extend({

    //tagName: 'ul',
    el: '#categoryItems',

    initialize: function(options) {
        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');

        // If the model changes we need to re-render
        this.model.bind('change', this.render);
    },

    render: function() {
        console.log("playlist render : " + this.model.get('title') );

        var element = jQuery(this.el);
        element.empty();

        var arrayLength = this.model.get('values').length;
        var html = "";
        for (var i = 0; i < arrayLength; i++) {
            html +=
                '<li >'
                + '<a href="#/category/'+ this.model.get('values')[i] + '">'
                + this.model.get('values')[i]
                + '</a>'
                + '</li>'
            ;
        }
        // Clear
        element.append(jQuery(html));
        return this;
    }
});


views.CategoryView = Backbone.View.extend({

    // The collection will be kept here
    collection: null,
    el: '#categoryItems',


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
        //element.append("<ul>");
        // Go through the collection items
        this.collection.forEach(function(item) {
            //alert("here: " + item);

            var itemView = new views.Category({
                model: item
            });
            console.log("CategoryView" + item);

            // Render the View
            element.append(itemView.render().el);
        });
        //element.append("</ul>");

        //this.$el.html(this.template({
        //	who: "Fred",
        //  collection: this.collection
        //}));

        return this;
    }
});
