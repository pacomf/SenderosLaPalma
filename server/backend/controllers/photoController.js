var mongoose = require('mongoose'),
	utils = require('./utilities/conversionDataModel'),
    photoModel = require('../models/photoModel');

exports.addPhoto = function (req, res){
	var newPhoto= new commentModel({
		id_sendero: req.params.idsendero,
    	id_owner: req.params.idowner,
    	url: req.params.url,
    	geo: utils.toGeo(req.params.latitude, req.params.longitude)
	});
	newPhoto.save();
	console.log("Added Photo "+newPhoto._id);
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