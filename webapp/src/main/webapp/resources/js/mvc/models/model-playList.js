//=======================================================================
// Playlist
//=======================================================================

var playListHost = "https://api.zype.com/playlists?api_key=CcD2Cjbq3ZcVIq2bl8wazA";

models.PlayListItem = Backbone.Model.extend({
    defaults : {
        _id: "",
        _keywords:[],
        active: true,
        created_at: "2015-03-09T19:29:27.529-04:00",
        deleted_at: null,
        description: "description",
        priority: 0,
        related_video_ids: [ ],
        site_id: "54d7884169702d070b0d0200",
        title: "title",
        updated_at: "2015-03-09T19:29:27.529-04:00",
        playlist_item_count: 0
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

models.PlayListItems = Backbone.PageableCollection.extend({
    model: models.PlayListItem,
    url: playListHost,
    initialize: function(options) {
        this.url= options.host;
    },
    parse: function (data) {
        return data.response;
    },
    state: {pageSize: 100},
    mode: "client" // page entirely on the client side
});

