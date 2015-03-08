var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel');
    senderoModel = require('../models/senderoModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addSendero = function(name, regular_name, version, length, type, difficulty, geometry_type, likes, geoStartLatitude, geoStartLongitude, geoEndLatitude, geoEndLongitude, coordinates, water_points){
	var newSendero = new senderoModel({
		name: name,
		regular_name: regular_name,
		version: version,
		length: parseFloat(length),
		type: type,
		difficulty: difficulty,
		geometry_type: geometry_type,
		likes: parseInt(likes),
		geoStart: utils.toGeo(geoStartLatitude, geoStartLongitude),
		geoEnd: utils.toGeo(geoEndLatitude, geoEndLongitude),
		coordinates: utils.Array2DToArrayGeoLoc(coordinates),
		water_points: utils.Array2DToArrayGeoLoc(water_points)
	});
	newSendero.save();
	console.log("Added Sendero "+newSendero._id);
}

exports.deleteAllSenderos = function(){
	senderoModel.remove().exec();
	console.log("All Senderos Remove!");
}

function checkEmpty(object){
	if ((object === null) || (object === undefined) || (object === '')){
		return true;
	}
	return false;
}

function getQuery(queryReq){
	var query = {};

	if (!checkEmpty(queryReq.search) && (queryReq.search === "byGeo")){
		var latitude = queryReq.latitude;
		var longitude = queryReq.longitude;
		var maxDistance = queryReq.maxDistance;
		if (checkEmpty(latitude) || checkEmpty(longitude)){
			return resErr;
		}
		if (checkEmpty(maxDistance)){
			maxDistance = 500000;
		}
		query.geoStart = {$near: [parseFloat(longitude), parseFloat(latitude)], $maxDistance : parseFloat(maxDistance)/111000.12};
	}

	if (!checkEmpty(queryReq.minLength) && !checkEmpty(queryReq.maxLength)){
		query.length = {$gte: parseFloat(queryReq.minLength), $lte: parseFloat(queryReq.maxLength)};
	} else if (!checkEmpty(queryReq.minLength)){
		query.length = {$gte: parseFloat(queryReq.minLength)};
	} else if (!checkEmpty(queryReq.maxLength)){
		query.length = {$lte: parseFloat(queryReq.maxLength)};
	}

	if (!checkEmpty(queryReq.difficulty)){
		query.difficulty = queryReq.difficulty;
	}

	return query;
}

function getNoSelect(queryReq){
	var noSelect ={};
	noSelect.__v=0;
	if ((!checkEmpty(queryReq.noCoordinates)) && (queryReq.noCoordinates)){
		noSelect.coordinates = 0;
	}

	if ((!checkEmpty(queryReq.noWaterPoints)) && (queryReq.noWaterPoints)){
		noSelect.water_points = 0;
	}
	return noSelect;
}

function getAllSenderos(res) {
	senderoModel.find(function (err, senderos) {
		if (!err){
			res.send(senderos);
		} else {
			res.send(resErr);
		}
	});
};

function getSenderosByQuery (req, res){
	var amount = parseInt(req.query.amount) ? !checkEmpty(req.query.amount): 20;
	senderoModel.find(getQuery(req.query), getNoSelect(req.query)).limit(amount).exec(function (err, senderos) {
		if (!err){
			res.send(senderos);
		} else {
			res.send(resErr);
		}
	});
}

exports.getSenderos = function (req, res) {
	if (checkEmpty(req.query)){
		getAllSenderos(res);
	} else {
		getSenderosByQuery(req, res);
	}
};


exports.getSendero = function (req, res) {
	senderoModel.findById(res.params.idsendero, function (err, sendero) {
		if (!err){
			res.send(sendero);
		} else {
			res.send(resErr);
		}
	});
};
