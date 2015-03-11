var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel'),
    photoModel = require('../models/photoModel');

var resOk = {"res" : "ok"};
var resErr = {"res" : "err"};

exports.addPhoto = function (req, res){
	var newPhoto= new photoModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	url: req.body.url,
    	geo: utils.toGeo(req.body.latitude, req.body.longitude)
	});
	newPhoto.save();
	console.log("Added Photo "+newPhoto._id);
	res.send(resOk);
}

exports.getPhotos = function (req, res) {
	photoModel.find({id_sendero: req.params.idsendero}).exec(function (err, photos) {
		if (!err){
			res.send(photos);
		} else {
			res.send(resErr);
		}
	});
};