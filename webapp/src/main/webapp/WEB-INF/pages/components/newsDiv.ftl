<#import "/spring.ftl" as spring />

<!-- RSSItems -->
<div id="rssItem-${rssItem.id}" class="well rssItemType " >
	<div id="rssItemHeader" class="rssItemHeader">

		<table id="rssHeaderTable" width="100%">
		<tr valign="top">
		
			<td valign="top" align="left">
                <div>
                    <b>
                        <a TARGET="_self" href="/company/${rssItem.company!''}">
                            ${rssItem.company!''}
                        </a>
                    </b>
                </div>
                <div>
                    <a TARGET="_self" href="/company/${rssItem.company!''}/${rssItem.channel!''}">
                    ${rssItem.channel!''}
                    </a>
                </div>
            </td>
            <td valign="top" align="right">
                <div class="rssItemDate">
                ${rssItem.date[0..9]!''}
                    <br/>
                ${rssItem.date[12..18]!''}
                </div>
            </td>
		</tr>
		<tr valign="top" colspan="2">
		</tr>

        </table>

	</div>
	<hr/>
	<div id="rssItemTitle" class="rssItemTitle">
		<h3>
            <a TARGET="_blank" href="${rssItem.uri!''}">
                <#--escape x as x?html>
                </#escape-->
                    ${rssItem.title!''}
            </a>
		</h3>
	</div>
	<div id="rssItemDescription" class="rssItemDescription">
		${rssItem.description!''}
	</div>
</div>
