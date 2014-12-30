
var data = [4, 8, 15, 16, 23, 42];

    
function next() {
  return {
     time: ++t,
     value: v = ~~Math.max(10, Math.min(90, v + 10 * (Math.random() - .5)))
   };
}

function DynamicChart()
{	
	var t = 1297110663; 				// start time (seconds since epoch)
    var v = 70; 						// start value (subscribers)
    //var data = d3.range(33).map(next); 	// starting dataset
 
	//data.shift();
	//data.push(next());

	var margin = {top: 20, right: 30, bottom: 30, left: 40};
    var w = 360 - margin.left - margin.right;
    var h = 200 - margin.top - margin.bottom;	
	var x = d3.scale.linear().domain([0, 1]).range([0, w]);
	var y = d3.scale.linear().domain([0, 100]).rangeRound([0, h]);
	
	//	var chart = d3.select(".chart")
//	d3.select("body").append("svg")
//	.attr("class", "chart")
//	.attr("width", w * data.length - 1)
//	.attr("height", h);
	
	var chart = d3.select(".chart")
    	.attr("width", w + margin.left + margin.right)
    	.attr("height", h + margin.top + margin.bottom)
    	.append("g")
    	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	chart.selectAll("rect")
		.data(data)
		.enter().append("rect")
		.attr("x", function(d, i) 	{ return x(i) - .5; })
		.attr("y", function(d) 		{ return h - y(d.value) - .5; })
		.attr("width", w)
		.attr("height", function(d) { return y(d.value); });
	
	chart.append("line")
		.attr("x1", 0)
		.attr("x2", w * data.length)
		.attr("y1", h - .5)
		.attr("y2", h - .5)
		.style("stroke", "#000");
	
   // Update…
	chart.selectAll("rect")
		.data(data)
		.transition()
		.duration(1000)
		.attr("y", 		function(d) { return h - y(d.value) - .5; })
		.attr("height", function(d) { return y(d.value); });

//	redrawClick(chart);
//	redraw(chart);
	//alert("Data: " + data);
}

function redrawClick(chart)
{
}

function redraw(chart) {

	// Using time as a key, join the new data to the old nodes.
	var rect = chart.selectAll("rect")
	.data(data, function(d) { return d.time; });

	//alert("a");
	 
	// Enter…
	rect.enter().insert("rect", "line")
		.attr("x", function(d, i) { return x(i) - .5; })
		.attr("y", function(d) { return h - y(d.value) - .5; })
		.attr("width", w)
		.attr("height", function(d) { return y(d.value); });
	//alert("b");
	 
	// Update…
	rect.transition()
		.duration(1000)
		.attr("x", function(d, i) { return x(i) - .5; });
	//alert("c");
	 
	// Exit…
	rect.exit().remove();
	//alert("d");
 
}

function UpdateChart(){

		
	var margin = {top: 20, right: 30, bottom: 30, left: 40},
    width = 360 - margin.left - margin.right,
    height = 200 - margin.top - margin.bottom;

	var x = d3.scale.ordinal().rangeRoundBands([0, width], .1);
	var y = d3.scale.linear().range([height, 0]);
	var xAxis = d3.svg.axis().scale(x).orient("bottom");
	var yAxis = d3.svg.axis().scale(y).orient("left");

	var chart = d3.select(".chart")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  .append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	d3.tsv("data.tsv", type, function(error, data) {
	  x.domain(data.map(function(d) { return d.name; }));
	  y.domain([0, d3.max(data, function(d) { return d.value; })]);
	
	  chart.append("g")
	      .attr("class", "x axis")
	      .attr("transform", "translate(0," + height + ")")
	      .call(xAxis);
	
	  chart.append("g")
	      .attr("class", "y axis")
	      .call(yAxis);
	
	  chart.selectAll(".bar")
	      .data(data)
	    .enter().append("rect")
	      .attr("class", "bar")
	      .attr("x", function(d) { return x(d.name); })
	      .attr("y", function(d) { return y(d.value); })
	      .attr("height", function(d) { return height - y(d.value); })
	      .attr("width", x.rangeBand());
	});


}

function type(d) {
  d.value = +d.value; // coerce to number
  return d;
}