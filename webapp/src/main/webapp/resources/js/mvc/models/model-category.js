models.CategoryItem = Backbone.Model.extend({
    defaults : {
        _id: "",
        _keywords:[],
        created_at: "2015-03-09T19:29:27.529-04:00",
        deleted_at: null,
        site_id: "54d7884169702d070b0d0200",
        title: "Getting Doug With High",
        updated_at: "2015-03-09T19:29:27.529-04:00",
        values: []
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

models.CategoryItems = Backbone.PageableCollection.extend({
    model: models.CategoryItem,
    url: '',
    initialize: function(options) {
        this.url= options.host;
    },
    parse: function (data) {
        return data.response;
    },
    state: {pageSize: 100},
    mode: "client" // page entirely on the client side
});

