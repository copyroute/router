
function makeDraggable(itemSelector)
{
    // let the gallery items be draggable
    $(itemSelector).draggable(
    {
      cancel: "a.ui-icon", // clicking an icon won't initiate dragging
      revert: "invalid", // when not dropped, the item will revert back to its initial position
      containment: "document",
      cursor: "move",
      helper: "clone",
      stop: function() {
      }
    });		    
}

function dropEvent(event, ui){

	// Save Cookie
	$.cookie.json = true;				
	$.cookie('penguins-rising', $(ui.draggable).prop('outerHTML'), { expires: 7, path: '/' });
    pinArticle(ui.draggable);		 	

	// Get Cookie
	var itemDiv = $.parseHTML($.cookie('penguins-rising'));
	$(itemDiv).draggable({
  		cancel: "a.ui-icon", // clicking an icon won't initiate dragging
		revert: "invalid", // when not dropped, the item will revert back to its initial position
		containment: "document",
	    helper: "clone",
	    cursor: "move"
    });
    //pinArticle($(itemDiv));
}	        
function pinArticle( $item ) {
	$item.appendTo("#footerDropBar");
	$item.css( "width", "150px");
	$item.css( "display", "table-cell");
		$item
          .find( ".rssItemHeader" )
            .css( "display", "none" )
          .end()
          .find( ".rssItemDescription" )
            .css( "display", "none" )
          .end();
	
}
