var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel'),
    senderoModel = require('../models/senderoModel'),
    photoModel = require('../models/photoModel'),
    ratingModel = require('../models/ratingModel'),
    commentModel = require('../models/commentModel');

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

exports.updateSendero = function(name, regular_name, img_url){
	senderoModel.find({regular_name: regular_name}).exec(function (err, senderos) {
		if (senderos.length == 0){
			console.log("No encontrada el sendero: "+regular_name);
			return;
		}
		senderos[0].name = name;
		var newPhoto= new photoModel({
			id_sendero: senderos[0]._id,
    		id_owner: "root",
    		date: new Date(),
    		url: img_url
		});
		newPhoto.save();
		senderos[0].save();
		console.log("Update Sendero "+senderos[0]._id);
	});
}

exports.updateSenderoWaterPoints = function(regular_name, geoWP){
	senderoModel.find({regular_name: regular_name}).exec(function (err, senderos) {
		if (senderos.length == 0){
			console.log("No encontrada el sendero: "+regular_name);
			return;
		}
		senderos[0].water_points.push(geoWP);
		senderos[0].save();
		console.log("Update Sendero "+senderos[0]._id);
	});
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
		query.length={};
		query.length.$gte= parseFloat(queryReq.minLength);
		query.length.$lte= parseFloat(queryReq.maxLength);
	} else if (!checkEmpty(queryReq.minLength)){
		query.length={};
		query.length.$gte= parseFloat(queryReq.minLength);
	} else if (!checkEmpty(queryReq.maxLength)){
		query.length={};
		query.length.$lte= parseFloat(queryReq.maxLength);
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
			res.send([]);
		}
	});
};

function getSenderosByQuery (req, res){
	var amount = 20;
	if (!checkEmpty(req.query.amount))
		amount = parseInt(req.query.amount);
	senderoModel.find(getQuery(req.query), getNoSelect(req.query)).limit(amount).exec(function (err, senderos) {
		if (!err){
			res.send(senderos);
		} else {
			res.send([]);
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
	var comments = req.query.comments ? (!checkEmpty(req.query) && !checkEmpty(req.query.comments)): 20;
	var ratings = req.query.ratings ? (!checkEmpty(req.query) && !checkEmpty(req.query.ratings)): 20;
	var photos = req.query.photos ? (!checkEmpty(req.query) && !checkEmpty(req.query.photos)): 20;
	var date;
	if (!checkEmpty(req.query) && !checkEmpty(req.query.date)){
		date = new Date(req.query.date);
	} else {
		date = new Date("2015-03-24T04:08:15.162Z");
	}
	var ret = {};
	senderoModel.findById(req.params.idsendero, function (err, sendero) {
		if ((!err) && (sendero !== null) && (sendero !== undefined)){
			ret.sendero = sendero._id;
			ret.rating = sendero.rating;
			ret.update = new Date();
			commentModel.find({id_sendero: req.params.idsendero, date: {"$gte": date}}).limit(comments).sort({'date': -1}).exec(function (err, comments) {
				if (!err){
					ret.comments = comments;
					photoModel.find({id_sendero: req.params.idsendero, date: {"$gte": date}}).limit(photos).sort({'date': -1}).exec(function (err, photos) {
						if (!err){
							ret.photos = photos;
							if (!checkEmpty(req.query) && !checkEmpty(req.query.user)){
								ratingModel.findOne({id_sendero: req.params.idsendero, id_owner: req.query.user}).exec(function (err, rating) {
									ret.userrating = rating.rating;
									res.send(ret);
								});
							} else {
								res.send(ret);
							}
						} else {
							res.send(resErr);
						}
					});
				} else {
					res.send(resErr);
				}
			});
		} else {
			res.send(resErr);
		}
	});
};
