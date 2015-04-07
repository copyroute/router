db.zips.aggregate( [
   { $group : { _id : { state : "$state", city : "$city" }, pop : { $sum : "$pop" } } },
   { $group : { _id : "$_id.state", avgCityPop : { $avg : "$pop" } } },
   { $out : "avgCityPopulation" }
] );

db.zips.aggregate( 
	{ $group : 
		{ _id : "$state", totalPop : 
			{ $sum : "$pop" } 
		} 
	}, 
	{ $match : 
		{totalPop : 
			{ $gte : 10*1000*1000 } 
		} 
	},
        { $out : "largeCities" }
);
