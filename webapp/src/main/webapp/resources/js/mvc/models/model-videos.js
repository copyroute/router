//=======================================================================
// RSSItem
//=======================================================================

var videoHost = "https://api.zype.com/videos?api_key=CcD2Cjbq3ZcVIq2bl8wazA";

models.VideoItem = Backbone.Model.extend({
    defaults : {
        _id: "",
        active: true,
        categories: [],
        country: "",
        created_at: "2014-11-30T12:03:06.783-05:00",
        description: "Loading...",
        duration: 91,
        episode: null,
        featured: false,
        foreign_id: null,
        keywords: [],
        segments: [],
        mature_content: false,
        published_at: "2014-11-30T12:01:32.000-05:00",
        rating: 0,
        related_playlist_ids: [],
        request_count: 14,
        season: null,
        site_id: "5463c68e69702d24db490000",
        status: "created",
        subscription_required: false,
        title: "Loading...",
        updated_at: "2014-12-17T23:00:03.649-05:00",
        video_zobjects: [],
        zobject_ids: [],
        thumbnails: []
    },
    initialize: function() {

    },
    // Example of how to do a validation in a model
    validate: function(attributes) {
        if (typeof attributes.title !== 'string') {
            // Return a failed validation
            return 'Title is mandatory';
        }
        if (typeof attributes.description !== 'string') {
            // Return a failed validation
            return 'Description is mandatory';
        }
        // All validations passed, don't return anything
    }

});

models.VideoItemList = Backbone.PageableCollection.extend({
    model: models.VideoItem,
    url: videoHost,
    initialize: function(options) {
        this.url= options.host;
    },
    parse: function (data) {
        return data.response;
    },
    state: {pageSize: 30},
    mode: "client" // page entirely on the client side
});

