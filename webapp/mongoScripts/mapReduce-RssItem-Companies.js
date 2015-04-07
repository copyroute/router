db.rssItem.mapReduce(
	function () {
		emit(this.company, 1);
	},
        function (key, values) {
    		var sum = 0;
		for (var i = 0; i < values.length; i++)
			sum += values[i];
		return sum;
	},
	{ out: "map_reduce_rssItem_companies" }
);
