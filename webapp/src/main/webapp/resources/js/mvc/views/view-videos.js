// ============================================
// Video Views
// ============================================
views.VideoPlayerView = Backbone.View.extend({
    // The collection will be kept here
    collection: null,

    el: '#videoDiv',

    initialize: function(options){
        this.collection = options.collection;

        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');
    },

    render: function() {

        var autoplay = false;
        //var apiKey=this.model.get('_id');
        var apiKey='54fa278f69702d0874034c06';
        var apiKey=this.collection;
        var element = jQuery(this.el);
        element.empty();
        element.append(jQuery(
            '<div id="videoPlayer" class="center">'
            +'<div id=zype_' + apiKey + '></div>'
            +'<script src="https://player.zype.com/embed/'+ apiKey +'.js?'
            +'autoplay='+autoplay
            +'&api_key=hl9Z1tsrV84sC130dThVUw" type="text/javascript"></script>'
            +'</div>'
        ));
        return this;
    }
});

views.VideoItemView = Backbone.View.extend({

    tagName: 'div',

    initialize: function(options) {
        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');
        // If the model changes we need to re-render
        this.model.bind('change', this.render);
    },

    render: function() {

        var element = jQuery(this.el);
        element.empty();

        var itemUrl = "";
        if( this.model.get('thumbnails') )
            if( this.model.get('thumbnails')[1] )
                itemUrl = this.model.get('thumbnails')[1].url;

        // Clear
        element.append(jQuery(
            '<div class="rssItem col-lg-3 pull-center text-center">'
            +'<h4><a href="#/posts/'
            + this.model.get('_id')
            +'">'
            + this.model.get('title')
            + '</a></h4><hr/>'
            + '<img src="' + itemUrl + '"/>'
            + '<hr/>'
            + this.model.get('description')

            +'</div><!-- /.col-lg-4 -->'
        ));
        // Write the table columns
        //jQuery(this.el).append(jQuery('<div>' + this.model.get('title') + '</div>'));
        //jQuery(this.el).append(jQuery('<p>' + this.model.get('description') + '</p>'));
        return this;
    }
});


views.VideoItemListView = Backbone.View.extend({

    // The collection will be kept here
    collection: null,

    el: '#rssItems',

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
        var element = jQuery(this.el);
        element.empty();

        // Go through the collection items
        this.collection.forEach(function(item) {

            var itemView = new views.VideoItemView({
                model: item
            });

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
