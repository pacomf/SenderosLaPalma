var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel');
    senderoModel = require('../models/senderoModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addSendero = function(name, regular_name, version, length, type, geometry_type, likes, geoStartLatitude, geoStartLongitude, geoEndLatitude, geoEndLongitude, coordinatesStr, water_pointsStr){
	var newSendero = new senderoModel({
		name: name,
		regular_name: regular_name,
		version: version,
		length: parseFloat(length),
		type: type,
		geometry_type: geometry_type,
		likes: parseInt(likes),
		geoStart: utils.toGeo(geoStartLatitude, geoStartLongitude),
		geoEnd: utils.toGeo(geoEndLatitude, geoEndLongitude),
		coordinates: utils.Array2DToArrayGeoLoc(coordinatesStr),
		water_points: utils.Array2DToArrayGeoLoc(water_pointsStr)
	});
	newSendero.save();
	console.log("Added Sendero "+newSendero._id);
}

exports.getGeoSenderos = function (req, res) {
	senderoModel.find({geoStart: {$near: [parseFloat(req.params.longitude), parseFloat(req.params.latitude)], $maxDistance : req.params.maxDistance/111000.12}}).limit(req.params.amount).exec(function (err, senderos) {
		if (!err){
			res.send(senderos);
		} else {
			res.send(resErr);
		}
	});
};

exports.getAllSenderos = function (req, res) {
	senderoModel.find(function (err, senderos) {
		if (!err){
			res.send(senderos);
		} else {
			res.send(resErr);
		}
	});
};
