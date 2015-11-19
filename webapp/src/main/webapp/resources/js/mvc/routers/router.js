// Router is responsible for driving the application. Usually
// this means populating the necessary data into models and
// collections, and then passing those to be displayed by
// appropriate views.

//var root="http://copyroute.com/category/";

var apiKey='api_key=CcD2Cjbq3ZcVIq2bl8wazA';
var paging='&page=0&per_page=10';

var videoRoot='https://api.zype.com/videos';
var playListRoot = 'https://api.zype.com/playlists';
var categoryRoot = 'https://api.zype.com/categories';


// Playlist ------------------------
function getPlayList(){
    var playListItems = new models.PlayListItems({ host: playListRoot + '?' + apiKey + paging  });
    playListItems.fetch();
    return playListItems;
}
function renderPlayList(playListItems) {
    var playListView = new views.PlayListView({ collection : playListItems});
    var playListElement = playListView.render().el;
    return playListElement;
}


// Category ------------------------
function getCategory(){
    var categoryItems = new models.CategoryItems({ host: categoryRoot + '?' + apiKey + paging });
    categoryItems.fetch();
    return categoryItems;
}
function renderCategory(categoryItems) {
    var categoryView = new views.CategoryView({ collection : categoryItems});
    var categoryElement = categoryView.render().el;
    return categoryElement;
}

// Carousel ------------------------
function renderCarousel(videoItemList) {
    var carouselView = new views.CarouselView({ collection : videoItemList });
    return carouselView.render().el;
}

// Video ------------------------
function renderVideo(videoId) {
    var videoPlayerView = new views.VideoPlayerView({ collection : videoId});
    return videoPlayerView.render().el;
}

// VideoItemList ------------------------
function getVideoItemList(videoUrl){
    var videoItemList = new models.VideoItemList({ host: videoUrl });
    videoItemList.fetch();
    return videoItemList;
}
function renderVideoItemList(videoItemList) {
    var videoItemListView = new views.VideoItemListView({ collection : videoItemList});
    //var videoItemListElement = videoItemListView.render().el;
    return videoItemListView.render().el;
}



// ------------------------
// Controllers
// Create Views & Render
// ------------------------

function displayMenu(){
    var playList = getPlayList();
    renderPlayList(playList);

    var categoryList = getCategory();
    renderCategory(categoryList);
}
function displayIndex(videoUrl) {

    displayMenu();

    var videoItemList = getVideoItemList(videoUrl);
    renderVideoItemList(videoItemList);
    renderCarousel(videoItemList);

    jQuery("#videoDiv").hide();
    jQuery("#carosel").show();
    console.log("Finished");
}

function displayPage(videoId) {

    displayMenu();
    renderVideo(videoId);

    jQuery("#carosel").hide();
    jQuery("#videoDiv").show();
    console.log("Finished");
}

// ------------------------
// Main : Initialize Router & Events
// ------------------------
var Router = Backbone.Router.extend({
  routes: {
        '': 'index',  // At first we display the index route
        'youtube': 'youtube',
        'posts/:id': 'getPost',
        'category/:id': 'getCategory',
        'playlist/:id': 'getPlayList'
  },

  index: function() {
      displayIndex(videoRoot + '?' + apiKey + paging);
  },
  youtube: function() {
      displayIndex(videoRoot + '?' + apiKey + paging);
  }
});

jQuery(document).ready(function() {

    // Instantiate the router
    var router = new Router();

    // Parameterized events
    router.on('route:getPost', function( id ){
        displayPage(id);
    });
    router.on('route:getCategory', function( id ){
        //alert(id);
        displayIndex(videoRoot + '?' + apiKey + paging + '&category[Genre]=' + id);
    });
    router.on('route:getPlayList', function( id ){
        displayIndex(playListRoot + '/' + id + '/videos' + '?' + apiKey + paging);
    });

    // Start routing
    Backbone.history.start({pushState: true});
    //Backbone.history.start();
});



// Example of adding a new person after rendering
// This will fire the 'add' event in the collection which causes the view to re-render
//    people.add([{
//          firstname: 'Zaphod',
//          lastname: 'Beeblebrox'
//    }]);

