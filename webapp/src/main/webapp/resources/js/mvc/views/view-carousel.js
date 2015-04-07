// ============================================
// Carousel Views
// ============================================
views.CarouselItemView = Backbone.View.extend({

    tagName: 'div',

    active : false,

    initialize: function(options) {
        this.active = options.active;
        _.bindAll(this, 'render');
        this.model.bind('change', this.render);
    },

    render: function() {

        var autoplay = false;
        var apiKey=this.model.get('_id');
        //var apiKey='54fa278f69702d0874034c06';
        var itemUrl = "";
        if( this.model.get('thumbnails') )
            if( this.model.get('thumbnails')[2] )
                itemUrl = this.model.get('thumbnails')[2].url;
        //alert(itemUrl);

        if(this.active)
            this.el = '<div class="item active">' ;
        else
            this.el = '<div class="item">' ;
        this.el +=
            ' <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="First slide"> '
            +'<div class="container">'
            +'<div class="carousel-caption pull-center text-center">'
            +'<div id="" class="carouselDiv pull-center text-center">'
                +'<div class="carouselHeader pull-center text-center">'
                    +'<h2><a href="#/posts/'
                    + this.model.get('_id') +'">'
                    + this.model.get('title') + '</a></h2><hr/>'
                +'</div>'
                +'<div class="carouselDescription pull-center text-center">'
                +    '<img src="' + itemUrl + '"/><hr/>'
                //+     this.model.get('description')
                + '</div>'
            +'</div><!-- /.col-lg-4 -->'
            //+' <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>'

            +'    </div>'
            +'  </div>'
            +'</div>' ;

        return this;
    }
});

views.CarouselView = Backbone.View.extend({

    el: '#carousel-inner',

    initialize: function(options) {
        this.collection = options.collection;

        // Ensure our methods keep the `this` reference to the view itself
        _.bindAll(this, 'render');

        // Bind collection changes to re-rendering
        this.collection.bind('reset', this.render);
        this.collection.bind('add', this.render);
        this.collection.bind('remove', this.render);
    },

    render: function() {
        var element = jQuery(this.el);
        element.empty();

        // Go through the collection items
        var active = true;
        this.collection.forEach(function(item) {
            var carouselItemView = new views.CarouselItemView({
                model: item,
                active: active
            });

            // Render the View
            element.append(carouselItemView.render().el);
            active=false;
        });
        return this;
    }
});

