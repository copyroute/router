News Aggregation and Routing
======
http://copyroute.com
===

Copyroute harvests hundereds of News Feeds (RSS) from the internet, and provides Web-Based search & analytics to the end User.

The project consists of two parts  :

- Webapp : provides an Web-Based interface to search/sort RSS news results stored in MongoDB. The webapp also supports a proxy feature, allowing anyone to consume search results in JSON, XML, and RSS formats.

- Services : Crawler component uses Java & Rome to harvest feeds and stores them into MongoDB.

Future plans include : 
- Integrating Backbone.js into Webapp
- Integrating LMAX Reactor into Services
- Clean up downstream data formats (json, xml, rss)
