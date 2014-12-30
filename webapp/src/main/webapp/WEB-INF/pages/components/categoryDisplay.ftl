<#import "/spring.ftl" as spring />



<div id="catagoryListPanel" >
<nav class="navbar navbar-inverse " role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
<div class="collapse navbar-collapse" >
	<ul id="navlist" class="nav navbar-nav">
        <li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
  				Categories <span class="caret"></span>
  			</a>
			<ul class="dropdown-menu scrollable-menu" role="menu">
				<#list CategoryList as elementList> 
					<li id="navItem">
					<a TARGET="_self" href="${ContextPath}/category/${elementList}"> 
						<span class="categoryList" id="category-${elementList}"><h2>${elementList}</h2></span>
					</a>
					</li>
				</#list>
		    </ul>
        </li>
	</ul>

  </div><!-- /.container-fluid -->
</nav>
</div>
