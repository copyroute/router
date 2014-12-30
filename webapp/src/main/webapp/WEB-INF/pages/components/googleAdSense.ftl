<#import "/spring.ftl" as spring />

<#macro printGoogleAd>

		<div id="googleAd">
			<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- penguins-rising -->
			<ins class="adsbygoogle"
			     style="display:block"
			     data-ad-client="ca-pub-2214001684108768"
			<#if DomainName == 'copyroute.com'>
			     data-ad-slot="5024757293"
			<#else>
			     data-ad-slot="9375525290"
			</#if> 
			     data-ad-format="auto"></ins>
			<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
	 
</#macro>

<#macro printGoogleAdScript DomainName>

		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');	

	<#if DomainName == 'penguins-rising.com'>
		  ga('create', 'UA-53573800-1', 'auto');
	<#elseif DomainName == 'copyroute.com'>
		  ga('create', 'UA-54280722-1', 'auto');
	<#elseif DomainName == 'theysed.com'>
		  ga('create', 'UA-54478202-1', 'auto');
	<#else>
	</#if>  
		  ga('require', 'linkid', 'linkid.js');
		  ga('send', 'pageview');
		</script>
	
</#macro>

