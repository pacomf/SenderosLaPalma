var mongoose = require('mongoose'),
    geoLocModel = require('../../models/geoLocModel');

exports.toGeo = function(latitude, longitude){
    if (latitude === undefined || longitude === undefined) 
    	return [0,0];
    else
    	return [parseFloat(longitude), parseFloat(latitude)];
    
}


function toGeoLoc(latitude, longitude){
	var geoLoc = new geoLocModel({
		latitude: parseFloat(latitude),
		longitude: parseFloat(longitude)
	});
	return geoLoc;
}

exports.toGeoLoc = toGeoLoc;

// Be careful!, The coordinates of the geopoints path to the La Palma Open Data is [[longitude, latitude],...]
exports.stringToArrayGeoLoc = function (coordinatesStr){
	var ret = [];
	try{
		coordinatesStr = coordinatesStr.substr(1, coordinatesStr.length-1);
		coordinatesStr = coordinatesStr.replace(/ /g, '');
		var stringArray = coordinatesStr.split('],');
		var coordinateStr = "";
		var coordinate = [];
		for(var i=0; i<stringArray.length; i++){
			coordinateStr = stringArray[i];
			coordinateStr = coordinateStr.replace(/\[/g, '').replace(/\]/g, '');
			coordinate = coordinateStr.split(',');
	    	ret.push(toGeoLoc(coordinate[1], coordinate[0]));
		}
	} catch (err){
		console.log("Error parseando: "+coordinatesStr);
		return [];
	}
	return ret;
}

exports.Array2DToArrayGeoLoc = function (coordinatesArray2D){
	var ret = [];
	try{
		for(var i=0; i<coordinatesArray2D.length; i++){
			// Be careful!, The coordinates of the geopoints path to the La Palma Open Data is [[longitude, latitude],...]
	    	ret.push(toGeoLoc(coordinatesArray2D[i][1], coordinatesArray2D[i][0]));
		}
	} catch (err){
		console.log("Error parseando: "+coordinatesArray2D);
		return [];
	}
	return ret;
}
