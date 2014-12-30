<#import "/spring.ftl" as spring />

<div id="myCarousel" class="carousel slide">
    <!-- Carousel nav 
	    <li data-target="#myCarousel" data-slide="cycle"></li>
	    <li data-target="#myCarousel" data-slide-to="1"></li>
	    <li data-target="#myCarousel" data-slide-to="2"></li>
	    -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide="cycle" class="active"></li>
    </ol>

    <!-- Carousel items 
    	    <div class="item">Item 2</div>
    
    -->
    <div id="bannerDisplay" class="carousel-inner">
    	<#list rssBannerItems as rssItem> 
			<#include "/components/banner.ftl" />	
		</#list>
				
    </div>
    <!-- Carousel nav -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
    
